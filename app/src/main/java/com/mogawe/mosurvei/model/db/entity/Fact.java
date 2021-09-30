package com.mogawe.mosurvei.model.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.converter.FileListConverter;
import com.mogawe.mosurvei.model.db.converter.ItemListConverter;

import java.io.File;
import java.io.Serializable;
import java.util.List;

@Entity
public class Fact implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int idPK;
    @SerializedName("idFactDisplay")
    @Expose
    private String id_fact_display;
    @SerializedName("idFactRef")
    @Expose
    private String id_fact_ref;
    @SerializedName("uuid")
    @Expose
    private String id_fact;
    @SerializedName("uuidFactType")
    @Expose
    private String id_fact_type;
    @SerializedName("actionRules")
    @Expose
    private String action_rules;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("sequence")
    @Expose
    private Integer sequence;
    @SerializedName("factName")
    @Expose
    private String fact_name;
    @SerializedName("hintName")
    @Expose
    private String hint_name;
    @SerializedName("allowNoResponse")
    @Expose
    private String allow_no_response;
    @SerializedName("minValue")
    @Expose
    private Integer min_value;
    @SerializedName("maxValue")
    @Expose
    private Integer max_value;
    @SerializedName("minDate")
    @Expose
    private Long min_date;
    @SerializedName("maxDate")
    @Expose
    private Long max_date;
    @SerializedName("selectionValidate")
    @Expose
    private String selection_validate;
    @SerializedName("stepValue")
    @Expose
    private String step_size = "1";
    @SerializedName("idItemDisplay")
    @Expose
    private String id_item_display;
    @SerializedName("uuidItem")
    @Expose
    private String id_item;
    @SerializedName("itemName")
    @Expose
    private String item_name;


    @SerializedName("idMetaDisplay")
    @Expose
    private String idMetaDisplay;
    @SerializedName("uuidMeta")
    @Expose
    private String uuidMeta;
    @SerializedName("metaName")
    @Expose
    private String metaName;

    @SerializedName("isVisible")
    @Expose
    private Boolean is_visible;
    @SerializedName("isMandatory")
    @Expose
    private Boolean is_mandatory;
    @SerializedName("items")
    @Expose
    private List<Item> items;
    @SerializedName("batchMetas")
    @Expose
    private List<FactMeta> factMetas;
    @SerializedName("pictureUrl")
    @Expose
    private String pictureUrl;
    @SerializedName("itemPlanograms")
    @Expose
    private List<List<Item>> itemPlanograms;
    @SerializedName("valueScore")
    @Expose
    private String score;
    @SerializedName("isCommaAllowed")
    @Expose
    private Boolean is_comma_allowed;
    @SerializedName("videoUrl")
    @Expose
    private String videoUrl;
    @SerializedName("timestampTaken")
    @Expose
    private String timestampTaken;

    private String input = "";
    private String comment = "";
    //    private List<com.mogawe.mosurvei.model.bean.Fact> questions;
    private List<File> files;
    private String story;
    private String factHint = "";
    private Boolean isDisabled = false;
    private String idSection;
    private Boolean isHide = false;
    private Boolean is_mandatory_temporary = false;

    public int getIdPK() {
        return idPK;
    }

    public void setIdPK(int idPK) {
        this.idPK = idPK;
    }

    public String getId_fact_display() {
        return id_fact_display;
    }

    public void setId_fact_display(String id_fact_display) {
        this.id_fact_display = id_fact_display;
    }

    public String getId_fact_ref() {
        return id_fact_ref;
    }

    public void setId_fact_ref(String id_fact_ref) {
        this.id_fact_ref = id_fact_ref;
    }

    @TypeConverters(ItemListConverter.class)
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getId_fact() {
        return id_fact;
    }

    public void setId_fact(String id_fact) {
        this.id_fact = id_fact;
    }

    public String getId_fact_type() {
        return id_fact_type;
    }

    public void setId_fact_type(String id_fact_type) {
        this.id_fact_type = id_fact_type;
    }

    public String getAction_rules() {
        return action_rules;
    }

    public void setAction_rules(String action_rules) {
        this.action_rules = action_rules;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getFact_name() {
        return fact_name;
    }

    public void setFact_name(String fact_name) {
        this.fact_name = fact_name;
    }

    public String getHint_name() {
        return hint_name;
    }

    public void setHint_name(String hint_name) {
        this.hint_name = hint_name;
    }

    public String getAllow_no_response() {
        return allow_no_response;
    }

    public void setAllow_no_response(String allow_no_response) {
        this.allow_no_response = allow_no_response;
    }

    public Integer getMin_value() {
        return min_value;
    }

    public void setMin_value(Integer min_value) {
        this.min_value = min_value;
    }

    public Integer getMax_value() {
        return max_value;
    }

    public void setMax_value(Integer max_value) {
        this.max_value = max_value;
    }

    public String getSelection_validate() {
        return selection_validate;
    }

    public void setSelection_validate(String selection_validate) {
        this.selection_validate = selection_validate;
    }

    public String getStep_size() {
        return step_size;
    }

    public void setStep_size(String step_size) {
        this.step_size = step_size;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    //    public List<com.mogawe.mosurvei.model.bean.Fact> getQuestions() {
//        return questions;
//    }
//
//    public void setQuestions(List<com.mogawe.mosurvei.model.bean.Fact> questions) {
//        this.questions = questions;
//    }
    @TypeConverters(FileListConverter.class)
    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getFactHint() {
        return factHint;
    }

    public void setFactHint(String factHint) {
        this.factHint = factHint;
    }

    public Boolean getDisabled() {
        return isDisabled;
    }

    public void setDisabled(Boolean disabled) {
        isDisabled = disabled;
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

    public String getIdSection() {
        return idSection;
    }

    public void setIdSection(String idSection) {
        this.idSection = idSection;
    }

    public Boolean getIs_visible() {
        return is_visible;
    }

    public void setIs_visible(Boolean is_visible) {
        this.is_visible = is_visible;
    }

    public Boolean getIs_mandatory() {
        return is_mandatory;
    }

    public void setIs_mandatory(Boolean is_mandatory) {
        this.is_mandatory = is_mandatory;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Long getMin_date() {
        return min_date;
    }

    public void setMin_date(Long min_date) {
        this.min_date = min_date;
    }

    public Long getMax_date() {
        return max_date;
    }

    public void setMax_date(Long max_date) {
        this.max_date = max_date;
    }

    public List<List<Item>> getItemPlanograms() {
        return itemPlanograms;
    }

    public void setItemPlanograms(List<List<Item>> itemPlanograms) {
        this.itemPlanograms = itemPlanograms;
    }

    public Boolean getHide() {
        return isHide;
    }

    public void setHide(Boolean hide) {
        isHide = hide;
    }

    public Boolean getIs_mandatory_temporary() {
        return is_mandatory_temporary;
    }

    public void setIs_mandatory_temporary(Boolean is_mandatory_temporary) {
        this.is_mandatory_temporary = is_mandatory_temporary;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Boolean getIs_comma_allowed() {
        return is_comma_allowed;
    }

    public void setIs_comma_allowed(Boolean is_comma_allowed) {
        this.is_comma_allowed = is_comma_allowed;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @TypeConverters(ItemListConverter.class)
    public List<FactMeta> getFactMetas() {
        return factMetas;
    }

    public void setFactMetas(List<FactMeta> factMetas) {
        this.factMetas = factMetas;
    }

    public String getIdMetaDisplay() {
        return idMetaDisplay;
    }

    public void setIdMetaDisplay(String idMetaDisplay) {
        this.idMetaDisplay = idMetaDisplay;
    }

    public String getUuidMeta() {
        return uuidMeta;
    }

    public void setUuidMeta(String uuidMeta) {
        this.uuidMeta = uuidMeta;
    }

    public String getMetaName() {
        return metaName;
    }

    public void setMetaName(String metaName) {
        this.metaName = metaName;
    }

    public String getTimestampTaken() {
        return timestampTaken;
    }

    public void setTimestampTaken(String timestampTaken) {
        this.timestampTaken = timestampTaken;
    }
}
