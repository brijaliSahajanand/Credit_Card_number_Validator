package com.example.myapplication.VPN;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activity.BaseActivity;
import com.example.myapplication.activity.First_Activity;
import com.example.myapplication.activity.Splash_Activity;
import com.example.myapplication.constant.Preference;
import com.example.myapplication.constant.Utils;


public class Privacy_Policy_Screen extends BaseActivity {

    //    WebView webView;
    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy__policy__screen);

        //webView = (WebView) findViewById(R.id.pp);
        textView = (TextView) findViewById(R.id.txt_privacy);
        TextView next = (TextView) findViewById(R.id.next);
        //UserDataGet();
        Utils.isConnectingToInternet(Privacy_Policy_Screen.this, new Utils.OnCheckNet() {
            @Override
            public void OnCheckNet(boolean b) {
                if (b) {
                    try {
                        if (Preference.getPrivacy_policy_html() != null) {
                            textView.setMovementMethod(LinkMovementMethod.getInstance());
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                textView.setText(Html.fromHtml(Preference.getPrivacy_policy_html(), Html.FROM_HTML_MODE_COMPACT));
                            } else {
                                textView.setText(Html.fromHtml(Preference.getPrivacy_policy_html()));
                            }

                        } else {
                            Toast.makeText(Privacy_Policy_Screen.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NullPointerException n) {
                        n.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //webView.loadUrl("file:///android_asset/pp.html");

                } else {
                    finishAffinity();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Ads.showAds(Privacy_Policy_Screen.this, new Ads.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        Splash_Activity.fromstart = 1;
                        startActivity(new Intent(Privacy_Policy_Screen.this, First_Activity.class));
                        finish();

                    }
                });

            }
        });
    }


    public class MyWebViewClient extends WebViewClient {
        public MyWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            webView.loadUrl(str);
            return true;
        }
    }

    boolean exit_flag = false;

    @Override
    public void onBackPressed() {
        if (exit_flag) {
            finishAffinity();
        } else {
            exit_flag = true;
            Toast.makeText(this, "Please tap again to exit!", Toast.LENGTH_SHORT).show();
        }


    }
}