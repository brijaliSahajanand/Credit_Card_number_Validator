package com.ads.adsdemosp.AdsClass;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ads.adsdemosp.Ids_Class;
import com.ads.adsdemosp.R;
import com.google.android.gms.ads.nativead.NativeAdView;

import java.util.HashMap;

public class Ads_Adapter_List {

    public static int pos = 0;
    public static HashMap<Integer, View> admob_nativehashmap = new HashMap<>();

    public static void NativeFull_Show(final Context context, final LinearLayout lnr_view, final LinearLayout lnr_load,int position) {
        pos = position;

        if (Ads_Adapter_List.admob_nativehashmap != null && Ads_Adapter_List.admob_nativehashmap.get(position) != null) {
            try {
                if (Ads_Adapter_List.admob_nativehashmap.get(position).getParent() != null)
                    ((ViewGroup) Ads_Adapter_List.admob_nativehashmap.get(position).getParent()).removeView(Ads_Adapter_List.admob_nativehashmap.get(position));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Ads_Adapter_List.Store_NativeFull_Show(context, lnr_view, lnr_load, position);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("onBindViewHolder12", "one");
            return;
        }
        Log.d("onBindViewHolder12", "two");
        if (Ids_Class.ads_type.equals("admob")) {
            Ads_Native.Admob_NativeFull_Show(context, lnr_view, lnr_load);
        } else {
            Ads_Native.Fb_NativeFull_Show(context, lnr_view, lnr_load);
        }
    }

    public static void Native_Custom_Show(Context context, LinearLayout lnr_view, LinearLayout lnr_load,int position) {

        pos = position;

        if (Ads_Adapter_List.admob_nativehashmap != null && Ads_Adapter_List.admob_nativehashmap.get(position) != null) {
            try {
                if (Ads_Adapter_List.admob_nativehashmap.get(position).getParent() != null)
                    ((ViewGroup) Ads_Adapter_List.admob_nativehashmap.get(position).getParent()).removeView(Ads_Adapter_List.admob_nativehashmap.get(position));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Ads_Adapter_List.Store_NativeFull_Show(context, lnr_view, lnr_load, position);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("onBindViewHolder12", "one");
            return;
        }
        Log.d("onBindViewHolder12", "two");
        if (Ids_Class.ads_type.equals("admob")) {
            if (Ids_Class.ads_native_type.equals("banner")) {
                Ads_Banner.Admob_banner_show(context, lnr_view, lnr_load);
            } else {
                Ads_Banner.Admob_Native_Custom_Show(context, lnr_view, lnr_load);
            }
        } else {
            Ads_Banner.Fb_Native_Custom_Show(context, lnr_view, lnr_load);
        }
    }


    public static void Store_NativeFull_Show(final Context context, final LinearLayout lnr_view, final LinearLayout lnr_load, int position) {
        if (admob_nativehashmap.get(position) != null) {
            lnr_view.removeAllViews();
            lnr_load.setVisibility(View.GONE);
            lnr_view.setVisibility(View.VISIBLE);
            Log.e("NativeFull_Show", "admob Native ad show : ");
            lnr_view.addView(admob_nativehashmap.get(position));

        } else {
            lnr_load.setVisibility(View.GONE);
            lnr_view.setVisibility(View.GONE);
        }
    }





    public static NativeAdView adView;

    public static void Admob_NativeFull_Show(final Context context, final LinearLayout lnr_view, final LinearLayout lnr_load) {
        if (Ads_Native.admobnativeAd != null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            adView = (NativeAdView) inflater.inflate(R.layout.ads_admob_native_full, null);
            lnr_load.setVisibility(View.GONE);
            lnr_view.setVisibility(View.VISIBLE);
            Ads_Native.populateUnifiedNativeAdView_full(Ads_Native.admobnativeAd, adView);
            Log.e("NativeFull_Show", "admob Native ad show : ");
            lnr_view.removeAllViews();
            lnr_view.addView(adView);
            admob_nativehashmap.put(pos, adView);
        } else if (Ads_Native.fb_native_adpater != null && Ads_Native.fb_native_adpater.isAdLoaded()) {
            lnr_load.setVisibility(View.GONE);
            lnr_view.setVisibility(View.VISIBLE);
            lnr_view.removeAllViews();
            LayoutInflater inflater = LayoutInflater.from(context);
            LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ads_fb_native_full, Ads_Native.nativeAdLayout, false);
            lnr_view.addView(adView);
            Ads_Native.fb_inflateAd_full(context, Ads_Native.fb_native_adpater, lnr_view, lnr_load, adView);
            Log.d("NativeFull_Show", "Fb_NativeFull_Show ");
            admob_nativehashmap.put(pos, adView);
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

        Ads_Native.AdmobNativeFull_Load(context);
    }

    public static void Fb_NativeFull_Show(final Context context, final LinearLayout lnr_view, final LinearLayout lnr_load) {
        if (Ads_Native.fb_native_adpater != null && Ads_Native.fb_native_adpater.isAdLoaded()) {
            lnr_load.setVisibility(View.GONE);
            lnr_view.setVisibility(View.VISIBLE);
            lnr_view.removeAllViews();
            LayoutInflater inflater = LayoutInflater.from(context);
            LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ads_fb_native_full, Ads_Native.nativeAdLayout, false);
            lnr_view.addView(adView);
            Ads_Native.fb_inflateAd_full(context, Ads_Native.fb_native_adpater, lnr_view, lnr_load, adView);
            Log.d("NativeFull_Show", "Fb_NativeFull_Show ");
            admob_nativehashmap.put(pos, adView);
        } else if (Ads_Native.admobnativeAd != null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            adView = (NativeAdView) inflater.inflate(R.layout.ads_admob_native_full, null);
            lnr_load.setVisibility(View.GONE);
            lnr_view.setVisibility(View.VISIBLE);
            Ads_Native.populateUnifiedNativeAdView_full(Ads_Native.admobnativeAd, adView);
            Log.e("NativeFull_Show", "admob Native ad show : ");
            lnr_view.removeAllViews();
            lnr_view.addView(adView);
            admob_nativehashmap.put(pos, adView);
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

        Ads_Native.FB_NativeAd_Load(context);
    }

}
