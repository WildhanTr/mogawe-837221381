package com.mogawe.mosurvei.viewmodel;

import android.app.Application;

import com.mogawe.mosurvei.MoSurveiApplication;
import com.mogawe.mosurvei.model.db.entity.User;
import com.mogawe.mosurvei.model.db.entity.WalletHistory;
import com.mogawe.mosurvei.model.network.response.transaction.TransactionResponse;
import com.mogawe.mosurvei.model.network.response.transaction.WalletHistoryResponse;
import com.mogawe.mosurvei.model.repository.TransactionRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TransactionViewModel extends AndroidViewModel {
    private TransactionRepository transactionRepository;
    private LiveData<TransactionResponse> responseLiveData;
    private LiveData<List<WalletHistory>> walletHistoryLiveData;


    public TransactionViewModel(@NonNull Application application) {
        super(application);
        transactionRepository = new TransactionRepository((MoSurveiApplication) application);
        responseLiveData = transactionRepository.getResponseLiveData();
        walletHistoryLiveData = transactionRepository.getWalletHistoryLiveData();
    }

    public void getWalletHistoriesFromServer(String DateFrom, String DateTo) {
        transactionRepository.getFromServer(DateFrom, DateTo);
    }

    public void transaction(User user, String transactionType, Integer amount, String description) {
        transactionRepository.mogawersTransaction(user, transactionType, amount, description);
    }
    public void requestCashOut(User user, String transactionType, Integer amount, String description, String channel) {
        transactionRepository.mogawersCashOut(user, transactionType, amount, description, channel);
    }

    public void getPendingPayment(){
        transactionRepository.getPendingPayment(null, null);
    }

    public LiveData<TransactionResponse> getResponseLiveData() {
        return responseLiveData;
    }

    public LiveData<List<WalletHistory>> getWalletHistoryLiveData() {
        return walletHistoryLiveData;
    }

    public void clear() {
        transactionRepository.clearTransactions();
    }
}
