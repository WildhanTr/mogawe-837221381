package com.mogawe.mosurvei.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mogawe.mosurvei.MoSurveiApplication;
import com.mogawe.mosurvei.model.db.entity.Notification;
import com.mogawe.mosurvei.model.db.entity.User;
import com.mogawe.mosurvei.model.network.response.CertificateCountResponse;
import com.mogawe.mosurvei.model.network.response.CertificateListResponse;
import com.mogawe.mosurvei.model.network.response.SupplierDashboardCountResponse;
import com.mogawe.mosurvei.model.network.response.UserResponse;
import com.mogawe.mosurvei.model.repository.UserRepository;
import com.mogawe.mosurvei.model.store.PrefHelper;
import com.mogawe.mosurvei.model.store.PrefKey;

import java.io.File;
import java.util.List;

public class UserViewModel extends AndroidViewModel {


    private UserRepository userRepository;
    private LiveData<UserResponse> responseLiveData;
    private LiveData<User> userLiveData;
    private LiveData<List<Notification>> notificationListLiveData;
    private LiveData<CertificateListResponse> certificateListResponseLiveData;
    private LiveData<CertificateCountResponse> certificateCountResponseLiveData;
    private LiveData<SupplierDashboardCountResponse> supplierDashboardCountResponseLiveData;


    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository((MoSurveiApplication) application);
        responseLiveData = userRepository.getResponseLiveData();
        if (PrefHelper.hasString(PrefKey.LOGGED_IN_AS)) {
            if (PrefHelper.getString(PrefKey.LOGGED_IN_AS).equalsIgnoreCase("BIZ")) {
                userLiveData = userRepository.getSupplierProfile();
            } else {
                userLiveData = userRepository.getProfile();
            }
        } else {
            userLiveData = userRepository.getProfile();
        }

