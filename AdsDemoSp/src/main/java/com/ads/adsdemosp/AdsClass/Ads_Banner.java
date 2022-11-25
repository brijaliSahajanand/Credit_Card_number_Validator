package com.ads.adsdemosp.AdsClass;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import com.facebook.ads.NativeBannerAd;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MediaContent;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;

import java.util.ArrayList;
import java.util.List;

public class Ads_Banner {

    public static int Iquizo_adsclick = 0;
    public static void Native_Custom_Show(Context context, LinearLayout lnr_view, LinearLayout lnr_load) {
        lnr_load.setVisibility(View.GONE);
        if(Ids_Class.ads_quiz_show && Ids_Class.ads_quiz_by_page_show && Ids_Class.is_small_native_quiz){
            if (Iquizo_adsclick == Ids_Class.quiz_Banner_adsclick) {
                Iquizo_adsclick = 0;
                lnr_load.setVisibility(View.GONE);
                lnr_view.setVisibility(View.VISIBLE);
                quiz_Ads.getquiz_banner_Ads(context,lnr_view);
                return;
            }else{
                Iquizo_adsclick++;
            }
        }
        if (Ids_Class.ads_type.equals("admob")) {
            if (Ids_Class.ads_native_type.equals("banner")) {
                Admob_banner_show(context, lnr_view, lnr_load);
            } else {
                Admob_Native_Custom_Show(context, lnr_view, lnr_load);
            }
        } else {
            Fb_Native_Custom_Show(context, lnr_view, lnr_load);
        }
    }

    public static void Admob_Native_Custom_Show(Context context, LinearLayout lnr_view, LinearLayout lnr_load) {
        if (admobnativeAd != null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            NativeAdView adView = (NativeAdView) inflater.inflate(R.layout.admob_nativecustom_banner, null);
            lnr_load.setVisibility(View.GONE);
            lnr_view.setVisibility(View.VISIBLE);
            populateUnifiedNativeAdView_full(admobnativeAd, adView);
            Log.e("NativeFull_Show", "admob Native ad show : ");
            lnr_view.removeAllViews();
            lnr_view.addView(adView);
            Ads_Adapter_List.admob_nativehashmap.put(Ads_Adapter_List.pos, adView);
        } else if (mNativeBannerAd != null && mNativeBannerAd.isAdLoaded()) {
            lnr_view.setVisibility(View.VISIBLE);
            lnr_load.setVisibility(View.GONE);
            lnr_view.removeAllViews();
            inflateAd(mNativeBannerAd, context, lnr_view);

        } else {
            if(Ids_Class.ads_quiz_show && Ids_Class.is_small_native_quiz){
                lnr_load.setVisibility(View.GONE);
                lnr_view.setVisibility(View.VISIBLE);
                quiz_Ads.getquiz_banner_Ads(context,lnr_view);
            }else {
                lnr_view.setVisibility(View.GONE);
                lnr_load.setVisibility(View.GONE);
            }
        }
        AdmobNativeCustom_Load(context);
    }


    public static void Fb_Native_Custom_Show(Context context, LinearLayout lnr_view, LinearLayout lnr_load) {
        if (mNativeBannerAd != null && mNativeBannerAd.isAdLoaded()) {
            lnr_view.setVisibility(View.VISIBLE);
            lnr_load.setVisibility(View.GONE);
            lnr_view.removeAllViews();
            inflateAd(mNativeBannerAd, context, lnr_view);
        } else if (Ids_Class.ads_native_type.equals("banner")) {
            Admob_banner_show(context, lnr_view, lnr_load);
        } else if (admobnativeAd != null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            NativeAdView adView = (NativeAdView) inflater.inflate(R.layout.admob_nativecustom_banner, null);
            lnr_load.setVisibility(View.GONE);
            lnr_view.setVisibility(View.VISIBLE);
            populateUnifiedNativeAdView_full(admobnativeAd, adView);
            Log.e("NativeFull_Show", "admob Native ad show : ");
            lnr_view.removeAllViews();
            lnr_view.addView(adView);
            Ads_Adapter_List.admob_nativehashmap.put(Ads_Adapter_List.pos, adView);

        } else {
            if(Ids_Class.ads_quiz_show && Ids_Class.is_small_native_quiz){
                lnr_load.setVisibility(View.GONE);
                lnr_view.setVisibility(View.VISIBLE);
                quiz_Ads.getquiz_banner_Ads(context,lnr_view);
            }else {
                lnr_view.setVisibility(View.GONE);
                lnr_load.setVisibility(View.GONE);
            }
        }
        Fb_NativeBanner_Load(context);
    }

