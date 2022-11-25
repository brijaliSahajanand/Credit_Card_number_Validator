package com.example.myapplication.activity;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.constant.Utils;


public class ComingsoonActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.ChangeStatusBar(ComingsoonActivity.this);
        setContentView(R.layout.activity_comingsoon);
    }
}