package com.mogawe.mosurvei.model.network.request.point;

public class RedeemRequest {
    private String uuid;
    private Integer count;

    public RedeemRequest(String uuid, Integer count) {
        this.uuid = uuid;
        this.count = count;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
