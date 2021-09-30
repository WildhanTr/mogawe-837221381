package com.mogawe.mosurvei.playground;

import android.content.Intent;

import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.view.BaseActivity;

public class FotoProduk extends BaseActivity {


    public static void startActivity(BaseActivity sourceActivity) {
        Intent intent = new Intent(sourceActivity, FotoProduk.class);
        sourceActivity.startActivity(intent);
    }

    @Override
    protected int layout() {
        return R.layout.a_tambah_foto_produk;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
