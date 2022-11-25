package com.ads.adsdemosp.AdsClass;

import android.app.Activity;

import com.ads.adsdemosp.Ids_Class;

public class Ads_VN_Screen {



    public static void VN_showAds(Activity context, Ads_Interstitial.OnFinishAds onFinishAd, boolean... doShowAds) {
        Ads_Interstitial.onFinishAds = onFinishAd;
        if (Ids_Class.ads_type.equals("admob") && Ids_Class.ads_App_open_InterstitialAd) {
            Ads_Interstitial.OpenAppAds_Show(context);
            return;
        }

        if (Ids_Class.ads_type.equals("admob")) {
            Ads_Interstitial.Admob_showAds_full(context);
        } else {
            Ads_Interstitial.Fb_showAds_full(context);
        }

    }



}
