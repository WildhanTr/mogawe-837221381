package com.mogawe.mosurvei.playground;

import android.content.Context;
import android.location.Address;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.model.bean.gawean.GaweanListResponse;
import com.mogawe.mosurvei.model.bean.gawean.GaweanListResponseItem;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class PaketGaweanAdapter extends RecyclerView.Adapter<PaketGaweanAdapter.MyViewHolder> {

    private ArrayList<GaweanListResponseItem> mDataset;
    private OnSelectedListener onSelectedListener;
    Context context;

    public PaketGaweanAdapter(Context context, ArrayList<GaweanListResponseItem> mDataset) {
        this.mDataset = mDataset;
        this.context = context;
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
        holder.txtJmlGawean.setText(mDataset.get(position).getGaweanCount()+" Gawean");
        NumberFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = formatter.format(mDataset.get(position).getJobFee());
        holder.txtHargaGawean.setText("Rp. " + formattedNumber+"");
        holder.container.setOnClickListener(v -> {
            if (onSelectedListener != null) onSelectedListener.onSelected(mDataset.get(position));
        });
        holder.imgMore.setOnClickListener(view -> {
            PopupMenu popup = new PopupMenu(context, holder.imgMore);
            //inflating menu from xml resource
            popup.inflate(R.menu.menu_paket_gawean);
            //adding click listener
            popup.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.hapus:
                        //handle menu1 click
                        removeAt(holder.getAdapterPosition());
                        return true;
                    default:
                        return false;
                }
            });
            //displaying the popup
            popup.show();
        });
    }

    public void removeAt(int position) {
        mDataset.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mDataset.size());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class MyViewHolder  extends RecyclerView.ViewHolder {
        public TextView txtJudulGawean, txtDeskripsi, txtSosmed, txtJmlGawean, txtHargaGawean;
        public CardView container;
        public ImageView imgGawean, imgMore;
        public MyViewHolder (View v) {
            super(v);
            txtJudulGawean = v.findViewById(R.id.txtJudulGawean);
            txtDeskripsi = v.findViewById(R.id.txtDeskripsi);
            txtSosmed = v.findViewById(R.id.txtSosmed);
            txtJmlGawean = v.findViewById(R.id.txtJmlGawean);
            txtHargaGawean = v.findViewById(R.id.txtHargaGawean);
            container = v.findViewById(R.id.container);
            imgGawean = v.findViewById(R.id.imgGawean);
            imgMore = v.findViewById(R.id.imgMore);
        }
    }

    public void setOnSelectedListener(OnSelectedListener onSelectedListener){
        this.onSelectedListener = onSelectedListener;
    }

    public interface OnSelectedListener {
        void onSelected(GaweanListResponseItem item);
        void onDeleted(GaweanListResponseItem item);
    }
}