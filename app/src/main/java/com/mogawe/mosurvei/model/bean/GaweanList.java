package com.mogawe.mosurvei.model.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.job.Job;

import java.io.Serializable;
import java.util.List;

public class GaweanList implements Serializable {

    @SerializedName("job_apply")
    @Expose
    private List<Job> jobApplyList = null;
    @SerializedName("job_assign")
    @Expose
    private List<Job> jobAssignList = null;

    public List<Job> getJobApplyList() {
        return jobApplyList;
    }

    public void setJobApplyList(List<Job> jobApplyList) {
        this.jobApplyList = jobApplyList;
    }

    public List<Job> getJobAssignList() {
        return jobAssignList;
    }

    public void setJobAssignList(List<Job> jobAssignList) {
        this.jobAssignList = jobAssignList;
    }
}
