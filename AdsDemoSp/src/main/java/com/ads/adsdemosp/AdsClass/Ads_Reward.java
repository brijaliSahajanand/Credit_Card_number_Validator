package com.ads.adsdemosp.AdsClass;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.ads.adsdemosp.Ids_Class;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class Ads_Reward {

    public static OnFinishAds onFinishAds;

    public interface OnFinishAds {
        void onFinishAds(boolean b);
    }
    public static void Show_Reward_Ads(Activity context, OnFinishAds onFinishAd, boolean... doShowAds){
        onFinishAds = onFinishAd;
        if (mRewardedAd != null) {

            mRewardedAd.show(context, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    Log.d("AdmobRewardAdsLoad12", "The user earned the reward.");
                   // onFinishAds.onFinishAds(true);
                }
            });
        } else {
            Log.d("AdmobRewardAdsLoad12", "The rewarded ad wasn't ready yet.");
            onFinishAds.onFinishAds(false);
        }
    }

    public static  RewardedAd mRewardedAd;
    public static void Admob_RewardAds_Load(Context context) {
        AdRequest adRequest = new AdRequest.Builder().build();

        RewardedAd.load(context, Ids_Class.admob_Rewards_ids,
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d("AdmobRewardAdsLoad12", loadAdError.toString());
                        mRewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        Log.d("AdmobRewardAdsLoad12", "Ad was loaded.");
                        Reward_Ads_Listner();
                    }
                });

    }

    private static void Reward_Ads_Listner() {
        mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdClicked() {
                // Called when a click is recorded for an ad.
                Log.d("AdmobRewardAdsLoad12", "Ad was clicked.");
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                // Set the ad reference to null so you don't show the ad a second time.
                Log.d("AdmobRewardAdsLoad12", "Ad dismissed fullscreen content.");
                mRewardedAd = null;
                onFinishAds.onFinishAds(true);
            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
                // Called when ad fails to show.
                Log.e("AdmobRewardAdsLoad12", "Ad failed to show fullscreen content.");
                mRewardedAd = null;
                onFinishAds.onFinishAds(true);
            }

            @Override
            public void onAdImpression() {
                // Called when an impression is recorded for an ad.
                Log.d("AdmobRewardAdsLoad12", "Ad recorded an impression.");
            }

            @Override
            public void onAdShowedFullScreenContent() {
                // Called when ad is shown.
                Log.d("AdmobRewardAdsLoad12", "Ad showed fullscreen content.");
            }
        });
    }
}
