package com.ads.adsdemosp.AdsClass;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.ads.adsdemosp.Ids_Class;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

public class Ads_AppOpen {
    public static int Iquizo_adsclick = 0;
    public static void OpenAppAds_Show(Activity context) {
        Log.d("OpenAppAds12", "OpenAppAds_Show ");

        if(Ids_Class.ads_quiz_show && Ids_Class.ads_quiz_by_page_show){
            if (Iquizo_adsclick == Ids_Class.quiz_AppOpen_adsclick) {
                Iquizo_adsclick = 0;
                quiz_Ads.getquiz_AppOpen_Ads(context);
                return;
            }else{
                Iquizo_adsclick++;
            }
        }
        try {
            if (!isShowingAd) {
                if (appOpenAd != null) {
                    Log.d("OpenAppAds12", "appOpenAd 1 ");
                    FullScreenContentCallback fullScreenContentCallback =
                            new FullScreenContentCallback() {
                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    isShowingAd = false;
                                }

                                @Override
                                public void onAdFailedToShowFullScreenContent(AdError adError) {
                                    Log.d("OpenAppAds12", "onAdFailedToShowFullScreenContent = " + adError.getMessage());
                                    isShowingAd = false;

                                }

                                @Override
                                public void onAdShowedFullScreenContent() {
                                    isShowingAd = true;
                                }
                            };

                    appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);
                    appOpenAd.show(context);
                    Log.d("OpenAppAds12", "appOpenAd show ");
                } else {
                    if(Ids_Class.ads_quiz_show){
                        quiz_Ads.getquiz_AppOpen_Ads(context);
                    }
                }
            }
        } catch (NullPointerException n) {
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        OpenAppAds(context);
    }

    public static int array_pos_AppOpenId = 0;
    public static boolean fail_array_id_AppOpenlId = false;

    public static void AppOpenRandomId() {
        try {
            if (Ids_Class.admobAppopenIds_list != null && Ids_Class.admobAppopenIds_list.size() != 0 && Ids_Class.admobAppopenIds_list.size() != array_pos_AppOpenId) {
                fail_array_id_AppOpenlId = true;
                Ids_Class.admob_AppOpen_ids =  Ids_Class.admobAppopenIds_list.get(array_pos_AppOpenId);
                array_pos_AppOpenId = array_pos_AppOpenId + 1;
            } else {
                array_pos_AppOpenId = 0;
                fail_array_id_AppOpenlId = false;
            }
        } catch (NullPointerException n) {
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AppOpenAd appOpenAd;

    public static AppOpenAd.AppOpenAdLoadCallback loadCallback;
    public static boolean isShowingAd = true;

    public static void OpenAppAds(Activity context) {
        AppOpenRandomId();
        if (!fail_array_id_AppOpenlId) {

            return;
        }
        try {
            String app_open_id = Ids_Class.admob_AppOpen_ids;
            loadCallback =
                    new AppOpenAd.AppOpenAdLoadCallback() {
                        @Override
                        public void onAdLoaded(AppOpenAd ad) {
                            appOpenAd = ad;
                            Log.d("OpenAppAds12", "onAdLoaded");
                            isShowingAd = false;
                            if(Ids_Class.ad_one_by_one_ids) {
                                if (Ids_Class.admobAppopenIds_list != null && Ids_Class.admobAppopenIds_list.size() != 0 && Ids_Class.admobAppopenIds_list.size() == array_pos_AppOpenId) {
                                    array_pos_AppOpenId = 0;
                                }
                            }else{
                                array_pos_AppOpenId = 0;
                            }
                        }

                        @Override
                        public void onAdFailedToLoad(LoadAdError loadAdError) {
                            Log.d("OpenAppAds12", "onAdFailedToLoad = " + loadAdError.getMessage());
                            appOpenAd = null;
                            isShowingAd = false;
                            OpenAppAds(context);
                        }
                    };
            AdRequest request = new AdRequest.Builder().build();
            Log.d("OpenAppAds12345", "app_open_id = " + app_open_id);
            AppOpenAd.load(
                    context, app_open_id, request,
                    AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
