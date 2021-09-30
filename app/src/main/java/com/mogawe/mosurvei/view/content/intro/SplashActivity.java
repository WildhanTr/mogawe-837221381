package com.mogawe.mosurvei.view.content.intro;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.model.network.response.UserResponse;
import com.mogawe.mosurvei.model.store.PrefHelper;
import com.mogawe.mosurvei.model.store.PrefKey;
import com.mogawe.mosurvei.util.Constant;
import com.mogawe.mosurvei.util.ExceptionHandler;
import com.mogawe.mosurvei.util.MutedVideoView;
import com.mogawe.mosurvei.view.BaseActivity;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.txvVersion)
    TextView txvVersion;
    @BindView(R.id.lnrUpdateApp)
    RelativeLayout lnrUpdateApp;
    @BindView(R.id.btnUpdateApp)
    Button btnUpdateApp;
    @BindView(R.id.txvMessage)
    TextView txvMessage;
    @BindView(R.id.tvAbaikan)
    TextView tvAbaikan;
    @BindView(R.id.txvMessageForced)
    TextView txvMessageForced;
    @BindView(R.id.videoView)
    MutedVideoView videoView;

    private String versionCode;

    public static void startActivity(Activity sourceActivity) {
        sourceActivity.startActivity(new Intent(sourceActivity, SplashActivity.class));
    }

    @Override
    protected int layout() {
        return R.layout.a_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(PrefHelper.getLong(PrefKey.NEXT_NEAR_JOB_NOTIFICATION_TIMESTAMP) == 0){
            PrefHelper.setLong(PrefKey.NEXT_NEAR_JOB_NOTIFICATION_TIMESTAMP, new Date().getTime() + Constant.NEAR_JOB_NOTIFICATION_INTERVAL);
        }

        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (pInfo != null) {
            String version = "Versi " + pInfo.versionName;
            txvVersion.setText(version);
            versionCode = String.valueOf(pInfo.versionCode);
        }
        final String versionName = pInfo.versionName;
        final Integer versionNumber = pInfo.versionCode;

        userViewModel.getResponseLiveData().observe(this, new Observer<UserResponse>() {
            @Override
            public void onChanged(UserResponse userResponse) {
                if(userResponse.isSuccess()){
                    switch (userResponse.getMessage()){
                        case Constant.DEPRECATED_VERSION:
                            txvTitleCostumDialog.setText("MoGawe Update");
                            txvMessageCostumDialog.setText("Harap update ke versi terbaru");
                            btnPositiveCostumDialog.setText("Ok");
                            btnPositiveCostumDialog.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String url = "https://play.google.com/store/apps/details?id=com.mogawe.mosurvei";
                                    try {
                                        PackageManager pm = getPackageManager();
                                        pm.getPackageInfo("com.android.vending", PackageManager.GET_ACTIVITIES);
                                        Intent i = new Intent(Intent.ACTION_VIEW);
                                        i.setData(Uri.parse(url));
                                        startActivity(i);
                                        finish();
                                    } catch (PackageManager.NameNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            costumDialog.setCancelable(false);
                            costumDialog.setCanceledOnTouchOutside(false);
                            showCostumDialog("Oke");

                            break;
                        case Constant.OLD_VERSION:
                            PrefHelper.setBoolean(PrefKey.UPDATE_AVAILABLE, true);
                            nextActivity();
                            break;
                        case Constant.LATEST_VERSION:
                            PrefHelper.setBoolean(PrefKey.UPDATE_AVAILABLE, false);
                            nextActivity();
                    }
                }else{
                    nextActivity();
                }
            }
        });

        setupViews();

        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash_logo);
        videoView.setVideoURI(video);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                View placeholder = (View) findViewById(R.id.placeholder);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        placeholder.setVisibility(View.GONE);
                    }
                },600);

            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                userViewModel.checkVersionV2(versionNumber);
            }
        });

        videoView.start();

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
    }

    private void setupViews() {
        txvMessage.setText(Html.fromHtml(getString(R.string.update_available_message)));
    }

    public void nextActivity() {
        IntroActivity.startActivity(SplashActivity.this);
    }

    public void updateApp(String force_download, String version) {
        lnrUpdateApp.setVisibility(View.VISIBLE);

        if (force_download.equals("1")) {
            tvAbaikan.setVisibility(View.GONE);

            txvMessageForced.setText(Html.fromHtml(getString(R.string.update_force)));

            txvMessageForced.setVisibility(View.VISIBLE);
        }

    }


    @OnClick(R.id.btnUpdateApp)
    public void onUpdateClick() {
        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    @OnClick(R.id.tvAbaikan)
    public void onTextClick() {
        nextActivity();
    }

}