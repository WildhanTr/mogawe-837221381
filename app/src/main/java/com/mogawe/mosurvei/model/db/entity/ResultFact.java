package com.mogawe.mosurvei.model.db.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@Entity(foreignKeys = @ForeignKey(entity = Result.class, parentColumns = "id", childColumns = "result_id", onDelete = CASCADE))
@Entity
public class ResultFact implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int result_id;

    @SerializedName(value="uuid", alternate = {"id_result_fact"})
    @Expose
    private String id_result_fact;
    @SerializedName(value="uuidResult", alternate = {"id_result"})
    @Expose
    private String id_result;
    @SerializedName(value="idFactDisplay", alternate = {"id_fact"})
    @Expose
    private String id_fact;
    @SerializedName(value="idItemDisplay", alternate = {"id_item"})
    @Expose
    private String id_item;
    @SerializedName(value="idMetaDisplay", alternate = {"id_meta"})
    @Expose
    private String id_meta;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("uuidFactType")
    @Expose
    private String uuidFactType;

    @SerializedName("sectionName")
    @Expose
    private String sectionName;
    @SerializedName("sectionSequence")
    @Expose
    private Integer sectionSequence;
    @SerializedName("sectionIsEdited")
    @Expose
    private Boolean sectionIsEdited;
    @SerializedName("sectionNotes")
    @Expose
    private String sectionNotes;
    @SerializedName("sectionStartTimestamp")
    @Expose
    private String sectionStartTimestamp;
    @SerializedName("sectionEndTimestamp")
    @Expose
    private String sectionEndTimestamp;
    @SerializedName("factName")
    @Expose
    private String factName;
    @SerializedName("factSequence")
    @Expose
    private Integer factSequence;
    @SerializedName("factTimestamp")
    @Expose
    private String factTimestamp;

    @Ignore
    private List<File> files = new ArrayList<>();
    @Ignore
    private File file;
    @Ignore
    private String timestampTaken;

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Integer getSectionSequence() {
        return sectionSequence;
    }

    public void setSectionSequence(Integer sectionSequence) {
        this.sectionSequence = sectionSequence;
    }

    public Boolean getSectionIsEdited() {
        return sectionIsEdited;
    }

    public void setSectionIsEdited(Boolean sectionIsEdited) {
        this.sectionIsEdited = sectionIsEdited;
    }

    public String getSectionNotes() {
        return sectionNotes;
    }

    public void setSectionNotes(String sectionNotes) {
        this.sectionNotes = sectionNotes;
    }

    public String getSectionStartTimestamp() {
        return sectionStartTimestamp;
    }

    public void setSectionStartTimestamp(String sectionStartTimestamp) {
        this.sectionStartTimestamp = sectionStartTimestamp;
    }

    public String getSectionEndTimestamp() {
        return sectionEndTimestamp;
    }

    public void setSectionEndTimestamp(String sectionEndTimestamp) {
        this.sectionEndTimestamp = sectionEndTimestamp;
    }

    public String getFactName() {
        return factName;
    }

    public void setFactName(String factName) {
        this.factName = factName;
    }

    public Integer getFactSequence() {
        return factSequence;
    }

    public void setFactSequence(Integer factSequence) {
        this.factSequence = factSequence;
    }

    public String getFactTimestamp() {
        return factTimestamp;
    }

    public void setFactTimestamp(String factTimestamp) {
        this.factTimestamp = factTimestamp;
    }

    public String getId_meta() {
        return id_meta;
    }

    public void setId_meta(String id_meta) {
        this.id_meta = id_meta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResult_id() {
        return result_id;
    }

    public void setResult_id(int result_id) {
        this.result_id = result_id;
    }

    public String getId_result() {
        return id_result;
    }

    public void setId_result(String id_result) {
        this.id_result = id_result;
    }

    public String getId_fact() {
        return id_fact;
    }

    public void setId_fact(String id_fact) {
        this.id_fact = id_fact;
    }

    public String getId_item() {
        return id_item;
    }

    public void setId_item(String id_item) {
        this.id_item = id_item;
    }

    public String getId_result_fact() {
        return id_result_fact;
    }

    public void setId_result_fact(String id_result_fact) {
        this.id_result_fact = id_result_fact;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public String getUuidFactType() {
        return uuidFactType;
    }

    public void setUuidFactType(String uuidFactType) {
        this.uuidFactType = uuidFactType;
    }

    public String getTimestampTaken() {
        return timestampTaken;
    }

    public void setTimestampTaken(String timestampTaken) {
        this.timestampTaken = timestampTaken;
    }
}