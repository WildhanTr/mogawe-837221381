package com.mogawe.mosurvei.model.network.response.point;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.DealsTransactions;
import com.mogawe.mosurvei.model.db.entity.Deals;
import com.mogawe.mosurvei.model.network.response.GenericResp;

import java.util.List;

public class DealsTransactionsResponse extends GenericResp {
    @SerializedName("object")
    @Expose
    private List<DealsTransactions> dealsTransactions;

    public List<DealsTransactions> getDealsTransactions() {
        return dealsTransactions;
    }

    public void setDealsTransactions(List<DealsTransactions> dealsTransactions) {
        this.dealsTransactions = dealsTransactions;
    }
}
