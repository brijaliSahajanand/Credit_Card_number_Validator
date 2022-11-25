package com.ads.adsdemosp.AdsClass;

import android.app.Activity;
import android.util.Log;

import com.ads.adsdemosp.Ids_Class;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

public class Ads_SplashAppOpen {

    public static OnFinishAds onFinishAds;

    public interface OnFinishAds {
        void onFinishAds(boolean b);
    }

    public static void OpenAppAds_Show(Activity context ) {

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
                                    onFinishAds.onFinishAds(true);
                                }

                                @Override
                                public void onAdFailedToShowFullScreenContent(AdError adError) {
                                    Log.d("OpenAppAds12", "onAdFailedToShowFullScreenContent = " + adError.getMessage());
                                    Ads_AppOpen.isShowingAd = false;
                                    onFinishAds.onFinishAds(true);

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
                    if(Ids_Class.ads_quiz_show){
                        quiz_Ads.getquiz_AppOpen_SplashAds(context,onFinishAds);
                    }else{
                        onFinishAds.onFinishAds(true);
                    }
                }
            }else{
                onFinishAds.onFinishAds(true);
            }
        } catch (NullPointerException n) {
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Ads_AppOpen.OpenAppAds(context);
    }



    public static void Splash_OpenAppAds_Load(Activity context,OnFinishAds onFinishAd, boolean... doShowAds) {
        onFinishAds = onFinishAd;
        Ads_AppOpen.AppOpenRandomId();
        if (!Ads_AppOpen.fail_array_id_AppOpenlId) {
            onFinishAds.onFinishAds(true);
            return;
        }
        try {
            String app_open_id = Ids_Class.admob_AppOpen_ids;
            Ads_AppOpen.loadCallback =
                    new AppOpenAd.AppOpenAdLoadCallback() {
                        @Override
                        public void onAdLoaded(AppOpenAd ad) {
                            Ads_AppOpen.appOpenAd = ad;
                            Log.d("OpenAppAds12", "onAdLoaded");
                            Ads_AppOpen.isShowingAd = false;
                            if(Ids_Class.ad_one_by_one_ids) {
                                if (Ids_Class.admobAppopenIds_list != null && Ids_Class.admobAppopenIds_list.size() != 0 && Ids_Class.admobAppopenIds_list.size() == Ads_AppOpen.array_pos_AppOpenId) {
                                    Ads_AppOpen.array_pos_AppOpenId = 0;
                                }
                            }else{
                                Ads_AppOpen.array_pos_AppOpenId = 0;
                            }
                            OpenAppAds_Show(context);
                        }

                        @Override
                        public void onAdFailedToLoad(LoadAdError loadAdError) {
                            Log.d("OpenAppAds12", "onAdFailedToLoad = " + loadAdError.getMessage());
                            Ads_AppOpen.appOpenAd = null;
                            Ads_AppOpen.isShowingAd = false;
                            Splash_OpenAppAds_Load(context,onFinishAd);
                        }
                    };
            AdRequest request = new AdRequest.Builder().build();
            Log.d("OpenAppAds12345", "app_open_id = " + app_open_id);
            AppOpenAd.load(
                    context, app_open_id, request,
                    AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, Ads_AppOpen.loadCallback);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
