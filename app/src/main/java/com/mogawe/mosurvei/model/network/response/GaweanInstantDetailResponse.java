package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.GaweanInstant;
import com.mogawe.mosurvei.model.db.entity.GaweanInstantDetail;

import java.util.List;

public class GaweanInstantDetailResponse extends GenericResp{

    @SerializedName("object")
    @Expose
    private GaweanInstantDetail gaweanInstantDetail;

    public GaweanInstantDetail getGaweanInstantDetail() {
        return gaweanInstantDetail;
    }

    public void setGaweanInstantDetail(GaweanInstantDetail gaweanInstantDetail) {
        this.gaweanInstantDetail = gaweanInstantDetail;
    }
}
