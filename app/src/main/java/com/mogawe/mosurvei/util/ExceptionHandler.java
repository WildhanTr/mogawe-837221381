package com.mogawe.mosurvei.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;

import com.mogawe.mosurvei.view.shared.error_page.ErrorActivity;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionHandler implements java.lang.Thread.UncaughtExceptionHandler {

    private final Activity myContext;
    private final String LINE_SEPARATOR = "\n";

    public ExceptionHandler(Activity myContext) {
        this.myContext = myContext;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable exception) {
        StringWriter stackTrace = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTrace));
        StringBuilder errorReport = new StringBuilder();
        errorReport.append("************ CAUSE OF ERROR ************\n\n");
        errorReport.append(stackTrace.toString());

        StringBuilder deviceInformation = new StringBuilder();
        deviceInformation.append("\n************ DEVICE INFORMATION ***********\n");
        deviceInformation.append("Brand: ");
        deviceInformation.append(Build.BRAND);
        deviceInformation.append(LINE_SEPARATOR);
        deviceInformation.append("Device: ");
        deviceInformation.append(Build.DEVICE);
        deviceInformation.append(LINE_SEPARATOR);
        deviceInformation.append("Model: ");
        deviceInformation.append(Build.MODEL);
        deviceInformation.append(LINE_SEPARATOR);
        deviceInformation.append("Id: ");
        deviceInformation.append(Build.ID);
        deviceInformation.append(LINE_SEPARATOR);
        deviceInformation.append("Product: ");
        deviceInformation.append(Build.PRODUCT);
        deviceInformation.append(LINE_SEPARATOR);

        StringBuilder firmwareInformation = new StringBuilder();
        firmwareInformation.append("\n************ FIRMWARE ************\n");
        firmwareInformation.append("Android Version / SDK: ");
        firmwareInformation.append(Build.VERSION.SDK);
        firmwareInformation.append(LINE_SEPARATOR);
        firmwareInformation.append("Release: ");
        firmwareInformation.append(Build.VERSION.RELEASE);
        firmwareInformation.append(LINE_SEPARATOR);
        firmwareInformation.append("Incremental: ");
        firmwareInformation.append(Build.VERSION.INCREMENTAL);
        firmwareInformation.append(LINE_SEPARATOR);

        Intent intent = new Intent(myContext, ErrorActivity.class);
        intent.putExtra("error", errorReport.toString());
        intent.putExtra("device", deviceInformation.toString());
        intent.putExtra("firmware", firmwareInformation.toString());
        intent.putExtra("activityName", myContext.getClass().getSimpleName());
        myContext.startActivity(intent);

        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
    }
}
