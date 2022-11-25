package com.ads.adsdemosp.AdsClass;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;

import com.ads.adsdemosp.BuildConfig;
import com.ads.adsdemosp.Ids_Class;
import com.ads.adsdemosp.R;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AdSettings;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MediaContent;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Ads_Native {
    public static int Iquizo_adsclick = 0;

//    public static HashMap<Integer, NativeAdView> admob_nativehashmap = new HashMap<>();


    public static int adsScreen = 0;

    public static void NativeFull_Show(final Context context, final LinearLayout lnr_view, final LinearLayout lnr_load) {
        lnr_load.setVisibility(View.GONE);
        if (adsScreen == Ids_Class.Native_adsscreen) {
            adsScreen = 0;

            if (Ids_Class.ads_quiz_show && Ids_Class.ads_quiz_by_page_show && Ids_Class.is_big_native_quiz) {
                if (Iquizo_adsclick == Ids_Class.quiz_Native_adsclick) {
                    Iquizo_adsclick = 0;
                    lnr_load.setVisibility(View.GONE);
                    lnr_view.setVisibility(View.VISIBLE);
                    quiz_Ads.getquiz_Native_Ads(context, lnr_view);
                    return;
                } else {
                    Iquizo_adsclick++;
                }
            }

            if (Ids_Class.ads_type.equals("admob")) {
                Admob_NativeFull_Show(context, lnr_view, lnr_load);
            } else {
                Fb_NativeFull_Show(context, lnr_view, lnr_load);
            }
        } else {
            adsScreen++;
            lnr_view.setVisibility(View.GONE);
            lnr_load.setVisibility(View.GONE);
        }
    }

    public static void Admob_NativeFull_Show(final Context context, final LinearLayout lnr_view, final LinearLayout lnr_load) {
        if (admobnativeAd != null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            NativeAdView adView = (NativeAdView) inflater.inflate(R.layout.ads_admob_native_full, null);
            lnr_load.setVisibility(View.GONE);
            lnr_view.setVisibility(View.VISIBLE);
            populateUnifiedNativeAdView_full(admobnativeAd, adView);
            Log.e("NativeFull_Show", "admob Native ad show : ");
            lnr_view.removeAllViews();
            lnr_view.addView(adView);
            Ads_Adapter_List.admob_nativehashmap.put(Ads_Adapter_List.pos, adView);
            //  Ads_Native.admob_nativehashmap.put(pos, lnr_view);
        } else if (fb_native_adpater != null && fb_native_adpater.isAdLoaded()) {
            lnr_load.setVisibility(View.GONE);
            lnr_view.setVisibility(View.VISIBLE);
            lnr_view.removeAllViews();
            LayoutInflater inflater = LayoutInflater.from(context);
            LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ads_fb_native_full, nativeAdLayout, false);
            lnr_view.addView(adView);
            fb_inflateAd_full(context, fb_native_adpater, lnr_view, lnr_load, adView);
            Ads_Adapter_List.admob_nativehashmap.put(Ads_Adapter_List.pos, adView);
            Log.d("NativeFull_Show", "Fb_NativeFull_Show ");
        } else {
            if (Ids_Class.ads_quiz_show && Ids_Class.is_big_native_quiz) {
                lnr_load.setVisibility(View.GONE);
                lnr_view.setVisibility(View.VISIBLE);
                quiz_Ads.getquiz_Native_Ads(context, lnr_view);
            } else {
                lnr_load.setVisibility(View.GONE);
                lnr_view.setVisibility(View.GONE);
            }
        }
        AdmobNativeFull_Load(context);
    }

    public static void Fb_NativeFull_Show(final Context context, final LinearLayout lnr_view, final LinearLayout lnr_load) {
        if (fb_native_adpater != null && fb_native_adpater.isAdLoaded()) {
            lnr_load.setVisibility(View.GONE);
            lnr_view.setVisibility(View.VISIBLE);
            lnr_view.removeAllViews();
            LayoutInflater inflater = LayoutInflater.from(context);
            LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ads_fb_native_full, nativeAdLayout, false);
            lnr_view.addView(adView);
            fb_inflateAd_full(context, fb_native_adpater, lnr_view, lnr_load, adView);
            Ads_Adapter_List.admob_nativehashmap.put(Ads_Adapter_List.pos, adView);
            Log.d("NativeFull_Show", "Fb_NativeFull_Show ");
        } else if (admobnativeAd != null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            NativeAdView adView = (NativeAdView) inflater.inflate(R.layout.ads_admob_native_full, null);
            lnr_load.setVisibility(View.GONE);
            lnr_view.setVisibility(View.VISIBLE);
            populateUnifiedNativeAdView_full(admobnativeAd, adView);
            Log.e("NativeFull_Show", "admob Native ad show : ");
            lnr_view.removeAllViews();
            lnr_view.addView(adView);
            Ads_Adapter_List.admob_nativehashmap.put(Ads_Adapter_List.pos, adView);
        } else {
            if (Ids_Class.ads_quiz_show && Ids_Class.is_big_native_quiz) {
                lnr_load.setVisibility(View.GONE);
                lnr_view.setVisibility(View.VISIBLE);
                quiz_Ads.getquiz_Native_Ads(context, lnr_view);
            } else {
                lnr_load.setVisibility(View.GONE);
                lnr_view.setVisibility(View.GONE);
            }
        }

        FB_NativeAd_Load(context);
    }


    public static int array_pos_NativeFullId = 0;
    public static boolean fail_array_id_NativeFullId = false;

    public static void NativeFullIdsRandomId() {
        try {
            if (Ids_Class.admobNativeFullIds_list != null && Ids_Class.admobNativeFullIds_list.size() != 0 && Ids_Class.admobNativeFullIds_list.size() != array_pos_NativeFullId) {
                fail_array_id_NativeFullId = true;
                Ids_Class.admob_NativeFull_ids = Ids_Class.admobNativeFullIds_list.get(array_pos_NativeFullId);
                array_pos_NativeFullId = array_pos_NativeFullId + 1;
            } else {
                array_pos_NativeFullId = 0;
                fail_array_id_NativeFullId = false;
            }
        } catch (NullPointerException n) {
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String adviewnative = "";
    public static NativeAd admobnativeAd;

    public static void AdmobNativeFull_Load(final Context context) {
        NativeFullIdsRandomId();
        if (!fail_array_id_NativeFullId) {
            if (Ids_Class.ads_type.equals("admob")) {
                FB_NativeAd_Load(context);
            }
            return;
        }
        adviewnative = Ids_Class.admob_NativeFull_ids;
        //Log.d("AdmobNativeFull_Load12","adviewnative = " +adviewnative);
        AdLoader.Builder builder = new AdLoader.Builder(context, adviewnative)
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        if (admobnativeAd != null) {
                            admobnativeAd = null;
                        }
                        admobnativeAd = nativeAd;
                        if(Ids_Class.ad_one_by_one_ids) {
                            if (Ids_Class.admobNativeFullIds_list != null && Ids_Class.admobNativeFullIds_list.size() != 0 && Ids_Class.admobNativeFullIds_list.size() == array_pos_NativeFullId) {
                                array_pos_NativeFullId = 0;
                            }
                        }else{
                            array_pos_NativeFullId = 0;
                        }




                    }
                });

        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Handle the failure by logging, altering the UI, and so on.
                Log.e("NativeFull_Show", "admob Native ad failed to load: " + adError.getCode());
                Log.e("ArrayListId", "admob Native ad failed to load: " + adError.getMessage());
                if (admobnativeAd != null) {
                    admobnativeAd = null;
                }

                AdmobNativeFull_Load(context);


            }

            @Override
            public void onAdClicked() {
                // Log the click event or other custom behavior.
            }
        })
                .build();

        Log.e("NativeFull_Show", "admob Native ad to loading: ");
        adLoader.loadAd(new AdRequest.Builder().build());
    }

    public static void populateUnifiedNativeAdView_full(NativeAd nativeAd, NativeAdView adView) {

        com.google.android.gms.ads.nativead.MediaView mediaView = adView.findViewById(R.id.ad_media);
        NativeAdView admob_main = (NativeAdView) adView.findViewById(R.id.admob_main);
        TextView txt_top_ads = (TextView) adView.findViewById(R.id.txt_top_ads);
        adView.setMediaView(mediaView);
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());


        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((TextView) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }
        try {
            admob_main.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.ads_native_bg_color)));
            ((TextView) adView.getCallToActionView()).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.ads_native_btn_color)));
            ((TextView) adView.getCallToActionView()).setTextColor(Color.parseColor(Ids_Class.ads_native_text_color));

            ((TextView) adView.getBodyView()).setTextColor(Color.parseColor(Ids_Class.ads_native_in_text_color));
            ((TextView) adView.getHeadlineView()).setTextColor(Color.parseColor(Ids_Class.ads_native_in_text_color));
            txt_top_ads.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.ads_native_btn_color)));
            txt_top_ads.setTextColor(Color.parseColor(Ids_Class.ads_native_text_color));

            ((TextView) adView.getPriceView()).setTextColor(Color.parseColor(Ids_Class.ads_native_in_text_color));
            ((TextView) adView.getPriceView()).setAlpha(0.5f);
            ((TextView) adView.getStoreView()).setTextColor(Color.parseColor(Ids_Class.ads_native_in_text_color));
            ((TextView) adView.getStoreView()).setAlpha(0.5f);
            ((TextView) adView.getAdvertiserView()).setTextColor(Color.parseColor(Ids_Class.ads_native_in_text_color));
            ((TextView) adView.getAdvertiserView()).setAlpha(0.5f);

            Ids_Class.makeMeBlink(adView.getCallToActionView());
        } catch (NullPointerException n) {
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.GONE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.GONE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }


        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.GONE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        adView.setNativeAd(nativeAd);
        MediaContent vc = nativeAd.getMediaContent();

        if (vc.hasVideoContent()) {
            nativeAd.getMediaContent().getVideoController().setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                @Override
                public void onVideoEnd() {
                    super.onVideoEnd();
                }
            });
        } else {
            mediaView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }


    public static String fb_native_id12 = "";
    public static com.facebook.ads.NativeAd fb_native_adpater;
    public static NativeAdListener nativeAdListener;

    public static void FB_NativeAd_Load(final Context context) {

        if (BuildConfig.DEBUG) {
            AdSettings.setTestMode(true);
        }
        fb_native_id12 = Ids_Class.fb_NativeFull_ids;
        fb_native_adpater = new com.facebook.ads.NativeAd(context, fb_native_id12);

        nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                Log.d("NativeFull_Show", "fb error = " + adError.getErrorMessage());
                if (Ids_Class.ads_type.equals("facebook")) {
                    AdmobNativeFull_Load(context);
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Race condition, load() called again before last ad was displayed
                Log.d("NativeFull_Show", "fb load ad= ");
                if (fb_native_adpater != ad) {
                    return;
                }


            }

            @Override
            public void onAdClicked(Ad ad) {
            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };
        // Request an ad

        if (!fb_native_adpater.isAdLoaded()) {
            Log.d("NativeFull_Show", "fb  Loadding..");
            fb_native_adpater.loadAd(
                    fb_native_adpater.buildLoadAdConfig()
                            .withAdListener(nativeAdListener)
                            .build());
        }
    }

    public static NativeAdLayout nativeAdLayout;

    public static void fb_inflateAd_full(Context context, com.facebook.ads.NativeAd nativeAd, LinearLayout adViewContainer, LinearLayout load, LinearLayout adView) {
        load.setVisibility(View.GONE);
        adViewContainer.setVisibility(View.VISIBLE);
        nativeAd.unregisterView();
        // Add the Ad view into the ad container.


        // Add the AdChoices icon

      /*  AdChoicesView adChoicesView = new AdChoicesView(context, nativeAd, true);
        adView.addView(adChoicesView, 0);*/
        LinearLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(context, nativeAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.

       // CardView card_btn = adView.findViewById(R.id.card_btn);
        LinearLayout ad_unit = adView.findViewById(R.id.ad_unit);
        MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        TextView nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);


        try {
            ad_unit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.fb_ads_native_bg_color)));
            nativeAdCallToAction.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.fb_ads_native_btn_color)));
            nativeAdCallToAction.setTextColor(Color.parseColor(Ids_Class.fb_ads_native_text_color));

            nativeAdBody.setTextColor(Color.parseColor(Ids_Class.fb_ads_native_in_txt_color));
            nativeAdSocialContext.setTextColor(Color.parseColor(Ids_Class.fb_ads_native_in_txt_color));
            nativeAdTitle.setTextColor(Color.parseColor(Ids_Class.fb_ads_native_in_txt_color));


          //  Ids_Class.makeMeBlink(nativeAdCallToAction);
        } catch (NullPointerException n) {
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set the Text.
//        nativeAdMedia.setVisibility(View.GONE);
        nativeAdTitle.setText(nativeAd.getAdvertiserName());
        nativeAdBody.setText(nativeAd.getAdBodyText());
        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

        // Create a list of clickable views
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        // Register the Title and CTA button to listen for clicks.
        nativeAd.registerViewForInteraction(
                adView,
                nativeAdMedia,
                nativeAdIcon,
                clickableViews);

    }


}
