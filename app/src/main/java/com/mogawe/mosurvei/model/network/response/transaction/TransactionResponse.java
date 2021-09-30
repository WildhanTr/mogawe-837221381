package com.mogawe.mosurvei.model.network.response.transaction;

import com.mogawe.mosurvei.model.db.entity.PendingPayment;
import com.mogawe.mosurvei.model.network.response.GenericResp;
import com.mogawe.mosurvei.model.network.response.UserResponse;

import java.util.List;

public class TransactionResponse extends GenericResp {

    private List<PendingPayment> pendingPayments;
    private RequestKey requestKey;

    public enum RequestKey {
        TRANSACTION_SUCCESS,
        CASH_OUT_REQUEST_SUCCESS,
        INSUFFICIENT_BALANCE,
        VERIFICATION_FAILED,
        TRANSACTION_FAILED,
        GET_PENDING_PAYMENT,
        FAILED
    }

    public List<PendingPayment> getPendingPayments() {
        return pendingPayments;
    }

    public void setPendingPayments(List<PendingPayment> pendingPayments) {
        this.pendingPayments = pendingPayments;
    }

    public RequestKey getRequestKey() {
        return requestKey;
    }

    public void setRequestKey(RequestKey requestKey) {
        this.requestKey = requestKey;
    }
}
