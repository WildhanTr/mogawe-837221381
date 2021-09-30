package com.mogawe.mosurvei.model.db.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderItem implements Serializable {

    @SerializedName("shipmentFee")
    private double shipmentFee;

    @SerializedName("shipmentProvinceName")
    private String shipmentProvinceName;

    @SerializedName("trackingToken")
    private String trackingToken;

    @SerializedName("shippingDate")
    private String shippingDate;

    @SerializedName("canceledReason")
    private String canceledReason;

    @SerializedName("buyerLat")
    private double buyerLat;

    @SerializedName("adminFee")
    private double adminFee;

    @SerializedName("paymentLink")
    private String paymentLink;

    @SerializedName("uuid")
    private String uuid;

    @SerializedName("shipmentType")
    private String shipmentType;

    @SerializedName("shipmentEstMin")
    private String shipmentEstMin;

    @SerializedName("paymentType")
    private String paymentType;

    @SerializedName("shipmentCityName")
    private String shipmentCityName;

    @SerializedName("deliveredDate")
    private String deliveredDate;

    @SerializedName("shipmentEstMax")
    private String shipmentEstMax;

    @SerializedName("processedDate")
    private String processedDate;

    @SerializedName("buyerAddress")
    private String buyerAddress;

    @SerializedName("total")
    private double total;

    @SerializedName("shipmentCityId")
    private int shipmentCityId;

    @SerializedName("paidDate")
    private String paidDate;

    @SerializedName("uuidMogawers")
    private String uuidMogawers;

    @SerializedName("price")
    private double price;

    @SerializedName("uuidProduct")
    private String uuidProduct;

    @SerializedName("resellerAddress")
    private String resellerAddress;

    @SerializedName("paymentOrderId")
    private String paymentOrderId;

    @SerializedName("buyerLng")
    private double buyerLng;

    @SerializedName("commission")
    private double commission;

    @SerializedName("shipmentService")
    private String shipmentService;

    @SerializedName("paymentStatus")
    private String paymentStatus;

    @SerializedName("finishedDate")
    private String finishedDate;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("resellerPhone")
    private String resellerPhone;

    @SerializedName("resellerName")
    private String resellerName;

    @SerializedName("buyerName")
    private String buyerName;

    @SerializedName("shipmentProvinceId")
    private int shipmentProvinceId;

    @SerializedName("transactionOrderId")
    private String transactionOrderId;

    @SerializedName("buyerPhone")
    private String buyerPhone;

    @SerializedName("canceledDate")
    private String canceledDate;

    @SerializedName("paymentMethod")
    private String paymentMethod;

    @SerializedName("shipmentResi")
    private String shipmentResi;

    @SerializedName("shipmentName")
    private String shipmentName;

    @SerializedName("orderDate")
    private String orderDate;

    @SerializedName("status")
    private String status;

    @SerializedName("productName")
    private String productName;

    @SerializedName("imageUrl")
    private String imageUrl;

    public double getShipmentFee() {
        return shipmentFee;
    }

    public void setShipmentFee(double shipmentFee) {
        this.shipmentFee = shipmentFee;
    }

    public String getShipmentProvinceName() {
        return shipmentProvinceName;
    }

    public void setShipmentProvinceName(String shipmentProvinceName) {
        this.shipmentProvinceName = shipmentProvinceName;
    }

    public String getTrackingToken() {
        return trackingToken;
    }

    public void setTrackingToken(String trackingToken) {
        this.trackingToken = trackingToken;
    }

    public String getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(String shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getCanceledReason() {
        return canceledReason;
    }

    public void setCanceledReason(String canceledReason) {
        this.canceledReason = canceledReason;
    }

    public double getBuyerLat() {
        return buyerLat;
    }

    public void setBuyerLat(double buyerLat) {
        this.buyerLat = buyerLat;
    }

    public double getAdminFee() {
        return adminFee;
    }

    public void setAdminFee(double adminFee) {
        this.adminFee = adminFee;
    }

    public String getPaymentLink() {
        return paymentLink;
    }

    public void setPaymentLink(String paymentLink) {
        this.paymentLink = paymentLink;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(String shipmentType) {
        this.shipmentType = shipmentType;
    }

    public String getShipmentEstMin() {
        return shipmentEstMin;
    }

    public void setShipmentEstMin(String shipmentEstMin) {
        this.shipmentEstMin = shipmentEstMin;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getShipmentCityName() {
        return shipmentCityName;
    }

    public void setShipmentCityName(String shipmentCityName) {
        this.shipmentCityName = shipmentCityName;
    }

    public String getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(String deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public String getShipmentEstMax() {
        return shipmentEstMax;
    }

    public void setShipmentEstMax(String shipmentEstMax) {
        this.shipmentEstMax = shipmentEstMax;
    }

    public String getProcessedDate() {
        return processedDate;
    }

    public void setProcessedDate(String processedDate) {
        this.processedDate = processedDate;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getShipmentCityId() {
        return shipmentCityId;
    }

    public void setShipmentCityId(int shipmentCityId) {
        this.shipmentCityId = shipmentCityId;
    }

    public String getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
    }

    public String getUuidMogawers() {
        return uuidMogawers;
    }

    public void setUuidMogawers(String uuidMogawers) {
        this.uuidMogawers = uuidMogawers;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUuidProduct() {
        return uuidProduct;
    }

    public void setUuidProduct(String uuidProduct) {
        this.uuidProduct = uuidProduct;
    }

    public String getResellerAddress() {
        return resellerAddress;
    }

    public void setResellerAddress(String resellerAddress) {
        this.resellerAddress = resellerAddress;
    }

    public String getPaymentOrderId() {
        return paymentOrderId;
    }

    public void setPaymentOrderId(String paymentOrderId) {
        this.paymentOrderId = paymentOrderId;
    }

    public double getBuyerLng() {
        return buyerLng;
    }

    public void setBuyerLng(double buyerLng) {
        this.buyerLng = buyerLng;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public String getShipmentService() {
        return shipmentService;
    }

    public void setShipmentService(String shipmentService) {
        this.shipmentService = shipmentService;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(String finishedDate) {
        this.finishedDate = finishedDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getResellerPhone() {
        return resellerPhone;
    }

    public void setResellerPhone(String resellerPhone) {
        this.resellerPhone = resellerPhone;
    }

    public String getResellerName() {
        return resellerName;
    }

    public void setResellerName(String resellerName) {
        this.resellerName = resellerName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public int getShipmentProvinceId() {
        return shipmentProvinceId;
    }

    public void setShipmentProvinceId(int shipmentProvinceId) {
        this.shipmentProvinceId = shipmentProvinceId;
    }

    public String getTransactionOrderId() {
        return transactionOrderId;
    }

    public void setTransactionOrderId(String transactionOrderId) {
        this.transactionOrderId = transactionOrderId;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getCanceledDate() {
        return canceledDate;
    }

    public void setCanceledDate(String canceledDate) {
        this.canceledDate = canceledDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getShipmentResi() {
        return shipmentResi;
    }

    public void setShipmentResi(String shipmentResi) {
        this.shipmentResi = shipmentResi;
    }

    public String getShipmentName() {
        return shipmentName;
    }

    public void setShipmentName(String shipmentName) {
        this.shipmentName = shipmentName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}