package com.mogawe.mosurvei.model.network.response.sales;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.CategorySupplierProduct;
import com.mogawe.mosurvei.model.network.response.GenericResp;

import java.util.List;

public class CategoryProductResponse extends GenericResp {

    @SerializedName("object")
    @Expose
    private List<CategorySupplierProduct> categorySupplierProductList;

    public List<CategorySupplierProduct> getCategorySupplierProductList() {
        return categorySupplierProductList;
    }

    public void setCategorySupplierProductList(List<CategorySupplierProduct> categorySupplierProductList) {
        this.categorySupplierProductList = categorySupplierProductList;
    }
}