package com.example.myapplication.constant;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ads.adsdemosp.AdsClass.Ads_ExitNativeFull;
import com.example.myapplication.R;
import com.example.myapplication.model.Example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Utils {
    public static String PlaystoreBaseUrl = "https://play.google.com/store/apps/details?id=";

    public static File RootDirectoryWhatsappShow = new File(Environment.getExternalStorageDirectory() + "/Download/XXVIVideoDownloader/Whatsapp");

    public static int position = 0;

    public static boolean vpnStart = false;
    public static boolean UpdateAds = false;

//    public static List<Example.VPNList> vpn_Lists = null;

    public static List<Example.CountryList> country_List = null;

    public static String Live = "http://104.131.36.193:6300/";



    public static no_internet no_internet;
    public static Dialog internet_dialog;



    public static void createFileFolder() {
        if (!RootDirectoryWhatsappShow.exists()) {
            RootDirectoryWhatsappShow.mkdirs();
        }

    }
    public static void ChangeStatusBar(Activity context) {

        context.requestWindowFeature(Window.FEATURE_NO_TITLE);
        context.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.getWindow().setNavigationBarColor(context.getResources().getColor(R.color.colorPrimary, context.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //   context.getWindow().setStatusBarColor(context.getResources().getColor(R.color.black));
            context.getWindow().setNavigationBarColor(context.getResources().getColor(R.color.colorPrimary));
        }
    }



    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public static void internetcheck(no_internet no_internet_) {
       no_internet = no_internet_;
    }

    public static void internetDialog(Context context) {
        internet_dialog = new Dialog(context);

        WindowManager.LayoutParams lWindowParams = new WindowManager.LayoutParams();
        lWindowParams.copyFrom(internet_dialog.getWindow().getAttributes());
        lWindowParams.width = WindowManager.LayoutParams.FILL_PARENT;
        lWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        internet_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        internet_dialog.setCancelable(false);
        internet_dialog.setContentView(R.layout.internet_dialogue);
        internet_dialog.show();
        internet_dialog.getWindow().setAttributes(lWindowParams);

        LinearLayout iv_try_again = internet_dialog.findViewById(R.id.ln_try_again);
        iv_try_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no_internet.no_internet();
            }
        });
    }





    public interface no_internet {
        void no_internet();
    }

    public interface adclick {
        void ad_click(Boolean i);
    }



    public static void OpenApp(Context context2, String str) {
        Intent launchIntentForPackage = context2.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage != null) {
            context2.startActivity(launchIntentForPackage);
        } else {
            setToast((Activity) context2, "App Not Available.");
        }
    }

    public static void setToast(Activity activity, String str) {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, "" + str, Toast.LENGTH_SHORT).show();
            }
        });

    }


    public static String lattestfile_path = "";
    public static boolean isDownloading;

    public interface DownloadDoneinterface {
        void refreshPage();
    }


    public interface UpdateDownPer {
        void percentage(int percentage);
    }


    public static UpdateDownPer updateDownPer;
    public static DownloadDoneinterface downloadDoneinterface;

    public static void isConnectingToInternet(Context context, OnCheckNet onChecknets, boolean... booleans) {
        onChecknet = onChecknets;
        CheckInternetData(context);

        // return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private static void CheckInternetData(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            onChecknet.OnCheckNet(true);
        } else {
            try {
                showAlertDialog(context, context.getString(R.string.app_name),
                        context.getString(R.string.disconnected),
                        "Retry");
            } catch (NumberFormatException ex) { // handle your exception
            }
        }
    }

    private static OnCheckNet onChecknet;

    public interface OnCheckNet {
        void OnCheckNet(boolean b);
    }
    public static void showAlertDialog(Context context, String title, String msg, String positiveText) {
        androidx.appcompat.app.AlertDialog alertDialog = new androidx.appcompat.app.AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(title)
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CheckInternetData(context);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onChecknet.OnCheckNet(false);
                    }
                })
                .show();
    }

    static Dialog dialog_simple;

    public static void showProgressDialog(Activity context) {
        try {
            try {
                if (dialog_simple != null && dialog_simple.isShowing()) {
                    dialog_simple.dismiss();
                }
            } catch (NullPointerException n) {
                n.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            dialog_simple = new Dialog(context);
            dialog_simple.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            LayoutInflater inflater = (LayoutInflater) dialog_simple.getLayoutInflater();
            View customView = inflater.inflate(R.layout.custom_progressbar, null);
            dialog_simple.setContentView(customView);
            dialog_simple.setCancelable(false);
            dialog_simple.setCanceledOnTouchOutside(false);
            dialog_simple.show();
        } catch (NullPointerException n) {
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void hideProgressDialog() {
        try {
            if (dialog_simple != null && dialog_simple.isShowing()) {
                dialog_simple.dismiss();
            }
        } catch (NullPointerException n) {
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Dialog exit_dialog;
    public static void Exit_Dialog(Activity activity) {
        exit_dialog = new Dialog( activity);
        exit_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        LayoutInflater inflater = (LayoutInflater) exit_dialog.getLayoutInflater();
        View customView = inflater.inflate(R.layout.custom_exit_dialog, null);
        exit_dialog.setContentView(customView);

        Window window = exit_dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        window.getDecorView().setSystemUiVisibility(uiOptions);

        exit_dialog.getWindow().setGravity(Gravity.BOTTOM);
        exit_dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        exit_dialog.getWindow().getAttributes().windowAnimations = R.style.ExitDialogAnimation;
        exit_dialog.setCancelable(true);
        exit_dialog.setCanceledOnTouchOutside(true);
        TextView txt_done = (TextView) exit_dialog.findViewById(R.id.txt_done);

        LinearLayout llline = (LinearLayout) exit_dialog.findViewById(R.id.llline);
        LinearLayout llnative = (LinearLayout) exit_dialog.findViewById(R.id.llnative);
        TextView ad_call_to_action = (TextView) exit_dialog.findViewById(R.id.ad_call_to_action);

        //NativeAds_Sp.NativeExit_Show( First_Activity.this, llnative, llline, ad_call_to_action);
        Ads_ExitNativeFull.Exit_NativeFull_Show(activity, llnative, llline, ad_call_to_action);

        txt_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finishAffinity();
            }
        });

        exit_dialog.show();
    }


    @SuppressLint("WrongConstant")
    public static View makeMeBlink(View view) {

        if (Preference.getbuttonanimate()) {
            ObjectAnimator AnimateGlass1;
            if (Preference.getanimationtype().equals("zoominout")) {
                AnimateGlass1 = ObjectAnimator.ofPropertyValuesHolder(view,
                        PropertyValuesHolder.ofFloat("scaleX", 0.9f, 1f),
                        PropertyValuesHolder.ofFloat("scaleY", 0.9f, 1f));
            } else {
                AnimateGlass1 = ObjectAnimator.ofFloat(view, "alpha", 0.3f, 1f);

            }
            AnimateGlass1.setDuration(500);
            AnimateGlass1.setRepeatMode(Animation.REVERSE);
            AnimateGlass1.setRepeatCount(Animation.INFINITE);
            AnimateGlass1.start();

        }
        return view;

    }

}
