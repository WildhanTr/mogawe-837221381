package com.mogawe.mosurvei.model.db.entity;

import java.io.Serializable;

public class CashOutItem implements Serializable {
    private String itemName;
    private String itemRek;
    private String itemPersonName;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemRek() {
        return itemRek;
    }

    public void setItemRek(String itemRek) {
        this.itemRek = itemRek;
    }

    public String getItemPersonName() {
        return itemPersonName;
    }

    public void setItemPersonName(String itemPersonName) {
        this.itemPersonName = itemPersonName;
    }
}
