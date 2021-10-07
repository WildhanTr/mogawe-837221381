package com.mogawe.mosurvei.view.content.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.view.BaseActivity;
import com.mogawe.mosurvei.view.content.home.HomeV2Activity;

import butterknife.BindView;

public class ProfileV2Activity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar appBar;


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

        setTitle("Profile Activity");
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

        ProfileNavBar profileNavBar = new ProfileNavBar(this);
    }
}