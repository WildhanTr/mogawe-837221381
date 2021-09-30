package com.mogawe.mosurvei.util.helper;

import android.view.DragEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.model.db.entity.Item;
import com.mogawe.mosurvei.view.shared.planogram.AdapterFacingColumn;

import java.util.List;

public class DragListener implements View.OnDragListener {

    private boolean isDropped = false;
    private Listener listener;

    public DragListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DROP:
                isDropped = true;
                int positionTarget = -1;

                View viewSource = (View) event.getLocalState();

                if (v.getId() == R.id.lnrProductFacing || v.getId() == R.id.btnAdd || v.getId() == R.id.btnProduct) {

                    RecyclerView target;
                    if (v.getId() == R.id.btnAdd) {
                        target = (RecyclerView) v.getRootView().findViewById(R.id.rvFacing);
                    } else {
                        target = (RecyclerView) v.getParent().getParent();
                        positionTarget = (int) v.getTag();
                    }

                    RecyclerView source = (RecyclerView) viewSource.getParent().getParent();

                    AdapterFacingColumn adapterSource = (AdapterFacingColumn) source.getAdapter();
                    int positionSource = (int) viewSource.getTag();

                    Item list = adapterSource.getList().get(positionSource);
                    List<Item> listSource = adapterSource.getList();

                    listSource.remove(positionSource);
                    adapterSource.updateList(listSource);
                    adapterSource.notifyDataSetChanged();


                    AdapterFacingColumn adapterTarget = (AdapterFacingColumn) target.getAdapter();
                    List<Item> customListTarget = adapterTarget.getList();
                    if (positionTarget >= 0) {
                        customListTarget.add(positionTarget, list);
                    } else {
                        customListTarget.add(list);
                    }
                    adapterTarget.updateList(customListTarget);
                    adapterTarget.notifyDataSetChanged();

                }
                break;
        }

        if (!isDropped && event.getLocalState() != null) {
            ((View) event.getLocalState()).setVisibility(View.VISIBLE);
        }
        return true;
    }

    public interface Listener {

        void onRowMoved(int fromPosition, int toPosition);

        void onRowSelected(AdapterFacingColumn.AdapterFacingColumnHolder myViewHolder);

        void onRowClear(AdapterFacingColumn.AdapterFacingColumnHolder myViewHolder);

    }
}
