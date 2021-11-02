package com.mogawe.mosurvei.view.shared.gawean;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.Result;
import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.model.bean.gawean.GaweanListResponse;
import com.mogawe.mosurvei.model.bean.gawean.GaweanListResponseItem;
import com.mogawe.mosurvei.model.db.entity.Section;
import com.mogawe.mosurvei.model.network.response.transaction.TransactionResponse;
import com.mogawe.mosurvei.model.network.response.transaction.WalletHistoryResponse;
import com.mogawe.mosurvei.model.store.PrefHelper;
import com.mogawe.mosurvei.model.store.PrefKey;
import com.mogawe.mosurvei.playground.PaketGaweanAdapter;
import com.mogawe.mosurvei.util.ExceptionHandler;
import com.mogawe.mosurvei.view.BaseActivity;
import com.mogawe.mosurvei.view.shared.planogram.AdapterFacingRow;

import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PaketGaweanActivity extends BaseActivity {

    RecyclerView listGawean;
    AppCompatSeekBar seekBar;
    Button btnTerima;
    PaketGaweanAdapter adapter;
    ArrayList<GaweanListResponseItem> items = new ArrayList<>();
    int step = 1;
    int max = 1000000;
    int min = 10000;


    private static final String TAG = "QRScannerActivity";

    public static void startActivity(Activity sourceActivity, int reqCode) {
        sourceActivity.startActivityForResult(new Intent(sourceActivity, PaketGaweanActivity.class), reqCode);
    }

    public static void startActivity(Activity sourceActivity, int reqCode, Section section) {
        sourceActivity.startActivityForResult(new Intent(sourceActivity, PaketGaweanActivity.class).putExtra(SECTION, section), reqCode);
    }

    @Override
    protected int layout() {
        return R.layout.a_gawean_paketan;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));

        listGawean = findViewById(R.id.listGawean);
        seekBar = findViewById(R.id.seekBar);
        btnTerima = findViewById(R.id.btnTerima);

        adapter = new PaketGaweanAdapter(items);
        RecyclerView.LayoutManager mLayoutManagerRow = new LinearLayoutManager(getApplicationContext());
        listGawean.setLayoutManager(mLayoutManagerRow);
        listGawean.setItemAnimator(new DefaultItemAnimator());
        listGawean.setAdapter(adapter);

        btnTerima.setOnClickListener(v -> {
            DetailPaketGaweanActivity.startActivity(this, 1);
        });

        loadData();

        seekBar.setMax( (max - min) / step );

        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener()
                {
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {}

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {}

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress,
                                                  boolean fromUser)
                    {
                        // Ex :
                        // And finally when you want to retrieve the value in the range you
                        // wanted in the first place -> [3-5]
                        //
                        // if progress = 13 -> value = 3 + (13 * 0.1) = 4.3
                        double value = min + (progress * step);

                    }
                }
        );

    }

    void loadData(){
        mMyApp.getApiNaufalService().getGaweanList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GaweanListResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                    }

                    @Override
                    public void onNext(GaweanListResponse response) {
                        if (!response.getGaweanListResponse().isEmpty()) {
                            adapter.updateData((ArrayList<GaweanListResponseItem>) response.getGaweanListResponse());
                        }
                    }
                });
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
