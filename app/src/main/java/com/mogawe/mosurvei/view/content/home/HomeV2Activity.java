package com.mogawe.mosurvei.view.content.home;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
    }
}