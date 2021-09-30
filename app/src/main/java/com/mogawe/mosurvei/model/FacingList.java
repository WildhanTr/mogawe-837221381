package com.mogawe.mosurvei.model;

import java.io.Serializable;

public class FacingList implements Serializable {


    String uuid;
    String productName;
    String urlImageProduct;

    Integer idItem;
    String uuidAdapter;

    public FacingList(String productName, String uuid) {
        this.productName = productName;
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getUuidAdapter() {
        return uuidAdapter;
    }

    public void setUuidAdapter(String uuidAdapter) {
        this.uuidAdapter = uuidAdapter;
    }

    public String getUrlImageProduct() {
        return urlImageProduct;
    }

    public void setUrlImageProduct(String urlImageProduct) {
        this.urlImageProduct = urlImageProduct;
    }
}
