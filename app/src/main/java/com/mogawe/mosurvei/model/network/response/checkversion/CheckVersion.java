
package com.mogawe.mosurvei.model.network.response.checkversion;

import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.network.response.GenericResp;

public class CheckVersion extends GenericResp{

    @SerializedName("data")
    private Data mData;

    public Data getData() {
        return mData;
    }

    public void setData(Data data) {
        mData = data;
    }

}
