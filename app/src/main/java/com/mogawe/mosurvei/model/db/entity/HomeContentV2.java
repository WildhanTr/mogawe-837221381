package com.mogawe.mosurvei.model.db.entity;

import android.view.View;

import androidx.room.Ignore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.Task.ToDoList;

public class HomeContentV2 implements Serializable {

    @SerializedName("postTimelines")
    private List<TimelineActivityEntity> postTimelines = new ArrayList<>();

    @SerializedName("code")
    private String code;

    @SerializedName("actionValue")
    private String actionValue;

    @SerializedName("jobs")
    private List<Task> tasks = new ArrayList<>();

    @SerializedName("taskToDos")
    private List<ToDoList> toDoLists = new ArrayList<>();

    @SerializedName("description")
    private String description;

    @SerializedName("type")
    private String type;

    @SerializedName("widgets")
    private List<WidgetEntity> widgets;

    @SerializedName("uuid")
    private String uuid;

    @SerializedName("sequence")
    private int sequence;

    @SerializedName("name")
    private String name;

    @SerializedName("action")
    private String action;

    @SerializedName("tag")
    private String tag;

    @SerializedName("actionName")
    private String actionName;

    @SerializedName("jobCategories")
    private List<HomeJobCategories> jobCategories;

//	private List<View> widgetViews;

    public List<TimelineActivityEntity> getPostTimelines() {
        return postTimelines;
    }

    public void setPostTimelines(List<TimelineActivityEntity> postTimelines) {
        this.postTimelines = postTimelines;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getActionValue() {
        return actionValue;
    }

    public void setActionValue(String actionValue) {
        this.actionValue = actionValue;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }


    public List<ToDoList> getToDoLists() {
        return toDoLists;
    }

    public void setToDoLists(List<ToDoList> toDoLists) {
        this.toDoLists = toDoLists;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<WidgetEntity> getWidgets() {
        return widgets;
    }

    public void setWidgets(List<WidgetEntity> widgets) {
        this.widgets = widgets;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public List<HomeJobCategories> getJobCategories() {
        return jobCategories;
    }

    public void setJobCategories(List<HomeJobCategories> jobCategories) {
        this.jobCategories = jobCategories;
    }

    //	public List<View> getWidgetViews() {
//		return widgetViews;
//	}
//
//	public void setWidgetViews(List<View> widgetViews) {
//		this.widgetViews = widgetViews;
//	}
}