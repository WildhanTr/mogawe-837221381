package com.mogawe.mosurvei.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.model.network.Service;
import com.mogawe.mosurvei.util.RxBus;
import com.mogawe.mosurvei.util.RxBusObject;
import com.mogawe.mosurvei.view.BaseActivity;
import com.mogawe.mosurvei.viewmodel.PointViewModel;
import com.mogawe.mosurvei.viewmodel.ResultViewModel;
import com.mogawe.mosurvei.viewmodel.SalesViewModel;
import com.mogawe.mosurvei.viewmodel.TaskViewModel;
import com.mogawe.mosurvei.viewmodel.TransactionViewModel;
import com.mogawe.mosurvei.viewmodel.UserViewModel;

import butterknife.BindColor;
import butterknife.BindString;
import butterknife.ButterKnife;
import icepick.Icepick;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseFragment extends Fragment {

    public static final String JOB_OBSERVATION = "e6d88855-76e3-4139-98aa-b98c1a1fd010";
    public static final String JOB_DISPLAY_SCAN = "fb348370-9500-4ff8-9e6b-ec89705e9c24";
    public static final String JOB_STORE_COUNT = "03e596f9-62c2-489d-8a37-1e8777313a6b";
    public static final String JOB_INTERVIEW = "7b9acf94-516c-434a-bc64-f2dee73ef316";
    public static final String JOB_CENSUS = "7296713a-ab63-4c43-b6f0-b58b9d4fd577";
    public static final String JOB_DATA_CRAWLING = "a1033f50-71c2-11eb-9439-0242ac130002";
    public static final String JOB_SALES = "6bffeba9-5997-4281-be0c-849b099f7266";

    @BindString(R.string.please_wait)
    public String pleaseWait;
    @BindString(R.string.request_failed)
    public String requestFailed;
    @BindString(R.string.error_no_internet)
    public String errorInternet;
    @BindColor(R.color.colorPrimary)
    public int colorPrimary;

    protected RxBus bus;
    private CompositeSubscription subscriptions;

    public AlertDialog dialog, costumDialog;
    public AlertDialog.Builder builder;
    public View dialogView, costumDialogView;

    public TextView txvMessageDialog;
    public ProgressBar pgbWaitingDialog;
    public Button btnPositiveDialog;
    public Button btnNegativeDialog;

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

    int mSignalStrength = 0;

    public LayoutInflater inflater;

    protected FragmentActivity mActivity;

    protected abstract int layout();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
        Icepick.restoreInstanceState(this, savedInstanceState);

        this.resultViewModel = ((BaseActivity) mActivity).resultViewModel;
        this.taskViewModel = ((BaseActivity) mActivity).taskViewModel;
        this.transactionViewModel = ((BaseActivity) mActivity).transactionViewModel;
        this.userViewModel = ((BaseActivity) mActivity).userViewModel;
        this.pointViewModel = ((BaseActivity) mActivity).pointViewModel;
        this.salesViewModel = ((BaseActivity) mActivity).salesViewModel;

        //MO-DIALOG
        builder = ((BaseActivity) mActivity).builder;
        dialog = ((BaseActivity) mActivity).dialog;
        costumDialog = ((BaseActivity) mActivity).costumDialog;
        dialogView = ((BaseActivity) mActivity).dialogView;
        costumDialogView = ((BaseActivity) mActivity).costumDialogView;
        txvMessageDialog = ((BaseActivity) mActivity).txvMessageDialog;
        pgbWaitingDialog = ((BaseActivity) mActivity).pgbWaitingDialog;
        btnNegativeDialog = ((BaseActivity) mActivity).btnNegativeDialog;
        btnPositiveDialog = ((BaseActivity) mActivity).btnPositiveDialog;
        mSignalStrength = ((BaseActivity) mActivity).mSignalStrength;

        txvTitleCostumDialog = ((BaseActivity) mActivity).txvTitleCostumDialog;
        txvMessageCostumDialog = ((BaseActivity) mActivity).txvMessageCostumDialog;
        btnPositiveCostumDialog = ((BaseActivity) mActivity).btnPositiveCostumDialog;
        btnNegativeCostumDialog = ((BaseActivity) mActivity).btnNegativeCostumDialog;

        inflater = getLayoutInflater();

        if(mActivity != null)
            this.bus = ((BaseActivity) mActivity).getBus();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FragmentActivity) {
            mActivity = (FragmentActivity) context;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.bus = ((BaseActivity) mActivity).getBus();
    }

    public boolean isNetworkAvailable() {

        System.out.println("activeNetworkFragment.. " + ((BaseActivity) mActivity).isNetworkAvailable());

        return ((BaseActivity) mActivity).isNetworkAvailable();
    }

    public boolean isAirplaneModeOn(Context context) {
        return ((BaseActivity) mActivity).isAirplaneModeOn(context);
    }

    public int getStatusBarHeight() {
        return ((BaseActivity) mActivity).getStatusBarHeight();
    }

    @Override
    public void onStart() {
        super.onStart();
        this.subscriptions = new CompositeSubscription();
        this.subscriptions
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

    @Override
    public void onStop() {
        super.onStop();
        this.subscriptions.clear();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(layout(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

//    public Service.Api getApi() {
//        return ((BaseActivity) ((Activity) mActivity)).getService().getApi();
//    }

    public Service.ApiUserService getApiUserService() {
        return ((BaseActivity) ((Activity) mActivity)).getService().getApiUserService();
    }

    public Service.ApiProjectService getApiProjectService() {
        return ((BaseActivity) ((Activity) mActivity)).getService().getApiProjectService();
    }

    public Service.ApiTaskService getApiTaskService() {
        return ((BaseActivity) ((Activity) mActivity)).getService().getApiTaskService();
    }

    public Service.ApiSearchService getApiSearchService() {
        return ((BaseActivity) ((Activity) mActivity)).getService().getApiSearchService();
    }

    public RxBus getBus() {
        return this.bus;
    }

    public void busHandler(RxBusObject.RxBusKey busKey, Object busObject) {

    }

    public void showProgressDialog(String message) {
        if (mActivity != null)
            ((BaseActivity) ((Activity) mActivity)).showProgressDialog(message);
    }

    public void showProgressHorizontalDialogMessage(String title, String message, Integer setProgressCount) {
        if (mActivity != null)
            ((BaseActivity) ((Activity) mActivity)).showProgressHorizontalDialogMessage(title, message, setProgressCount);
    }

    public void setProgressHorizontalDialogMessage(Integer setProgress) {
        if (mActivity != null)
            ((BaseActivity) ((Activity) mActivity)).setProgressHorizontalDialogMessage(setProgress);
    }

    public void setFixProgressHorizontalDialogMessage(Integer setProgress) {
        if (mActivity != null)
            ((BaseActivity) ((Activity) mActivity)).setFixProgressHorizontalDialogMessage(setProgress);
    }

    public String getTimeStampNow() {
        if (mActivity != null)
            return ((BaseActivity) ((Activity) mActivity)).getTimeStampNow();
        return null;
    }

    public void dismissProgressDialog() {
        if (mActivity != null)
            ((BaseActivity) ((Activity) mActivity)).dismissProgressDialog();
    }

    public String getAddressFromLatLng(Double latitude, Double longitude) {
        if (mActivity != null)
            return  ((BaseActivity) ((Activity) mActivity)).getAddressFromLatLng(latitude, longitude);
        return null;
    }


    public void showNeutralCostumDialog(String title, String content, final Boolean isFinishActivity) {
        try {
            costumDialog.setView(costumDialogView);
            costumDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            txvTitleCostumDialog.setText(title);
            txvMessageCostumDialog.setText(content);
            btnPositiveCostumDialog.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            btnNegativeCostumDialog.setVisibility(View.GONE);
            btnPositiveCostumDialog.setText("OK");
            btnPositiveCostumDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    costumDialog.dismiss();
                    if (isFinishActivity)
                        mActivity.finish();
                }
            });
            costumDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showNeutralDialog(String title, String content, final Boolean isFinishActivity) {
        new MaterialDialog.Builder(mActivity)
                .backgroundColor(Color.WHITE)
                .title(title)
                .titleColor(Color.BLACK)
                .content(content)
                .contentColor(getResources().getColor(R.color.grey_700))
                .neutralText("ok")
                .neutralColor(colorPrimary)
                .onNeutral(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        if (isFinishActivity)
                            mActivity.finish();
                    }
                })
                .show();
    }

    public void showFailedDialog(String message) {
        try {
            if (mActivity != null) {
                new MaterialDialog.Builder(mActivity)
                        .iconRes(R.mipmap.ic_launcher)
                        .backgroundColor(Color.WHITE)
                        .title(getString(R.string.request_failed).toUpperCase())
                        .titleColor(colorPrimary)
                        .content(message)
                        .contentColor(((BaseActivity) mActivity).getResources().getColor(R.color.grey_700))
                        .positiveText(R.string.action_ok)
                        .positiveColor(colorPrimary)
                        .cancelable(false)
                        .show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void showSuccessDialog(String message, final Boolean isFinishActivity) {
        if (mActivity != null) {
            new MaterialDialog.Builder(mActivity)
                    .iconRes(R.mipmap.ic_launcher)
                    .backgroundColor(Color.WHITE)
                    .title(getString(R.string.request_success).toUpperCase())
                    .titleColor(colorPrimary)
                    .content(message)
                    .contentColor(getResources().getColor(R.color.grey_700))
                    .positiveText(R.string.action_ok)
                    .positiveColor(colorPrimary)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            if (isFinishActivity) {
                                mActivity.finish();
                            }
                        }
                    })
                    .cancelable(false)
                    .show();
        }
    }

    public void initMoDialog() {
        dialog = builder.create();
        dialogView = inflater.inflate(R.layout.l_dialog_box, null);
        dialog.setView(dialogView);
        btnPositiveDialog = dialogView.findViewById(R.id.btnPositive);
        btnNegativeDialog = dialogView.findViewById(R.id.btnNegative);
    }

    public void showCostumDialog(String type) {
        costumDialog.setView(costumDialogView);
        costumDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        costumDialog.setContentView(R.layout.costum_dialog_mogawe);

        switch (type) {
            case "Oke":
                btnPositiveCostumDialog.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                btnNegativeCostumDialog.setVisibility(View.GONE);
                break;
            case "Cancel":
                btnNegativeCostumDialog.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                btnPositiveCostumDialog.setVisibility(View.GONE);
                break;
            case "Oke&Cancel":
                btnPositiveCostumDialog.setVisibility(View.VISIBLE);
                btnNegativeCostumDialog.setVisibility(View.VISIBLE);
                break;
            case "NoButton":
                btnPositiveCostumDialog.setVisibility(View.GONE);
                btnNegativeCostumDialog.setVisibility(View.GONE);
                break;
        }

        if (txvTitleCostumDialog.getText().toString().equals("")) {
            txvTitleCostumDialog.setVisibility(View.GONE);
        } else {
            txvTitleCostumDialog.setVisibility(View.VISIBLE);
        }

        costumDialog.show();

    }


    public void showMoDialog(String title, String message, Boolean isProgress) {

        ((BaseActivity) mActivity).builder = this.builder;
        ((BaseActivity) mActivity).dialog = this.dialog;
        ((BaseActivity) mActivity).dialogView = this.dialogView;
        ((BaseActivity) mActivity).txvMessageDialog = this.txvMessageDialog;
        ((BaseActivity) mActivity).pgbWaitingDialog = this.pgbWaitingDialog;
        ((BaseActivity) mActivity).btnPositiveDialog = this.btnPositiveDialog;
        ((BaseActivity) mActivity).btnNegativeDialog = this.btnNegativeDialog;
        ((BaseActivity) mActivity).showMoDialog(title, message, isProgress);
    }

    public void dismissMoDialog() {
        ((BaseActivity) mActivity).dialog.dismiss();
    }

    public void setMoDialogAsProgress(String title, String message) {
        ((BaseActivity) mActivity).txvMessageDialog.setText(message);
        ((BaseActivity) mActivity).pgbWaitingDialog.setVisibility(View.VISIBLE);
        ((BaseActivity) mActivity).btnPositiveDialog.setVisibility(View.GONE);
        ((BaseActivity) mActivity).btnNegativeDialog.setVisibility(View.GONE);
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
        View view = mActivity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