        notificationListLiveData = userRepository.getNotificationList();
        certificateListResponseLiveData = userRepository.getCertificateListResponseLiveData();
        certificateCountResponseLiveData = userRepository.getCertificateCountResponseLiveData();
        supplierDashboardCountResponseLiveData = userRepository.getSupplierDashboardCountResponseLiveData();
    }

    public LiveData<UserResponse> getResponseLiveData() {
        return responseLiveData;
    }

    public void checkVersion(String versionName) {
        responseLiveData = userRepository.checkVersion(versionName);
    }

    public void checkVersionV2(Integer versionNumber) {
        responseLiveData = userRepository.checkVersionV2(versionNumber);
    }

    public void register(User user) {
        responseLiveData = userRepository.registerToServer(user);
    }

    public void registerSupplier(User user) {
        responseLiveData = userRepository.registerSupplierToServer(user);
    }

    public void getProfileFromServer() {
        userRepository.getProfileFromServer();
    }

    public void activate(User user) {
        responseLiveData = userRepository.activateToServer(user);
    }

    public void activateEmailSupplier(User user) {
        responseLiveData = userRepository.activateEmailSupplierToServer(user);
    }

    public void activateNewPassword(User user) {
        responseLiveData = userRepository.activateNewPasswordToServer(user);
    }

    public void activateNewPasswordSupplier(User user) {
        responseLiveData = userRepository.activateNewPasswordSupplierToServer(user);
    }

    public void login(User user) {
        responseLiveData = userRepository.loginToServer(user);
    }

    public void loginSupplier(User user) {
        responseLiveData = userRepository.loginSupplierToServer(user);
    }

    public void loginSocMedia(User user, int socialMedia) {
        responseLiveData = userRepository.loginBySocMedToServer(user, socialMedia);
    }

    public void resendEmailActivation(User user) {
        responseLiveData = userRepository.requestResendEmailActivationToServer(user);
    }

    public void resendEmailActivationSupplier(User user) {
        responseLiveData = userRepository.requestResendEmailActivationSupplierToServer(user);
    }

    public void resetPassword(User user) {
        responseLiveData = userRepository.requestResetPasswordToServer(user);
    }

    public void resetPasswordSupplier(User user) {
        responseLiveData = userRepository.requestResetPasswordSupplierToServer(user);
    }

    public void updatePhone(User user, String newPhoneNumber) {
        responseLiveData = userRepository.updatePhoneToServer(user, newPhoneNumber);
    }

    public void updatePhoneCitcall(User user, String newPhoneNumber) {
        responseLiveData = userRepository.updatePhoneToServerCitcall(user, newPhoneNumber);
    }

    public void updatePhoneNumberCitcallPublic(User user, String newPhoneNumber) {
        responseLiveData = userRepository.updatePhoneNumberCitcallPublic(user, newPhoneNumber);
    }

    public void updateProfile(User user) {
        responseLiveData = userRepository.updateProfileToServer(user);
    }

    public void updateProfileSupplier(User user) {
        responseLiveData = userRepository.updateProfileSupplierToServer(user);
    }

    public void updatePhotoProfile(User user, File imageFile) {
        responseLiveData = userRepository.updatePhotoProfileToServer(user, imageFile);
    }

    public void updatePhotoProfileSupplier(User user, File imageFile) {
        responseLiveData = userRepository.updatePhotoProfileSupplierToServer(user, imageFile);
    }

    public void checkTerms() {
        responseLiveData = userRepository.checkTerms();
    }

    public void agreeTerms() {
        responseLiveData = userRepository.agreeTerms();
    }

    public void resendPhoneActivation(User user) {
        responseLiveData = userRepository.requestResendPhoneActivationToServer(user);
    }

    public void activatePhone(User user) {
        responseLiveData = userRepository.activatePhoneToServer(user);
    }

    public void phoneVerification(User user) {
        responseLiveData = userRepository.phoneVerification(user);
    }

    public void updateDeviceId(String deviceId) {
        userRepository.updateDeviceId(deviceId);
    }

    public void checkQRDownline(String mogawerCode) {
        userRepository.checkQRDownline(mogawerCode);
    }

    public void addDownline(String mogawerCode) {
        userRepository.addDownline(mogawerCode);
    }

    public void getDownline(Integer page, Integer offset) {
        userRepository.getDownline(page, offset);
    }

    public void reportError(Integer versionNumber, String error, String brand, String device, String model, String idDevice, String product, String sdk, String release, String incremental, String percentAvailableRam, String availableRam, String operatorName, String activityName, String signalStrength, String battery, String availableStorage) {
        userRepository.reportError(versionNumber, error, brand, device, model, idDevice, product, sdk, release, incremental, percentAvailableRam, availableRam, operatorName, activityName, signalStrength, battery, availableStorage);
    }

    public void misscallOtp(String phoneNumber, int gateway) {
        userRepository.misscallOTP(phoneNumber, gateway);
    }

    public void openAPI(String url, String value) {
        userRepository.openApi(url, value);
    }

    public LiveData<User> profile() {
        return userLiveData;
    }


    public void clear() {
        userRepository.deleteUser();
    }

    public LiveData<List<Notification>> getNotificationListLiveData() {
        return notificationListLiveData;
    }

    public void clearNotification() {
        userRepository.deleteNotification();
    }

    public void saveNotification(Notification notification) {
        userRepository.saveNotification(notification);
    }

    public void listCertificate() {
        certificateListResponseLiveData = userRepository.listCertificate();
    }

    public void applyCertificate(String uuidCertificate) {
        certificateListResponseLiveData = userRepository.applyCertificate(uuidCertificate);
    }

    public void countCertificate() {
        certificateCountResponseLiveData = userRepository.countCertificate();
    }

    public void countDashboardSupplier() {
        supplierDashboardCountResponseLiveData = userRepository.countDashboardSupplier();
    }

    public LiveData<CertificateListResponse> getCertificateListResponseLiveData() {
        return certificateListResponseLiveData;
    }

    public LiveData<CertificateCountResponse> getCertificateCountResponseLiveData() {
        return certificateCountResponseLiveData;
    }

    public LiveData<SupplierDashboardCountResponse> getSupplierDashboardCountResponseLiveData() {
        return supplierDashboardCountResponseLiveData;
    }
}