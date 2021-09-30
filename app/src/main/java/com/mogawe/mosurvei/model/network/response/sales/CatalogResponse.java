package com.mogawe.mosurvei.model.network.response.sales;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.Catalog;
import com.mogawe.mosurvei.model.db.entity.CategorySupplierProduct;
import com.mogawe.mosurvei.model.db.entity.HistoryResult;
import com.mogawe.mosurvei.model.network.response.GenericResp;
import com.mogawe.mosurvei.model.network.response.GenericResponse;
import com.mogawe.mosurvei.model.network.response.point.DealsResponse;

import java.util.List;

public class CatalogResponse extends GenericResp {

    @SerializedName("rowCount")
    @Expose
    private Integer rowCount;
    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;
    @SerializedName("object")
    @Expose
    private List<Catalog> catalogList;

    private CatalogDetailResponse catalogDetailResponse;
    private DetailSummaryResponse detailSummaryResponse;
    private CategoryProductResponse categoryProductResponse;
    private OrdersResponse ordersResponse;

    RequestKey requestKey;

    public enum RequestKey {
        FAILED,
        GET_CATALOG,
        GET_DETAIL_CATALOG,
        GET_DETAIL_SUMMARY,
        GET_PRODUK_LIST,
        GET_FAVORITE_LIST,
        ADD_TO_FAVORITE,
        REMOVE_FROM_FAVORITE,
        GET_CATEGORY_PRODUCT,
        GET_ORDER_LIST,
        PROCESS_SUCCESS,
        SENT_RESI_SUCCESS,
    }

    public RequestKey getRequestKey() {
        return requestKey;
    }

    public void setRequestKey(RequestKey requestKey) {
        this.requestKey = requestKey;
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

    public List<Catalog> getCatalogList() {
        return catalogList;
    }

    public void setCatalogList(List<Catalog> catalogList) {
        this.catalogList = catalogList;
    }

    public CatalogDetailResponse getCatalogDetailResponse() {
        return catalogDetailResponse;
    }

    public void setCatalogDetailResponse(CatalogDetailResponse catalogDetailResponse) {
        this.catalogDetailResponse = catalogDetailResponse;
    }

    public DetailSummaryResponse getDetailSummaryResponse() {
        return detailSummaryResponse;
    }

    public void setDetailSummaryResponse(DetailSummaryResponse detailSummaryResponse) {
        this.detailSummaryResponse = detailSummaryResponse;
    }

    public CategoryProductResponse getCategoryProductResponse() {
        return categoryProductResponse;
    }

    public void setCategoryProductResponse(CategoryProductResponse categoryProductResponse) {
        this.categoryProductResponse = categoryProductResponse;
    }

    public OrdersResponse getOrdersResponse() {
        return ordersResponse;
    }

    public void setOrdersResponse(OrdersResponse ordersResponse) {
        this.ordersResponse = ordersResponse;
    }
}
