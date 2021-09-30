package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.Product;

import java.io.Serializable;
import java.util.List;

public class ProductResponse extends GenericResponse implements Serializable {

    @SerializedName("object")
    @Expose
    private List<Product> productList;

    @SerializedName("object2")
    @Expose
    private List<Product> productList2;


    @SerializedName("rowCount")
    @Expose
    private Integer rowCount;

    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;

    @SerializedName("offset")
    @Expose
    private Integer offset;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList2() {
        return productList2;
    }

    public void setProductList2(List<Product> productList2) {
        this.productList2 = productList2;
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
