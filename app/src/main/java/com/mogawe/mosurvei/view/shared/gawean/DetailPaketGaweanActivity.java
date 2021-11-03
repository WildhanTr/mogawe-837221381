package com.mogawe.mosurvei.view.shared.gawean;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.model.bean.MapPlace;
import com.mogawe.mosurvei.model.bean.gawean.GaweanListResponse;
import com.mogawe.mosurvei.model.bean.gawean.GaweanListResponseItem;
import com.mogawe.mosurvei.model.db.entity.Section;
import com.mogawe.mosurvei.playground.PaketGaweanAdapter;
import com.mogawe.mosurvei.util.ExceptionHandler;
import com.mogawe.mosurvei.view.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DetailPaketGaweanActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imgGawean) ImageView imgGawean;
    @BindView(R.id.txtTittleGawean) TextView txtTitleGawean;
    @BindView(R.id.txtDescGawean) TextView txtDescGawean;
    @BindView(R.id.txtFeeGawean) TextView txtFeeGawean;

    GaweanListResponseItem item;
    private static final String TAG = "QRScannerActivity";

    public static void startActivity(Activity sourceActivity, GaweanListResponseItem item) {
        Intent intent = new Intent(sourceActivity, DetailPaketGaweanActivity.class);
        intent.putExtra("PLACE", item);
        sourceActivity.startActivity(intent);
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


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Gawean Paketan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        item = (GaweanListResponseItem) getIntent().getSerializableExtra("PLACE");

        Glide.with(this).load(item.getPictureUrl()).error(R.color.grey_200).into(imgGawean);
        txtDescGawean.setText(item.getDescription());
        txtFeeGawean.setText("Rp. " + item.getJobFee());
        txtTitleGawean.setText(item.getName());

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
