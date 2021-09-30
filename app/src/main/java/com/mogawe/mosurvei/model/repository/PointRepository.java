package com.mogawe.mosurvei.model.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mogawe.mosurvei.MoSurveiApplication;
import com.mogawe.mosurvei.model.db.dao.DealsDao;
import com.mogawe.mosurvei.model.db.dao.UserDao;
import com.mogawe.mosurvei.model.db.entity.Deals;
import com.mogawe.mosurvei.model.db.entity.WalletHistory;
import com.mogawe.mosurvei.model.network.request.point.RedeemRequest;
import com.mogawe.mosurvei.model.network.response.GenericResponse;
import com.mogawe.mosurvei.model.network.response.point.DealTransactionResponse;
import com.mogawe.mosurvei.model.network.response.point.DealsResponse;
import com.mogawe.mosurvei.model.network.response.point.DealsTransactionsResponse;
import com.mogawe.mosurvei.model.network.response.transaction.TransactionResponse;
import com.mogawe.mosurvei.model.store.PrefHelper;
import com.mogawe.mosurvei.model.store.PrefKey;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PointRepository {

    private MoSurveiApplication application;
    private MutableLiveData<DealsResponse> responseLiveData = new MutableLiveData<>();
    private LiveData<List<Deals>> dealsLiveData;
    private DealsDao dealsDao;

    public PointRepository(MoSurveiApplication application) {
        this.application = application;
        if (application != null) {
            if (application.getDatabase() != null) {
                dealsDao = application.getDatabase().dealsDao();
                dealsLiveData = dealsDao.findAll();
            }
        }
    }

    public MutableLiveData<DealsResponse> getResponseLiveData() {
        return responseLiveData;
    }

//    public LiveData<List<Deals>> getDealsLiveData() {
//        if (dealsLiveData.getValue() == null) {
////            if (PrefHelper.hasString(PrefKey.TOKEN))
//            getDeals();
//        }
//        return dealsLiveData;
//    }

    public LiveData<DealsResponse> getDeals() {
        application.getApiUserService().getDeals(PrefHelper.getString(PrefKey.TOKEN))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DealsResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(DealsResponse response) {
                        DealsResponse dealsResponse = new DealsResponse();
                        if (response.isSuccess()) {
                            dealsResponse.setMessage(response.getMessage());
                            dealsResponse.setReturnValue(response.getReturnValue());
                            dealsResponse.setRequestKey(DealsResponse.RequestKey.GET_DEALS);
                            dealsResponse.setDeals(response.getDeals());
                            System.out.println("askdas" + dealsResponse);
                            responseLiveData.setValue(dealsResponse);
                        } else {
                            dealsResponse.setMessage(response.getMessage());
                            dealsResponse.setReturnValue(response.getReturnValue());
                            dealsResponse.setRequestKey(DealsResponse.RequestKey.FAILED);
                            dealsResponse.setDeals(response.getDeals());

                            responseLiveData.setValue(dealsResponse);
                        }
                    }
                });
        return responseLiveData;
    }

    public LiveData<DealsResponse> getMyVoucher() {
        application.getApiUserService().getMyVoucher(PrefHelper.getString(PrefKey.TOKEN))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DealsTransactionsResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(DealsTransactionsResponse response) {
                        DealsResponse dealsResponse = new DealsResponse();
                        if (response.isSuccess()) {
                            dealsResponse.setReturnValue(response.getReturnValue());
                            dealsResponse.setMessage(response.getMessage());
                            dealsResponse.setRequestKey(DealsResponse.RequestKey.GET_MY_VOUCHER);
                            dealsResponse.setDealsTransactions(response.getDealsTransactions());

                            responseLiveData.setValue(dealsResponse);
                        } else {
                            dealsResponse.setReturnValue(response.getReturnValue());
                            dealsResponse.setMessage(response.getMessage());
                            dealsResponse.setRequestKey(DealsResponse.RequestKey.FAILED);
                            dealsResponse.setDealsTransactions(response.getDealsTransactions());

                            responseLiveData.setValue(dealsResponse);
                        }
                    }
                });
        return responseLiveData;
    }

    public LiveData<DealsResponse> redeemVoucher(Deals deals, Integer count) {

        application.getApiUserService().redeemVoucher(PrefHelper.getString(PrefKey.TOKEN), new RedeemRequest(deals.getUuid(), count))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GenericResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        DealsResponse dealsResponse = new DealsResponse();
                        dealsResponse.setReturnValue("001");
                        dealsResponse.setMessage("Terjadi kesalahan");
                        dealsResponse.setRequestKey(DealsResponse.RequestKey.FAILED);

                        responseLiveData.setValue(dealsResponse);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(GenericResponse response) {
                        DealsResponse dealsResponse = new DealsResponse();
                        if (response.isSuccess()) {
                            dealsResponse.setReturnValue(response.getReturnValue());
                            dealsResponse.setMessage(response.getMessage());
                            dealsResponse.setRequestKey(DealsResponse.RequestKey.REDEEM_VOUCHER);

                            responseLiveData.setValue(dealsResponse);
                        } else {
                            dealsResponse.setReturnValue(response.getReturnValue());
                            dealsResponse.setMessage(response.getMessage());
                            dealsResponse.setRequestKey(DealsResponse.RequestKey.FAILED);

                            responseLiveData.setValue(dealsResponse);
                        }
                    }
                });
        return responseLiveData;
    }

    public LiveData<DealsResponse> getUrlOrderUV(String uuid) {
        application.getApiUserService().getUrlOrderUV(PrefHelper.getString(PrefKey.TOKEN), uuid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DealTransactionResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(DealTransactionResponse response) {
                        DealsResponse dealsResponse = new DealsResponse();
                        if (response.isSuccess()) {
                            dealsResponse.setReturnValue(response.getReturnValue());
                            dealsResponse.setMessage(response.getMessage());
                            dealsResponse.setRequestKey(DealsResponse.RequestKey.GET_URL_ORDER_UV);
                            dealsResponse.setDealsTransaction(response.getDealsTransactions());

                            responseLiveData.setValue(dealsResponse);
                        } else {
                            dealsResponse.setReturnValue(response.getReturnValue());
                            dealsResponse.setMessage(response.getMessage());
                            dealsResponse.setRequestKey(DealsResponse.RequestKey.FAILED);

                            responseLiveData.setValue(dealsResponse);
                        }
                    }
                });
        return responseLiveData;
    }

}
