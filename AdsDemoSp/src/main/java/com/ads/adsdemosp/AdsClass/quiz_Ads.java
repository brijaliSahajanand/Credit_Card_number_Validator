package com.ads.adsdemosp.AdsClass;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ads.adsdemosp.Ids_Class;
import com.ads.adsdemosp.R;

import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class quiz_Ads {

  /*
   public static void getquiz_Full_Ads(Context context, Ads_Interstitial.OnFinishAds onFinishAds) {
        Dialog dialog = new Dialog(context, android.R.style.Theme_Light);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().getAttributes().windowAnimations = R.style.QuerekaInter;
        dialog.setContentView(R.layout.custom_quiz_interstitial_layout);

        Button btn_close = (Button) dialog.findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                onFinishAds.onFinishAds(true);
            }
        });

        dialog.setOnKeyListener(new Dialog.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialog.dismiss();
                    onFinishAds.onFinishAds(true);
                }
                return true;
            }
        });

        dialog.show();
    }
*/

    public static void getquiz_Full_Ads(Context context,Ads_Interstitial.OnFinishAds onFinishAds) {
        Dialog dialog = new Dialog(context, android.R.style.Theme_Light);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().getAttributes().windowAnimations = R.style.QuerekaInter;
        dialog.setContentView(R.layout.custom_quiz_interstitial_layout);
        GifImageView gif_inter_round = dialog.findViewById(R.id.gif_inter_round);
        TextView tv_text_ad_name = dialog.findViewById(R.id.tv_text_ad_name);
        TextView tv_text_ad_desc = dialog.findViewById(R.id.tv_text_ad_desc);

        LinearLayout lnr_play_it = dialog.findViewById(R.id.lnr_play_it);
        LinearLayout headerLL = dialog.findViewById(R.id.headerLL);
        TextView txt_btn = dialog.findViewById(R.id.txt_btn);
        TextView txt_bottom_ads = dialog.findViewById(R.id.txt_bottom_ads);
        TextView txt_top_ads = dialog.findViewById(R.id.txt_top_ads);
        ImageView qurekaAdsClose = dialog.findViewById(R.id.qurekaAdsClose);

        try {
            headerLL.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.ads_native_bg_color)));
            lnr_play_it.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.ads_native_btn_color)));
            txt_btn.setTextColor(Color.parseColor(Ids_Class.ads_native_text_color));
            txt_top_ads.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.ads_native_btn_color)));
            txt_top_ads.setTextColor(Color.parseColor(Ids_Class.ads_native_text_color));
            txt_bottom_ads.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.ads_native_btn_color)));
            txt_bottom_ads.setTextColor(Color.parseColor(Ids_Class.ads_native_text_color));

            qurekaAdsClose.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.ads_native_in_text_color)));
            tv_text_ad_name.setTextColor(Color.parseColor(Ids_Class.ads_native_in_text_color));
            tv_text_ad_desc.setTextColor(Color.parseColor(Ids_Class.ads_native_in_text_color));
        } catch (NullPointerException n) {
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        int random = new Random().nextInt(5);
        if (random == 1) {
            tv_text_ad_name.setText("Play Tech Quiz");
            tv_text_ad_desc.setText("Win 15,000 Coins & No Install Required");
            gif_inter_round.setBackgroundResource(R.drawable.round3);
            dialog.findViewById(R.id.qurekaAds).setBackgroundResource(R.drawable.inter1);
            dialog.findViewById(R.id.qurekaAds1).setBackgroundResource(R.drawable.inter1);
        } else if (random == 2) {
            tv_text_ad_name.setText("Give Way Enter To Win");
            tv_text_ad_desc.setText("Win 1,50,000 Coins & No Install Required");
            gif_inter_round.setBackgroundResource(R.drawable.round3);
            dialog.findViewById(R.id.qurekaAds).setBackgroundResource(R.drawable.inter2);
            dialog.findViewById(R.id.qurekaAds1).setBackgroundResource(R.drawable.inter2);
        } else if (random == 3) {
            tv_text_ad_name.setText("Play Fruit Chop Game");
            tv_text_ad_desc.setText("Win 50,000 Coins With Mobile Games");
            gif_inter_round.setBackgroundResource(R.drawable.round2);
            dialog.findViewById(R.id.qurekaAds).setBackgroundResource(R.drawable.inter3);
            dialog.findViewById(R.id.qurekaAds1).setBackgroundResource(R.drawable.inter3);
        } else if (random == 4) {
            tv_text_ad_name.setText("Play Bubble shooter game");
            tv_text_ad_desc.setText("Win 50,000 Coins & More");
            gif_inter_round.setBackgroundResource(R.drawable.round4);
            dialog.findViewById(R.id.qurekaAds).setBackgroundResource(R.drawable.inter4);
            dialog.findViewById(R.id.qurekaAds1).setBackgroundResource(R.drawable.inter4);
        } else {
            tv_text_ad_name.setText("Play Car Racing Game");
            tv_text_ad_desc.setText("Win 5,00,000 Coins & No Installation Required");
            gif_inter_round.setBackgroundResource(R.drawable.round5);
            dialog.findViewById(R.id.qurekaAds).setBackgroundResource(R.drawable.inter5);
            dialog.findViewById(R.id.qurekaAds1).setBackgroundResource(R.drawable.inter5);
        }

        (dialog.findViewById(R.id.qurekaAds)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quiz_click(context);
            }
        });

        (dialog.findViewById(R.id.ll_qureka)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quiz_click(context);
            }
        });

        (dialog.findViewById(R.id.qurekaAdsClose)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                onFinishAds.onFinishAds(true);
            }
        });

        dialog.setOnKeyListener(new Dialog.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialog.dismiss();
                    onFinishAds.onFinishAds(true);
                }
                return true;
            }
        });

        Ids_Class.makeMeBlink(lnr_play_it);

        dialog.show();
    }


    public static void getquiz_Native_Ads(Context context,  LinearLayout linearLayout) {
        linearLayout.setVisibility(View.VISIBLE);
        View layout2 = LayoutInflater.from(context).inflate(R.layout.custom_quiz_native_layout, linearLayout, false);

        TextView tv_text_ad_name = layout2.findViewById(R.id.tv_text_ad_name);
        TextView tv_text_ad_desc = layout2.findViewById(R.id.tv_text_ad_desc);
        GifImageView iv_round_gif = layout2.findViewById(R.id.iv_round_gif);

        RelativeLayout rlt_main = layout2.findViewById(R.id.rlt_main);
        LinearLayout lnr_btn = layout2.findViewById(R.id.lnr_btn);
        TextView txt_btn = layout2.findViewById(R.id.txt_btn);

        TextView txt_top_ads = layout2.findViewById(R.id.txt_top_ads);
        TextView txt_bottom_ads = layout2.findViewById(R.id.txt_bottom_ads);

        try {
            rlt_main.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.ads_native_bg_color)));
            lnr_btn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.ads_native_btn_color)));
            txt_btn.setTextColor(Color.parseColor(Ids_Class.ads_native_text_color));
            txt_top_ads.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.ads_native_btn_color)));
            txt_top_ads.setTextColor(Color.parseColor(Ids_Class.ads_native_text_color));
            txt_bottom_ads.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.ads_native_btn_color)));
            txt_bottom_ads.setTextColor(Color.parseColor(Ids_Class.ads_native_text_color));
            tv_text_ad_name.setTextColor(Color.parseColor(Ids_Class.ads_native_in_text_color));
            tv_text_ad_desc.setTextColor(Color.parseColor(Ids_Class.ads_native_in_text_color));
        } catch (NullPointerException n) {
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int random = new Random().nextInt(5);
        if (random == 1) {
            tv_text_ad_name.setText("Play Cricket Win Coins");
            tv_text_ad_desc.setText("Win 5,00,000 Coins & More");
            layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(R.drawable.native1);
            iv_round_gif.setBackgroundResource(R.drawable.round1);
        } else if (random == 2) {
            tv_text_ad_name.setText("Play Bubble Shooter Game");
            tv_text_ad_desc.setText("Win 50,000 Coins With Mobile Games");
            layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(R.drawable.native2);
            iv_round_gif.setBackgroundResource(R.drawable.round2);
        } else if (random == 3) {
            tv_text_ad_name.setText("Play Fruit Chop Game");
            tv_text_ad_desc.setText("Win 50,000 Coins No Install Required");
            layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(R.drawable.native3);
            iv_round_gif.setBackgroundResource(R.drawable.round3);
        } else if (random == 4) {
            tv_text_ad_name.setText("Play Don't Crash Game");
            tv_text_ad_desc.setText("Collect 50,000 Coins Now");
            layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(R.drawable.native4);
            iv_round_gif.setBackgroundResource(R.drawable.round4);
        } else {
            tv_text_ad_name.setText("Play Car Racing Game");
            tv_text_ad_desc.setText("Win Coin & No Installation Required");
            layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(R.drawable.native5);
            iv_round_gif.setBackgroundResource(R.drawable.round5);
        }

        layout2.findViewById(R.id.playNowLL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               quiz_click(context);

            }
        });

        Ids_Class.makeMeBlink(lnr_btn);
        linearLayout.removeAllViews();
        linearLayout.addView(layout2);
    }


    public static void getquiz_banner_Ads(Context context, LinearLayout lnr_view) {
        lnr_view.setVisibility(View.VISIBLE);

        View layout2 = LayoutInflater.from(context).inflate(R.layout.custom_quiz_native_banner_layout, lnr_view, false);
        TextView tv_text_ad_name = layout2.findViewById(R.id.tv_text_ad_name);
        TextView tv_text_ad_desc = layout2.findViewById(R.id.tv_text_ad_desc);
        GifImageView iv_round_gif = layout2.findViewById(R.id.iv_round_gif);
        LinearLayout playNowLL = layout2.findViewById(R.id.playNowLL);

        TextView txt_btn = layout2.findViewById(R.id.txt_btn);
        TextView txt_bottom_ads = layout2.findViewById(R.id.txt_bottom_ads);
        RelativeLayout rlBanner = layout2.findViewById(R.id.rlBanner);

        try {
            rlBanner.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.ads_native_bg_color)));
            playNowLL.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.ads_native_btn_color)));
            txt_btn.setTextColor(Color.parseColor(Ids_Class.ads_native_text_color));

            txt_bottom_ads.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.ads_native_btn_color)));
            txt_bottom_ads.setTextColor(Color.parseColor(Ids_Class.ads_native_text_color));
            tv_text_ad_name.setTextColor(Color.parseColor(Ids_Class.ads_native_in_text_color));
            tv_text_ad_desc.setTextColor(Color.parseColor(Ids_Class.ads_native_in_text_color));
        } catch (NullPointerException n) {
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int random = new Random().nextInt(5);
        if (random == 1) {
            tv_text_ad_name.setText("Play & Win Coins");
            tv_text_ad_desc.setText("Win 5,00,000 Coins & More");

            iv_round_gif.setImageResource(R.drawable.round1);
        } else if (random == 2) {
            tv_text_ad_name.setText("Play Bubble Shooter Game");
            tv_text_ad_desc.setText("Win 50,000 Coins With Mobile Games");

            iv_round_gif.setImageResource(R.drawable.round2);
        } else if (random == 3) {
            tv_text_ad_name.setText("Play Fruit Chop Game");
            tv_text_ad_desc.setText("Win 50,000 Coins No Install Required");

            iv_round_gif.setImageResource(R.drawable.round3);
        } else if (random == 4) {
            tv_text_ad_name.setText("Play Don't Crash Game");
            tv_text_ad_desc.setText("Collect 50,000 Coins Now");

            iv_round_gif.setImageResource(R.drawable.round4);
        } else {
            tv_text_ad_name.setText("Play Car Racing Game");
            tv_text_ad_desc.setText("Win Coin & No Installation Required");

            iv_round_gif.setImageResource(R.drawable.round5);
        }

        // layout2.findViewById(R.id.rlBanner).setBackgroundResource(Utils.getQurekaBannerAds());
        layout2.findViewById(R.id.rlBanner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               quiz_click(context);
            }
        });

        Ids_Class.makeMeBlink(playNowLL);
        lnr_view.removeAllViews();
        lnr_view.addView(layout2);
    }

  /*  public static void getquiz_banner_Ads(Context context, LinearLayout lnr_view) {
        View layout2 = LayoutInflater.from(context).inflate(R.layout.custom_quiz_native_banner_layout, lnr_view, false);

        Button btn_close = (Button) layout2.findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        lnr_view.removeAllViews();
        lnr_view.addView(layout2);
        Ads_Adapter_List.admob_nativehashmap.put(Ads_Adapter_List.pos, layout2);
    }*/


    public static Dialog appopendialog;
    public static LinearLayout ll_continue;
    public static RelativeLayout rl_qureka;

    public static void getquiz_AppOpen_Ads(Context activity) {
        //Log.d("OpenAppAds12", "getAddOpenQurekha 2 ");
        if (appopendialog != null)
            if (appopendialog.isShowing())
                appopendialog.dismiss();
        appopendialog = new Dialog(activity);
        appopendialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        appopendialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        appopendialog.setContentView(R.layout.custom_quiz_dialog_appopen_layout);
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        final int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        appopendialog.getWindow().setLayout(width, height);
        appopendialog.setCancelable(false);
        appopendialog.show();
        Ads_AppOpen.isShowingAd = true;
        ll_continue = appopendialog.findViewById(R.id.ll_continue);
        rl_qureka = appopendialog.findViewById(R.id.rl_qureka);

        GifImageView gif_app_open = appopendialog.findViewById(R.id.gif_app_open);
        TextView tv_header_text = appopendialog.findViewById(R.id.tv_header_text);
        ImageView iv_qureka_img = appopendialog.findViewById(R.id.iv_qureka_img);
        int random = new Random().nextInt(3);
        if (random == 1) {
            tv_header_text.setText("Play Tech Quiz And Win Cash");
            iv_qureka_img.setBackgroundResource(R.drawable.open2);
            gif_app_open.setBackgroundResource(R.drawable.round2);
        } else if (random == 2) {
            tv_header_text.setText("Play GK Quiz And Win Cash");
            iv_qureka_img.setBackgroundResource(R.drawable.open3);
            gif_app_open.setBackgroundResource(R.drawable.round3);
        } else {
            tv_header_text.setText("History Quiz And Win Cash");
            iv_qureka_img.setBackgroundResource(R.drawable.open5);
            gif_app_open.setBackgroundResource(R.drawable.round5);
        }

        Button btn_play_now = appopendialog.findViewById(R.id.btn_play_now);
        Button btn_play_now1 = appopendialog.findViewById(R.id.btn_play_now1);
        TextView txt_top_ads = appopendialog.findViewById(R.id.txt_top_ads);
        TextView txt_bottom_ads = appopendialog.findViewById(R.id.txt_bottom_ads);
        LinearLayout lnr_sec_main = appopendialog.findViewById(R.id.lnr_sec_main);


        try {
           // lnr_sec_main.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.ads_native_bg_color)));
            btn_play_now1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.ads_native_btn_color)));
            btn_play_now1.setTextColor(Color.parseColor(Ids_Class.ads_native_text_color));
            btn_play_now.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.ads_native_btn_color)));
            btn_play_now.setTextColor(Color.parseColor(Ids_Class.ads_native_text_color));
          /*  txt_top_ads.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.ads_native_btn_color)));
            txt_top_ads.setTextColor(Color.parseColor(Ids_Class.ads_native_text_color));
            txt_bottom_ads.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.ads_native_btn_color)));
            txt_bottom_ads.setTextColor(Color.parseColor(Ids_Class.ads_native_text_color));
            tv_header_text.setTextColor(Color.parseColor(Ids_Class.ads_native_btn_color));*/
        } catch (NullPointerException n) {
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        btn_play_now1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quiz_click(activity);
            }
        });

        ll_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ads_AppOpen.isShowingAd = false;
                appopendialog.dismiss();
            }
        });


        btn_play_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quiz_click(activity);
            }
        });

        rl_qureka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quiz_click(activity);
            }
        });

        appopendialog.setOnKeyListener(new Dialog.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    Ads_AppOpen.isShowingAd = false;
                    appopendialog.dismiss();
                }
                return true;
            }
        });


        Ids_Class.makeMeBlink(btn_play_now);
        //Log.d("OpenAppAds12", "getAddOpenQurekha 3 ");

    }



   /* public static void getquiz_AppOpen_Ads(Context context) {
        Dialog dialog = new Dialog(context, android.R.style.Theme_Light);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.QuerekaInter;
        dialog.setContentView(R.layout.custom_quiz_appopen_layout);

        Button btn_close = (Button) dialog.findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setOnKeyListener(new Dialog.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    Ads_AppOpen.isShowingAd = false;
                    dialog.dismiss();
                }
                return true;
            }
        });

        dialog.show();
    }*/

    public static void getquiz_AppOpen_SplashAds(Context activity, Ads_SplashAppOpen.OnFinishAds onFinishAds) {
        //Log.d("OpenAppAds12", "getAddOpenQurekha 2 ");
        if (appopendialog != null)
            if (appopendialog.isShowing())
                appopendialog.dismiss();
        appopendialog = new Dialog(activity);
        appopendialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        appopendialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        appopendialog.setContentView(R.layout.custom_quiz_dialog_appopen_layout);
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        final int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        appopendialog.getWindow().setLayout(width, height);
        appopendialog.setCancelable(false);
        appopendialog.show();
        Ads_AppOpen.isShowingAd = true;
        ll_continue = appopendialog.findViewById(R.id.ll_continue);
        rl_qureka = appopendialog.findViewById(R.id.rl_qureka);

        GifImageView gif_app_open = appopendialog.findViewById(R.id.gif_app_open);
        TextView tv_header_text = appopendialog.findViewById(R.id.tv_header_text);
        ImageView iv_qureka_img = appopendialog.findViewById(R.id.iv_qureka_img);
        int random = new Random().nextInt(3);
        if (random == 1) {
            tv_header_text.setText("Play Tech Quiz And Win Cash");
            iv_qureka_img.setBackgroundResource(R.drawable.open2);
            gif_app_open.setBackgroundResource(R.drawable.round2);
        } else if (random == 2) {
            tv_header_text.setText("Play GK Quiz And Win Cash");
            iv_qureka_img.setBackgroundResource(R.drawable.open3);
            gif_app_open.setBackgroundResource(R.drawable.round3);
        } else {
            tv_header_text.setText("History Quiz And Win Cash");
            iv_qureka_img.setBackgroundResource(R.drawable.open5);
            gif_app_open.setBackgroundResource(R.drawable.round5);
        }


        Button btn_play_now = appopendialog.findViewById(R.id.btn_play_now);
        Button btn_play_now1 = appopendialog.findViewById(R.id.btn_play_now1);
        TextView txt_top_ads = appopendialog.findViewById(R.id.txt_top_ads);
        TextView txt_bottom_ads = appopendialog.findViewById(R.id.txt_bottom_ads);
        LinearLayout lnr_sec_main = appopendialog.findViewById(R.id.lnr_sec_main);


        try {
         //   lnr_sec_main.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.ads_native_bg_color)));
            btn_play_now1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.ads_native_btn_color)));
            btn_play_now1.setTextColor(Color.parseColor(Ids_Class.ads_native_text_color));
            btn_play_now.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.ads_native_btn_color)));
            btn_play_now.setTextColor(Color.parseColor(Ids_Class.ads_native_text_color));
            /*txt_top_ads.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.ads_native_btn_color)));
            txt_top_ads.setTextColor(Color.parseColor(Ids_Class.ads_native_text_color));
            txt_bottom_ads.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Ids_Class.ads_native_btn_color)));
            txt_bottom_ads.setTextColor(Color.parseColor(Ids_Class.ads_native_text_color));
            tv_header_text.setTextColor(Color.parseColor(Ids_Class.ads_native_btn_color));*/
        } catch (NullPointerException n) {
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        btn_play_now1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quiz_click(activity);
            }
        });

        ll_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ads_AppOpen.isShowingAd = false;
                appopendialog.dismiss();
                onFinishAds.onFinishAds(true);
            }
        });


        btn_play_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quiz_click(activity);
            }
        });

        rl_qureka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quiz_click(activity);
            }
        });

        appopendialog.setOnKeyListener(new Dialog.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    Ads_AppOpen.isShowingAd = false;
                    appopendialog.dismiss();
                    onFinishAds.onFinishAds(true);
                }
                return true;
            }
        });


        Ids_Class.makeMeBlink(btn_play_now);
        //Log.d("OpenAppAds12", "getAddOpenQurekha 3 ");

    }

    /*public static void getquiz_AppOpen_SplashAds(Context context, Ads_SplashAppOpen.OnFinishAds onFinishAds) {
        Dialog dialog = new Dialog(context, android.R.style.Theme_Light);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.QuerekaInter;
        dialog.setContentView(R.layout.custom_quiz_appopen_layout);

        Button btn_close = (Button) dialog.findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                onFinishAds.onFinishAds(true);
            }
        });

        dialog.setOnKeyListener(new Dialog.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    Ads_AppOpen.isShowingAd = false;
                    dialog.dismiss();
                    onFinishAds.onFinishAds(true);
                }
                return true;
            }
        });

        dialog.show();
    }*/

    public static void quiz_click(Context context){
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            Bundle bundle = new Bundle();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                bundle.putBinder("android.support.customtabs.extra.SESSION", (IBinder) null);
            }
            intent.putExtras(bundle);
            intent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", context.getResources().getColor(R.color.purple_500));
            intent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", true);
            intent.setPackage("com.android.chrome");
            intent.setData(Uri.parse(Ids_Class.quizLink));
            context.startActivity(intent, (Bundle) null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
