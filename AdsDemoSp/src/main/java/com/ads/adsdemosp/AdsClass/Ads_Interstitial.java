package com.ads.adsdemosp.AdsClass;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.ads.adsdemosp.BuildConfig;
import com.ads.adsdemosp.Ids_Class;
import com.facebook.ads.Ad;
import com.facebook.ads.AdSettings;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;
import java.util.List;

public class Ads_Interstitial {

    public static int Iquizo_adsclick = 0;

    public static int App_Open_adsclick = 0;
    public static int App_Open_Backadsclick = 0;
    public static int adsclick = 0;
    public static int Backadsclick = 0;
    public static OnFinishAds onFinishAds;

    public interface OnFinishAds {
        void onFinishAds(boolean b);
    }

    public static void showAds_full(Activity context, OnFinishAds onFinishAd, boolean... doShowAds) {
        onFinishAds = onFinishAd;
        if (Ids_Class.ads_type.equals("admob") && Ids_Class.ads_App_open_InterstitialAd) {
            if (App_Open_adsclick == Ids_Class.AppOpen_adsclick) {
                App_Open_adsclick = 0;
                OpenAppAds_Show(context);
                return;
            }else{
                App_Open_adsclick++;
            }
        }

        if (adsclick == Ids_Class.Interstitial_adsclick) {
            adsclick = 0;
            if(Ids_Class.ads_quiz_show && Ids_Class.ads_quiz_by_page_show){
                if (Iquizo_adsclick == Ids_Class.quiz_Interstitial_adsclick) {
                    Iquizo_adsclick = 0;
                    quiz_Ads.getquiz_Full_Ads(context, onFinishAds);
                    return;
                }else{
                    Iquizo_adsclick++;
                }

            }
            if (Ids_Class.ads_type.equals("admob")) {
                Admob_showAds_full(context);
            } else {
                Fb_showAds_full(context);
            }
        } else {
            adsclick++;
            onFinishAds.onFinishAds(true);
        }
    }



    public static void BackshowAds_full(Activity context, OnFinishAds onFinishAd, boolean... doShowAds) {
        onFinishAds = onFinishAd;
        if (Ids_Class.ads_type.equals("admob") && Ids_Class.ads_App_open_InterstitialAd) {
            if (App_Open_Backadsclick == Ids_Class.AppOpen_Backadsclick) {
                App_Open_Backadsclick = 0;
                OpenAppAds_Show(context);
                return;
            }else{
                App_Open_Backadsclick++;
            }
        }

        if (Backadsclick == Ids_Class.Interstitial_Backadsclick) {
            Backadsclick = 0;
            if(Ids_Class.ads_quiz_show && Ids_Class.ads_quiz_by_page_show){
                if (Iquizo_adsclick == Ids_Class.quiz_Interstitial_backadsclick) {
                    Iquizo_adsclick = 0;
                    quiz_Ads.getquiz_Full_Ads(context, onFinishAds);
                    return;
                }else{
                    Iquizo_adsclick++;
                }
            }
            if (Ids_Class.ads_type.equals("admob")) {
                Admob_showAds_full(context);
            } else {
                Fb_showAds_full(context);
            }
        } else {
            Backadsclick++;
            onFinishAds.onFinishAds(true);

        }
    }

    public static void OnlyshowAds_full(Activity context, OnFinishAds onFinishAd, boolean... doShowAds) {
        onFinishAds = onFinishAd;
        if (Ids_Class.ads_type.equals("admob")) {
            Admob_showAds_full(context);
        } else {
            Fb_showAds_full(context);
        }
    }

    public static void Admob_showAds_full(Activity context) {

       if (Admob_mInterstitialAd != null) {
            Admob_mInterstitialAd.show(context);
        } else if (fb_interstitialAd != null && fb_interstitialAd.isAdLoaded()) {
            fb_interstitialAd.show();
        } else {
            if(Ids_Class.ads_quiz_show){
                quiz_Ads.getquiz_Full_Ads(context,onFinishAds);
            }else{
                onFinishAds.onFinishAds(true);
            }

        }
        Admob_InterstitialAd_Load(context);

    }

    public static void Fb_showAds_full(Activity context) {

        if (fb_interstitialAd != null && fb_interstitialAd.isAdLoaded()) {
            fb_interstitialAd.show();
        } else if (Admob_mInterstitialAd != null) {
            Admob_mInterstitialAd.show(context);
        } else {
            if(Ids_Class.ads_quiz_show){
                quiz_Ads.getquiz_Full_Ads(context,onFinishAds);
            }else{
                onFinishAds.onFinishAds(true);
            }
        }
        Fb_InterstitialAd_Load(context);
    }


