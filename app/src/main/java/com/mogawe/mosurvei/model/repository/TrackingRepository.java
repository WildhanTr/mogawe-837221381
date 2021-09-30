package com.mogawe.mosurvei.model.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.mogawe.mosurvei.MoSurveiApplication;
import com.mogawe.mosurvei.model.bean.location.Coordinate;
import com.mogawe.mosurvei.model.db.dao.TrackingDao;
import com.mogawe.mosurvei.model.db.entity.Tracking;
import com.mogawe.mosurvei.model.network.request.user.NearJobRequest;
import com.mogawe.mosurvei.model.network.request.user.UpdateTrackingRequest;
import com.mogawe.mosurvei.model.network.response.GenericResponse;
import com.mogawe.mosurvei.model.store.PrefHelper;
import com.mogawe.mosurvei.model.store.PrefKey;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TrackingRepository {


    private static final String ERROR_CODE = "001";
    private static final String ERROR_MESSAGE_SERVER = "Gagal terhubung ke server, nih.\nCoba cek koneksi internetmu dan coba lagi, ya!";
    private static final String ERROR_MESSAGE_INTERNET = "Coba cek koneksi internetmu, ya!";

    private MoSurveiApplication application;
    private MutableLiveData<GenericResponse> responseLiveData = new MutableLiveData<>();
    private LiveData<List<Tracking>> trackingListLiveData;
    private TrackingDao trackingDao;


    public TrackingRepository(MoSurveiApplication application) {
        this.application = application;

        if (application != null) {
            if (application.getDatabase() != null) {
                trackingDao = application.getDatabase().trackingDao();
                trackingListLiveData = trackingDao.findAll();
            }
        }

    }

    public MutableLiveData<GenericResponse> getResponseLiveData() {
        return responseLiveData;
    }

    public LiveData<List<Tracking>> getTrackingListLiveData() {
        return trackingListLiveData;
    }

    public LiveData<List<Tracking>> save(Tracking tracking) {

        sendToServer(tracking);
        return trackingListLiveData;

    }


    private void sendRequestNotification(long id_mogawers) {

        NearJobRequest nearJobRequest = new NearJobRequest();
        nearJobRequest.setId_mogawers(id_mogawers);

        Gson gson = new Gson();
        if (PrefHelper.getString(PrefKey.MY_LOCATION) != null && !PrefHelper.getString(PrefKey.MY_LOCATION).isEmpty()) {
            Coordinate myCoordinate = gson.fromJson(PrefHelper.getString(PrefKey.MY_LOCATION), Coordinate.class);
            nearJobRequest.setLat(myCoordinate.getLat());
            nearJobRequest.setLng(myCoordinate.getLng());
        }

        application.getApiUserService().sendNearJobNotification(PrefHelper.getString(PrefKey.TOKEN), nearJobRequest)
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

                    }
                });
    }

    private void sendToServer(Tracking tracking) {

        UpdateTrackingRequest request = new UpdateTrackingRequest(tracking.getLatitude(), tracking.getLongitude(), tracking.getVersionNumber(), tracking.getBrand(), tracking.getDevice(), tracking.getModel(), tracking.getIdDevice(), tracking.getProduct(), tracking.getSdk(), tracking.getRelease(), tracking.getIncremental(), tracking.getPercentAvailableRam(), tracking.getAvailableRam(), tracking.getOperatorName());
        application.getApiUserService().sendTrack(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GenericResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        tracking.setSynced(false);
                        saveToDatabase(tracking);
                    }

                    @Override
                    public void onNext(GenericResponse response) {
                        if (response.isSuccess()) {
                            tracking.setSynced(true);
                        } else {
                            tracking.setSynced(false);
                        }
                        saveToDatabase(tracking);


                    }
                });
    }

    private void saveToDatabase(Tracking tracking) {
        new SaveAsyncTask(trackingDao).execute(tracking);
    }

    private static class SaveAsyncTask extends AsyncTask<Tracking, Void, Void> {

        private TrackingDao trackingDao;

        private SaveAsyncTask(TrackingDao trackingDao) {
            this.trackingDao = trackingDao;
        }

        @Override
        protected Void doInBackground(Tracking... trackings) {
            trackingDao.save(trackings[0]);
            return null;
        }
    }
}
