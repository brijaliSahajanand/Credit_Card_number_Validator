package com.example.myapplication.constant;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class Global extends Application {
    private static final String ONESIGNAL_APP_ID = "fc494916-6008-4892-b027-366c8e1097f1";

    private static Context mContext;
    private static Global mInstance;
    static SharedPreferences.Editor prefEditor;
    static SharedPreferences preferences;

    public void onCreate() {
        super.onCreate();
        SharedPreferences sharedPreferences = getSharedPreferences("news", 0);
        preferences = sharedPreferences;
        prefEditor = sharedPreferences.edit();
        mInstance = this;
        mContext = this;


    }

    public static synchronized Context getContext() {
        Context context;
        synchronized (Global.class) {
            context = mContext;
        }
        return context;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();



    }

    public static void set_InAppPurchase(String str) {
        prefEditor.putString("InAppPurchase", str).commit();
    }

    public static String get_InAppPurchase() {
        return preferences.getString("InAppPurchase", "");
    }

    public static void set_Inprdictionads(String str) {
        prefEditor.putString("Inprdictionads", str).commit();
    }

    public static String get_Inprdictionads() {
        return preferences.getString("Inprdictionads", "");
    }

    public static void set_InprdictionadsGetBothTeamstoscore(String str) {
        prefEditor.putString("InprdictionadsGetBothTeamstoscore", str).commit();
    }

    public static String get_InprdictionadsGetBothTeamstoscore() {
        return preferences.getString("InprdictionadsGetBothTeamstoscore", "");
    }

    public static void set_InGetFirstHalfHour(String str) {
        prefEditor.putString("InGetFirstHalfHour", str).commit();
    }

    public static String get_InGetFirstHalfHour() {
        return preferences.getString("InGetFirstHalfHour", "");
    }

    public static void set_InGetGoalOver(String str) {
        prefEditor.putString("GetGoalOver", str).commit();
    }

    public static String get_InGetGoalOver() {
        return preferences.getString("GetGoalOver", "");
    }

    public static void set_InGetGoalUnder(String str) {
        prefEditor.putString("GetGoalUnder", str).commit();
    }

    public static String get_InGetGoalUnder() {
        return preferences.getString("GetGoalUnder", "");
    }

    public static void set_InGetOverOnePointFiveGoals(String str) {
        prefEditor.putString("GetOverOnePointFiveGoals", str).commit();
    }

    public static String get_InGetOverOnePointFiveGoals() {
        return preferences.getString("GetOverOnePointFiveGoals", "");
    }

    public static void set_InGetOverTwoPointFiveGoals(String str) {
        prefEditor.putString("GetOverTwoPointFiveGoals", str).commit();
    }

    public static String get_InGetOverTwoPointFiveGoals() {
        return preferences.getString("GetOverTwoPointFiveGoals", "");
    }

    public static void set_InGetOverZeroFiveFirstHalfGoals(String str) {
        prefEditor.putString("GetOverZeroFiveFirstHalfGoals", str).commit();
    }

    public static String get_InGetOverZeroFiveFirstHalfGoals() {
        return preferences.getString("GetOverZeroFiveFirstHalfGoals", "");
    }

    public static void set_InGetOverzeroPointFiveGoals(String str) {
        prefEditor.putString("GetOverzeroPointFiveGoals", str).commit();
    }

    public static String get_InGetOverzeroPointFiveGoals() {
        return preferences.getString("GetOverzeroPointFiveGoals", "");
    }

    public static void set_InGetSecondHalfHour(String str) {
        prefEditor.putString("GetSecondHalfHour", str).commit();
    }

    public static String get_InGetSecondHalfHour() {
        return preferences.getString("GetSecondHalfHour", "");
    }

    public static void set_InmyFavorite2Adapter(String str) {
        prefEditor.putString("myFavorite2Adapter", str).commit();
    }

    public static String get_InmyFavorite2Adapter() {
        return preferences.getString("myFavorite2Adapter", "");
    }

    public static void set_email_add(String str) {
        prefEditor.putString("email_add", str).commit();
    }

    public static String get_email_add() {
        return preferences.getString("email_add", "");
    }
}
