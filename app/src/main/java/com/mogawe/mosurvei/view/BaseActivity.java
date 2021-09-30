package com.mogawe.mosurvei.view;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.gson.Gson;
import com.mogawe.mosurvei.MoSurveiApplication;
import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.fcm.MoGaweFirebaseMessagingService;
import com.mogawe.mosurvei.model.db.entity.Task;
import com.mogawe.mosurvei.model.network.Service;
import com.mogawe.mosurvei.model.network.response.section.SectionSourceResponse;
import com.mogawe.mosurvei.model.store.PrefHelper;
import com.mogawe.mosurvei.model.store.PrefKey;
import com.mogawe.mosurvei.util.Constant;
import com.mogawe.mosurvei.util.ExceptionHandler;
import com.mogawe.mosurvei.util.RxBus;
import com.mogawe.mosurvei.util.RxBusObject;
import com.mogawe.mosurvei.view.shared.webview.WebviewActivity;
import com.mogawe.mosurvei.viewmodel.PointViewModel;
import com.mogawe.mosurvei.viewmodel.ResultViewModel;
import com.mogawe.mosurvei.viewmodel.SalesViewModel;
import com.mogawe.mosurvei.viewmodel.TaskViewModel;
import com.mogawe.mosurvei.viewmodel.TransactionViewModel;
import com.mogawe.mosurvei.viewmodel.UserViewModel;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindColor;
import butterknife.BindString;
import butterknife.ButterKnife;
import icepick.Icepick;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseActivity extends AppCompatActivity {

    public static final String JOB_OBSERVATION = "1";
    public static final String JOB_DISPLAY_SCAN = "2";
    public static final String JOB_STORE_COUNT = "3";
    public static final String JOB_INTERVIEW = "5";
    public static final String JOB_CENSUS = "6";
    public static final int REQUEST_ACTIVATION = 11;
    public final static int REQUEST_TAKE_PHOTO = 1;
    public final static int REQUEST_LOC = 221;
    public final static int REQUEST_SHIIPING_ADDRESS = 222;
    public final static int PICK_CONTACT = 577;
    public final static int REQUEST_LOGISTIC = 223;
    public final static int REQUEST_TAKE_PHOTO_FROM_GALLERY = 17;
    public final static int REQUEST_TAKE_AUDIO = 191;
    public final static int REQUEST_TAKE_VIDEO = 101;
    public final static int REQUEST_TAKE_BACKGROUND_VIDEO = 103;
    public final static int REQUEST_TAKE_SCREEN_RECORDER = 102;
    public static final int REQUEST_ORDER = 1201;
    public final static int REQUEST_SELECT_GALLERY = 2;
    public final static int REQUEST_CHANGE_PHONE = 3;
    public final static int REQUEST_WEBVIEW = 4;
    public final static int REQUEST_WEB_CAPTURE = 55;
    public final static int REQUEST_TERMS_AND_CONDS = 12;
    public static final int REQUEST_GOOGLE_SING_IN = 9001;
    public static final int REQUEST_TWIITER_SIGN_IN = 9002;
    public static final int RESULT_CANCEL_ORDER = 111;
    public static final int RESULT_FINISH = 2;
    public static final int RESULT_COMPLETE = 40;
    public static final int RESULT_SCREENOUT = 41;
    public static final int RESULT_QUOTAFULL = 42;
    public static final int RESULT_CLOSED = 43;
    public static final int RESULT_CANCELED = 44;
    public static final int RESULT_ACCEPTED = 45;
    public static final int SOCMED_FB = 1;
    public static final int SOCMED_TWT = 3;
    public static final int SOCMED_GMAIL = 2;
    public final static int REQUEST_BARCODE = 14;
    public final static int RESULT_BARCODE = 15;
    public final static int REQUEST_END_ACTIVITY = 104;
    public static final int REQUEST_READ_OR_WRITE_STORAGE = 112;
    public static final int UUID_GAWEAN_INSTANT_UPDATE = 114;

    public static final String INSTAGRAM = "3258af1e-a10e-4c30-a880-8be92f9f4761";
    public static final String STANDART = "f6e800ab-d5c9-4ae8-b0fa-337145088312";
    public static final String ACTIVITY_TRACKER = "45a0d09c-5b2f-494c-b5e8-d2b8a231ceb9";
    public static final String WEB_RESULT = "webResult";
    public static final String QR_CODE = "qrcode";
    public static final String SECTION = "section";
    public static final String FILE_PATH = "filePath";
    public static final String FACT = "fact";

    private static final String TAG = "BaseActivity";
    @BindString(R.string.error_no_internet)
    public String errorInternet;
    @BindString(R.string.request_failed)
    public String requestFailed;
    @BindString(R.string.error_location_not_found)
    public String errorLocationNotFound;
    @BindString(R.string.please_wait)
    public String pleaseWait;
    @BindString(R.string.starting_job)
    public String startingJob;
    @BindColor(R.color.colorPrimary)
    public int colorPrimary;
    @BindColor(R.color.grey_600)
    public int colorGrey;

    @BindString(R.string.info_title_fill_all_form)
    public String titleFillAllForm;
    @BindString(R.string.info_title_all_form_must_valid)
    public String titleAllFormValid;
    @BindString(R.string.info_title_request_failed)
    public String titleRequestFailed;
    @BindString(R.string.info_title_request_success)
    public String titleRequestSuccess;
    @BindString(R.string.info_email_sent)
    public String infoEmailSent;

    @BindString(R.string.info_please_fill_full_name)
    public String pleaseFillFullname;
    @BindString(R.string.info_please_fill_phone_number)
    public String pleaseFillPhoneNumber;
    @BindString(R.string.info_please_fill_email_registration)
    public String pleaseFillEmailRegistration;
    @BindString(R.string.info_please_fill_email)
    public String pleaseFillEmail;
    @BindString(R.string.info_email_not_valid)
    public String emailNotValid;
    @BindString(R.string.info_phone_not_valid)
    public String phoneNotValid;
    @BindString(R.string.info_please_fill_password_registration)
    public String pleaseFillPasswordRegistration;
    @BindString(R.string.info_please_fill_password)
    public String pleaseFillPassword;
    @BindString(R.string.info_please_agree_terms_and_conds)
    public String pleaseAgreeTermsAndConds;
    @BindString(R.string.info_please_fill_activation_code)
    public String pleaseFillActivationCode;
    @BindString(R.string.info_please_fill_new_password)
    public String pleaseFillNewPassword;
    @BindString(R.string.info_please_insert_birthdate)
    public String pleaseInsertBirthdate;

    private RxBus bus;
    private Service service;
    private CompositeSubscription subscriptions;
    private ProgressDialog progressDialog;
    private Handler handleHorizontalProgressBar;

    public AlertDialog dialog, costumDialog;
    public AlertDialog.Builder builder;
    public LayoutInflater inflater;
    public LinearLayout vg;
    public View dialogView, costumDialogView;

    public TextView txvMessageDialog;
    public ProgressBar pgbWaitingDialog;
    public Button btnPositiveDialog;
    public Button btnNegativeDialog;
    public TextView txvCheckboxDialog;
    public CheckBox cbCheckBoxDialog;

    public TaskViewModel taskViewModel;
    public ResultViewModel resultViewModel;
    public TransactionViewModel transactionViewModel;
    public UserViewModel userViewModel;
    public PointViewModel pointViewModel;
    public SalesViewModel salesViewModel;

    public Button btnNegativeCostumDialog;
    public Button btnPositiveCostumDialog;
    public TextView txvTitleCostumDialog;
    public TextView txvMessageCostumDialog;
    public ImageView imvCostumDialog;

    TelephonyManager mTelephonyManager;
    MyPhoneStateListener mPhoneStatelistener;
    public int mSignalStrength = 0;

    protected MoSurveiApplication mMyApp;

    protected abstract int layout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMyApp = (MoSurveiApplication) this.getApplicationContext();
        setContentView(layout());
        ButterKnife.bind(this);

        MoGaweFirebaseMessagingService.getCurrentDeviceToken();

        Icepick.restoreInstanceState(this, savedInstanceState);
        this.bus = new RxBus();
        this.progressDialog = new ProgressDialog(this);
        this.builder = new AlertDialog.Builder(this);
        this.btnNegativeDialog = new Button(this);
        this.btnPositiveDialog = new Button(this);
        this.cbCheckBoxDialog = new CheckBox(this);
        this.txvCheckboxDialog = new CheckBox(this);

        this.btnNegativeCostumDialog = new Button(this);
        this.btnPositiveCostumDialog = new Button(this);
        this.txvTitleCostumDialog = new TextView(this);
        this.txvMessageCostumDialog = new TextView(this);
        this.imvCostumDialog = new ImageView(this);

        this.dialog = builder.create();
        this.vg = new LinearLayout(this);
        this.dialog.setView(vg);
        this.service = new Service();

        this.costumDialog = builder.create();

        this.resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        this.taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        this.transactionViewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);
        this.userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        this.pointViewModel = ViewModelProviders.of(this).get(PointViewModel.class);
        this.salesViewModel = ViewModelProviders.of(this).get(SalesViewModel.class);

        setMiuiStatusBarDarkMode(false);

        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.l_dialog_box, null, false);
        txvMessageDialog = dialogView.findViewById(R.id.txvMessageDialog);
        pgbWaitingDialog = dialogView.findViewById(R.id.pgbWaiting);
        btnPositiveDialog = dialogView.findViewById(R.id.btnPositive);
        btnNegativeDialog = dialogView.findViewById(R.id.btnNegative);
        cbCheckBoxDialog = dialogView.findViewById(R.id.cbTermsAndConds);
        txvCheckboxDialog = dialogView.findViewById(R.id.txvTermsAndConds);

        costumDialogView = inflater.inflate(R.layout.costum_dialog_mogawe, null, false);
        txvTitleCostumDialog = costumDialogView.findViewById(R.id.titleCostumDialog);
        txvMessageCostumDialog = costumDialogView.findViewById(R.id.messageCostumDialog);
        imvCostumDialog = costumDialogView.findViewById(R.id.imvCostumDialog);
        btnPositiveCostumDialog = costumDialogView.findViewById(R.id.btnPositiveCostumDialog);
        btnNegativeCostumDialog = costumDialogView.findViewById(R.id.btnNegativeCostumDialog);

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));

        if (isNetworkAvailable()) {
            PrefHelper.setBoolean(PrefKey.IS_OFFLINE, false);
        } else {
            PrefHelper.setBoolean(PrefKey.IS_OFFLINE, true);
        }

        mPhoneStatelistener = new MyPhoneStateListener();
        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        mTelephonyManager.listen(mPhoneStatelistener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
    }

    private static String getSignalStrength(Context context) throws SecurityException {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String strength = null;
        List<CellInfo> cellInfos = telephonyManager.getAllCellInfo();   //This will give info of all sims present inside your mobile
        if (cellInfos != null) {
            for (int i = 0; i < cellInfos.size(); i++) {
                if (cellInfos.get(i).isRegistered()) {
                    if (cellInfos.get(i) instanceof CellInfoWcdma) {
                        CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfos.get(i);
                        CellSignalStrengthWcdma cellSignalStrengthWcdma = cellInfoWcdma.getCellSignalStrength();
                        strength = String.valueOf(cellSignalStrengthWcdma.getDbm());
                    } else if (cellInfos.get(i) instanceof CellInfoGsm) {
                        CellInfoGsm cellInfogsm = (CellInfoGsm) cellInfos.get(i);
                        CellSignalStrengthGsm cellSignalStrengthGsm = cellInfogsm.getCellSignalStrength();
                        strength = String.valueOf(cellSignalStrengthGsm.getDbm());
                    } else if (cellInfos.get(i) instanceof CellInfoLte) {
                        CellInfoLte cellInfoLte = (CellInfoLte) cellInfos.get(i);
                        CellSignalStrengthLte cellSignalStrengthLte = cellInfoLte.getCellSignalStrength();
                        strength = String.valueOf(cellSignalStrengthLte.getDbm());
                    } else if (cellInfos.get(i) instanceof CellInfoCdma) {
                        CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfos.get(i);
                        CellSignalStrengthCdma cellSignalStrengthCdma = cellInfoCdma.getCellSignalStrength();
                        strength = String.valueOf(cellSignalStrengthCdma.getDbm());
                    }
                }
            }
        }
        return strength;
    }

    public static void setMargins (View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public String getTimeStampNow() {
        //Date object
        Date date = new Date();
        //getTime() returns current time in milliseconds
        long time = date.getTime();
        //Passed the milliseconds to constructor of Timestamp class
        Timestamp ts = new Timestamp(time);

        return ts.toString();
    }

    public String getAddressFromLatLng(Double latitude, Double longitude) {
        try {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, new Locale("in", "ID"));

            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

            return (address + " " + city + " " + postalCode).replace(",", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public Map<String, String> getAdministrationFromLatLng(Double latitude, Double longitude) {
        try {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, new Locale("in", "ID"));

            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            Map<String, String> data = new HashMap<>();
            String address;
            String kelurahan;
            String kecamatan;
            String kota;
            String provinsi;
            String latlong;

            try {
                address = addresses.get(0).getAddressLine(0).split(",")[0];
            } catch (Exception e) {
                address = "";
            }
            try {
                kelurahan = addresses.get(0).getSubLocality();
            } catch (Exception e) {
                kelurahan = "";
            }
            try {
                kecamatan = addresses.get(0).getLocality();
            } catch (Exception e) {
                kecamatan = "";
            }
            try {
                kota = addresses.get(0).getSubAdminArea();
            } catch (Exception e) {
                kota = "";
            }
            try {
                provinsi = addresses.get(0).getAdminArea();
            } catch (Exception e) {
                provinsi = "";
            }
            try {
                latlong = addresses.get(0).getLatitude() + "," + addresses.get(0).getLongitude();
            } catch (Exception e) {
                latlong = "";
            }

            data.put("alamat", address.replace(", ", ",").replace(". ", ".").replaceAll("[^A-Za-z0-9]", " "));
            data.put("kelurahan", kelurahan.replace(", ", ",").replace(". ", ".").replaceAll("[^A-Za-z0-9]", " "));
            data.put("kecamatan", kecamatan.replace(", ", ",").replace(". ", ".").replaceAll("[^A-Za-z0-9]", " "));
            data.put("kota", kota.replace(", ", ",").replace(". ", ".").replaceAll("[^A-Za-z0-9]", " "));
            data.put("provinsi", provinsi.replace(", ", ",").replace(". ", ".").replaceAll("[^A-Za-z0-9]", " "));
            data.put("latlong", latlong.replace(",", "\\,"));


            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Gets the percentate of battery
     *
     * @param context
     * @return true if enabled.
     */
    public static int getBatteryPercentage(Context context) {

        if (Build.VERSION.SDK_INT >= 21) {

            BatteryManager bm = (BatteryManager) context.getSystemService(BATTERY_SERVICE);
            return bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

        } else {

            IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent batteryStatus = context.registerReceiver(null, iFilter);

            int level = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) : -1;
            int scale = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1) : -1;

            double batteryPct = level / (double) scale;

            return (int) (batteryPct * 100);
        }
    }

    /**
     * Gets the state of Airplane Mode.
     *
     * @param context
     * @return true if enabled.
     */
    public boolean isAirplaneModeOn(Context context) {

        return Settings.System.getInt(context.getContentResolver(),
                Settings.Global.AIRPLANE_MODE_ON, 0) != 0;

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (bus != null) {
            subscriptions = new CompositeSubscription();
            subscriptions
                    .add(bus.toObserverable()
                            .subscribe(new Action1<Object>() {
                                @Override
                                public void call(Object event) {
                                    if (event instanceof RxBusObject) {
                                        RxBusObject busObject = (RxBusObject) event;
                                        busHandler(busObject.getKey(), busObject.getObject());
                                    }
                                }
                            })
                    );
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        PrefHelper.setBoolean(PrefKey.IS_PAGE_ACTIVE, false);
        clearReferences();
    }

    @Override
    protected void onResume() {
        super.onResume();
        PrefHelper.setBoolean(PrefKey.IS_PAGE_ACTIVE, true);
        mMyApp.setCurrentActivity(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        subscriptions.unsubscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        progressDialog.dismiss();
        dismissCostumDialog();
        clearReferences();

        freeMemory();
    }

    public void freeMemory() {
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
    }

    private void clearReferences(){
        Activity currActivity = mMyApp.getCurrentActivity();
        if (this.equals(currActivity))
            mMyApp.setCurrentActivity(null);
    }

    public RxBus getBus() {
        return bus;
    }

    public void busHandler(RxBusObject.RxBusKey busKey, Object busObject) {

    }

    public boolean isFragmentNotNull(String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) != null) {
            return true;
        } else {
            return false;
        }
    }

    public int getStatusBarHeight() {
        final Resources resources = this.getResources();
        final int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
            if (resources.getDimensionPixelSize(resourceId) == 135) {
                return resources.getDimensionPixelSize(resourceId) + 36;
            } else
                return resources.getDimensionPixelSize(resourceId);
        else
            return (int) Math.ceil((Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? 24 : 25) * resources.getDisplayMetrics().density);
    }

    public int getViewHight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        return height + getNavigationBarHeight();
    }

    public int getViewWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        return width;
    }

    private int getNavigationBarHeight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int usableHeight = metrics.heightPixels;
            getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
            int realHeight = metrics.heightPixels;
            if (realHeight > usableHeight)
                return realHeight - usableHeight;
            else
                return 0;
        }
        return 0;
    }

    public void transitionBackground(View view, int colorFrom, int colorTo, int duration) {

        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(duration); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                view.setBackgroundColor((int) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
    }

    public boolean isFragmentVisible(String tag) {
        if (isFragmentNotNull(tag)
                && getSupportFragmentManager().findFragmentByTag(tag).isVisible()) {
            return true;
        } else {
            return false;
        }
    }

    public Fragment getVisibleFragment(String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    public void showProgressDialog(String message) {
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    public void showCostumDialog(String type) {
        costumDialog.setView(costumDialogView);
        try {
            costumDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        costumDialog.setContentView(R.layout.costum_dialog_mogawe);

        switch (type) {
            case "Oke":
                btnPositiveCostumDialog.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                imvCostumDialog.setVisibility(View.GONE);
                btnNegativeCostumDialog.setVisibility(View.GONE);
                break;
            case "Cancel":
                btnNegativeCostumDialog.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                imvCostumDialog.setVisibility(View.GONE);
                btnPositiveCostumDialog.setVisibility(View.GONE);
                break;
            case "Oke&Cancel":
                btnPositiveCostumDialog.setVisibility(View.VISIBLE);
                imvCostumDialog.setVisibility(View.GONE);
                btnNegativeCostumDialog.setVisibility(View.VISIBLE);
                break;
            case "NoButton":
                btnPositiveCostumDialog.setVisibility(View.GONE);
                imvCostumDialog.setVisibility(View.GONE);
                btnNegativeCostumDialog.setVisibility(View.GONE);
                break;
            case "Oke&Image":
                btnPositiveCostumDialog.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                imvCostumDialog.setVisibility(View.VISIBLE);
                btnNegativeCostumDialog.setVisibility(View.GONE);
                break;
            case "Cancel&Image":
                btnNegativeCostumDialog.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                imvCostumDialog.setVisibility(View.VISIBLE);
                btnPositiveCostumDialog.setVisibility(View.GONE);
                break;
            case "Oke&Cancel&Image":
                btnPositiveCostumDialog.setVisibility(View.VISIBLE);
                imvCostumDialog.setVisibility(View.VISIBLE);
                btnNegativeCostumDialog.setVisibility(View.VISIBLE);
                break;
            case "NoButton&Image":
                btnPositiveCostumDialog.setVisibility(View.GONE);
                imvCostumDialog.setVisibility(View.VISIBLE);
                btnNegativeCostumDialog.setVisibility(View.GONE);
                break;
        }

        if (txvTitleCostumDialog.getText().toString().equals("") || txvTitleCostumDialog.getText() == null || txvTitleCostumDialog.getText().toString().contains("Title Title")) {
            txvTitleCostumDialog.setVisibility(View.GONE);
        } else {
            txvTitleCostumDialog.setVisibility(View.VISIBLE);
        }

        if (txvMessageCostumDialog.getText().toString().equals("") || txvMessageCostumDialog.getText() == null || txvMessageCostumDialog.getText().toString().contains("Lorem Ipsum is simply dummy text of the printing and typesetting industry.")) {
            txvMessageCostumDialog.setVisibility(View.GONE);
        } else {
            txvMessageCostumDialog.setVisibility(View.VISIBLE);
        }

        try {
            costumDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        View costumDialog = getLayoutInflater().inflate(R.layout.costum_dialog_mogawe, null, true);
//
//        sourceActivity.addContentView(costumDialog, new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT
//        ));

    }

    public void dismissCostumDialog() {
        if (costumDialog != null && costumDialog.isShowing()) {
            costumDialog.dismiss();
        }
    }

    public static long getAvailableSpaceInMB() {
        final long SIZE_KB = 1024L;
        final long SIZE_MB = SIZE_KB * SIZE_KB;
        long availableSpace = -1L;
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        availableSpace = (long) stat.getAvailableBlocks() * (long) stat.getBlockSize();
        return availableSpace / SIZE_MB;
    }

    public void setProgressDialogMessage(String message) {
        progressDialog.setMessage(message);
    }

    public void showProgressHorizontalDialogMessage(String title, String message, Integer setProgressCount) {

        progressDialog.setMax(setProgressCount);
        progressDialog.setMessage(message);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.setTitle(title);
        progressDialog.setProgress(0);
        progressDialog.setProgressNumberFormat(null);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    while (progressDialog.getProgress() <= progressDialog
//                            .getMax()) {
//                        Thread.sleep(200);
//                        handleHorizontalProgressBar.sendMessage(handleHorizontalProgressBar.obtainMessage());
////                        if (progressDialog.getProgress() == progressDialog
////                                .getMax()) {
////                            progressDialog.dismiss();
////                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }

    public void setProgressHorizontalDialogMessage(Integer setProgress) {
        progressDialog.incrementProgressBy(setProgress);
//        handleHorizontalProgressBar = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                progressDialog.incrementProgressBy(setProgress);
//            }
//        };
//        handleHorizontalProgressBar.sendMessage(handleHorizontalProgressBar.obtainMessage());
    }

    public void setFixProgressHorizontalDialogMessage(Integer setProgress) {
        progressDialog.setProgress(setProgress);
//        handleHorizontalProgressBar = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                progressDialog.incrementProgressBy(setProgress);
//            }
//        };
//        handleHorizontalProgressBar.sendMessage(handleHorizontalProgressBar.obtainMessage());
    }

//    Handler handle = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            progressDoalog.incrementProgressBy(1);
//        }
//    };

    public void setProgressMessage(String message) {
        progressDialog.setMessage(message);
    }

    public void dismissProgressDialog() {
        progressDialog.hide();
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

//    public Service.Api getApi() {
//        return getService().getApi();
//    }

    public Service.ApiUserService getApiUserService() {
        return getService().getApiUserService();
    }

    public Service.ApiTaskService getApiTaskService() {
        return getService().getApiTaskService();
    }

    public Service.ApiProjectService getApiProjectService() {
        return getService().getApiProjectService();
    }

    public Service.ApiSectionService getApiSectionService() {
        return getService().getApiSectionService();
    }

    public String minutesToHourMinutes(Integer minutes) {
        Integer hourperminutes = 60;

        if (minutes < 60) {
            return minutes.toString() + " Menit";
        } else {
            Integer hour = 0;
            String desc;
            while (minutes >= 60) {
                minutes -= hourperminutes;
                hour++;
            }
            if (minutes > 0) {
                desc = hour.toString() + " Jam " + minutes.toString() + " Menit";
            } else {
                desc = hour.toString() + " Jam ";
            }

            return desc;
        }
    }

    public void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    class MyPhoneStateListener extends PhoneStateListener {

        @Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            mSignalStrength = signalStrength.getGsmSignalStrength();
            mSignalStrength = (2 * mSignalStrength) - 113; // -> dBm
        }
    }

    private boolean checkGooglePlayServicesAvailable() {
        final int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if (status == ConnectionResult.SUCCESS) {
            return true;
        }
        return false;
    }

    private IntentFilter getNetworkChangeIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        return intentFilter;
    }

    private void getKeyHash(Context context) {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.mogawe.mosurvei", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("Obtained KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    public boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        //should check null because in air plan mode it will be null
        return (netInfo != null && netInfo.isConnected());
    }

    public boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public boolean isPhoneNumberValid(String phone) {
        boolean isValid = false;

        String expression = "^08[0-9]{8,}$";
        CharSequence inputStr = phone;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public void showNeutralCostumDialog(String title, String content, final Boolean isFinishActivity) {
        try {
            costumDialog.setView(costumDialogView);
            costumDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            txvTitleCostumDialog.setText(title);
            txvMessageCostumDialog.setText(content);
            btnPositiveCostumDialog.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            btnNegativeCostumDialog.setVisibility(View.GONE);
            btnPositiveCostumDialog.setText(R.string.action_alright);
            btnPositiveCostumDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    costumDialog.dismiss();
                    if (isFinishActivity)
                        finish();
                }
            });
            costumDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showNeutralDialog(String title, String content, final Boolean isFinishActivity) {
        try {
            new MaterialDialog.Builder(this)
                    .backgroundColor(Color.WHITE)
                    .title(title)
                    .titleColor(colorPrimary)
                    .content(content)
                    .contentColor(colorGrey)
                    .positiveText(R.string.action_alright)
                    .positiveColor(colorPrimary)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                            if (isFinishActivity)
                                finish();
                        }
                    })
                    .cancelable(false)
                    .canceledOnTouchOutside(false)
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initMoDialog() {
        dialog = builder.create();
        dialogView = inflater.inflate(R.layout.l_dialog_box, null);
        dialog.setView(dialogView);
        btnPositiveDialog = dialogView.findViewById(R.id.btnPositive);
        btnNegativeDialog = dialogView.findViewById(R.id.btnNegative);
    }

    public void initMoDialog(View view) {
        dialog = builder.create();
        dialogView = view;
        dialog.setView(dialogView);
        btnPositiveDialog = dialogView.findViewById(R.id.btnPositive);
        btnNegativeDialog = dialogView.findViewById(R.id.btnNegative);
    }

    public void updateMoDialog() {

    }

    public void showMoDialog(String title, String message, Boolean isProgress) {
        dialog = builder.create();

        txvMessageDialog = dialogView.findViewById(R.id.txvMessageDialog);
        pgbWaitingDialog = dialogView.findViewById(R.id.pgbWaiting);
        cbCheckBoxDialog = dialogView.findViewById(R.id.cbTermsAndConds);
        txvCheckboxDialog = dialogView.findViewById(R.id.txvTermsAndConds);
        txvMessageDialog.setText(message);
        pgbWaitingDialog.setVisibility(isProgress == true ? View.VISIBLE : View.GONE);
        txvMessageDialog.setVisibility(!message.equals("") ? View.VISIBLE : View.GONE);

        cbCheckBoxDialog.setVisibility(View.GONE);
        txvCheckboxDialog.setVisibility(View.GONE);

        dialog.setView(dialogView);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.setTitle(title != null ? title : null);
        dialog.show();
    }

    public void showTermsAndAgreeDialog(String title, String message, Boolean isProgress) {
        dialog = builder.create();

        txvMessageDialog = dialogView.findViewById(R.id.txvMessageDialog);
        pgbWaitingDialog = dialogView.findViewById(R.id.pgbWaiting);
        txvCheckboxDialog = dialogView.findViewById(R.id.txvTermsAndConds);
        cbCheckBoxDialog = dialogView.findViewById(R.id.cbTermsAndConds);

        txvMessageDialog.setText(message);
        pgbWaitingDialog.setVisibility(isProgress == true ? View.VISIBLE : View.GONE);

        cbCheckBoxDialog.setVisibility(View.VISIBLE);
        txvCheckboxDialog.setVisibility(View.VISIBLE);
        txvMessageDialog.setVisibility(!message.equals("") ? View.VISIBLE : View.GONE);

        txvCheckboxDialog.setText(Html.fromHtml(getString(R.string.agree_terms_and_conds)));

        if (!cbCheckBoxDialog.isChecked()) {
            btnPositiveDialog.setEnabled(false);
            btnPositiveDialog.setTextColor(getResources().getColor(R.color.grey_500));
        }
        cbCheckBoxDialog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    btnPositiveDialog.setEnabled(true);
                    btnPositiveDialog.setTextColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    btnPositiveDialog.setEnabled(false);
                    btnPositiveDialog.setTextColor(getResources().getColor(R.color.grey_500));
                }
            }
        });

        txvCheckboxDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebviewActivity.startActivityForResult(BaseActivity.this, getString(R.string.terms_and_conds), Service.AGREEMENT_URL, REQUEST_TERMS_AND_CONDS);
            }
        });

        dialog.setView(dialogView);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setTitle(title != null ? title : null);
        dialog.show();
    }

    public void dismissMoDialog() {
        dialog.dismiss();
    }

    public void setMoDialogAsAlert(String title, String message) {
        txvMessageDialog.setText(message);
        pgbWaitingDialog.setVisibility(View.GONE);
        btnPositiveDialog.setVisibility(View.VISIBLE);
        btnNegativeDialog.setVisibility(View.VISIBLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
    }

    public void setMoDialogAsProgress(String title, String message) {
        txvMessageDialog.setText(message);
        pgbWaitingDialog.setVisibility(View.VISIBLE);
        btnPositiveDialog.setVisibility(View.GONE);
        btnNegativeDialog.setVisibility(View.GONE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

    }

    public void setMoDialog(String title, String message, Boolean isProgress) {
        txvMessageDialog = dialogView.findViewById(R.id.txvMessageDialog);
        pgbWaitingDialog = dialogView.findViewById(R.id.pgbWaiting);
        txvMessageDialog.setText(message);
        pgbWaitingDialog.setVisibility(isProgress == true ? View.VISIBLE : View.GONE);
    }

    public void logOut() {

    }

    public String formatPrice(Double price) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) numberFormat).getDecimalFormatSymbols();
        decimalFormatSymbols.setCurrencySymbol("");
        ((DecimalFormat) numberFormat).setDecimalFormatSymbols(decimalFormatSymbols);

        return numberFormat.format(price).trim();
    }

    public void onServiceStart() {
        progressDialog.setMessage(pleaseWait);
        progressDialog.show();
    }

    public void onServiceStop() {
        dismissProgressDialog();
    }

    public void onServiceSuccess() {

    }

    public void onServiceFailed() {

    }

    public void onServiceError() {
        dismissProgressDialog();
        showNeutralCostumDialog(requestFailed, errorInternet.toLowerCase(), false);
    }

    public boolean setMiuiStatusBarDarkMode(boolean darkmode) {
        Class<? extends Window> clazz = getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }


    public Boolean isLastActivity() {
        ActivityManager mngr = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

        List<ActivityManager.RunningTaskInfo> taskList = mngr.getRunningTasks(10);

        if (taskList.get(0).numActivities == 1 &&
                taskList.get(0).topActivity.getClassName().equals(this.getClass().getName())) {
            Log.i(TAG, "This is last activity in the stack");
            return true;
        }
        return false;
    }

    public boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i("isMyServiceRunning?", true + "");
                return true;
            }
        }
        Log.i("isMyServiceRunning?", false + "");
        return false;
    }
}

