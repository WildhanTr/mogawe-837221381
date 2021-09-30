package com.mogawe.mosurvei.model.network.request.transaction;

public class CashOutRequest {
    private Integer idMogawers;
    private String transactionType;
    private String requestedChannel;
    private Integer amount;
    private String reference;
    private String description;
    private String password;

    public Integer getIdMogawers() {
        return idMogawers;
    }

    public void setIdMogawers(Integer idMogawers) {
        this.idMogawers = idMogawers;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getRequestedChannel() {
        return requestedChannel;
    }

    public void setRequestedChannel(String requestedChannel) {
        this.requestedChannel = requestedChannel;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
