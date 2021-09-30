package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.Catalog;

import java.io.Serializable;

public class CatalogObjResponse extends GenericResp implements Serializable {

    @SerializedName("object")
    @Expose
    private Catalog object;

    private RequestKey requestKey;

    public Catalog getObject() {
        return object;
    }

    public void setObject(Catalog object) {
        this.object = object;
    }

    public RequestKey getRequestKey() {
        return requestKey;
    }

    public void setRequestKey(RequestKey requestKey) {
        this.requestKey = requestKey;
    }

    public enum RequestKey {
        EDIT,
        DETAIL,
        CREATE,
        PUBLISH,
        UNPUBLISH,
        DELETE,
        CHECKOUT
    }
}
