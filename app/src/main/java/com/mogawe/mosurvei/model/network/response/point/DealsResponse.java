package com.mogawe.mosurvei.model.network.response.point;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.Deals;
import com.mogawe.mosurvei.model.db.entity.DealsTransactions;
import com.mogawe.mosurvei.model.db.entity.WalletHistory;
import com.mogawe.mosurvei.model.network.response.GenericResp;
import com.mogawe.mosurvei.model.network.response.task.MyTaskResponse;

import java.util.List;

public class DealsResponse extends GenericResp {
    @SerializedName("object")
    @Expose
    private List<Deals> deals;

    private List<DealsTransactions> dealsTransactions;

    private DealsTransactions dealsTransaction;

    RequestKey requestKey;

    public List<Deals> getDeals() {
        return deals;
    }

    public void setDeals(List<Deals> deals) {
        this.deals = deals;
    }

    public enum RequestKey {
        GET_MY_VOUCHER,
        FAILED,
        GET_DEALS,
        REDEEM_VOUCHER,
        GET_URL_ORDER_UV
    }

    public RequestKey getRequestKey() {
        return requestKey;
    }

    public void setRequestKey(RequestKey requestKey) {
        this.requestKey = requestKey;
    }

    public List<DealsTransactions> getDealsTransactions() {
        return dealsTransactions;
    }

    public void setDealsTransactions(List<DealsTransactions> dealsTransactions) {
        this.dealsTransactions = dealsTransactions;
    }

    public DealsTransactions getDealsTransaction() {
        return dealsTransaction;
    }

    public void setDealsTransaction(DealsTransactions dealsTransaction) {
        this.dealsTransaction = dealsTransaction;
    }
}
