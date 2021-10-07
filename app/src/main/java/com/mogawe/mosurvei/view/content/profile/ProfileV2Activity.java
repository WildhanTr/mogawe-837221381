package com.mogawe.mosurvei.view.content.profile;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.view.BaseActivity;
import com.mogawe.mosurvei.view.content.profiledetail.ProfileDetailActivity;

import butterknife.BindView;

public class ProfileV2Activity extends BaseActivity {

    @BindView(R.id.nested_scroll_profile)
    NestedScrollView nestedScrollProfile;
    @BindView(R.id.iv_menu_chat)
    AppCompatImageView ivProfileMenuChat;
    @BindView(R.id.iv_menu_notification)
    AppCompatImageView ivProfileMenuNotif;
    @BindView(R.id.iv_menu_profile)
    AppCompatImageView ivProfileMenuProfile;
    @BindView(R.id.iv_target_harian_view_more)
    AppCompatImageView ivProfileTargetHarianViewMore;
    @BindView(R.id.btn_target_harian_add_gawean)
    AppCompatButton btnProfileTambahGawean;
    @BindView(R.id.tv_gawean_menu_penugasan)
    AppCompatTextView tvGaweanPenugasan;
    @BindView(R.id.tv_gawean_menu_etalase)
    AppCompatTextView tvGaweanEtalase;
    @BindView(R.id.ll_gawean_view_more)
    LinearLayoutCompat llGaweanViewMore;

    @BindView(R.id.vp_profile_menu)
    ViewPager2 vpProfileBanner;
    @BindView(R.id.rv_proflie_gawean)
    RecyclerView rvProfileGawen;

    private AdapterVPBanner adapterBanner;
    private AdapterGaweanPenugasan adapterPenugasan;
    private AdapterGaweanEtalase adapterEtalase;

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

        setupAdapter();
        initDataResponse();
        initComponent();
        setupOnClick();
    }

    private void initComponent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            nestedScrollProfile.setOnScrollChangeListener(
                    (View.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                        if (scrollY > 120 && nestedScrollProfile.isShown()) {
                            vpProfileBanner.setVisibility(View.GONE);
                        } else if (scrollY < 120) {
                            vpProfileBanner.setVisibility(View.VISIBLE);
                        }
                    });
        } else {
            nestedScrollProfile.getViewTreeObserver().addOnScrollChangedListener(() -> {
                int mScrollY = nestedScrollProfile.getScrollY();
                if (mScrollY > 120 && nestedScrollProfile.isShown()) {
                    vpProfileBanner.setVisibility(View.GONE);
                } else if (mScrollY < 120) {
                    vpProfileBanner.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    private void setupOnClick() {
        ivProfileMenuChat.setOnClickListener(v -> {
            Toast.makeText(this, "Menu Chat Selected", Toast.LENGTH_SHORT).show();
        });
        ivProfileMenuNotif.setOnClickListener(v -> {
            Toast.makeText(this, "Menu Chat Selected", Toast.LENGTH_SHORT).show();
        });
        ivProfileMenuProfile.setOnClickListener(v -> {
            Toast.makeText(this, "Menu Chat Selected", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, ProfileDetailActivity.class));
        });
        ivProfileTargetHarianViewMore.setOnClickListener(v -> {
            Toast.makeText(this, "View More Target Harian", Toast.LENGTH_SHORT).show();
        });
        btnProfileTambahGawean.setOnClickListener(v -> {
            Toast.makeText(this, "Tambah Gawean Target Harian", Toast.LENGTH_SHORT).show();
        });
        tvGaweanPenugasan.setOnClickListener(v -> {
            Toast.makeText(this, "Menu Penugasan Gawean Selected", Toast.LENGTH_SHORT).show();
            tvGaweanPenugasan.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.bg_menu_on_left));
            tvGaweanPenugasan.setTextColor(ContextCompat.getColor(this, R.color.white));
            tvGaweanEtalase.setBackgroundDrawable(null);
            tvGaweanEtalase.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
            initAdapter(1);
        });
        tvGaweanEtalase.setOnClickListener(v -> {
            Toast.makeText(this, "Menu Penugasan Gawean Selected", Toast.LENGTH_SHORT).show();
            tvGaweanPenugasan.setBackgroundDrawable(null);
            tvGaweanPenugasan.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
            tvGaweanEtalase.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.bg_menu_on_right));
            tvGaweanEtalase.setTextColor(ContextCompat.getColor(this, R.color.white));
            initAdapter(2);
        });
        llGaweanViewMore.setOnClickListener(v -> {
            Toast.makeText(this, "Lihat Semua Gawean Selected", Toast.LENGTH_SHORT).show();
        });

    }

    private void initDataResponse() {

    }

    private void setupAdapter() {
        adapterBanner = new AdapterVPBanner();
        adapterBanner.setOnItemClick(
                select -> {
                    Toast.makeText(this, "Banner Select", Toast.LENGTH_SHORT).show();
                    return null;
                });

        adapterPenugasan = new AdapterGaweanPenugasan();
        adapterPenugasan.setOnItemClick(
                select -> {
                    Toast.makeText(this, "Penugasan Select", Toast.LENGTH_SHORT).show();
                    return null;
                });

        adapterEtalase = new AdapterGaweanEtalase();
        adapterEtalase.setOnItemClick(
                select -> {
                    Toast.makeText(this, "Etalase Select", Toast.LENGTH_SHORT).show();
                    return null;
                });

        initAdapter(1);
        vpProfileBanner.setAdapter(adapterBanner);
    }

    private void initAdapter(int i) {
        switch (i) {
            case 1:
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                rvProfileGawen.setLayoutManager(linearLayoutManager);
                rvProfileGawen.setAdapter(adapterPenugasan);
                break;
            case 2:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
                rvProfileGawen.setLayoutManager(gridLayoutManager);
                rvProfileGawen.setAdapter(adapterEtalase);
                break;
        }
    }

}