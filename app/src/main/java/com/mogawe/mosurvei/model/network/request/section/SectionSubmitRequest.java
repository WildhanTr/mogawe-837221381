package com.mogawe.mosurvei.model.network.request.section;

import com.mogawe.mosurvei.model.db.entity.ResultFact;
import com.mogawe.mosurvei.model.network.request.GenericReq;

import java.util.List;

public class SectionSubmitRequest extends GenericReq {
    private String idTask;
    private String idResult;
    private String uuidSession;
    private Double latitude;
    private Double longitude;

    private List<ResultFact> results;

    public String getIdTask() {
        return idTask;
    }

    public void setIdTask(String idTask) {
        this.idTask = idTask;
    }

    public List<ResultFact> getResults() {
        return results;
    }

    public void setResults(List<ResultFact> results) {
        this.results = results;
    }

    public String getUuidSession() {
        return uuidSession;
    }

    public void setUuidSession(String uuidSession) {
        this.uuidSession = uuidSession;
    }

    public String getIdResult() {
        return idResult;
    }

    public void setIdResult(String idResult) {
        this.idResult = idResult;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
