package com.mogawe.mosurvei.view.shared.phone_validation;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.model.db.entity.User;
import com.mogawe.mosurvei.util.Constant;
import com.mogawe.mosurvei.view.BaseActivity;
import com.mogawe.mosurvei.view.BaseFragment;

import androidx.fragment.app.FragmentTransaction;

public class PhoneActivationFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PhoneActivationFragment() {
        // Required empty public constructor
    }
    public static PhoneActivationFragment newInstance(String param1, String param2) {
        PhoneActivationFragment fragment = new PhoneActivationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layout() {
        return R.layout.f_phone_activation;
    }

    public static void showFragment(BaseActivity sourceActivity, User user) {
        if (!sourceActivity.isFragmentNotNull(Constant.PHONE_ACTIVATION_FRAGMENT)) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("user",user);
            FragmentTransaction fragmentTransaction = sourceActivity.getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_out_right, R.anim.slide_in_left);
            fragmentTransaction.replace(R.id.layout_container, new PhoneActivationFragment(), Constant.PHONE_ACTIVATION_FRAGMENT);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
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
        View view = inflater.inflate(R.layout.f_phone_activation, container, false);
        return view;
    }

}
