package com.mogawe.mosurvei.view.shared.qrscanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.Result;
import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.model.db.entity.Section;
import com.mogawe.mosurvei.util.ExceptionHandler;
import com.mogawe.mosurvei.view.BaseActivity;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRScannerActivity extends BaseActivity implements ZXingScannerView.ResultHandler {

    private static final String TAG = "QRScannerActivity";


    private ZXingScannerView mScannerView;
    private Section section;

    public static void startActivity(Activity sourceActivity, int reqCode) {
        sourceActivity.startActivityForResult(new Intent(sourceActivity, QRScannerActivity.class), reqCode);
    }

    public static void startActivity(Activity sourceActivity, int reqCode, Section section) {
        sourceActivity.startActivityForResult(new Intent(sourceActivity, QRScannerActivity.class).putExtra(SECTION, section), reqCode);
    }

    @Override
    protected int layout() {
        return R.layout.a_qr_scanner;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        if(getIntent().hasExtra(SECTION))
            section = (Section) getIntent().getSerializableExtra(SECTION);

        setContentView(mScannerView);

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
    }


    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        Log.v("TAG", rawResult.getText()); // Prints scan results
        Log.v("TAG", rawResult.getBarcodeFormat().toString());

        Intent intent = new Intent();
        intent.putExtra(QR_CODE, rawResult.getText());
        intent.putExtra(SECTION, section);
        setResult(RESULT_BARCODE, intent);
        finish();
    }
}
