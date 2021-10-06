package com.mogawe.mosurvei.view.content.home;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.model.network.response.DailyTargetResponse;
import com.mogawe.mosurvei.view.BaseActivity;
import com.mogawe.mosurvei.view.BaseFragment;
import com.mogawe.mosurvei.view.shared.map.LocationFragment;

import java.text.NumberFormat;
import java.util.Locale;

public class DailyTargetFragment extends BaseFragment {
    // indicate if daily target has surplus
    private static int STATUS_SURPLUS = 0;
    // indicate if daily target has reach reached
    private static  int STATUS_REACHED = 1;
    // indicate if daily target has not yet reached
    private static int STATUS_NOT_REACHED = 2;
    // daily target indicator, value is (STATUS_SURPLUS, STATUS_REACHED, STATUS_NOT_REACHED)
    private static int STATUS;

    // daily target dividen
    private static Double dailyTargetRemainder;
    // daily target progress (percentage)
    private static Double dailyTargetPercentage;

    public static final String TAG = DailyTargetFragment.class.getSimpleName();

    // mock data to induce STATUS_NOT_REACHED
    private static String mockJsonDataDailyTargetNotReached =
            "{\n" +
            "  \"targetRevenue\": 120000,\n" +
            "  \"todayRevenue\": 100000\n" +
            "}";

    // mock data to induce STATUS_SURPLUS (should be STATUS_REACHED tho...)
    private static  String mockJsonDataDailyTargetSurplus =
            "{\n" +
            "  \"targetRevenue\": 120000,\n" +
            "  \"todayRevenue\": 200000\n" +
            "}";

    @Override
    protected int layout() {
        return R.layout.a_daily_target;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (STATUS == STATUS_SURPLUS) {
            setDailyTargetStatusSurplus(view);
        } else if (STATUS == STATUS_NOT_REACHED) {
            setDailyTargetStatusNotReached(view);
        }
    }

    public static void showFragment(BaseActivity baseActivity) {
//        if (!baseActivity.isFragmentNotNull(TAG)) {
//            FragmentTransaction fragmentTransaction = baseActivity.getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.frame_daily_target_fragment, new DailyTargetFragment(), TAG);
//            fragmentTransaction.commit();
//        }

        FragmentTransaction fragmentTransaction = baseActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_daily_target_fragment, new DailyTargetFragment(), TAG);
        fragmentTransaction.commit();
    }

    public static void showDailyTargetWithSurplus(BaseActivity baseActivity) {
        showFragment(baseActivity);
        // induce STATUS_SURPLUS
        DailyTargetResponse dailyTargetResponse = mockDataDailyTarget(mockJsonDataDailyTargetSurplus);
        calculateDailyTarget(dailyTargetResponse);
    }

    public static void showDailyTargetNotReached(BaseActivity baseActivity) {
        showFragment(baseActivity);
        // induce STATUS_NOT_REACHED
        DailyTargetResponse dailyTargetResponse = mockDataDailyTarget(mockJsonDataDailyTargetNotReached);
        calculateDailyTarget(dailyTargetResponse);
    }

    // set layout for surplus daily target
    private void setDailyTargetStatusSurplus(View view) {
        // hide the competence detail container
        LinearLayout competenceDetailContainer = view.findViewById(R.id.competence_detail_container);
        ViewGroup.LayoutParams layoutParams = competenceDetailContainer.getLayoutParams();
        layoutParams.height = 0;
        competenceDetailContainer.setLayoutParams(layoutParams);

        // set details message to "Selamat! Kamu melebihi target:"
        TextView detailsMessage = view.findViewById(R.id.message);
        detailsMessage.setText("Selamat! Kamu melebihi target:");

        // set progress bar value to 100
        ProgressBar progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setProgress(100);

        // set progress bar text
        TextView progressBarText = view.findViewById(R.id.progress_bar_text);
        if (dailyTargetPercentage == 100) {
            progressBarText.setText("100%");
        } else {
            Double roundedDailyTargetPercentage = Double.valueOf(round(dailyTargetPercentage.doubleValue(), 1));
            progressBarText.setText( roundedDailyTargetPercentage.toString()+"%");
        }

        // set amount and its text color
        TextView amount = view.findViewById(R.id.amount);
        amount.setText("+"+formatNumberToRupiah(dailyTargetRemainder));
        amount.setTextColor(getResources().getColor(R.color.green_600, null));
    }

    // set layout for daily target not reached
    private void setDailyTargetStatusNotReached(View view) {
        // set details message to "Kejar sisa target hari ini:"
        TextView detailsMessage = view.findViewById(R.id.message);
        detailsMessage.setText("Kejar sisa target hari ini:");

        // set progress bar value
        ProgressBar progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setProgress(dailyTargetPercentage.intValue());

        // set progress bar text
        Double roundedDailyTargetPercentage = Double.valueOf(round(dailyTargetPercentage.doubleValue(), 1));
        TextView progressBarText = view.findViewById(R.id.progress_bar_text);
        progressBarText.setText( roundedDailyTargetPercentage.toString()+"%");

        // set amount and its text color
        TextView amount = view.findViewById(R.id.amount);
        amount.setText(formatNumberToRupiah(dailyTargetRemainder));
        amount.setTextColor(getResources().getColor(R.color.red_700, null));
    }

    // mocking the json response
    private static DailyTargetResponse mockDataDailyTarget(String jsonString) {
        return new Gson().fromJson(jsonString, DailyTargetResponse.class);
    }

    // calculate target remainder and completion percentage, and also will set STATUS
    // depending on the result
    private static void calculateDailyTarget(DailyTargetResponse dailyTargetResponse) {
        Double targetRevenue = dailyTargetResponse.getTargetRevenue();
        Double todayRevenue = dailyTargetResponse.getTodayRevenue();
        dailyTargetRemainder = targetRevenue - todayRevenue;
        dailyTargetPercentage = todayRevenue / targetRevenue * 100;

        if (dailyTargetPercentage < 100 || dailyTargetRemainder > 0) {
            STATUS = STATUS_NOT_REACHED;
        } else {
            STATUS = STATUS_SURPLUS;
        }
    }

    // for rounding double to n decimal place
    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    // format number to rupiah
    private String formatNumberToRupiah(Double number) {
        Locale indonesianLocale = new Locale("in", "ID");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(indonesianLocale);
        return numberFormat.format(number).replace("Rp", "Rp. ").replace("-", "");
    }
}
