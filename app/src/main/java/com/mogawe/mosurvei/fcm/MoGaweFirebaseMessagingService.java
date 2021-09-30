package com.mogawe.mosurvei.fcm;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.mogawe.mosurvei.MoSurveiApplication;
import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.model.bean.location.Coordinate;
import com.mogawe.mosurvei.model.db.entity.Notification;
import com.mogawe.mosurvei.model.db.entity.Tracking;
import com.mogawe.mosurvei.model.repository.TaskRepository;
import com.mogawe.mosurvei.model.repository.TrackingRepository;
import com.mogawe.mosurvei.model.repository.UserRepository;
import com.mogawe.mosurvei.model.store.PrefHelper;
import com.mogawe.mosurvei.model.store.PrefKey;
import com.mogawe.mosurvei.view.BaseActivity;
import com.qiscus.sdk.chat.core.QiscusCore;
import com.qiscus.sdk.chat.core.util.QiscusFirebaseMessagingUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

public class MoGaweFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FcmMessagingService";
    private static final String MAIN_ACTIVITY = "MainActivity";

    private static final String KEY_TYPE = "type";

    private JSONObject messageJson;

    private UserRepository repository = new UserRepository(((MoSurveiApplication) getApplication()));
    private TaskRepository taskRepository;
    private TrackingRepository trackingRepository = new TrackingRepository(((MoSurveiApplication) getApplication()));

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        messageJson = constructMessageToJsonObject(remoteMessage.getData());
        taskRepository = new TaskRepository(((MoSurveiApplication) getApplication()));
        repository = new UserRepository(((MoSurveiApplication) getApplication()));

        PrefHelper.setBoolean(PrefKey.IS_NOTIF_AVAILABLE, true);
        Intent intent1 = new Intent("com.mogawe.mosurvei_FCM-MESSAGE");
        intent1.putExtra("n_type", "set_notif_indicator");
        LocalBroadcastManager localBroadcastManager1 = LocalBroadcastManager.getInstance(this);
        localBroadcastManager1.sendBroadcast(intent1);

    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        try {
            repository = new UserRepository((MoSurveiApplication) getApplication());
            repository.updateDeviceId(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d("Qiscus", "onNewToken " + s);
        //Notify Qiscus Chat SDK about FCM token
        QiscusCore.registerDeviceToken(s);
        //TODO : Application part here, maybe you need to send FCM token to your Backend
    }

    private JSONObject constructMessageToJsonObject(Map<String, String> data) {
        JSONObject object = new JSONObject();
        for (Map.Entry<String, String> key : data.entrySet()) {
            try {
                Log.i("GCM Message.Key : ", key.getKey());
                Log.i("GCM Message.Value : ", key.getValue());
                object.put(key.getKey(), key.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return object;
    }

    private String getValueFromJson(String key) {
        try {
            return messageJson.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Create and show a simple notification containing the received GCM message.
     *
     * @param message GCM message received.
     */
    private void revokeActivity(String message, String code) {
        Intent intent = new Intent(MAIN_ACTIVITY);

        //put whatever data you want to send, if any
        intent.putExtra("message", message);

        //send broadcast
        sendBroadcast(intent);
        //System.out.println("OUT >> TEST");

    }

    /** =============================== GENERAL NOTIFICATION HANDLER ======================== **/

    /**
     * Create and show a simple general notification related information containing the received GCM message.
     *
     * @param message GCM message received.
     */
    public void sendNotification(Context context, String title, String message, String object, String type) {

        // setup notif

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, "MoGawe");
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notificationDB = new Notification();
        notificationDB.setTitle(title);
        notificationDB.setDesc(message);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        notificationDB.setTimeStamp(String.valueOf(timestamp.getTime()));

        repository.saveNotification(notificationDB);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    public static void getCurrentDeviceToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }

                        if (task.getResult() != null) {
                            String currentToken = task.getResult().getToken();

                            UserRepository repository = new UserRepository(MoSurveiApplication.getInstance());
                            repository.updateDeviceId(currentToken);
                        }

                    }
                });
    }
}
