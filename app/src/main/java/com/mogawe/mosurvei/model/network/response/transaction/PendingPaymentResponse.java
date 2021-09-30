package com.mogawe.mosurvei.model.network.response.transaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.PendingPayment;
import com.mogawe.mosurvei.model.network.response.GenericResponse;

import java.util.List;

public class PendingPaymentResponse extends GenericResponse {
    @SerializedName("object")
    @Expose
    private List<PendingPayment> pendingPayments;

    public List<PendingPayment> getPendingPayments() {
        return pendingPayments;
    }

    public void setPendingPayments(List<PendingPayment> pendingPayments) {
        this.pendingPayments = pendingPayments;
    }
}
