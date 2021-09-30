package com.mogawe.mosurvei.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mogawe.mosurvei.MoSurveiApplication;
import com.mogawe.mosurvei.model.db.entity.Deals;
import com.mogawe.mosurvei.model.network.response.point.DealsResponse;
import com.mogawe.mosurvei.model.repository.PointRepository;

import java.util.List;

public class PointViewModel extends AndroidViewModel {
    private PointRepository pointRepository;
    private LiveData<DealsResponse> responseLiveData;
    private LiveData<List<Deals>> dealsLiveData;

    public PointViewModel(@NonNull Application application) {
        super(application);
        pointRepository = new PointRepository((MoSurveiApplication) application);
        responseLiveData = pointRepository.getResponseLiveData();
//        dealsLiveData = pointRepository.getDealsLiveData();
    }

    public void getDeals() {
        responseLiveData = pointRepository.getDeals();
    }

    public void getMyVoucher(){
        pointRepository.getMyVoucher();
    }

    public void getUrlOrderUV(String uuid){
        pointRepository.getUrlOrderUV(uuid);
    }

    public void redeemVoucher(Deals deals, Integer count){
        pointRepository.redeemVoucher(deals, count);
    }

    public LiveData<DealsResponse> getResponseLiveData() {
        return responseLiveData;
    }

    public LiveData<List<Deals>> getDealsLiveData() {
        return dealsLiveData;
    }

}
