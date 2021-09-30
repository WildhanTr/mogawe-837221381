package com.mogawe.mosurvei.model.bean;

import java.io.File;
import java.io.Serializable;

public class VideoGroup implements Serializable {

    private String factId;
    private File file;
    private Integer index;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getFactId() {
        return factId;
    }

    public void setFactId(String factId) {
        this.factId = factId;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
