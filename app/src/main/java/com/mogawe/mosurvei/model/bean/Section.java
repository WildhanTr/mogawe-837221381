package com.mogawe.mosurvei.model.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Glen Rynaldy on 02/03/2018.
 */

public class Section implements Serializable {

    @SerializedName("id_projects_displays")
    @Expose
    private String idProjectsDisplays;
    @SerializedName("id_project")
    @Expose
    private String idProject;
    @SerializedName("id_display")
    @Expose
    private String idDisplay;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("action_rules")
    @Expose
    private String actionRules;
    @SerializedName("sort")
    @Expose
    private String sort;
    @SerializedName("facts")
    @Expose
    private List<Fact> facts = null;
    private String reffId = "0";

    public String getIdProjectsDisplays() {
        return idProjectsDisplays;
    }

    public void setIdProjectsDisplays(String idProjectsDisplays) {
        this.idProjectsDisplays = idProjectsDisplays;
    }

    public String getIdProject() {
        return idProject;
    }

    public void setIdProject(String idProject) {
        this.idProject = idProject;
    }

    public String getIdDisplay() {
        return idDisplay;
    }

    public void setIdDisplay(String idDisplay) {
        this.idDisplay = idDisplay;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getActionRules() {
        return actionRules;
    }

    public void setActionRules(String actionRules) {
        this.actionRules = actionRules;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public List<Fact> getFacts() {
        return facts;
    }

    public void setFacts(List<Fact> facts) {
        this.facts = facts;
    }

    public String getReffId() {
        return reffId;
    }

    public void setReffId(String reffId) {
        this.reffId = reffId;
    }
}