    public static void OpenAppAds_Show(Activity context) {
        Log.d("OpenAppAds12", "OpenAppAds_Show ");
        try {
            if (!Ads_AppOpen.isShowingAd) {
                if (Ads_AppOpen.appOpenAd != null) {
                    Log.d("OpenAppAds12", "appOpenAd 1 ");
                    FullScreenContentCallback fullScreenContentCallback =
                            new FullScreenContentCallback() {
                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    Ads_AppOpen.isShowingAd = false;
                                    Show_After_AppOpen_ads(context);
                                }

                                @Override
                                public void onAdFailedToShowFullScreenContent(AdError adError) {
                                    Log.d("OpenAppAds12", "onAdFailedToShowFullScreenContent = " + adError.getMessage());
                                    Show_After_AppOpen_ads(context);
                                }

                                @Override
                                public void onAdShowedFullScreenContent() {
                                    Ads_AppOpen.isShowingAd = true;
                                }
                            };

                    Ads_AppOpen.appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);
                    Ads_AppOpen.appOpenAd.show(context);
                    Log.d("OpenAppAds12", "appOpenAd show ");
                } else {
                    Show_After_AppOpen_ads(context);
                }
            }else{
                Show_After_AppOpen_ads(context);
            }
        } catch (NullPointerException n) {
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Ads_AppOpen.OpenAppAds(context);
    }

    public static void Show_After_AppOpen_ads(Context context){
        if (Admob_mInterstitialAd != null) {
            Admob_mInterstitialAd.show((Activity) context);
        } else if (fb_interstitialAd != null && fb_interstitialAd.isAdLoaded()) {
            fb_interstitialAd.show();
        } else {
            if(Ids_Class.ads_quiz_show){
                quiz_Ads.getquiz_Full_Ads(context,onFinishAds);
            }else{
                onFinishAds.onFinishAds(true);
            }
        }
        Admob_InterstitialAd_Load(context);
    }

    public static int array_pos_InterstitialId = 0;
    public static boolean fail_array_id_InterstitialId = false;

    public static void InterstitialIdsRandomId() {
        try {
            if (Ids_Class.admobInterstitialIds_list != null && Ids_Class.admobInterstitialIds_list.size() != 0 && Ids_Class.admobInterstitialIds_list.size() != array_pos_InterstitialId) {
                fail_array_id_InterstitialId = true;
                Ids_Class.admob_Interstitial_ids = Ids_Class.admobInterstitialIds_list.get(array_pos_InterstitialId);
                array_pos_InterstitialId = array_pos_InterstitialId + 1;
            } else {
                array_pos_InterstitialId = 0;
                fail_array_id_InterstitialId = false;
            }
        } catch (NullPointerException n) {
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static com.google.android.gms.ads.interstitial.InterstitialAd Admob_mInterstitialAd;

    public static void Admob_InterstitialAd_Load(Context context) {
        InterstitialIdsRandomId();

        if (!Ads_Interstitial.fail_array_id_InterstitialId) {
            if (Ids_Class.ads_type.equals("admob")) {
                Fb_InterstitialAd_Load(context);
            }
            return;
        }

        String Interstitialad_ids = Ids_Class.admob_Interstitial_ids;
        AdRequest adRequest = new AdRequest.Builder().build();
        Admob_mInterstitialAd = null;
       // Admob_mInterstitialAd = new com.google.android.gms.ads.interstitial.InterstitialAd(context);
       // Admob_mInterstitialAd.setAdUnitId(Interstitialad_ids);
        //Admob_mInterstitialAd.loadAd(adRequest);
        //Log.d("Admob_InterstitialA12","Interstitialad_ids = " +Interstitialad_ids);


        InterstitialAd.load(context,Interstitialad_ids, adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        if(Ids_Class.ad_one_by_one_ids) {
                            if (Ids_Class.admobInterstitialIds_list != null && Ids_Class.admobInterstitialIds_list.size() != 0 && Ids_Class.admobInterstitialIds_list.size() == array_pos_InterstitialId) {
                                array_pos_InterstitialId = 0;
                            }
                        }else{
                            array_pos_InterstitialId = 0;
                        }
                        Log.d("Admob_InterstitialA12","array_pos_InterstitialId = " +array_pos_InterstitialId);
                        Admob_mInterstitialAd = interstitialAd;

                        onAdsLoadAdListener();
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Admob_mInterstitialAd = null;
                        Admob_InterstitialAd_Load(context);
                    }
                });



       /* Admob_mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                array_pos_InterstitialId = 0;
                //Log.d("Interstitialad12", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Admob_InterstitialAd_Load(context);
            }

            @Override
            public void onAdOpened() {
            }

            @Override
            public void onAdClicked() {
            }

            @Override
            public void onAdLeftApplication() {
            }

            @Override
            public void onAdClosed() {
                onFinishAds.onFinishAds(true);
            }
        });*/

    }

    private static void onAdsLoadAdListener() {
        Admob_mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
            @Override
            public void onAdClicked() {
                // Called when a click is recorded for an ad.

            }

            @Override
            public void onAdDismissedFullScreenContent() {
                onFinishAds.onFinishAds(true);
            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
                onFinishAds.onFinishAds(true);
            }

            @Override
            public void onAdImpression() {
                // Called when an impression is recorded for an ad.
            }

            @Override
            public void onAdShowedFullScreenContent() {
                // Called when ad is shown.
            }
        });
    }

    public static com.facebook.ads.InterstitialAd fb_interstitialAd;

    public static void Fb_InterstitialAd_Load(Context context) {
        if (BuildConfig.DEBUG) {
            AdSettings.setTestMode(true);
        }
        if (fb_interstitialAd != null){
            fb_interstitialAd = null;
        }
        String fb_interstitialAd_id = Ids_Class.fb_Interstitial_ids;
        fb_interstitialAd = new com.facebook.ads.InterstitialAd(context, fb_interstitialAd_id);


        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                onFinishAds.onFinishAds(true);
            }


            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {
                Log.d("Interstitialad12", "fb Interstitial ad is onError = " + adError.getErrorMessage());
                if (fb_interstitialAd != null){
                    fb_interstitialAd = null;
                }
                if (Ids_Class.ads_type.equals("facebook")) {
                    Admob_InterstitialAd_Load(context);
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {


            }

            @Override
            public void onAdClicked(Ad ad) {
            }

            @Override
            public void onLoggingImpression(Ad ad) {
            }
        };
        fb_interstitialAd.loadAd(
                fb_interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());

    }
}
