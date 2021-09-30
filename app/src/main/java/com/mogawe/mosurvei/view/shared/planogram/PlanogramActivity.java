package com.mogawe.mosurvei.view.shared.planogram;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.model.db.entity.Fact;
import com.mogawe.mosurvei.model.db.entity.Item;
import com.mogawe.mosurvei.model.db.entity.Section;
import com.mogawe.mosurvei.model.network.response.ItemResponse;
import com.mogawe.mosurvei.model.network.response.task.MyTaskResponse;
import com.mogawe.mosurvei.model.store.PrefHelper;
import com.mogawe.mosurvei.model.store.PrefKey;
import com.mogawe.mosurvei.util.RxBusObject;
import com.mogawe.mosurvei.util.ui.Animator;
import com.mogawe.mosurvei.util.ui.GalanoButton;
import com.mogawe.mosurvei.view.BaseActivity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class PlanogramActivity extends BaseActivity {

    private static final String PLACE = "place";
    private static final String SELECTABLE = "selectable";
    private static final String FACT_ID = "factId";
    private static final String FACT = "fact";
    private static final String JOB_ID = "jobId";
    private static final String SECTION = "section";
    private static final String SOURCE_ITEMS = "sourceItems";

    @BindView(R.id.toolbar)
    Toolbar appBar;

    @BindView(R.id.rvFacingRow)
    RecyclerView rvFacingRow;

    @BindView(R.id.rltSearchFacing)
    public RelativeLayout rltSearchFacing;
    @BindView(R.id.crdSearchFacing)
    public CardView crdSearchFacing;
    @BindView(R.id.progressBarHorizontal)
    public MaterialProgressBar progressBarHorizontal;
    @BindView(R.id.imvCloseSearchFacing)
    public ImageView imvCloseSearchFacing;
    @BindView(R.id.edtSearchFacing)
    public EditText edtSearchFacing;
    @BindView(R.id.rvFacingProduct)
    public RecyclerView rvFacingProduct;
    @BindView(R.id.txvErrorProduct)
    public TextView txvErrorProduct;
    @BindView(R.id.imvClearEdtTextSearchFacing)
    public ImageView imvClearEdtTextSearchFacing;

    @BindView(R.id.lnrSearchProduct)
    public LinearLayout lnrSearchProduct;

    @BindView(R.id.lnrUpdateDeleteProduct)
    public LinearLayout lnrUpdateDeleteProduct;
    @BindView(R.id.imvProductFacing)
    public ImageView imvProductFacing;
    @BindView(R.id.btnDeleteProduct)
    public Button btnDeleteProduct;
    @BindView(R.id.btnEditProduct)
    public Button btnEditProduct;
    @BindView(R.id.txvProductName)
    public TextView txvProductName;
    @BindView(R.id.txvProductSize)
    public TextView txvProductSize;

    @BindView(R.id.closeDetail)
    public ImageView closeDetail;

    private AdapterFacingRow adapterFacingRow;
    List<View> facingViews = new ArrayList<>();
    private LinkedHashMap<String, AdapterFacingColumn> adapterFacingColumnHashMap = new LinkedHashMap<>();

    private ItemResponse getSearchableItem = new ItemResponse();
    private Integer pageSearching = 1;
    private boolean bottomFacingProductIsReached = false;
    private AdapterFacingSearchItem adapterFacingSearchItem;
    private boolean isSeachingFacingProduct = false;
    public Section section;
    public String factId;
    public String uuidSelectedFacingAdapter;
    public Fact fact;
    public List<List<Item>> itemFacingAll = new ArrayList<>();

    public List<Item> sourceItems = new ArrayList<>();
    public List<Item> skuItems = new ArrayList<>();

    public Item facingListItem;


    public static void startActivityForResult(BaseActivity sourceActivity, int requestCode, Boolean isSelectable, String jobId, String factId, Section section, Fact fact) {

        Intent intent = new Intent(sourceActivity, PlanogramActivity.class);
//        intent.putExtra(SELECTABLE, isSelectable);
//        intent.putExtra(JOB_ID, jobId);
        intent.putExtra(FACT_ID, factId);
        intent.putExtra(FACT, fact);
        intent.putExtra(SECTION, section);
        sourceActivity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected int layout() {
        return R.layout.a_planogram;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        section = (Section) getIntent().getSerializableExtra(SECTION);
        fact = (Fact) getIntent().getSerializableExtra(FACT);
        factId = getIntent().getStringExtra(FACT_ID);

        GalanoButton txvSimpan = new GalanoButton(this);
        txvSimpan.setText("Simpan");
        txvSimpan.setAllCaps(false);
        txvSimpan.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        txvSimpan.setPadding(2, 2, 2, 2);
        txvSimpan.setBackground(getDrawable(R.drawable.button_primary));
        txvSimpan.setTextColor(getResources().getColor(R.color.white));
        txvSimpan.setTypeface(txvSimpan.getTypeface(), Typeface.BOLD);
        Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.END;
        layoutParams.setMargins(8, 8, 50, 8);
        txvSimpan.setLayoutParams(layoutParams);

        txvSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animator.popTap(v);
                sendDataToSectionFormActivity();
            }
        });

        appBar.addView(txvSimpan);

        setSupportActionBar(appBar);
        setTitle("Planogram");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        appBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        loadTemplatePlano();

        imvCloseSearchFacing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animator.disappear(crdSearchFacing, Animator.VERY_SHORT);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rltSearchFacing.setVisibility(View.GONE);
                    }

                }, Animator.VERY_VERY_SHORT);
            }
        });

        rltSearchFacing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animator.disappear(crdSearchFacing, Animator.VERY_SHORT);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rltSearchFacing.setVisibility(View.GONE);
                        pageSearching = 1;
                        rvFacingProduct.setVisibility(View.GONE);
                        lnrSearchProduct.setVisibility(View.GONE);
                        lnrUpdateDeleteProduct.setVisibility(View.GONE);
                        if (getSearchableItem.getItems() != null) {
                            getSearchableItem.getItems().clear();
                        }
                    }

                }, Animator.VERY_VERY_SHORT);
            }
        });

        imvClearEdtTextSearchFacing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtSearchFacing.setText("");
            }
        });

        edtSearchFacing.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (getSearchableItem.getItems() != null) {
                        getSearchableItem.getItems().clear();
                    }

                    txvErrorProduct.setVisibility(View.GONE);

                    pageSearching = 1;
                    bottomFacingProductIsReached = false;

                    if (PrefHelper.getBoolean(PrefKey.IS_UPDATE_FACING_ITEM)) {
                        taskViewModel.getSearchItemFacing(factId, edtSearchFacing.getText().toString(), pageSearching, 20, uuidSelectedFacingAdapter, facingListItem.getIdItem());
                    } else {
                        taskViewModel.getSearchItemFacing(factId, edtSearchFacing.getText().toString(), pageSearching, 20, uuidSelectedFacingAdapter, null);
                    }

                    crdSearchFacing.setVisibility(View.VISIBLE);
                    progressBarHorizontal.setVisibility(View.VISIBLE);
                    hideKeyboard();
                } else {
                    hideKeyboard();
                }
                return false;
            }
        });

        edtSearchFacing.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (getSearchableItem.getItems() != null) {
                    getSearchableItem.getItems().clear();
                }

                txvErrorProduct.setVisibility(View.GONE);

                pageSearching = 1;
                bottomFacingProductIsReached = false;

                if (facingListItem != null && PrefHelper.getBoolean(PrefKey.IS_UPDATE_FACING_ITEM)) {
                    taskViewModel.getSearchItemFacing(factId, edtSearchFacing.getText().toString(), pageSearching, 20, uuidSelectedFacingAdapter, facingListItem.getIdItem());
                } else {
                    taskViewModel.getSearchItemFacing(factId, edtSearchFacing.getText().toString(), pageSearching, 20, uuidSelectedFacingAdapter, null);
                }

                crdSearchFacing.setVisibility(View.VISIBLE);
                progressBarHorizontal.setVisibility(View.VISIBLE);
            }
        });

        btnDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txvTitleCostumDialog.setText("Peringatan");
                if (facingListItem.getItem_name() != null) {
                    txvMessageCostumDialog.setText("Yakin ingin menghapus produk " + facingListItem.getItem_name() + " dari list?");
                } else {
                    txvMessageCostumDialog.setText("Yakin ingin menghapus produk kosong dari list?");
                }
                btnPositiveCostumDialog.setText("Ya");
                btnPositiveCostumDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (facingListItem != null) {
                            Animator.disappear(crdSearchFacing, Animator.VERY_SHORT);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    rltSearchFacing.setVisibility(View.GONE);
                                    pageSearching = 1;
                                    rvFacingProduct.setVisibility(View.GONE);
                                    lnrSearchProduct.setVisibility(View.GONE);
                                    lnrUpdateDeleteProduct.setVisibility(View.GONE);
                                }

                            }, Animator.VERY_VERY_SHORT);
                            getBus().send(new RxBusObject(RxBusObject.RxBusKey.DELETE_ROW_FACING, facingListItem));
                        }
                        costumDialog.dismiss();
                    }
                });
                btnNegativeCostumDialog.setText("Tidak");
                btnNegativeCostumDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        costumDialog.dismiss();
                    }
                });
                showCostumDialog("Oke&Cancel");
            }
        });

        btnEditProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animator.disappear(crdSearchFacing, Animator.VERY_SHORT);
                rvFacingProduct.setVisibility(View.GONE);
                pageSearching = 1;

                PrefHelper.setBoolean(PrefKey.IS_UPDATE_FACING_ITEM, true);

                uuidSelectedFacingAdapter = facingListItem.getUuidAdapter();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lnrUpdateDeleteProduct.setVisibility(View.GONE);
                        lnrSearchProduct.setVisibility(View.VISIBLE);
                        Animator.appear(crdSearchFacing, Animator.VERY_SHORT);

                        if (!edtSearchFacing.getText().toString().equals("")) {
                            if (PrefHelper.getBoolean(PrefKey.IS_UPDATE_FACING_ITEM)) {
                                taskViewModel.getSearchItemFacing(factId, edtSearchFacing.getText().toString(), pageSearching, 20, uuidSelectedFacingAdapter, facingListItem.getIdItem());
                            } else {
                                taskViewModel.getSearchItemFacing(factId, edtSearchFacing.getText().toString(), pageSearching, 20, uuidSelectedFacingAdapter, null);
                            }

                            crdSearchFacing.setVisibility(View.VISIBLE);
                            progressBarHorizontal.setVisibility(View.VISIBLE);
                            hideKeyboard();
                        }
                    }

                }, Animator.VERY_VERY_SHORT);
            }
        });

        closeDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animator.disappear(crdSearchFacing, Animator.VERY_SHORT);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rltSearchFacing.setVisibility(View.GONE);
                        pageSearching = 1;
                        rvFacingProduct.setVisibility(View.GONE);
                        lnrSearchProduct.setVisibility(View.GONE);
                        lnrUpdateDeleteProduct.setVisibility(View.GONE);
                        if (getSearchableItem.getItems() != null)
                            getSearchableItem.getItems().clear();
                    }

                }, Animator.VERY_VERY_SHORT);
            }
        });

        if (taskViewModel != null) {
            taskViewModel.getResponseLiveData().observe(this, new Observer<MyTaskResponse>() {
                @Override
                public void onChanged(MyTaskResponse myTaskResponse) {
                    if (myTaskResponse.isSuccess()) {
                        switch (myTaskResponse.getRequestKey()) {
                            case GET_ITEM_SEARCH:
                                getSearchableItem = myTaskResponse.getItemResponse();
                                isSeachingFacingProduct = false;

                                if (pageSearching == 1) {
                                    if (getSearchableItem.getItems().size() <= 0) {
                                        txvErrorProduct.setVisibility(View.VISIBLE);
                                    } else {
                                        txvErrorProduct.setVisibility(View.GONE);
                                    }

                                    loadFacingProduct();
                                } else {
                                    adapterFacingSearchItem.addData(getSearchableItem.getItems());
                                    progressBarHorizontal.setVisibility(View.INVISIBLE);
                                    if (getSearchableItem.getItems().size() == 0) {
                                        bottomFacingProductIsReached = true;
                                    }
                                }

                                break;
                            case FAILED:
                                dismissProgressDialog();
                                showNeutralCostumDialog("Gagal", "gagal mengakhiri gawean", false);
                                break;
                        }
                    } else {
                        dismissProgressDialog();
                    }
                }
            });

        }
    }

    private void sendDataToSectionFormActivity() {

        List<List<Item>> itemFacingAll = new ArrayList<>();

        if (adapterFacingColumnHashMap != null) {
            for (AdapterFacingColumn adapterFacingColumn : adapterFacingColumnHashMap.values()) {
                itemFacingAll.add(adapterFacingColumn.getList());
            }
        }

        for (List<Item> itemList : itemFacingAll) {
            sourceItems.addAll(itemList);
        }

        Gson gson = new Gson();


//        for (Item i : sourceItems) {
//            if (skuItems.size() != 0) {
//                if (!isExistItem(i)) {
//                    if (i.getUuidBrand().equals("596e44d0-c1c5-4565-8f17-dfcd4ed2cdc2") || i.getUuidBrand().equals("be136e14-2fbf-4ef8-9ac1-3ab467e06bf1") || i.getUuidBrand().equals("b2897620-6b8b-4e54-859b-d8f77520f32d") || i.getUuidBrand().equals("82a8234a-4c97-41a2-b978-b861dddb2124") || i.getUuidBrand().equals("bf6ae829-ac3c-4ca1-a63c-666f18b35a44") || i.getUuidBrand().equals("68186092-77f3-4c9f-9e9a-9588fcb654da")) {
//                        skuItems.add(i);
//                    }
//                }
//            } else {
//                if (i.getUuidBrand().equals("596e44d0-c1c5-4565-8f17-dfcd4ed2cdc2") || i.getUuidBrand().equals("be136e14-2fbf-4ef8-9ac1-3ab467e06bf1") || i.getUuidBrand().equals("b2897620-6b8b-4e54-859b-d8f77520f32d") || i.getUuidBrand().equals("82a8234a-4c97-41a2-b978-b861dddb2124") || i.getUuidBrand().equals("bf6ae829-ac3c-4ca1-a63c-666f18b35a44") || i.getUuidBrand().equals("68186092-77f3-4c9f-9e9a-9588fcb654da")) {
//                    skuItems.add(i);
//                }
//
//            }
//        }

//        String itemStock = gson.toJson(skuItems);

        String jsonItemFacingAll = gson.toJson(itemFacingAll);

        Intent intent = new Intent();
        intent.putExtra("jsonItemFacingAll", jsonItemFacingAll);
        intent.putExtra(SECTION, section);
        intent.putExtra(FACT, fact);
        intent.putExtra(FACT_ID, factId);
//        intent.putExtra(SOURCE_ITEMS, itemStock);
        setResult(RESULT_OK, intent);
        finish();
    }

    public Boolean isExistItem(Item i) {

        for (Item skuItem : skuItems) {
            if (i.getId_item().equals(skuItem.getId_item())) {
                return true;
            }
        }

        return false;
    }

    private void loadTemplatePlano() {

        if (!fact.getInput().equals("")) {
            Gson gson = new Gson();
            itemFacingAll = gson.fromJson(fact.getInput(), new TypeToken<List<List<Item>>>() {
            }.getType());
        } else {
            itemFacingAll = fact.getItemPlanograms();
        }

        if (itemFacingAll != null)
            for (List<Item> items : itemFacingAll) {
                //============COLUMN====================
                View facingRowData = getLayoutInflater().inflate(R.layout.l_facing_list, null, true);
                RecyclerView rvFacing = facingRowData.findViewById(R.id.rvFacing);

                String uniqueId = UUID.randomUUID().toString();

                AdapterFacingColumn adapterFacingColumn = new AdapterFacingColumn(this, items, uniqueId);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                rvFacing.setLayoutManager(mLayoutManager);
                rvFacing.setItemAnimator(new DefaultItemAnimator());
                ((SimpleItemAnimator) rvFacing.getItemAnimator()).setSupportsChangeAnimations(true);

//        ItemTouchHelper.Callback callback = new ItemMoveCallback(adapterFacingColumn);
//        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
//        touchHelper.attachToRecyclerView(rvFacing);

                rvFacing.setAdapter(adapterFacingColumn);
                rvFacing.setOnDragListener(adapterFacingColumn.getDragInstance());

                facingViews.add(facingRowData);

                adapterFacingColumnHashMap.put(uniqueId, adapterFacingColumn);

                //============COLUMN====================
            }

        //============ROW====================
        adapterFacingRow = new AdapterFacingRow(this, facingViews);
        RecyclerView.LayoutManager mLayoutManagerRow = new LinearLayoutManager(getApplicationContext());
        rvFacingRow.setLayoutManager(mLayoutManagerRow);
        rvFacingRow.setItemAnimator(new DefaultItemAnimator());
        rvFacingRow.setAdapter(adapterFacingRow);
        //============ROW====================
    }

    @Override
    public void busHandler(RxBusObject.RxBusKey busKey, Object busObject) {
        super.busHandler(busKey, busObject);

        switch (busKey) {
            case ADD_ROW_FACING:
                View facingRowData = getLayoutInflater().inflate(R.layout.l_facing_list, null, true);
                List<Item> facingLists = new ArrayList<>();

                RecyclerView rvFacing = facingRowData.findViewById(R.id.rvFacing);

                String uniqueId = UUID.randomUUID().toString();

                AdapterFacingColumn adapterFacingColumn = new AdapterFacingColumn(this, facingLists, uniqueId);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                rvFacing.setLayoutManager(mLayoutManager);
                rvFacing.setItemAnimator(new DefaultItemAnimator());
                rvFacing.setOnDragListener(adapterFacingColumn.getDragInstance());

//                ItemTouchHelper.Callback callback = new ItemMoveCallback(adapterFacingColumn);
//                ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
//                touchHelper.attachToRecyclerView(rvFacing);

                rvFacing.setAdapter(adapterFacingColumn);

                adapterFacingColumnHashMap.put(uniqueId, adapterFacingColumn);

                if (adapterFacingRow != null)
                    adapterFacingRow.addSingleData(facingRowData);

                break;
            case ADD_ITEM_FACING:
                Item item = (Item) busObject;

                adapterFacingColumn = adapterFacingColumnHashMap.get(item.getUuidAdapter());

                if (adapterFacingColumn != null)
                    adapterFacingColumn.addSingleData(item);

                Animator.disappear(crdSearchFacing, Animator.VERY_SHORT);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rltSearchFacing.setVisibility(View.GONE);
                        pageSearching = 1;
                        rvFacingProduct.setVisibility(View.GONE);
                        lnrSearchProduct.setVisibility(View.GONE);
                        lnrUpdateDeleteProduct.setVisibility(View.GONE);
                    }

                }, Animator.VERY_VERY_SHORT);

                break;
            case DELETE_ROW_FACING:
                facingListItem = (Item) busObject;

                adapterFacingColumn = adapterFacingColumnHashMap.get(facingListItem.getUuidAdapter());

                if (adapterFacingColumn != null)
                    adapterFacingColumn.removeAt(facingListItem.getIdItem());

                break;
            case UPDATE_ROW_FACING:
                facingListItem = (Item) busObject;

                adapterFacingColumn = adapterFacingColumnHashMap.get(facingListItem.getUuidAdapter());

                if (adapterFacingColumn != null)
                    adapterFacingColumn.updateData(facingListItem);

                PrefHelper.setBoolean(PrefKey.IS_UPDATE_FACING_ITEM, false);

                Animator.disappear(crdSearchFacing, Animator.VERY_SHORT);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rltSearchFacing.setVisibility(View.GONE);
                        pageSearching = 1;
                        rvFacingProduct.setVisibility(View.GONE);
                        lnrSearchProduct.setVisibility(View.GONE);
                        lnrUpdateDeleteProduct.setVisibility(View.GONE);
                    }

                }, Animator.VERY_VERY_SHORT);
                break;
            case SELECT_ROW_FACING:
                facingListItem = (Item) busObject;

                Glide.with(this)
                        .load(facingListItem.getItemPicture())
                        .error(R.color.grey_200)
                        .into(imvProductFacing);

                if (facingListItem.getItem_name() != null) {
                    txvProductName.setText(facingListItem.getItem_name());
                } else {
                    txvProductName.setText("Kosong");
                }

                if (facingListItem.getSize() != null) {
                    txvProductSize.setText(facingListItem.getSize());
                } else {
                    txvProductSize.setText(null);
                }

                rltSearchFacing.setVisibility(View.VISIBLE);
                lnrUpdateDeleteProduct.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Animator.appear(crdSearchFacing, Animator.VERY_SHORT);
                    }

                }, Animator.VERY_VERY_SHORT);

                break;
            case OPEN_SEARCH_FACING:
                String idAdapter = (String) busObject;

                /**
                 * Initiate Custom Dialog
                 */
