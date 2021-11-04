package com.mogawe.mosurvei.playground;

import android.location.Address;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.model.bean.gawean.GaweanListResponse;
import com.mogawe.mosurvei.model.bean.gawean.GaweanListResponseItem;

import java.util.ArrayList;

public class PaketGaweanAdapter extends RecyclerView.Adapter<PaketGaweanAdapter.MyViewHolder> {

    private ArrayList<GaweanListResponseItem> mDataset;
    private OnSelectedListener onSelectedListener;

    public PaketGaweanAdapter(ArrayList<GaweanListResponseItem> mDataset) {
        this.mDataset = mDataset;
    }

    public void updateData(ArrayList<GaweanListResponseItem> mDataset){
        this.mDataset = mDataset;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.paket_gawean_item_layout, parent, false);
        return new PaketGaweanAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtJudulGawean.setText(mDataset.get(position).getName());
        holder.txtDeskripsi.setText(mDataset.get(position).getDescription());
        holder.txtSosmed.setText(mDataset.get(position).getLocationTrip());
        holder.txtJmlGawean.setText(mDataset.get(position).getGaweanCount()+"");
        holder.txtHargaGawean.setText(mDataset.get(position).getJobFee()+"");
        holder.container.setOnClickListener(v -> {
            if (onSelectedListener != null) onSelectedListener.onSelected(mDataset.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class MyViewHolder  extends RecyclerView.ViewHolder {
        public TextView txtJudulGawean, txtDeskripsi, txtSosmed, txtJmlGawean, txtHargaGawean;
        public CardView container;
        public ImageView imgGawean;
        public MyViewHolder (View v) {
            super(v);
            txtJudulGawean = v.findViewById(R.id.txtJudulGawean);
            txtDeskripsi = v.findViewById(R.id.txtDeskripsi);
            txtSosmed = v.findViewById(R.id.txtSosmed);
            txtJmlGawean = v.findViewById(R.id.txtJmlGawean);
            txtHargaGawean = v.findViewById(R.id.txtHargaGawean);
            container = v.findViewById(R.id.container);
            imgGawean = v.findViewById(R.id.imgGawean);
        }
    }

    public void setOnSelectedListener(OnSelectedListener onSelectedListener){
        this.onSelectedListener = onSelectedListener;
    }

    public interface OnSelectedListener {
        void onSelected(GaweanListResponseItem item);
    }
}