package com.mogawe.mosurvei.view.shared.planogram;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.util.RxBusObject;
import com.mogawe.mosurvei.util.ui.Animator;
import com.mogawe.mosurvei.view.BaseActivity;

import java.util.List;

public class AdapterFacingRow extends RecyclerView.Adapter<AdapterFacingRow.AdapterFacingRowHolder> {

    private Context context;
    public List<View> list;

    public int VIEW_TYPE_FOOTER = 1;
    public int VIEW_TYPE_CELL = 0;


    public AdapterFacingRow(Context context, List<View> list) {
        this.context = context;
        this.list = list;
    }

    public void addData(List<View> newData) {
        this.list.addAll(newData);
        notifyDataSetChanged();
    }

    public void setData(List<View> newData) {
        this.list = newData;
        notifyDataSetChanged();
    }

    public void addSingleData(View data) {
        int insertIndex = list.size();
        list.add(insertIndex, data);
        notifyItemInserted(insertIndex);
    }

    @Override
    public AdapterFacingRow.AdapterFacingRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView;

        if (viewType == VIEW_TYPE_CELL) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ai_view_home, parent, false);
            //Create viewholder for your default cell
        } else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ai_add_facing_row, parent, false);
            //Create viewholder for your footer view
        }

        return new AdapterFacingRow.AdapterFacingRowHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterFacingRow.AdapterFacingRowHolder holder, int position) {

        if(position == list.size()) {
            holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Animator.popTap(view);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ((BaseActivity) context).getBus().send(new RxBusObject(RxBusObject.RxBusKey.ADD_ROW_FACING, null));
                        }
                    }, Animator.VERY_SHORT);
                }
            });
        } else {
            holder.crdViewHome.removeAllViews();

            if (list.get(position).getParent() == null)
                holder.crdViewHome.addView(list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() + 1 : 0;
    }


    @Override
    public int getItemViewType(int position) {
        return (position == list.size()) ? VIEW_TYPE_FOOTER : VIEW_TYPE_CELL;
    }

    public class AdapterFacingRowHolder extends RecyclerView.ViewHolder {

//        @BindView(R.id.lnrViewHome)
        LinearLayout crdViewHome;
//        @BindView(R.id.buttonAdd)
        Button buttonAdd;

        public AdapterFacingRowHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(this, itemView);

            crdViewHome =  (LinearLayout)itemView.findViewById(R.id.lnrViewHome);
            buttonAdd = (Button) itemView.findViewById(R.id.buttonAdd);
        }
    }
}
