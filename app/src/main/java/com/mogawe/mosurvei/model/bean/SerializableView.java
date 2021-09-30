package com.mogawe.mosurvei.model.bean;

import android.view.View;

import java.io.Serializable;

public class SerializableView implements Serializable {

    private View view;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
