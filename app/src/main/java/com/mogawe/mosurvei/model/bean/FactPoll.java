package com.mogawe.mosurvei.model.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * Created by mobiletech on 5/14/17.
 */

public class FactPoll implements Serializable {


    private String val;
    private Integer counter;
    private Integer total;

    public FactPoll() {
    }

    public FactPoll(String val, Integer counter, Integer total) {
        this.val = val;
        this.counter = counter;
        this.total = total;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
