package com.mogawe.mosurvei.model.bean.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by glenrynaldi on 9/11/16.
 */

public class GeocodeResult implements Serializable {

    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<>();
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * @return The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * @param results The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
