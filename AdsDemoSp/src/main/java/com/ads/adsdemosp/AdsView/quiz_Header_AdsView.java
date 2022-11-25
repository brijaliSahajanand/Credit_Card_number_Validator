package com.ads.adsdemosp.AdsView;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.ads.adsdemosp.AdsClass.quiz_Ads;
import com.ads.adsdemosp.Ids_Class;
import com.ads.adsdemosp.R;

import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class quiz_Header_AdsView extends LinearLayout {
    public quiz_Header_AdsView(Context context) {
        super(context);
    }

    public quiz_Header_AdsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater li = LayoutInflater.from(context);
        LinearLayout ll = (LinearLayout) li.inflate(R.layout.quiz_header_adsview_layout, this);
        TextView txt_top_ads = (TextView) ll.findViewById(R.id.txt_top_ads);
        RelativeLayout rlt_main = (RelativeLayout) ll.findViewById(R.id.rlt_main);
        GifImageView iv_round_one = (GifImageView) ll.findViewById(R.id.iv_round_one);


        if(Ids_Class.ads_quiz_show && Ids_Class.quiz_header_show){
            rlt_main.setVisibility(VISIBLE);
        }else{
            rlt_main.setVisibility(GONE);
            return;
        }

        txt_top_ads.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.ads_native_btn_color)));
        txt_top_ads.setTextColor(Color.parseColor(Ids_Class.ads_native_text_color));
        int random = new Random().nextInt(3);
        if (random == 1) {
            iv_round_one.setBackgroundResource(R.drawable.round2);
        } else if (random == 2) {
            iv_round_one.setBackgroundResource(R.drawable.round3);
        } else {
            iv_round_one.setBackgroundResource(R.drawable.round5);
        }
        iv_round_one.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                quiz_Ads.quiz_click(context);
            }
        });

    }

    public quiz_Header_AdsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public quiz_Header_AdsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
