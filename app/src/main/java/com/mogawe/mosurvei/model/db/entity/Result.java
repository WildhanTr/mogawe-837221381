package com.mogawe.mosurvei.model.db.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Result implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("idResult")
    @Expose
    @NonNull
    private String id_result;
    @SerializedName("idTask")
    @Expose
    private String id_task;


    @Ignore
    private List<ResultFact> result_facts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_result() {
        return id_result;
    }

    public void setId_result(String id_result) {
        this.id_result = id_result;
    }

    public String getId_task() {
        return id_task;
    }

    public void setId_task(String id_task) {
        this.id_task = id_task;
    }

    public List<ResultFact> getResult_facts() {
        return result_facts;
    }

    public void setResult_facts(List<ResultFact> result_facts) {
        this.result_facts = result_facts;
    }


}