    public static int array_pos_BannerId = 0;
    public static boolean fail_array_id_BannerId = false;

    public static void BannerIdsRandomId() {
        try {
            if (Ids_Class.admobBannerIds_list != null && Ids_Class.admobBannerIds_list.size() != 0 && Ids_Class.admobBannerIds_list.size() != array_pos_BannerId) {
                fail_array_id_BannerId = true;
                Ids_Class.admob_Banner_ids = Ids_Class.admobBannerIds_list.get(array_pos_BannerId);
                array_pos_BannerId = array_pos_BannerId + 1;
            } else {
                array_pos_BannerId = 0;
                fail_array_id_BannerId = false;
            }
        } catch (NullPointerException n) {
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AdView admob_adView;

    public static void Admob_banner_show(Context context, LinearLayout lnr_adview, LinearLayout ll_label) {
        ll_label.setVisibility(View.GONE);
        BannerIdsRandomId();
        if (!fail_array_id_BannerId) {
            if (Ids_Class.ads_type.equals("admob")) {
                Fb_NativeBanner_Load_For_Banner(context, lnr_adview, ll_label);
            } else {
                if(Ids_Class.ads_quiz_show && Ids_Class.is_small_native_quiz){
                    ll_label.setVisibility(View.GONE);
                    lnr_adview.setVisibility(View.VISIBLE);
                    quiz_Ads.getquiz_banner_Ads(context,lnr_adview);
                }else {
                    lnr_adview.setVisibility(View.GONE);
                    ll_label.setVisibility(View.GONE);
                }

            }

            return;
        }
        lnr_adview.removeAllViews();
        String banner_ads_id = Ids_Class.admob_Banner_ids;
        //Log.d("Admob_banner_show12","banner_ads_id = " +banner_ads_id);
        admob_adView = new AdView(context);
        admob_adView.setAdUnitId(banner_ads_id);
        lnr_adview.addView(admob_adView);
        com.google.android.gms.ads.AdSize adSize = getAdSize((Activity) context);
        admob_adView.setAdSize(adSize);
        AdRequest adRequest = new AdRequest.Builder().build();
        admob_adView.loadAd(adRequest);

        admob_adView.setAdListener(new com.google.android.gms.ads.AdListener() {

            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.

                if(Ids_Class.ad_one_by_one_ids) {
                    if (Ids_Class.admobBannerIds_list != null && Ids_Class.admobBannerIds_list.size() != 0 && Ids_Class.admobBannerIds_list.size() == array_pos_BannerId) {
                        array_pos_BannerId = 0;
                    }
                }else{
                    array_pos_BannerId = 0;
                }
                lnr_adview.setVisibility(View.VISIBLE);
                ll_label.setVisibility(View.GONE);
                if (Ads_Adapter_List.admob_nativehashmap.get(Ads_Adapter_List.pos) == null) {
                    Ads_Adapter_List.admob_nativehashmap.put(Ads_Adapter_List.pos, admob_adView);
                }
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
                Log.d("native_banner", "onAdFailedToLoad = " + adError.getCode());
                Admob_banner_show(context, lnr_adview, ll_label);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

    }

    public static com.google.android.gms.ads.AdSize getAdSize(Activity context) {
        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
        Display display = context.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);

        // Step 3 - Get adaptive ad size and return for setting on the ad view.
        return com.google.android.gms.ads.AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth);
    }


    public static int array_pos_NativeCustomId = 0;
    public static boolean fail_array_id_NativeCustomId = false;

    public static void NativeCustomIdsRandomId() {
        try {
            if (Ids_Class.admobNativeCustomIds_list != null && Ids_Class.admobNativeCustomIds_list.size() != 0 && Ids_Class.admobNativeCustomIds_list.size() != array_pos_NativeCustomId) {
                fail_array_id_NativeCustomId = true;
                Ids_Class.admob_NativeCustom_ids = Ids_Class.admobNativeCustomIds_list.get(array_pos_NativeCustomId);
                array_pos_NativeCustomId = array_pos_NativeCustomId + 1;
            } else {
                array_pos_NativeCustomId = 0;
                fail_array_id_NativeCustomId = false;
            }
        } catch (NullPointerException n) {
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String adviewnative = "";
    public static NativeAd admobnativeAd;

    public static void AdmobNativeCustom_Load(final Context context) {
        NativeCustomIdsRandomId();

        if (!fail_array_id_NativeCustomId) {
            if (Ids_Class.ads_type.equals("admob")) {
                Fb_NativeBanner_Load(context);
            }
            return;
        }

        adviewnative = Ids_Class.admob_NativeCustom_ids;
      //  Log.d("AdmobNativeCustom12","adviewnative = " +adviewnative);
        AdLoader.Builder builder = new AdLoader.Builder(context, adviewnative)
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        if (admobnativeAd != null) {
                            admobnativeAd = null;
                        }
                        admobnativeAd = nativeAd;

                        if(Ids_Class.ad_one_by_one_ids) {
                            if (Ids_Class.admobNativeCustomIds_list != null && Ids_Class.admobNativeCustomIds_list.size() != 0 && Ids_Class.admobNativeCustomIds_list.size() == array_pos_NativeCustomId) {
                                array_pos_NativeCustomId = 0;
                            }
                        }else{
                            array_pos_NativeCustomId = 0;
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

                AdmobNativeCustom_Load(context);

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
        NativeAdView admob_main = (NativeAdView) adView.findViewById(R.id.admob_main);
        com.google.android.gms.ads.nativead.MediaView mediaView = adView.findViewById(R.id.ad_media);

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

        //VideoController vc = nativeAd.getVideoController();
        MediaContent vc = nativeAd.getMediaContent();

        // Updates the UI to say whether or not this ad has a video asset.
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

    private static NativeBannerAd mNativeBannerAd;

    public static void Fb_NativeBanner_Load_For_Banner(Context context, LinearLayout lnr_adview, LinearLayout ll_label) {
        if (BuildConfig.DEBUG) {
            AdSettings.setTestMode(true);
        }
        String fb_nativebanner_id = Ids_Class.fb_NativeBanner_ids;
        mNativeBannerAd = new NativeBannerAd(context, fb_nativebanner_id);

        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, AdError adError) {
                Log.d("Facebook_Native_banner", "onError: " + adError.getErrorMessage());
                if(Ids_Class.ads_quiz_show && Ids_Class.is_small_native_quiz){
                    ll_label.setVisibility(View.GONE);
                    lnr_adview.setVisibility(View.VISIBLE);
                    quiz_Ads.getquiz_banner_Ads(context,lnr_adview);
                }else {
                    lnr_adview.setVisibility(View.GONE);
                    ll_label.setVisibility(View.GONE);
                }

            }

            @Override
            public void onAdLoaded(Ad ad) {
                Log.d("Facebook_Native_banner", "onAdLoaded: ");
                if (mNativeBannerAd != null && mNativeBannerAd.isAdLoaded()) {
                    lnr_adview.setVisibility(View.VISIBLE);
                    ll_label.setVisibility(View.GONE);
                    lnr_adview.removeAllViews();
                    inflateAd(mNativeBannerAd, context, lnr_adview);

                } else {
                    lnr_adview.setVisibility(View.GONE);
                    ll_label.setVisibility(View.GONE);
                }

            }

            @Override
            public void onAdClicked(Ad ad) {
            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };
        // Initiate a request to load an ad.
        mNativeBannerAd.loadAd(
                mNativeBannerAd.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .build());
    }


    public static void Fb_NativeBanner_Load(Context context) {
        if (BuildConfig.DEBUG) {
            AdSettings.setTestMode(true);
        }
        String fb_nativebanner_id = Ids_Class.fb_NativeBanner_ids;
        mNativeBannerAd = new NativeBannerAd(context, fb_nativebanner_id);

        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, AdError adError) {
                Log.d("Facebook_Native_banner", "onError: " + adError.getErrorMessage());
                if (Ids_Class.ads_type.equals("facebook")) {
                    if (Ids_Class.ads_native_type.equals("nativebanner")) {
                        AdmobNativeCustom_Load(context);
                    }
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {
                Log.d("Facebook_Native_banner", "onAdLoaded: ");

            }

            @Override
            public void onAdClicked(Ad ad) {
            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };
        // Initiate a request to load an ad.
        mNativeBannerAd.loadAd(
                mNativeBannerAd.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .build());
    }

    public static LinearLayout fb_adView;
    public static NativeAdLayout nativeAdLayout;

    public static void inflateAd(NativeBannerAd nativeBannerAd, Context context, LinearLayout linearLayout) {
        nativeBannerAd.unregisterView();

        LayoutInflater inflater = LayoutInflater.from(context);
        fb_adView = (LinearLayout) inflater.inflate(R.layout.ads_fb_native_banner_custom, nativeAdLayout, false);
        // Add the AdChoices icon
        LinearLayout ad_unit = fb_adView.findViewById(R.id.ad_unit);

        RelativeLayout adChoicesContainer = fb_adView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(context, nativeBannerAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);
        // Create native UI using the ad metadata.
        TextView nativeAdTitle = fb_adView.findViewById(R.id.native_ad_title);
        TextView nativeAdSocialContext = fb_adView.findViewById(R.id.native_ad_social_context);
        TextView sponsoredLabel = fb_adView.findViewById(R.id.native_ad_sponsored_label);
        MediaView nativeAdIconView = fb_adView.findViewById(R.id.native_icon_view);
        TextView nativeAdCallToAction = fb_adView.findViewById(R.id.native_ad_call_to_action);

        try {
            ad_unit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.fb_ads_native_bg_color)));
            nativeAdCallToAction.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.fb_ads_native_btn_color)));
            nativeAdCallToAction.setTextColor(Color.parseColor(Ids_Class.fb_ads_native_text_color));

            nativeAdSocialContext.setTextColor(Color.parseColor(Ids_Class.fb_ads_native_in_txt_color));
            nativeAdTitle.setTextColor(Color.parseColor(Ids_Class.fb_ads_native_in_txt_color));

           // Ids_Class.makeMeBlink(nativeAdCallToAction);
        } catch (NullPointerException n) {
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set the Text.
        nativeAdCallToAction.setText(nativeBannerAd.getAdCallToAction());
        nativeAdCallToAction.setVisibility(
                nativeBannerAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdTitle.setText(nativeBannerAd.getAdvertiserName());
        nativeAdSocialContext.setText(nativeBannerAd.getAdSocialContext());
        sponsoredLabel.setText(nativeBannerAd.getSponsoredTranslation());

        // Register the Title and CTA button to listen for clicks.
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);
        nativeBannerAd.registerViewForInteraction(fb_adView, nativeAdIconView, clickableViews);
        linearLayout.addView(fb_adView);
        Ads_Adapter_List.admob_nativehashmap.put(Ads_Adapter_List.pos, fb_adView);

    }


}
