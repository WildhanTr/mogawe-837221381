package com.mogawe.mosurvei.viewmodel;

import android.app.Application;

import com.mogawe.mosurvei.MoSurveiApplication;
import com.mogawe.mosurvei.model.db.entity.Tracking;
import com.mogawe.mosurvei.model.repository.TrackingRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TrackingViewModel extends AndroidViewModel {


    private TrackingRepository trackingRepository;
    private LiveData<List<Tracking>> trackingListLiveData;

    public TrackingViewModel(@NonNull Application application) {
        super(application);
        trackingRepository = new TrackingRepository((MoSurveiApplication) application);
        trackingListLiveData = trackingRepository.getTrackingListLiveData();
    }

    public void save(Tracking tracking) {
        trackingListLiveData = trackingRepository.save(tracking);
    }


}
