package com.mogawe.mosurvei.view.content.hire_me.instant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.AppBarLayout;
import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.view.BaseActivity;
import com.mogawe.mosurvei.view.content.hire_me.bundled.BundleAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class InstantActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar appBar;

    @BindView(R.id.pagerSample)
    ViewPager2 pagerSample;

    @Override
    protected int layout() {
        return R.layout.a_instant;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Instant Activity");
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

        initiateViewPager();
    }

    public void initiateViewPager(){

        List<String> stringList = new ArrayList<>(Arrays.asList("1","2","3","4","5","6","7","8","9","10"));

        BundleAdapter adapter = new BundleAdapter(InstantActivity.this, stringList);
        pagerSample.setAdapter(adapter);
    }
}