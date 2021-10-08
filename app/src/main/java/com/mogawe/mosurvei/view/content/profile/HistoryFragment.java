package com.mogawe.mosurvei.view.content.profile;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.view.BaseActivity;
import com.mogawe.mosurvei.view.BaseFragment;

public class HistoryFragment extends BaseFragment {
    public static final String TAG = HistoryFragment.class.getSimpleName();
    private BaseActivity baseActivity;

    public HistoryFragment(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    protected int layout() {
        return R.layout.a_profile_history_fragment;
    }

    public static void showFragment(BaseActivity baseActivity) {
        FragmentTransaction fragmentTransaction = baseActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_fragment, new HistoryFragment(baseActivity), TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // set the date range dropdown filter
        setDateRangeFilterDropdown(view);
        // set history list view
        setHistoryRecyclerView(view);
    }

    private void setDateRangeFilterDropdown(View view) {
        Spinner spinner = view.findViewById(R.id.date_range);
        String[] ranges = {"last 7 days", "last month", "last year"};
        ArrayAdapter<CharSequence> langAdapter = new ArrayAdapter<>(getActivity(), R.layout.a_spinner_text, ranges );
        langAdapter.setDropDownViewResource(R.layout.a_simple_spinner_dropdown);
        spinner.setAdapter(langAdapter);
    }

    private void setHistoryRecyclerView(View view) {
//        ListView listView = view.findViewById(R.id.history_list);
//        HistoryListViewAdapter historyListViewAdapter = new HistoryListViewAdapter(getContext());
//        listView.setAdapter(historyListViewAdapter);

        RecyclerView recyclerView = view.findViewById(R.id.history_list);
        HistoryRecyclerViewAdapter historyRecyclerViewAdapter = new HistoryRecyclerViewAdapter(baseActivity);
        recyclerView.setAdapter(historyRecyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
