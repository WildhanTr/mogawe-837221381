
package com.mogawe.mosurvei.model.bean;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Item implements Serializable {

    @SerializedName("id_projects_displays_facts_products")
    @Expose
    private String idProjectsDisplaysFactsProducts;
    @SerializedName("id_projects_displays_facts")
    @Expose
    private String idProjectsDisplaysFacts;
    @SerializedName("id_item")
    @Expose
    private String idItem;
    @SerializedName("item_name")
    @Expose
    private String itemName;
    private String idFact;

    public String getIdProjectsDisplaysFactsProducts() {
        return idProjectsDisplaysFactsProducts;
    }

    public void setIdProjectsDisplaysFactsProducts(String idProjectsDisplaysFactsProducts) {
        this.idProjectsDisplaysFactsProducts = idProjectsDisplaysFactsProducts;
    }

    public String getIdProjectsDisplaysFacts() {
        return idProjectsDisplaysFacts;
    }

    public void setIdProjectsDisplaysFacts(String idProjectsDisplaysFacts) {
        this.idProjectsDisplaysFacts = idProjectsDisplaysFacts;
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getIdFact() {
        return idFact;
    }

    public void setIdFact(String idFact) {
        this.idFact = idFact;
    }
}
