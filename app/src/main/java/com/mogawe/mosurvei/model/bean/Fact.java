package com.mogawe.mosurvei.model.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * Created by mobiletech on 5/14/17.
 */

public class Fact implements Serializable {

    @SerializedName("id_projects_displays_facts")
    @Expose
    private String idProjectsDisplaysFacts;
    @SerializedName("id_projects_displays")
    @Expose
    private String idProjectsDisplays;
    @SerializedName("id_project")
    @Expose
    private String idProject;
    @SerializedName("fact_name")
    @Expose
    private String factName;
    @SerializedName("action_rules")
    @Expose
    private String actionRules;
    @SerializedName("id_fact_parameter")
    @Expose
    private String idFactParameter;
    @SerializedName("id_fact")
    @Expose
    private String idFact;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("active")
    @Expose
    private String active;
    @SerializedName("id_fact_type")
    @Expose
    private String idFactType;
    @SerializedName("id_fact_group")
    @Expose
    private String idFactGroup;
    @SerializedName("fact_type")
    @Expose
    private String factType;
    @SerializedName("fact_group")
    @Expose
    private String factGroup;
    @SerializedName("min_value")
    @Expose
    private String minValue = "";
    @SerializedName("max_value")
    @Expose
    private String maxValue = "";
    @SerializedName("id_store_group")
    @Expose
    private String idStoreGroup;
    @SerializedName("id_store")
    @Expose
    private String idStore;
    @SerializedName("id_display")
    @Expose
    private String idDisplay;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("sequence")
    @Expose
    private String sequence;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("item_desc")
    @Expose
    private String itemDesc;
    @SerializedName("id_item")
    @Expose
    private String idItem = "";
    @SerializedName("selection_validate")
    @Expose
    private String selectionValidate = "";
    @SerializedName("item_name")
    @Expose
    private String itemName;
    @SerializedName("allow_no_response")
    @Expose
    private String allowNoResponse;
    @SerializedName("step_value")
    @Expose
    private String stepValue = "";
    @SerializedName("items")
    @Expose
    private List<Fact> items = null;
    @SerializedName("jml_item")
    @Expose
    private String jmlitem = "";
    @SerializedName("is_visible")
    @Expose
    private Boolean is_visible;
    @SerializedName("id_projects_displays_facts_products")
    @Expose
    private String idProjectsDisplaysFactsProducts;
    private String input = "";
    private String comment = "";
    private List<Fact> questions;
    private List<File> files;
    private String story;
    private String factHint = "";
    private Boolean isMandatory = true;
    private Boolean isDisabled = false;
    private List<FactPoll> pollings;


    public Fact() {
    }

    public Fact(String factName, String factHint, String value, String idFactType, String story) {
        this.factName = factName;
        this.value = value;
        this.idFactType = idFactType;
        this.story = story;
        this.factHint = factHint;
    }

    public Fact(String factName, String factHint, String value, String idFactType, String story, String Input) {
        this.factName = factName;
        this.value = value;
        this.idFactType = idFactType;
        this.story = story;
        this.factHint = factHint;
        this.input = input;
    }

    public String getIdFact() {
        return idFact;
    }

    public void setIdFact(String idFact) {
        this.idFact = idFact;
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

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getIdFactType() {
        return idFactType;
    }

    public void setIdFactType(String idFactType) {
        this.idFactType = idFactType;
    }

    public String getIdFactGroup() {
        return idFactGroup;
    }

    public void setIdFactGroup(String idFactGroup) {
        this.idFactGroup = idFactGroup;
    }

    public String getFactType() {
        return factType;
    }

    public void setFactType(String factType) {
        this.factType = factType;
    }

    public String getFactGroup() {
        return factGroup;
    }

    public void setFactGroup(String factGroup) {
        this.factGroup = factGroup;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getIdStore() {
        return idStore;
    }

    public void setIdStore(String idStore) {
        this.idStore = idStore;
    }

    public String getIdStoreGroup() {
        return idStoreGroup;
    }

    public void setIdStoreGroup(String idStoreGroup) {
        this.idStoreGroup = idStoreGroup;
    }

    public String getIdDisplay() {
        return idDisplay;
    }

    public void setIdDisplay(String idDisplay) {
        this.idDisplay = idDisplay;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public List<Fact> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Fact> questions) {
        this.questions = questions;
    }

    public String getSelectionValidate() {
        return selectionValidate;
    }

    public void setSelectionValidate(String selectionValidate) {
        this.selectionValidate = selectionValidate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public String getIdProjectsDisplaysFacts() {
        return idProjectsDisplaysFacts;
    }

    public void setIdProjectsDisplaysFacts(String idProjectsDisplaysFacts) {
        this.idProjectsDisplaysFacts = idProjectsDisplaysFacts;
    }

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

    public String getFactName() {
        return factName;
    }

    public void setFactName(String factName) {
        this.factName = factName;
    }

    public String getActionRules() {
        return actionRules;
    }

    public void setActionRules(String actionRules) {
        this.actionRules = actionRules;
    }

    public String getIdFactParameter() {
        return idFactParameter;
    }

    public void setIdFactParameter(String idFactParameter) {
        this.idFactParameter = idFactParameter;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getAllowNoResponse() {
        return allowNoResponse;
    }

    public void setAllowNoResponse(String allowNoResponse) {
        this.allowNoResponse = allowNoResponse;
    }

    public List<Fact> getItems() {
        return items;
    }

    public void setItems(List<Fact> items) {
        this.items = items;
    }

    public String getIdProjectsDisplaysFactsProducts() {
        return idProjectsDisplaysFactsProducts;
    }

    public void setIdProjectsDisplaysFactsProducts(String idProjectsDisplaysFactsProducts) {
        this.idProjectsDisplaysFactsProducts = idProjectsDisplaysFactsProducts;
    }

    public String getStepValue() {
        return stepValue;
    }

    public void setStepValue(String stepValue) {
        this.stepValue = stepValue;
    }

    public String getJmlitem() {
        return jmlitem;
    }

    public void setJmlitem(String jmlitem) {
        this.jmlitem = jmlitem;
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

    public Boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(Boolean mandatory) {
        isMandatory = mandatory;
    }

    public Boolean getDisabled() {
        return isDisabled;
    }

    public void setDisabled(Boolean disabled) {
        isDisabled = disabled;
    }

    public List<FactPoll> getPollings() {
        return pollings;
    }

    public void setPollings(List<FactPoll> pollings) {
        this.pollings = pollings;
    }

    public Boolean getIs_visible() {
        return is_visible;
    }

    public void setIs_visible(Boolean is_visible) {
        this.is_visible = is_visible;
    }
}
