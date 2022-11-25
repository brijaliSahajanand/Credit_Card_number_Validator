package com.example.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.example.myapplication.R;
import com.example.myapplication.adapter.HistoryAdapter;
import com.example.myapplication.model.DbHandler;

import java.util.ArrayList;
import java.util.HashMap;


public class HistoryActivity extends AppCompatActivity {

    RecyclerView recycleview;
    Activity activity;
    ImageView imgBack;
    HistoryAdapter adapter;
    ArrayList<HashMap<String, String>> DataOfHistory = new ArrayList<>();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_history);

        activity = HistoryActivity.this;


        DataOfHistory = new DbHandler(this).GetUsers();
        recycleview = findViewById(R.id.recycleview);
        recycleview.setLayoutManager(new LinearLayoutManager(activity));
        if (DataOfHistory.size() > 0) {
            adapter = new HistoryAdapter(activity, DataOfHistory);
            recycleview.setAdapter(adapter);
        }

        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    public void onResume() {
        super.onResume();
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
