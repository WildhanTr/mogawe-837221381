package com.mogawe.mosurvei.view.shared.gawean;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;

import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.model.bean.gawean.GaweanListResponse;
import com.mogawe.mosurvei.model.bean.gawean.GaweanListResponseItem;
import com.mogawe.mosurvei.model.db.entity.Section;
import com.mogawe.mosurvei.playground.PaketGaweanAdapter;
import com.mogawe.mosurvei.util.ExceptionHandler;
import com.mogawe.mosurvei.view.BaseActivity;

import java.util.ArrayList;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DetailPaketGaweanActivity extends BaseActivity {


    private static final String TAG = "QRScannerActivity";

    public static void startActivity(Activity sourceActivity, int reqCode) {
        sourceActivity.startActivityForResult(new Intent(sourceActivity, DetailPaketGaweanActivity.class), reqCode);
    }

    public static void startActivity(Activity sourceActivity, int reqCode, Section section) {
        sourceActivity.startActivityForResult(new Intent(sourceActivity, DetailPaketGaweanActivity.class).putExtra(SECTION, section), reqCode);
    }

    @Override
    protected int layout() {
        return R.layout.a_gawean_detail_paketan;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));



    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
