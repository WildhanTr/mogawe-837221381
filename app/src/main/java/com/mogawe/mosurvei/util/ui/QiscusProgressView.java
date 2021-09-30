package com.mogawe.mosurvei.util.ui;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public interface QiscusProgressView {
    int getProgress();

    void setProgress(int progress);

    int getFinishedColor();

    void setFinishedColor(int finishedColor);

    int getUnfinishedColor();

    void setUnfinishedColor(int unfinishedColor);

    void setVisibility(@Visibility int visibility);

    @IntDef({VISIBLE, INVISIBLE, GONE})
    @Retention(RetentionPolicy.SOURCE)
    @interface Visibility {
    }
}
