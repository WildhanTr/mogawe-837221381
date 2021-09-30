package com.mogawe.mosurvei.view.content.intro;

import android.Manifest;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.model.db.entity.User;
import com.mogawe.mosurvei.model.network.response.UserResponse;
import com.mogawe.mosurvei.model.store.PrefHelper;
import com.mogawe.mosurvei.model.store.PrefKey;
import com.mogawe.mosurvei.util.Constant;
import com.mogawe.mosurvei.util.ExceptionHandler;
import com.mogawe.mosurvei.util.ui.Animator;
import com.mogawe.mosurvei.view.BaseActivity;
import com.mogawe.mosurvei.view.content.about.IntroAboutActivity;
import com.mogawe.mosurvei.viewmodel.UserViewModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

public class IntroActivity extends BaseActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnSkip, btnNext, btnLogin, btnRegister;
    @BindView(R.id.btnFacebook)
    LinearLayout btnFacebook;
    @BindView(R.id.btnGoogle)
    LinearLayout btnGoogle;
    @BindView(R.id.btnTwitter)
    LinearLayout btnTwitter;
    @BindView(R.id.btnMoGawe)
    Button btnMoGawe;
    @BindView(R.id.btnMoBisnis)
    Button btnMoBisnis;
    @BindView(R.id.lnrIndicator)
    LinearLayout lnrIndicator;
    @BindView(R.id.lnrLogin)
    LinearLayout lnrLogin;

    private Bundle googleData = null;
    private Bundle facebookData = null;
    private String socmedPhotoUrl = "";

    private User user;
    private Boolean isForResults = false;
    private AlertDialog dialog;
    private ValueAnimator animator;
    private CallbackManager fbCallManager = CallbackManager.Factory.create();
    TwitterAuthClient client;

    private BaseActivity activity;

    private static final String TAG = "IntroActivity";
    private static final String FOR_RESULTS = "forResult";

    @Override
    protected int layout() {
        return R.layout.a_intro;
    }

    public static void startActivity(Activity sourceActivity) {
        sourceActivity.startActivity(new Intent(sourceActivity, IntroActivity.class));
        sourceActivity.finish();
    }
    public static void startActivityWithNoFinish(Activity sourceActivity) {
        sourceActivity.startActivity(new Intent(sourceActivity, IntroActivity.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = this;

        client = new TwitterAuthClient();
        isForResults = getIntent().getBooleanExtra(FOR_RESULTS, false);
        userViewModel.getResponseLiveData().observe(this, new androidx.lifecycle.Observer<UserResponse>() {
            @Override
            public void onChanged(UserResponse response) {
                if (response != null) {
                    if (response.isSuccess()) {
                        switch (response.getRequestKey()) {
                            case LOGIN:
                                break;
                            case LOGIN_SOCMED:
                                if (response.getReturnValue().equals("000")) {
                                    switch (response.getType()) {
                                        case "register":
                                            activity.setProgressDialogMessage("Sedang menyiapkan akun anda...");
                                            if (socmedPhotoUrl != null && !socmedPhotoUrl.equals("")) {
                                                imageDownload(getApplicationContext(), socmedPhotoUrl, userViewModel, user);
                                            } else {
                                                userViewModel.checkTerms();
//                                                nextActivity();
                                            }
                                            break;
                                        case "login":
                                            userViewModel.checkTerms();
//                                            nextActivity();
                                    }
                                } else {
                                    activity.dismissProgressDialog();
                                    activity.showNeutralCostumDialog("Terjadi kesalahan", "Coba lagi", false);
                                }
                                break;
                            case UPDATE_PHOTO_PROFILE:
                                if (response.getReturnValue().equals("000")) {
                                    activity.setProgressDialogMessage("Hampir selesai...");
                                    userViewModel.checkTerms();
//                                    nextActivity();
                                } else {
                                    activity.dismissProgressDialog();
                                    activity.showNeutralCostumDialog("Terjadi kesalahan", "Coba lagi", false);
                                }
                                break;
                            case TERMS_READ:
                                PrefHelper.setBoolean(PrefKey.IS_LOGGED_IN, true);
                                PrefHelper.setBoolean(PrefKey.CERTIFICATE_AFTER_LOGIN, true);
                                nextActivity();
                                break;

                        }
                    } else {
                        hideLoading();
                        dismissProgressDialog();
                        if (response.getRequestKey() == UserResponse.RequestKey.TERMS_NOT_READ) {

                        } else {
                            if (response.getMessage().equals(Constant.SOCMED_EMPTY_PASSWORD)) {
                                txvTitleCostumDialog.setText("Pemberitahuan");
                                txvMessageCostumDialog.setText("Email ini sudah didaftarkan menggunakan akun sosial media dan belum memiliki password. Buat " +
                                        "password sekarang?");
                                btnPositiveCostumDialog.setText("Buat Password");
                                btnPositiveCostumDialog.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                });
                                btnNegativeCostumDialog.setText("Tidak, Nanti Saja");
                                btnNegativeCostumDialog.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        costumDialog.dismiss();
                                    }
                                });
                                showCostumDialog("Oke&Cancel");
                                hideLoading();
                                dismissProgressDialog();
                            } else {
                                if (!response.getReturnValue().equals("002")) {
                                    showNeutralCostumDialog(titleRequestFailed, response.getMessage(), false);
                                }
                            }
                        }

                    }
                }
            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET}, 1);
        }


        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);


        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.f_onboarding_slide_1,
                R.layout.f_onboarding_slide_2,
                R.layout.f_onboarding_slide_3,
                R.layout.f_onboarding_slide_final,};

        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);


        //AUTO SCROLL TIMER
        //Uncomment this to start auto scroll page
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new SliderTimer(), 3000, 5000);
//
//        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }


    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            //IF LAST SLIDE THEN CHANGE LAYOUT
            if (position == layouts.length - 1) {
                lnrIndicator.setVisibility(View.GONE);
                lnrLogin.setVisibility(View.VISIBLE);
            } else {
                lnrIndicator.setVisibility(View.VISIBLE);
                lnrLogin.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            IntroActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() < layouts.length - 1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        fbCallManager.onActivityResult(requestCode, resultCode, data);
        client.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_GOOGLE_SING_IN:
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                handleSignInResult(task);
                break;

            case REQUEST_TWIITER_SIGN_IN:
                client.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PrefHelper.getBoolean(PrefKey.IS_LOGGED_IN) && !isForResults) {

        } else {
            //REMOVE NOTIFICATION
            userViewModel.clearNotification();
        }
    }

    private void hideLoading() {
        if (animator != null)
            animator.cancel();

        if (dialog != null)
            dialog.dismiss();
    }

    public void nextActivity() {

    }

    public static void imageDownload(Context ctx, String url, UserViewModel userViewModel, User user) {
        Picasso.get()
                .load(url)
                .into(getTarget(url, userViewModel, user));
    }

    //target to save
    private static Target getTarget(final String url, UserViewModel userViewModel, User user) {
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        File file = new File(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MoGawe Pictures").getPath() + "/profilGambar.jpg");
                        try {
                            file.createNewFile();
                            FileOutputStream ostream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, ostream);
                            ostream.flush();
                            ostream.close();
                            userViewModel.updatePhotoProfile(user, file);
                        } catch (IOException e) {
                            Log.e("IOException", e.getLocalizedMessage());
                        }
                    }
                }).start();

            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        return target;
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            googleData = new Bundle();
            googleData.putString("codeSocmed", "2");
            googleData.putString("idSocmed", account.getId());
            googleData.putString("display_name", account.getDisplayName());
            googleData.putString("email", account.getEmail());

            if (account.getPhotoUrl() != null)
                googleData.putString("photoUrl", account.getPhotoUrl().toString());
            else
                googleData.putString("photoUrl", null);

            user = new User();
            user.setProfilePicture(googleData.getString("profile_pic"));
            user.setFullName(googleData.getString("display_name"));
            user.setEmail(googleData.getString("email"));
            user.setProfilePicture(googleData.getString("photoUrl"));
            user.setSocmedId(googleData.getString("idSocmed"));

            socmedPhotoUrl = user.getProfilePicture();

            this.showProgressDialog("Mencoba Login dengan akun google anda...");
            userViewModel.loginSocMedia(user, BaseActivity.SOCMED_GMAIL);
