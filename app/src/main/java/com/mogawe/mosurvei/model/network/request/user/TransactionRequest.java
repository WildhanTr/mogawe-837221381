package com.mogawe.mosurvei.model.network.request.user;

public class TransactionRequest {
    private Integer id_mogawers;
    private String transaction_type;
    private Integer amount;
    private String reference;
    private String description;
    private String password;

    public Integer getId_mogawers() {
        return id_mogawers;
    }

    public void setId_mogawers(Integer id_mogawers) {
        this.id_mogawers = id_mogawers;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
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
