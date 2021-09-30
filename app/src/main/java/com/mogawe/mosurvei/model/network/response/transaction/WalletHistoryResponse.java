package com.mogawe.mosurvei.model.network.response.transaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.WalletHistory;
import com.mogawe.mosurvei.model.network.response.GenericResp;

import java.util.List;

public class WalletHistoryResponse extends TransactionResponse {
    @SerializedName("object")
    @Expose
    private List<WalletHistory> walletHistories;

    public List<WalletHistory> getWalletHistories() {
        return walletHistories;
    }

    public void setWalletHistories(List<WalletHistory> walletHistories) {
        this.walletHistories = walletHistories;
    }
}
