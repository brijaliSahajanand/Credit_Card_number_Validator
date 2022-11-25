package com.ads.adsdemosp.AdsView;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.ads.adsdemosp.AdsClass.Ads_Native;
import com.ads.adsdemosp.R;

public class NativeAdsView extends LinearLayout {
    public NativeAdsView(Context context) {
        super(context);
    }

    public NativeAdsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater li = LayoutInflater.from(context);
        LinearLayout ll = (LinearLayout) li.inflate(R.layout.layout_native_ads_full, this);

        LinearLayout llline = (LinearLayout) ll.findViewById(R.id.llline_full);
        LinearLayout llnative = (LinearLayout) ll.findViewById(R.id.llnative_full);
        Ads_Native.NativeFull_Show(context, llnative, llline);
    }


    public NativeAdsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NativeAdsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

}
