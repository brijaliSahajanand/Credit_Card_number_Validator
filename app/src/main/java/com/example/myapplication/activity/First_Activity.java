package com.example.myapplication.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.ads.adsdemosp.AdsClass.Ads_ExitNativeFull;
import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.example.myapplication.BuildConfig;
import com.example.myapplication.R;
import com.example.myapplication.constant.Preference;
import com.example.myapplication.constant.Utils;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.ForwardToSettingsCallback;
import com.permissionx.guolindev.callback.RequestCallback;
import com.permissionx.guolindev.request.ForwardScope;

import java.util.List;


public class First_Activity extends BaseActivity {
    TextView iv_nxt_button;
    LinearLayout ln_rate_us;
    String[] permissions = {
            "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
    LinearLayout llRateUs, llShareApp, llPrivacy;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(First_Activity.this.getResources().getColor(R.color.colorPrimary));
        }
        setContentView(R.layout.activity_first_);
        InItViewData();
    }
    public void InItViewData() {
        iv_nxt_button = findViewById(R.id.iv_nxt_button);
        llRateUs = findViewById(R.id.llRateUs);
        llShareApp = findViewById(R.id.llShareApp);
        llPrivacy = findViewById(R.id.llPrivacy);
        llRateUs.setOnClickListener(OnClickData);
        llShareApp.setOnClickListener(OnClickData);
        llPrivacy.setOnClickListener(OnClickData);
        iv_nxt_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CheckPermission()) {
                    onPermissionGranted();
                } else {
                    TakePermission();
                }
            }
        });
    }

    private boolean CheckPermission() {
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private View.OnClickListener OnClickData = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.llRateUs:
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Utils.PlaystoreBaseUrl + BuildConfig.APPLICATION_ID));
                    startActivity(intent);
                    break;
                case R.id.llShareApp:
                    try {
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
                        String shareMessage = "\nLet me recommend you this application\n\n";
                        shareMessage = shareMessage + Utils.PlaystoreBaseUrl + BuildConfig.APPLICATION_ID + "\n\n";
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                        startActivity(Intent.createChooser(shareIntent, "choose one"));
                    } catch (Exception e) {
                    }
                    break;
                case R.id.llPrivacy:
                    if (!Preference.getprivacy_policy().isEmpty()) {
                        String urlString = Preference.getprivacy_policy();
                        Intent intent_ = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                        intent_.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_.setPackage("com.android.chrome");
                        try {
                            startActivity(intent_);
                        } catch (ActivityNotFoundException ex) {
                            intent_.setPackage(null);
                            startActivity(intent_);
                        }
                    }
                    break;
            }
        }
    };
    private void onPermissionGranted() {
        Ads_Interstitial.showAds_full(First_Activity.this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                if (Preference.getscreenshow() >= 2) {
                    Intent intent = new Intent(First_Activity.this, Second_Activity.class);
                    startActivity(intent);
                } else if (Preference.getscreenshow() == 1) {
                    if (!Preference.getFirstTime()) {
                        Intent intent = new Intent(First_Activity.this, OverviewActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(First_Activity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
    public void TakePermission() {
        PermissionX.init(First_Activity.this)
                .permissions(permissions)
                .onForwardToSettings(new ForwardToSettingsCallback() {
                    @Override
                    public void onForwardToSettings(@NonNull ForwardScope scope, @NonNull List<String> deniedList) {
                        scope.showForwardToSettingsDialog(deniedList, "Core fundamental are based on these permissions",
                                "OK", "Cancel");
                    }
                }).request(new RequestCallback() {
            @Override
            public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                if (allGranted) {
                    PermissionGranted = true;

                }
            }
        });
    }
    boolean PermissionGranted = false;
    Boolean isRationale;
    Boolean isFirst = true;
    private boolean checkPermission(List permissionsList, String permission) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option
            if (!isFirst) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                    isRationale = true;
                    return false;
                }
            }
        }
        return true;
    }
    boolean exit_flag = false;
    @Override
    public void onBackPressed() {
        if (Ads_ExitNativeFull.checkExitAdsLoaded()) {
            Utils.Exit_Dialog(First_Activity.this);
        } else {
            if (exit_flag) {
                finishAffinity();
            } else {
                exit_flag = true;
                Toast.makeText(this, "Please tap again!", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exit_flag = false;
                    }
                }, 3000);
            }
        }


    }
}