package com.mogawe.mosurvei.view.content.profile;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.model.network.response.HistoryResponse;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.ViewHolder> {
    private HashMap<String, Integer> statusColorMap = new HashMap<>();
    private ArrayList<HistoryResponse> historyResponses;
    private int itemCount;
    private Context context;

    public HistoryRecyclerViewAdapter(Context context) {
        this.context = context;

        // get mock data
        historyResponses = new HistoryMockData().getMockData();
        itemCount = historyResponses.size();

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
        HistoryResponse historyResponse = historyResponses.get(position);
        if (historyResponse.isStartNewDate()) {

            // layout params
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.date.getLayoutParams();
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            layoutParams.setMargins(layoutParams.getMarginStart(), layoutParams.getMarginStart(), 0, layoutParams.getMarginStart());
            holder.date.setLayoutParams(layoutParams);

            // set text
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            try {
                date = dateFormat.parse(historyResponse.getTimestamp());
                dateFormat = new SimpleDateFormat("dd MMMM yyyy", new Locale("in", "ID"));
                holder.date.setText(dateFormat.format(date));
            } catch (ParseException e) {
                Toast.makeText(context, "Invalid date format", Toast.LENGTH_LONG).show();
            }

        }

        holder.description.setText(historyResponse.getDescription());

        holder.status.setText(historyResponse.getStatus());
        holder.status.setBackgroundTintList(context.getColorStateList(statusColorMap.get(historyResponse.getStatus())));

        holder.category.setText(historyResponse.getCategory());

        String amountString = formatNumberToRupiah(historyResponse.getAmount());
        if (historyResponse.getStatus().equals("Approved")) {
            amountString = "+"+amountString;
        } else if (historyResponse.getStatus().equals("Completed")) {
            holder.amount.setTypeface(holder.amount.getTypeface(), Typeface.BOLD_ITALIC);
            holder.description.setTypeface(holder.amount.getTypeface(), Typeface.BOLD_ITALIC);
            holder.description.setTextColor(context.getColorStateList(R.color.grey_600));
        }

        holder.amount.setText(amountString);
        holder.amount.setTextColor(context.getColor(statusColorMap.get(historyResponse.getStatus())));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "description: "+historyResponse.getDescription()+"; status: "+historyResponse.getStatus()+"; amount: "+historyResponse.getAmount().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView date;
        private TextView description;
        private TextView status;
        private TextView category;
        private TextView amount;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            System.out.println("ViewHolder called");
            date = itemView.findViewById(R.id.date);
            description = itemView.findViewById(R.id.description);
            status = itemView.findViewById(R.id.status);
            category = itemView.findViewById(R.id.category);
            amount = itemView.findViewById(R.id.amount);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }

    // for rounding double to n decimal place
    private double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    // format number to rupiah
    private String formatNumberToRupiah(Double number) {
        Locale indonesianLocale = new Locale("in", "ID");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(indonesianLocale);
        return numberFormat.format(number).replace("Rp", "Rp. ").replace(",00", "");
    }
}
