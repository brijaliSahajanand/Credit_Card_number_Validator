package com.ads.adsdemosp.AdsView;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.ads.adsdemosp.AdsClass.Ads_Banner;
import com.ads.adsdemosp.R;

public class BannerAdsView extends LinearLayout {
    public BannerAdsView(Context context) {
        super(context);
    }

    public BannerAdsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater li = LayoutInflater.from(context);
        LinearLayout ll = (LinearLayout) li.inflate(R.layout.banneradsview, this);

        LinearLayout llline = (LinearLayout) ll.findViewById(R.id.llline);
        LinearLayout llnative = (LinearLayout) ll.findViewById(R.id.llnative);
        Ads_Banner.Native_Custom_Show(context, llnative, llline);
    }


    public BannerAdsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BannerAdsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

}
