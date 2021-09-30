package com.mogawe.mosurvei.model.repository;

import android.os.AsyncTask;

import com.mogawe.mosurvei.MoSurveiApplication;
import com.mogawe.mosurvei.model.db.dao.TaskDao;
import com.mogawe.mosurvei.model.db.dao.UserDao;
import com.mogawe.mosurvei.model.db.dao.WalletHistoryDao;
import com.mogawe.mosurvei.model.db.entity.User;
import com.mogawe.mosurvei.model.db.entity.WalletHistory;
import com.mogawe.mosurvei.model.network.request.transaction.CashOutRequest;
import com.mogawe.mosurvei.model.network.request.transaction.GetPendingPaymentRequest;
import com.mogawe.mosurvei.model.network.request.transaction.WalletHistoryRequest;
import com.mogawe.mosurvei.model.network.request.user.TransactionRequest;
import com.mogawe.mosurvei.model.network.response.GenericResponse;
import com.mogawe.mosurvei.model.network.response.transaction.PendingPaymentResponse;
import com.mogawe.mosurvei.model.network.response.transaction.TransactionResponse;
import com.mogawe.mosurvei.model.network.response.transaction.WalletHistoryResponse;
import com.mogawe.mosurvei.model.store.PrefHelper;
import com.mogawe.mosurvei.model.store.PrefKey;
import com.mogawe.mosurvei.util.PassDigest;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TransactionRepository {

    private static final String ERROR_CODE = "001";
    private static final String ERROR_MESSAGE_SERVER = "Gagal terhubung ke server, nih.\nCoba cek koneksi internetmu dan coba lagi, ya!";
    private static final String ERROR_MESSAGE_INTERNET = "Coba cek koneksi internetmu, ya!";

    private MoSurveiApplication application;
    private MutableLiveData<TransactionResponse> responseLiveData = new MutableLiveData<>();
    private LiveData<List<WalletHistory>> walletHistoryLiveData;
    private WalletHistoryDao walletHistoryDao;
    private UserDao userDao;

    public TransactionRepository(MoSurveiApplication application) {
        this.application = application;
        if (application != null) {
            if (application.getDatabase() != null) {
                walletHistoryDao = application.getDatabase().walletHistoryDao();
                userDao = application.getDatabase().userDao();
                walletHistoryLiveData = walletHistoryDao.findAll();
            }
        }
    }

    public MutableLiveData<TransactionResponse> getResponseLiveData() {
        return responseLiveData;
    }

    public LiveData<List<WalletHistory>> getWalletHistoryLiveData() {
        if (walletHistoryLiveData.getValue() == null) {
//            if (PrefHelper.hasString(PrefKey.TOKEN))
            getFromServer(null, null);
        }
        return walletHistoryLiveData;
    }

    public void getFromServer(String dateFrom, String dateTo) {
        WalletHistoryRequest request = new WalletHistoryRequest(dateFrom, dateTo);
        application.getApiUserService().transactionHistory(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WalletHistoryResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        TransactionResponse transactionResponse = new TransactionResponse();
                        transactionResponse.setReturnValue("001");
                        transactionResponse.setRequestKey(TransactionResponse.RequestKey.FAILED);
                        responseLiveData.setValue(transactionResponse);
                    }

                    @Override
                    public void onNext(WalletHistoryResponse walletHistoryResponse) {
                        if (walletHistoryResponse.isSuccess()) {
                            saveToDatabase(walletHistoryResponse.getWalletHistories());
                        }
                    }
                });
    }

    public void mogawersTransaction(User user, String transactionType, Integer amount, String description) {
        TransactionRequest request = new TransactionRequest();
        request.setId_mogawers(user.getId_mogawers());
        request.setAmount(amount);
        request.setTransaction_type(transactionType);
        request.setDescription(description);
        try {
            request.setPassword(PassDigest.SHA256(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        application.getApiUserService().transaction(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TransactionResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        TransactionResponse transactionResponse = new TransactionResponse();
                        transactionResponse.setReturnValue("001");
                        transactionResponse.setRequestKey(TransactionResponse.RequestKey.FAILED);
                        responseLiveData.setValue(transactionResponse);
                    }

                    @Override
                    public void onNext(TransactionResponse response) {
                        if(response.isSuccess()){
                            response.setRequestKey(TransactionResponse.RequestKey.TRANSACTION_SUCCESS);

                            //UPDATE BALANCE TO LOCAL DB
                            user.setBalance(user.getBalance() + amount);
                            updateUser(user);

                            responseLiveData.setValue(response);
                        }else{
                            if(response.getMessage().equals("Saldo mogawers tidak mencukupi")){
                                response.setRequestKey(TransactionResponse.RequestKey.INSUFFICIENT_BALANCE);
                            }else{
                                response.setRequestKey(TransactionResponse.RequestKey.TRANSACTION_FAILED);
                            }
                            responseLiveData.setValue(response);
                        }
                    }
                });
    }

    public void mogawersCashOut(User user, String transactionType, Integer amount, String description, String channel) {
        CashOutRequest request = new CashOutRequest();
        request.setIdMogawers(user.getId_mogawers());
        request.setTransactionType("REQUEST_CASHOUT");
        request.setRequestedChannel(channel);
        request.setAmount(amount);
        request.setReference("");
        request.setDescription(description);

        try {
            request.setPassword(PassDigest.SHA256(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        application.getApiUserService().requestCashout(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GenericResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        TransactionResponse transactionResponse = new TransactionResponse();
                        transactionResponse.setReturnValue("001");
                        transactionResponse.setRequestKey(TransactionResponse.RequestKey.FAILED);
                        responseLiveData.setValue(transactionResponse);
                    }

                    @Override
                    public void onNext(GenericResponse response) {
                        if(response.isSuccess()){
                            TransactionResponse transactionResponse = new TransactionResponse();
                            transactionResponse.setRequestKey(TransactionResponse.RequestKey.CASH_OUT_REQUEST_SUCCESS);
                            transactionResponse.setMessage(response.getMessage());
                            transactionResponse.setReturnValue(response.getReturnValue());

                            //UPDATE BALANCE TO LOCAL DB
                            user.setPending_payment(user.getPending_payment() == null ? 0 + amount : user.getPending_payment() + amount);
                            updateUser(user);

                            responseLiveData.setValue(transactionResponse);
                        }else{
                            TransactionResponse transactionResponse = new TransactionResponse();
                            transactionResponse.setRequestKey(TransactionResponse.RequestKey.TRANSACTION_FAILED);
                            transactionResponse.setMessage(response.getMessage());
                            transactionResponse.setReturnValue(response.getReturnValue());

                            responseLiveData.setValue(transactionResponse);
                        }
                    }
                });
    }

    public void getPendingPayment(String dateFrom, String dateEnd) {
        GetPendingPaymentRequest request = new GetPendingPaymentRequest();
        request.setDateFrom(null);
        request.setDateTo(null);

        application.getApiUserService().getPendingPayment(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PendingPaymentResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        TransactionResponse transactionResponse = new TransactionResponse();
                        transactionResponse.setReturnValue("001");
                        transactionResponse.setRequestKey(TransactionResponse.RequestKey.FAILED);
                        responseLiveData.setValue(transactionResponse);
                    }

                    @Override
                    public void onNext(PendingPaymentResponse response) {
                        if(response.isSuccess()){
                            TransactionResponse transactionResponse = new TransactionResponse();
                            transactionResponse.setRequestKey(TransactionResponse.RequestKey.GET_PENDING_PAYMENT);
                            transactionResponse.setReturnValue(response.getReturnValue());
                            transactionResponse.setMessage(response.getMessage());
                            transactionResponse.setPendingPayments(response.getPendingPayments());

                            responseLiveData.setValue(transactionResponse);
                        }else{
                            TransactionResponse transactionResponse = new TransactionResponse();
                            transactionResponse.setRequestKey(TransactionResponse.RequestKey.FAILED);
                            transactionResponse.setReturnValue(response.getReturnValue());
                            transactionResponse.setMessage(response.getMessage());

                            responseLiveData.setValue(transactionResponse);
                        }
                    }

                });
    }

    public void updateUser(User user) {
        new UpdateUserAsycTask(userDao).execute(user);
//        new UpdateSpesificAsyncTask(userDao).execute(user);
    }

    public void clearTransactions(){
        new ClearDBAsyncTask(walletHistoryDao).execute();
    }

    private void saveToDatabase(List<WalletHistory> walletHistories) {
        new TransactionRepository.SaveAsyncTask(walletHistoryDao).execute(walletHistories);
    }

    private static class SaveAsyncTask extends AsyncTask<List<WalletHistory>, Void, Void> {

        private WalletHistoryDao walletHistoryDao;

        private SaveAsyncTask(WalletHistoryDao walletHistoryDao) {
            this.walletHistoryDao = walletHistoryDao;
        }

        @Override
        protected Void doInBackground(List<WalletHistory>... walletHistories) {
            walletHistoryDao.deleteAll();
            if(walletHistories.length > 0){
                walletHistoryDao.saveAll(walletHistories[0]);
            }

            return null;
        }
    }

    private static class UpdateUserAsycTask extends AsyncTask<User, Void, Void> {

        private UserDao userDao;

        private UpdateUserAsycTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    private static class ClearDBAsyncTask extends AsyncTask<Void, Void, Void> {

        private WalletHistoryDao walletHistoryDao;

        private ClearDBAsyncTask(WalletHistoryDao walletHistoryDao) {
            this.walletHistoryDao = walletHistoryDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            walletHistoryDao.deleteAll();
            return null;
        }
    }


}
