package com.mogawe.mosurvei.presenter.user;

import com.mogawe.mosurvei.model.network.request.ChangePasswordRequest;
import com.mogawe.mosurvei.model.network.request.GenericReq;
import com.mogawe.mosurvei.model.network.response.GenericResponse;
import com.mogawe.mosurvei.model.store.PrefHelper;
import com.mogawe.mosurvei.model.store.PrefKey;
import com.mogawe.mosurvei.util.PassDigest;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by mobiletech on 5/5/17.
 */

public class ChangePassPresenter {

    public void changePassword(String old_password, String new_password, final String conf_password) {

        String oldhashPass = "";
        try {
            oldhashPass = PassDigest.SHA256(old_password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String newhashPass = "";
        try {
            newhashPass = PassDigest.SHA256(new_password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ChangePasswordRequest request = new ChangePasswordRequest();

        request.setOldpassword(oldhashPass);
        request.setNewpassword(newhashPass);

        GenericReq req = new GenericReq();
    }

}
