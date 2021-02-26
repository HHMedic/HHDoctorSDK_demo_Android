package com.hhmedic.android.hhdoctorvideodemo.activity;

import android.content.Context;
import android.content.SharedPreferences;

import com.hhmedic.android.hhdoctorvideodemo.application.SharedPreferenceUtils;


public class LocalConfig {

    private static final String KEY = "isDevelop";
    private static final String USER_TOKEN = "demo_user_token";
    private static final String PID = "pid";
    private static final String ENABLE_ADD_MEMBER = "can_add_member";
    private static final String ENABLE_MULTI_CALL = "enable_multi_call";
    private static final String ENABLE_ACTIVATE = "enable_activate";
    private static final String ENABLE_MEDICAL = "enable_medical";

    private static final String MESSAGE_TITLE = "message_title";

    static final String DefaultUserToken = "ADA30C693C69A30E652F4FEE4F1C8025BB7A1F1299CAF508460D59212594CED3";//"8DC2FD1D49451309DF7123716BFF20843F0D04F68EA2608F6783B874E4F50EEF";
    static final String DefaultCallOrderId = "1559198060885";
    static final String DefaultMedicineOrderId = "Y2019053014434215200";

    static void setDevelop(Context context, Boolean isDevelop) {
        SharedPreferenceUtils.setValue(context,KEY,isDevelop);
    }

    public static Boolean isDevelop(Context context) {
        return SharedPreferenceUtils.getBooleanValue(context,KEY,true);
    }

    static void setLoginedToken(Context context, String token) {
        SharedPreferenceUtils.setValue(context,USER_TOKEN,token.trim());
    }

    static String getLoginedToken(Context context) {
        return SharedPreferenceUtils.getStringValue(context,USER_TOKEN).trim();
    }

    public static void setPid(Context context,String pid) {
        SharedPreferenceUtils.setValue(context,PID,pid);
    }

    public static String getPid(Context context) {
        return SharedPreferenceUtils.getStringValue(context,PID);
    }

    public static void setEnableAddMember(Context context, Boolean canAddMember) {
        SharedPreferenceUtils.setValue(context, ENABLE_ADD_MEMBER,canAddMember);
    }

    public static Boolean getEnableAddMember(Context context) {
        return SharedPreferenceUtils.getBooleanValue(context, ENABLE_ADD_MEMBER, true);
    }

    public static void setEnableMultiCall(Context context,Boolean canAddMember) {
        SharedPreferenceUtils.setValue(context, ENABLE_MULTI_CALL,canAddMember);
    }

    public static Boolean getEnableMultiCall(Context context) {
        return SharedPreferenceUtils.getBooleanValue(context, ENABLE_MULTI_CALL, true);
    }

    public static void setEnableActivate(Context context,Boolean canAddMember) {
        SharedPreferenceUtils.setValue(context, ENABLE_ACTIVATE,canAddMember);
    }

    public static Boolean getEnableActivate(Context context) {
        return SharedPreferenceUtils.getBooleanValue(context, ENABLE_ACTIVATE, true);
    }

    public static void setEnableMedical(Context context,Boolean canAddMember) {
        SharedPreferenceUtils.setValue(context, ENABLE_MEDICAL,canAddMember);
    }

    public static Boolean getEnableMedical(Context context) {
        return SharedPreferenceUtils.getBooleanValue(context, ENABLE_MEDICAL, true);
    }

    public static void setMessageTitle(Context context,String title) {
        SharedPreferenceUtils.setValue(context,MESSAGE_TITLE,title);
    }

    public static String getMessageTitle(Context context) {
        return SharedPreferenceUtils.getStringValue(context,MESSAGE_TITLE);
    }
}
