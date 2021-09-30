package com.mogawe.mosurvei.view.shared.planogram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.model.db.entity.Item;
import com.mogawe.mosurvei.model.store.PrefHelper;
import com.mogawe.mosurvei.model.store.PrefKey;
import com.mogawe.mosurvei.util.RxBusObject;
import com.mogawe.mosurvei.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterFacingSearchItem extends RecyclerView.Adapter<AdapterFacingSearchItem.AdapteSearhFacingHolder> {

    private Context context;
    public List<Item> facingLists = new ArrayList<>();

    public AdapterFacingSearchItem(Context context, List<Item> facingLists) {
        this.context = context;
        this.facingLists = facingLists;
    }

    public void addData(List<Item> newData) {
        this.facingLists.addAll(newData);
        notifyDataSetChanged();
    }

    @Override
    public AdapteSearhFacingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapteSearhFacingHolder(LayoutInflater.from(context).inflate(R.layout.ai_facing_search_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapteSearhFacingHolder holder, int position) {
        final Item item = facingLists.get(position);

        holder.txvProductName.setText(item.getItem_name());

        holder.lnrProductItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PrefHelper.getBoolean(PrefKey.IS_UPDATE_FACING_ITEM)) {
                    ((BaseActivity) context).getBus().send(new RxBusObject(RxBusObject.RxBusKey.UPDATE_ROW_FACING, item));
                } else {
                    ((BaseActivity) context).getBus().send(new RxBusObject(RxBusObject.RxBusKey.ADD_ITEM_FACING, item));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return facingLists != null ? facingLists.size() : 0;
    }

    public class AdapteSearhFacingHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txvProductName)
        TextView txvProductName;
        @BindView(R.id.lnrProductItem)
        LinearLayout lnrProductItem;

        public AdapteSearhFacingHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
