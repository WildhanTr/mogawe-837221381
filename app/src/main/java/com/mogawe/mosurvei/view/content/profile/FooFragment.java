package com.mogawe.mosurvei.view.content.profile;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.view.BaseActivity;
import com.mogawe.mosurvei.view.BaseFragment;

public class FooFragment extends BaseFragment {
    public static final String TAG = FooFragment.class.getSimpleName();
    private BaseActivity baseActivity;

    public FooFragment(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    public static void showFragment(BaseActivity baseActivity) {
        FragmentTransaction fragmentTransaction = baseActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_fragment, new FooFragment(baseActivity), TAG);
        fragmentTransaction.commit();
    }

    @Override
    protected int layout() {
        return R.layout.foo_fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
