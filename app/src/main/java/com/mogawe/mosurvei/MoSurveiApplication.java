package com.mogawe.mosurvei;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.multidex.MultiDexApplication;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.mogawe.mosurvei.fcm.MoGaweFirebaseMessagingService;
import com.mogawe.mosurvei.model.db.MoGaweDatabase;
import com.mogawe.mosurvei.model.db.entity.Notification;
import com.mogawe.mosurvei.model.network.Service;
import com.mogawe.mosurvei.model.repository.UserRepository;
import com.mogawe.mosurvei.util.NotificationClickReceiver;
import com.qiscus.sdk.chat.core.QiscusCore;
import com.qiscus.sdk.chat.core.data.model.NotificationListener;
import com.qiscus.sdk.chat.core.data.model.QiscusComment;
import com.qiscus.sdk.chat.core.util.BuildVersionUtil;
import com.qiscus.sdk.chat.core.util.QiscusAndroidUtil;
import com.qiscus.sdk.chat.core.util.QiscusNumberUtil;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

import java.sql.Timestamp;
import java.util.Objects;

public class MoSurveiApplication extends MultiDexApplication {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String APPID = "mogawe-i1y2t3fnz2jt32";

    private static MoSurveiApplication instance;
    private Service service;
    private SharedPreferences sharedPreferences;
    private MoGaweDatabase database;
    private Boolean taskRunning = false;
    private UserRepository repository = new UserRepository(((MoSurveiApplication) this));

    public static MoSurveiApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        this.service = new Service();
        this.database = MoGaweDatabase.getInstance(this);
        setupSharedPreferences();

        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))//enable logging when app is in debug mode
                .twitterAuthConfig(new TwitterAuthConfig(getResources().getString(R.string.com_twitter_sdk_android_CONSUMER_KEY), getResources().getString(R.string.com_twitter_sdk_android_CONSUMER_SECRET)))//pass the created app Consumer KEY and Secret also called API Key and Secret
                .debug(true)//enable debug mode
                .build();

        //finally initialize twitter with created configs
        Twitter.initialize(config);

        MoGaweFirebaseMessagingService.getCurrentDeviceToken();

        //Initiate Qiscus
        QiscusCore.setup(this, APPID);

        QiscusCore.getChatConfig().setEnableFcmPushNotification(true).setNotificationListener(new NotificationListener() {
            @Override
            public void onHandlePushNotification(Context context, QiscusComment qiscusComment) {
                showNotification(context, qiscusComment, repository);
            }
        }); // default is false

    }

    private Activity mCurrentActivity = null;
    public Activity getCurrentActivity(){
        return mCurrentActivity;
    }
    public void setCurrentActivity(Activity mCurrentActivity){
        this.mCurrentActivity = mCurrentActivity;
    }

    public Service getService() {
        return service;
    }

//    public Service.Api getApi() {
//        return service.getApi();
//    }

    public Service.ApiUserService getApiUserService() {
        return service.getApiUserService();
    }

    public Service.ApiUserCitcallService getApiUserCitcallService() {
        return service.getApiUserCitcallService();
    }

    public Service.ApiTaskService getApiTaskService() {
        return service.getApiTaskService();
    }

    public Service.ApiSectionService getSectionService() {
        return service.getApiSectionService();
    }

    public Service.ApiSearchService getSearchService() {
        return service.getApiSearchService();
    }

    public Service.ApiSalesService getApiSalesService() {
        return service.getApiSalesService();
    }

    public Service.ApiNaufalService getApiNaufalService() {
        return service.getApiNaufalService();
    }

    private void setupSharedPreferences() {
        this.sharedPreferences = getSharedPreferences(MoSurveiApplication.class.getSimpleName(), Context.MODE_PRIVATE);
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public MoGaweDatabase getDatabase() {
        return database;
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    public boolean checkPlayServices(Activity activity) {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(activity, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Toast.makeText(activity, "This device is not supported to use Google Play Services", Toast.LENGTH_SHORT).show();
                activity.finish();
            }
            return false;
        }
        return true;
    }

    public LocalBroadcastManager getLocalBroadcastManager() {
        return LocalBroadcastManager.getInstance(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static void showNotification(Context context, QiscusComment qiscusComment, UserRepository userRepository) {
        System.out.println("DSADSVsdavfdvbgfbdewdafsdf");

        if (QiscusCore.getDataStore().isContains(qiscusComment)) {
            return;
        }

        System.out.println("sadfsgdhffgfdsa");

        QiscusCore.getDataStore().addOrUpdate(qiscusComment);

        String notificationChannelId = QiscusCore.getApps().getPackageName() + ".qiscus.sdk.notification.channel";
        if (BuildVersionUtil.isOreoOrHigher()) {
            NotificationChannel notificationChannel =
                    new NotificationChannel(notificationChannelId, "Chat", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        PendingIntent pendingIntent;
        Intent openIntent = new Intent(context, NotificationClickReceiver.class);
        openIntent.putExtra("data", qiscusComment);

        pendingIntent = PendingIntent.getBroadcast(context, QiscusNumberUtil.convertToInt(qiscusComment.getRoomId()),
                openIntent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_ONE_SHOT);

        System.out.println("pendinggaweae " + pendingIntent.getIntentSender().getCreatorUserHandle().toString());

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, notificationChannelId);
        notificationBuilder.setContentTitle(qiscusComment.getSender())
                .setContentIntent(pendingIntent)
                .setContentText(qiscusComment.getMessage())
                .setTicker(qiscusComment.getMessage())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setGroup("CHAT_NOTIF_" + qiscusComment.getRoomId())
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        Notification notificationDB = new Notification();
        notificationDB.setTitle(qiscusComment.getRoomName());
        notificationDB.setDesc(qiscusComment.getMessage());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        notificationDB.setTimeStamp(String.valueOf(timestamp.getTime()));

        userRepository.saveNotification(notificationDB);

        QiscusAndroidUtil.runOnUIThread(() -> NotificationManagerCompat.from(context)
                .notify(QiscusNumberUtil.convertToInt(qiscusComment.getRoomId()), notificationBuilder.build()));
    }

}
