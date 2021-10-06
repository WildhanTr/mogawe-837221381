package com.mogawe.mosurvei.view.content.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.view.BaseActivity;

import butterknife.BindView;

public class ProfileV2Activity extends BaseActivity {

//    @BindView(R.id.toolbar)
//    Toolbar appBar;

    public static void startActivity(BaseActivity sourceActivity) {
        Intent intent = new Intent(sourceActivity, ProfileV2Activity.class);
        sourceActivity.startActivity(intent);
    }

    @Override
    protected int layout() {
        return R.layout.a_profile_v2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setTitle("Profile Activity");
//        setSupportActionBar(appBar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        appBar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //What to do on back clicked
//                onBackPressed();
//            }
//        });
    }
}