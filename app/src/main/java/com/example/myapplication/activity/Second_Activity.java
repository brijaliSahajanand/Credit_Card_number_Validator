package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.example.myapplication.R;
import com.example.myapplication.constant.Preference;
import com.example.myapplication.constant.Utils;


public class Second_Activity extends BaseActivity {
    TextView iv_get_started;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.ChangeStatusBar(Second_Activity.this);
        setContentView(R.layout.activity_second_);

        InItViewData();
    }

    public void InItViewData() {
        iv_get_started = findViewById(R.id.iv_get_started);
        iv_get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ads_Interstitial.showAds_full(Second_Activity.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        if (Preference.getscreenshow() > 2) {
                            Intent intent = new Intent(Second_Activity.this, Third_Activity.class);
                            startActivity(intent);
                        } else {
                            if (!Preference.getFirstTime()) {
                                Intent intent = new Intent(Second_Activity.this, OverviewActivity.class);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(Second_Activity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
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
                Intent intent = new Intent(Second_Activity.this, First_Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}