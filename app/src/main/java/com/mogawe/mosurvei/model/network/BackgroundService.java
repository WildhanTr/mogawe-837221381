package com.mogawe.mosurvei.model.network;

import android.app.Service;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.IBinder;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.mogawe.mosurvei.MoSurveiApplication;
import com.mogawe.mosurvei.model.db.entity.Fact;
import com.mogawe.mosurvei.model.db.entity.Item;
import com.mogawe.mosurvei.model.db.entity.PendingTask;
import com.mogawe.mosurvei.model.db.entity.Result;
import com.mogawe.mosurvei.model.db.entity.ResultFact;
import com.mogawe.mosurvei.model.db.entity.Section;
import com.mogawe.mosurvei.model.db.entity.Task;
import com.mogawe.mosurvei.model.db.entity.User;
import com.mogawe.mosurvei.model.network.response.section.SectionSourceResponse;
import com.mogawe.mosurvei.model.repository.ResultRepository;
import com.mogawe.mosurvei.model.repository.TaskRepository;
import com.mogawe.mosurvei.model.repository.UserRepository;
import com.mogawe.mosurvei.viewmodel.ResultViewModel;
import com.mogawe.mosurvei.viewmodel.TaskViewModel;
import com.mogawe.mosurvei.viewmodel.UserViewModel;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BackgroundService extends Service {

    ResultViewModel resultViewModel;
    TaskViewModel taskViewModel;
    UserViewModel userViewModel;
    SectionSourceResponse sectionSource;
    Task task;
    Intent myIntent;
    User user;
    PendingTask pendingTask;

    ResultRepository resultRepository;
    TaskRepository taskRepository;
    UserRepository userRepository;

    private static final String MATRIX = "50cd1934-0ce8-4a6a-bf86-5b2b4a49548b";
    private static final String PICTURE = "9b01ba60-1b42-43c1-a609-c1e6a4ca79a8";
    private static final String VIDEO = "b019f6f3-363e-4224-a8cd-41905fc056b6";
    private static final String AUDIO_RECORDER = "9dae912f-ed6a-4675-938b-14fd5e2d3865";

    public BackgroundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        onTaskRemoved(intent);

        resultRepository = new ResultRepository((MoSurveiApplication) getApplication());
        taskRepository = new TaskRepository((MoSurveiApplication) getApplication());
        userRepository = new UserRepository((MoSurveiApplication) getApplication());

        pendingTask = new PendingTask();

        userRepository.getProfile().observe((LifecycleOwner) this, new Observer<User>() {
            @Override
            public void onChanged(User mUser) {
                if (mUser != null) {
                    user = mUser;
                }
            }
        });

        myIntent = intent; // this getter is just for example purpose, can differ
        if (myIntent != null && myIntent.getExtras() != null) {
            task = (Task) myIntent.getSerializableExtra("task");

            pendingTask.setId_task(task.getId_task());
            pendingTask.setGroup_name(task.getGroup_name());
            pendingTask.setId_job(task.getId_job());
            pendingTask.setId_job_type(task.getId_job_type());
            pendingTask.setLatitude(task.getLatitude());
            pendingTask.setLongitude(task.getLongitude());
            pendingTask.setLocation_name(task.getLocation_name());
            pendingTask.setTutorial(task.getTutorial());
            pendingTask.setJob_type_name(task.getJob_type_name());
            pendingTask.setName(task.getName());
            pendingTask.setJob_name(task.getJob_name());
            pendingTask.setCode(task.getCode());
            pendingTask.setDescription(task.getDescription());
            pendingTask.setBrief(task.getBrief());
            pendingTask.setHow_to(task.getHow_to());
            pendingTask.setDuration(task.getDuration());
            pendingTask.setMinimum_level(task.getMinimum_level());
            pendingTask.setLimits(task.getLimits());
            pendingTask.setFee(task.getFee());
            pendingTask.setType(task.getType());
            pendingTask.setTarget(task.getTarget());
            pendingTask.setSchedule(task.getSchedule());
            pendingTask.setScreening(task.getScreening());
            pendingTask.setPoints(task.getPoints());
            pendingTask.setStatus_task(task.getStatus_task());
            pendingTask.setStatus_notes(task.getStatus_notes());
            pendingTask.setIs_pinned(task.isIs_pinned());
            pendingTask.setIs_qc_needed(task.isIs_qc_needed());
            pendingTask.setIs_auto_approved(task.isIs_auto_approved());
            pendingTask.setIs_direct(task.isIs_direct());
            pendingTask.setIs_auto_approved(task.isIs_auto_approved());
            pendingTask.setJob_picture(task.getJob_picture());
            pendingTask.setJob_banner(task.getJob_banner());
            pendingTask.setJob_color(task.getJob_color());
            pendingTask.setJob_text_color(task.getJob_text_color());
            pendingTask.setRadius(task.getRadius());
            pendingTask.setLocation_level(task.getLocation_level());
            pendingTask.setLocationLevel(task.getLocationLevel());
            pendingTask.setJob_activities(task.getJob_activities());
            pendingTask.setResultCount(task.getResultCount());
            pendingTask.setJobId(task.getJobId());
            pendingTask.setStartDate(task.getStartDate());
            pendingTask.setEndDate(task.getEndDate());
            pendingTask.setLocationAdress(task.getLocationAdress());
            pendingTask.setJobTimes(task.getJobTimes());
            pendingTask.setAssigned(task.isAssigned());
            pendingTask.setFavorite(task.isFavorite());
            pendingTask.setDistanceLocation(task.getDistanceLocation());
            pendingTask.setDistance(task.getDistance());


            taskViewModel.getQuestSectionSource(Integer.valueOf(task.getJobId())).observe((LifecycleOwner) this, new Observer<SectionSourceResponse>() {
                @Override
                public void onChanged(SectionSourceResponse sectionSourceResponse) {

                    sectionSource = sectionSourceResponse;

                    Result result = new Result();
                    Result resultImages = new Result();
                    Result resultVideos = new Result();
                    Result resultAudios = new Result();
                    List<ResultFact> resultFacts = new ArrayList<>();
                    List<ResultFact> resultFactsImages = new ArrayList<>();
                    List<ResultFact> resultFactsVideo = new ArrayList<>();
                    List<ResultFact> resultFactsAudio = new ArrayList<>();
                    List<ResultFact> pictureResultFact = new ArrayList<>();
                    List<ResultFact> videoResultFact = new ArrayList<>();
                    List<ResultFact> audioResultFact = new ArrayList<>();

                    result.setId_task(task.getId_task());
                    resultImages.setId_task(task.getId_task());
                    resultVideos.setId_task(task.getId_task());

                    for (Section s : sectionSource.getSections()) {
                        for (Fact f : s.getFacts()) {
                            if (f.getId_fact_type().equals(MATRIX)) {
                                if (f.getItems() != null) {
                                    for (Item i : f.getItems()) {
                                        ResultFact resultFact = new ResultFact();
                                        resultFact.setId_fact(f.getId_fact_display());
                                        resultFact.setValue(i.getInput());
                                        resultFact.setId_item(i.getId_item_display());
                                        resultFacts.add(resultFact);
                                    }
                                }
                            } else if (f.getId_fact_type().equals(PICTURE)) {
                                ResultFact resultFact = new ResultFact();
                                String value;
                                if (f.getFiles() != null) {
                                    value = String.valueOf(f.getFiles().size());
                                } else {
                                    value = String.valueOf(0);
                                }

                                resultFact.setId_fact(f.getId_fact_display());
                                resultFact.setValue(value);
                                resultFact.setFiles(f.getFiles());
                                if (s.getId_item_display() != null && !s.getId_item_display().isEmpty()) {
                                    resultFact.setId_item(s.getId_item_display());
                                }
                                System.out.println("id_item_display : " + f.getId_item_display());


                                resultFacts.add(resultFact);

                                pictureResultFact.add(resultFact);

                                if (f.getFiles() != null && f.getFiles().size() != 0) {
                                    resultFactsImages.add(resultFact);
                                }
                            } else if (f.getId_fact_type().equals(VIDEO)) {
                                ResultFact resultFact = new ResultFact();
                                String value;
                                if (f.getFiles() != null) {
                                    value = String.valueOf(f.getFiles().size());
                                } else {
                                    value = String.valueOf(0);
                                }

                                resultFact.setId_fact(f.getId_fact_display());
                                System.out.println("VIDEOVALUE " + f.getFiles());
                                System.out.println("VIDEOVALUE " + value);
                                resultFact.setValue(value);
                                resultFact.setFiles(f.getFiles());
                                if (s.getId_item_display() != null && !s.getId_item_display().isEmpty()) {
                                    resultFact.setId_item(s.getId_item_display());
                                }
                                System.out.println("id_item_display : " + f.getId_item_display());


                                resultFacts.add(resultFact);

                                videoResultFact.add(resultFact);

                                if (f.getFiles() != null && f.getFiles().size() != 0) {
                                    resultFactsVideo.add(resultFact);
                                }
                            } else if (f.getId_fact_type().equals(AUDIO_RECORDER)) {
                                ResultFact resultFact = new ResultFact();
                                String value;
                                if (f.getFiles() != null) {
                                    value = String.valueOf(f.getFiles().size());
                                } else {
                                    value = String.valueOf(0);
                                }

                                resultFact.setId_fact(f.getId_fact_display());
                                System.out.println("AUDIOVALUE " + f.getFiles());
                                System.out.println("AUDIOVALUE " + value);
                                resultFact.setValue(value);
                                resultFact.setFiles(f.getFiles());
                                resultFact.setUuidFactType(f.getId_fact_type());
                                if (s.getId_item_display() != null && !s.getId_item_display().isEmpty()) {
                                    resultFact.setId_item(s.getId_item_display());
                                }
                                System.out.println("id_item_display : " + f.getId_item_display());


                                resultFacts.add(resultFact);

                                audioResultFact.add(resultFact);

                                if (f.getFiles() != null && f.getFiles().size() != 0) {
                                    resultFactsAudio.add(resultFact);
                                }
                            } else {
                                ResultFact resultFact = new ResultFact();
                                String value = f.getInput();

                                resultFact.setId_fact(f.getId_fact_display());
                                resultFact.setValue(value);
                                if (s.getId_item_display() != null && !s.getId_item_display().isEmpty()) {
                                    resultFact.setId_item(s.getId_item_display());
                                }
                                resultFacts.add(resultFact);
                            }

                        }
                    }

                    result.setResult_facts(resultFacts);
                    resultImages.setResult_facts(resultFactsImages);
                    resultVideos.setResult_facts(resultFactsVideo);
                    resultAudios.setResult_facts(resultFactsAudio);

//        for(ResultFact rf : result.getResult_facts()){
//            System.out.println(rf.getId_fact());
//            System.out.println(rf.getValue());
//            System.out.println(rf.getId_item());
//            for(File rfi : rf.getFiles()){
//                System.out.println(getFilePath());
//            }
//
//        }

//        resultViewModel.submitResultToServer(result, resultImages, sectionSource.getUuidSession());
                    resultViewModel.submitResult(result, resultImages, sectionSourceResponse.getUuidSession(), resultVideos, Double.parseDouble(sectionSourceResponse.getLatitude()), Double.parseDouble(sectionSourceResponse.getLongitude()), resultAudios);
                }
            });

        }

        resultViewModel.getResponseLiveData().observe((LifecycleOwner) this, new Observer<SectionSourceResponse>() {
            @Override
            public void onChanged(SectionSourceResponse sectionSourceResponse) {
                if (sectionSourceResponse.isSuccess()) {
                    switch (sectionSourceResponse.getRequestKey()) {
                        case RESULT_UPLOADED:
//                            showProgressDialog("Mengakhiri Gawean...");
                            taskViewModel.closeGawean(task, sectionSourceResponse.getUuid(), user);
                            break;
                        case RESULT_CREATED:
                            System.out.println("OUT >> SUBMIT 13");
                            sectionSourceResponse.setIndexStart(0);
                            sectionSourceResponse.setIndexCount(sectionSourceResponse.getResult().getResult_facts().size());
//                            showProgressHorizontalDialogMessage("Uploading...", "Mengupload Data...", sectionSourceResponse.getIndexCount());
                            resultViewModel.submitResultFact(sectionSourceResponse.getResult(), sectionSourceResponse.getResultImages(), sectionSourceResponse.getUuidSession(), sectionSourceResponse.getIndexStart(), sectionSourceResponse.getIndexCount(), sectionSourceResponse.getResultVideos(), sectionSourceResponse.getResultAudios());
                            break;
                        case RESULT_FACT_UPLOAD_ON_PROGRESS:
                            sectionSourceResponse.setIndexStart(sectionSourceResponse.getIndexStart() + 10);
//                            setProgressHorizontalDialogMessage(10);

                            Double progress = sectionSourceResponse.getIndexStart().doubleValue() / sectionSourceResponse.getIndexCount().doubleValue() * 100.00;
//                            setProgressDialogMessage("Mengupload... "+ String.format("%.2f", progress) +"%");
                            resultViewModel.submitResultFact(sectionSourceResponse.getResult(), sectionSourceResponse.getResultImages(), sectionSourceResponse.getUuidSession(), sectionSourceResponse.getIndexStart(), sectionSourceResponse.getIndexCount(), sectionSourceResponse.getResultVideos(), sectionSourceResponse.getResultAudios());
                            break;
                        case RESULT_FACT_UPLOAD_DONE:
//                            setProgressHorizontalDialogMessage(10);
                            if (sectionSourceResponse.getResultImages().getResult_facts().size() > 0) {
                                Integer imageCount = 0;

                                List<ResultFact> resultFactImages = new ArrayList<>();
                                for (ResultFact rf : sectionSourceResponse.getResultImages().getResult_facts()) {
                                    Integer number_rff = 1;
                                    if (rf.getFiles() != null) {
                                        for (File rff : rf.getFiles()) {
                                            ResultFact rfi = new ResultFact();
                                            rfi.setId(rf.getId());
                                            rfi.setId_fact(rf.getId_fact());
                                            rfi.setId_result(rf.getId_result());
                                            rfi.setId_result_fact(rf.getId_result_fact());
                                            rfi.setFile(rff);
                                            rfi.setNotes(rf.getNotes());
                                            rfi.setId_item(rf.getId_item());
                                            rfi.setValue(rf.getValue());
                                            rfi.setResult_id(rf.getResult_id());
                                            System.out.println("asdasdasdxxrwdsf " + rff.getAbsolutePath());
                                            resultFactImages.add(rfi);
                                            imageCount++;
                                        }
                                    }
                                }

                                Result resultImages = sectionSourceResponse.getResultImages();
                                resultImages.setResult_facts(resultFactImages);
                                sectionSourceResponse.setResultImages(resultImages);
                                sectionSourceResponse.setImageStart(0);
                                sectionSourceResponse.setImageCount(imageCount);

//                                showProgressHorizontalDialogMessage("Uploading...", "Mengupload " + sectionSourceResponse.getImageCount() + " Foto...", sectionSourceResponse.getImageCount());
                                resultViewModel.submitResultImages(sectionSourceResponse.getResultImages(), sectionSourceResponse.getImageStart(), sectionSourceResponse.getImageCount(), sectionSourceResponse.getResultVideos(), null, null, null, null, null, getTimeStampNow(), getAddressFromLatLng(task.getLatitude(), task.getLongitude()));
                            } else {
                                System.out.println("SOURCE = " + sectionSourceResponse.getResultVideos().getResult_facts());
                                if (sectionSourceResponse.getResultVideos().getResult_facts().size() > 0) {
                                    Integer videoCount = 0;

                                    System.out.println("adwasdkoawkdoskdos : " + sectionSourceResponse.getResultVideos().getResult_facts().size());
                                    List<ResultFact> resultFactVideos = new ArrayList<>();
                                    for (ResultFact rf : sectionSourceResponse.getResultVideos().getResult_facts()) {
                                        Integer number_rff = 1;
                                        if (rf.getFiles() != null) {
                                            for (File rff : rf.getFiles()) {
                                                ResultFact rfi = new ResultFact();
                                                rfi.setId(rf.getId());
                                                rfi.setId_fact(rf.getId_fact());
                                                rfi.setId_result(rf.getId_result());
                                                rfi.setId_result_fact(rf.getId_result_fact());
                                                rfi.setFile(rff);
                                                rfi.setNotes(rf.getNotes());
                                                rfi.setId_item(rf.getId_item());
                                                rfi.setValue(rf.getValue());
                                                rfi.setResult_id(rf.getResult_id());

                                                resultFactVideos.add(rfi);
                                                videoCount++;
                                            }
                                        }
                                    }

                                    Result resultVideos = sectionSourceResponse.getResultVideos();
                                    resultVideos.setResult_facts(resultFactVideos);
                                    sectionSourceResponse.setResultVideos(resultVideos);
                                    sectionSourceResponse.setVideoStart(0);
                                    sectionSourceResponse.setVideoCount(videoCount);

                                    System.out.println("adwsodkawodksokd " + sectionSourceResponse.getResultVideos().getResult_facts().size());

//                                    showProgressHorizontalDialogMessage("Uploading...", "Mengupload " + sectionSourceResponse.getVideoCount() + " Video...", sectionSourceResponse.getVideoCount());
                                    System.out.println("VIDEOSTART " + sectionSourceResponse.getVideoStart());
                                    resultViewModel.submitResultImages(null, null, null, sectionSourceResponse.getResultVideos(), sectionSourceResponse.getVideoStart(), sectionSourceResponse.getVideoCount(), null, null, null, getTimeStampNow(), getAddressFromLatLng(task.getLatitude(), task.getLongitude()));
                                } else if (sectionSourceResponse.getResultAudios().getResult_facts().size() > 0) {
                                    Integer audioCount = 0;

                                    System.out.println("adwasdkoawkdoskdos : " + sectionSourceResponse.getResultAudios().getResult_facts().size());
                                    List<ResultFact> resultFactAudios = new ArrayList<>();
                                    for (ResultFact rf : sectionSourceResponse.getResultVideos().getResult_facts()) {
                                        Integer number_rff = 1;
                                        if (rf.getFiles() != null) {
                                            for (File rff : rf.getFiles()) {
                                                ResultFact rfi = new ResultFact();
                                                rfi.setId(rf.getId());
                                                rfi.setId_fact(rf.getId_fact());
                                                rfi.setId_result(rf.getId_result());
                                                rfi.setId_result_fact(rf.getId_result_fact());
                                                rfi.setFile(rff);
                                                rfi.setNotes(rf.getNotes());
                                                rfi.setId_item(rf.getId_item());
                                                rfi.setValue(rf.getValue());
                                                rfi.setResult_id(rf.getResult_id());

                                                resultFactAudios.add(rfi);
                                                audioCount++;
                                            }
                                        }
                                    }

                                    Result resultAudios = sectionSourceResponse.getResultAudios();
                                    resultAudios.setResult_facts(resultFactAudios);
                                    sectionSourceResponse.setResultAudios(resultAudios);
                                    sectionSourceResponse.setAudioStart(0);
                                    sectionSourceResponse.setAudioCount(audioCount);

                                    System.out.println("adwsodkawodksokd " + sectionSourceResponse.getResultAudios().getResult_facts().size());

//                                    showProgressHorizontalDialogMessage("Uploading...", "Mengupload " + sectionSourceResponse.getAudioCount() + " Audio...", sectionSourceResponse.getAudioCount());
                                    System.out.println("AUDIOSTART " + sectionSourceResponse.getAudioStart());
                                    resultViewModel.submitResultImages(null, null, null, null, null, null, sectionSourceResponse.getResultAudios(), sectionSourceResponse.getAudioStart(), sectionSourceResponse.getAudioCount(), getTimeStampNow(), getAddressFromLatLng(task.getLatitude(), task.getLongitude()));
                                } else {
//                                    showProgressDialog("Mengakhiri Gawean...");
                                    taskViewModel.closeGawean(task, sectionSourceResponse.getResult().getId_result(), user);
                                }

                            }

                            break;
                        case RESULT_FACT_IMAGES_UPLOAD_ON_PROGRESS:
                            sectionSourceResponse.setImageStart(sectionSourceResponse.getImageStart() + 1);
//                            setProgressHorizontalDialogMessage(1);
//                            setProgressDialogMessage("Mengupload "+ (sectionSourceResponse.getImageStart() + 1) +" Foto...");
                            resultViewModel.submitResultImages(sectionSourceResponse.getResultImages(), sectionSourceResponse.getImageStart(), sectionSourceResponse.getImageCount(), sectionSourceResponse.getResultVideos(), null, null, sectionSourceResponse.getResultAudios(), null, null, getTimeStampNow(), getAddressFromLatLng(task.getLatitude(), task.getLongitude()));
                            break;
                        case RESULT_FACT_IMAGES_UPLOAD_DONE:
//                            setProgressHorizontalDialogMessage(10);
                            if (sectionSourceResponse.getResultVideos().getResult_facts().size() > 0) {
                                System.out.println("NUHUNAHINU");
                                Integer videoCount = 0;

                                List<ResultFact> resultFactVideos = new ArrayList<>();
                                for (ResultFact rf : sectionSourceResponse.getResultVideos().getResult_facts()) {
                                    Integer number_rff = 1;
                                    if (rf.getFiles() != null) {
                                        for (File rff : rf.getFiles()) {
                                            ResultFact rfi = new ResultFact();
                                            rfi.setId(rf.getId());
                                            rfi.setId_fact(rf.getId_fact());
                                            rfi.setId_result(rf.getId_result());
                                            rfi.setId_result_fact(rf.getId_result_fact());
                                            rfi.setFile(rff);
                                            rfi.setNotes(rf.getNotes());
                                            rfi.setId_item(rf.getId_item());
                                            rfi.setValue(rf.getValue());
                                            rfi.setResult_id(rf.getResult_id());

                                            resultFactVideos.add(rfi);
                                            videoCount++;
                                        }
                                    }
                                }

                                Result resultVideos = sectionSourceResponse.getResultVideos();
                                resultVideos.setResult_facts(resultFactVideos);
                                sectionSourceResponse.setResultVideos(resultVideos);
                                sectionSourceResponse.setVideoStart(0);
                                sectionSourceResponse.setVideoCount(videoCount);

//                                showProgressHorizontalDialogMessage("Uploading...", "Mengupload " + sectionSourceResponse.getVideoCount() + " Video...", sectionSourceResponse.getVideoCount());
                                resultViewModel.submitResultImages(null, null, null, sectionSourceResponse.getResultVideos(), sectionSourceResponse.getVideoStart(), sectionSourceResponse.getVideoCount(), null, null, null, getTimeStampNow(), getAddressFromLatLng(task.getLatitude(), task.getLongitude()));
                            } else {
//                                setProgressHorizontalDialogMessage(1);
//                                showProgressDialog("Mengakhiri Gawean...");
                                taskViewModel.closeGawean(task, sectionSourceResponse.getResultImages().getId_result(), user);
                            }


                        case RESULT_FACT_VIDEOS_UPLOAD_ON_UPDATE_PERCENTAGE:
                            System.out.println("PERSEN = " + sectionSourceResponse.getVideoProgressPercentage());
                            if (sectionSourceResponse.getResultVideos().getResult_facts().size() > 0) {
                                if (sectionSourceResponse.getVideoProgressPercentage() != null) {
                                    System.out.println("PERSEN > " + sectionSourceResponse.getVideoProgressPercentage());
//                                    showProgressHorizontalDialogMessage("Uploading...", "Mengupload Video...", 100);
//                                    setFixProgressHorizontalDialogMessage(sectionSourceResponse.getVideoProgressPercentage());
                                }
                            }
                            break;
                        case RESULT_FACT_VIDEOS_UPLOAD_ON_PROGRESS:
                            sectionSourceResponse.setVideoStart(sectionSourceResponse.getVideoStart() + 1);
//                            setProgressHorizontalDialogMessage(1);
//                            setProgressDialogMessage("Mengupload "+ (sectionSourceResponse.getImageStart() + 1) +" Foto...");
                            resultViewModel.submitResultImages(null, null, null, sectionSourceResponse.getResultVideos(), sectionSourceResponse.getVideoStart(), sectionSourceResponse.getVideoCount(), sectionSourceResponse.getResultAudios(), null, null, getTimeStampNow(), getAddressFromLatLng(task.getLatitude(), task.getLongitude()));
                            break;
                        case RESULT_FACT_VIDEOS_UPLOAD_DONE:
                            if (sectionSourceResponse.getResultAudios().getResult_facts().size() > 0) {
                                System.out.println("NUHUNAHINU");
                                Integer audioCount = 0;

                                List<ResultFact> resultFactAudios = new ArrayList<>();
                                for (ResultFact rf : sectionSourceResponse.getResultAudios().getResult_facts()) {
                                    Integer number_rff = 1;
                                    if (rf.getFiles() != null) {
                                        for (File rff : rf.getFiles()) {
                                            ResultFact rfi = new ResultFact();
                                            rfi.setId(rf.getId());
                                            rfi.setId_fact(rf.getId_fact());
                                            rfi.setId_result(rf.getId_result());
                                            rfi.setId_result_fact(rf.getId_result_fact());
                                            rfi.setFile(rff);
                                            rfi.setNotes(rf.getNotes());
                                            rfi.setId_item(rf.getId_item());
                                            rfi.setValue(rf.getValue());
                                            rfi.setResult_id(rf.getResult_id());

                                            resultFactAudios.add(rfi);
                                            audioCount++;
                                        }
                                    }
                                }

                                Result resultAudios = sectionSourceResponse.getResultAudios();
                                resultAudios.setResult_facts(resultFactAudios);
                                sectionSourceResponse.setResultAudios(resultAudios);
                                sectionSourceResponse.setAudioStart(0);
                                sectionSourceResponse.setAudioCount(audioCount);

//                                showProgressHorizontalDialogMessage("Uploading...", "Mengupload " + sectionSourceResponse.getAudioCount() + " Audio...", sectionSourceResponse.getAudioCount());
                                resultViewModel.submitResultImages(null, null, null, null, null, null, sectionSourceResponse.getResultVideos(), sectionSourceResponse.getVideoStart(), sectionSourceResponse.getVideoCount(), getTimeStampNow(), getAddressFromLatLng(task.getLatitude(), task.getLongitude()));
                            } else {
//                                setProgressHorizontalDialogMessage(1);
//                                showProgressDialog("Mengakhiri Gawean...");
                                taskViewModel.closeGawean(task, sectionSourceResponse.getResultVideos().getId_result(), user);
                            }
                            break;
                        case RESULT_FACT_AUDIOS_UPLOAD_ON_PROGRESS:
                            sectionSourceResponse.setAudioStart(sectionSourceResponse.getAudioStart() + 1);
//                            setProgressHorizontalDialogMessage(1);
//                            setProgressDialogMessage("Mengupload "+ (sectionSourceResponse.getImageStart() + 1) +" Foto...");
                            resultViewModel.submitResultImages(null, null, null, null, null, null, sectionSourceResponse.getResultAudios(), sectionSourceResponse.getAudioStart(), sectionSourceResponse.getAudioCount(), getTimeStampNow(), getAddressFromLatLng(task.getLatitude(), task.getLongitude()));
                            break;
                        case  RESULT_FACT_AUDIOS_UPLOAD_DONE:
//                            setProgressHorizontalDialogMessage(1);
//                            showProgressDialog("Mengakhiri Gawean...");
                            taskViewModel.closeGawean(task, sectionSourceResponse.getResultAudios().getId_result(), user);
                            break;
                    }
                }
            }
        });

        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {

        Intent restartServiceIntent = new Intent(getApplicationContext(), this.getClass());
        restartServiceIntent.setPackage(getPackageName());
        startService(restartServiceIntent);

        super.onTaskRemoved(rootIntent);
    }

    public String getTimeStampNow() {
        //Date object
        Date date= new Date();
        //getTime() returns current time in milliseconds
        long time = date.getTime();
        //Passed the milliseconds to constructor of Timestamp class
        Timestamp ts = new Timestamp(time);
        System.out.println("Current Time Stamp: "+ts);

        return ts.toString();
    }

    public String getAddressFromLatLng(Double latitude, Double longitude) {
        try {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, new Locale("in", "ID"));

            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

            return (address + " " + city + " " + postalCode).replace(",", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

}
