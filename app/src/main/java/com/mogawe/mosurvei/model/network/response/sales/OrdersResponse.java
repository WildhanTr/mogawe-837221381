package com.mogawe.mosurvei.model.network.response.sales;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.Order;
import com.mogawe.mosurvei.model.db.entity.Catalog;
import com.mogawe.mosurvei.model.db.entity.OrderItem;
import com.mogawe.mosurvei.model.network.response.GenericResp;

import java.util.List;

public class OrdersResponse extends GenericResp {

    @SerializedName("rowCount")
    @Expose
    private Integer rowCount;
    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("object")
    @Expose
    private List<Order> orderItemList;

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

    public List<Order> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<Order> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
