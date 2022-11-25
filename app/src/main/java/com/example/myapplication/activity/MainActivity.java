package com.example.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.example.myapplication.R;
import com.example.myapplication.VPN.Sample_Connection;
import com.example.myapplication.constant.Preference;
import com.example.myapplication.constant.Utils;


public class MainActivity extends Activity {
    private ImageView credit_card;
    private ImageView history, iv_cast;
    Activity activity;

    private ImageView search;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Utils.ChangeStatusBar(MainActivity.this);
        setContentView((int) R.layout.activity_main);
        activity = MainActivity.this;
        credit_card = (ImageView) findViewById(R.id.credit_card);
        search = (ImageView) findViewById(R.id.search);
        history = (ImageView) findViewById(R.id.history);
        iv_cast = (ImageView) findViewById(R.id.iv_cast);
        iv_cast.setOnClickListener(v -> {
            Ads_Interstitial.showAds_full((Activity) activity, new Ads_Interstitial.OnFinishAds() {
                @Override
                public void onFinishAds(boolean b) {
                    startActivity(new Intent(MainActivity.this, Sample_Connection.class));
                }
            });
        });
        if (Preference.getVn_header_show() && Preference.getVPN_Show()) {
            iv_cast.setVisibility(View.VISIBLE);
        }
        else {
            iv_cast.setVisibility(View.GONE);
        }
        this.credit_card.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Ads_Interstitial.showAds_full(MainActivity.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {

                        MainActivity.this.startActivity(new Intent(MainActivity.this, CreditCardActivity.class));
                    }
                });
            }
        });
        this.search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Ads_Interstitial.showAds_full(MainActivity.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        MainActivity.this.startActivity(new Intent(MainActivity.this, SearchCardActivity.class));
                    }
                });
            }
        });
        this.history.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Ads_Interstitial.showAds_full(MainActivity.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        MainActivity.this.startActivity(new Intent(MainActivity.this, HistoryActivity.class));
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
