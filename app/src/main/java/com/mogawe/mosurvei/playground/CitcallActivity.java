package com.mogawe.mosurvei.playground;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.view.BaseActivity;

import butterknife.BindView;

public class CitcallActivity extends BaseActivity {

    public static final int CALL_LOG_PERMISSION = 1;

    @BindView(R.id.field_phone_suffix)
    EditText field_phone_suffix;
    @BindView(R.id.field_phone_prefix)
    EditText field_phone_prefix;
    @BindView(R.id.btn_verify)
    Button btn_verify;

    @Override
    protected int layout() {
        return R.layout.a_citcall;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestPermissionCallLog();
//
//        btn_verify.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                requestOtp();
//            }
//        });

    }

    private void requestOtp() {
        final String reqPrefix = field_phone_prefix.getText().toString().trim();
        final String reqMsisdn = field_phone_suffix.getText().toString().trim();

        if (TextUtils.isEmpty(reqPrefix)) {
            field_phone_prefix.setError("Please Fill Country Code");
            field_phone_prefix.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(reqMsisdn)) {
            field_phone_suffix.setError("Please Fill Mobile Number");
            field_phone_suffix.requestFocus();
            return;
        }

        userViewModel.misscallOtp(reqPrefix + reqMsisdn, 0);

    }

//    public void requestPermissionCallLog() {
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if((ContextCompat.checkSelfPermission(this,
//                    Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED)) {
//                ActivityCompat.requestPermissions(CitcallActivity.this,
//                        new String[] {Manifest.permission.READ_CALL_LOG}, CALL_LOG_PERMISSION);
//            }
//        }
//    }
}
