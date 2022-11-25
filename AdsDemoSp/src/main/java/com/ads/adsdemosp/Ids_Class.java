package com.ads.adsdemosp;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;

import com.ads.adsdemosp.AdsClass.Ads_AppOpen;
import com.ads.adsdemosp.AdsClass.Ads_Banner;
import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.ads.adsdemosp.AdsClass.Ads_Native;
import com.facebook.ads.AdSettings;
import com.facebook.ads.internal.api.AdSettingsApi;
import com.facebook.ads.internal.settings.AdInternalSettings;

import java.util.ArrayList;
import java.util.List;

public class Ids_Class {





    public static int Interstitial_adsclick = 0;
    public static int Native_adsscreen = 0;
    public static String admob_Interstitial_ids = "/6499/example/interstitia";
    public static String admob_NativeFull_ids = "/6499/example/nativ";
    public static String admob_NativeCustom_ids = "/6499/example/nativ";
    public static String admob_Banner_ids = "/6499/example/banne";
    public static String admob_AppOpen_ids = "/6499/example/app-ope";

    public static String admob_Rewards_ids = "/6499/example/rewarde";

    public static List<String> admobInterstitialIds_list = new ArrayList<>();
    public static List<String> admobNativeFullIds_list = new ArrayList<>();
    public static List<String> admobNativeCustomIds_list = new ArrayList<>();
    public static List<String> admobBannerIds_list = new ArrayList<>();
    public static List<String> admobAppopenIds_list = new ArrayList<>();

    public static String fb_Interstitial_ids = "YOUR_PLACEMENT_I";
    public static String fb_NativeFull_ids = "YOUR_PLACEMENT_I";
    public static String fb_NativeBanner_ids = "YOUR_PLACEMENT_I";

    public static String ads_type = "admob";
    public static String ads_native_type = "banner";

    public static String ads_native_btn_color = "#FFFFFFFF";
    public static String ads_native_text_color = "#FF000000";
    public static String ads_native_in_text_color = "#FFFFFFFF";

    public static boolean buttonanimate = false;
    public static String animation_type = "zoominout";

    public static boolean ads_App_open_InterstitialAd = true;

    public static boolean ads_quiz_show = true;
    public static boolean ads_quiz_by_page_show = false;

    public static int quiz_Interstitial_adsclick = 2;
    public static int quiz_Native_adsclick = 2;
    public static int quiz_Banner_adsclick = 2;
    public static int quiz_AppOpen_adsclick = 2;
    public static int quiz_Interstitial_backadsclick = 2;

    public static int AppOpen_adsclick = 2;
    public static int AppOpen_Backadsclick = 2;

    public static int Interstitial_Backadsclick = 0;

    public static boolean is_big_native_quiz = false;
    public static boolean is_small_native_quiz = false;


    public static String ads_native_bg_color = "#68000000";
    public static String fb_ads_native_btn_color = "#4286F4";
    public static String fb_ads_native_text_color = "#FFFFFFFF";
    public static String fb_ads_native_bg_color = "#FFFFFFFF";
    public static String fb_ads_native_in_txt_color = "#FF000000";

    public static String quizLink = "";

    public static boolean quiz_header_show = false;

    public static boolean ad_one_by_one_ids = false;


    public static void Laod_NativeAds(Context context) {
       // Ads_AppOpen.OpenAppAds((Activity) context);
        if (ads_type.equals("admob")) {
            Ads_Native.AdmobNativeFull_Load(context);
            Ads_Interstitial.Admob_InterstitialAd_Load(context);
            if (Ids_Class.ads_native_type.equals("nativebanner")) {
                Ads_Banner.AdmobNativeCustom_Load(context);
            }
        } else {
            Ads_Native.FB_NativeAd_Load(context);
            Ads_Interstitial.Fb_InterstitialAd_Load(context);
            Ads_Banner.Fb_NativeBanner_Load(context);
        }
    }


    @SuppressLint("WrongConstant")
    public static View makeMeBlink(View view) {
        if (Ids_Class.buttonanimate) {
            ObjectAnimator AnimateGlass1;
            if (Ids_Class.animation_type.equals("zoominout")) {
                AnimateGlass1 = ObjectAnimator.ofPropertyValuesHolder(view,
                        PropertyValuesHolder.ofFloat("scaleX", 0.9f, 1f),
                        PropertyValuesHolder.ofFloat("scaleY", 0.9f, 1f));
            } else {
                AnimateGlass1 = ObjectAnimator.ofFloat(view, "alpha", 0.3f, 1f);
            }
            AnimateGlass1.setDuration(500);
            AnimateGlass1.setRepeatMode(Animation.REVERSE);
            AnimateGlass1.setRepeatCount(Animation.INFINITE);
            AnimateGlass1.start();
        }
        return view;

    }


}
