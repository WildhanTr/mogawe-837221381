package com.mogawe.mosurvei.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mogawe.mosurvei.MoSurveiApplication;
import com.mogawe.mosurvei.model.db.entity.Result;
import com.mogawe.mosurvei.model.db.entity.ResultList;
import com.mogawe.mosurvei.model.network.response.section.SectionSourceResponse;
import com.mogawe.mosurvei.model.repository.ResultRepository;
import com.mogawe.mosurvei.view.BaseActivity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ResultViewModel extends AndroidViewModel {
    private BaseActivity activity;
    private MoSurveiApplication application;
    private ResultRepository resultRepository;
    private LiveData<SectionSourceResponse> responseLiveData;
    private LiveData<List<Result>> sectionSourceLiveData;
    private LiveData<List<ResultList>> allResultListLiveData;
    private LiveData<ResultList> resultListLiveData;
    private ExecutorService executorService;

    public ResultViewModel(@NonNull Application application) {
        super(application);
        resultRepository = new ResultRepository((MoSurveiApplication) application);
        responseLiveData = resultRepository.getResponseLiveData();
        sectionSourceLiveData = resultRepository.getResultLiveData();
        allResultListLiveData = resultRepository.getAllResultList();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<ResultList>> getAllResultList() {
        return allResultListLiveData;
    }


    public LiveData<ResultList> getResultListByIdAndUuidSession(Integer idJob, String uuidSession) {
        resultListLiveData = resultRepository.getResultListByIdAndUuidSession(idJob, uuidSession);
        return resultListLiveData;
    }

    public void saveResultListToDatabase(ResultList resultList) {
        resultRepository.saveResultListToDatabase(resultList);
    }

    public void deleteResultList(ResultList resultList, Integer idJob, String uuidSession) {
        resultRepository.deleteResultList(resultList, idJob, uuidSession);
    }

    public void submitResult(Result result, Result resultImages, String uuidSession, Result resultVideos, Double latitude, Double longitude, Result resultAudios) {
        System.out.println("OUT >> SUBMIT 10");
        resultRepository.submitResult(result, resultImages, uuidSession, resultVideos, latitude, longitude, resultAudios);
    }

    public void submitResultFact(Result result, Result resultImages, String uuidSession, Integer indexStart, Integer indexCount, Result resultVideos, Result resultAudios) {
        resultRepository.submitResultFact(result, resultImages, uuidSession, indexStart, indexCount, resultVideos, resultAudios);
    }

    public void submitResultImages(Result resultImages, Integer imageStart, Integer imageCount, Result resultVideos, Integer videoStart, Integer videoCount, Result resultAudios, Integer audioStart, Integer audioCount, String timeStamp, String geoLoc) {
        resultRepository.submitResultImages(resultImages, imageStart, imageCount, resultVideos, videoStart, videoCount, resultAudios, audioStart, audioCount, timeStamp, geoLoc);
    }

    public LiveData<SectionSourceResponse> getResponseLiveData() {
        return responseLiveData;
    }

//    public LiveData<SectionSourceResponse> get() {
//        return responseLiveData;
//    }

    public LiveData<List<Result>> getTask() {
        return sectionSourceLiveData;
    }

    public void clear() {
        resultRepository.clearResults();
    }

    public void setActivity(BaseActivity activity){
        this.activity = activity;
    }
}
