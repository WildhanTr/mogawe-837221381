package com.mogawe.mosurvei.model.network;

import androidx.room.Update;

import com.mogawe.mosurvei.model.bean.Order;
import com.mogawe.mosurvei.model.bean.gawean.GaweanListResponse;
import com.mogawe.mosurvei.model.db.entity.Catalog;
import com.mogawe.mosurvei.model.db.entity.Supplier;
import com.mogawe.mosurvei.model.db.entity.User;
import com.mogawe.mosurvei.model.network.request.ChangePasswordRequest;
import com.mogawe.mosurvei.model.network.request.FieldTaskToDoRequest;
import com.mogawe.mosurvei.model.network.request.PostStatusRequest;
import com.mogawe.mosurvei.model.network.request.Result.ResultHistoryRequest;
import com.mogawe.mosurvei.model.network.request.point.RedeemRequest;
import com.mogawe.mosurvei.model.network.request.sales.Shipment;
import com.mogawe.mosurvei.model.network.request.section.SectionSubmitRequest;
import com.mogawe.mosurvei.model.network.request.task.GetAllCategoriesRequest;
import com.mogawe.mosurvei.model.network.request.task.GetContentByCategoriesRequest;
import com.mogawe.mosurvei.model.network.request.task.GetJobCategoryRequest;
import com.mogawe.mosurvei.model.network.request.task.MyTaskRequest;
import com.mogawe.mosurvei.model.network.request.task.SJROfferRequest;
import com.mogawe.mosurvei.model.network.request.transaction.CashOutRequest;
import com.mogawe.mosurvei.model.network.request.transaction.GetPendingPaymentRequest;
import com.mogawe.mosurvei.model.network.request.transaction.WalletHistoryRequest;
import com.mogawe.mosurvei.model.network.request.user.ActivationRequest;
import com.mogawe.mosurvei.model.network.request.user.CitcalMisscallRequest;
import com.mogawe.mosurvei.model.network.request.user.GetDownlineRequest;
import com.mogawe.mosurvei.model.network.request.user.LoginRequest;
import com.mogawe.mosurvei.model.network.request.user.LoginSocMedRequest;
import com.mogawe.mosurvei.model.network.request.user.NearJobRequest;
import com.mogawe.mosurvei.model.network.request.user.PhoneActivationRequest;
import com.mogawe.mosurvei.model.network.request.user.ProfileRequest;
import com.mogawe.mosurvei.model.network.request.user.RegistrationRequest;
import com.mogawe.mosurvei.model.network.request.user.ReportErrorRequest;
import com.mogawe.mosurvei.model.network.request.user.ResendActivationRequest;
import com.mogawe.mosurvei.model.network.request.user.ResetPasswordRequest;
import com.mogawe.mosurvei.model.network.request.user.SaveChatRoomRequest;
import com.mogawe.mosurvei.model.network.request.user.TransactionRequest;
import com.mogawe.mosurvei.model.network.request.user.UpdateDeviceIdRequest;
import com.mogawe.mosurvei.model.network.request.user.UpdatePhoneRequest;
import com.mogawe.mosurvei.model.network.request.user.UpdateProfileRequest;
import com.mogawe.mosurvei.model.network.request.user.UpdateTrackingRequest;
import com.mogawe.mosurvei.model.network.response.CatalogObjResponse;
import com.mogawe.mosurvei.model.network.response.CertificateCountResponse;
import com.mogawe.mosurvei.model.network.response.CertificateListResponse;
import com.mogawe.mosurvei.model.network.response.CitcallResponse;
import com.mogawe.mosurvei.model.network.response.DownlinerResponse;
import com.mogawe.mosurvei.model.network.response.FactPollResponse;
import com.mogawe.mosurvei.model.network.response.GaweanInstantDetailResponse;
import com.mogawe.mosurvei.model.network.response.GaweanInstantResponse;
import com.mogawe.mosurvei.model.network.response.GaweanResponse;
import com.mogawe.mosurvei.model.network.response.GaweanResponseV2;
import com.mogawe.mosurvei.model.network.response.GawenInformationResponse;
import com.mogawe.mosurvei.model.network.response.GenericResp;
import com.mogawe.mosurvei.model.network.response.GenericResponse;
import com.mogawe.mosurvei.model.network.response.GeoLocationSpotCheckResponse;
import com.mogawe.mosurvei.model.network.response.HistoryResultResponse;
import com.mogawe.mosurvei.model.network.response.ItemResponse;
import com.mogawe.mosurvei.model.network.response.JobAttachmentResponse;
import com.mogawe.mosurvei.model.network.response.JobRefCodeResponse;
import com.mogawe.mosurvei.model.network.response.LocationResponse;
import com.mogawe.mosurvei.model.network.response.OrderResponse;
import com.mogawe.mosurvei.model.network.response.ProductCategoryListResponse;
import com.mogawe.mosurvei.model.network.response.ProductResponse;
import com.mogawe.mosurvei.model.network.response.SupplierDashboardCountResponse;
import com.mogawe.mosurvei.model.network.response.TaskResponse;
import com.mogawe.mosurvei.model.network.response.TaskToDoResponse;
import com.mogawe.mosurvei.model.network.response.UserResponse;
import com.mogawe.mosurvei.model.network.response.VerifyChatRoomResponse;
import com.mogawe.mosurvei.model.network.response.job.FeaturedJobResponse;
import com.mogawe.mosurvei.model.network.response.point.DealTransactionResponse;
import com.mogawe.mosurvei.model.network.response.point.DealsResponse;
import com.mogawe.mosurvei.model.network.response.point.DealsTransactionsResponse;
import com.mogawe.mosurvei.model.network.response.sales.CatalogDetailResponse;
import com.mogawe.mosurvei.model.network.response.sales.CatalogResponse;
import com.mogawe.mosurvei.model.network.response.sales.CategoryProductResponse;
import com.mogawe.mosurvei.model.network.response.sales.DetailSummaryResponse;
import com.mogawe.mosurvei.model.network.response.sales.OrdersResponse;
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
import com.mogawe.mosurvei.model.network.response.transaction.PendingPaymentResponse;
import com.mogawe.mosurvei.model.network.response.transaction.TransactionResponse;
import com.mogawe.mosurvei.model.network.response.transaction.WalletHistoryResponse;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

