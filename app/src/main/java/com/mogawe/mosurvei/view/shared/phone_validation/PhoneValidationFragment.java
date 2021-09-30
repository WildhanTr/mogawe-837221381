package com.mogawe.mosurvei.view.shared.phone_validation;

import android.os.Bundle;

import androidx.fragment.app.FragmentTransaction;

import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.model.db.entity.User;
import com.mogawe.mosurvei.model.network.response.UserResponse;
import com.mogawe.mosurvei.util.Constant;
import com.mogawe.mosurvei.view.BaseActivity;
import com.mogawe.mosurvei.view.BaseFragment;

public class PhoneValidationFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private TextInputLayout edtPhoneNumberWrapper;
    private TextInputLayout edtActivationCodeWrapper;

    private EditText edtPhoneNumber;
    private EditText edtActivationCode;
    private Button btnSubmit;

    private String phoneNumber;
    private User user;

    private BaseActivity activity;

    public PhoneValidationFragment() {
        // Required empty public constructor
    }

    public static PhoneValidationFragment newInstance(String param1, String param2) {
        PhoneValidationFragment fragment = new PhoneValidationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layout() {
        return R.layout.f_phone_validation;
    }

    public static void showFragment(BaseActivity sourceActivity, User user) {

        if (!sourceActivity.isFragmentNotNull(Constant.PHONE_VALIDATION_FRAGMENT)) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("user",user);

            FragmentTransaction fragmentTransaction = sourceActivity.getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_out_right, R.anim.slide_in_left);

        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_phone_validation, container, false);

        edtPhoneNumberWrapper = view.findViewById(R.id.edtPhoneNumberWrapper);
        edtActivationCodeWrapper = view.findViewById(R.id.edtActivationCodeWrapper);


        edtPhoneNumber = view.findViewById(R.id.edtPhoneNumber);
        edtPhoneNumber.setInputType(InputType.TYPE_CLASS_PHONE);
        edtPhoneNumber.setKeyListener(DigitsKeyListener.getInstance("0123456789"));

        user = (User) getArguments().getSerializable("user");

        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateNumber();
            }
        });

        return view;
    }

    public void validateNumber(){
        phoneNumber = edtPhoneNumber.getText().toString();
//        userViewModel.updatePhone(user);
    }
}