//            userViewModel.updatePhotoProfile(user, f);
            //updateUI(account);
        } catch (ApiException e) {
//            showNeutralCostumDialog(null,"signInResult:failed code=" + e.getStatusCode(),false);
            if (e.getStatusCode() != 12501) {
                showNeutralCostumDialog(null, "Gagal masuk. Error Code : " + e.getStatusCode(), false);
            }
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            //updateUI(null);
        }
    }


    public void fetchTwitterEmail(final TwitterSession twitterSession) {
        client.requestEmail(twitterSession, new Callback<String>() {
            @Override
            public void success(Result<String> result) {
                //here it will give u only email and rest of other information u can get from TwitterSession
                System.out.println("data email");
                System.out.println(result.data);
                //  userDetailsLabel.setText("User Id : " + twitterSession.getUserId() + "\nScreen Name : " + twitterSession.getUserName() + "\nEmail Id : " + result.data);
            }

            @Override
            public void failure(TwitterException exception) {
//                Toast.makeText(MainActivity.this, "Failed to authenticate. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @OnClick(R.id.btnLogin)
    void onClickLogin(View v) {
        Animator.popTap(v);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                LoginActivity.startActivity(IntroActivity.this);
            }
        }, Animator.VERY_SHORT);
    }

    @OnClick(R.id.btnRegister)
    void onClickRegister(View v) {
        Animator.popTap(v);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                RegistrationActivity.startActivity(IntroActivity.this);
            }
        }, Animator.VERY_SHORT);
    }

    @OnClick(R.id.btnMoGawe)
    void onClickMoGawe(View v) {
        Animator.popTap(v);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                LoginActivity.startActivity(IntroActivity.this);
            }
        }, Animator.VERY_SHORT);
    }

    @OnClick(R.id.btnMoBisnis)
    void onClickMobisnis(View v) {
        Animator.popTap(v);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                SupplierLoginActivity.startActivity(IntroActivity.this);
            }
        }, Animator.VERY_SHORT);
    }


    @OnClick(R.id.btnGoogle)
    void onClickLoginGoogle(View v) {

        Animator.popTap(v);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();
                GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(IntroActivity.this, gso);

                if (googleData == null) {
                    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(IntroActivity.this);
                    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                    startActivityForResult(signInIntent, REQUEST_GOOGLE_SING_IN);
                } else {
                    mGoogleSignInClient.signOut()
                            .addOnCompleteListener(IntroActivity.this, new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    googleData = null;
                                    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(IntroActivity.this);
                                    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                                    startActivityForResult(signInIntent, REQUEST_GOOGLE_SING_IN);
                                }
                            });
                }
            }
        }, 200);


    }

    @OnClick(R.id.btnFacebook)
    void onClickLoginFacebook(View v) {

        Animator.popTap(v);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (facebookData == null) {
                    LoginManager.getInstance().logInWithReadPermissions(IntroActivity.this, Arrays.asList("public_profile", "email"));
                    LoginManager.getInstance().registerCallback(fbCallManager,
                            new FacebookCallback<LoginResult>() {
                                @Override
                                public void onSuccess(LoginResult loginResult) {
                                    AccessToken accessToken = AccessToken.getCurrentAccessToken();
                                    GraphRequest request = GraphRequest.newMeRequest(
                                            accessToken,
                                            new GraphRequest.GraphJSONObjectCallback() {
                                                @Override
                                                public void onCompleted(JSONObject object, GraphResponse response) {
                                                    Log.i("LoginActivity", response.toString());

                                                    facebookData = new Bundle();
                                                    facebookData = getFacebookData(object);

                                                    user = new User();
                                                    user.setProfilePicture(facebookData.getString("profile_pic"));

                                                    List<String> fullname = new ArrayList<>();

                                                    String firstName = facebookData.getString("first_name") != null ? facebookData.getString("first_name") : "";
                                                    fullname.add(firstName);
                                                    String middleName = facebookData.getString("middle_name") != null ? facebookData.getString("middle_name") : "";
                                                    fullname.add(middleName);
                                                    String lastName = facebookData.getString("last_name") != null ? facebookData.getString("last_name") : "";
                                                    fullname.add(lastName);

                                                    String fullnameString = "";
                                                    Integer index = 0;
                                                    for (String s : fullname) {
                                                        if (index != 0)
                                                            fullnameString += " ";

                                                        fullnameString += s;
                                                        index++;
                                                    }

                                                    user.setFullName(fullnameString);
                                                    user.setSocmedId(facebookData.getString("idSocmed"));
                                                    user.setEmail(facebookData.getString("email"));

                                                    socmedPhotoUrl = user.getProfilePicture();
                                                    activity.showProgressDialog("mencoba login dengan akun facebook anda...");

                                                    if (user.getEmail() != null) {
                                                        userViewModel.loginSocMedia(user, BaseActivity.SOCMED_FB);
                                                    } else {
                                                        LoginManager.getInstance().logOut();
                                                        AccessToken.setCurrentAccessToken(null);
                                                        facebookData = null;
                                                        showNeutralCostumDialog("Maaf", "Facebook kamu tidak mengijinkan kami mengakses email", false);
                                                    }

                                                }
                                            });
                                    Bundle parameters = new Bundle();

                                    parameters.putString("fields", "id,first_name,middle_name,last_name,email");
                                    request.setParameters(parameters);
                                    request.executeAsync();
                                }

                                @Override
                                public void onCancel() {
                                    // App code
                                }

                                @Override
                                public void onError(FacebookException exception) {
                                    System.out.println(exception);
                                    System.out.println("something wrong");
                                }
                            });
                } else {
                    LoginManager.getInstance().logOut();
                    AccessToken.setCurrentAccessToken(null);
                    facebookData = null;

                }
            }
        }, 200);


    }

    private Bundle getFacebookData(JSONObject object) {

        try {
            Bundle bundle = new Bundle();
            String id = object.getString("id");
            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("codeSocmed", "1");
            bundle.putString("idSocmed", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("middle_name"))
                bundle.putString("middle_name", object.getString("middle_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));

            return bundle;
        } catch (JSONException e) {

        }
        return null;
    }

    @OnClick(R.id.btnTwitter)
    void onClickTwitter(View v) {

        Animator.popTap(v);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                client.authorize(IntroActivity.this, new Callback<TwitterSession>() {
                    @Override
                    public void success(Result<TwitterSession> result) {

                        System.out.println("success");

                        // Do something with result, which provides a TwitterSession for making API calls
                        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                        TwitterAuthToken authToken = session.getAuthToken();

                        String token = result.data.getAuthToken().token;
                        String secret = result.data.getAuthToken().secret;
                        TwitterSession user = result.data;


                        // TwitterSession twitterSession = session;
                        System.out.println("session");
                        System.out.println("userid : " + result.data.getUserId());
                        System.out.println("username : " + result.data.getUserName());
                        fetchTwitterEmail(result.data);

                        login(user);

                    }

                    @Override
                    public void failure(TwitterException exception) {
                        System.out.println("failure");
                        // Do something on failure
//                        Toast.makeText(IntroActivity.this, "Autentication Failed!", Toast.LENGTH_SHORT).show();
                        onClickTwitter(v);
                    }

                    public void login(TwitterSession session) {
                        TwitterCore.getInstance().getApiClient(session).getAccountService().verifyCredentials(true, false, true).enqueue(new Callback<com.twitter.sdk.android.core.models.User>() {
                            @Override
                            public void success(Result<com.twitter.sdk.android.core.models.User> userResult) {
                                try {
                                    com.twitter.sdk.android.core.models.User userTwitter = userResult.data;
                                    String fullname = userTwitter.name;
                                    String userId = userTwitter.id + "";
                                    String userSocialProfile = userTwitter.profileImageUrl;
                                    String userEmail = userTwitter.email;


                                    user = new User();
                                    user.setFullName(fullname);
                                    user.setSocmedId(userId);
                                    user.setProfilePicture(userSocialProfile);
                                    user.setEmail(userEmail);

                                    socmedPhotoUrl = userSocialProfile;
                                    activity.showProgressDialog("mencoba login dengan akun twitter anda...");
                                    userViewModel.loginSocMedia(user, BaseActivity.SOCMED_TWT);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void failure(TwitterException e) {
                            }
                        });
                    }
                });
            }
        }, 200);
    }


    @OnClick(R.id.btnNext)
    void onClickNext(View v) {
        Animator.popTap(v);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewPager.getCurrentItem() < layouts.length - 1) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            }
        }, Animator.VERY_SHORT);
    }
}
