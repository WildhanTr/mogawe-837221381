package com.mogawe.mosurvei.model.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Order implements Serializable {

    @SerializedName("uuidProduct")
    @Expose
    private String uuidProduct;
    @SerializedName("buyerName")
    @Expose
    private String buyerName;
    @SerializedName("buyerPhone")
    @Expose
    private String buyerPhone;
    @SerializedName("buyerAddress")
    @Expose
    private String buyerAddress;
    @SerializedName("buyerLat")
    @Expose
    private Double buyerLat;
    @SerializedName("buyerLng")
    @Expose
    private Double buyerLng;
    @SerializedName("shipmentType")
    @Expose
    private String shipmentType;
    @SerializedName("shipmentName")
    @Expose
    private String shipmentName;
    @SerializedName("shipmentFee")
    @Expose
    private Integer shipmentFee;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("resellerName")
    @Expose
    private String resellerName;
    @SerializedName("resellerPhone")
    @Expose
    private String resellerPhone;
    @SerializedName("resellerAddress")
    @Expose
    private String resellerAddress;
    @SerializedName("paymentMethod")
    @Expose
    private String paymentMethod;

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("uuidMogawers")
    @Expose
    private String uuidMogawers;
    @SerializedName("shipmentService")
    @Expose
    private String shipmentService;
    @SerializedName("shipmentResi")
    @Expose
    private String shipmentResi;
    @SerializedName("shipmentProvinceId")
    @Expose
    private String shipmentProvinceId;
    @SerializedName("shipmentProvinceName")
    @Expose
    private String shipmentProvinceName;
    @SerializedName("shipmentCityId")
    @Expose
    private String shipmentCityId;
    @SerializedName("shipmentCityName")
    @Expose
    private String shipmentCityName;
    @SerializedName("shipmentEstMax")
    @Expose
    private String shipmentEstMax;
    @SerializedName("shipmentEstMin")
    @Expose
    private String shipmentEstMin;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("total")
    @Expose
    private Double total;
    @SerializedName("commission")
    @Expose
    private Double commission;
    @SerializedName("adminFee")
    @Expose
    private Double adminFee;
    @SerializedName("transactionOrderId")
    @Expose
    private String transactionOrderId;
    @SerializedName("paymentOrderId")
    @Expose
    private String paymentOrderId;
    @SerializedName("paymentType")
    @Expose
    private String paymentType;
    @SerializedName("paymentLink")
    @Expose
    private String paymentLink;
    @SerializedName("paymentStatus")
    @Expose
    private String paymentStatus;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("orderDate")
    @Expose
    private String orderDate;
    @SerializedName("paidDate")
    @Expose
    private String paidDate;
    @SerializedName("processedDate")
    @Expose
    private String processedDate;
    @SerializedName("shippingDate")
    @Expose
    private String shippingDate;
    @SerializedName("deliveredDate")
    @Expose
    private String deliveredDate;
    @SerializedName("finishedDate")
    @Expose
    private String finishedDate;
    @SerializedName("canceledDate")
    @Expose
    private String canceledDate;
    @SerializedName("canceledReason")
    @Expose
    private String canceledReason;
    @SerializedName("trackingToken")
    @Expose
    private String trackingToken;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("additionalNotes")
    @Expose
    private String additionalNotes;
    @SerializedName("isShippingTakeAway")
    @Expose
    private String isShippingTakeAway;
    @SerializedName("isShippingOwnCourier")
    @Expose
    private String isShippingOwnCourier;
    @SerializedName("isShippingExpedition")
    @Expose
    private String isShippingExpedition;


    public String getUuidProduct() {
        return uuidProduct;
    }

    public void setUuidProduct(String uuidProduct) {
        this.uuidProduct = uuidProduct;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public Double getBuyerLat() {
        return buyerLat;
    }

    public void setBuyerLat(Double buyerLat) {
        this.buyerLat = buyerLat;
    }

    public Double getBuyerLng() {
        return buyerLng;
    }

    public void setBuyerLng(Double buyerLng) {
        this.buyerLng = buyerLng;
    }

    public String getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(String shipmentType) {
        this.shipmentType = shipmentType;
    }

    public String getShipmentName() {
        return shipmentName;
    }

    public void setShipmentName(String shipmentName) {
        this.shipmentName = shipmentName;
    }

    public Integer getShipmentFee() {
        return shipmentFee;
    }

    public void setShipmentFee(Integer shipmentFee) {
        this.shipmentFee = shipmentFee;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getResellerName() {
        return resellerName;
    }

    public void setResellerName(String resellerName) {
        this.resellerName = resellerName;
    }

    public String getResellerPhone() {
        return resellerPhone;
    }

    public void setResellerPhone(String resellerPhone) {
        this.resellerPhone = resellerPhone;
    }

    public String getResellerAddress() {
        return resellerAddress;
    }

    public void setResellerAddress(String resellerAddress) {
        this.resellerAddress = resellerAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
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

    public String getUuidMogawers() {
        return uuidMogawers;
    }

    public void setUuidMogawers(String uuidMogawers) {
        this.uuidMogawers = uuidMogawers;
    }

    public String getShipmentService() {
        return shipmentService;
    }

    public void setShipmentService(String shipmentService) {
        this.shipmentService = shipmentService;
    }

    public String getShipmentResi() {
        return shipmentResi;
    }

    public void setShipmentResi(String shipmentResi) {
        this.shipmentResi = shipmentResi;
    }

    public String getShipmentProvinceId() {
        return shipmentProvinceId;
    }

    public void setShipmentProvinceId(String shipmentProvinceId) {
        this.shipmentProvinceId = shipmentProvinceId;
    }

    public String getShipmentProvinceName() {
        return shipmentProvinceName;
    }

    public void setShipmentProvinceName(String shipmentProvinceName) {
        this.shipmentProvinceName = shipmentProvinceName;
    }

    public String getShipmentCityId() {
        return shipmentCityId;
    }

    public void setShipmentCityId(String shipmentCityId) {
        this.shipmentCityId = shipmentCityId;
    }

    public String getShipmentCityName() {
        return shipmentCityName;
    }

    public void setShipmentCityName(String shipmentCityName) {
        this.shipmentCityName = shipmentCityName;
    }

    public String getShipmentEstMax() {
        return shipmentEstMax;
    }

    public void setShipmentEstMax(String shipmentEstMax) {
        this.shipmentEstMax = shipmentEstMax;
    }

    public String getShipmentEstMin() {
        return shipmentEstMin;
    }

    public void setShipmentEstMin(String shipmentEstMin) {
        this.shipmentEstMin = shipmentEstMin;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Double getAdminFee() {
        return adminFee;
    }

    public void setAdminFee(Double adminFee) {
        this.adminFee = adminFee;
    }

    public String getTransactionOrderId() {
        return transactionOrderId;
    }

    public void setTransactionOrderId(String transactionOrderId) {
        this.transactionOrderId = transactionOrderId;
    }

    public String getPaymentOrderId() {
        return paymentOrderId;
    }

    public void setPaymentOrderId(String paymentOrderId) {
        this.paymentOrderId = paymentOrderId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentLink() {
        return paymentLink;
    }

    public void setPaymentLink(String paymentLink) {
        this.paymentLink = paymentLink;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
    }

    public String getProcessedDate() {
        return processedDate;
    }

    public void setProcessedDate(String processedDate) {
        this.processedDate = processedDate;
    }

    public String getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(String shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(String deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public String getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(String finishedDate) {
        this.finishedDate = finishedDate;
    }

    public String getCanceledDate() {
        return canceledDate;
    }

    public void setCanceledDate(String canceledDate) {
        this.canceledDate = canceledDate;
    }

    public String getCanceledReason() {
        return canceledReason;
    }

    public void setCanceledReason(String canceledReason) {
        this.canceledReason = canceledReason;
    }

    public String getTrackingToken() {
        return trackingToken;
    }

    public void setTrackingToken(String trackingToken) {
        this.trackingToken = trackingToken;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public String getIsShippingTakeAway() {
        return isShippingTakeAway;
    }

    public void setIsShippingTakeAway(String isShippingTakeAway) {
        this.isShippingTakeAway = isShippingTakeAway;
    }

    public String getIsShippingOwnCourier() {
        return isShippingOwnCourier;
    }

    public void setIsShippingOwnCourier(String isShippingOwnCourier) {
        this.isShippingOwnCourier = isShippingOwnCourier;
    }

    public String getIsShippingExpedition() {
        return isShippingExpedition;
    }

    public void setIsShippingExpedition(String isShippingExpedition) {
        this.isShippingExpedition = isShippingExpedition;
    }

}
