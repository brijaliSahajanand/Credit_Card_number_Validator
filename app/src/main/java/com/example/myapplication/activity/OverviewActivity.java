package com.example.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.example.myapplication.R;
import com.example.myapplication.constant.Preference;
import com.example.myapplication.constant.Utils;


public class OverviewActivity extends Activity {
    private TextView save_next;
    ImageView imgBack;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_overview);
        Preference.isFirstTime(true);
        save_next = (TextView) findViewById(R.id.save_next);
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        save_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Ads_Interstitial.showAds_full(OverviewActivity.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        startActivity(new Intent(OverviewActivity.this, MainActivity.class));
                    }
                });
            }
        });
    }
    @Override
    public void onBackPressed() {
        Ads_Interstitial.BackshowAds_full(this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                finish();
            }
        });
    }
}
