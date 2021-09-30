package com.mogawe.mosurvei.model.network.response.point;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.DealsTransactions;
import com.mogawe.mosurvei.model.network.response.GenericResp;
import com.mogawe.mosurvei.model.network.response.GenericResponse;

import java.util.List;

public class DealTransactionResponse extends GenericResp {
    @SerializedName("object")
    @Expose
    private DealsTransactions dealsTransactions;

    public DealsTransactions getDealsTransactions() {
        return dealsTransactions;
    }

    public void setDealsTransactions(DealsTransactions dealsTransactions) {
        this.dealsTransactions = dealsTransactions;
    }
}
