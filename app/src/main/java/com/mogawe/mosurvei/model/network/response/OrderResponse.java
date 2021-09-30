package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.Order;
import com.mogawe.mosurvei.model.db.entity.Catalog;

import java.io.Serializable;

public class OrderResponse extends GenericResp implements Serializable {

    @SerializedName("object")
    @Expose
    private Order object;

    public Order getObject() {
        return object;
    }

    public void setObject(Order object) {
        this.object = object;
    }

}
