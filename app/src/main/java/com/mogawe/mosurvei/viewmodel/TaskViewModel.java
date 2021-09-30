package com.mogawe.mosurvei.viewmodel;

import android.app.Application;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.android.gms.maps.model.LatLng;
import com.mogawe.mosurvei.MoSurveiApplication;
import com.mogawe.mosurvei.model.bean.Order;
import com.mogawe.mosurvei.model.db.entity.Catalog;
import com.mogawe.mosurvei.model.db.entity.PendingTask;
import com.mogawe.mosurvei.model.db.entity.Section;
import com.mogawe.mosurvei.model.db.entity.Task;
import com.mogawe.mosurvei.model.db.entity.User;
import com.mogawe.mosurvei.model.network.response.CatalogObjResponse;
import com.mogawe.mosurvei.model.network.response.JobAttachmentResponse;
import com.mogawe.mosurvei.model.network.response.JobRefCodeResponse;
import com.mogawe.mosurvei.model.network.response.OrderResponse;
import com.mogawe.mosurvei.model.network.response.ProductCategoryListResponse;
import com.mogawe.mosurvei.model.network.response.TaskResponse;
import com.mogawe.mosurvei.model.network.response.section.SectionSourceResponse;
import com.mogawe.mosurvei.model.network.response.shipment.CityListResponse;
import com.mogawe.mosurvei.model.network.response.shipment.LogisticListResponse;
import com.mogawe.mosurvei.model.network.response.shipment.ProvinceListResponse;
import com.mogawe.mosurvei.model.network.response.task.MyTaskResponse;
import com.mogawe.mosurvei.model.network.response.task.SupplierResponse;
import com.mogawe.mosurvei.model.repository.TaskRepository;
import com.mogawe.mosurvei.view.BaseActivity;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository taskRepository;
    private LiveData<MyTaskResponse> responseLiveData;
    private LiveData<List<Task>> taskLiveData;
    private LiveData<List<PendingTask>> taskPendingLiveDatas;
    private LiveData<List<Section>> sectionLiveData;
    private LiveData<List<SectionSourceResponse>> sectionAllSourceResponsesLiveData;
    private LiveData<SectionSourceResponse> sectionSourceResponseLiveData;
    private LiveData<SectionSourceResponse> sectionSourceResponseLiveDataByUuidSection;
    private ExecutorService executorService;


    private LiveData<TaskResponse> taskResponseLiveData;
    private LiveData<JobRefCodeResponse> refCodeResponseLiveData;
    private LiveData<JobAttachmentResponse> attachmentResponseLiveData;
    private LiveData<SupplierResponse> supplierResponseLiveData;
    private LiveData<ProductCategoryListResponse> productCategoryListResponseLiveData;
    private LiveData<CatalogObjResponse> catalogObjResponseLiveData;
    private LiveData<ProvinceListResponse> provinceListResponseLiveData;
    private LiveData<CityListResponse> cityListResponseLiveData;
    private LiveData<LogisticListResponse> logisticListResponseLiveData;
    private LiveData<OrderResponse> orderResponseLiveData;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        taskRepository = new TaskRepository((MoSurveiApplication) application);
        responseLiveData = taskRepository.getResponseLiveData();
        taskLiveData = taskRepository.getTaskLiveData();
        taskPendingLiveDatas = taskRepository.getTaskPendingLiveDatas();
        sectionLiveData = taskRepository.getSectionLiveData();

        refCodeResponseLiveData = taskRepository.getRefCodeResponseLiveData();
        attachmentResponseLiveData = taskRepository.getAttachmentResponseLiveData();
        taskResponseLiveData = taskRepository.getTaskResponseLiveData();


        sectionAllSourceResponsesLiveData = taskRepository.getAllSectionSourceLiveData();
        executorService = Executors.newSingleThreadExecutor();

        supplierResponseLiveData = taskRepository.getSupplierResponseLiveData();
        productCategoryListResponseLiveData = taskRepository.getProductCategoryListResponseLiveData();
        catalogObjResponseLiveData = taskRepository.getCatalogResponseLiveData();

        provinceListResponseLiveData = taskRepository.getProvinceListResponseLiveData();
        cityListResponseLiveData = taskRepository.getCityListResponseLiveData();
        logisticListResponseLiveData = taskRepository.getLogisticListResponseLiveData();
        orderResponseLiveData = taskRepository.getOrderResponseLiveData();
    }

    public void getJobRefCode(String uuidTask) {
        refCodeResponseLiveData = taskRepository.getRefCode(uuidTask);
    }

    public void getAttachment(String uuidTask) {
        attachmentResponseLiveData = taskRepository.getAttachments(uuidTask);
    }

    public void detailJob(String uuidJob) {
        taskResponseLiveData = taskRepository.detailJob(uuidJob);
    }

    public void getTaskToday() {
        taskRepository.getTaskToday();
    }

    public void getTaskToDo(Integer page, Integer offset, Double lat, Double lng) {
        taskRepository.getTaskToDo(page, offset, lat, lng);
    }

    public void updateSequenceToDoList(List<String> uuid) {
        taskRepository.updateSequenceToDoList(uuid);
    }

    public void getTaskFromServer(Integer page, Integer offset) {
        taskRepository.getFromServer(page, offset);
    }

    public void getGaweanStarting(Integer page, Integer offset) {
        taskRepository.getGaweanStarting(page, offset);
    }

    public void getSectionDirect(BaseActivity baseActivity, Task taskSection) {
        taskRepository.getTaskSectionDirect(baseActivity, taskSection);
    }

    public void getSection(BaseActivity baseActivity, Task taskSection, String uuidSession) {
        taskRepository.getTaskSection(baseActivity, taskSection, uuidSession);
    }

    public void getSectionForSavingToDB(BaseActivity baseActivity, Task taskSection, String uuidSession) {
        taskRepository.getTaskSectionForSavingToDB(baseActivity, taskSection, uuidSession);
    }

    public void getAllCategoryGawean(Double lat, Double lng, Integer page, Integer offset) {
        taskRepository.getAllCategoryGawean(lat, lng, page, offset);
    }

    public void getWidgetRow(Double lat, Double lng, Integer page, Integer offset) {
        taskRepository.getWidgetRow(lat, lng, page, offset);
    }

    public void getSearchItemFacing(String uuidFact, String produk, Integer page, Integer offset, String uuidSelectedFacingAdapter, Integer idFacingItem) {
        taskRepository.getSearchItemFacing(uuidFact, produk, page, offset, uuidSelectedFacingAdapter, idFacingItem);
    }

    public void getSearchItemPresence(String uuidFact, String produk, Integer page, Integer offset) {
        taskRepository.getSearchItemPresence(uuidFact, produk, page, offset);
    }

    public void getlocationSpotCheck(String uuidFact, Double lat, Double lng) {
        taskRepository.getlocationSpotCheck(uuidFact, lat, lng);
    }

