package com.mogawe.mosurvei.model.network.request;

public class ChangePasswordRequest {


    private String oldPassword;
    private String newPassword;

    public String getOldpassword() {
        return oldPassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldPassword = oldpassword;
    }

    public String getNewpassword() {
        return newPassword;
    }

    public void setNewpassword(String newpassword) {
        this.newPassword = newpassword;
    }
}
