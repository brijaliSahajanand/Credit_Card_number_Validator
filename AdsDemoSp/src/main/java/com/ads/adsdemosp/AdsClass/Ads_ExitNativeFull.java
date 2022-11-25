package com.ads.adsdemosp.AdsClass;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ads.adsdemosp.Ids_Class;
import com.ads.adsdemosp.R;
import com.google.android.gms.ads.MediaContent;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;

public class Ads_ExitNativeFull {
    public static boolean checkExitAdsLoaded() {
        if (Ads_Native.admobnativeAd != null) {
            return true;
        }else  if(Ids_Class.ads_quiz_show && Ids_Class.is_big_native_quiz){
            return true;
        }
        return false;
    }

    public static void Exit_NativeFull_Show(final Context context, final LinearLayout lnr_view, final LinearLayout lnr_load,TextView txt_btn) {
        if (Ads_Native.admobnativeAd != null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            NativeAdView adView = (NativeAdView) inflater.inflate(R.layout.ads_admob_native_full, null);
            lnr_load.setVisibility(View.GONE);
            lnr_view.setVisibility(View.VISIBLE);
            populateUnifiedNativeAdView_full(Ads_Native.admobnativeAd, adView,txt_btn);
            Log.e("NativeFull_Show", "admob Native ad show : ");
            lnr_view.removeAllViews();
            lnr_view.addView(adView);
            Ads_Adapter_List.admob_nativehashmap.put(Ads_Adapter_List.pos, adView);
            //  Ads_Native.admob_nativehashmap.put(pos, lnr_view);

        } else {
            txt_btn.setVisibility(View.GONE);
            if(Ids_Class.ads_quiz_show && Ids_Class.is_big_native_quiz){
                lnr_load.setVisibility(View.GONE);
                lnr_view.setVisibility(View.VISIBLE);
                quiz_Ads.getquiz_Native_Ads(context,lnr_view);
            }else {
                lnr_load.setVisibility(View.GONE);
                lnr_view.setVisibility(View.GONE);
            }
        }
        Ads_Native.AdmobNativeFull_Load(context);
    }

    public static void populateUnifiedNativeAdView_full(NativeAd nativeAd, NativeAdView adView,TextView txt_call) {

        com.google.android.gms.ads.nativead.MediaView mediaView = adView.findViewById(R.id.ad_media);
        NativeAdView admob_main = (NativeAdView) adView.findViewById(R.id.admob_main);
        TextView txt_top_ads = (TextView) adView.findViewById(R.id.txt_top_ads);
        adView.setMediaView(mediaView);
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.findViewById(R.id.ad_call_to_action).setVisibility(View.GONE);
        //adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setCallToActionView(txt_call);
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
            adView.getCallToActionView().setVisibility(View.GONE);
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

}
