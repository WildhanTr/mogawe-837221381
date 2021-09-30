package com.mogawe.mosurvei.model.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.mogawe.mosurvei.MoSurveiApplication;
import com.mogawe.mosurvei.model.db.dao.NotificationDao;
import com.mogawe.mosurvei.model.db.dao.UserDao;
import com.mogawe.mosurvei.model.db.entity.Notification;
import com.mogawe.mosurvei.model.db.entity.User;
import com.mogawe.mosurvei.model.network.request.user.ActivationRequest;
import com.mogawe.mosurvei.model.network.request.user.CitcalMisscallRequest;
import com.mogawe.mosurvei.model.network.request.user.GetDownlineRequest;
import com.mogawe.mosurvei.model.network.request.user.LoginRequest;
import com.mogawe.mosurvei.model.network.request.user.LoginSocMedRequest;
import com.mogawe.mosurvei.model.network.request.user.PhoneActivationRequest;
import com.mogawe.mosurvei.model.network.request.user.ProfileRequest;
import com.mogawe.mosurvei.model.network.request.user.RegistrationRequest;
import com.mogawe.mosurvei.model.network.request.user.ReportErrorRequest;
import com.mogawe.mosurvei.model.network.request.user.ResendActivationRequest;
import com.mogawe.mosurvei.model.network.request.user.ResetPasswordRequest;
import com.mogawe.mosurvei.model.network.request.user.UpdateDeviceIdRequest;
import com.mogawe.mosurvei.model.network.request.user.UpdatePhoneRequest;
import com.mogawe.mosurvei.model.network.request.user.UpdateProfileRequest;
import com.mogawe.mosurvei.model.network.response.CertificateCountResponse;
import com.mogawe.mosurvei.model.network.response.CertificateListResponse;
import com.mogawe.mosurvei.model.network.response.CitcallResponse;
import com.mogawe.mosurvei.model.network.response.DownlinerResponse;
import com.mogawe.mosurvei.model.network.response.GenericResponse;
import com.mogawe.mosurvei.model.network.response.SupplierDashboardCountResponse;
import com.mogawe.mosurvei.model.network.response.UserResponse;
import com.mogawe.mosurvei.model.store.PrefHelper;
import com.mogawe.mosurvei.model.store.PrefKey;
import com.mogawe.mosurvei.util.PassDigest;
import com.mogawe.mosurvei.view.BaseActivity;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserRepository {


    private static final String ERROR_CODE = "001";
    private static final String ACCOUNT_INACTIVE = "002";
    private static final String ERROR_MESSAGE_SERVER = "Gagal terhubung ke server, nih.\nCoba cek koneksi internetmu dan coba lagi, ya!";
    private static final String ERROR_MESSAGE_INTERNET = "Coba cek koneksi internetmu, ya!";

    private MoSurveiApplication application;
    private MutableLiveData<UserResponse> responseLiveData = new MutableLiveData<>();
    private LiveData<User> userLiveData;
    private LiveData<List<Notification>> notificationLiveData;
    private UserDao userDao;
    private NotificationDao notificationDao;


    public UserRepository(MoSurveiApplication application) {
        this.application = application;

        if (application != null) {
            if (application.getDatabase() != null) {
                userDao = application.getDatabase().userDao();
                notificationDao = application.getDatabase().notificationDao();
                userLiveData = userDao.findUser();
                notificationLiveData = notificationDao.findAll();

                System.out.println("DSafdsasdasd " + notificationDao);
                System.out.println("fdsgefsadasd  " + application.getDatabase().notificationDao());
            }
        }

    }

    public MutableLiveData<UserResponse> getResponseLiveData() {
        return responseLiveData;
    }

    public LiveData<UserResponse> checkVersion(String versionName) {

        application.getApiUserService().checkVersion(versionName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GenericResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                        responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.REGISTRATION));
                    }

                    @Override
                    public void onNext(GenericResponse genericResponse) {
                        if (genericResponse.isSuccess()) {
                            UserResponse userResponse = new UserResponse();
                            userResponse.setRequestKey(UserResponse.RequestKey.REQUEST_SUCCESS);
                            userResponse.setMessage(genericResponse.getMessage());
                            userResponse.setReturnValue(genericResponse.getReturnValue());

                            responseLiveData.setValue(userResponse);
                        } else {
                            UserResponse userResponse = new UserResponse();
                            userResponse.setRequestKey(UserResponse.RequestKey.REQUEST_FAILED);
                            userResponse.setMessage(genericResponse.getMessage());
                            userResponse.setReturnValue(genericResponse.getReturnValue());

                            responseLiveData.setValue(userResponse);
                        }
                    }
                });
        return responseLiveData;
    }

    public LiveData<UserResponse> checkVersionV2(Integer versionNumber) {

        application.getApiUserService().checkVersionV2(versionNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GenericResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                        responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.REGISTRATION));
                    }

                    @Override
                    public void onNext(GenericResponse genericResponse) {
                        if (genericResponse.isSuccess()) {
                            UserResponse userResponse = new UserResponse();
                            userResponse.setRequestKey(UserResponse.RequestKey.REQUEST_SUCCESS);
                            userResponse.setMessage(genericResponse.getMessage());
                            userResponse.setReturnValue(genericResponse.getReturnValue());

                            responseLiveData.setValue(userResponse);
                        } else {
                            UserResponse userResponse = new UserResponse();
                            userResponse.setRequestKey(UserResponse.RequestKey.REQUEST_FAILED);
                            userResponse.setMessage(genericResponse.getMessage());
                            userResponse.setReturnValue(genericResponse.getReturnValue());

                            responseLiveData.setValue(userResponse);
                        }
                    }
                });
        return responseLiveData;
    }


    public LiveData<UserResponse> checkTerms() {
        application.getApiUserService().checkTerms(PrefHelper.getString(PrefKey.TOKEN))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GenericResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(GenericResponse genericResponse) {
                        if (genericResponse.isSuccess()) {
                            UserResponse userResponse = new UserResponse();
                            userResponse.setRequestKey(UserResponse.RequestKey.TERMS_READ);
                            userResponse.setMessage(genericResponse.getMessage());
                            userResponse.setReturnValue(genericResponse.getReturnValue());
                            System.out.println("Check Terms >> " + genericResponse);
                            responseLiveData.setValue(userResponse);
                        } else {
                            UserResponse userResponse = new UserResponse();
                            userResponse.setRequestKey(UserResponse.RequestKey.TERMS_NOT_READ);
                            userResponse.setMessage(genericResponse.getMessage());
                            userResponse.setReturnValue(genericResponse.getReturnValue());
                            System.out.println("Check Terms Not Read >> " + genericResponse);
                            responseLiveData.setValue(userResponse);
                        }
                    }
                });
        return responseLiveData;
    }

    public LiveData<UserResponse> agreeTerms() {
        application.getApiUserService().agreeTerms(PrefHelper.getString(PrefKey.TOKEN))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GenericResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(GenericResponse genericResponse) {
                        if (genericResponse.isSuccess()) {
                            UserResponse userResponse = new UserResponse();
                            userResponse.setRequestKey(UserResponse.RequestKey.TERMS_READ);
                            userResponse.setMessage(genericResponse.getMessage());
                            userResponse.setReturnValue(genericResponse.getReturnValue());
                            System.out.println("Agree Terms >> " + genericResponse);
                            getProfileFromServer();
                            responseLiveData.setValue(userResponse);
                        }
                    }
                });
        return responseLiveData;
    }

    public LiveData<UserResponse> registerToServer(final User user) {

        String hashPass = user.getPassword();
        try {
            hashPass = PassDigest.SHA256(user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        RegistrationRequest request = new RegistrationRequest(user.getEmail(), user.getFullName(), hashPass, user.getRefCode());

        application.getApiUserService().registration(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.REGISTRATION));
                    }

                    @Override
                    public void onNext(UserResponse response) {
                        if (response.isSuccess()) {
                            response.setRequestKey(UserResponse.RequestKey.REGISTRATION);
                            responseLiveData.setValue(response);
                        } else {
                            responseLiveData.setValue(new UserResponse(ERROR_CODE, response.getMessage(), UserResponse.RequestKey.LOGIN));
                        }
//                        registerDO(user, response);
                    }
                });
        return responseLiveData;
    }



    public LiveData<UserResponse> registerSupplierToServer(final User user) {

        String hashPass = user.getPassword();
        try {
            hashPass = PassDigest.SHA256(user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        RegistrationRequest request = new RegistrationRequest(user.getEmail(), user.getFullName(), hashPass);
        request.setStoreName(user.getStoreName());
        request.setStoreAddress(user.getStoreAddress());
        request.setStoreLat(user.getStoreLat());
        request.setStoreLng(user.getStoreLng());
        request.setShipmentProvinceId(user.getProvinceId());
        request.setShipmentProvinceName(user.getShipmentProvinceName());
        request.setShipmentCityId(user.getShipmentCityId());
        request.setShipmentCityName(user.getShipmentCityName());

        application.getApiUserService().supplierRegistration(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.REGISTRATION));
                    }

                    @Override
                    public void onNext(UserResponse response) {
                        if (response.isSuccess()) {
                            response.setRequestKey(UserResponse.RequestKey.REGISTRATION);
                            responseLiveData.setValue(response);
                        } else {
                            responseLiveData.setValue(new UserResponse(ERROR_CODE, response.getMessage(), UserResponse.RequestKey.LOGIN));
                        }
//                        registerDO(user, response);
                    }
                });
        return responseLiveData;
    }


    public LiveData<UserResponse> activateToServer(User user) {

        ActivationRequest request = new ActivationRequest(user.getEmail(), user.getActivationCode());

        application.getApiUserService().activation(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.ACTIVATION));
                    }

                    @Override
                    public void onNext(UserResponse response) {
                        response.setRequestKey(UserResponse.RequestKey.ACTIVATION);
                        responseLiveData.setValue(response);
                    }
                });


        return responseLiveData;
    }


    public LiveData<UserResponse> activateEmailSupplierToServer(User user) {

        ActivationRequest request = new ActivationRequest(user.getEmail(), user.getActivationCode());

        application.getApiUserService().supplierEmailActivation(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.ACTIVATION));
                    }

                    @Override
                    public void onNext(UserResponse response) {
                        response.setRequestKey(UserResponse.RequestKey.ACTIVATION);
                        responseLiveData.setValue(response);
                    }
                });


        return responseLiveData;
    }


    public LiveData<UserResponse> activateNewPasswordToServer(User user) {

        ActivationRequest request = new ActivationRequest(user.getEmail(), user.getActivationCode());

        application.getApiUserService().newPasswordActivation(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.ACTIVATION));
                    }

                    @Override
                    public void onNext(UserResponse response) {
                        response.setRequestKey(UserResponse.RequestKey.ACTIVATION);
                        user.setHasPassword(true);
                        updateUser(user);
                        response.setUser(user);
                        System.out.println("ACTIVATIONawjdiajdiajsidj");
                        responseLiveData.setValue(response);
                    }
                });


        return responseLiveData;
    }


    public LiveData<UserResponse> activateNewPasswordSupplierToServer(User user) {

        ActivationRequest request = new ActivationRequest(user.getEmail(), user.getActivationCode());

        application.getApiUserService().newPasswordActivationSupplier(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.ACTIVATION));
                    }

                    @Override
                    public void onNext(UserResponse response) {
                        response.setRequestKey(UserResponse.RequestKey.ACTIVATION);
                        user.setHasPassword(true);
                        updateUser(user);
                        response.setUser(user);
                        System.out.println("ACTIVATIONawjdiajdiajsidj");
                        responseLiveData.setValue(response);
                    }
                });


        return responseLiveData;
    }


    public LiveData<UserResponse> activatePhoneToServer(final User user) {

        PhoneActivationRequest request = new PhoneActivationRequest(user.getEmail(), user.getPhone(), user.getActivationCode());

        application.getApiUserService().phoneActivation(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.ACTIVATION));
                    }

                    @Override
                    public void onNext(UserResponse response) {
                        System.out.println("asdsadasddfdb " + response.isSuccess());
                        if (response.isSuccess()) {
                            user.setPhoneActive(true);
                            updateUser(user);
                        }
                        System.out.println("ACTIVATIONPHONEAWOKAWOKAWO");
                        response.setRequestKey(UserResponse.RequestKey.ACTIVATION);
                        responseLiveData.setValue(response);
                    }
                });


        return responseLiveData;
    }

    public LiveData<UserResponse> phoneVerification(final User user) {

        PhoneActivationRequest request = new PhoneActivationRequest(user.getEmail(), user.getPhone(), user.getActivationCode());

        application.getApiUserService().phoneVerification(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.ACTIVATION));
                    }

                    @Override
                    public void onNext(UserResponse response) {
                        System.out.println("asdsadasddfdb " + response.isSuccess());
                        if (response.isSuccess()) {
                            user.setPhoneActive(true);
                            updateUser(user);
                        }
                        System.out.println("ACTIVATIONPHONEAWOKAWOKAWO");
                        response.setRequestKey(UserResponse.RequestKey.ACTIVATION);
                        responseLiveData.setValue(response);
                    }
                });


        return responseLiveData;
    }

    public LiveData<UserResponse> loginToServer(final User user) {

        String hashPass = user.getPassword();
        try {
            hashPass = PassDigest.SHA256(user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoginRequest request = new LoginRequest(user.getEmail(), hashPass);

        application.getApiUserService().login(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.LOGIN));
                    }

                    @Override
                    public void onNext(UserResponse response) {
                        System.out.println("Response user repository : " + response);
                        System.out.println(response.getMessage());
                        if (response.isSuccess()) {
                            response.setRequestKey(UserResponse.RequestKey.LOGIN);
                            responseLiveData.setValue(response);
                            if (response.isSuccess()) {
                                PrefHelper.setBoolean(PrefKey.IS_LOGGED_IN, true);
                                PrefHelper.setString(PrefKey.LOGGED_IN_AS, "CROWD");
                                PrefHelper.setBoolean(PrefKey.CERTIFICATE_AFTER_LOGIN, true);
                                PrefHelper.setString(PrefKey.TOKEN, response.getToken());
                            }

                        } else {

                            if (response.getReturnValue().equals("002")) {
                                responseLiveData.setValue(new UserResponse(ACCOUNT_INACTIVE, response.getMessage(), UserResponse.RequestKey.LOGIN));
                            } else {
                                UserResponse userResponse = new UserResponse();
                                userResponse.setReturnValue(ERROR_CODE);
                                userResponse.setMessage(response.getMessage());
                                userResponse.setRequestKey(UserResponse.RequestKey.LOGIN);
                                userResponse.setUser(response.getUser());
                                responseLiveData.setValue(userResponse);
                            }

                        }
//                        updateTokenDO(user, response);
                    }
                });


        return responseLiveData;
    }



    public LiveData<UserResponse> loginSupplierToServer(final User user) {

        String hashPass = user.getPassword();
        try {
            hashPass = PassDigest.SHA256(user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoginRequest request = new LoginRequest(user.getEmail(), hashPass);

        application.getApiUserService().supplierLogin(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.LOGIN));
                    }

                    @Override
                    public void onNext(UserResponse response) {
                        System.out.println("Response user repository : " + response);
                        System.out.println(response.getMessage());
                        if (response.isSuccess()) {
                            response.setRequestKey(UserResponse.RequestKey.LOGIN);
                            responseLiveData.setValue(response);
                            if (response.isSuccess()) {
                                PrefHelper.setBoolean(PrefKey.IS_LOGGED_IN, true);
                                PrefHelper.setString(PrefKey.LOGGED_IN_AS, "BIZ");
                                PrefHelper.setBoolean(PrefKey.CERTIFICATE_AFTER_LOGIN, true);
                                PrefHelper.setString(PrefKey.TOKEN, response.getToken());
                            }

                        } else {

                            if (response.getReturnValue().equals("002")) {
                                responseLiveData.setValue(new UserResponse(ACCOUNT_INACTIVE, response.getMessage(), UserResponse.RequestKey.LOGIN));
                            } else {
                                UserResponse userResponse = new UserResponse();
                                userResponse.setReturnValue(ERROR_CODE);
                                userResponse.setMessage(response.getMessage());
                                userResponse.setRequestKey(UserResponse.RequestKey.LOGIN);
                                userResponse.setUser(response.getUser());
                                responseLiveData.setValue(userResponse);
                            }

                        }
//                        updateTokenDO(user, response);
                    }
                });


        return responseLiveData;
    }


    public LiveData<UserResponse> loginBySocMedToServer(final User user, int socialMedia) {

        String facebookId = "";
        String gmailId = "";
        String twitterId = "";
        switch (socialMedia) {
            case BaseActivity.SOCMED_FB:
                facebookId = user.getSocmedId();
                break;
            case BaseActivity.SOCMED_GMAIL:
                gmailId = user.getSocmedId();
                break;
            case BaseActivity.SOCMED_TWT:
                twitterId = user.getSocmedId();
                break;

        }

        LoginSocMedRequest request = new LoginSocMedRequest(user.getFullName(), user.getEmail(), facebookId, gmailId, twitterId);

        application.getApiUserService().loginBySocMed(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.LOGIN_SOCMED));

                    }

                    @Override
                    public void onNext(UserResponse response) {
                        if (response.isSuccess()) {
                            PrefHelper.setString(PrefKey.TOKEN, response.getToken());
                            response.setRequestKey(UserResponse.RequestKey.LOGIN_SOCMED);
                            responseLiveData.setValue(response);
                        } else {
                            responseLiveData.setValue(new UserResponse(ERROR_CODE, response.getMessage(), UserResponse.RequestKey.LOGIN));
                        }
//                        registerSocmedDO(user, response);
                    }
                });


        return responseLiveData;
    }

    public LiveData<UserResponse> requestResendEmailActivationToServer(User user) {

        ResendActivationRequest request = new ResendActivationRequest(user.getEmail());

        application.getApiUserService().resendActivationCode(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.RESEND_EMAIL));
                    }

                    @Override
                    public void onNext(UserResponse response) {
                        response.setRequestKey(UserResponse.RequestKey.RESEND_EMAIL);
                        responseLiveData.setValue(response);
                    }
                });


        return responseLiveData;
    }


    public LiveData<UserResponse> requestResendEmailActivationSupplierToServer(User user) {

        ResendActivationRequest request = new ResendActivationRequest(user.getEmail());

        application.getApiUserService().resendActivationCodeSupplier(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.RESEND_EMAIL));
                    }

                    @Override
                    public void onNext(UserResponse response) {
                        response.setRequestKey(UserResponse.RequestKey.RESEND_EMAIL);
                        responseLiveData.setValue(response);
                    }
                });


        return responseLiveData;
    }


    public LiveData<UserResponse> requestResendPhoneActivationToServer(User user) {

        PhoneActivationRequest request = new PhoneActivationRequest(user.getEmail(), user.getPhone());

        application.getApiUserService().sendPhoneActivationCode(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.RESEND_SMS));
                    }

                    @Override
                    public void onNext(UserResponse response) {
                        response.setRequestKey(UserResponse.RequestKey.RESEND_SMS);
                        responseLiveData.setValue(response);
                    }
                });


        return responseLiveData;
    }

    public LiveData<UserResponse> requestResetPasswordToServer(User user) {

        String hashPass = user.getPassword();
        try {
            hashPass = PassDigest.SHA256(user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResetPasswordRequest request = new ResetPasswordRequest(user.getEmail(), hashPass);
        application.getApiUserService().resetPassword(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.RESET_PASSWORD));
                    }

                    @Override
                    public void onNext(UserResponse response) {
                        if (response.isSuccess()) {
                            user.setHasPassword(true);
                            updateUser(user);
                            response.setRequestKey(UserResponse.RequestKey.RESET_PASSWORD);
                            responseLiveData.setValue(response);
                        }

                    }
                });
        return responseLiveData;
    }


    public LiveData<UserResponse> requestResetPasswordSupplierToServer(User user) {

        String hashPass = user.getPassword();
        try {
            hashPass = PassDigest.SHA256(user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResetPasswordRequest request = new ResetPasswordRequest(user.getEmail(), hashPass);
        application.getApiUserService().resetPasswordSupplier(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.RESET_PASSWORD));
                    }

                    @Override
                    public void onNext(UserResponse response) {
                        if (response.isSuccess()) {
                            user.setHasPassword(true);
                            updateUser(user);
                            response.setRequestKey(UserResponse.RequestKey.RESET_PASSWORD);
                            responseLiveData.setValue(response);
                        }

                    }
                });
        return responseLiveData;
    }

    public LiveData<UserResponse> openApi(String url, String value) {

        application.getApiUserService().openApi(PrefHelper.getString(PrefKey.TOKEN), url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.RESET_PASSWORD));
                    }

                    @Override
                    public void onNext(UserResponse response) {
                        response.setRequestKey(UserResponse.RequestKey.RESPONSE_OPEN_API);
                        responseLiveData.setValue(response);
                    }
                });
        return responseLiveData;
    }


    public LiveData<UserResponse> updatePhoneToServer(final User user, String newPhoneNumber) {
        UpdatePhoneRequest request = new UpdatePhoneRequest();
//        request.setCity(user.getCity() != null ? user.getCity() : null);
//        request.setBirthdate(user.getBirthdate() != null ? user.getBirthdate() : null);
//        request.setGender(user.getGender() != null ? user.getGender() : null);
//        request.setEdu(user.getEdu() != null ? user.getEdu() : null);
        request.setPhone(newPhoneNumber);
        Gson gson = new Gson();
        System.out.println(gson.toJson(request).toString());

        application.getApiUserService().updatePhoneNumber(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.UPDATE_PHONE));
                    }

                    @Override
                    public void onNext(UserResponse response) {
                        if (response.isSuccess()) {
                            user.setPhone(newPhoneNumber);
                            user.setPhoneActive(true);
                            updateUser(user);
                            response.setRequestKey(UserResponse.RequestKey.UPDATE_PHONE);
                            responseLiveData.setValue(response);
                        } else {
                            response.setRequestKey(UserResponse.RequestKey.REQUEST_FAILED);
                            responseLiveData.setValue(response);
                        }

                    }
                });


        return responseLiveData;
    }

    public LiveData<UserResponse> updatePhoneToServerCitcall(final User user, String newPhoneNumber) {
        UpdatePhoneRequest request = new UpdatePhoneRequest();
//        request.setCity(user.getCity() != null ? user.getCity() : null);
//        request.setBirthdate(user.getBirthdate() != null ? user.getBirthdate() : null);
//        request.setGender(user.getGender() != null ? user.getGender() : null);
//        request.setEdu(user.getEdu() != null ? user.getEdu() : null);
        request.setPhone(newPhoneNumber);
        Gson gson = new Gson();
        System.out.println(gson.toJson(request).toString());

        application.getApiUserService().updatePhoneNumberCitcall(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GenericResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.RESEND_SMS));
                    }

                    @Override
                    public void onNext(GenericResponse response) {
                        UserResponse userResponse = new UserResponse();
                        System.out.println("adsfvsadasdawdadw " + response);
                        if (response.isSuccess()) {
                            if (user != null) {
                                user.setPhone(newPhoneNumber);
                                user.setPhoneActive(true);
                                updateUser(user);
                            }
                            userResponse.setReturnValue(response.getReturnValue());
                            userResponse.setMessage(response.getMessage());
                            userResponse.setRequestKey(UserResponse.RequestKey.RESEND_SMS);
                            responseLiveData.setValue(userResponse);
                        } else {
                            userResponse.setReturnValue(response.getReturnValue());
                            userResponse.setMessage(response.getMessage());
                            userResponse.setRequestKey(UserResponse.RequestKey.REQUEST_FAILED);
                            responseLiveData.setValue(userResponse);
                        }

                    }
                });


        return responseLiveData;
    }

    public LiveData<UserResponse> updatePhoneNumberCitcallPublic(final User user, String newPhoneNumber) {
        UpdatePhoneRequest request = new UpdatePhoneRequest();
//        request.setCity(user.getCity() != null ? user.getCity() : null);
//        request.setBirthdate(user.getBirthdate() != null ? user.getBirthdate() : null);
//        request.setGender(user.getGender() != null ? user.getGender() : null);
//        request.setEdu(user.getEdu() != null ? user.getEdu() : null);
        request.setPhone(newPhoneNumber);
        Gson gson = new Gson();
        System.out.println(gson.toJson(request).toString());

        application.getApiUserService().updatePhoneNumberCitcallPublic(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GenericResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.RESEND_SMS));
                    }

                    @Override
                    public void onNext(GenericResponse response) {
                        UserResponse userResponse = new UserResponse();
                        System.out.println("adsfvsadasdawdadw " + response);
                        if (response.isSuccess()) {
                            if (user != null) {
                                user.setPhone(newPhoneNumber);
                                user.setPhoneActive(true);
                                updateUser(user);
                            }
                            userResponse.setReturnValue(response.getReturnValue());
                            userResponse.setMessage(response.getMessage());
                            userResponse.setRequestKey(UserResponse.RequestKey.RESEND_SMS);
                            responseLiveData.setValue(userResponse);
                        } else {
                            userResponse.setReturnValue(response.getReturnValue());
                            userResponse.setMessage(response.getMessage());
                            userResponse.setRequestKey(UserResponse.RequestKey.REQUEST_FAILED);
                            responseLiveData.setValue(userResponse);
                        }

                    }
                });


        return responseLiveData;
    }


    public LiveData<UserResponse> updateProfileToServer(final User user) {
        UpdateProfileRequest request = new UpdateProfileRequest();

        request.setCity(user.getCity() != null ? user.getCity() : null);
        if (user.getBirthdate() != null) {
            request.setBirthdate(user.getBirthdate());
        }

        request.setFullName(user.getFullName() != null ? user.getFullName() : null);
        request.setGender(user.getGender() != null ? user.getGender() : null);
        request.setEdu(user.getEdu() != null ? user.getEdu() : null);
//        request.setPhone(user.getPhone() != null ? user.getPhone() : null);
//        request.setLatitude(user.getLatitude().toString());
//        request.setLongitude(user.getLongitude().toString());

        application.getApiUserService().updateProfile(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.UPDATE_PROFILE));
                    }

                    @Override
                    public void onNext(UserResponse response) {
                        if (response.isSuccess()) {
                            updateUser(user);
                        }
                        response.setRequestKey(UserResponse.RequestKey.UPDATE_PROFILE);
                        responseLiveData.setValue(response);
                    }
                });


        return responseLiveData;
    }


    public LiveData<UserResponse> updateProfileSupplierToServer(final User user) {

        application.getApiUserService().updateProfileSupplier(PrefHelper.getString(PrefKey.TOKEN), user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.UPDATE_PROFILE));
                    }

                    @Override
                    public void onNext(UserResponse response) {
                        if (response.isSuccess()) {
                            updateUser(user);
                        }
                        response.setRequestKey(UserResponse.RequestKey.UPDATE_PROFILE);
                        responseLiveData.setValue(response);
                    }
                });


        return responseLiveData;
    }


    public LiveData<UserResponse> updatePhotoProfileToServer(User user, File imageFile) {
        String imageName = "PROFILE_" + user.getCode() + ".jpg";

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part multiPart = MultipartBody.Part.createFormData("profilePicture", imageName, requestBody);

        application.getApiUserService().updatePhotoProfile(PrefHelper.getString(PrefKey.TOKEN), multiPart)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("gagal");
                        System.out.println(e);
                        e.printStackTrace();
                        responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.UPDATE_PHOTO_PROFILE));
                    }

                    @Override
                    public void onNext(UserResponse response) {
                        System.out.println("sukses");
                        System.out.println(response);
                        response.setRequestKey(UserResponse.RequestKey.UPDATE_PHOTO_PROFILE);
                        responseLiveData.setValue(response);
                    }
                });


        return responseLiveData;
    }


    public LiveData<UserResponse> updatePhotoProfileSupplierToServer(User user, File imageFile) {
        String imageName = "PROFILE_" + user.getCode() + ".jpg";

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part multiPart = MultipartBody.Part.createFormData("profilePicture", imageName, requestBody);

        application.getApiUserService().updatePhotoProfileSupplier(PrefHelper.getString(PrefKey.TOKEN), multiPart)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("gagal");
                        System.out.println(e);
                        e.printStackTrace();
                        responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.UPDATE_PHOTO_PROFILE));
                    }

                    @Override
                    public void onNext(UserResponse response) {
                        System.out.println("sukses");
                        System.out.println(response);
                        response.setRequestKey(UserResponse.RequestKey.UPDATE_PHOTO_PROFILE);
                        responseLiveData.setValue(response);
                    }
                });


        return responseLiveData;
    }

    public LiveData<User> getProfile() {

        if (userLiveData.getValue() == null) {
            if (PrefHelper.hasString(PrefKey.TOKEN))
                getProfileFromServer();
        }
        return userLiveData;
    }


    public LiveData<User> getSupplierProfile() {

        if (userLiveData.getValue() == null) {
            if (PrefHelper.hasString(PrefKey.TOKEN))
                getSupplierProfileFromServer();
        }
        return userLiveData;
    }

    public void getProfileFromServer() {
        ProfileRequest request = new ProfileRequest();
        application.getApiUserService().profile(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        //responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.PROFILE));
                    }

                    @Override
                    public void onNext(UserResponse response) {

                        //      response.setRequestKey(UserResponse.RequestKey.PROFILE);
                        //      responseLiveData.setValue(response);
                        if (response.isSuccess()) {
                            PrefHelper.setString(PrefKey.ID_USER, response.getUser().getCode());
//                            PrefHelper.setString(PrefKey.USER_FULLNAME, response.getUser().getFullName());
//                            PrefHelper.setString(PrefKey.USER_EMAIL, response.getUser().getEmail());
//                            PrefHelper.setString(PrefKey.USER_PHONE, response.getUser().getPhone());
//                            PrefHelper.setString(PrefKey.USER_PICTURE, response.getUser().getProfilePicture());
//                            PrefHelper.setString(PrefKey.USER_SEX, response.getUser().getGender());
//                            PrefHelper.setString(PrefKey.USER_BIRTHDATE, response.getUser().getBirthdate());
//                            PrefHelper.setString(PrefKey.USER_EDUCATION, response.getUser().getEdu());
//                            PrefHelper.setString(PrefKey.USER_LOCATION, response.getUser().getCity());
//                            PrefHelper.setString(PrefKey.USER_SKILL, response.getUser().getLevel().toString());
                            deleteUser();
                            System.out.println("respdaosjdapsdnasd " + response.getUser().getEmail());
                            System.out.println("getFullName = " + response.getUser().getFullName());
                            System.out.println("getMogawersCode = " + response.getUser().getCode());
                            saveUser(response.getUser());

                            response.getUser().setToken(PrefHelper.getString(PrefKey.TOKEN));
                            response.setRequestKey(UserResponse.RequestKey.PROFILE);

                            responseLiveData.setValue(response);
                        } else {
                            if (response.isInactive()) {
                                response.setRequestKey(UserResponse.RequestKey.TOKEN_EXPIRED);
                                responseLiveData.setValue(response);
                            }
                        }
                    }
                });
    }


    public void getSupplierProfileFromServer() {
        ProfileRequest request = new ProfileRequest();
        application.getApiUserService().supplierProfile(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        //responseLiveData.setValue(new UserResponse(ERROR_CODE, ERROR_MESSAGE_SERVER, UserResponse.RequestKey.PROFILE));
                    }

                    @Override
                    public void onNext(UserResponse response) {
                        if (response.isSuccess()) {
                            PrefHelper.setString(PrefKey.ID_USER, response.getUser().getCode());
                            deleteUser();
                            saveUser(response.getUser());

                            response.getUser().setToken(PrefHelper.getString(PrefKey.TOKEN));
                            response.setRequestKey(UserResponse.RequestKey.PROFILE);

                            responseLiveData.setValue(response);
                        } else {
                            if (response.isInactive()) {
                                response.setRequestKey(UserResponse.RequestKey.TOKEN_EXPIRED);
                                responseLiveData.setValue(response);
                            }
                        }
                    }
                });
    }


    public void updateDeviceId(String deviceId) {
        UpdateDeviceIdRequest request = new UpdateDeviceIdRequest(deviceId);
        System.out.println("adjsidjsaijdoaisj "+ deviceId);
        application.getApiUserService().updateDeviceId(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GenericResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        PrefHelper.setBoolean(PrefKey.SENT_GCM_TOKEN_TO_SERVER, false);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(GenericResponse response) {
                        if (response.isSuccess()) {
                            PrefHelper.setBoolean(PrefKey.SENT_GCM_TOKEN_TO_SERVER, true);
                        } else {
                            PrefHelper.setBoolean(PrefKey.SENT_GCM_TOKEN_TO_SERVER, false);
                        }

                    }
                });

    }

    public void checkQRDownline(String mogawerCode) {
        application.getApiUserService().checkQRDownline(PrefHelper.getString(PrefKey.TOKEN), mogawerCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UserResponse response) {
                        UserResponse userResponse = new UserResponse();
                        if (response.isFailure()) {
                            userResponse.setReturnValue(response.getReturnValue());
                            userResponse.setMessage(response.getMessage());
                            userResponse.setRequestKey(UserResponse.RequestKey.CHECK_DOWNLINE_FAILED);

                            responseLiveData.setValue(userResponse);
                        } else {
                            userResponse.setReturnValue(response.getReturnValue());
                            userResponse.setMessage(response.getMessage());
                            userResponse.setRequestKey(UserResponse.RequestKey.CHECK_DOWNLINE);

                            responseLiveData.setValue(userResponse);
                        }
                    }
                });

    }

    public void addDownline(String mogawerCode) {
        application.getApiUserService().addDownline(PrefHelper.getString(PrefKey.TOKEN), mogawerCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UserResponse response) {
                        UserResponse userResponse = new UserResponse();
                        if (response.isSuccess()) {
                            userResponse.setReturnValue(response.getReturnValue());
                            userResponse.setMessage(response.getMessage());
                            userResponse.setRequestKey(UserResponse.RequestKey.ADD_DOWNLINE);

                            responseLiveData.setValue(userResponse);
                        } else {
                            userResponse.setReturnValue(response.getReturnValue());
                            userResponse.setMessage(response.getMessage());
                            userResponse.setRequestKey(UserResponse.RequestKey.ADD_DOWNLINE_FAILED);

                            responseLiveData.setValue(userResponse);
                        }
                    }
                });

    }

    public void getDownline(Integer page, Integer offset) {
        GetDownlineRequest request = new GetDownlineRequest();
        request.setPage(page);
        request.setOffset(offset);

        application.getApiUserService().getDownline(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DownlinerResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(DownlinerResponse response) {
                        DownlinerResponse downlinerResponse = new DownlinerResponse();
                        UserResponse userResponse = new UserResponse();
                        if (response.isSuccess()) {
                            userResponse.setReturnValue(response.getReturnValue());
                            userResponse.setMessage(response.getMessage());
                            userResponse.setRequestKey(UserResponse.RequestKey.GET_DOWNLINE);
                            userResponse.setRow_count(response.getRow_count());
                            userResponse.setPage_count(response.getPage_count());
                            userResponse.setOffset(response.getOffset());
                            userResponse.setMyDownline(response.getMyDownline());

                            responseLiveData.setValue(userResponse);
                        } else {
                            userResponse.setReturnValue(response.getReturnValue());
                            userResponse.setMessage(response.getMessage());
                            userResponse.setRequestKey(UserResponse.RequestKey.REQUEST_FAILED);

                            responseLiveData.setValue(userResponse);
                        }
                    }
                });

    }

    public void reportError(Integer versionNumber, String error, String brand, String device, String model, String idDevice, String product, String sdk, String release, String incremental, String percentAvailableRam, String availableRam, String operatorName, String activityName, String signalStrength, String battery, String availableStorage) {
        ReportErrorRequest request = new ReportErrorRequest();
        request.setVersionNumber(versionNumber);
        request.setError(error);
        request.setBrand(brand);
        request.setDevice(device);
        request.setModel(model);
        request.setIdDevice(idDevice);
        request.setProduct(product);
        request.setSdk(sdk);
        request.setRelease(release);
        request.setIncremental(incremental);
        request.setPercentAvailableRam(percentAvailableRam);
        request.setAvailableRam(availableRam);
        request.setOperatorName(operatorName);
        request.setActivityName(activityName);
        request.setSignalStrength(signalStrength);
        request.setBattery(battery);
        request.setAvailableStorage(availableStorage);

        application.getApiUserService().reportError(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GenericResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(GenericResponse response) {
                        UserResponse userResponse = new UserResponse();
                        if (response.isSuccess()) {
                            userResponse.setReturnValue(response.getReturnValue());
                            userResponse.setMessage(response.getMessage());
                            userResponse.setRequestKey(UserResponse.RequestKey.REQUEST_SUCCESS);

                            responseLiveData.setValue(userResponse);
                        } else {
                            userResponse.setReturnValue(response.getReturnValue());
                            userResponse.setMessage(response.getMessage());
                            userResponse.setRequestKey(UserResponse.RequestKey.REQUEST_FAILED);

                            responseLiveData.setValue(userResponse);
                        }
                    }
                });

    }

    public void misscallOTP(String phoneNumber, int gateway) {

        String contentType = "application/json";
        String apiKey = "c0748a6b483b0046591feb637c3517be";

        CitcalMisscallRequest request = new CitcalMisscallRequest();
        request.setPhoneNumber(phoneNumber);
        request.setGateway(gateway);

        application.getApiUserCitcallService().misscallOTP(contentType, apiKey, request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CitcallResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CitcallResponse citcallResponse) {
                        System.out.println("citcall 1 " + citcallResponse.getToken());
                        System.out.println("citcall 2 " + citcallResponse.getMsisdn());
                        System.out.println("citcall 3 " + citcallResponse.getTrxid());
                    }
                });

    }


    //GET CERTIFICATE LIST
    private MutableLiveData<CertificateListResponse> certificateListResponseLiveData = new MutableLiveData<>();
    public MutableLiveData<CertificateListResponse> getCertificateListResponseLiveData() {
        return certificateListResponseLiveData;
    }
    public LiveData<CertificateListResponse> listCertificate() {
        application.getApiUserService().listCertificate(PrefHelper.getString(PrefKey.TOKEN))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CertificateListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                        CertificateListResponse dataResponse = new CertificateListResponse();
                        dataResponse.setMessage(ERROR_MESSAGE_SERVER);
                        dataResponse.setReturnValue(ERROR_CODE);
                        certificateListResponseLiveData.setValue(dataResponse);
                    }

                    @Override
                    public void onNext(CertificateListResponse genericResponse) {
                        if (genericResponse.isSuccess()) {
                            CertificateListResponse dataResponse = new CertificateListResponse();
                            dataResponse.setMessage(genericResponse.getMessage());
                            dataResponse.setReturnValue(genericResponse.getReturnValue());
                            dataResponse.setObject(genericResponse.getObject());

                            certificateListResponseLiveData.setValue(dataResponse);
                        } else {
                            CertificateListResponse dataResponse = new CertificateListResponse();
                            dataResponse.setMessage(genericResponse.getMessage());
                            dataResponse.setReturnValue(genericResponse.getReturnValue());

                            certificateListResponseLiveData.setValue(dataResponse);
                        }
                    }
                });
        return certificateListResponseLiveData;
    }


    public LiveData<CertificateListResponse> applyCertificate(String uuidCertificate) {
        application.getApiUserService().applyCertificate(PrefHelper.getString(PrefKey.TOKEN), uuidCertificate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GenericResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                        CertificateListResponse dataResponse = new CertificateListResponse();
                        dataResponse.setMessage(ERROR_MESSAGE_SERVER);
                        dataResponse.setReturnValue(ERROR_CODE);
                        certificateListResponseLiveData.setValue(dataResponse);
                    }

                    @Override
                    public void onNext(GenericResponse genericResponse) {
                        if (genericResponse.isSuccess()) {
                            CertificateListResponse dataResponse = new CertificateListResponse();
                            dataResponse.setMessage(genericResponse.getMessage());
                            dataResponse.setReturnValue(genericResponse.getReturnValue());
                            certificateListResponseLiveData.setValue(dataResponse);
                        } else {
                            CertificateListResponse dataResponse = new CertificateListResponse();
                            dataResponse.setMessage(genericResponse.getMessage());
                            dataResponse.setReturnValue(genericResponse.getReturnValue());

                            certificateListResponseLiveData.setValue(dataResponse);
                        }
                    }
                });
        return certificateListResponseLiveData;
    }


    //GET CERTIFICATE COUNT
    private MutableLiveData<CertificateCountResponse> certificateCountResponseLiveData = new MutableLiveData<>();
    public MutableLiveData<CertificateCountResponse> getCertificateCountResponseLiveData() {
        return certificateCountResponseLiveData;
    }
    public LiveData<CertificateCountResponse> countCertificate() {
        application.getApiUserService().countCertificate(PrefHelper.getString(PrefKey.TOKEN))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CertificateCountResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                        CertificateCountResponse dataResponse = new CertificateCountResponse();
                        dataResponse.setMessage(ERROR_MESSAGE_SERVER);
                        dataResponse.setReturnValue(ERROR_CODE);
                        certificateCountResponseLiveData.setValue(dataResponse);
                    }

                    @Override
                    public void onNext(CertificateCountResponse genericResponse) {
                        if (genericResponse.isSuccess()) {
                            CertificateCountResponse dataResponse = new CertificateCountResponse();
                            dataResponse.setMessage(genericResponse.getMessage());
                            dataResponse.setReturnValue(genericResponse.getReturnValue());
                            dataResponse.setObject(genericResponse.getObject());

                            certificateCountResponseLiveData.setValue(dataResponse);
                        } else {
                            CertificateCountResponse dataResponse = new CertificateCountResponse();
                            dataResponse.setMessage(genericResponse.getMessage());
                            dataResponse.setReturnValue(genericResponse.getReturnValue());

                            certificateCountResponseLiveData.setValue(dataResponse);
                        }
                    }
                });
        return certificateCountResponseLiveData;
    }



    //GET SUPPLIER DASHBOARD COUNT
    private MutableLiveData<SupplierDashboardCountResponse> supplierDashboardCountResponseLiveData = new MutableLiveData<>();
    public MutableLiveData<SupplierDashboardCountResponse> getSupplierDashboardCountResponseLiveData() {
        return supplierDashboardCountResponseLiveData;
    }
    public LiveData<SupplierDashboardCountResponse> countDashboardSupplier() {
        application.getApiUserService().countDashboardSupplier(PrefHelper.getString(PrefKey.TOKEN))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SupplierDashboardCountResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                        SupplierDashboardCountResponse dataResponse = new SupplierDashboardCountResponse();
                        dataResponse.setMessage(ERROR_MESSAGE_SERVER);
                        dataResponse.setReturnValue(ERROR_CODE);
                        supplierDashboardCountResponseLiveData.setValue(dataResponse);
                    }

                    @Override
                    public void onNext(SupplierDashboardCountResponse genericResponse) {
                        if (genericResponse.isSuccess()) {
                            SupplierDashboardCountResponse dataResponse = new SupplierDashboardCountResponse();
                            dataResponse.setMessage(genericResponse.getMessage());
                            dataResponse.setReturnValue(genericResponse.getReturnValue());
                            dataResponse.setObject(genericResponse.getObject());

                            supplierDashboardCountResponseLiveData.setValue(dataResponse);
                        } else {
                            SupplierDashboardCountResponse dataResponse = new SupplierDashboardCountResponse();
                            dataResponse.setMessage(genericResponse.getMessage());
                            dataResponse.setReturnValue(genericResponse.getReturnValue());

                            supplierDashboardCountResponseLiveData.setValue(dataResponse);
                        }
                    }
                });
        return supplierDashboardCountResponseLiveData;
    }


    public void saveUser(User user) {
        System.out.println("saveUser " + userDao);
        System.out.println(user.getEmail());
        System.out.println(user.getFullName());
        System.out.println(user.getCode());
        new SaveAsyncTask(userDao).execute(user);
    }

    public void updateUser(User user) {
        new UpdateAsyncTask(userDao).execute(user);
//        new UpdateSpesificAsyncTask(userDao).execute(user);
    }

    public void deleteUser() {
        new DeleteAsyncTask(userDao).execute();
    }

    private static class SaveAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao userDao;

        private SaveAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.save(users[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao userDao;

        private UpdateAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }

//    private static class UpdateSpesificAsyncTask extends AsyncTask<User, Void, Void> {
//
//        private UserDao userDao;
//
//        private UpdateSpesificAsyncTask(UserDao userDao) {
//            this.userDao = userDao;
//        }
//
//        @Override
//        protected Void doInBackground(User... users) {
//            userDao.updateUser(users[0]);
//            return null;
//        }
//    }

    private static class DeleteAsyncTask extends AsyncTask<Void, Void, Void> {

        private UserDao userDao;

        private DeleteAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAll();
            return null;
        }
    }

    public LiveData<List<Notification>> getNotificationList() {

        return notificationLiveData;

    }

    public void saveNotification(Notification notification) {
        System.out.println("Dfdsgdfdfsvdf " + notificationDao);
        if (notificationDao == null)
            notificationDao = application.getDatabase().notificationDao();

        new SaveNotificationAsyncTask(notificationDao).execute(notification);
    }

    public void deleteNotification() {
        new DeleteNotificationAsyncTask(notificationDao).execute();
    }

    private static class SaveNotificationAsyncTask extends AsyncTask<Notification, Void, Void> {

        private NotificationDao notificationDao;

        private SaveNotificationAsyncTask(NotificationDao notificationDao) {
            this.notificationDao = notificationDao;
        }

        @Override
        protected Void doInBackground(Notification... notifications) {

            System.out.println("ndsfaidsasmads " + notificationDao);
            notificationDao.save(notifications[0]);
            return null;
        }
    }

    private static class DeleteNotificationAsyncTask extends AsyncTask<Void, Void, Void> {

        private NotificationDao notificationDao;

        private DeleteNotificationAsyncTask(NotificationDao notificationDao) {
            this.notificationDao = notificationDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            notificationDao.deleteAll();
            return null;
        }
    }

}