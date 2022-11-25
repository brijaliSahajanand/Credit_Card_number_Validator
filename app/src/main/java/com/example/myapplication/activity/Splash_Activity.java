package com.example.myapplication.activity;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ads.adsdemosp.AdsClass.Ads_AppOpen;
import com.ads.adsdemosp.AdsClass.Ads_SplashAppOpen;
import com.ads.adsdemosp.Appcontroller;
import com.ads.adsdemosp.Ids_Class;
import com.anchorfree.partner.api.ClientInfo;
import com.anchorfree.partner.api.auth.AuthMethod;
import com.anchorfree.partner.api.response.User;
import com.anchorfree.reporting.TrackingConstants;
import com.anchorfree.sdk.HydraTransportConfig;
import com.anchorfree.sdk.NotificationConfig;
import com.anchorfree.sdk.TransportConfig;
import com.anchorfree.sdk.UnifiedSDK;
import com.anchorfree.sdk.UnifiedSDKConfig;
import com.anchorfree.vpnsdk.callbacks.CompletableCallback;
import com.anchorfree.vpnsdk.exceptions.VpnException;
import com.anchorfree.vpnsdk.vpnservice.VPNState;
import com.example.myapplication.BuildConfig;
import com.example.myapplication.R;
import com.example.myapplication.VPN.Sample_Connection;
import com.example.myapplication.constant.Preference;
import com.example.myapplication.constant.Utils;
import com.example.myapplication.model.Example;
import com.example.myapplication.model.Pro_IPModel;
import com.example.myapplication.model.TraficLimitResponse;
import com.example.myapplication.retrofit.APIClient;
import com.example.myapplication.retrofit.APIInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Splash_Activity extends BaseActivity {
    public static List<String> admob_native_list = new ArrayList<>();
    public static List<String> admob_native_banner_list = new ArrayList<>();
    public static List<String> admob_interstitial_list = new ArrayList<>();
    public static List<String> admob_app_open_list = new ArrayList<>();
    public static List<String> admob_adaptive_banner_list = new ArrayList<>();
    boolean vn_show = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.ChangeStatusBar(Splash_Activity.this);
        setContentView(R.layout.activity_splash);
        Ads_AppOpen.isShowingAd = true;
        Appcontroller.fast_start = false;
        if (Utils.isNetworkConnected(this)) {
            subScribePushChannel();
        } else {
            Utils.internetDialog(Splash_Activity.this);
            Utils.internetcheck(new Utils.no_internet() {
                @Override
                public void no_internet() {
                    if (Utils.isNetworkConnected(Splash_Activity.this)) {
                        subScribePushChannel();
                        Utils.internet_dialog.dismiss();
                    }
                }
            });
        }
    }

    private void subScribePushChannel() {
        CheckUpSetting();
        try {
            FirebaseMessaging.getInstance().subscribeToTopic("CreditCradd_BZ1")
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void CallIpApi() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(7000, TimeUnit.SECONDS)
                .connectTimeout(7000, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ip-api.com/json/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        Call<Pro_IPModel> call = apiInterface.getipdata();
        call.enqueue(new Callback<Pro_IPModel>() {
            @Override
            public void onResponse(Call<Pro_IPModel> call, Response<Pro_IPModel> response) {
                // Utils.hideProgress();
                if (response.isSuccessful()) {
                    String Region = response.body().getRegionName();
                    String Country = response.body().getCountry();
                    String City = response.body().getCity();
                    Preference.setStateNameVN(Region);
                    Preference.setCountryNmeVN(Country);
                    Preference.setCityNmeVN(City);
                }
                setting_api();
            }

            @Override
            public void onFailure(Call<Pro_IPModel> call, Throwable t) {
                Log.d("publicip", "onfailure: failed");
                setting_api();
            }
        });
    }

    public void setting_api() {
        HashMap<String, String> SendData = new HashMap<>();
        SendData.put("package", this.getPackageName());
        SendData.put("country", Preference.getCountryNmeVN());
        SendData.put("state", Preference.getStateNameVN());
        SendData.put("city", Preference.getCityNmeVN());
        APIInterface apiInterface_local = APIClient.getClient().create(APIInterface.class);
        Call<Example> call = apiInterface_local.setting_api(BuildConfig.APPLICATION_ID,
                Preference.getCountryNmeVN(), Preference.getStateNameVN(),
                Preference.getCityNmeVN());
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful()) {
                    Example.Package aPackage = response.body().getPackage();
                    Preference.setads_click(aPackage.getAdsClick());
                    Preference.setmaintenance(aPackage.getMaintenance());
                    Preference.setback_click(aPackage.getBackClick());
                    Preference.set_id(aPackage.getId());
                    Preference.setPackage(aPackage.getPackage());
                    Preference.setversion_name(aPackage.getVersionName());
                    Preference.setapp_msg(aPackage.getAppMsg());
                    Preference.setupdate_url(aPackage.getUpdateUrl());
                    Preference.setis_update(aPackage.getIsUpdate());
                    Preference.setads_type(aPackage.getAdsType());
                    Preference.setsplash_ads_type(aPackage.getSplashAdsType());
                    Preference.setlink(aPackage.getLink());
                    Preference.setprivacy_policy(aPackage.getPrivacyPolicy());
                    Preference.setVn_header_show(response.body().getPackage().getVnHeaderShow());
                    admob_adaptive_banner_list = response.body().getPackage().getAdmob_banner_id_list();
                    admob_native_list = response.body().getPackage().getAdmob_native_id_list();
                    admob_native_banner_list = response.body().getPackage().getAdmob_bottom_native_id_list();
                    admob_interstitial_list = response.body().getPackage().getAdmob_interstitial_id_list();
                    admob_app_open_list = response.body().getPackage().getApp_open_id_list();
                    Preference.setAppOpen_click(aPackage.app_open_interstitial_click);
                    Preference.setAppOpen_back_click(aPackage.app_open_back_interstitial_click);
                    Preference.setAppOpen_inter_show(aPackage.app_open_interstitial_show);
                    Preference.setPrivacy_policy_html(response.body().privacypolicy);
                    Preference.setnative_button_color(aPackage.admob_native_btn_color);
                    Preference.setnative_btn_text_color(aPackage.admob_native_btn_text_color);
                    Preference.setcomingsoon(aPackage.comingsoon);
                    Preference.setqureka_header_show(aPackage.quiz_header_show);
                    Preference.setLetsgoscreen(aPackage.firstscreen);
                    Preference.setNextscreen(aPackage.secondscreen);
                    Preference.setNative_by_page(aPackage.native_by_page);
                    Preference.setqureka_show(response.body().getPackage().getIs_quiz());
                    Preference.setPrivacy_policy_html(response.body().privacypolicy);
                    Utils.country_List = response.body().countryList;
                    Preference.setIs_big_native_qureka(aPackage.is_big_native_quiz);
                    Preference.setIs_small_native_qureka(aPackage.is_small_native_quiz);
                    Preference.setadmob_reward_id(aPackage.admob_reward_id);
                    Preference.setFb_native_id(aPackage.fb_native_id);
                    Preference.setFb_native_banner_id(aPackage.fb_native_banner_id);
                    Preference.setFb_interstitial_id(aPackage.fb_interstitial_id);
                    Preference.setVn_direct_connect(response.body().getPackage().vn_direct_connect);
                    Preference.setAd_one_by_one(response.body().getPackage().ad_one_by_one_ids);
                    if (response.body().getPackage().is_start_with_zero) {
                        Preference.setFulladsArrayINT(0);
                        Preference.setNativeArrayINT(0);
                        Preference.setNativeBannerArrayINT(0);
                        Preference.setOpenAdsArrayINT(0);
                    }
                    try {
                        if (response.body().getPackage().vnid.length() > 0) {
                            Preference.setVnid(response.body().getPackage().vnid);
                            Preference.setVnpassword(response.body().getPackage().vnpassword);
                        }
                    } catch (NullPointerException n) {
                        n.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Preference.setVPN_Show(response.body().vn_show);
                    if (aPackage.bottom_ads_type.equals("nativebanner")) {
                        Preference.setisBottom_native(true);
                    } else {
                        Preference.setisBottom_native(false);
                    }
                    Preference.setBottomads_type(aPackage.bottom_ads_type);
                    //QUREKA
                    Preference.setAdmob_page(response.body().getPackage().admob_page);
                    Preference.setinter_admob(response.body().getPackage().inter_admob_click);
                    Preference.setnative_admob(response.body().getPackage().native_admob_click);
                    Preference.setbanner_admob(response.body().getPackage().banner_admob_click);
                    Preference.setAds_open_admob(response.body().getPackage().ads_open_admob_click);
                    Preference.setInter_back_admob(response.body().getPackage().inter_back_admob_click);
                    Preference.setRendomserver(response.body().getPackage().random_server);
                    Preference.set_server_short(response.body().getPackage().country_code);
                    Preference.setserver_name(response.body().getPackage().country_name);
                    Preference.setServer_image(response.body().getPackage().images_url);
                    Preference.setbuttonanimate(response.body().getPackage().ads_buttom_animation);
                    Preference.setanimationtype(response.body().getPackage().ads_button_animation_type);
                    Preference.setadmob_native_content_text_color(response.body().getPackage().admob_native_content_text_color);
                    Preference.setadmob_native_bg_color(response.body().getPackage().admob_native_bg_color);
                    Preference.setfb_native_btn_color(response.body().getPackage().fb_native_btn_color);
                    Preference.setfb_native_btn_text_color(response.body().getPackage().fb_native_btn_text_color);
                    Preference.setfb_native_content_text_color(response.body().getPackage().fb_native_content_text_color);
                    Preference.setfb_native_bg_color(response.body().getPackage().fb_native_bg_color);
                    Preference.setscreenshow(response.body().getPackage().screen_show);
                    Preference.setgift_view_show(response.body().getPackage().gift_view_show);
                    FirebaseAnalytics.getInstance(Splash_Activity.this).setAnalyticsCollectionEnabled(aPackage.firebaseanalytics);
                    Release_AdsSetUP();
                    if (response.body().getPackage().getMaintenance()) {
                        GetUnderMaintenanceDialog(1, response.body().getPackage().getAppMsg(), response.body().getPackage().getUpdateUrl());
                    } else {
                        if (compareVersionNames(GetVersionCode(), response.body().getPackage().getVersionName()) == -1) {
                            GetUnderMaintenanceDialog(2, response.body().getPackage().getAppMsg(), response.body().getPackage().getUpdateUrl());
                        } else {
                            int version1 = Integer.parseInt(response.body().getPackage().version_code);
                            int real_version1 = BuildConfig.VERSION_CODE;
                            if (version1 == real_version1) {
                                if (response.body().getPackage().update_coming_soon) {
                                    Preference.setcomingsoon(true);
                                    startActivity(new Intent(Splash_Activity.this, ComingsoonActivity.class));
                                    finish();
                                    return;
                                }
                            }
                            if (Preference.getVPN_Show()) {
                                initHydraSdk();
                            } else {
                                IntentActivity();
                            }
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d("seeting_api", "onFailure: " + t.toString());
                Toast.makeText(Splash_Activity.this, "Something Wen't Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
    Dialog undermaintenancedialog;
    TextView tv_maintenance_msg;
    Button btn_ok, btn_cancel;
    private void GetUnderMaintenanceDialog(int flag, String Maintenance, String link) {
        undermaintenancedialog = new Dialog(Splash_Activity.this);
        undermaintenancedialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        undermaintenancedialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        undermaintenancedialog.setContentView(R.layout.dialog_undermaintenanace);
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        final int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        undermaintenancedialog.getWindow().setLayout(width, height);
        undermaintenancedialog.setCancelable(false);
        undermaintenancedialog.show();
        tv_maintenance_msg = undermaintenancedialog.findViewById(R.id.tv_maintenance_msg);
        btn_ok = undermaintenancedialog.findViewById(R.id.btn_ok);
        btn_cancel = undermaintenancedialog.findViewById(R.id.btn_cancel);
        if (flag == 2) {
            btn_ok.setText("Update");
            btn_cancel.setVisibility(View.VISIBLE);
        }
        if (flag == 3) {
            tv_maintenance_msg.setText("Internet is required to use this app");
        } else {
            tv_maintenance_msg.setText(Maintenance);
        }
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 1) {
                    finish();
                } else if (flag == 2) {
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
                    }
                } else if (flag == 3) {
                    finish();
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 1) {
                    undermaintenancedialog.dismiss();
                    IntentActivity();
                } else {
                    finish();
                }
            }
        });
    }
    public int compareVersionNames(String oldVersionName, String newVersionName) {
        int res = 0;
        String[] oldNumbers = oldVersionName.split("\\.");
        String[] newNumbers = newVersionName.split("\\.");
        int maxIndex = Math.min(oldNumbers.length, newNumbers.length);
        for (int i = 0; i < maxIndex; i++) {
            int oldVersionPart = Integer.valueOf(oldNumbers[i]);
            int newVersionPart = Integer.valueOf(newNumbers[i]);
            if (oldVersionPart < newVersionPart) {
                res = -1;
                break;
            } else if (oldVersionPart > newVersionPart) {
                res = 1;
                break;
            }
        }
        if (res == 0 && oldNumbers.length != newNumbers.length) {
            res = (oldNumbers.length > newNumbers.length) ? 1 : -1;
        }
        return res;
    }
    private String GetVersionCode() {
        String version = null;
        PackageManager manager = getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(getPackageName(), 0);
            version = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            version = "1.0";
        }
        return version;
    }

    CountDownTimer countDownTimer = null;
    public boolean hasFinished = false;

    private void cancelCountDownAnimation() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    private void IntentActivity() {
        if (!Preference.getcomingsoon()) {
            LoadNativeAds();
        }
        Ads_SplashAppOpen.Splash_OpenAppAds_Load(Splash_Activity.this, new Ads_SplashAppOpen.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                IntentActivy();
            }
        });
    }

    public static int fromstart = 0;

    private void IntentActivy() {
        if (Preference.getcomingsoon()) {
            if (Preference.getisVpnConnect()) {
                disconnectFromVnp();
            } else {
                startActivity(new Intent(Splash_Activity.this, ComingsoonActivity.class));
                finish();
            }
        } else {
            Appcontroller.fast_start = false;
            if (Preference.getisVpnConnect()) {
                if (!Preference.getVPN_Show()) {
                    disconnectFromVnp();
                } else {
                    startActivity(new Intent(Splash_Activity.this, First_Activity.class));
                    finish();
                }
            } else {
                if (Preference.getVPN_Show()) {
                    Intent intent = new Intent(Splash_Activity.this, Sample_Connection.class);
                    intent.putExtra("type", Preference.getVn_direct_connect());
                    intent.putExtra("type_connection", "connection");
                    startActivity(intent);
                    finish();
                } else {
                    if (Preference.getisVpnConnect()) {
                        disconnectFromVnp();
                    } else {
                        startActivity(new Intent(Splash_Activity.this, First_Activity.class));
                        finish();
                    }
                }
            }
        }
    }

    public void disconnectFromVnp() {
        UnifiedSDK.getInstance().getVPN().stop(TrackingConstants.GprReasons.M_UI, new CompletableCallback() {
            @Override
            public void complete() {
                Utils.vpnStart = false;
                Preference.setisVpnConnect(false);
                if (Preference.getcomingsoon()) {
                    startActivity(new Intent(Splash_Activity.this, ComingsoonActivity.class));
                    finish();
                } else {
                    if (Preference.getscreenshow() >= 3) {
                        fromstart = 1;
                        startActivity(new Intent(Splash_Activity.this, First_Activity.class));
                        finish();
                    } else if (Preference.getscreenshow() == 2) {
                        fromstart = 2;
                        startActivity(new Intent(Splash_Activity.this, Second_Activity.class));
                        finish();
                    } else {
                        fromstart = 3;
                        startActivity(new Intent(Splash_Activity.this, Third_Activity.class));
                        finish();
                    }
                }
            }

            @Override
            public void error(@NonNull VpnException e) {
            }
        });
    }

    public boolean vpn() {
        String iface = "";
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.isUp())
                    iface = networkInterface.getName();
                Log.d("DEBUG", "IFACE NAME: " + iface);
                if (iface.contains("tun") || iface.contains("ppp") || iface.contains("pptp")) {
                    return true;
                }
            }
        } catch (SocketException e1) {
            e1.printStackTrace();
        }
        return false;
    }

    Dialog Waringdialog;
    TextView tv_maintenance_msg1;
    Button btn_ok1, btn_cancel1;

    private void GetWaringDialog() {
        Waringdialog = new Dialog(Splash_Activity.this);
        Waringdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Waringdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Waringdialog.setContentView(R.layout.dialog_undermaintenanace);
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        final int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        Waringdialog.getWindow().setLayout(width, height);
        Waringdialog.setCancelable(false);
        Waringdialog.show();
        tv_maintenance_msg1 = Waringdialog.findViewById(R.id.tv_maintenance_msg);
        btn_ok1 = Waringdialog.findViewById(R.id.btn_ok);
        btn_cancel1 = Waringdialog.findViewById(R.id.btn_cancel);
        tv_maintenance_msg1.setText("Please disconnect other VPN, after use our APP!");
        btn_ok1.setText("Setting");
        btn_cancel1.setVisibility(View.VISIBLE);
        btn_cancel1.setText("Ok");
        btn_ok1.setVisibility(View.GONE);
        btn_ok1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Waringdialog.dismiss();
                Intent intent = new Intent("android.net.vpn.SETTINGS");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityForResult(intent, 10);
            }
        });
        btn_cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Waringdialog.dismiss();
                finishAffinity();
            }
        });
    }

    private void Release_AdsSetUP() {
        if (BuildConfig.DEBUG) {
            Ids_Class.admobInterstitialIds_list.add("/6499/example/interstitial-");
            Ids_Class.admobNativeFullIds_list.add("/6499/example/native-");
            Ids_Class.admobNativeCustomIds_list.add("/6499/example/native-");
            Ids_Class.admobBannerIds_list.add("/6499/example/banner-");
            Ids_Class.admobAppopenIds_list.add("/6499/example/app_open_new-");
        } else {
            Ids_Class.admobInterstitialIds_list = admob_interstitial_list;
            Ids_Class.admobNativeFullIds_list = admob_native_list;
            Ids_Class.admobNativeCustomIds_list = admob_native_banner_list;
            Ids_Class.admobBannerIds_list = admob_adaptive_banner_list;
            Ids_Class.admobAppopenIds_list = admob_app_open_list;
        }
        Ids_Class.admob_Rewards_ids = Preference.getadmob_reward_id();
        Ids_Class.fb_Interstitial_ids = Preference.getFb_interstitial_id();
        Ids_Class.fb_NativeFull_ids = Preference.getFb_native_id();
        Ids_Class.fb_NativeBanner_ids = Preference.getFb_native_banner_id();
        Ids_Class.ads_type = Preference.getads_type();
        Ids_Class.ads_native_type = Preference.getBottomads_type();
        Ids_Class.ads_native_btn_color = Preference.getnative_button_color();
        Ids_Class.ads_native_text_color = Preference.getnative_btn_text_color();
        Ids_Class.ads_native_bg_color = Preference.getadmob_native_bg_color();
        Ids_Class.ads_native_in_text_color = Preference.getadmob_native_content_text_color();
        Ids_Class.buttonanimate = Preference.getbuttonanimate();
        Ids_Class.animation_type = Preference.getanimationtype();
        Ids_Class.Interstitial_adsclick = Preference.getads_click();
        Ids_Class.Interstitial_Backadsclick = Preference.getback_click();
        Ids_Class.Native_adsscreen = Preference.getNative_by_page();
        Ids_Class.ads_App_open_InterstitialAd = Preference.getAppOpen_inter_show();
        Ids_Class.AppOpen_adsclick = Preference.getAppOpen_click();
        Ids_Class.AppOpen_Backadsclick = Preference.getAppOpen_back_click();
        Ids_Class.ads_quiz_show = Preference.getqureka_show();
        Ids_Class.ads_quiz_by_page_show = Preference.getAdmob_page();
        Ids_Class.quiz_Interstitial_adsclick = Preference.getinter_admob();
        Ids_Class.quiz_Native_adsclick = Preference.getnative_admob();
        Ids_Class.quiz_Banner_adsclick = Preference.getbanner_admob();
        Ids_Class.quiz_AppOpen_adsclick = Preference.getAds_open_admob();
        Ids_Class.quiz_Interstitial_backadsclick = Preference.getInter_back_admob();
        Ids_Class.is_big_native_quiz = Preference.getIs_big_native_qureka();
        Ids_Class.is_small_native_quiz = Preference.getIs_small_native_qureka();
        Ids_Class.quiz_header_show = Preference.getQureka_header_show();
        Ids_Class.fb_ads_native_btn_color = Preference.getfb_native_btn_color();
        Ids_Class.fb_ads_native_text_color = Preference.getfb_native_btn_text_color();
        Ids_Class.fb_ads_native_bg_color = Preference.getfb_native_bg_color();
        Ids_Class.fb_ads_native_in_txt_color = Preference.getfb_native_content_text_color();
        Ids_Class.quizLink = Preference.getlink();
        Ids_Class.ad_one_by_one_ids = Preference.getAd_one_by_one();


    }

    private void LoadNativeAds() {
        Ids_Class.Laod_NativeAds(Splash_Activity.this);
    }

    private void CheckUpSetting() {
        UnifiedSDK.getVpnState(new com.anchorfree.vpnsdk.callbacks.Callback<VPNState>() {
            @Override
            public void success(@NonNull VPNState vpnState) {
                switch (vpnState) {
                    case IDLE: {
                        Log.d("SplashActivity12", "IDLE = " + vpnState);
                        Preference.setisVpnConnect(false);
                        Utils.vpnStart = false;
                        if (!vpn()) {
                            Log.d("SplashActivity12", "vpn disconnect");
                            if (Preference.getCountryNmeVN().isEmpty() || Preference.getStateNameVN().isEmpty() || Preference.getCityNmeVN().isEmpty()) {
                                Log.d("SplashActivity12", "check ip");
                                CallIpApi();
                            } else {
                                Log.d("SplashActivity12", "not check ip");
                                setting_api();
                            }
                        } else {
                            //  Toast.makeText(this, "Please disconnect VPN after use our APP!", Toast.LENGTH_SHORT).show();
                            Log.d("SplashActivity12", "other vpn connected");
                            GetWaringDialog();
                        }
                        break;
                    }
                    case CONNECTED: {
                        Log.d("SplashActivity12", "CONNECTED = " + vpnState);
                        Preference.setisVpnConnect(true);
                        Utils.vpnStart = true;
                        setting_api();
                        break;
                    }
                    case CONNECTING_VPN: {
                        Log.d("SplashActivity12", "CONNECTING_VPN = " + vpnState);
                        break;
                    }
                    case CONNECTING_CREDENTIALS:
                    case CONNECTING_PERMISSIONS:
                    case PAUSED: {
                        Log.d("SplashActivity12", "PAUSED  = " + vpnState);
                        break;
                    }
                }
            }

            @Override
            public void failure(@NonNull VpnException e) {
                Log.d("SplashActivity12", "getVpnState  failure = " + e.getMessage());
                Preference.setisVpnConnect(false);
                if (!vpn()) {
                    Log.d("SplashActivity12", "vpn disconnect");
                    if (Preference.getCountryNmeVN().isEmpty() || Preference.getStateNameVN().isEmpty() || Preference.getCityNmeVN().isEmpty()) {
                        Log.d("SplashActivity12", "check ip");
                        CallIpApi();
                    } else {
                        Log.d("SplashActivity12", "not check ip");
                        setting_api();
                    }
                } else {
                    //  Toast.makeText(this, "Please disconnect VPN after use our APP!", Toast.LENGTH_SHORT).show();
                    Log.d("SplashActivity12", "other vpn connected");
                    GetWaringDialog();
                }
            }
        });
    }

    UnifiedSDK unifiedSDK;
    String publicVpnKey = "";
    String PasswordVpn = "";

    public void initHydraSdk() {
        publicVpnKey = Preference.getVnid();
        PasswordVpn = Preference.getVnpassword();
        Log.d("3/9 Data -- ", publicVpnKey + " - ");
        createNotificationChannel();
        ClientInfo clientInfo = ClientInfo.newBuilder()
                .baseUrl("https://d2isj403unfbyl.cloudfront.net")
                .carrierId(publicVpnKey)
                .build();
        List<TransportConfig> transportConfigList = new ArrayList<>();
        transportConfigList.add(HydraTransportConfig.create());
//        transportConfigList.add(OpenVpnTransportConfig.tcp());
//        transportConfigList.add(OpenVpnTransportConfig.udp());
        UnifiedSDK.update(transportConfigList, CompletableCallback.EMPTY);
        UnifiedSDKConfig config = UnifiedSDKConfig.newBuilder().idfaEnabled(false).build();
        unifiedSDK = UnifiedSDK.getInstance(clientInfo, config);
        NotificationConfig notificationConfig = NotificationConfig.newBuilder()
                .title(getResources().getString(R.string.app_name))
                .channelId(CHANNEL_ID)
                .build();
        UnifiedSDK.update(notificationConfig);
        UnifiedSDK.setLoggingLevel(Log.VERBOSE);
        LoginAPi_Token();
//        loginToVpn();

    }

    private static final String CHANNEL_ID = "VPNMaster";

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "VPNMaster";
            String description = "VPN notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void loginToVpn() {
        Log.e("MainActivity12", "loginToVpn");
        AuthMethod authMethod = AuthMethod.anonymous();
        UnifiedSDK.getInstance().getBackend().login(authMethod, new com.anchorfree.vpnsdk.callbacks.Callback<User>() {
            @Override
            public void success(@NonNull User user) {
                Preference.setAura_user_id(user.getSubscriber().getId());
                Log.e("MainActivity12", "success" + user);
                IntentActivity();
            }

            @Override
            public void failure(@NonNull VpnException e) {
                Preference.setVPN_Show(false);
                IntentActivity();
                Log.e("MainActivity12", "error = " + e);
            }
        });
    }


    private void LoginAPi_Token() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-prod.northghost.com/partner/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIInterface apiInterface_local = retrofit.create(APIInterface.class);
        Call<TraficLimitResponse> call = apiInterface_local.Call_Add_Trafic("login?login=" + publicVpnKey + "&password=" + PasswordVpn);
        call.enqueue(new Callback<TraficLimitResponse>() {
            @Override
            public void onResponse(Call<TraficLimitResponse> call, Response<TraficLimitResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("ScratchActivity1", "LoginAPi_Token  =  response succ = " + response.body().result);
                    if (response.body().result.equals("OK")) {
                        Log.d("ScratchActivity1", "LoginAPi_Token  =  OK");
                        Log.d("ScratchActivity1", "LoginAPi_Token  =  access_token = " + response.body().access_token);
                        Preference.setAccessToken(response.body().access_token);
                        loginToVpn();
                    } else {
                        loginToVpn();
//                        IntentActivity();
                    }

                } else {
                    Log.d("ScratchActivity1", "LoginAPi_Token = " + response.message());
                    //  Toast.makeText(Pro_SplashActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    loginToVpn();
//                    IntentActivity();
                }


            }

            @Override
            public void onFailure(Call<TraficLimitResponse> call, Throwable t) {
//                IntentActivity();
                loginToVpn();
                Log.d("ScratchActivity1", "LoginAPi_Token onFailure= " + t.getMessage());
                // Toast.makeText(Pro_SplashActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}