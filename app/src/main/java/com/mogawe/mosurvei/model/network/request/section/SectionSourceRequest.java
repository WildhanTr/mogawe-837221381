package com.mogawe.mosurvei.model.network.request.section;

import com.mogawe.mosurvei.model.network.request.GenericReq;

public class SectionSourceRequest extends GenericReq {
    String idTask;

    public String getIdTask() {
        return idTask;
    }

    public void setIdTask(String idTask) {
        this.idTask = idTask;
    }
}