//                final Dialog dialog = new Dialog(PlanogramActivity.this);
//                dialog.setContentView(R.layout.l_costum_dialog_add_facing);
//                dialog.setTitle("Tambah Produk Facing");

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                AlertDialog dialog;

                dialog = builder.create();
                inflater = getLayoutInflater();
                dialogView = inflater.inflate(R.layout.l_costum_dialog_add_facing, null);
                dialog.setView(dialogView);
                dialog.setCancelable(true);
                dialog.setTitle("Tambah produk");

                Button btnAdd = (Button) dialogView.findViewById(R.id.btnAdd);
                Button btnAddNull = (Button) dialogView.findViewById(R.id.btnAddNull);
                Button btnDuplicate = (Button) dialogView.findViewById(R.id.btnDuplicate);

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        uuidSelectedFacingAdapter = (String) busObject;

                        rltSearchFacing.setVisibility(View.VISIBLE);
                        lnrSearchProduct.setVisibility(View.VISIBLE);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Animator.appear(crdSearchFacing, Animator.VERY_SHORT);
                                if (!edtSearchFacing.getText().toString().equals("")) {
                                    if (PrefHelper.getBoolean(PrefKey.IS_UPDATE_FACING_ITEM)) {
                                        taskViewModel.getSearchItemFacing(factId, edtSearchFacing.getText().toString(), pageSearching, 20, uuidSelectedFacingAdapter, facingListItem.getIdItem());
                                    } else {
                                        taskViewModel.getSearchItemFacing(factId, edtSearchFacing.getText().toString(), pageSearching, 20, uuidSelectedFacingAdapter, null);
                                    }

                                    crdSearchFacing.setVisibility(View.VISIBLE);
                                    progressBarHorizontal.setVisibility(View.VISIBLE);
                                    hideKeyboard();
                                }
                            }

                        }, Animator.VERY_VERY_SHORT);
                        dialog.dismiss();
                    }
                });

                btnAddNull.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AdapterFacingColumn adapterFacingColumn = adapterFacingColumnHashMap.get(idAdapter);

                        Item nullItem = new Item();

                        if (adapterFacingColumn != null)
                            adapterFacingColumn.addSingleData(null);

                        dialog.dismiss();
                    }

                });

                btnDuplicate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AdapterFacingColumn adapterFacingColumn = adapterFacingColumnHashMap.get(idAdapter);

                        if (adapterFacingColumn != null)
                            adapterFacingColumn.addSingleData(adapterFacingColumn.getList().get(adapterFacingColumn.getItemCount() - 2));

                        dialog.dismiss();

                    }
                });

                dialog.show();

                break;
        }
    }

    private void loadFacingProduct() {
        rvFacingProduct.setVisibility(View.VISIBLE);
        ////============FACINGITEM====================
        adapterFacingSearchItem = new AdapterFacingSearchItem(PlanogramActivity.this, getSearchableItem.getItems());
        RecyclerView.LayoutManager layoutManagerFacingItem = new LinearLayoutManager(getApplicationContext());
        rvFacingProduct.setLayoutManager(layoutManagerFacingItem);
        rvFacingProduct.setItemAnimator(new DefaultItemAnimator());
        rvFacingProduct.setAdapter(adapterFacingSearchItem);
        ////============FACINGITEM====================

        rvFacingProduct.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
                int totalItemCount = layoutManager.getItemCount();
                int lastVisible = layoutManager.findLastVisibleItemPosition();

                boolean endHasBeenReached = (lastVisible + 1) >= totalItemCount;

                if (totalItemCount > 0 && endHasBeenReached && !isSeachingFacingProduct && !bottomFacingProductIsReached && totalItemCount >= 20) {

                    pageSearching += 1;
                    if (PrefHelper.getBoolean(PrefKey.IS_UPDATE_FACING_ITEM)) {
                        taskViewModel.getSearchItemFacing(factId, edtSearchFacing.getText().toString(), pageSearching, 20, uuidSelectedFacingAdapter, facingListItem.getIdItem());
                    } else {
                        taskViewModel.getSearchItemFacing(factId, edtSearchFacing.getText().toString(), pageSearching, 20, uuidSelectedFacingAdapter, null);
                    }
                    progressBarHorizontal.setVisibility(View.VISIBLE);
                    isSeachingFacingProduct = true;
                }
            }
        });

        progressBarHorizontal.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (rltSearchFacing.getVisibility() == View.VISIBLE) {
            Animator.disappear(crdSearchFacing, Animator.VERY_SHORT);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    rltSearchFacing.setVisibility(View.GONE);
                    pageSearching = 1;
                    rvFacingProduct.setVisibility(View.GONE);
                    lnrSearchProduct.setVisibility(View.GONE);
                    lnrUpdateDeleteProduct.setVisibility(View.GONE);
                    if (getSearchableItem.getItems() != null)
                        getSearchableItem.getItems().clear();
                }

            }, Animator.VERY_VERY_SHORT);
        } else {
            txvTitleCostumDialog.setText("Keluar");
            txvMessageCostumDialog.setText("Apa kamu yakin ingin keluar dari planogram?");
            btnPositiveCostumDialog.setText("Simpan dan keluar");
            btnPositiveCostumDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendDataToSectionFormActivity();
                    finish();
                    costumDialog.dismiss();
                }
            });
            btnNegativeCostumDialog.setText("Tidak dan keluar");
            btnNegativeCostumDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    costumDialog.dismiss();
                }
            });
            showCostumDialog("Oke&Cancel");
        }
    }
}
