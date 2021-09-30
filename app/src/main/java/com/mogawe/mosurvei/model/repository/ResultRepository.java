package com.mogawe.mosurvei.model.repository;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.mogawe.mosurvei.MoSurveiApplication;
import com.mogawe.mosurvei.model.db.dao.ResultDao;
import com.mogawe.mosurvei.model.db.dao.ResultListDao;
import com.mogawe.mosurvei.model.db.entity.Result;
import com.mogawe.mosurvei.model.db.entity.ResultFact;
import com.mogawe.mosurvei.model.db.entity.ResultList;
import com.mogawe.mosurvei.model.network.request.ProgressRequestBody;
import com.mogawe.mosurvei.model.network.request.section.SectionSubmitRequest;
import com.mogawe.mosurvei.model.network.response.section.SectionSourceResponse;
import com.mogawe.mosurvei.model.store.PrefHelper;
import com.mogawe.mosurvei.model.store.PrefKey;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ResultRepository implements ProgressRequestBody.UploadCallbacks {

    private static final String ERROR_CODE = "001";
    private static final String ERROR_MESSAGE_SERVER = "Gagal terhubung ke server, nih.\nCoba cek koneksi internetmu dan coba lagi, ya!";
    private static final String ERROR_MESSAGE_INTERNET = "Coba cek koneksi internetmu, ya!";

    private MoSurveiApplication application;
    private MutableLiveData<SectionSourceResponse> responseLiveData = new MutableLiveData<>();
    private LiveData<List<Result>> resultLiveData;
    private LiveData<List<ResultList>> allResultListLiveData;
    private LiveData<ResultList> resultListLiveData;
    private ResultDao resultDao;
    private ResultListDao resultListDao;
    private Integer uploadedProgress = 0;
    private Integer uploadedTotal = 0;

    private Result resultVideosRepo;
    private Result resultAudiosRepo;

    TaskRepository taskRepository;

    public ResultRepository(MoSurveiApplication application) {
        this.application = application;
        if (application != null) {
            if (application.getDatabase() != null) {
                resultDao = application.getDatabase().resultDao();
                resultListDao = application.getDatabase().resultListDao();
                allResultListLiveData = resultListDao.findAll();
                resultLiveData = resultDao.findAll();
            }
        }
    }

    public MutableLiveData<SectionSourceResponse> getResponseLiveData() {
        return responseLiveData;
    }

    public LiveData<List<Result>> getResultLiveData() {

        if (resultLiveData.getValue() == null) {
//            if (PrefHelper.hasString(PrefKey.TOKEN))
//                getFromServer();
        }

        return resultLiveData;

    }

//    public void getFromServer() {
//        MyTaskRequest request = new MyTaskRequest();
//        application.getApiTaskService().getTask(PrefHelper.getString(PrefKey.TOKEN))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<MyTaskResponse>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onNext(MyTaskResponse myTaskResponse) {
//                        if (myTaskResponse.isSuccess()) {
////                            saveToDatabase(myTaskResponse.getTask(), currentTask);
//                        }
//                    }
//                });
//    }

    public LiveData<List<ResultList>> getAllResultList() {
        return allResultListLiveData;
    }

    public LiveData<ResultList> getResultListByIdAndUuidSession(Integer idJob, String uuidSession) {

        resultListLiveData = resultListDao.findResultByIdJobAndUuidSession(idJob, uuidSession);
        System.out.println("ADA RESULT " + resultListLiveData.getValue());
        return resultListLiveData;
    }

    public void saveResultListToDatabase(ResultList resultList) {
        new SaveAsyncTaskResultList(resultListDao).execute(resultList);
    }

    public void deleteResultList(ResultList resultList, Integer idJob, String uuidSession) {
        new DeleteAsyncTaskResultList(resultListDao, idJob, uuidSession).execute(resultList);
    }

    public void submitResult(Result result, Result resultImages, String uuidSession, Result resultVideos, Double latitude, Double longitude, Result resultAudios) {
        System.out.println("OUT >> SUBMIT 11");
        SectionSubmitRequest request = new SectionSubmitRequest();
        request.setIdTask(result.getId_task());
        request.setUuidSession(uuidSession);

        request.setLatitude(latitude);
        request.setLongitude(longitude);

        System.out.println("DOUBLE LARIATO " + request.getLatitude());
        System.out.println("DOUBLE LONGIATO " + request.getLongitude());

        application.getApiTaskService().submitResult(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SectionSourceResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
//                        activity.dismissProgressDialog();
//                        activity.showNeutralCostumDialog("Gagal", "Upload Gagal", false);
                        SectionSourceResponse sectionSourceResponse = new SectionSourceResponse();
                        sectionSourceResponse.setRequestKey(SectionSourceResponse.RequestKey.UPLOAD_FAILED);
                        sectionSourceResponse.setMessage(ERROR_MESSAGE_SERVER);
                        sectionSourceResponse.setReturnValue("001");
                        responseLiveData.setValue(sectionSourceResponse);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(SectionSourceResponse sectionSourceResponse) {
                        if (sectionSourceResponse.isSuccess()) {
                            System.out.println("OUT >> SUBMIT 12");
                            sectionSourceResponse.setRequestKey(SectionSourceResponse.RequestKey.RESULT_CREATED);
                            sectionSourceResponse.setReturnValue(sectionSourceResponse.getReturnValue());

                            result.setId_result(sectionSourceResponse.getUuid());
                            resultImages.setId_result(sectionSourceResponse.getUuid());
                            resultVideos.setId_result(sectionSourceResponse.getUuid());
                            resultAudios.setId_result(sectionSourceResponse.getUuid());
                            sectionSourceResponse.setResult(result);
                            sectionSourceResponse.setResultImages(resultImages);
                            sectionSourceResponse.setResultVideos(resultVideos);
                            sectionSourceResponse.setResultAudios(resultAudios);
                            responseLiveData.setValue(sectionSourceResponse);
                        } else {
//                            activity.dismissProgressDialog();
                            sectionSourceResponse.setRequestKey(SectionSourceResponse.RequestKey.UPLOAD_FAILED);
                            sectionSourceResponse.setMessage(ERROR_MESSAGE_SERVER);
                            sectionSourceResponse.setReturnValue("001");
                            responseLiveData.setValue(sectionSourceResponse);
//                            activity.showNeutralCostumDialog("Gagal...", "terjadi kesalahan saat submit gawean", false);
                        }
                    }
                });
    }

    public void submitResultFact(Result result, Result resultImages, String uuidSession, Integer indexStart, Integer indexCount, Result resultVideos, Result resultAudios) {
        SectionSubmitRequest request = new SectionSubmitRequest();

        List<ResultFact> partialResultFact = new ArrayList<>();

        Integer indexTo = 0;
        if ((result.getResult_facts().size() - indexStart) < 10) {
            indexTo = indexStart + result.getResult_facts().size() - indexStart;
        } else {
            indexTo = indexStart + 10;
        }

        for (Integer i = indexStart; i < indexTo; i++) {
            if (result.getResult_facts().get(i) != null) {
                partialResultFact.add(result.getResult_facts().get(i));

                System.out.println("asdkoaskdowakijdsoij => " + new Gson().toJson(result.getResult_facts().get(i)));
            }
        }

        request.setIdResult(result.getId_result());
        request.setResults(partialResultFact);

        application.getApiTaskService().submitResultFact(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SectionSourceResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
//                        activity.dismissProgressDialog();
//                        activity.showNeutralCostumDialog("Gagal", "Upload Gagal", false);
                        SectionSourceResponse sectionSourceResponse = new SectionSourceResponse();
                        sectionSourceResponse.setRequestKey(SectionSourceResponse.RequestKey.UPLOAD_FAILED);
                        sectionSourceResponse.setMessage(ERROR_MESSAGE_SERVER);
                        sectionSourceResponse.setReturnValue("001");
                        responseLiveData.setValue(sectionSourceResponse);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(SectionSourceResponse sectionSourceResponse) {
                        if (sectionSourceResponse.isSuccess()) {
                            if ((indexCount - (indexStart + 1)) < 10) {
                                sectionSourceResponse.setRequestKey(SectionSourceResponse.RequestKey.RESULT_FACT_UPLOAD_DONE);
                            } else {
                                sectionSourceResponse.setRequestKey(SectionSourceResponse.RequestKey.RESULT_FACT_UPLOAD_ON_PROGRESS);
                            }

                            sectionSourceResponse.setReturnValue(sectionSourceResponse.getReturnValue());
                            sectionSourceResponse.setResult(result);
                            sectionSourceResponse.setResultImages(resultImages);
                            sectionSourceResponse.setResultVideos(resultVideos);
                            sectionSourceResponse.setResultAudios(resultAudios);
                            sectionSourceResponse.setIndexStart(indexStart);
                            sectionSourceResponse.setIndexCount(indexCount);
                            responseLiveData.setValue(sectionSourceResponse);
                        } else {
//                            activity.dismissProgressDialog();
                            sectionSourceResponse.setRequestKey(SectionSourceResponse.RequestKey.UPLOAD_FAILED);
                            sectionSourceResponse.setMessage(ERROR_MESSAGE_SERVER);
                            sectionSourceResponse.setReturnValue("001");
                            responseLiveData.setValue(sectionSourceResponse);
//                            activity.showNeutralCostumDialog("Gagal...", "terjadi kesalahan saat submit gawean", false);
                        }
                    }
                });
    }

    public void submitResultImages(Result resultImages, Integer imageStart, Integer imageCount, Result resultVideos, Integer videoStart, Integer videoCount, Result resultAudios, Integer audioStart, Integer audioCount, String timestamp, String geoLoc) {


        resultVideosRepo = resultVideos;
        resultAudiosRepo = resultAudios;

        RequestBody id_result_part = null;
        RequestBody id_fact_part = null;
        RequestBody id_item_part = null;
        MultipartBody.Part multiPart = null;
        String timeStampTaken = "";

        if (resultImages != null && imageStart != null && imageCount != null) {
            String imageName = resultImages.getId_task() + "_" + resultImages.getResult_facts().get(imageStart).getId_fact();
            id_result_part = RequestBody.create(MultipartBody.FORM, resultImages.getId_result());
            id_fact_part = RequestBody.create(MultipartBody.FORM, resultImages.getResult_facts().get(imageStart).getId_fact());
            id_item_part = RequestBody.create(MultipartBody.FORM, resultImages.getResult_facts().get(imageStart).getId_item() == null ? "" : resultImages.getResult_facts().get(imageStart).getId_item());
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), resultImages.getResult_facts().get(imageStart).getFile());

            System.out.println("OUT >> jancook "+resultImages.getResult_facts().get(imageStart).getFile().getAbsolutePath());
            System.out.println("OUT >> jancook "+resultImages.getResult_facts().get(imageStart).getFile());

            timeStampTaken = resultImages.getResult_facts().get(imageStart).getTimestampTaken();
            multiPart = MultipartBody.Part.createFormData("files", imageName, requestBody);

        } else if (resultVideos != null && videoStart != null && videoCount != null) {
            System.out.println("OUT >> Videos");
            String videoName = resultVideos.getId_task() + "_" + resultVideos.getResult_facts().get(videoStart).getId_fact();
            id_result_part = RequestBody.create(MultipartBody.FORM, resultVideos.getId_result());
            id_fact_part = RequestBody.create(MultipartBody.FORM, resultVideos.getResult_facts().get(videoStart).getId_fact());
            id_item_part = RequestBody.create(MultipartBody.FORM, resultVideos.getResult_facts().get(videoStart).getId_item() == null ? "" : resultVideos.getResult_facts().get(videoStart).getId_item());
            ProgressRequestBody requestBody = new ProgressRequestBody(resultVideos.getResult_facts().get(videoStart).getFile(), "video", this);

            timeStampTaken = resultVideos.getResult_facts().get(videoStart).getTimestampTaken();
            multiPart = MultipartBody.Part.createFormData("files", videoName, requestBody);
        } else if (resultAudios != null && audioStart != null && audioCount != null) {
            System.out.println("OUT >> Audios " + resultAudios);
            String audioName = resultAudios.getId_task() + "_" + resultAudios.getResult_facts().get(audioStart).getId_fact();
            id_result_part = RequestBody.create(MultipartBody.FORM, resultAudios.getId_result());
            id_fact_part = RequestBody.create(MultipartBody.FORM, resultAudios.getResult_facts().get(audioStart).getId_fact());
            id_item_part = RequestBody.create(MultipartBody.FORM, resultAudios.getResult_facts().get(audioStart).getId_item() == null ? "" : resultAudios.getResult_facts().get(audioStart).getId_item());
            ProgressRequestBody requestBody = new ProgressRequestBody(resultAudios.getResult_facts().get(audioStart).getFile(), "audio", this);

            timeStampTaken = resultAudios.getResult_facts().get(audioStart).getTimestampTaken();
            System.out.println("timaemdadas a " + timeStampTaken);
            multiPart = MultipartBody.Part.createFormData("files", audioName, requestBody);
        }

        application.getApiTaskService().uploadPicture(PrefHelper.getString(PrefKey.TOKEN), multiPart, id_result_part, id_fact_part, id_item_part, timeStampTaken, geoLoc)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SectionSourceResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        SectionSourceResponse sectionSourceResponse = new SectionSourceResponse();
                        sectionSourceResponse.setRequestKey(SectionSourceResponse.RequestKey.UPLOAD_FAILED);
                        sectionSourceResponse.setReturnValue("001");
                        sectionSourceResponse.setMessage(ERROR_MESSAGE_SERVER);
                        responseLiveData.setValue(sectionSourceResponse);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(SectionSourceResponse sectionSourceResponse) {
                        if (sectionSourceResponse.isSuccess()) {
                            if (resultImages != null && imageStart != null && imageCount != null) {
                                sectionSourceResponse.setUuid(resultImages.getId_result());
                                if ((imageStart + 1) < imageCount) {
                                    sectionSourceResponse.setRequestKey(SectionSourceResponse.RequestKey.RESULT_FACT_IMAGES_UPLOAD_ON_PROGRESS);
                                } else {
                                    sectionSourceResponse.setRequestKey(SectionSourceResponse.RequestKey.RESULT_FACT_IMAGES_UPLOAD_DONE);
                                }
                                sectionSourceResponse.setResultImages(resultImages);
                                sectionSourceResponse.setImageStart(imageStart);
                                sectionSourceResponse.setImageCount(imageCount);

                                sectionSourceResponse.setResultVideos(resultVideos);
                                sectionSourceResponse.setResultAudios(resultAudios);
                                responseLiveData.setValue(sectionSourceResponse);
                            } else if (resultAudios != null && audioStart != null && audioCount != null) {
                                System.out.println("OUT >> Audios");
                                sectionSourceResponse.setUuid(resultAudios.getId_result());
                                if ((audioStart + 1) < audioCount) {
                                    sectionSourceResponse.setRequestKey(SectionSourceResponse.RequestKey.RESULT_FACT_AUDIOS_UPLOAD_ON_PROGRESS);
                                } else {
                                    sectionSourceResponse.setRequestKey(SectionSourceResponse.RequestKey.RESULT_FACT_AUDIOS_UPLOAD_DONE);
                                }
                                sectionSourceResponse.setResultAudios(resultAudios);
                                sectionSourceResponse.setAudioStart(audioStart);
                                sectionSourceResponse.setAudioCount(audioCount);

                                responseLiveData.setValue(sectionSourceResponse);
                            } else if (resultVideos != null && videoStart != null && videoCount != null) {
                                System.out.println("OUT >> Videos");
                                sectionSourceResponse.setUuid(resultVideos.getId_result());
                                if ((videoStart + 1) < videoCount) {
                                    sectionSourceResponse.setRequestKey(SectionSourceResponse.RequestKey.RESULT_FACT_VIDEOS_UPLOAD_ON_PROGRESS);
                                } else {
                                    sectionSourceResponse.setRequestKey(SectionSourceResponse.RequestKey.RESULT_FACT_VIDEOS_UPLOAD_DONE);
                                }
                                sectionSourceResponse.setResultVideos(resultVideos);
                                sectionSourceResponse.setVideoStart(videoStart);
                                sectionSourceResponse.setVideoCount(videoCount);

                                sectionSourceResponse.setResultAudios(resultAudios);

                                responseLiveData.setValue(sectionSourceResponse);
                            }

                        } else {
//                            activity.showNeutralCostumDialog("gagal", sectionSourceResponse.getMessage(),false);
                            sectionSourceResponse.setRequestKey(SectionSourceResponse.RequestKey.UPLOAD_FAILED);
                            sectionSourceResponse.setMessage(ERROR_MESSAGE_SERVER);
                            sectionSourceResponse.setReturnValue("001");
                            responseLiveData.setValue(sectionSourceResponse);
                        }
                    }
                });
    }

    private void clearFromDatabase(Result result) {
        new ResultRepository.DeleteAsyncTask(resultDao).execute(result);
    }

    public void clearResults() {
        resultDao.deleteAll();
    }

    //============================================================
    @Override
    public void onProgressUpdate(int percentage) {
        System.out.println("PERCENTAGE : " + percentage);
        if (percentage >= 0) {
            if (resultVideosRepo != null) {
                SectionSourceResponse sectionSourceResponse = new SectionSourceResponse();
                sectionSourceResponse.setVideoProgressPercentage(percentage);
                sectionSourceResponse.setReturnValue("000");
                sectionSourceResponse.setMessage("berhasil");
                sectionSourceResponse.setResultVideos(resultVideosRepo);
                sectionSourceResponse.setRequestKey(SectionSourceResponse.RequestKey.RESULT_FACT_VIDEOS_UPLOAD_ON_UPDATE_PERCENTAGE);
                responseLiveData.setValue(sectionSourceResponse);
            } else {
                SectionSourceResponse sectionSourceResponse = new SectionSourceResponse();
                sectionSourceResponse.setVideoProgressPercentage(percentage);
                sectionSourceResponse.setReturnValue("000");
                sectionSourceResponse.setMessage("berhasil");
                sectionSourceResponse.setResultAudios(resultAudiosRepo);
                sectionSourceResponse.setRequestKey(SectionSourceResponse.RequestKey.RESULT_FACT_VIDEOS_UPLOAD_ON_UPDATE_PERCENTAGE);
                responseLiveData.setValue(sectionSourceResponse);
            }
        }

    }

    @Override
    public void onError() {

    }

    @Override
    public void onFinish() {
//        sectionFormActivity.dismissProgressDialog();
    }

    //============================================================
    private static class DeleteAsyncTask extends AsyncTask<Result, Void, Void> {

        private ResultDao resultDao;

        private DeleteAsyncTask(ResultDao resultDao) {
            this.resultDao = resultDao;
        }

        @Override
        protected Void doInBackground(Result... results) {
            resultDao.delete(results[0]);
            return null;
        }
    }

    private static class SaveAsyncTaskResultList extends AsyncTask<ResultList, Void, Void> {

        private ResultListDao resultListDao;

        private SaveAsyncTaskResultList(ResultListDao resultListDao) {
            this.resultListDao = resultListDao;
        }

        @Override
        protected Void doInBackground(ResultList... resultLists) {
            if (resultLists != null) {
                resultListDao.save(resultLists[0]);
            }
            return null;
        }
    }

    private static class DeleteAsyncTaskResultList extends AsyncTask<ResultList, Void, Void> {

        private ResultListDao resultListDao;
        private Integer idJob;
        private String uuidSession;

        private DeleteAsyncTaskResultList(ResultListDao resultListDao, Integer idJob, String uuidSession) {
            this.resultListDao = resultListDao;
            this.idJob = idJob;
            this.uuidSession = uuidSession;
        }

        @Override
        protected Void doInBackground(ResultList... resultLists) {
            if (resultLists != null) {
                resultListDao.deleteByIdJobAndUuidSession(idJob, uuidSession);
            }
            return null;
        }
    }

//    private void saveToDatabase(List<Task> task, List<Task> currentTask) {
//        Collection colCurrentTask = new ArrayList(Arrays.asList(currentTask));
//        Collection colServerTask = new ArrayList(Arrays.asList(task));
//        if (currentTask == null) {
//            new TaskRepository.SaveAsyncTask(taskDao).execute(task);
//        } else {
//            if (task.size() != currentTask.size()) {
//                System.out.println("SaveAsyncTask");
//                new TaskRepository.SaveAsyncTask(taskDao).execute(task);
//            }
//        }
//    }
}
