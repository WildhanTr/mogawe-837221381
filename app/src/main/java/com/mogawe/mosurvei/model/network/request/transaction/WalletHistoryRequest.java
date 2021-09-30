package com.mogawe.mosurvei.model.network.request.transaction;

import com.mogawe.mosurvei.model.network.request.GenericReq;

import java.io.Serializable;

public class WalletHistoryRequest extends GenericReq implements Serializable {
    public String dateFrom;
    public String dateTo;

    public WalletHistoryRequest(String dateFrom, String dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}
