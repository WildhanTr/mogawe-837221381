
package com.mogawe.mosurvei.model.network.response.checkversion;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Data implements Serializable{

    @SerializedName("force_download")
    private String mForceDownload;
    @SerializedName("id_version")
    private String mIdVersion;
    @SerializedName("version_name")
    private String mVersionName;

    public String getForceDownload() {
        return mForceDownload;
    }

    public void setForceDownload(String forceDownload) {
        mForceDownload = forceDownload;
    }

    public String getIdVersion() {
        return mIdVersion;
    }

    public void setIdVersion(String idVersion) {
        mIdVersion = idVersion;
    }

    public String getVersionName() {
        return mVersionName;
    }

    public void setVersionName(String versionName) {
        mVersionName = versionName;
    }

}
