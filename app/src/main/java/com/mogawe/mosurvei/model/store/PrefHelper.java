package com.mogawe.mosurvei.model.store;

import android.content.SharedPreferences;
import android.location.Location;

import com.mogawe.mosurvei.MoSurveiApplication;

public class PrefHelper {

    private static SharedPreferences preferences;

    private static void initPref() {
        preferences = MoSurveiApplication.getInstance().getSharedPreferences();
    }

    public static void setString(PrefKey key, String value) {
        initPref();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key.toString(), value);
        editor.commit();
    }

    public static String getString(PrefKey key) {
        initPref();
        return preferences.getString(key.toString(), "");
    }

    public static void setInt(PrefKey key, int value) {
        initPref();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key.toString(), value);
        editor.commit();
    }

    public static int getInt(PrefKey key) {
        initPref();
        return preferences.getInt(key.toString(), -1);
    }

    public static void setLong(PrefKey key, long value) {
        initPref();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key.toString(), value);
        editor.commit();
    }

    public static long getLong(PrefKey key) {
        initPref();
        return preferences.getLong(key.toString(), -1);
    }

    public static void setBoolean(PrefKey key, boolean value) {
        initPref();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key.toString(), value);
        editor.commit();
    }
    public static boolean getBoolean(PrefKey key) {
        initPref();
        return preferences.getBoolean(key.toString(), false);
    }

    public static boolean hasString(PrefKey key) {
        return PrefHelper.getString(key) != null && !PrefHelper.getString(key).equals("");
    }

    public static void clearPreference(PrefKey key) {
        initPref();
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key.toString());
        editor.commit();
    }

    public static void clearAllPreferences() {
        initPref();
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

}
