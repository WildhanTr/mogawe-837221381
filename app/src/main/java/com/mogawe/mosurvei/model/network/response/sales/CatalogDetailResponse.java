package com.mogawe.mosurvei.model.network.response.sales;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.Catalog;
import com.mogawe.mosurvei.model.network.response.GenericResp;

import java.util.List;

public class CatalogDetailResponse extends GenericResp {

    @SerializedName("object")
    @Expose
    private Catalog catalog;

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }
}
