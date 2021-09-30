package com.mogawe.mosurvei.model.network.response.sales;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.Catalog;
import com.mogawe.mosurvei.model.db.entity.DetailSummary;
import com.mogawe.mosurvei.model.network.response.GenericResp;

public class DetailSummaryResponse extends GenericResp {

    @SerializedName("object")
    @Expose
    private DetailSummary detailSummary;

    public DetailSummary getDetailSummary() {
        return detailSummary;
    }

    public void setDetailSummary(DetailSummary detailSummary) {
        this.detailSummary = detailSummary;
    }
}