//    public void getJobByCategory(Double lat, Double lng, String uuidCategories) {
//        taskRepository.getJobByCategory(lat, lng, uuidCategories);
//    }

    public void getGaweankuByCategory(String uuid, Integer page, Integer offset) {
        taskRepository.getGaweankuByCategory(uuid, page, offset);
    }

    public void getJobByCategoryV2(Double lat, Double lng, String uuidCategories, Integer page, Integer offset) {
        taskRepository.getJobByCategoryV2(lat, lng, uuidCategories, page, offset, null);
    }

    public void getJobByCategoryV2(Double lat, Double lng, String uuidCategories, Integer page, Integer offset, String activeJobCategory) {
        taskRepository.getJobByCategoryV2(lat, lng, uuidCategories, page, offset, activeJobCategory);
    }

    public void getAdsByCategory(String uuidCategories) {
        taskRepository.getAdsByCategories(uuidCategories);
    }

    public void startGawean(Task task, Long timestamp) {
        System.out.println("OUT >>> 2");
        System.out.println(responseLiveData);
        System.out.println(taskLiveData);
        taskRepository.startGawean(task, timestamp);
    }

    public void startGaweanPending(Task task, Long timestamp) {
        taskRepository.startGaweanPending(task, timestamp);
    }

    public void startGaweanOffline(Task task, Long timestamp) {
        taskRepository.startGaweanOffline(task, timestamp);
    }

    public void closeGawean(Task task, String idResult, User user) {
        taskRepository.closeGawean(task, idResult, user);
    }

    public void closeJob(String uuidTask) {
        taskRepository.closeJob(uuidTask);
    }

    public void verifyChatRoom(String uuidJob) {
        taskRepository.verifyChatRoom(uuidJob);
    }

    public void saveChatRoom(String uuidJob, long chatRoomId) {
        System.out.println("sfdsaadd");
        taskRepository.saveChatRoom(uuidJob, chatRoomId);
    }

    public void historyJobInfo(String uuidTask) {
        taskRepository.historyJobInfo(uuidTask);
    }

    public void cancelGawean(Task task) {
        taskRepository.cancelGawean(task);
    }

    public void applyGawean(Task task, Location myPosition) {
        taskRepository.applyGawean(task, 1, null, myPosition, false);
    }

    public void applyGaweanBatch(Task task, Integer batchCount, Integer batchProgress, Location myPosition) {
        taskRepository.applyGawean(task, batchCount, batchProgress, myPosition, true);
    }

    public void pinTask(Task task, boolean isPinned) {
        taskRepository.pinTask(task, isPinned);
    }

    public void getTaskPolyline(Task task) {
        taskRepository.getTaskPolyline(task);
    }

    public void getSpecificProject(String projectName, LatLng myPosition, LatLng centerPosition) {
        taskRepository.getSpesificProject(projectName, myPosition, centerPosition);
    }

    public void searchGawean(LatLng myPosition, Integer upToFee, Integer radius, String project, Integer jobPage, List<String> uuidLocation, List<String> uuidClassJob, String jobName) {
        taskRepository.searchGawean(myPosition, upToFee, radius, project, jobPage, uuidLocation, uuidClassJob, jobName);
    }

    public void searchGaweanV2(LatLng myPosition, Integer upToFee, Integer radius, String project, Integer jobPage, List<String> uuidLocation, List<String> uuidClassJob, String jobName) {
        taskRepository.searchGaweanV2(myPosition, upToFee, radius, project, jobPage, uuidLocation, uuidClassJob, jobName);
    }

    public void confirmOrder(String to) {
        taskRepository.confirmOrder(to);
    }

    public void getActiveOrder() {
        taskRepository.getActiveOrder();
    }

    public void searchGaweanGrid(LatLng myPosition, Integer upToFee, Integer radius, String project, Integer jobPage, List<String> uuidLocation, List<String> uuidClassJob, String jobName) {
        taskRepository.searchGaweanGrid(myPosition, upToFee, radius, project, jobPage, uuidLocation, uuidClassJob, jobName);
    }

    public void getCategoryProduct(Integer jobPage) {
        taskRepository.getCategoryProduct(jobPage);
    }

    public void getTimelineActivity(Integer timelinePage, String type, String uuidMoGawers) {
        taskRepository.getTimelineActivity(timelinePage, type, uuidMoGawers);
    }

    public void getGaweanInstant(Integer page) {
        taskRepository.getGaweanInstant(page);
    }

    public void getGaweanInstantInformation(String uuid) {
        taskRepository.getGaweanInstantDetail(uuid);
    }

    public void activateInstantGawean(String uuidGawean) {
        taskRepository.activateInstantGawean(uuidGawean);
    }

    public void deactivateInstantGawean(String uuidGawean) {
        taskRepository.deactivateInstantGawean(uuidGawean);
    }

    public void acceptGaweanOffer(String uuidQueue, String uuidJob) {
        taskRepository.acceptGaweanOffer(uuidQueue, uuidJob);
    }

    public void declineGaweanOffer(String uuidQueue, String uuidJob) {
        taskRepository.declineGaweanOffer(uuidQueue, uuidJob);
    }

    public void likePostTimeline(String uuidPostTimeline) {
        taskRepository.likePostTimeline(uuidPostTimeline);
    }

    public void unlikePostTimeline(String uuidPostTimeline) {
        taskRepository.unlikePostTimeline(uuidPostTimeline);
    }

    public void postTimelineStatus(String content, String uuidMoGawers, String uuidPostType) {
        taskRepository.postTimelineStatus(content, uuidMoGawers, uuidPostType);
    }

    public void getLocationList(String uuid, LatLng latLng, Integer jobPage) {
        taskRepository.getLocationList(uuid, latLng.latitude, latLng.longitude, jobPage);
    }

    public void connectToMoGawers(String uuidMoGawers) {
        taskRepository.connectToMoGawers(uuidMoGawers);
    }

    public void disconnectToMoGawers(String uuidMoGawers) {
        taskRepository.disconnectToMoGawers(uuidMoGawers);
    }

    public void goingTo(String uuidTask) {
        taskRepository.goingTo(uuidTask);
    }

    public void cancelTo(String uuidTask) {
        taskRepository.cancelTo(uuidTask);
    }

    public void getGaweanInformation(String uuidTask) {
        taskRepository.getGaweanInformation(uuidTask);
    }

    public void getGaweanInformationByIdJob(String uuidTask) {
        taskRepository.getGaweanInformationByIdJob(uuidTask);
    }

    public void getSubCategoryList(String uuid, LatLng latLng, Integer jobPage) {
        taskRepository.getSubCategoryList(uuid, latLng.latitude, latLng.longitude, jobPage);
    }

    public void saveInputQuest(List<Section> sections) {
        taskRepository.saveQuestToDatabase(sections);
    }

    public void saveQuestionerV2(SectionSourceResponse sectionSourceResponse) {
        taskRepository.saveQuestToDatabaseV2(sectionSourceResponse);
    }

    public void updateQuestionerV2(SectionSourceResponse sectionSourceResponse, Integer idJob, String uuidSession) {
        taskRepository.updateQuestToDatabaseV2(sectionSourceResponse, idJob, uuidSession);
    }

    public void saveQuestToDatabaseV2(SectionSourceResponse sectionSourceResponse) {
        taskRepository.saveQuestToDatabaseV2(sectionSourceResponse);
    }


    public void updateQuestionerByIdTaskV2(SectionSourceResponse sectionSourceResponse, String id_task) {
        taskRepository.updateQuestToDatabaseByIdTaskV2(sectionSourceResponse, id_task);
    }

    public void deleteQuestionerV2(SectionSourceResponse sectionSourceResponse, Integer idJob, String uuidSession) {
        taskRepository.deleteQuestToDatabaseV2(sectionSourceResponse, idJob, uuidSession);
    }

    public void deleteQuestToDatabaseByIdTaskV2(SectionSourceResponse sectionSourceResponse, String id_task) {
        taskRepository.deleteQuestToDatabaseByIdTaskV2(sectionSourceResponse, id_task);
    }

    public void insertPendingTask(PendingTask pendingTask) {
        taskRepository.insertPendingTask(pendingTask);
    }

    public void deletePendingTask(PendingTask pendingTask, Integer id, String uuidSession) {
        taskRepository.deletePendingTask(pendingTask, id, uuidSession);
    }

    public void supplierRegistrationPost(String storeName, String storeAddress, String storeLat, String storeLng) {
        taskRepository.supplierRegistrationPost(storeName, storeAddress, storeLat, storeLng);
    }

    public void supplierCreateProduct(Catalog catalog) {
        catalogObjResponseLiveData = taskRepository.supplierCreateProduct(catalog);
    }

    public void supplierEditProduct(Catalog catalog) {
        catalogObjResponseLiveData = taskRepository.supplierEditProduct(catalog);
    }

    public void getDetailProduct(Catalog catalog) {
        catalogObjResponseLiveData = taskRepository.getDetailProduct(catalog);
    }

    public void publishProduct(Catalog catalog) {
        catalogObjResponseLiveData = taskRepository.publishProduct(catalog);
    }

    public void unpublishProduct(Catalog catalog) {
        catalogObjResponseLiveData = taskRepository.unpublishProduct(catalog);
    }

    public void deleteProduct(Catalog catalog) {
        catalogObjResponseLiveData = taskRepository.deleteProduct(catalog);
    }

    public void checkoutOrder(Order order) {
        supplierResponseLiveData = taskRepository.checkoutOrder(order);
    }

    public void provinceList() {
        provinceListResponseLiveData = taskRepository.provinceList();
    }

    public void cityList() {
        cityListResponseLiveData = taskRepository.cityList();
    }

    public void logisticList(String origin, String destination, int weight, String courier) {
        logisticListResponseLiveData = taskRepository.logisticList(origin, destination, weight, courier);
    }

    public void detailOrder(Order order) {
        orderResponseLiveData = taskRepository.detailOrder(order);
    }

    public void supplierDetailOrder(Order order) {
        orderResponseLiveData = taskRepository.supplierDetailOrder(order);
    }


    public void supplierUploadImagesProduct(File images) {
        supplierResponseLiveData = taskRepository.supplierUploadImagesProduct(images);
    }

    public void supplierDeleteImageProduct(String uuid) {
        supplierResponseLiveData = taskRepository.supplierDeleteImageProduct(uuid);
    }

    public void getCategoryProductSupplier() {
        productCategoryListResponseLiveData = taskRepository.getCategoryProductSupplier();
    }


    public LiveData<MyTaskResponse> getResponseLiveData() {
        return responseLiveData;
    }

    public LiveData<MyTaskResponse> get() {
        return responseLiveData;
    }

    public LiveData<List<Task>> getTask() {
        return taskLiveData;
    }

    public LiveData<List<PendingTask>> getTaskPending() {
        return taskPendingLiveDatas;
    }

    public LiveData<List<Section>> getQuestSection() {
        return sectionLiveData;
    }

    public LiveData<List<SectionSourceResponse>> getAllQuestSectionSource() {
        return sectionAllSourceResponsesLiveData;
    }

    public LiveData<SectionSourceResponse> getQuestSectionSource(Integer idJob) {
        sectionSourceResponseLiveData = taskRepository.getSectionSourceLiveData(idJob);
        return sectionSourceResponseLiveData;
    }

    public LiveData<SectionSourceResponse> getQuestSectionSourceByIdTask(String idTask, Integer jobId) {
        sectionSourceResponseLiveData = taskRepository.getSectionSourceByIdTaskLiveData(idTask, jobId);
        return sectionSourceResponseLiveData;
    }

    public LiveData<SectionSourceResponse> getQuestSectionSourceById(Integer idSection) {
        sectionSourceResponseLiveDataByUuidSection = taskRepository.getSectionSourceLiveDataById(idSection);
        return sectionSourceResponseLiveDataByUuidSection;
    }

    public LiveData<SectionSourceResponse> getQuestSectionSourceByUuid(String uuidSession) {
        sectionSourceResponseLiveDataByUuidSection = taskRepository.getSectionSourceLiveDataByUuidSession(uuidSession);
        return sectionSourceResponseLiveDataByUuidSection;
    }

    public LiveData<JobRefCodeResponse> getRefCodeResponseLiveData() {
        return refCodeResponseLiveData;
    }

    public void setRefCodeResponseLiveData(LiveData<JobRefCodeResponse> refCodeResponseLiveData) {
        this.refCodeResponseLiveData = refCodeResponseLiveData;
    }

    public LiveData<JobAttachmentResponse> getAttachmentResponseLiveData() {
        return attachmentResponseLiveData;
    }

    public void setAttachmentResponseLiveData(LiveData<JobAttachmentResponse> attachmentResponseLiveData) {
        this.attachmentResponseLiveData = attachmentResponseLiveData;
    }

    public LiveData<TaskResponse> getTaskResponseLiveData() {
        return taskResponseLiveData;
    }

    public void setTaskResponseLiveData(LiveData<TaskResponse> taskResponseLiveData) {
        this.taskResponseLiveData = taskResponseLiveData;
    }

    public LiveData<SupplierResponse> getSupplierResponseLiveData() {
        return supplierResponseLiveData;
    }

    public LiveData<ProductCategoryListResponse> getProductCategoryListResponseLiveData() {
        return productCategoryListResponseLiveData;
    }

    public LiveData<CatalogObjResponse> getCatalogObjResponseLiveData() {
        return catalogObjResponseLiveData;
    }

    public void setCatalogObjResponseLiveData(LiveData<CatalogObjResponse> catalogObjResponseLiveData) {
        this.catalogObjResponseLiveData = catalogObjResponseLiveData;
    }

    public LiveData<ProvinceListResponse> getProvinceListResponseLiveData() {
        return provinceListResponseLiveData;
    }

    public LiveData<CityListResponse> getCityListResponseLiveData() {
        return cityListResponseLiveData;
    }

    public LiveData<LogisticListResponse> getLogisticListResponseLiveData() {
        return logisticListResponseLiveData;
    }

    public LiveData<OrderResponse> getOrderResponseLiveData() {
        return orderResponseLiveData;
    }

    public void clear() {
        taskRepository.clearTask();
    }
}
