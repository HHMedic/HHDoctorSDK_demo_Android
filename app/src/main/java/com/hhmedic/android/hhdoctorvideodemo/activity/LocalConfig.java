package com.hhmedic.android.hhdoctorvideodemo.activity;

import android.content.Context;
import android.content.SharedPreferences;


public class LocalConfig {

    public static final String KEY = "isDevelop";
    public static final String USER_TOKEN = "user_token";

    public static final String DefaultUserToken = "D68A2DBC99CD9289CD19886F16F9156F3F0D04F68EA2608F6783B874E4F50EEF";
    public static final String DefaultCallOrderId = "1559198060885";
    public static final String DefaultMedicineOrderId = "Y2019053014434215200";

//    public static String currentUserToken;

    public static void setDevelop(Context context, Boolean isDevelop) {
        SharedPreferences preferences = context.getSharedPreferences("develop",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY, isDevelop);
        editor.commit();
    }

    public static Boolean isDevelop(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("develop",Context.MODE_PRIVATE);
        return preferences.getBoolean(KEY, true);
    }

    public static void setLoginedToken(Context context,String token) {
        SharedPreferences preferences = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USER_TOKEN, token);
        editor.commit();
    }

    public static String getLoginedToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        return preferences.getString(USER_TOKEN, "");
    }
}
