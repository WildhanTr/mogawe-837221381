package com.mogawe.mosurvei.model.bean.history;

import java.io.Serializable;

public class WalletHistory implements Serializable {
    private String id_history;
    private String dateday;
    private String description;
    private String cashChange;
    private String castType;

    public String getId_history() {
        return id_history;
    }

    public void setId_history(String id_history) {
        this.id_history = id_history;
    }

    public String getDateday() {
        return dateday;
    }

    public void setDateday(String dateday) {
        this.dateday = dateday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCashChange() {
        return cashChange;
    }

    public void setCashChange(String cashChange) {
        this.cashChange = cashChange;
    }

    public String getCastType() {
        return castType;
    }

    public void setCastType(String castType) {
        this.castType = castType;
    }
}
