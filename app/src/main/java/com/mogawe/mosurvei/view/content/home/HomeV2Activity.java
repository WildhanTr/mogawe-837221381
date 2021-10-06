package com.mogawe.mosurvei.view.content.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.google.android.material.appbar.AppBarLayout;
import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.view.BaseActivity;

import butterknife.BindView;

public class HomeV2Activity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar appBar;


    public static void startActivity(BaseActivity sourceActivity) {
        Intent intent = new Intent(sourceActivity, HomeV2Activity.class);
        sourceActivity.startActivity(intent);
    }

    @Override
    protected int layout() {
        return R.layout.a_home_v2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Home Activity");
        setSupportActionBar(appBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        appBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });

        AppBarLayout.LayoutParams params = new AppBarLayout.LayoutParams(
                AppBarLayout.LayoutParams.MATCH_PARENT,
                AppBarLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, getStatusBarHeight(), 0, 0);
        appBar.setLayoutParams(params);

        // TODO
        // this will be temporary
        // set up the button for showing DailyTargetFragment
        buttonShowDailyTargetFragmentWithSurplus();
        buttonShowDailyTargetFragmentNotReached();
    }

    // TODO
    // this is to show the DailyTarget fragment, this is just temporary
    // and will be removed
    private void buttonShowDailyTargetFragmentWithSurplus() {
        Button button = findViewById(R.id.btn_show_daily_target_surplus_fragment);
        button.setOnClickListener(v -> {
            System.out.println("show DailyTarget fragment");
            showDailyTargetFragmentWithSurplus();
        });
    }

    // TODO
    // this is to show the DailyTarget fragment, this is just temporary
    // and will be removed
    private void buttonShowDailyTargetFragmentNotReached() {
        Button button = findViewById(R.id.btn_show_daily_target_not_reached_fragment);
        button.setOnClickListener(v -> {
            System.out.println("show DailyTarget fragment");
            showDailyTargetFragmentNotReached();
        });
    }

    private void showDailyTargetFragmentWithSurplus() {
        DailyTargetFragment.showDailyTargetWithSurplus(this);
    }

    private void showDailyTargetFragmentNotReached() {
        DailyTargetFragment.showDailyTargetNotReached(this);
    }
}