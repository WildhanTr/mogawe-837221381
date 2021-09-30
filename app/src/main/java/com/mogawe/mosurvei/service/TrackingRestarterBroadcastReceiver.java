package com.mogawe.mosurvei.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class TrackingRestarterBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try{
            context.startService(new Intent(context, TrackingService.class));
        }catch (Exception e){

        }
//        try{
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                context.startForegroundService(new Intent(context, TrackingService.class));
//            } else {
//                context.startService(new Intent(context, TrackingService.class));
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }

    }
}