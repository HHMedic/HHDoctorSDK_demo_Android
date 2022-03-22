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
    private static final String ENABLE_USER_CENTER = "enable_user_center";
    private static final String ENABLE_SUMMARY_CARD = "enable_summary_card";
    private static final String ENABLE_MEDICAL_CARD = "enable_medical_card";

    private static final String ENABLE_VIP_INFO = "enable_vip_info";

    private static final String ENABLE_ADD_MEMBER_IN_DOC  = "can_add_member_in_doc";

    private static final String ENABLE_CAN_BUY  = "can_buy";

    private static final String HIDE_CAMERA_CONTROL = "hide_camera_control";

    private static final String CLOSE_CAMERA_CALL = "close_camera_call";

    private static final String CLOSE_MORE_FUNC = "close_more_func";

    private static final String MESSAGE_TITLE = "message_title";

    static final String DefaultUserToken = "3BCC438BBBE99F86C48A3C1DB11A43843F0D04F68EA2608F6783B874E4F50EEF";
    static final String DefaultCallOrderId = "1559198060885";
    static final String DefaultMedicineOrderId = "Y2019053014434215200";

    static void setDevelop(Context context, Boolean isDevelop) {
        SharedPreferenceUtils.setValue(context,KEY,isDevelop);
    }

    public static boolean isDevelop(Context context) {
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

    public static boolean getEnableAddMember(Context context) {
        return SharedPreferenceUtils.getBooleanValue(context, ENABLE_ADD_MEMBER, true);
    }

    public static void setEnableMultiCall(Context context,boolean canAddMember) {
        SharedPreferenceUtils.setValue(context, ENABLE_MULTI_CALL,canAddMember);
    }

    public static boolean getEnableMultiCall(Context context) {
        return SharedPreferenceUtils.getBooleanValue(context, ENABLE_MULTI_CALL, true);
    }

    public static void setEnableActivate(Context context,boolean canAddMember) {
        SharedPreferenceUtils.setValue(context, ENABLE_ACTIVATE,canAddMember);
    }

    public static boolean getEnableActivate(Context context) {
        return SharedPreferenceUtils.getBooleanValue(context, ENABLE_ACTIVATE, true);
    }

    public static void setEnableMedical(Context context,boolean canAddMember) {
        SharedPreferenceUtils.setValue(context, ENABLE_MEDICAL,canAddMember);
    }

    public static boolean getEnableMedical(Context context) {
        return SharedPreferenceUtils.getBooleanValue(context, ENABLE_MEDICAL, true);
    }

    public static void setMessageTitle(Context context,String title) {
        SharedPreferenceUtils.setValue(context,MESSAGE_TITLE,title);
    }

    public static String getMessageTitle(Context context) {
        return SharedPreferenceUtils.getStringValue(context,MESSAGE_TITLE);
    }

    public static void setEnableUserCenter(Context context,boolean enable) {
        SharedPreferenceUtils.setValue(context, ENABLE_USER_CENTER,enable);
    }

    public static boolean getEnableUserCenter(Context context) {
        return SharedPreferenceUtils.getBooleanValue(context, ENABLE_USER_CENTER, true);
    }

    public static void setEnableSummaryCard(Context context,boolean enable) {
        SharedPreferenceUtils.setValue(context, ENABLE_SUMMARY_CARD,enable);
    }

    public static boolean getEnableSummaryCard(Context context) {
        return SharedPreferenceUtils.getBooleanValue(context, ENABLE_SUMMARY_CARD, true);
    }

    public static void setEnableMedicalCard(Context context,boolean canAddMember) {
        SharedPreferenceUtils.setValue(context, ENABLE_MEDICAL_CARD,canAddMember);
    }

    public static Boolean getEnableMedicalCard(Context context) {
        return SharedPreferenceUtils.getBooleanValue(context, ENABLE_MEDICAL_CARD, true);
    }



    public static void setEnableVipInfo(Context context,boolean canShowVipInfo){

        SharedPreferenceUtils.setValue(context, ENABLE_VIP_INFO,canShowVipInfo);
    }

    public static boolean getEnableVipInfo(Context context) {
        return SharedPreferenceUtils.getBooleanValue(context, ENABLE_VIP_INFO, true);
    }



    public static void setEnableAddMemberInDoc(Context context,boolean canShowVipInfo){

        SharedPreferenceUtils.setValue(context, ENABLE_ADD_MEMBER_IN_DOC,canShowVipInfo);
    }

    public static boolean getEnableAddMemberInDoc(Context context) {
        return SharedPreferenceUtils.getBooleanValue(context, ENABLE_ADD_MEMBER_IN_DOC, true);
    }

    public static void setEnableCanBuy(Context context,boolean canShowVipInfo){

        SharedPreferenceUtils.setValue(context, ENABLE_CAN_BUY,canShowVipInfo);
    }

    public static boolean getEnableCanBuy(Context context) {
        return SharedPreferenceUtils.getBooleanValue(context, ENABLE_CAN_BUY, true);
    }

    public static void setHideCameraControl(Context context,boolean canShowVipInfo){

        SharedPreferenceUtils.setValue(context, HIDE_CAMERA_CONTROL,canShowVipInfo);
    }

    public static boolean getHideCameraControl(Context context) {
        return SharedPreferenceUtils.getBooleanValue(context, HIDE_CAMERA_CONTROL, false);
    }

    public static void setCloseCameraCall(Context context,boolean canShowVipInfo){

        SharedPreferenceUtils.setValue(context, CLOSE_CAMERA_CALL,canShowVipInfo);
    }

    public static boolean getCloseCameraCall(Context context) {
        return SharedPreferenceUtils.getBooleanValue(context, CLOSE_CAMERA_CALL, false);
    }

    public static void setCloseMoreFunc(Context context, boolean close) {
        SharedPreferenceUtils.setValue(context, CLOSE_MORE_FUNC, close);
    }

    public static boolean getCloseMoreFunc(Context context) {
        return SharedPreferenceUtils.getBooleanValue(context, CLOSE_MORE_FUNC, false);
    }
}