//Example material dialog
//                        new MaterialDialog.Builder(getActivity())
//                                .backgroundColor(Color.WHITE)
//                                .title("Perhatian")
//                                .titleColor(getActivity().getResources().getColor(R.color.red_600))
//                                .content(content)
//                                .contentColor(ResourcesCompat.getColor(getActivity().getResources(), R.color.grey_600, null))
//                                .positiveText("Oke")
//                                .positiveColor(getActivity().getResources().getColor(R.color.red_600))
//                                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                    @Override
//                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                        ((HomeActivity) getActivity()).applyGawean(job);
//                                    }
//                                })
//                                .negativeText("Nanti saja")
//                                .negativeColor(ResourcesCompat.getColor(getActivity().getResources(), R.color.grey_600, null))
//                                .onNegative(new MaterialDialog.SingleButtonCallback() {
//                                    @Override
//                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                        PrefHelper.setBoolean(PrefKey.IS_TRY_GAWEAN,true);
//                                    }
//                                })
//                                .show();

//                            new MaterialDialog.Builder(getActivity())
//                                    .backgroundColor(Color.WHITE)
//                                    .title("Perhatian")
//                                    .titleColor(getActivity().getResources().getColor(R.color.red_600))
//                                    .content("Permintaan job ini sedang dalam proses. Mohon tunggu 1x24 jam")
//                                    .contentColor(ResourcesCompat.getColor(getActivity().getResources(), R.color.grey_600, null))
//                                    .positiveText("Oke")
//                                    .positiveColor(getActivity().getResources().getColor(R.color.red_600))
//                                    .show();

//                                new MaterialDialog.Builder(getActivity())
//                                        .backgroundColor(Color.WHITE)
//                                        .content("Kerjakan Gawean ini?")
//                                        .contentColor(ResourcesCompat.getColor(getActivity().getResources(), R.color.grey_600, null))
//                                        .positiveText("Oke")
//                                        .positiveColor(getActivity().getResources().getColor(R.color.red_600))
//                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                            @Override
//                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                                showProgressDialog("Memulai Gawean...");
//                                                taskViewModel.startGawean(job);
//                                            }
//                                        })
//                                        .negativeText("Nanti saja")
//                                        .negativeColor(getActivity().getResources().getColor(R.color.grey_600))
//                                        .onNegative(new MaterialDialog.SingleButtonCallback() {
//                                            @Override
//                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                                PrefHelper.setBoolean(PrefKey.IS_TRY_GAWEAN,true);
//                                            }
//                                        })
//                                        .show();
