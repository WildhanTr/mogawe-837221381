package com.mogawe.mosurvei.model.db.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.converter.FactListConverter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Section implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int idPK;
    @SerializedName(value="idSectionDisplay", alternate = {"id_section_display"})
    @Expose
    private String id_section_display;
    @SerializedName(value="uuid", alternate = {"id_section"})
    @Expose
    private String id_section;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName(value="actionRules", alternate = {"action_rules"})
    @Expose
    private String action_rules;
    @SerializedName("sort")
    @Expose
    private String sort;
    @SerializedName("facts")
    @Expose
    private List<Fact> facts;
    @SerializedName(value="idItemDisplay", alternate = {"id_item_display"})
    @Expose
    private String id_item_display;
    @SerializedName(value="uuidItem", alternate = {"id_item"})
    @Expose
    private String id_item;
    @SerializedName(value="itemName", alternate = {"item_name"})
    @Expose
    private String item_name;

    @SerializedName(value="idMetaDisplay", alternate = {"id_meta_display"})
    @Expose
    private String id_meta_display;
    @SerializedName(value="uuidMeta", alternate = {"id_meta"})
    @Expose
    private String id_meta;
    @SerializedName(value="metaName", alternate = {"meta_name"})
    @Expose
    private String meta_name;

    @SerializedName("uuidQuestionnaireTemplate")
    @Expose
    private String uuidQuestionnaireTemplate;
    @SerializedName("questionnaireTemplateName")
    @Expose
    private String questionnaireTemplateName;
    @SerializedName("versionNumber")
    @Expose
    private int versionNumber;
    @SerializedName("isEdited")
    @Expose
    private Boolean isEdited = false;
    @SerializedName("notes")
    @Expose
    private String notes;

    @Ignore
    private Section parentSection;

    @Ignore
    private String uuidAdapterFacing;

    @Ignore
    private Integer idFacingItem;

    @Ignore
    private Date startTimestamp;

    @Ignore
    private Date endTimestamp;

    @Ignore
    private Boolean isSaved = false;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getEdited() {
        return isEdited;
    }

    public void setEdited(Boolean edited) {
        isEdited = edited;
    }

    public Date getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Date startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Date getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Date endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public String getId_meta_display() {
        return id_meta_display;
    }

    public void setId_meta_display(String id_meta_display) {
        this.id_meta_display = id_meta_display;
    }

    public String getId_meta() {
        return id_meta;
    }

    public void setId_meta(String id_meta) {
        this.id_meta = id_meta;
    }

    public String getMeta_name() {
        return meta_name;
    }

    public void setMeta_name(String meta_name) {
        this.meta_name = meta_name;
    }

    public Section getParentSection() {
        return parentSection;
    }

    public void setParentSection(Section parentSection) {
        this.parentSection = parentSection;
    }

    public int getIdPK() {
        return idPK;
    }

    public void setIdPK(int id) {
        this.idPK = id;
    }

    public String getId_section_display() {
        return id_section_display;
    }

    public void setId_section_display(String id_section_display) {
        this.id_section_display = id_section_display;
    }

    public String getId_section() {
        return id_section;
    }

    public void setId_section(String id_section) {
        this.id_section = id_section;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction_rules() {
        return action_rules;
    }

    public void setAction_rules(String action_rules) {
        this.action_rules = action_rules;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @TypeConverters(FactListConverter.class)
    public List<Fact> getFacts() {
        return facts;
    }

    public void setFacts(List<Fact> facts) {
        this.facts = facts;
    }

    public String getId_item() {
        return id_item;
    }

    public void setId_item(String id_item) {
        this.id_item = id_item;
    }

    public String getId_item_display() {
        return id_item_display;
    }

    public void setId_item_display(String id_item_display) {
        this.id_item_display = id_item_display;
    }
    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getUuidAdapterFacing() {
        return uuidAdapterFacing;
    }

    public void setUuidAdapterFacing(String uuidAdapterFacing) {
        this.uuidAdapterFacing = uuidAdapterFacing;
    }

    public Integer getIdFacingItem() {
        return idFacingItem;
    }

    public void setIdFacingItem(Integer idFacingItem) {
        this.idFacingItem = idFacingItem;
    }

    public Boolean getSaved() {
        return isSaved;
    }

    public void setSaved(Boolean saved) {
        isSaved = saved;
    }

    public String getUuidQuestionnaireTemplate() {
        return uuidQuestionnaireTemplate;
    }

    public void setUuidQuestionnaireTemplate(String uuidQuestionnaireTemplate) {
        this.uuidQuestionnaireTemplate = uuidQuestionnaireTemplate;
    }

    public String getQuestionnaireTemplateName() {
        return questionnaireTemplateName;
    }

    public void setQuestionnaireTemplateName(String questionnaireTemplateName) {
        this.questionnaireTemplateName = questionnaireTemplateName;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }

}
