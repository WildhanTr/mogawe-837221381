package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.Certificate;
import com.mogawe.mosurvei.model.db.entity.CategorySupplierProduct;

import java.io.Serializable;
import java.util.List;

public class ProductCategoryListResponse extends GenericResp implements Serializable {

    @SerializedName("object")
    @Expose
    private List<CategorySupplierProduct> object;

    public List<CategorySupplierProduct> getObject() {
        return object;
    }

    public void setObject(List<CategorySupplierProduct> object) {
        this.object = object;
    }
}
