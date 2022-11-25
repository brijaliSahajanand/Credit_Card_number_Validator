package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.example.myapplication.R;
import com.example.myapplication.constant.Preference;
import com.example.myapplication.constant.Utils;


public class Third_Activity extends BaseActivity {

    TextView iv_nxt_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.ChangeStatusBar(Third_Activity.this);
        setContentView(R.layout.activity_third_);

        InItViewData();
    }

    private void InItViewData() {
        iv_nxt_button = findViewById(R.id.iv_nxt_button);
        iv_nxt_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPermissionGranted();
            }
        });
    }


    private void onPermissionGranted() {
        Ads_Interstitial.showAds_full(Third_Activity.this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                if (!Preference.getFirstTime()) {
                    Intent intent = new Intent(Third_Activity.this, OverviewActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(Third_Activity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    boolean exit_flag = false;
    boolean PermissionGranted = false;

    @Override
    public void onBackPressed() {
        Ads_Interstitial.BackshowAds_full(this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                Intent intent = new Intent(Third_Activity.this, Second_Activity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}