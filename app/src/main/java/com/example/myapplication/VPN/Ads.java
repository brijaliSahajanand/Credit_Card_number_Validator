package com.example.myapplication.VPN;

import android.app.Activity;
import android.content.Context;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.ads.adsdemosp.AdsClass.Ads_VN_Screen;


public class Ads {


    public static OnFinishAds onFinishAds;


    public static void showAds(Context context, OnFinishAds onFinishAd, boolean... doShowAds) {

        onFinishAds = onFinishAd;

        Ads_Interstitial.showAds_full((Activity) context, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                onFinishAds.onFinishAds(true);
            }
        });


    }

    public static void showAds_back(Context context, OnFinishAds onFinishAd, boolean... doShowAds) {
        onFinishAds = onFinishAd;
        Ads_Interstitial.BackshowAds_full((Activity) context, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                onFinishAds.onFinishAds(true);
            }
        });

    }

    public static void showAds_new(Context context, OnFinishAds onFinishAd, boolean... doShowAds) {

        onFinishAds = onFinishAd;

        Ads_VN_Screen.VN_showAds((Activity) context, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                onFinishAds.onFinishAds(true);
            }
        });


    }


    public interface OnFinishAds {
        void onFinishAds(boolean b);
    }
}
