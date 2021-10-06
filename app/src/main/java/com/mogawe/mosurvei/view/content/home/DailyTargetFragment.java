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

import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.view.BaseActivity;
import com.mogawe.mosurvei.view.BaseFragment;
import com.mogawe.mosurvei.view.shared.map.LocationFragment;

public class DailyTargetFragment extends BaseFragment {
    // indicate if daily target has surplus
    private static int STATUS_SURPLUS = 0;
    // indicate if daily target has reach reached
    private static  int STATUS_REACHED = 1;
    // indicate if daily target has not yet reached
    private static int STATUS_NOT_REACHED = 2;
    // daily target indicator, value is (STATUS_SURPLUS, STATUS_REACHED, STATUS_NOT_REACHED)
    private static int STATUS;

    public static final String TAG = DailyTargetFragment.class.getSimpleName();

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

        STATUS = STATUS_SURPLUS;
    }

    public static void showDailyTargetNotReached(BaseActivity baseActivity) {
        showFragment(baseActivity);

        STATUS = STATUS_NOT_REACHED;
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
        progressBarText.setText("125%");

        // set amount and its text color
        TextView amount = view.findViewById(R.id.amount);
        amount.setText("+Rp. 50.000");
        amount.setTextColor(getResources().getColor(R.color.green_600, null));
    }

    // set layout for daily target not reached
    private void setDailyTargetStatusNotReached(View view) {
        // set details message to "Kejar sisa target hari ini:"
        TextView detailsMessage = view.findViewById(R.id.message);
        detailsMessage.setText("Kejar sisa target hari ini:");

        // set progress bar value to 20
        ProgressBar progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setProgress(20);

        // set progress bar text
        TextView progressBarText = view.findViewById(R.id.progress_bar_text);
        progressBarText.setText("20%");

        // set amount and its text color
        TextView amount = view.findViewById(R.id.amount);
        amount.setText("Rp. 50.000");
        amount.setTextColor(getResources().getColor(R.color.red_700, null));
    }
}
