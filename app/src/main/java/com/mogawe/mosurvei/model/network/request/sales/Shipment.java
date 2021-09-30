package com.mogawe.mosurvei.model.network.request.sales;

import java.io.Serializable;

public class Shipment implements Serializable {

    private String uuid;
    private String shipmentResi;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getShipmentResi() {
        return shipmentResi;
    }

    public void setShipmentResi(String shipmentResi) {
        this.shipmentResi = shipmentResi;
    }
}