public class Service {


    public static final String PROFILE_IMAGE_URL = "https://mogawe.id/content/profile/";
    public static final String AGREEMENT_URL = "https://mogawe.id/partnership.html";
    private static final String USER_CITCALL_SERVICE_URL = "http://104.199.196.122/gateway/v3/";

    //LIVE
    private static final String SERVICE_URL = "https://backend-service-live-dot-mogawe-222614.appspot.com/";

    private static final String PROJECT_SERVICE_URL = "https://backend-service-live-dot-mogawe-222614.appspot.com/";

    private ApiUserService apiUserService;
    private ApiUserCitcallService apiUserCitcallService;
    private ApiTaskService apiTaskService;
    private ApiSectionService apiSectionService;
    private ApiProjectService apiProjectService;
    private ApiSearchService apiSearchService;
    private ApiSalesService apiSalesService;
    private ApiNaufalService apiNaufalService;

    public Service() {
        Retrofit retrofitUserService = new Retrofit.Builder()
                .client(provideOkHttpClient())
                .baseUrl("https://backend-service-live-dot-mogawe-222614.appspot.com/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.apiUserService = retrofitUserService.create(ApiUserService.class);

        Retrofit retrofitUserCitcallService = new Retrofit.Builder()
                .client(provideOkHttpClient())
                .baseUrl(USER_CITCALL_SERVICE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.apiUserCitcallService = retrofitUserCitcallService.create(ApiUserCitcallService.class);

        Retrofit retrofitTaskService = new Retrofit.Builder()
                .client(provideOkHttpClient())
                .baseUrl(SERVICE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.apiTaskService = retrofitTaskService.create(ApiTaskService.class);

        Retrofit retrofitSectionService = new Retrofit.Builder()
                .client(provideOkHttpClient())
                .baseUrl(SERVICE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.apiSectionService = retrofitSectionService.create(ApiSectionService.class);

        Retrofit retrofitProjectService = new Retrofit.Builder()
                .client(provideOkHttpClient())
                .baseUrl(PROJECT_SERVICE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.apiProjectService = retrofitProjectService.create(ApiProjectService.class);

        Retrofit retrofitSearchService = new Retrofit.Builder()
                .client(provideOkHttpClient())
                .baseUrl(SERVICE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.apiSearchService = retrofitSearchService.create(ApiSearchService.class);

        Retrofit retrofitSalesService = new Retrofit.Builder()
                .client(provideOkHttpClient())
                .baseUrl(SERVICE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.apiSalesService = retrofitSalesService.create(ApiSalesService.class);

        Retrofit retrofitNaufalService = new Retrofit.Builder()
                .client(provideOkHttpClient())
                .baseUrl("https://kitadakwah.com/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.apiNaufalService = retrofitNaufalService.create(ApiNaufalService.class);
    }

    private OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(120, TimeUnit.SECONDS); // connect timeout
        httpClient.readTimeout(600, TimeUnit.SECONDS);    // socket timeout
        httpClient.addInterceptor(interceptor);
        return httpClient.build();
    }

    public ApiUserService getApiUserService() {
        return apiUserService;
    }

    public ApiUserCitcallService getApiUserCitcallService() {
        return apiUserCitcallService;
    }

    public ApiTaskService getApiTaskService() {
        return apiTaskService;
    }

    public ApiSectionService getApiSectionService() {
        return apiSectionService;
    }

    public ApiProjectService getApiProjectService() {
        return apiProjectService;
    }

    public ApiSearchService getApiSearchService() {
        return apiSearchService;
    }

    public ApiSalesService getApiSalesService() {
        return apiSalesService;
    }

    public ApiNaufalService getApiNaufalService() {
        return apiNaufalService;
    }

    //    public interface Api {
//
//        @FormUrlEncoded
//        @POST("mib/register")
//        Observable<GenericResp> register(@Field("username") String username,
//                                         @Field("api_key") String api_key,
//                                         @Field("email") String email,
//                                         @Field("nama_lengkap") String nama_lengkap,
//                                         @Field("no_handphone") String no_handphone,
//                                         @Field("gender") String gender,
//                                         @Field("birth_date") String birth_date,
//                                         @Field("education") String education,
//                                         @Field("research_exp") String research_exp,
//                                         @Field("biz_location") String biz_location,
//                                         @Field("prefer_location") String prefer_location,
//                                         @Field("password") String password,
//                                         @Field("lat") String lat,
//                                         @Field("lng") String lng,
//                                         @Field("city") String city,
//                                         @Field("address") String address,
//                                         @Field("q1") String q1, //has_using_camera
//                                         @Field("q2") String q2, //has_doing_interview
//                                         @Field("q3") String q3, //has_counting_stock
//                                         @Field("q4") String q4); //has_shopping_in_mini_market
//
//        @FormUrlEncoded
//        @POST("mib/logged/my-job")
//        Observable<GaweanListResp> gaweanJob(@Field("username") String username,
//                                             @Field("api_key") String api_key,
//                                             @Field("token") String token,
//                                             @Field("latitude") String latitude,
//                                             @Field("longitude") String longitude);
//
//        @FormUrlEncoded
//        @POST("mib/logged/apply")
//        Observable<GenericResp> applyJob(@Field("username") String username,
//                                         @Field("api_key") String api_key,
//                                         @Field("token") String token,
//                                         @Field("jobID") String jobID);
//
//        @FormUrlEncoded
//        @POST("mib/logged/locate-store")
//        Observable<BursaListResp> findJob(@Field("username") String username,
//                                          @Field("api_key") String api_key,
//                                          @Field("token") String token,
//                                          @Field("has_position") String has_position,
//                                          @Field("curr_location") String curr_location,
//                                          @Field("srch_location") String srch_location,
//                                          @Field("city_code") String city_code);
//
//        @FormUrlEncoded
//        @POST("mib/logged/job-history")
//        Observable<JobListResp> getHistory(@Field("username") String username,
//                                           @Field("api_key") String api_key,
//                                           @Field("token") String token);
//
//
//        @FormUrlEncoded
//        @POST("api/data_capture/checkVersion")
//        Observable<CheckVersion> checkVersion(@Field("username") String username,
//                                              @Field("api_key") String api_key,
//                                              @Field("id_version") String id_version);
//
//
//        @FormUrlEncoded
//        @POST("mib/logged/get-section")
//        Observable<SectionResp> getSection(@Field("username") String username,
//                                           @Field("api_key") String api_key,
//                                           @Field("curr_location") String curr_location,
//                                           @Field("token") String token,
//                                           @Field("id_job") String id_job);
//
//        @FormUrlEncoded
//        @POST("mib/logged/save-data")
//        Observable<GenericResp> saveData(@Field("username") String username,
//                                         @Field("api_key") String api_key,
//                                         @Field("token") String token,
//                                         @Field("id_job") String jobId,
//                                         @Field("id_store") String storeID,
//                                         @Field("id_periode") String periodID,
//                                         @Field("facts") String facts);
//
//
//        @FormUrlEncoded
//        @POST("mib/logged/start")
//        Observable<GenericResp> startGawean(@Field("username") String username,
//                                            @Field("api_key") String api_key,
//                                            @Field("token") String token,
//                                            @Field("id_job") String jobId);
//
//        @FormUrlEncoded
//        @POST("mib/logged/end")
//        Observable<GenericResp> endGawean(@Field("username") String username,
//                                          @Field("api_key") String api_key,
//                                          @Field("token") String token,
//                                          @Field("id_job") String jobId,
//                                          @Field("id_observation") String idObservation,
//                                          @Field("id_fact") String facts);
//
//
//        @Multipart
//        @POST("mib/logged/save-image")
//        Observable<GenericResp> uploadPhoto(@Part("username") RequestBody username,
//                                            @Part("api_key") RequestBody api_key,
//                                            @Part("token") RequestBody token,
//                                            @Part("id_job") RequestBody id_job,
//                                            @Part("id_store") RequestBody id_store,
//                                            @Part("id_fact") RequestBody id_fact,
//                                            @Part("id_observation") RequestBody id_observation,
//                                            @Part MultipartBody.Part photo);
//
//        @FormUrlEncoded
//        @POST("mib/update-token")
//        Observable<GenericResp> updateToken(@Field("username") String username,
//                                            @Field("api_key") String api_key,
//                                            @Field("email") String email,
//                                            @Field("token") String token);
//
//    }

    public interface ApiUserService {

        @GET("api/mogawers/versionChecker")
        Observable<GenericResponse> checkVersion(@Query("versionName") String versionName);

        @GET("api/mogawers/versionCheckerV2")
        Observable<GenericResponse> checkVersionV2(@Query("versionNumber") Integer versionNumber);

        @GET
        Observable<UserResponse> openApi(@Header("token") String token, @Url String url);

        @POST("api/mogawers/registration")
        Observable<UserResponse> registration(@Body RegistrationRequest request);

        @POST("api/mogawers/activation")
        Observable<UserResponse> activation(@Body ActivationRequest request);

        @POST("api/mogawers/logIn")
        Observable<UserResponse> login(@Body LoginRequest request);

        @POST("api/mogawers/logInSocMed")
        Observable<UserResponse> loginBySocMed(@Body LoginSocMedRequest request);

        @POST("api/mogawers/resendActivationCode")
        Observable<UserResponse> resendActivationCode(@Body ResendActivationRequest request);

        @POST("api/mogawers/forgotPassword")
        Observable<UserResponse> resetPassword(@Body ResetPasswordRequest request);

        @POST("api/mogawers/activateNewPassword")
        Observable<UserResponse> newPasswordActivation(@Body ActivationRequest request);

        @POST("api/mogawers/v2/getProfile")
        Observable<UserResponse> profile(@Header("token") String token, @Body ProfileRequest request);

        @POST("api/mogawers/updateProfile")
        Observable<UserResponse> updatePhoneNumber(@Header("token") String token, @Body UpdatePhoneRequest request);

        @POST("api/mogawers/sendPhoneActivationCode")
        Observable<UserResponse> sendPhoneActivationCode(@Body PhoneActivationRequest request);

        @POST("api/mogawers/otp/citcall/send")
        Observable<GenericResponse> updatePhoneNumberCitcall(@Header("token") String token, @Body UpdatePhoneRequest request);

        @POST("api/mogawers/otp/citcall/public/send")
        Observable<GenericResponse> updatePhoneNumberCitcallPublic(@Header("token") String token, @Body UpdatePhoneRequest request);

        @POST("api/mogawers/activationPhone")
        Observable<UserResponse> phoneActivation(@Body PhoneActivationRequest request);

        @POST("/api/mogawers/otp/verify")
        Observable<UserResponse> phoneVerification(@Header("token") String token, @Body PhoneActivationRequest request);

        @POST("api/mogawers/updateProfile")
        Observable<UserResponse> updateProfile(@Header("token") String token, @Body UpdateProfileRequest request);

        @Multipart
        @POST("api/mogawers/updatePhotoProfile")
        Observable<UserResponse> updatePhotoProfile(@Header("token") String token,
                                                    @Part MultipartBody.Part picture);

        @POST("api/mogawers/requestCashout")
        Observable<GenericResponse> requestCashout(@Header("token") String token, @Body CashOutRequest request);

        @POST("api/mogawers/requestCashout/pending")
        Observable<PendingPaymentResponse> getPendingPayment(@Header("token") String token, @Body GetPendingPaymentRequest request);

        @POST("api/mogawers/updateDeviceID")
        Observable<GenericResponse> updateDeviceId(@Header("token") String token, @Body UpdateDeviceIdRequest request);

        @POST("api/mogawers/location/tracking")
        Observable<GenericResponse> sendTrack(@Header("token") String token, @Body UpdateTrackingRequest request);

        @POST("api/mogawers/transaction")
        Observable<TransactionResponse> transaction(@Header("token") String token, @Body TransactionRequest request);

        @POST("api/mogawers/transaction/history")
        Observable<WalletHistoryResponse> transactionHistory(@Header("token") String token, @Body WalletHistoryRequest request);

        @POST("api/mogawers/changePassword")
        Observable<GenericResponse> changePassword(@Header("token") String token, @Body ChangePasswordRequest request);

        @POST("api/mogawers/location/getNearJobNotification")
        Observable<GenericResponse> sendNearJobNotification(@Header("token") String token, @Body NearJobRequest request);

        @GET("api/mogawers/mogawersCheckTerms/")
        Observable<GenericResponse> checkTerms(@Header("token") String token);

        @GET("api/mogawers/mogawersAgreeTerms/")
        Observable<GenericResponse> agreeTerms(@Header("token") String token);

        @GET("api/deals/available")
        Observable<DealsResponse> getDeals(@Header("token") String token);

        @GET("api/deals/myVoucher")
        Observable<DealsTransactionsResponse> getMyVoucher(@Header("token") String token);

        @POST("api/deals/trx/openOrderUV")
        Observable<GenericResponse> redeemVoucher(@Header("token") String token, @Body RedeemRequest request);

        @GET("api/deals/trx/getUrlOrderUV/{uuid}")
        Observable<DealTransactionResponse> getUrlOrderUV(@Header("token") String token, @Path(value = "uuid", encoded = true) String uuid);

        @GET("api/mogawers/mogawersByCodeForRecruit/{mogawerCode}")
        Observable<UserResponse> checkQRDownline(@Header("token") String token, @Path(value = "mogawerCode", encoded = true) String mogawerCode);

        @GET("api/mogawers/recruitMoGawers/{mogawerCode}")
        Observable<UserResponse> addDownline(@Header("token") String token, @Path(value = "mogawerCode", encoded = true) String mogawerCode);

        @POST("api/mogawers/getDownline")
        Observable<DownlinerResponse> getDownline(@Header("token") String token, @Body GetDownlineRequest request);

        @POST("api/mogawers/reportError")
        Observable<GenericResponse> reportError(@Header("token") String token, @Body ReportErrorRequest request);

        @GET("api/mogawers/certificate/mine")
        Observable<CertificateListResponse> listCertificate(@Header("token") String token);

        @GET("api/mogawers/certificate/count")
        Observable<CertificateCountResponse> countCertificate(@Header("token") String token);

        @GET("api/mogawers/certificate/apply/{uuid}")
        Observable<GenericResponse> applyCertificate(@Header("token") String token, @Path(value = "uuid", encoded = true) String uuidCertificate);


        @POST("api/user/supplier/registration")
        Observable<UserResponse> supplierRegistration(@Body RegistrationRequest request);

        @POST("api/user/supplier/email/activate")
        Observable<UserResponse> supplierEmailActivation(@Body ActivationRequest request);

        @POST("api/user/supplier/act-code/resend")
        Observable<UserResponse> resendActivationCodeSupplier(@Body ResendActivationRequest request);

        @POST("api/user/supplier/login")
        Observable<UserResponse> supplierLogin(@Body LoginRequest request);

        @POST("api/user/supplier/password/forgot")
        Observable<UserResponse> resetPasswordSupplier(@Body ResetPasswordRequest request);

        @POST("api/user/supplier/new-password/activate")
        Observable<UserResponse> newPasswordActivationSupplier(@Body ActivationRequest request);

        @POST("api/user/supplier/profile/me")
        Observable<UserResponse> supplierProfile(@Header("token") String token, @Body ProfileRequest request);

        @POST("api/user/supplier/profile/update")
        Observable<UserResponse> updateProfileSupplier(@Header("token") String token, @Body User request);


        @Multipart
        @POST("api/user/supplier/profile-pic/update")
        Observable<UserResponse> updatePhotoProfileSupplier(@Header("token") String token,
                                                            @Part MultipartBody.Part picture);

        @POST("api/user/supplier/password/change")
        Observable<GenericResponse> changePasswordSupplier(@Header("token") String token, @Body ChangePasswordRequest request);

        @GET("api/user/supplier/dashboard")
        Observable<SupplierDashboardCountResponse> countDashboardSupplier(@Header("token") String token);
    }

    public interface ApiUserCitcallService {
        @POST("asynccall")
        Observable<CitcallResponse> misscallOTP(@Header("content-type") String contentType, @Header("Authorization") String apiKey, @Body CitcalMisscallRequest request);
    }

    public interface ApiTaskService {
        @GET("api/fieldtask/attachment/{uuidTask}")
        Observable<JobAttachmentResponse> attachmentList(@Header("token") String token, @Path(value = "uuidTask", encoded = true) String uuidTask);

        @GET("api/fieldtask/salescode/{uuidTask}")
        Observable<JobRefCodeResponse> getRefCode(@Header("token") String token, @Path(value = "uuidTask", encoded = true) String uuidTask);

        @GET("api/fieldtask/getAllGawean")
        Observable<HomeContentsResponse> getAllGawean(@Header("token") String token, @Query("lat") Double latitude, @Query("lng") Double longitude);

        @POST("api/fieldtask/myTask")
        Observable<MyTaskResponse> getTask(@Header("token") String token, @Body MyTaskRequest request);

        @GET("api/fieldtask/myTaskToday")
        Observable<MyTaskTodayResponse> getTaskToday(@Header("token") String token);

        @GET("api/fieldtask/myTaskToDo")
        Observable<TaskToDoResponse> getTaskToDo(@Header("token") String token, @Query("page") Integer page, @Query("offset") Integer offset, @Query("lat") Double latitude, @Query("lng") Double longitude);

        @PUT("api/fieldtask/myTaskToDo/updateSequence")
        Observable<MyTaskTodayResponse> updateSequenceToDoList(@Header("token") String token, @Body FieldTaskToDoRequest fieldTaskToDo);

        @GET("api/fieldtask/searchFeaturedJob/")
        Observable<FeaturedJobResponse> searchFeaturedJob(@Header("token") String token, @Query("lat") Double latitude, @Query("lng") Double longitude);

        @GET("api/fieldtask/searchSpecificJob/")
        Observable<MyTaskResponse> searchSpecificProject(@Header("token") String token, @Query("p") String project, @Query("lat") Double latitude, @Query("lng") Double longitude, @Query("centerlat") Double centerlat, @Query("centerlng") Double centerlng);

        @GET("api/fieldtask/searchAvailableJob/")
        Observable<GaweanResponse> searchJob(@Header("token") String token, @Query("a") Double amount, @Query("lat") Double latitude, @Query("lng") Double longitude, @Query("radius") Integer radius, @Query("exceptJob") List<String> exceptJob);

        @GET("api/fieldtask/getPolyline")
        Observable<TaskPolylineResponse> getTaskPolyline(@Header("token") String token, @Query("idJob") String idJob);

        @GET("api/fieldtask/pinTask")
        Observable<GenericResponse> pinTask(@Header("token") String token, @Query("idTask") String idTask, @Query("isPinned") boolean isPinned);

        @GET("api/fieldtask/start/")
        Observable<StartEndTaskResponse> startGawean(@Header("token") String token, @Query("idTask") String idTask, @Query("timestamp") Long timestamp);

        @GET("api/fieldtask/close/")
        Observable<StartEndTaskResponse> closeGawean(@Header("token") String token, @Query("idResult") String idResult, @Query("idTask") String idTask);
//        Observable<StartEndTaskResponse> closeGawean(@Header("token") String token, , @Query("idTask") String idTask);

        @GET("api/fieldtask/closeJob/{uuidTask}")
        Observable<StartEndTaskResponse> closeJob(@Header("token") String token, @Path(value = "uuidTask", encoded = true) String uuidTask);

        @GET("api/fieldtask/history/{uuidTask}")
        Observable<HistoryTaskResponse> historyJob(@Header("token") String token, @Path(value = "uuidTask", encoded = false) String uuidTask);

        @GET("api/fieldtask/apply/")
        Observable<MyTaskResponse> applyGawean(@Header("token") String token, @Query("idJob") String idJob);

        @GET("api/fieldtask/cancel/{idTask}/")
        Observable<MyTaskResponse> cancelGawean(@Header("token") String token, @Path(value = "idTask", encoded = true) String idTask);

        @POST("api/fieldresult/getHistoryResult/")
        Observable<HistoryResultResponse> getJobHistory(@Header("token") String token, @Body ResultHistoryRequest resultHistoryRequest);

        @GET("/api/fieldresult/history")
        Observable<HistoryResultResponse> getJobHistoryV2(@Header("token") String token, @Query("q") String query, @Query("page") Integer page, @Query("offset") Integer offset, @Query("periode") String periode, @Query("status") String status, @Query("startdate") String startdate, @Query("enddate") String enddate);

        @POST("api/fieldresultfact/submitTask")
        Observable<SectionSourceResponse> submitTask(@Header("token") String token, @Body SectionSubmitRequest sectionSubmitRequest);

        @POST("api/fieldtask/getAllCategoryGawean")
        Observable<HomeContentsResponse> getAllCategoryGawean(@Header("token") String token, @Body GetAllCategoriesRequest getAllCategoriesRequest);

        @GET("api/fieldtask/v3/widgetrow/get")
        Observable<HomeContentsResponse> getWidgetRow(@Header("token") String token, @Header("Content-Type") String contentType, @Query("page") Integer page, @Query("offset") Integer offset, @Query("lat") Double latitude, @Query("lng") Double longitude);

//        @GET("api/fieldtask/getJobByCategories")
//        Observable<MyTaskResponse> getJobByCategories(@Header("token") String token, @Query("lat") Double lat, @Query("lng") Double lng, @Query("categories") String categories);

        @POST("api/fieldtask/getJobByCategories")
        Observable<MyTaskResponse> getJobByCategoriesV2(@Header("token") String token, @Body GetJobCategoryRequest getAllCategoriesRequest);

        @POST("api/fieldtask/getAdsByCategories")
        Observable<GetAdsResponse> getAdsByCategories(@Header("token") String token, @Body GetContentByCategoriesRequest request);


        @POST("api/fieldresultfact/submitResult")
        Observable<SectionSourceResponse> submitResult(@Header("token") String token, @Body SectionSubmitRequest sectionSubmitRequest);

        @POST("api/fieldresultfact/submitResultFact")
        Observable<SectionSourceResponse> submitResultFact(@Header("token") String token, @Body SectionSubmitRequest sectionSubmitRequest);

        @POST("api/fieldtask/job/chat/verify")
        Observable<VerifyChatRoomResponse> verifyChatRoom(@Header("token") String token, @Body HashMap<String, String> uuidJob);

        @POST("api/fieldtask/job/chat/open")
        Observable<VerifyChatRoomResponse> saveChatRoom(@Header("token") String token, @Body SaveChatRoomRequest saveChatRoomRequest);

        @Multipart
        @POST("api/fieldresultfact/uploadFileV2")
        Observable<SectionSourceResponse> uploadPicture(@Header("token") String token, @Part MultipartBody.Part image, @Part("idResult") RequestBody id_result, @Part("idFact") RequestBody id_fact, @Part("idItem") RequestBody id_item, @Query("timestamp") String timestamp, @Query("geoLoc") String geoLoc);

        @GET("api/fieldtask/getJobFactPolling/{idTask}")
        Observable<FactPollResponse> getJobFactPolling(@Header("token") String token, @Path(value = "idTask", encoded = true) String idTask);

        @GET("api/fieldactivity/get")
        Observable<TimelineActivityResponse> getTimelineActivity(@Header("token") String token, @Query("page") Integer page, @Query("offset") Integer offset);

        @POST("api/fieldactivity/like/{idTask}")
        Observable<TimelineActivityResponse> likePostTimeline(@Header("token") String token, @Path(value = "idTask", encoded = true) String uuidPostTimeline);

        @DELETE("api/fieldactivity/unlike/{idTask}")
        Observable<TimelineActivityResponse> unlikePostTimeline(@Header("token") String token, @Path(value = "idTask", encoded = true) String uuidPostTimeline);

        @POST("api/fieldactivity/post")
        Observable<TimelineActivityResponse> postTimelineStatus(@Header("token") String token, @Body PostStatusRequest request);

        @POST("api/mogawers/connect/{uuidMoGawers}")
        Observable<TimelineActivityResponse> connectToMoGawers(@Header("token") String token, @Path(value = "uuidMoGawers", encoded = true) String uuidMoGawers);

        @DELETE("api/mogawers/disconnect/{uuidMoGawers}")
        Observable<TimelineActivityResponse> disconnectToMoGawers(@Header("token") String token, @Path(value = "uuidMoGawers", encoded = true) String uuidMoGawers);

        @GET("api/fieldtask/goingto/{uuidTask}")
        Observable<MyTaskResponse> goingTo(@Header("token") String token, @Path(value = "uuidTask", encoded = true) String uuidTask);

        @GET("api/fieldtask/cancelto/{uuidTask}")
        Observable<MyTaskResponse> cancelTo(@Header("token") String token, @Path(value = "uuidTask", encoded = true) String uuidTask);

        @GET("api/fieldtask/get/{uuidTask}")
        Observable<GawenInformationResponse> getGaweanInformation(@Header("token") String token, @Path(value = "uuidTask", encoded = true) String uuidTask);

        @GET("api/fieldtask/job/get/{uuidTask}")
        Observable<GawenInformationResponse> getGaweanInformationByIdJob(@Header("token") String token, @Path(value = "uuidTask", encoded = true) String uuidTask);

        @GET("api/fieldtask/job/get/{uuidJob}")
        Observable<TaskResponse> detailJob(@Header("token") String token, @Path(value = "uuidJob", encoded = true) String uuidJob);

        @POST("api/supplier/registration")
        Observable<SupplierResponse> supplierRegistrationPost(@Header("token") String token, @Body Supplier request);

        @POST("api/supplier/product/create")
        Observable<CatalogObjResponse> supplierCreateProduct(@Header("token") String token, @Body Catalog request);

        @PUT("api/supplier/product/update")
        Observable<CatalogObjResponse> supplierEditProduct(@Header("token") String token, @Body Catalog request);

        @Multipart
        @PUT("api/supplier/product/image/upload")
        Observable<SupplierResponse> supplierUploadImagesProduct(@Header("token") String token, @Part MultipartBody.Part picture);

        @GET("api/supplier/product/category/get")
        Observable<ProductCategoryListResponse> getCategoryProductSupplier(@Header("token") String token);

        @GET("/api/fieldbatchspec/get")
        Observable<GaweanInstantResponse> getGaweanInstant(@Header("token") String token, @Query("page") Integer page, @Query("offset") Integer offset);

        @GET("api/fieldbatchspec/get/{uuid}")
        Observable<GaweanInstantDetailResponse> getGaweanInstantDetail(@Header("token") String token, @Path(value = "uuid", encoded = true) String uuid);

        @POST("api/fieldbatchspec/sign/{uuid}")
        Observable<GenericResponse> activateInstantGawean(@Header("token") String token, @Path(value = "uuid", encoded = true) String uuid);

        @DELETE("api/fieldbatchspec/unsign/{uuid}")
        Observable<GenericResponse> deactivateInstantGawean(@Header("token") String token, @Path(value = "uuid", encoded = true) String uuid);

        @POST("api/fieldsjr/offer/accept")
        Observable<GenericResponse> acceptGaweanOffer(@Header("token") String token, @Body SJROfferRequest request);

        @POST("api/fieldsjr/offer/decline")
        Observable<GenericResponse> declineGaweanOffer(@Header("token") String token, @Body SJROfferRequest request);

    }

    public interface ApiSectionService {
        @GET("api/fieldfact/getByTask/")
        Observable<SectionSourceResponse> getTask(@Header("token") String token, @Query("idTask") String id_task);

            @GET("api/fieldfact/applyAndGetSection/")
        Observable<SectionSourceResponse> directApply(@Header("token") String token, @Query("idJob") String idJob);

        @GET("api/fieldfact/findSearchableItemByFact/")
        Observable<ItemResponse> searchItemFacing(@Header("token") String token, @Query("uuidFact") String uuidFact, @Query("q") String produk, @Query("p") Integer page, @Query("off") Integer offset);

        @GET("api/fieldfact/spot/check")
        Observable<GeoLocationSpotCheckResponse> getlocationSpotCheck(@Header("token") String token, @Query("uuidFact") String uuidFact, @Query("lat") Double latitude, @Query("lng") Double longitude);
    }

    public interface ApiProjectService {
        @GET("api/job/searchAvailableJob/")
        Observable<GaweanResponse> searchJob(@Header("token") String token, @Query("a") Double amount, @Query("lat") Double latitude, @Query("lng") Double longitude);

        @GET("api/job/searchSpecificJob/")
        Observable<GaweanResponse> searchSpecificJob(@Header("token") String token, @Query("p") String project, @Query("lat") Double latitude, @Query("lng") Double longitude, @Query("centerlat") Double centerlat, @Query("centerlng") Double centerlng);

        @GET("api/job/searchFeaturedJob/")
        Observable<FeaturedJobResponse> searchFeaturedJob(@Header("token") String token, @Query("lat") Double latitude, @Query("lng") Double longitude);
    }

    public interface ApiSearchService {
        @GET("api/fieldsearch/gawean")
        Observable<GaweanResponse> searchJob(@Header("token") String token, @Query("a") Integer amount, @Query("lat") Double latitude, @Query("lng") Double longitude, @Query("p") String project, @Query("r") Integer radius, @Query("pg") Integer page, @Query("off") Integer offset, @Query("l") List<String> uuidLocation, @Query("c") List<String> uuidClassJob, @Query("jn") String jobName);

        @GET("api/fieldsearch/v2/gawean")
        Observable<GaweanResponseV2> searchJobV2(@Header("token") String token, @Query("a") Integer amount, @Query("lat") Double latitude, @Query("lng") Double longitude, @Query("p") String project, @Query("r") Integer radius, @Query("pg") Integer page, @Query("off") Integer offset, @Query("l") List<String> uuidLocation, @Query("c") List<String> uuidClassJob, @Query("jn") String jobName);

        @GET("api/fieldsearch/v2/confirmOrder")
        Observable<GenericResponse> confirmOrder(@Header("token") String token, @Query("to") String to);

        @GET("api/fieldsearch/v2/activeOrder")
        Observable<GaweanResponseV2> getActiveOrder(@Header("token") String token);

        @GET("api/fieldsearch/class")
        Observable<ProductResponse> getCategoryProduct(@Header("token") String token, @Query("pg") Integer page, @Query("off") Integer offset);

        @GET("api/fieldsearch/class/{uuid}/location")
        Observable<LocationResponse> getLocationList(@Header("token") String token, @Path(value = "uuid", encoded = true) String uuid, @Query("lat") Double lat, @Query("lng") Double lng, @Query("r") Integer radius, @Query("pg") Integer page, @Query("off") Integer offset);

        @GET("api/fieldsearch/class/{uuid}/subclass")
        Observable<ProductResponse> getSubCategoryList(@Header("token") String token, @Path(value = "uuid", encoded = true) String uuid, @Query("pg") Integer page, @Query("off") Integer offset, @Query("lat") Double lat, @Query("lng") Double lng, @Query("r") Integer radius);

    }

    public interface ApiSalesService {
        @GET("api/sales/product/get")
        Observable<CatalogResponse> getCatalogList(@Header("token") String token, @Query("q") String query, @Query("uuidCategory") String uuidCategory, @Query("page") Integer page, @Query("offset") Integer offset);

        @POST("api/sales/product/favorite/{uuid}")
        Observable<CatalogResponse> addToFavorite(@Header("token") String token, @Path(value = "uuid", encoded = true) String uuid);

        @GET("api/sales/product/get/{uuid}")
        Observable<CatalogDetailResponse> getDetailCatalog(@Header("token") String token, @Path(value = "uuid", encoded = true) String uuid);

        @GET("api/sales/product/summary/{uuid}")
        Observable<DetailSummaryResponse> getDetailSummary(@Header("token") String token, @Path(value = "uuid", encoded = true) String uuid);

        @DELETE("api/sales/product/favorite/{uuid}")
        Observable<CatalogResponse> removeFromFavorite(@Header("token") String token, @Path(value = "uuid", encoded = true) String uuid);

        @GET("api/supplier/product/get")
        Observable<CatalogResponse> getProdukList(@Header("token") String token, @Query("q") String query, @Query("page") Integer page, @Query("offset") Integer offset);

        @GET("/api/sales/product/category/get")
        Observable<CategoryProductResponse> getCategorySalesList(@Header("token") String token);

        @GET("api/supplier/product/get/{uuid}")
        Observable<CatalogObjResponse> detailProduct(@Header("token") String token, @Path(value = "uuid", encoded = true) String uuid);

        @GET("api/supplier/product/order/get")
        Observable<OrdersResponse> getOrderList(@Header("token") String token, @Query("q") String query, @Query("status") String status, @Query("page") Integer page, @Query("offset") Integer offset);

        @GET("api/sales/product/order/get")
        Observable<OrdersResponse> getOrderListSales(@Header("token") String token, @Query("q") String query, @Query("status") String status, @Query("page") Integer page, @Query("offset") Integer offset);

        @GET("api/sales/product/favorite/get")
        Observable<CatalogResponse> getFavoriteList(@Header("token") String token, @Query("q") String query, @Query("page") Integer page, @Query("offset") Integer offset);

        @PUT("api/supplier/product/publish/{uuid}")
        Observable<CatalogObjResponse> publishProduct(@Header("token") String token, @Path(value = "uuid", encoded = true) String uuid);

        @PUT("api/supplier/product/unpublish/{uuid}")
        Observable<CatalogObjResponse> unpublishProduct(@Header("token") String token, @Path(value = "uuid", encoded = true) String uuid);

        @PUT("api/supplier/product/order/shipment/process")
        Observable<CatalogResponse> processOrder(@Header("token") String token, @Body Shipment shipment);

        @PUT("api/supplier/product/order/shipment/sent")
        Observable<CatalogResponse> sentResi(@Header("token") String token, @Body Shipment shipment);

        @DELETE("api/supplier/product/delete/{uuid}")
        Observable<CatalogObjResponse> deleteProduct(@Header("token") String token, @Path(value = "uuid", encoded = true) String uuid);

        @POST("api/sales/product/checkout")
        Observable<SupplierResponse> checkoutOrder(@Header("token") String token, @Body Order order);

        @GET("api/sales/shipment/province")
        Observable<ProvinceListResponse> provinceList(@Header("token") String token);

        @GET("api/sales/shipment/city")
        Observable<CityListResponse> cityList(@Header("token") String token);

        @GET("api/sales/shipment/v2/cost")
        Observable<LogisticListResponse> logisticList(@Header("token") String token, @Query("origin") String origin, @Query("destination") String destination,
                                                      @Query("weight") int weight, @Query("courier") String courier);

        @GET("api/sales/product/order/get/{orderNo}")
        Observable<OrderResponse> detailOrder(@Header("token") String token, @Path(value = "orderNo", encoded = true) String orderNo);

        @GET("api/supplier/product/order/get/{orderNo}")
        Observable<OrderResponse> supplierDetailOrder(@Header("token") String token, @Path(value = "orderNo", encoded = true) String orderNo);

        @DELETE("api/supplier/product/image/delete/{uuid}")
        Observable<SupplierResponse> supplierDeleteImageProduct(@Header("token") String token, @Path(value = "uuid", encoded = true) String uuidJob);

    }

    public interface ApiNaufalService {
        @GET("gawean.json")
        Observable<GaweanListResponse> getGaweanList();
    }
}