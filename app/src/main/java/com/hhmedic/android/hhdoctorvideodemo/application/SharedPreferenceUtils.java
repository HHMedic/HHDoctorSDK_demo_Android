package com.hhmedic.android.hhdoctorvideodemo.application;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtils {

    private static final String PREFERENCE_NAME = "HH_SDK_DEMO";

    public static void setValue(Context context,String key,String value) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(key, value);
        editor.apply();
    }

    public static void setValue(Context context,String key,Boolean value) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static String getStringValue(Context context,String key) {
        return getSharePreference(context).getString(key,"");
    }

    public static boolean getBooleanValue(Context context,String key,boolean defaultValue) {
        return getSharePreference(context).getBoolean(key, defaultValue);
    }

    private static SharedPreferences getSharePreference(Context context) {
        return context.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        SharedPreferences preferences = getSharePreference(context);
        return preferences.edit();
    }
}
