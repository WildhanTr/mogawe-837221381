package com.mogawe.mosurvei.view.content.profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mogawe.mosurvei.R;

import java.util.HashMap;

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.ViewHolder> {
    private HashMap<String, Integer> statusColorMap = new HashMap<>();


    public HistoryRecyclerViewAdapter() {
        // status color
        statusColorMap.put("Approved", R.color.green_400);
        statusColorMap.put("Completed", R.color.yellow_700);
        statusColorMap.put("Transfer Out", R.color.red_600);
        statusColorMap.put("Canceled", R.color.grey_700);
        statusColorMap.put("Revoked", R.color.black);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("HistoryRecyclerViewAdapter.onCreateViewHolder: viewType: " + Integer.valueOf(viewType).toString());
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.a_profile_history_list_view_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            System.out.println("ViewHolder called");
        }
    }
}
