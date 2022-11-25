package com.ads.adsdemosp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.ads.adsdemosp.AdsClass.Ads_AppOpen;


public class Appcontroller extends Application {
    private static Appcontroller ourInstance = new Appcontroller();

    public static Appcontroller getInstance() {
        return ourInstance;
    }

    private int numStarted = 0;
    public static boolean fast_start = false;

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
        SetAppOpenads();
    }

    private void SetAppOpenads() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                //Log.d("App_Controller", "onActivityCreated");
            }

            @Override
            public void onActivityStarted(Activity activity) {
                //Log.d("App_Controller", "onActivityStarted");
                if (numStarted == 0) {
                    //app went to foreground
                    Log.d("Appcontroller12","fast_start = " +fast_start);
                    if (fast_start) {
                        Ads_AppOpen.OpenAppAds_Show(activity);
                    }else{
                        fast_start = true;
                    }

                }
                numStarted++;
            }

            @Override
            public void onActivityResumed(Activity activity) {
                //Log.d("App_Controller", "onActivityResumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                //Log.d("App_Controller", "onActivityPaused");
            }

            @Override
            public void onActivityStopped(Activity activity) {
                //Log.d("App_Controller", "onActivityStopped");
                numStarted--;
                if (numStarted == 0) {
                    // app went to background
                    //Log.d("App_Controller", "background");
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                //Log.d("App_Controller", "onActivitySaveInstanceState");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                //Log.d("App_Controller", "onActivityDestroyed");
            }
        });

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    public static Appcontroller getApp() {
        if (ourInstance == null) {
            ourInstance = new Appcontroller();
        }
        return ourInstance;
    }


}