package com.mogawe.mosurvei.view.shared.planogram;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.model.db.entity.Item;
import com.mogawe.mosurvei.model.store.PrefHelper;
import com.mogawe.mosurvei.model.store.PrefKey;
import com.mogawe.mosurvei.util.RxBusObject;
import com.mogawe.mosurvei.util.helper.DragListener;
import com.mogawe.mosurvei.view.BaseActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdapterFacingColumn extends RecyclerView.Adapter<AdapterFacingColumn.AdapterFacingColumnHolder> implements DragListener.Listener {

    private Context context;
    public List<Item> facingLists = new ArrayList<>();
    public int VIEW_TYPE_FOOTER = 1;
    public int VIEW_TYPE_CELL = 0;
    private String idAdapter;
    private DragListener.Listener listener = this;

    private long mLastClickTime = 0;

    public AdapterFacingColumn(Context context, List<Item> facingList, String idAdapter) {
        this.context = context;
        this.facingLists = facingList;
        this.idAdapter = idAdapter;
    }

    public void addSingleData(Item data) {
        int insertIndex = facingLists.size();
        facingLists.add(insertIndex, data);
        notifyItemInserted(insertIndex);
    }


    public void addData(List<Item> newData) {
        this.facingLists.addAll(newData);
        notifyDataSetChanged();
    }

    public void removeAt(int position) {
        this.facingLists.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, facingLists.size());
    }

    public void updateData(Item newFacingListItem) {
        Integer position = newFacingListItem.getIdItem();

        this.facingLists.set(position, newFacingListItem);
        notifyItemChanged(position);
        notifyItemRangeChanged(position, facingLists.size());

    }

    public DragListener getDragInstance() {
        if (listener != null) {
            return new DragListener(listener);
        } else {
            Log.e("ListAdapter", "Listener wasn't initialized!");
            return null;
        }
    }

    public void updateList(List<Item> list) {
        this.facingLists = list;
    }

    public List<Item> getList() {
        return facingLists;
    }

    @Override
    public AdapterFacingColumnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView;

        if (viewType == VIEW_TYPE_CELL) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ai_facing, parent, false);
            //Create viewholder for your default cell
        } else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ai_add_facing, parent, false);
            //Create viewholder for your footer view
        }

        return new AdapterFacingColumnHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFacingColumnHolder holder, int position) {
        if (position == facingLists.size()) {
            holder.buttonAdd.setTag(position);
            holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PrefHelper.setBoolean(PrefKey.IS_UPDATE_FACING_ITEM, false);
                    ((BaseActivity) context).getBus().send(new RxBusObject(RxBusObject.RxBusKey.OPEN_SEARCH_FACING, idAdapter));
                }
            });
        } else {
            final Item item = facingLists.get(position);
            if (item != null) {
                String itemName = item.getItem_name();
                String itemSize = item.getSize();
                String urlItemPicture = item.getItemPicture();
                Double itemWidth = item.getWidth();
                Double itemHeight = item.getHeight();
                Integer width;
                Integer height;

                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)holder.imvProductFacing.getLayoutParams();
                float density = context.getResources()
                        .getDisplayMetrics()
                        .density;

                if (itemWidth != null && itemHeight != null) {
                    layoutParams.height = pxToDp(itemHeight.intValue()*3, density);
                    layoutParams.width = pxToDp(itemWidth.intValue()*3, density);
                    holder.imvProductFacing.setLayoutParams(layoutParams);
                } else {
                    layoutParams.height = pxToDp(80, density);
                    layoutParams.width = pxToDp(60, density);
                    holder.imvProductFacing.setLayoutParams(layoutParams);
                }

                holder.txvGramasi.setText(itemSize);
                Glide.with(context.getApplicationContext())
                        .load(urlItemPicture)
//                    .memoryPolicy(MemoryPolicy.NO_CACHE)
//                    .networkPolicy(NetworkPolicy.NO_CACHE)
                        .error(R.color.grey_400)
                        .into(holder.imvProductFacing);

            } else {
                holder.txvGramasi.setText("Kosong");
                holder.imvProductFacing.setImageResource(R.color.grey_300);
            }

            holder.btnProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                        return;
                    }
                    mLastClickTime = SystemClock.elapsedRealtime();

                    if (item != null) {
                        item.setIdItem(position);
                        item.setUuidAdapter(idAdapter);
                        ((BaseActivity) context).getBus().send(new RxBusObject(RxBusObject.RxBusKey.SELECT_ROW_FACING, item));
                    } else {
                        Item nullItem = new Item();
                        nullItem.setIdItem(position);
                        nullItem.setUuidAdapter(idAdapter);
                        ((BaseActivity) context).getBus().send(new RxBusObject(RxBusObject.RxBusKey.SELECT_ROW_FACING, nullItem));
                    }

                }
            });

            holder.btnProduct.setTag(position);
            holder.btnProduct.setOnDragListener(new DragListener(listener));
            holder.btnProduct.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        v.startDragAndDrop(data, shadowBuilder, v, 0);
                    } else {
                        v.startDrag(data, shadowBuilder, v, 0);
                    }
                    return true;
                }
            });
        }
    }

    public static int pxToDp(int dp, float density) {

        return Math.round((float) dp * density);
    }

    @Override
    public int getItemCount() {
        return facingLists != null ? facingLists.size() + 1 : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == facingLists.size()) ? VIEW_TYPE_FOOTER : VIEW_TYPE_CELL;
    }

    @Override
    public void onRowMoved(int fromPosition, int toPosition) {
        if (toPosition != facingLists.size()) {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(facingLists, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(facingLists, i, i - 1);
                }
            }
            notifyItemMoved(fromPosition, toPosition);
        }
    }

    @Override
    public void onRowSelected(AdapterFacingColumnHolder myViewHolder) {
        myViewHolder.btnProduct.setBackgroundColor(Color.GRAY);
    }

    @Override
    public void onRowClear(AdapterFacingColumnHolder myViewHolder) {
        myViewHolder.btnProduct.setBackgroundResource(R.color.colorPrimary);
    }

    public class AdapterFacingColumnHolder extends RecyclerView.ViewHolder {

        //        @BindView(R.id.btnProduct)
        LinearLayout btnProduct;
        LinearLayout lnrProductFacing;
        ImageView imvProductFacing;
        TextView txvGramasi;
        //        @BindView(R.id.buttonAdd)
        ImageButton buttonAdd;

        public AdapterFacingColumnHolder(@NonNull View itemView) {
            super(itemView);
//            ButterKnife.bind(this, itemView);

            btnProduct = (LinearLayout) itemView.findViewById(R.id.btnProduct);
            lnrProductFacing = (LinearLayout) itemView.findViewById(R.id.lnrProductFacing);
            imvProductFacing = (ImageView) itemView.findViewById(R.id.imvProductFacing);
            txvGramasi = (TextView) itemView.findViewById(R.id.txvGramasi);


            buttonAdd = (ImageButton) itemView.findViewById(R.id.buttonAdd);
        }
    }
}
