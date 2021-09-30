package com.mogawe.mosurvei.model.repository;

import android.location.Location;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.mogawe.mosurvei.MoSurveiApplication;
import com.mogawe.mosurvei.model.bean.Order;
import com.mogawe.mosurvei.model.bean.location.Coordinate;
import com.mogawe.mosurvei.model.db.dao.SectionDao;
import com.mogawe.mosurvei.model.db.dao.SectionSourceDao;
import com.mogawe.mosurvei.model.db.dao.TaskDao;
import com.mogawe.mosurvei.model.db.dao.TaskPendingDao;
import com.mogawe.mosurvei.model.db.dao.UserDao;
import com.mogawe.mosurvei.model.db.entity.Catalog;
import com.mogawe.mosurvei.model.db.entity.Item;
import com.mogawe.mosurvei.model.db.entity.PendingTask;
import com.mogawe.mosurvei.model.db.entity.Section;
import com.mogawe.mosurvei.model.db.entity.Supplier;
import com.mogawe.mosurvei.model.db.entity.Task;
import com.mogawe.mosurvei.model.db.entity.TaskToday;
import com.mogawe.mosurvei.model.db.entity.User;
import com.mogawe.mosurvei.model.network.request.FieldTaskToDoRequest;
import com.mogawe.mosurvei.model.network.request.PostStatusRequest;
import com.mogawe.mosurvei.model.network.request.task.GetAllCategoriesRequest;
import com.mogawe.mosurvei.model.network.request.task.GetContentByCategoriesRequest;
import com.mogawe.mosurvei.model.network.request.task.GetJobCategoryRequest;
import com.mogawe.mosurvei.model.network.request.task.MyTaskRequest;
import com.mogawe.mosurvei.model.network.request.task.SJROfferRequest;
import com.mogawe.mosurvei.model.network.request.user.SaveChatRoomRequest;
import com.mogawe.mosurvei.model.network.response.CatalogObjResponse;
import com.mogawe.mosurvei.model.network.response.GaweanInstantDetailResponse;
import com.mogawe.mosurvei.model.network.response.GaweanInstantResponse;
import com.mogawe.mosurvei.model.network.response.GaweanResponse;
import com.mogawe.mosurvei.model.network.response.GaweanResponseV2;
import com.mogawe.mosurvei.model.network.response.GawenInformationResponse;
import com.mogawe.mosurvei.model.network.response.GenericResponse;
import com.mogawe.mosurvei.model.network.response.GeoLocationSpotCheckResponse;
import com.mogawe.mosurvei.model.network.response.ItemResponse;
import com.mogawe.mosurvei.model.network.response.JobAttachmentResponse;
import com.mogawe.mosurvei.model.network.response.JobRefCodeResponse;
import com.mogawe.mosurvei.model.network.response.LocationResponse;
import com.mogawe.mosurvei.model.network.response.OrderResponse;
import com.mogawe.mosurvei.model.network.response.ProductCategoryListResponse;
import com.mogawe.mosurvei.model.network.response.ProductResponse;
import com.mogawe.mosurvei.model.network.response.TaskResponse;
import com.mogawe.mosurvei.model.network.response.TaskToDoResponse;
import com.mogawe.mosurvei.model.network.response.UserResponse;
import com.mogawe.mosurvei.model.network.response.VerifyChatRoomResponse;
import com.mogawe.mosurvei.model.network.response.section.SectionSourceResponse;
import com.mogawe.mosurvei.model.network.response.shipment.CityListResponse;
import com.mogawe.mosurvei.model.network.response.shipment.LogisticListResponse;
import com.mogawe.mosurvei.model.network.response.shipment.ProvinceListResponse;
import com.mogawe.mosurvei.model.network.response.task.GetAdsResponse;
import com.mogawe.mosurvei.model.network.response.task.HistoryTaskResponse;
import com.mogawe.mosurvei.model.network.response.task.HomeContentsResponse;
import com.mogawe.mosurvei.model.network.response.task.MyTaskResponse;
import com.mogawe.mosurvei.model.network.response.task.MyTaskTodayResponse;
import com.mogawe.mosurvei.model.network.response.task.StartEndTaskResponse;
import com.mogawe.mosurvei.model.network.response.task.SupplierResponse;
import com.mogawe.mosurvei.model.network.response.task.TaskPolylineResponse;
import com.mogawe.mosurvei.model.network.response.timeline.TimelineActivityResponse;
import com.mogawe.mosurvei.model.store.PrefHelper;
import com.mogawe.mosurvei.model.store.PrefKey;
import com.mogawe.mosurvei.util.Constant;
import com.mogawe.mosurvei.view.BaseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TaskRepository {

    private static final String ERROR_CODE = "001";
    private static final String ERROR_MESSAGE_SERVER = "Gagal terhubung ke server, nih.\nCoba cek koneksi internetmu dan coba lagi, ya!";
    private static final String ERROR_MESSAGE_INTERNET = "Coba cek koneksi internetmu, ya!";

    private MoSurveiApplication application;
    private MutableLiveData<MyTaskResponse> responseLiveData = new MutableLiveData<>();
    private LiveData<List<Task>> taskLiveData;
    private LiveData<List<Section>> sectionLiveData;
    private LiveData<List<SectionSourceResponse>> sectionAllSourceResponseLiveData;
    private LiveData<SectionSourceResponse> sectionSourceResponseLiveData;
    private LiveData<List<PendingTask>> taskPendingLiveDatas;
    private LiveData<Task> taskPendingLiveData;
    private MutableLiveData<TaskResponse> taskResponseLiveData = new MutableLiveData<>();
    private TaskDao taskDao;
    private TaskPendingDao taskPendingDao;
    private UserDao userDao;
    private SectionDao sectionDao;
    private SectionSourceDao sectionSourceDao;

    private UserRepository userRepository = new UserRepository((MoSurveiApplication) application);


    public TaskRepository(MoSurveiApplication application) {
        this.application = application;
        if (application != null) {
            if (application.getDatabase() != null) {
                userDao = application.getDatabase().userDao();
                taskDao = application.getDatabase().taskDao();
                taskPendingDao = application.getDatabase().taskPendingDao();
                sectionDao = application.getDatabase().sectionDao();
                sectionSourceDao = application.getDatabase().sectionSourceDao();
                taskLiveData = taskDao.findAll();

                taskPendingLiveDatas = taskPendingDao.findAll();
                sectionLiveData = sectionDao.findAll();

                sectionAllSourceResponseLiveData = sectionSourceDao.findAll();
            }
        }
    }

    public LiveData<List<Task>> getTaskLiveData() {
//        if (taskLiveData.getValue() == null) {
////            if (PrefHelper.hasString(PrefKey.TOKEN))
////            getFromServer(null);
//            Gson gson = new Gson();
////            if(PrefHelper.getString(PrefKey.MY_LOCATION) == null || PrefHelper.getString(PrefKey.MY_LOCATION).equals("")){
////                getAllGawean(0.0,0.0);
////            }else{
////                Coordinate myCoordinate = gson.fromJson(PrefHelper.getString(PrefKey.MY_LOCATION), Coordinate.class);
////                getAllGawean(myCoordinate.getLat(),myCoordinate.getLng());
////            }
//        }
        System.out.println("ADA TASK " + taskLiveData.getValue());
        return taskLiveData;
    }

    public LiveData<List<PendingTask>> getTaskPendingLiveDatas() {
//        if (taskLiveData.getValue() == null) {
////            if (PrefHelper.hasString(PrefKey.TOKEN))
////            getFromServer(null);
//            Gson gson = new Gson();
////            if(PrefHelper.getString(PrefKey.MY_LOCATION) == null || PrefHelper.getString(PrefKey.MY_LOCATION).equals("")){
////                getAllGawean(0.0,0.0);
////            }else{
////                Coordinate myCoordinate = gson.fromJson(PrefHelper.getString(PrefKey.MY_LOCATION), Coordinate.class);
////                getAllGawean(myCoordinate.getLat(),myCoordinate.getLng());
////            }
//        }
        System.out.println("ADA TASK " + taskPendingLiveDatas.getValue());
        return taskPendingLiveDatas;
    }

    public LiveData<SectionSourceResponse> getSectionSourceLiveDataByUuidSession(String uuidSession) {
//        if (taskLiveData.getValue() == null) {
////            if (PrefHelper.hasString(PrefKey.TOKEN))
////            getFromServer(null);
//            Gson gson = new Gson();
////            if(PrefHelper.getString(PrefKey.MY_LOCATION) == null || PrefHelper.getString(PrefKey.MY_LOCATION).equals("")){
////                getAllGawean(0.0,0.0);
////            }else{
////                Coordinate myCoordinate = gson.fromJson(PrefHelper.getString(PrefKey.MY_LOCATION), Coordinate.class);
////                getAllGawean(myCoordinate.getLat(),myCoordinate.getLng());
////            }
//        }
        sectionSourceResponseLiveData = sectionSourceDao.findSectionByUuidSession(uuidSession);
        System.out.println("ADA SECTION SOURCE " + sectionSourceResponseLiveData.getValue());
        return sectionSourceResponseLiveData;
    }

    public LiveData<SectionSourceResponse> getSectionSourceLiveDataById(Integer idSection) {
//        if (taskLiveData.getValue() == null) {
////            if (PrefHelper.hasString(PrefKey.TOKEN))
////            getFromServer(null);
//            Gson gson = new Gson();
////            if(PrefHelper.getString(PrefKey.MY_LOCATION) == null || PrefHelper.getString(PrefKey.MY_LOCATION).equals("")){
////                getAllGawean(0.0,0.0);
////            }else{
////                Coordinate myCoordinate = gson.fromJson(PrefHelper.getString(PrefKey.MY_LOCATION), Coordinate.class);
////                getAllGawean(myCoordinate.getLat(),myCoordinate.getLng());
////            }
//        }
        sectionSourceResponseLiveData = sectionSourceDao.findSectionById(idSection);
        System.out.println("ADA SECTION SOURCE " + sectionSourceResponseLiveData.getValue());
        return sectionSourceResponseLiveData;
    }

    public LiveData<SectionSourceResponse> getSectionSourceLiveData(Integer idJob) {
//        if (taskLiveData.getValue() == null) {
////            if (PrefHelper.hasString(PrefKey.TOKEN))
////            getFromServer(null);
//            Gson gson = new Gson();
////            if(PrefHelper.getString(PrefKey.MY_LOCATION) == null || PrefHelper.getString(PrefKey.MY_LOCATION).equals("")){
////                getAllGawean(0.0,0.0);
////            }else{
////                Coordinate myCoordinate = gson.fromJson(PrefHelper.getString(PrefKey.MY_LOCATION), Coordinate.class);
////                getAllGawean(myCoordinate.getLat(),myCoordinate.getLng());
////            }
//        }
        sectionSourceResponseLiveData = sectionSourceDao.findSectionByIdJob(idJob, false);
        System.out.println("ADA SECTION SOURCE " + sectionSourceResponseLiveData.getValue());
        return sectionSourceResponseLiveData;
    }

    public LiveData<SectionSourceResponse> getSectionSourceByIdTaskLiveData(String id_task, Integer jobId) {
//        if (taskLiveData.getValue() == null) {
////            if (PrefHelper.hasString(PrefKey.TOKEN))
////            getFromServer(null);
//            Gson gson = new Gson();
////            if(PrefHelper.getString(PrefKey.MY_LOCATION) == null || PrefHelper.getString(PrefKey.MY_LOCATION).equals("")){
////                getAllGawean(0.0,0.0);
////            }else{
////                Coordinate myCoordinate = gson.fromJson(PrefHelper.getString(PrefKey.MY_LOCATION), Coordinate.class);
////                getAllGawean(myCoordinate.getLat(),myCoordinate.getLng());
////            }
//        }
        sectionSourceResponseLiveData = sectionSourceDao.findSectionByIdTask(id_task, jobId, false);
        System.out.println("ADA SECTION SOURCE NYA " + sectionSourceResponseLiveData.getValue());
        return sectionSourceResponseLiveData;
    }

    public LiveData<List<SectionSourceResponse>> getAllSectionSourceLiveData() {
//        if (taskLiveData.getValue() == null) {
////            if (PrefHelper.hasString(PrefKey.TOKEN))
////            getFromServer(null);
//            Gson gson = new Gson();
////            if(PrefHelper.getString(PrefKey.MY_LOCATION) == null || PrefHelper.getString(PrefKey.MY_LOCATION).equals("")){
////                getAllGawean(0.0,0.0);
////            }else{
////                Coordinate myCoordinate = gson.fromJson(PrefHelper.getString(PrefKey.MY_LOCATION), Coordinate.class);
////                getAllGawean(myCoordinate.getLat(),myCoordinate.getLng());
////            }
//        }
        System.out.println("ADA SECTION " + taskLiveData.getValue());
        return sectionAllSourceResponseLiveData;
    }

    public LiveData<List<Section>> getSectionLiveData() {
//        if (taskLiveData.getValue() == null) {
////            if (PrefHelper.hasString(PrefKey.TOKEN))
////            getFromServer(null);
//            Gson gson = new Gson();
////            if(PrefHelper.getString(PrefKey.MY_LOCATION) == null || PrefHelper.getString(PrefKey.MY_LOCATION).equals("")){
////                getAllGawean(0.0,0.0);
////            }else{
////                Coordinate myCoordinate = gson.fromJson(PrefHelper.getString(PrefKey.MY_LOCATION), Coordinate.class);
////                getAllGawean(myCoordinate.getLat(),myCoordinate.getLng());
////            }
//        }
        System.out.println("ADA SECTION " + taskLiveData.getValue());
        return sectionLiveData;
    }


    private MutableLiveData<JobRefCodeResponse> refCodeResponseLiveData = new MutableLiveData<>();
    private MutableLiveData<JobAttachmentResponse> attachmentResponseLiveData = new MutableLiveData<>();

    public MutableLiveData<JobRefCodeResponse> getRefCodeResponseLiveData() {
        return refCodeResponseLiveData;
    }

    public MutableLiveData<JobAttachmentResponse> getAttachmentResponseLiveData() {
        return attachmentResponseLiveData;
    }

    public LiveData<JobRefCodeResponse> getRefCode(String uuidTask) {

        application.getApiTaskService().getRefCode(PrefHelper.getString(PrefKey.TOKEN), uuidTask)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JobRefCodeResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                        JobRefCodeResponse dataResponse = new JobRefCodeResponse();
                        dataResponse.setMessage(ERROR_MESSAGE_SERVER);
                        dataResponse.setReturnValue(ERROR_CODE);
                        dataResponse.setRequestKey(UserResponse.RequestKey.JOB_REF_CODE);
                        refCodeResponseLiveData.setValue(dataResponse);
                    }

                    @Override
                    public void onNext(JobRefCodeResponse genericResponse) {
                        if (genericResponse.isSuccess()) {
                            JobRefCodeResponse dataResponse = new JobRefCodeResponse();
                            dataResponse.setRequestKey(UserResponse.RequestKey.REQUEST_SUCCESS);
                            dataResponse.setMessage(genericResponse.getMessage());
                            dataResponse.setReturnValue(genericResponse.getReturnValue());
                            dataResponse.setObject(genericResponse.getObject());

                            refCodeResponseLiveData.setValue(dataResponse);
                        } else {
                            JobRefCodeResponse dataResponse = new JobRefCodeResponse();
                            dataResponse.setRequestKey(UserResponse.RequestKey.REQUEST_FAILED);
                            dataResponse.setMessage(genericResponse.getMessage());
                            dataResponse.setReturnValue(genericResponse.getReturnValue());

                            refCodeResponseLiveData.setValue(dataResponse);
                        }
                    }
                });
        return refCodeResponseLiveData;
    }


    public LiveData<JobAttachmentResponse> getAttachments(String uuidTask) {

        application.getApiTaskService().attachmentList(PrefHelper.getString(PrefKey.TOKEN), uuidTask)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JobAttachmentResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                        JobAttachmentResponse dataResponse = new JobAttachmentResponse();
                        dataResponse.setMessage(ERROR_MESSAGE_SERVER);
                        dataResponse.setReturnValue(ERROR_CODE);
                        dataResponse.setRequestKey(UserResponse.RequestKey.JOB_ATTACHMENT);
                        attachmentResponseLiveData.setValue(dataResponse);
                    }

                    @Override
                    public void onNext(JobAttachmentResponse genericResponse) {
                        if (genericResponse.isSuccess()) {
                            JobAttachmentResponse dataResponse = new JobAttachmentResponse();
                            dataResponse.setRequestKey(UserResponse.RequestKey.REQUEST_SUCCESS);
                            dataResponse.setMessage(genericResponse.getMessage());
                            dataResponse.setReturnValue(genericResponse.getReturnValue());
                            dataResponse.setObject(genericResponse.getObject());

                            attachmentResponseLiveData.setValue(dataResponse);
                        } else {
                            JobAttachmentResponse dataResponse = new JobAttachmentResponse();
                            dataResponse.setRequestKey(UserResponse.RequestKey.REQUEST_FAILED);
                            dataResponse.setMessage(genericResponse.getMessage());
                            dataResponse.setReturnValue(genericResponse.getReturnValue());

                            attachmentResponseLiveData.setValue(dataResponse);
                        }
                    }
                });
        return attachmentResponseLiveData;
    }


    public MutableLiveData<MyTaskResponse> getResponseLiveData() {
        return responseLiveData;
    }

    public void getFromServer(Integer page, Integer offset) {
        MyTaskRequest request = new MyTaskRequest();
        request.setPage(page);
        request.setOffset(offset);

        application.getApiTaskService().getTask(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyTaskResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(MyTaskResponse myTaskResponse) {
                        if (myTaskResponse.isSuccess()) {
                            //List<Task> tasks = new ArrayList<>(myTaskResponse.getTask());

                            myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.GET_MY_TASK);
                            myTaskResponse.setPage_count(myTaskResponse.getPage_count());
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setReturnValue("001");
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void getTaskToday() {

        application.getApiTaskService().getTaskToday(PrefHelper.getString(PrefKey.TOKEN))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyTaskTodayResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(MyTaskTodayResponse myTaskTodayResponse) {
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        if (myTaskTodayResponse.isSuccess()) {

                            List<TaskToday> taskTodayList = new ArrayList<>(myTaskTodayResponse.getTaskGroups());

                            myTaskResponse.setReturnValue(myTaskTodayResponse.getReturnValue());
                            myTaskResponse.setMessage(myTaskTodayResponse.getMessage());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.GET_MY_TASK_TODAY);
                            myTaskResponse.setTaskTodayList(taskTodayList);
                        } else {
                            myTaskResponse.setReturnValue("001");
                            myTaskResponse.setMessage(myTaskTodayResponse.getMessage());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        }
                        responseLiveData.setValue(myTaskResponse);
                    }
                });
    }

    public void getTaskToDo(Integer page, Integer offset, Double lat, Double lng) {

        application.getApiTaskService().getTaskToDo(PrefHelper.getString(PrefKey.TOKEN), page, offset, lat, lng)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TaskToDoResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(TaskToDoResponse taskToDoResponse) {
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        if (taskToDoResponse.isSuccess()) {

                            myTaskResponse.setReturnValue(taskToDoResponse.getReturnValue());
                            myTaskResponse.setMessage(taskToDoResponse.getMessage());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.GET_TASK_TO_DO);
                            myTaskResponse.setTaskToDoResponse(taskToDoResponse);
                        } else {
                            myTaskResponse.setReturnValue("001");
                            myTaskResponse.setMessage(taskToDoResponse.getMessage());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        }
                        responseLiveData.setValue(myTaskResponse);
                    }
                });
    }

    public void updateSequenceToDoList(List<String> uuids) {
        FieldTaskToDoRequest fieldTaskToDoRequest = new FieldTaskToDoRequest();
        List<FieldTaskToDoRequest.FieldTaskToDo> fieldTaskToDoList = new ArrayList<>();
        for (String uuid : uuids) {
            FieldTaskToDoRequest.FieldTaskToDo fieldTaskToDo = new FieldTaskToDoRequest.FieldTaskToDo();
            fieldTaskToDo.setUuid(uuid);
            System.out.println("uuiiiiid " + uuid);
            System.out.println("fieldTaskToDo " + fieldTaskToDo.getUuid());
            fieldTaskToDoList.add(fieldTaskToDo);
        }

        fieldTaskToDoRequest.setFieldTaskToDo(fieldTaskToDoList);

        application.getApiTaskService().updateSequenceToDoList(PrefHelper.getString(PrefKey.TOKEN), fieldTaskToDoRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyTaskTodayResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(MyTaskTodayResponse myTaskTodayResponse) {
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        if (myTaskTodayResponse.isSuccess()) {

                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.UPDATE_SEQUENCE_SUCCESS);
                            myTaskResponse.setReturnValue(myTaskTodayResponse.getReturnValue());
                            myTaskResponse.setMessage(myTaskTodayResponse.getMessage());
                        } else {
                            myTaskResponse.setReturnValue("001");
                            myTaskResponse.setMessage(myTaskTodayResponse.getMessage());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        }
                        responseLiveData.setValue(myTaskResponse);
                    }
                });
    }

    public void getGaweanStarting(Integer page, Integer offset) {
        MyTaskRequest request = new MyTaskRequest();
        request.setPage(page);
        request.setOffset(offset);
        request.setStartingOnly(true);

        application.getApiTaskService().getTask(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyTaskResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(MyTaskResponse myTaskResponse) {
                        if (myTaskResponse.isSuccess()) {
                            //saveToDatabase(myTaskResponse.getTask(), currentTask);

                            List<Task> tasks = new ArrayList<>(myTaskResponse.getTask());

//                            saveToDatabase(tasks);
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.GET_MY_TASK_STARTING);
                            myTaskResponse.setPage_count(myTaskResponse.getPage_count());
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            myTaskResponse.setReturnValue("001");
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.GET_MY_TASK_STARTING_NULL);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void getAllCategoryGawean(Double lat, Double lng, Integer page, Integer offset) {
        GetAllCategoriesRequest request = new GetAllCategoriesRequest();
        request.setLat(lat);
        request.setLng(lng);
        request.setPage(page);
        request.setOffset(offset);
        application.getApiTaskService().getAllCategoryGawean(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeContentsResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setMessage("");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(HomeContentsResponse homeContentsResponse) {
                        if (homeContentsResponse.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.GET_CONTENT_CATEGORIES);
                            myTaskResponse.setReturnValue(homeContentsResponse.getReturnValue());
                            myTaskResponse.setMessage(homeContentsResponse.getMessage());
                            myTaskResponse.setHomeContents(homeContentsResponse.getGroupJobs());
                            myTaskResponse.setRow_count(homeContentsResponse.getRow_count());
                            myTaskResponse.setPage_count(homeContentsResponse.getPage_count());

                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void getWidgetRow(Double lat, Double lng, Integer page, Integer offset) {

        application.getApiTaskService().getWidgetRow(PrefHelper.getString(PrefKey.TOKEN), "application/json", page, offset, lat, lng)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeContentsResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setMessage("");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(HomeContentsResponse homeContentsResponse) {
                        if (homeContentsResponse.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.GET_WIDGET_ROW);
                            myTaskResponse.setReturnValue(homeContentsResponse.getReturnValue());
                            myTaskResponse.setMessage(homeContentsResponse.getMessage());
                            myTaskResponse.setHomeContentsResponse(homeContentsResponse);
                            myTaskResponse.setRow_count(homeContentsResponse.getRow_count());

                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

//    public void getJobByCategory(Double lat, Double lng, String uuidCategory) {
//        application.getApiTaskService().getJobByCategories(PrefHelper.getString(PrefKey.TOKEN), lat, lng, uuidCategory)
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
//                        MyTaskResponse myTaskResponse = new MyTaskResponse();
//                        myTaskResponse.setReturnValue("001");
//                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
//                        responseLiveData.setValue(myTaskResponse);
//                    }
//
//                    @Override
//                    public void onNext(MyTaskResponse tasksResponse) {
//                        if (tasksResponse.isSuccess()) {
//                            MyTaskResponse myTaskResponse = new MyTaskResponse();
//                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.GET_CONTENT_BY_CATEGORIES);
//                            myTaskResponse.setReturnValue(tasksResponse.getReturnValue());
//                            myTaskResponse.setMessage(tasksResponse.getMessage());
//                            myTaskResponse.setTask(tasksResponse.getTask());
//                            myTaskResponse.setUuid(uuidCategory);
//                            myTaskResponse.setRow_count(tasksResponse.getRow_count());
//                            myTaskResponse.setPage_count(tasksResponse.getPage_count());
//                            myTaskResponse.setType(Constant.CONTENT_JOB);
//
//                            List<Task> tasks = new ArrayList<>();
//                            saveToDatabase(tasks);
//                            responseLiveData.setValue(myTaskResponse);
//                        }
//                    }
//                });
//    }

    public void getJobByCategoryV2(Double lat, Double lng, String uuidCategory, Integer page, Integer offset, String activeJobCategory) {
        GetJobCategoryRequest request = new GetJobCategoryRequest();
        request.setLat(lat);
        request.setLng(lng);
        request.setUuidCategories(uuidCategory);
        request.setPage(page);
        request.setOffset(offset);
        application.getApiTaskService().getJobByCategoriesV2(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyTaskResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(MyTaskResponse tasksResponse) {
                        if (tasksResponse.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.GET_CONTENT_BY_CATEGORIES);
                            myTaskResponse.setReturnValue(tasksResponse.getReturnValue());
                            myTaskResponse.setMessage(tasksResponse.getMessage());
                            myTaskResponse.setTask(tasksResponse.getTask());
                            myTaskResponse.setUuid(uuidCategory);
                            myTaskResponse.setRow_count(tasksResponse.getRow_count());
                            myTaskResponse.setPage_count(tasksResponse.getPage_count());
                            myTaskResponse.setActiveUuidJobCategories(activeJobCategory);
                            myTaskResponse.setType(Constant.CONTENT_JOB);

                            List<Task> tasks = new ArrayList<>(myTaskResponse.getTask());
//                            saveToDatabase(tasks);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void getGaweankuByCategory(String uuid, Integer page, Integer offset) {
        MyTaskRequest request = new MyTaskRequest();
        request.setPage(page);
        request.setOffset(offset);

        application.getApiTaskService().getTask(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyTaskResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(MyTaskResponse myTaskResponse) {
                        if (myTaskResponse.isSuccess()) {
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.GET_CONTENT_BY_CATEGORIES);
                            myTaskResponse.setType(Constant.GAWEANKU);
                            myTaskResponse.setUuid(uuid);
                            myTaskResponse.setActiveUuidJobCategories(uuid);

                            List<Task> tasks = new ArrayList<>(myTaskResponse.getTask());
                            saveToDatabase(tasks);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            if (myTaskResponse.getMessage().equals("Task tidak ditemukan")) {
                                myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.ALL_GAWEAN_LOADED);
                                myTaskResponse.setUuid(uuid);

                                responseLiveData.setValue(myTaskResponse);
                            }
                        }
//                        if (myTaskResponse.isSuccess()) {
//                            List<Task> tasks = new ArrayList<>(myTaskResponse.getTask());
//
//                            saveToDatabase(tasks);
//                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.GET_CONTENT_BY_CATEGORIES);
//                            myTaskResponse.setPage_count(myTaskResponse.getPage_count());
//                            responseLiveData.setValue(myTaskResponse);
//                        } else {
//                            myTaskResponse.setReturnValue("001");
//                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
//                            responseLiveData.setValue(myTaskResponse);
//                        }
                    }
                });
    }

    public void getAdsByCategories(String uuidCategory) {
        GetContentByCategoriesRequest request = new GetContentByCategoriesRequest();
        request.setUuidCategories(uuidCategory);
        request.setPage(1);
        request.setOffset(99999);

        application.getApiTaskService().getAdsByCategories(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetAdsResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(GetAdsResponse getAdsResponse) {
                        if (getAdsResponse.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.GET_CONTENT_BY_CATEGORIES);
                            myTaskResponse.setReturnValue(getAdsResponse.getReturnValue());
                            myTaskResponse.setMessage(getAdsResponse.getMessage());
                            myTaskResponse.setAdsList(getAdsResponse.getAds());
                            myTaskResponse.setUuid(uuidCategory);
                            myTaskResponse.setRow_count(getAdsResponse.getRow_count());
                            myTaskResponse.setPage_count(getAdsResponse.getPage_count());
                            myTaskResponse.setType(Constant.CONTENT_ADS);

                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void startGawean(Task task, Long timestamp) {
        System.out.println("OUT >>> 3");
        application.getApiTaskService().startGawean(PrefHelper.getString(PrefKey.TOKEN), task.getId_task(), timestamp)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StartEndTaskResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(StartEndTaskResponse startEndTaskResponse) {
                        if (startEndTaskResponse.isSuccess()) {
                            System.out.println("OUT >>> 4");
                            System.out.println("AM I CALLED : STARTSERVICEGAWEAN");
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setReturnValue(startEndTaskResponse.getReturnValue());
                            myTaskResponse.setUuid(startEndTaskResponse.getUuid());
                            myTaskResponse.setMessage(startEndTaskResponse.getMessage());
                            myTaskResponse.setMtask(task);
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.START_GAWEAN);
                            System.out.println("REQUEST KEY " + myTaskResponse.getRequestKey());
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(startEndTaskResponse.getMessage());
                            myTaskResponse.setReturnValue(startEndTaskResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void startGaweanPending(Task task, Long timestamp) {
        application.getApiTaskService().startGawean(PrefHelper.getString(PrefKey.TOKEN), task.getId_task(), timestamp)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StartEndTaskResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(StartEndTaskResponse startEndTaskResponse) {
                        if (startEndTaskResponse.isSuccess()) {
                            System.out.println("AM I CALLED : STARTSERVICEGAWEAN");
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setReturnValue(startEndTaskResponse.getReturnValue());
                            myTaskResponse.setUuid(startEndTaskResponse.getUuid());
                            myTaskResponse.setMessage(startEndTaskResponse.getMessage());
                            myTaskResponse.setMtask(task);
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.START_GAWEAN_PENDING);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(startEndTaskResponse.getMessage());
                            myTaskResponse.setReturnValue(startEndTaskResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void startGaweanOffline(Task task, Long timestamp) {
        application.getApiTaskService().startGawean(PrefHelper.getString(PrefKey.TOKEN), task.getId_task(), timestamp)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StartEndTaskResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(StartEndTaskResponse startEndTaskResponse) {
                        if (startEndTaskResponse.isSuccess()) {
                            System.out.println("AM I CALLED : STARTSERVICEGAWEAN");
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setReturnValue(startEndTaskResponse.getReturnValue());
                            myTaskResponse.setUuid(startEndTaskResponse.getUuid());
                            myTaskResponse.setMessage(startEndTaskResponse.getMessage());
                            myTaskResponse.setMtask(task);
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.START_GAWEAN_OFFLINE);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(startEndTaskResponse.getMessage());
                            myTaskResponse.setReturnValue(startEndTaskResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }


    public void closeGawean(Task task, String idResult, User user) {
        System.out.println("console log idresult : " + idResult);
        System.out.println("console log idTask : " + task.getId_task());
        application.getApiTaskService().closeGawean(PrefHelper.getString(PrefKey.TOKEN), idResult, task.getId_task())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StartEndTaskResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(StartEndTaskResponse startEndTaskResponse) {
                        if (startEndTaskResponse.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setReturnValue(startEndTaskResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.CLOSE_GAWEAN);
                            if (!startEndTaskResponse.getTask().isIs_qc_needed()) {
                                user.setBalance(user.getBalance() + startEndTaskResponse.getTask().getFee());
                            }
                            updateUser(user);
                            Gson gson = new Gson();
                            Coordinate myCoordinate = gson.fromJson(PrefHelper.getString(PrefKey.MY_LOCATION), Coordinate.class);

                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setReturnValue(startEndTaskResponse.getReturnValue());
                            myTaskResponse.setMessage(startEndTaskResponse.getMessage());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void closeJob(String uuidTask) {
        application.getApiTaskService().closeJob(PrefHelper.getString(PrefKey.TOKEN), uuidTask)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StartEndTaskResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(StartEndTaskResponse startEndTaskResponse) {
                        if (startEndTaskResponse.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setReturnValue(startEndTaskResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.CLOSE_GAWEAN);

                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setReturnValue(startEndTaskResponse.getReturnValue());
                            myTaskResponse.setMessage(startEndTaskResponse.getMessage());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void verifyChatRoom(String uuidJob) {
        HashMap<String, String> hashMapUuidJob = new HashMap<>();
        hashMapUuidJob.put("uuidJob", uuidJob);

        application.getApiTaskService().verifyChatRoom(PrefHelper.getString(PrefKey.TOKEN), hashMapUuidJob)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VerifyChatRoomResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(VerifyChatRoomResponse verifyChatRoomResponse) {
                        if (verifyChatRoomResponse.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setReturnValue(verifyChatRoomResponse.getReturnValue());
                            myTaskResponse.setMessage(verifyChatRoomResponse.getMessage());
                            myTaskResponse.setChatRoomId(verifyChatRoomResponse.getObject());
                            System.out.println("CHAT ROOM SAVED");
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.CHAT_ROOM_ID);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            System.out.println("gedsfsadadss");
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(verifyChatRoomResponse.getMessage());
                            myTaskResponse.setReturnValue(verifyChatRoomResponse.getReturnValue());
                            if (verifyChatRoomResponse.getReturnValue().equals("002")) {
                                myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.CHAT_ROOM_ID_NOT_FOUND);
                            } else {
                                myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            }
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void saveChatRoom(String uuidJob, long chatRoomId) {
        SaveChatRoomRequest saveChatRoomRequest = new SaveChatRoomRequest();
        saveChatRoomRequest.setUuidJob(uuidJob);
        saveChatRoomRequest.setIdChatRoom(chatRoomId);

        application.getApiTaskService().saveChatRoom(PrefHelper.getString(PrefKey.TOKEN), saveChatRoomRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VerifyChatRoomResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(VerifyChatRoomResponse verifyChatRoomResponse) {
                        if (verifyChatRoomResponse.isSuccess()) {
                            System.out.println("asdfgdfsafggnfdsgnfdgsb");
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setReturnValue(verifyChatRoomResponse.getReturnValue());
                            myTaskResponse.setMessage(verifyChatRoomResponse.getMessage());
                            myTaskResponse.setChatRoomId(verifyChatRoomResponse.getObject());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.CHAT_ROOM_SAVED);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            System.out.println("asdadsassa");
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(verifyChatRoomResponse.getMessage());
                            myTaskResponse.setReturnValue(verifyChatRoomResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void historyJobInfo(String uuidTask) {
        application.getApiTaskService().historyJob(PrefHelper.getString(PrefKey.TOKEN), uuidTask)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HistoryTaskResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(HistoryTaskResponse historyTaskResponse) {
                        if (historyTaskResponse.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setReturnValue(historyTaskResponse.getReturnValue());
                            myTaskResponse.setMessage(historyTaskResponse.getMessage());
                            myTaskResponse.setHistoryTaskDone(historyTaskResponse.getHistoryTaskDones());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.GET_HISTORY_JOB_DONE);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(historyTaskResponse.getMessage());
                            myTaskResponse.setReturnValue(historyTaskResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void cancelGawean(Task task) {
        application.getApiTaskService().cancelGawean(PrefHelper.getString(PrefKey.TOKEN), task.getId_task())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyTaskResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        myTaskResponse.setMessage("Gagal");
                        responseLiveData.setValue(myTaskResponse);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(MyTaskResponse myTaskResponse) {
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.CANCEL_GAWEAN);
                        responseLiveData.setValue(myTaskResponse);
                    }
                });
    }

    public void applyGawean(Task task, Integer batchCount, Integer batchProgress, Location myPosition, boolean isBatch) {
        application.getApiTaskService().applyGawean(PrefHelper.getString(PrefKey.TOKEN), task.getId_job())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyTaskResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(MyTaskResponse myTaskResponse) {
                        if (myTaskResponse.isSuccess()) {
                            if (isBatch) {
                                MyTaskResponse taskResponse = new MyTaskResponse();
                                taskResponse.setReturnValue(myTaskResponse.getReturnValue());
                                if (batchCount == batchProgress) {
                                    taskResponse.setRequestKey(MyTaskResponse.RequestKey.APPLY_GAWEAW_BATCH_SUCCESS);
                                } else {
                                    taskResponse.setRequestKey(MyTaskResponse.RequestKey.APPLY_GAWEAW_BATCH_PROGRESS);
                                }
                                task.setStatus_task("waiting");
                                taskResponse.setMessage(myTaskResponse.getMessage());
                                responseLiveData.setValue(taskResponse);
                            } else {
                                Gson gson = new Gson();
                                if (PrefHelper.getString(PrefKey.MY_LOCATION) != null && !PrefHelper.getString(PrefKey.MY_LOCATION).isEmpty()) {
                                    Coordinate myCoordinate = gson.fromJson(PrefHelper.getString(PrefKey.MY_LOCATION), Coordinate.class);
                                    myPosition.setLatitude(myCoordinate.getLat());
                                    myPosition.setLongitude(myCoordinate.getLng());
                                }
                                MyTaskResponse taskResponse = new MyTaskResponse();
                                taskResponse.setMtask(task);
                                taskResponse.setUuid(myTaskResponse.getUuid());
                                taskResponse.setReturnValue(myTaskResponse.getReturnValue());
                                taskResponse.setRequestKey(MyTaskResponse.RequestKey.APPLY_GAWEAN);
                                taskResponse.setMessage(myTaskResponse.getMessage());
                                responseLiveData.setValue(taskResponse);
                            }
                        } else {
                            MyTaskResponse taskResponse = new MyTaskResponse();
                            taskResponse.setReturnValue(myTaskResponse.getReturnValue());
                            taskResponse.setMessage(myTaskResponse.getMessage());
                            taskResponse.setJobName(task.getName());
                            if (batchCount > 1) {
                                myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.APPLY_GAWEAW_BATCH_FAILED);
                            } else {
                                myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            }
                            responseLiveData.setValue(taskResponse);
                        }
                    }
                });
    }

    public void getTaskSectionDirect(BaseActivity baseActivity, Task task) {
        application.getSectionService().directApply(PrefHelper.getString(PrefKey.TOKEN), task.getId_job())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SectionSourceResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(SectionSourceResponse sectionSourceResponse) {

                    }
                });
    }

    public void getSearchItemFacing(String uuidFact, String produk, Integer page, Integer offset, String uuidSelectedFacingAdapter, Integer idFacingItem) {
        application.getSectionService().searchItemFacing(PrefHelper.getString(PrefKey.TOKEN), uuidFact, produk, page, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ItemResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(ItemResponse response) {
                        if (response.isSuccess()) {

                            for (Item item : response.getItems()) {
                                item.setUuidAdapter(uuidSelectedFacingAdapter);
                                if (idFacingItem != null) {
                                    item.setIdItem(idFacingItem);
                                }
                            }

                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setReturnValue(response.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.GET_ITEM_SEARCH);
                            myTaskResponse.setMessage(response.getMessage());
                            myTaskResponse.setItemResponse(response);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setReturnValue("001");
                            myTaskResponse.setMessage(response.getMessage());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void getSearchItemPresence(String uuidFact, String produk, Integer page, Integer offset) {
        application.getSectionService().searchItemFacing(PrefHelper.getString(PrefKey.TOKEN), uuidFact, produk, page, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ItemResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(ItemResponse response) {
                        if (response.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setReturnValue(response.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.GET_ITEM_SEARCH);
                            myTaskResponse.setMessage(response.getMessage());
                            myTaskResponse.setItemResponse(response);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setReturnValue("001");
                            myTaskResponse.setMessage(response.getMessage());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void getlocationSpotCheck(String uuidFact, Double lat, Double lng) {
        application.getSectionService().getlocationSpotCheck(PrefHelper.getString(PrefKey.TOKEN), uuidFact, lat, lng)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GeoLocationSpotCheckResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(GeoLocationSpotCheckResponse response) {
                        if (response.isSuccess()) {

                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setReturnValue(response.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.GET_GEO_LOCATION_SPOT_CHECK);
                            myTaskResponse.setMessage(response.getMessage());
                            myTaskResponse.setGeoLocationSpotCheckResponse(response);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setReturnValue("001");
                            myTaskResponse.setMessage(response.getMessage());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void getTaskSectionForSavingToDB(BaseActivity baseActivity, Task task, String uuidSession) {
        application.getSectionService().getTask(PrefHelper.getString(PrefKey.TOKEN), task.getId_task())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SectionSourceResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(SectionSourceResponse sectionSourceResponse) {
                        if (sectionSourceResponse.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            sectionSourceResponse.setId_task(task.getId_task());
                            sectionSourceResponse.setUuidSession(uuidSession);
                            sectionSourceResponse.setIdJob(Integer.valueOf(task.getJobId()));

                            myTaskResponse.setReturnValue(sectionSourceResponse.getReturnValue());
                            myTaskResponse.setMessage(sectionSourceResponse.getMessage());
                            myTaskResponse.setMtask(task);
                            myTaskResponse.setSectionSourceResponse(sectionSourceResponse);
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.GET_DOWNLOAD_SECTION);

                            System.out.println("asdasdasdasdaf => " + sectionSourceResponse);

//                            saveQuestToDatabaseV2(sectionSourceResponse);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setReturnValue("001");
                            myTaskResponse.setMessage(sectionSourceResponse.getMessage());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void getTaskSection(BaseActivity baseActivity, Task task, String uuidSession) {
        System.out.println("OUT >>> 7");
        application.getSectionService().getTask(PrefHelper.getString(PrefKey.TOKEN), task.getId_task())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SectionSourceResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(SectionSourceResponse sectionSourceResponse) {

                    }
                });
    }

    public void pinTask(Task task, boolean isPinned) {
        application.getApiTaskService().pinTask(PrefHelper.getString(PrefKey.TOKEN), task.getId_task(), isPinned)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GenericResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(GenericResponse genericResponse) {
                        if (genericResponse.isSuccess()) {
                            updateTask(task);
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setReturnValue(genericResponse.getReturnValue());
                            myTaskResponse.setMessage(genericResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.PIN_TASK);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setReturnValue(genericResponse.getReturnValue());
                            myTaskResponse.setMessage(genericResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.PIN_TASK);
                        }
                    }
                });
    }

    public void getTaskPolyline(Task task) {
        application.getApiTaskService().getTaskPolyline(PrefHelper.getString(PrefKey.TOKEN), task.getId_job())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TaskPolylineResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(TaskPolylineResponse taskPolylineResponse) {
                        if (taskPolylineResponse.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(taskPolylineResponse.getMessage());
                            myTaskResponse.setReturnValue(taskPolylineResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.GET_TASK_POLYLINE);
                            myTaskResponse.setTaskPolyline(taskPolylineResponse.getTaskPolyline());

                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(taskPolylineResponse.getMessage());
                            myTaskResponse.setReturnValue(taskPolylineResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);

                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void getSpesificProject(String projectName, LatLng myPosition, LatLng centerLatLng) {
        application.getApiTaskService().searchSpecificProject(PrefHelper.getString(PrefKey.TOKEN), projectName, myPosition.latitude, myPosition.longitude, centerLatLng.latitude, centerLatLng.longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyTaskResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(MyTaskResponse myTaskResponse) {
                        if (myTaskResponse.isSuccess()) {
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.GET_TASK_SPECIFIC_PROJECT);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void searchGawean(LatLng myPosition, Integer upToFee, Integer radius, String project, Integer jobPage, List<String> uuidLocation, List<String> uuidClassJob, String jobName) {
        application.getSearchService().searchJob(PrefHelper.getString(PrefKey.TOKEN), upToFee, myPosition.latitude, myPosition.longitude, project, radius, jobPage, 10, uuidLocation, uuidClassJob, jobName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GaweanResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(GaweanResponse gaweanResponse) {
                        if (gaweanResponse.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(gaweanResponse.getMessage());
                            myTaskResponse.setReturnValue(gaweanResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.SEARCH_GAWEAN_RESULT);
                            myTaskResponse.setGaweanResponse(gaweanResponse);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(gaweanResponse.getMessage());
                            myTaskResponse.setReturnValue(gaweanResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            myTaskResponse.setGaweanResponse(gaweanResponse);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void searchGaweanV2(LatLng myPosition, Integer upToFee, Integer radius, String project, Integer jobPage, List<String> uuidLocation, List<String> uuidClassJob, String jobName) {
        application.getSearchService().searchJobV2(PrefHelper.getString(PrefKey.TOKEN), upToFee, myPosition.latitude, myPosition.longitude, project, radius, jobPage, 10, uuidLocation, uuidClassJob, jobName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GaweanResponseV2>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(GaweanResponseV2 gaweanResponse) {
                        if (gaweanResponse.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(gaweanResponse.getMessage());
                            myTaskResponse.setReturnValue(gaweanResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.SEARCH_GAWEAN_RESULT);
                            myTaskResponse.setGaweanResponseV2(gaweanResponse);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(gaweanResponse.getMessage());
                            myTaskResponse.setReturnValue(gaweanResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            myTaskResponse.setGaweanResponseV2(gaweanResponse);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void getActiveOrder() {
        application.getSearchService().getActiveOrder(PrefHelper.getString(PrefKey.TOKEN))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GaweanResponseV2>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(GaweanResponseV2 gaweanResponse) {
                        if (gaweanResponse.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(gaweanResponse.getMessage());
                            myTaskResponse.setReturnValue(gaweanResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.GET_ACTIVE_ORDER);
                            myTaskResponse.setGaweanResponseV2(gaweanResponse);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(gaweanResponse.getMessage());
                            myTaskResponse.setReturnValue(gaweanResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            myTaskResponse.setGaweanResponseV2(gaweanResponse);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void confirmOrder(String to) {
        application.getSearchService().confirmOrder(PrefHelper.getString(PrefKey.TOKEN), to)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GenericResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GenericResponse genericResponse) {
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        if (genericResponse.isSuccess()) {
                            myTaskResponse.setReturnValue(genericResponse.getReturnValue());
                            myTaskResponse.setMessage(genericResponse.getMessage());
                            if (!myTaskResponse.getReturnValue().equals("Gawean gagal diambil")) {
                                myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.CONFIRM_ORDER);
                            } else {
                                myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.NOTHING);
                            }

                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            myTaskResponse.setReturnValue(genericResponse.getReturnValue());
                            myTaskResponse.setMessage(genericResponse.getMessage());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);

                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void searchGaweanGrid(LatLng myPosition, Integer upToFee, Integer radius, String project, Integer jobPage, List<String> uuidLocation, List<String> uuidClassJob, String jobName) {
        application.getSearchService().searchJob(PrefHelper.getString(PrefKey.TOKEN), upToFee, myPosition.latitude, myPosition.longitude, project, radius, jobPage, 20, uuidLocation, uuidClassJob, jobName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GaweanResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(GaweanResponse gaweanResponse) {
                        if (gaweanResponse.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(gaweanResponse.getMessage());
                            myTaskResponse.setReturnValue(gaweanResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.SEARCH_GAWEAN_GRID_RESULT);
                            myTaskResponse.setGaweanResponse(gaweanResponse);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(gaweanResponse.getMessage());
                            myTaskResponse.setReturnValue(gaweanResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            myTaskResponse.setGaweanResponse(gaweanResponse);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void getTimelineActivity(Integer jobPage, String type, String uuidMoGawers) {
        application.getApiTaskService().getTimelineActivity(PrefHelper.getString(PrefKey.TOKEN), jobPage, 20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TimelineActivityResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(TimelineActivityResponse timelineActivityResponse) {
                        if (timelineActivityResponse.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(timelineActivityResponse.getMessage());
                            myTaskResponse.setReturnValue(timelineActivityResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.GET_TIMELINE_LIST);
                            myTaskResponse.setRow_count(timelineActivityResponse.getRowCount());
                            myTaskResponse.setTimelineActivityResponse(timelineActivityResponse);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(timelineActivityResponse.getMessage());
                            myTaskResponse.setReturnValue(timelineActivityResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            myTaskResponse.setTimelineActivityResponse(timelineActivityResponse);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void getGaweanInstant(Integer jobPage) {

        application.getApiTaskService().getGaweanInstant(PrefHelper.getString(PrefKey.TOKEN), jobPage, 20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GaweanInstantResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(GaweanInstantResponse gaweanInstantResponse) {
                        if (gaweanInstantResponse.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(gaweanInstantResponse.getMessage());
                            myTaskResponse.setReturnValue(gaweanInstantResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.GET_GAWEAN_INSTANT);
                            myTaskResponse.setRow_count(gaweanInstantResponse.getRowCount());
                            myTaskResponse.setGaweanInstantResponse(gaweanInstantResponse);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(gaweanInstantResponse.getMessage());
                            myTaskResponse.setReturnValue(gaweanInstantResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            myTaskResponse.setGaweanInstantResponse(gaweanInstantResponse);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void getGaweanInstantDetail(String uuid) {
        application.getApiTaskService().getGaweanInstantDetail(PrefHelper.getString(PrefKey.TOKEN), uuid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GaweanInstantDetailResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(GaweanInstantDetailResponse gaweanInstantDetailResponse) {
                        MyTaskResponse myTaskResponse = new MyTaskResponse();

                        if (gaweanInstantDetailResponse.isSuccess()) {
                            myTaskResponse.setGaweanInstantDetailResponse(gaweanInstantDetailResponse);
                            myTaskResponse.setMessage(gaweanInstantDetailResponse.getMessage());
                            myTaskResponse.setReturnValue(gaweanInstantDetailResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.GET_GAWEAN_INSTANT_INFORMATION);
                        } else {
                            myTaskResponse.setReturnValue(gaweanInstantDetailResponse.getReturnValue());
                            myTaskResponse.setMessage(gaweanInstantDetailResponse.getMessage());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        }
                        responseLiveData.setValue(myTaskResponse);
                    }
                });
    }

    public void likePostTimeline(String uuidPostTimeline) {
        application.getApiTaskService().likePostTimeline(PrefHelper.getString(PrefKey.TOKEN), uuidPostTimeline)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TimelineActivityResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(TimelineActivityResponse timelineActivityResponse) {
                        if (timelineActivityResponse.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(timelineActivityResponse.getMessage());
                            myTaskResponse.setReturnValue(timelineActivityResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.LIKE_TIMELINE_SUCCESS);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(timelineActivityResponse.getMessage());
                            myTaskResponse.setReturnValue(timelineActivityResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void activateInstantGawean(String uuidGawean) {
        application.getApiTaskService().activateInstantGawean(PrefHelper.getString(PrefKey.TOKEN), uuidGawean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GenericResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(GenericResponse timelineActivityResponse) {
                        if (timelineActivityResponse.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(timelineActivityResponse.getMessage());
                            myTaskResponse.setReturnValue(timelineActivityResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.ACTIVATE_INSTANT_GAWEAN_SUCCESS);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(timelineActivityResponse.getMessage());
                            myTaskResponse.setReturnValue(timelineActivityResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void deactivateInstantGawean(String uuidGawean) {
        application.getApiTaskService().deactivateInstantGawean(PrefHelper.getString(PrefKey.TOKEN), uuidGawean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GenericResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(GenericResponse timelineActivityResponse) {
                        if (timelineActivityResponse.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(timelineActivityResponse.getMessage());
                            myTaskResponse.setReturnValue(timelineActivityResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.DEACTIVATE_INSTANT_GAWEAN_SUCCESS);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(timelineActivityResponse.getMessage());
                            myTaskResponse.setReturnValue(timelineActivityResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void acceptGaweanOffer(String uuidQueue, String uuidJob) {
        SJROfferRequest request = new SJROfferRequest();
        request.setUuidQueue(uuidQueue);
        request.setUuidJob(uuidJob);

        application.getApiTaskService().acceptGaweanOffer(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GenericResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(GenericResponse timelineActivityResponse) {
                        if (timelineActivityResponse.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(timelineActivityResponse.getMessage());
                            myTaskResponse.setReturnValue(timelineActivityResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.ACCEPT_GAWEAN_OFFERING_SUCCESS);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(timelineActivityResponse.getMessage());
                            myTaskResponse.setReturnValue(timelineActivityResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void declineGaweanOffer(String uuidQueue, String uuidJob) {
        SJROfferRequest request = new SJROfferRequest();
        request.setUuidQueue(uuidQueue);
        request.setUuidJob(uuidJob);

        application.getApiTaskService().declineGaweanOffer(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GenericResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(GenericResponse timelineActivityResponse) {
                        if (timelineActivityResponse.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(timelineActivityResponse.getMessage());
                            myTaskResponse.setReturnValue(timelineActivityResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.DECLINE_GAWEAN_OFFERING_SUCCESS);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(timelineActivityResponse.getMessage());
                            myTaskResponse.setReturnValue(timelineActivityResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void unlikePostTimeline(String uuidPostTimeline) {
        application.getApiTaskService().unlikePostTimeline(PrefHelper.getString(PrefKey.TOKEN), uuidPostTimeline)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TimelineActivityResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(TimelineActivityResponse timelineActivityResponse) {
                        if (timelineActivityResponse.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(timelineActivityResponse.getMessage());
                            myTaskResponse.setReturnValue(timelineActivityResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.UNLIKE_TIMELINE_SUCCESS);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(timelineActivityResponse.getMessage());
                            myTaskResponse.setReturnValue(timelineActivityResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void postTimelineStatus(String content, String uuidMoGawers, String uuidPostType) {
        PostStatusRequest request = new PostStatusRequest();
        request.setContent(content);
        request.setUuidMogawers(uuidMoGawers);
        request.setUuidPostType(uuidPostType);
        request.setCommentable(true);
        request.setLikeable(true);
        request.setShareable(true);

        application.getApiTaskService().postTimelineStatus(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TimelineActivityResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(TimelineActivityResponse response) {
                        if (response.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.POST_STATUS_SUCCES);
                            myTaskResponse.setReturnValue(response.getReturnValue());
                            myTaskResponse.setMessage(response.getMessage());
                            myTaskResponse.setUuid(response.getUuid());

                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void connectToMoGawers(String uuidMoGawers) {
        application.getApiTaskService().connectToMoGawers(PrefHelper.getString(PrefKey.TOKEN), uuidMoGawers)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TimelineActivityResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(TimelineActivityResponse timelineActivityResponse) {
                        if (timelineActivityResponse.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(timelineActivityResponse.getMessage());
                            myTaskResponse.setReturnValue(timelineActivityResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.CONNECT_SUCCESS);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(timelineActivityResponse.getMessage());
                            myTaskResponse.setReturnValue(timelineActivityResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void disconnectToMoGawers(String uuidMoGawers) {
        application.getApiTaskService().disconnectToMoGawers(PrefHelper.getString(PrefKey.TOKEN), uuidMoGawers)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TimelineActivityResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(TimelineActivityResponse timelineActivityResponse) {
                        if (timelineActivityResponse.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(timelineActivityResponse.getMessage());
                            myTaskResponse.setReturnValue(timelineActivityResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.CONNECT_SUCCESS);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(timelineActivityResponse.getMessage());
                            myTaskResponse.setReturnValue(timelineActivityResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void goingTo(String uuidTask) {
        application.getApiTaskService().goingTo(PrefHelper.getString(PrefKey.TOKEN), uuidTask)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyTaskResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(MyTaskResponse myTaskResponse) {
                        if (myTaskResponse.isSuccess()) {
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.GOING_TO_SUCCESS);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void cancelTo(String uuidTask) {
        application.getApiTaskService().cancelTo(PrefHelper.getString(PrefKey.TOKEN), uuidTask)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyTaskResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(MyTaskResponse myTaskResponse) {
                        if (myTaskResponse.isSuccess()) {
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.CANCEL_TO_SUCCESS);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void getGaweanInformation(String uuidTask) {
        application.getApiTaskService().getGaweanInformation(PrefHelper.getString(PrefKey.TOKEN), uuidTask)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GawenInformationResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(GawenInformationResponse gawenInformationResponse) {
                        MyTaskResponse myTaskResponse = new MyTaskResponse();

                        if (gawenInformationResponse.isSuccess()) {

                            myTaskResponse.setGawenInformationResponse(gawenInformationResponse);
                            myTaskResponse.setMessage(gawenInformationResponse.getMessage());
                            myTaskResponse.setReturnValue(gawenInformationResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.GET_GAWEAN_INFORMATION);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            myTaskResponse.setReturnValue(gawenInformationResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void getGaweanInformationByIdJob(String uuidTask) {
        application.getApiTaskService().getGaweanInformationByIdJob(PrefHelper.getString(PrefKey.TOKEN), uuidTask)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GawenInformationResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(GawenInformationResponse gawenInformationResponse) {
                        MyTaskResponse myTaskResponse = new MyTaskResponse();

                        if (gawenInformationResponse.isSuccess()) {

                            myTaskResponse.setGawenInformationResponse(gawenInformationResponse);
                            myTaskResponse.setMessage(gawenInformationResponse.getMessage());
                            myTaskResponse.setReturnValue(gawenInformationResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.GET_GAWEAN_INFORMATION);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            myTaskResponse.setReturnValue(gawenInformationResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void getCategoryProduct(Integer jobPage) {
        application.getSearchService().getCategoryProduct(PrefHelper.getString(PrefKey.TOKEN), jobPage, 20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(ProductResponse productResponse) {
                        if (productResponse.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(productResponse.getMessage());
                            myTaskResponse.setReturnValue(productResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.PRODUCT_CATEGORY_RESULT);
                            myTaskResponse.setProductResponse(productResponse);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(productResponse.getMessage());
                            myTaskResponse.setReturnValue(productResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            myTaskResponse.setProductResponse(productResponse);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void getLocationList(String uuidLocation, Double lat, Double lng, Integer page) {
        application.getSearchService().getLocationList(PrefHelper.getString(PrefKey.TOKEN), uuidLocation, lat, lng, 25000, page, 20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LocationResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(LocationResponse locationResponse) {
                        if (locationResponse.isSuccess()) {
                            System.out.println("BERHASIL HOREE");
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(locationResponse.getMessage());
                            myTaskResponse.setReturnValue(locationResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.LOCATION_LIST_RESULT);
                            myTaskResponse.setLocationResponse(locationResponse);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            System.out.println("GAGAL HOREE");
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(locationResponse.getMessage());
                            myTaskResponse.setReturnValue(locationResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            myTaskResponse.setLocationResponse(locationResponse);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void getSubCategoryList(String uuidLocation, Double lat, Double lng, Integer page) {
        application.getSearchService().getSubCategoryList(PrefHelper.getString(PrefKey.TOKEN), uuidLocation, page, 20, lat, lng, 25000)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(ProductResponse productResponse) {
                        if (productResponse.isSuccess()) {
                            System.out.println("BERHASIL HOREE");
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(productResponse.getMessage());
                            myTaskResponse.setReturnValue(productResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.SUB_PRODUCT_CATEGORY_RESULT);
                            myTaskResponse.setProductResponse(productResponse);
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            System.out.println("GAGAL HOREE");
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setMessage(productResponse.getMessage());
                            myTaskResponse.setReturnValue(productResponse.getReturnValue());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            myTaskResponse.setProductResponse(productResponse);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }

    public void supplierRegistrationPost(String storeName, String storeAddress, String storeLat, String storeLng) {
        Supplier request = new Supplier();
        request.setStoreName(storeName);
        request.setStoreAddress(storeAddress);
        request.setStoreLat(storeLat);
        request.setStoreLng(storeLng);
        application.getApiTaskService().supplierRegistrationPost(PrefHelper.getString(PrefKey.TOKEN), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SupplierResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyTaskResponse myTaskResponse = new MyTaskResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                        responseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(SupplierResponse response) {
                        if (response.isSuccess()) {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.SUPPLIER_REGISTRATION_SUCCESS);
                            myTaskResponse.setReturnValue(response.getReturnValue());
                            myTaskResponse.setMessage(response.getMessage());
                            myTaskResponse.setObjectResponse(response.getObject());
                            responseLiveData.setValue(myTaskResponse);
                        } else {
                            MyTaskResponse myTaskResponse = new MyTaskResponse();
                            myTaskResponse.setReturnValue("001");
                            myTaskResponse.setMessage(response.getMessage());
                            myTaskResponse.setRequestKey(MyTaskResponse.RequestKey.FAILED);
                            responseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
    }


    private MutableLiveData<CatalogObjResponse> catalogResponseLiveData = new MutableLiveData<>();

    public MutableLiveData<CatalogObjResponse> getCatalogResponseLiveData() {
        return catalogResponseLiveData;
    }

    public LiveData<CatalogObjResponse> supplierCreateProduct(Catalog catalog) {
        application.getApiTaskService().supplierCreateProduct(PrefHelper.getString(PrefKey.TOKEN), catalog)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CatalogObjResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        CatalogObjResponse myTaskResponse = new CatalogObjResponse();
                        myTaskResponse.setReturnValue("001");
                        catalogResponseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(CatalogObjResponse response) {
                        if (response.isSuccess()) {
                            CatalogObjResponse myTaskResponse = new CatalogObjResponse();
                            myTaskResponse.setReturnValue(response.getReturnValue());
                            myTaskResponse.setMessage(response.getMessage());
                            myTaskResponse.setObject(response.getObject());
                            catalogResponseLiveData.setValue(myTaskResponse);
                        } else {
                            CatalogObjResponse myTaskResponse = new CatalogObjResponse();
                            myTaskResponse.setReturnValue("001");
                            myTaskResponse.setMessage(response.getMessage());
                            catalogResponseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
        return catalogResponseLiveData;
    }


    public LiveData<CatalogObjResponse> supplierEditProduct(Catalog catalog) {
        application.getApiTaskService().supplierEditProduct(PrefHelper.getString(PrefKey.TOKEN), catalog)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CatalogObjResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        CatalogObjResponse myTaskResponse = new CatalogObjResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(CatalogObjResponse.RequestKey.EDIT);
                        catalogResponseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(CatalogObjResponse response) {
                        if (response.isSuccess()) {
                            CatalogObjResponse myTaskResponse = new CatalogObjResponse();
                            myTaskResponse.setReturnValue(response.getReturnValue());
                            myTaskResponse.setMessage(response.getMessage());
                            myTaskResponse.setObject(response.getObject());
                            myTaskResponse.setRequestKey(CatalogObjResponse.RequestKey.EDIT);
                            catalogResponseLiveData.setValue(myTaskResponse);
                        } else {
                            CatalogObjResponse myTaskResponse = new CatalogObjResponse();
                            myTaskResponse.setReturnValue("001");
                            myTaskResponse.setMessage(response.getMessage());
                            myTaskResponse.setRequestKey(CatalogObjResponse.RequestKey.EDIT);
                            catalogResponseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
        return catalogResponseLiveData;
    }


    public LiveData<CatalogObjResponse> getDetailProduct(Catalog catalog) {
        application.getApiSalesService().detailProduct(PrefHelper.getString(PrefKey.TOKEN), catalog.getUuid())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CatalogObjResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        CatalogObjResponse myTaskResponse = new CatalogObjResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(CatalogObjResponse.RequestKey.DETAIL);
                        catalogResponseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(CatalogObjResponse response) {
                        if (response.isSuccess()) {
                            CatalogObjResponse myTaskResponse = new CatalogObjResponse();
                            myTaskResponse.setReturnValue(response.getReturnValue());
                            myTaskResponse.setMessage(response.getMessage());
                            myTaskResponse.setObject(response.getObject());
                            myTaskResponse.setRequestKey(CatalogObjResponse.RequestKey.DETAIL);
                            catalogResponseLiveData.setValue(myTaskResponse);
                        } else {
                            CatalogObjResponse myTaskResponse = new CatalogObjResponse();
                            myTaskResponse.setReturnValue("001");
                            myTaskResponse.setMessage(response.getMessage());
                            myTaskResponse.setRequestKey(CatalogObjResponse.RequestKey.DETAIL);
                            catalogResponseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
        return catalogResponseLiveData;
    }

    public LiveData<CatalogObjResponse> publishProduct(Catalog catalog) {
        application.getApiSalesService().publishProduct(PrefHelper.getString(PrefKey.TOKEN), catalog.getUuid())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CatalogObjResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        CatalogObjResponse myTaskResponse = new CatalogObjResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(CatalogObjResponse.RequestKey.PUBLISH);
                        catalogResponseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(CatalogObjResponse response) {
                        if (response.isSuccess()) {
                            CatalogObjResponse myTaskResponse = new CatalogObjResponse();
                            myTaskResponse.setReturnValue(response.getReturnValue());
                            myTaskResponse.setMessage(response.getMessage());
                            myTaskResponse.setObject(response.getObject());
                            myTaskResponse.setRequestKey(CatalogObjResponse.RequestKey.PUBLISH);
                            catalogResponseLiveData.setValue(myTaskResponse);
                        } else {
                            CatalogObjResponse myTaskResponse = new CatalogObjResponse();
                            myTaskResponse.setReturnValue("001");
                            myTaskResponse.setMessage(response.getMessage());
                            myTaskResponse.setRequestKey(CatalogObjResponse.RequestKey.PUBLISH);
                            catalogResponseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
        return catalogResponseLiveData;
    }

    public LiveData<CatalogObjResponse> unpublishProduct(Catalog catalog) {
        application.getApiSalesService().unpublishProduct(PrefHelper.getString(PrefKey.TOKEN), catalog.getUuid())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CatalogObjResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        CatalogObjResponse myTaskResponse = new CatalogObjResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(CatalogObjResponse.RequestKey.UNPUBLISH);
                        catalogResponseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(CatalogObjResponse response) {
                        if (response.isSuccess()) {
                            CatalogObjResponse myTaskResponse = new CatalogObjResponse();
                            myTaskResponse.setReturnValue(response.getReturnValue());
                            myTaskResponse.setMessage(response.getMessage());
                            myTaskResponse.setObject(response.getObject());
                            myTaskResponse.setRequestKey(CatalogObjResponse.RequestKey.UNPUBLISH);
                            catalogResponseLiveData.setValue(myTaskResponse);
                        } else {
                            CatalogObjResponse myTaskResponse = new CatalogObjResponse();
                            myTaskResponse.setReturnValue("001");
                            myTaskResponse.setMessage(response.getMessage());
                            myTaskResponse.setRequestKey(CatalogObjResponse.RequestKey.UNPUBLISH);
                            catalogResponseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
        return catalogResponseLiveData;
    }

    public LiveData<CatalogObjResponse> deleteProduct(Catalog catalog) {
        application.getApiSalesService().deleteProduct(PrefHelper.getString(PrefKey.TOKEN), catalog.getUuid())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CatalogObjResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        CatalogObjResponse myTaskResponse = new CatalogObjResponse();
                        myTaskResponse.setReturnValue("001");
                        myTaskResponse.setRequestKey(CatalogObjResponse.RequestKey.DELETE);
                        catalogResponseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(CatalogObjResponse response) {
                        if (response.isSuccess()) {
                            CatalogObjResponse myTaskResponse = new CatalogObjResponse();
                            myTaskResponse.setReturnValue(response.getReturnValue());
                            myTaskResponse.setMessage(response.getMessage());
                            myTaskResponse.setObject(response.getObject());
                            myTaskResponse.setRequestKey(CatalogObjResponse.RequestKey.DELETE);
                            catalogResponseLiveData.setValue(myTaskResponse);
                        } else {
                            CatalogObjResponse myTaskResponse = new CatalogObjResponse();
                            myTaskResponse.setReturnValue("001");
                            myTaskResponse.setMessage(response.getMessage());
                            myTaskResponse.setRequestKey(CatalogObjResponse.RequestKey.DELETE);
                            catalogResponseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
        return catalogResponseLiveData;
    }


    public LiveData<SupplierResponse> checkoutOrder(Order order) {

        System.out.println("OUT >> "+order.getBuyerPhone());

        application.getApiSalesService().checkoutOrder(PrefHelper.getString(PrefKey.TOKEN), order)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SupplierResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        SupplierResponse myTaskResponse = new SupplierResponse();
                        myTaskResponse.setReturnValue("001");
                        supplierResponseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(SupplierResponse response) {
                        if (response.isSuccess()) {
                            SupplierResponse myTaskResponse = new SupplierResponse();
                            myTaskResponse.setReturnValue(response.getReturnValue());
                            myTaskResponse.setObject(response.getObject());
                            myTaskResponse.setMessage(response.getMessage());
                            supplierResponseLiveData.setValue(myTaskResponse);
                        } else {
                            SupplierResponse myTaskResponse = new SupplierResponse();
                            myTaskResponse.setReturnValue("001");
                            myTaskResponse.setMessage(response.getMessage());
                            supplierResponseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
        return supplierResponseLiveData;
    }


    private MutableLiveData<SupplierResponse> supplierResponseLiveData = new MutableLiveData<>();

    public MutableLiveData<SupplierResponse> getSupplierResponseLiveData() {
        return supplierResponseLiveData;
    }

    public LiveData<SupplierResponse> supplierUploadImagesProduct(File imageFile) {
        String imageName = "IMAGES_products_" + ".jpg";
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part multiPart = MultipartBody.Part.createFormData("file", imageName, requestBody);

        application.getApiTaskService().supplierUploadImagesProduct(PrefHelper.getString(PrefKey.TOKEN), multiPart)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SupplierResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        SupplierResponse response = new SupplierResponse();
                        response.setReturnValue("001");
                        supplierResponseLiveData.setValue(response);
                    }

                    @Override
                    public void onNext(SupplierResponse response) {
                        if (response.isSuccess()) {
                            supplierResponseLiveData.setValue(response);
                        } else {
                            response.setReturnValue("001");
                            supplierResponseLiveData.setValue(response);
                        }
                    }
                });
        return supplierResponseLiveData;
    }


    private MutableLiveData<ProductCategoryListResponse> productCategoryListResponseLiveData = new MutableLiveData<>();

    public MutableLiveData<ProductCategoryListResponse> getProductCategoryListResponseLiveData() {
        return productCategoryListResponseLiveData;
    }

    public LiveData<ProductCategoryListResponse> getCategoryProductSupplier() {
        application.getApiTaskService().getCategoryProductSupplier(PrefHelper.getString(PrefKey.TOKEN))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductCategoryListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        ProductCategoryListResponse myTaskResponse = new ProductCategoryListResponse();
                        myTaskResponse.setReturnValue("001");
                        productCategoryListResponseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(ProductCategoryListResponse response) {
                        if (response.isSuccess()) {
                            ProductCategoryListResponse myTaskResponse = new ProductCategoryListResponse();
                            myTaskResponse.setReturnValue(response.getReturnValue());
                            myTaskResponse.setMessage(response.getMessage());
                            myTaskResponse.setObject(response.getObject());
                            productCategoryListResponseLiveData.setValue(myTaskResponse);
                        } else {
                            ProductCategoryListResponse myTaskResponse = new ProductCategoryListResponse();
                            myTaskResponse.setReturnValue("001");
                            myTaskResponse.setMessage(response.getMessage());
                            productCategoryListResponseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
        return productCategoryListResponseLiveData;
    }


    public LiveData<TaskResponse> getTaskResponseLiveData() {
        return taskResponseLiveData;
    }

    public LiveData<TaskResponse> detailJob(String uuidJob) {
        application.getApiTaskService().detailJob(PrefHelper.getString(PrefKey.TOKEN), uuidJob)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TaskResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                        TaskResponse dataResponse = new TaskResponse();
                        dataResponse.setMessage(ERROR_MESSAGE_SERVER);
                        dataResponse.setReturnValue(ERROR_CODE);
                        taskResponseLiveData.setValue(dataResponse);
                    }

                    @Override
                    public void onNext(TaskResponse genericResponse) {
                        if (genericResponse.isSuccess()) {
                            TaskResponse dataResponse = new TaskResponse();
                            dataResponse.setMessage(genericResponse.getMessage());
                            dataResponse.setReturnValue(genericResponse.getReturnValue());
                            dataResponse.setObject(genericResponse.getObject());
                            taskResponseLiveData.setValue(dataResponse);
                        } else {
                            TaskResponse dataResponse = new TaskResponse();
                            dataResponse.setMessage(genericResponse.getMessage());
                            dataResponse.setReturnValue(genericResponse.getReturnValue());
                            taskResponseLiveData.setValue(dataResponse);
                        }
                    }
                });
        return taskResponseLiveData;
    }


    private MutableLiveData<ProvinceListResponse> provinceListResponseLiveData = new MutableLiveData<>();

    public MutableLiveData<ProvinceListResponse> getProvinceListResponseLiveData() {
        return provinceListResponseLiveData;
    }

    public LiveData<ProvinceListResponse> provinceList() {
        application.getApiSalesService().provinceList(PrefHelper.getString(PrefKey.TOKEN))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProvinceListResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        ProvinceListResponse myTaskResponse = new ProvinceListResponse();
                        myTaskResponse.setReturnValue("001");
                        provinceListResponseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(ProvinceListResponse response) {
                        if (response.isSuccess()) {
                            ProvinceListResponse myTaskResponse = new ProvinceListResponse();
                            myTaskResponse.setReturnValue(response.getReturnValue());
                            myTaskResponse.setMessage(response.getMessage());
                            myTaskResponse.setObject(response.getObject());
                            provinceListResponseLiveData.setValue(myTaskResponse);
                        } else {
                            ProvinceListResponse myTaskResponse = new ProvinceListResponse();
                            myTaskResponse.setReturnValue("001");
                            myTaskResponse.setMessage(response.getMessage());
                            provinceListResponseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
        return provinceListResponseLiveData;
    }


    private MutableLiveData<CityListResponse> cityListResponseLiveData = new MutableLiveData<>();

    public MutableLiveData<CityListResponse> getCityListResponseLiveData() {
        return cityListResponseLiveData;
    }

    public LiveData<CityListResponse> cityList() {
        application.getApiSalesService().cityList(PrefHelper.getString(PrefKey.TOKEN))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CityListResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        CityListResponse myTaskResponse = new CityListResponse();
                        myTaskResponse.setReturnValue("001");
                        cityListResponseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(CityListResponse response) {
                        if (response.isSuccess()) {
                            CityListResponse myTaskResponse = new CityListResponse();
                            myTaskResponse.setReturnValue(response.getReturnValue());
                            myTaskResponse.setMessage(response.getMessage());
                            myTaskResponse.setObject(response.getObject());
                            cityListResponseLiveData.setValue(myTaskResponse);
                        } else {
                            CityListResponse myTaskResponse = new CityListResponse();
                            myTaskResponse.setReturnValue("001");
                            myTaskResponse.setMessage(response.getMessage());
                            cityListResponseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
        return cityListResponseLiveData;
    }


    private MutableLiveData<LogisticListResponse> logisticListResponseLiveData = new MutableLiveData<>();

    public MutableLiveData<LogisticListResponse> getLogisticListResponseLiveData() {
        return logisticListResponseLiveData;
    }

    public LiveData<LogisticListResponse> logisticList(String origin, String destination, int weight, String courier) {
        application.getApiSalesService().logisticList(PrefHelper.getString(PrefKey.TOKEN), origin, destination, weight, courier)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LogisticListResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        LogisticListResponse myTaskResponse = new LogisticListResponse();
                        myTaskResponse.setReturnValue("001");
                        logisticListResponseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(LogisticListResponse response) {
                        if (response.isSuccess()) {
                            LogisticListResponse myTaskResponse = new LogisticListResponse();
                            myTaskResponse.setReturnValue(response.getReturnValue());
                            myTaskResponse.setMessage(response.getMessage());
                            myTaskResponse.setObject(response.getObject());
                            logisticListResponseLiveData.setValue(myTaskResponse);
                        } else {
                            LogisticListResponse myTaskResponse = new LogisticListResponse();
                            myTaskResponse.setReturnValue("001");
                            myTaskResponse.setMessage(response.getMessage());
                            logisticListResponseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
        return logisticListResponseLiveData;
    }


    private MutableLiveData<OrderResponse> orderResponseLiveData = new MutableLiveData<>();

    public MutableLiveData<OrderResponse> getOrderResponseLiveData() {
        return orderResponseLiveData;
    }

    public LiveData<OrderResponse> detailOrder(Order order) {
        application.getApiSalesService().detailOrder(PrefHelper.getString(PrefKey.TOKEN), order.getUuid())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrderResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        OrderResponse myTaskResponse = new OrderResponse();
                        myTaskResponse.setReturnValue("001");
                        orderResponseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(OrderResponse response) {
                        if (response.isSuccess()) {
                            OrderResponse myTaskResponse = new OrderResponse();
                            myTaskResponse.setReturnValue(response.getReturnValue());
                            myTaskResponse.setMessage(response.getMessage());
                            myTaskResponse.setObject(response.getObject());
                            orderResponseLiveData.setValue(myTaskResponse);
                        } else {
                            OrderResponse myTaskResponse = new OrderResponse();
                            myTaskResponse.setReturnValue("001");
                            myTaskResponse.setMessage(response.getMessage());
                            orderResponseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
        return orderResponseLiveData;
    }

    public LiveData<OrderResponse> supplierDetailOrder(Order order) {
        application.getApiSalesService().supplierDetailOrder(PrefHelper.getString(PrefKey.TOKEN), order.getUuid())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrderResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        OrderResponse myTaskResponse = new OrderResponse();
                        myTaskResponse.setReturnValue("001");
                        orderResponseLiveData.setValue(myTaskResponse);
                    }

                    @Override
                    public void onNext(OrderResponse response) {
                        if (response.isSuccess()) {
                            OrderResponse myTaskResponse = new OrderResponse();
                            myTaskResponse.setReturnValue(response.getReturnValue());
                            myTaskResponse.setMessage(response.getMessage());
                            myTaskResponse.setObject(response.getObject());
                            orderResponseLiveData.setValue(myTaskResponse);
                        } else {
                            OrderResponse myTaskResponse = new OrderResponse();
                            myTaskResponse.setReturnValue("001");
                            myTaskResponse.setMessage(response.getMessage());
                            orderResponseLiveData.setValue(myTaskResponse);
                        }
                    }
                });
        return orderResponseLiveData;
    }

    public LiveData<SupplierResponse> supplierDeleteImageProduct(String uuid) {
        application.getApiSalesService().supplierDeleteImageProduct(PrefHelper.getString(PrefKey.TOKEN), uuid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SupplierResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        SupplierResponse supplierResponse = new SupplierResponse();
                        supplierResponse.setReturnValue("001");
                        supplierResponseLiveData.setValue(supplierResponse);
                    }

                    @Override
                    public void onNext(SupplierResponse response) {
                        if (response.isSuccess()) {
                            response.setRequestKey(SupplierResponse.RequestKey.DELETE_IMAGE);
                            supplierResponseLiveData.setValue(response);
                        } else {
                            supplierResponseLiveData.setValue(response);
                        }
                    }
                });
        return supplierResponseLiveData;
    }


    public void saveAllQuestToDatabase(List<SectionSourceResponse> sectionSourceResponses) {
        new SaveAsyncTaskSectionSourceAll(sectionSourceDao).execute(sectionSourceResponses);
    }

    public void saveQuestToDatabaseV2(SectionSourceResponse sectionSourceResponse) {
        new SaveAsyncTaskSectionSource(sectionSourceDao).execute(sectionSourceResponse);
    }

    public void updateQuestToDatabaseV2(SectionSourceResponse sectionSourceResponse, Integer idJob, String uuidSession) {
        new UpdateAsyncTaskSectionSource(sectionSourceDao, idJob, uuidSession).execute(sectionSourceResponse);
    }

    public void updateQuestToDatabaseByIdTaskV2(SectionSourceResponse sectionSourceResponse, String id_task) {
        new UpdateAsyncTaskSectionSourceByIdTask(sectionSourceDao, id_task).execute(sectionSourceResponse);
    }

    public void deleteQuestToDatabaseV2(SectionSourceResponse sectionSourceResponse, Integer idJob, String uuidSession) {
        new DeleteAsyncTaskSectionSource(sectionSourceDao, idJob, uuidSession).execute(sectionSourceResponse);
    }

    public void deleteQuestToDatabaseByIdTaskV2(SectionSourceResponse sectionSourceResponse, String id_task) {
        new DeleteAsyncTaskSectionSourceByIdTask(sectionSourceDao, id_task).execute(sectionSourceResponse);
    }

    public void saveQuestToDatabase(List<Section> sections) {
        new SaveAsyncTaskSection(sectionDao).execute(sections);
    }

    private void saveToDatabase(List<Task> task) {
        new SaveAsyncTask(taskDao).execute(task);
    }

    public void updateUser(User user) {
        new UpdateUserAsyncTask(userDao).execute(user);
    }

    public void updateTask(Task task) {
        new UpdateAsyncTask(taskDao).execute(task);
    }

    public void insertTask(Task task) {
        new SaveSingleAsyncTask(taskDao).execute(task);
    }

    public void insertPendingTask(PendingTask pendingTask) {
        new SaveSinglePendingJobAsyncTask(taskPendingDao).execute(pendingTask);
    }

    public void deletePendingTask(PendingTask pendingTask, Integer id, String uuidSession) {
        new DeleteSinglePendingJobAsyncTask(taskPendingDao, id, uuidSession).execute(pendingTask);
    }

    public void clearTask() {
        new ClearDBAsyncTask(taskDao).execute();
    }

    private static class SaveSinglePendingJobAsyncTask extends AsyncTask<PendingTask, Void, Void> {

        private TaskPendingDao taskPendingDao;

        private SaveSinglePendingJobAsyncTask(TaskPendingDao taskPendingDao) {
            this.taskPendingDao = taskPendingDao;
        }

        @Override
        protected Void doInBackground(PendingTask... pendingTasks) {
            System.out.println("PEDING TASKSS " + pendingTasks[0]);
            if (pendingTasks[0] != null) {
                taskPendingDao.save(pendingTasks[0]);
            }
            return null;
        }
    }

    private static class DeleteSinglePendingJobAsyncTask extends AsyncTask<PendingTask, Void, Void> {

        private TaskPendingDao taskPendingDao;
        private Integer idJob;
        private String uuidSession;

        private DeleteSinglePendingJobAsyncTask(TaskPendingDao taskPendingDao, Integer idJob, String uuidSession) {
            this.taskPendingDao = taskPendingDao;
            this.idJob = idJob;
            this.uuidSession = uuidSession;
        }

        @Override
        protected Void doInBackground(PendingTask... pendingTasks) {
            if (pendingTasks[0] != null) {
                taskPendingDao.deleteByUserId(idJob, uuidSession);
            }
            return null;
        }
    }

    private static class SaveSingleAsyncTask extends AsyncTask<Task, Void, Void> {

        private TaskDao taskDao;

        private SaveSingleAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            if (tasks[0] != null) {
                taskDao.save(tasks[0]);
            }
            return null;
        }
    }

    private static class SaveAsyncTask extends AsyncTask<List<Task>, Void, Void> {

        private TaskDao taskDao;

        private SaveAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(List<Task>... tasks) {
            taskDao.deleteAll();
            System.out.println("SHIT TASK " + tasks[0].size());
            System.out.println(tasks[0] != null);
            if (tasks[0] != null) {
                taskDao.saveAll(tasks[0]);
            }
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Task, Void, Void> {

        private TaskDao taskDao;

        private UpdateAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.update(tasks[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Task, Void, Void> {

        private TaskDao taskDao;

        private DeleteAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.delete(tasks[0]);
            return null;
        }
    }

    private static class ClearDBAsyncTask extends AsyncTask<Void, Void, Void> {

        private TaskDao taskDao;

        private ClearDBAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.deleteAll();
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao userDao;

        private UpdateUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    private static class SaveAsyncTaskSection extends AsyncTask<List<com.mogawe.mosurvei.model.db.entity.Section>, Void, Void> {

        private SectionDao sectionDao;

        private SaveAsyncTaskSection(SectionDao sectionDao) {
            this.sectionDao = sectionDao;
        }

        @Override
        protected Void doInBackground(List<com.mogawe.mosurvei.model.db.entity.Section>... sections) {
//            sectionDao.deleteAll();
            if (sections[0] != null) {
                sectionDao.saveAll(sections[0]);
            }
            return null;
        }
    }

    private static class SaveAsyncTaskSectionSourceAll extends AsyncTask<List<SectionSourceResponse>, Void, Void> {

        private SectionSourceDao sectionSourceDao;

        private SaveAsyncTaskSectionSourceAll(SectionSourceDao sectionSourceDao) {
            this.sectionSourceDao = sectionSourceDao;
        }

        @Override
        protected Void doInBackground(List<SectionSourceResponse>... sectionSources) {
//            sectionDao.deleteAll();
            if (sectionSources[0] != null) {
                sectionSourceDao.saveAll(sectionSources[0]);
            }
            return null;
        }
    }

    private static class SaveAsyncTaskSectionSource extends AsyncTask<SectionSourceResponse, Void, Void> {

        private SectionSourceDao sectionSourceDao;

        private SaveAsyncTaskSectionSource(SectionSourceDao sectionSourceDao) {
            this.sectionSourceDao = sectionSourceDao;
        }

        @Override
        protected Void doInBackground(SectionSourceResponse... sectionSourceResponses) {
            if (sectionSourceResponses != null) {
                sectionSourceDao.save(sectionSourceResponses[0]);
            }
            return null;
        }
    }

    private static class UpdateAsyncTaskSectionSource extends AsyncTask<SectionSourceResponse, Void, Void> {

        private SectionSourceDao sectionSourceDao;
        private Integer idJob;
        private String uuidSession;

        private UpdateAsyncTaskSectionSource(SectionSourceDao sectionSourceDao, Integer idJob, String uuidSession) {
            this.sectionSourceDao = sectionSourceDao;
            this.idJob = idJob;
            this.uuidSession = uuidSession;
        }

        @Override
        protected Void doInBackground(SectionSourceResponse... sectionSourceResponses) {
            if (sectionSourceResponses != null) {
                System.out.println("idjob " + idJob);
                if (idJob != null) {
                    sectionSourceDao.updateById(sectionSourceResponses[0].getSections(), idJob, uuidSession);
                }
            }
            return null;
        }
    }

    private static class UpdateAsyncTaskSectionSourceByIdTask extends AsyncTask<SectionSourceResponse, Void, Void> {

        private SectionSourceDao sectionSourceDao;
        private String id_task;

        private UpdateAsyncTaskSectionSourceByIdTask(SectionSourceDao sectionSourceDao, String id_task) {
            this.sectionSourceDao = sectionSourceDao;
            this.id_task = id_task;
        }

        @Override
        protected Void doInBackground(SectionSourceResponse... sectionSourceResponses) {
            if (sectionSourceResponses != null) {
                if (id_task != null) {
                    sectionSourceDao.updateByIdTask(sectionSourceResponses[0].getSections(), id_task);
                }
            }
            return null;
        }
    }

    private static class DeleteAsyncTaskSectionSource extends AsyncTask<SectionSourceResponse, Void, Void> {

        private SectionSourceDao sectionSourceDao;
        private Integer idJob;
        private String uuidSession;

        private DeleteAsyncTaskSectionSource(SectionSourceDao sectionSourceDao, Integer idJob, String uuidSession) {
            this.sectionSourceDao = sectionSourceDao;
            this.idJob = idJob;
            this.uuidSession = uuidSession;
        }

        @Override
        protected Void doInBackground(SectionSourceResponse... sectionSourceResponses) {
            if (sectionSourceResponses != null) {
                if (idJob != null) {
                    sectionSourceDao.deleteByUserId(idJob, uuidSession);
                }
            }
            return null;
        }
    }

    private static class DeleteAsyncTaskSectionSourceByIdTask extends AsyncTask<SectionSourceResponse, Void, Void> {

        private SectionSourceDao sectionSourceDao;
        private String id_task;

        private DeleteAsyncTaskSectionSourceByIdTask(SectionSourceDao sectionSourceDao, String id_task) {
            this.sectionSourceDao = sectionSourceDao;
            this.id_task = id_task;
        }

        @Override
        protected Void doInBackground(SectionSourceResponse... sectionSourceResponses) {
            if (sectionSourceResponses != null) {
                if (id_task != null) {
                    sectionSourceDao.deleteByIdTask(id_task);
                }
            }
            return null;
        }
    }

}