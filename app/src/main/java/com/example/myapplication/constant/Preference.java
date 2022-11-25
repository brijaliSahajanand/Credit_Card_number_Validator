package com.example.myapplication.constant;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapplication.Appcontroller;


public class Preference {
    private static final String recentvideo = "recentvideo";
    private static final String playlist = "playlist";
    public static String getrecentvideo() {
        return get().getString(recentvideo, "");
    }
    public static void setrecentvideo(String value) {
        get().edit().putString(recentvideo, value).apply();
    }
    public static String getplaylist() {
        return get().getString(playlist, "");
    }
    public static void setplaylist(String value) {
        get().edit().putString(playlist, value).apply();
    }
    private static final String ads_click = "ads_click"; //i
    private static final String maintenance = "maintenance";  //i
    private static final String back_click = "back_click";  //i
    private static final String ads_open_time = "ads_open_time";  //i
    private static final String ads_click_time = "ads_click_time";  //i
    private static final String admob_native_two = "admob_native_two";
    private static final String _id = "_id";
    private static final String Package = "package";
    private static final String admob_interstitial_id = "admob_interstitial_id";
    private static final String admob_native_id = "admob_native_id";
    private static final String admob_banner_id = "admob_banner_id";
    private static final String version_name = "version_name";
    private static final String version_code = "version_code";
    private static final String app_msg = "app_msg";
    private static final String update_url = "update_url";
    private static final String is_update = "is_update";
    private static final String ads_type = "ads_type";
    private static final String splash_ads_type = "splash_ads_type";
    private static final String link = "link";
    private static final String privacy_policy = "privacy_policy";
    private static final String app_open_id = "app_open_id";
    private static final String is_live = "is_live";
    private static final String qureka_show = "qureka_show";
    private static final String BASE_URL = "BASE_URL";
    private static final String THEME_POSITION = "theme position";

    private static final String howtoshow = "howtoshow";
    private static final String percentage = "percentage";
    private static final String aura_user_id = "aura_user_id";
    private static final String accessToken = "accessToken";
    private static final String VPNTime_date = "VPNTime_date";
    private static final String server_short = "server_short";
    private static final String server_name = "server_name";
    private static final String server_image = "server_image";
    private static final String letsgoscreen = "letsgoscreen";
    private static final String nextscreen = "nextscreen";
    private static final String privacy_policy_html = "privacy_policy_html";
    private static final String Whatsapp_URI = "Whatsapp_URI";
    private static final String StateNameVN = "StateNameVN";
    private static final String CountryNmeVN = "CountryNmeVN";
    private static final String CityNmeVN = "CityNmeVN";
    private static final String VN_Show = "VN_Show";
    private static final String appOpen_click = "appOpen_click";
    private static final String appOpen_back_click = "appOpen_back_click";
    private static final String appOpen_inter_show = "appOpen_inter_show";
    private static final String vnid = "vpnid";
    private static final String vnpassword = "vnpassword";
    private static final String isVnConnect = "isVnConnect";
    private static final String native_button_color = "native_button_color";
    private static final String native_btn_text_color = "native_btn_text_color";
    private static final String comingsoon = "comingsoon";
    private static final String qureka_header_show = "qureka_header_show";
    private static final String isBottom_native = "isBottom_native";
    private static final String islivetv = "islivetv";
    private static final String screenshow = "screenshow";
    private static final String livetvurl = "livetvurl ";
    private static final String rendomserver = "rendomserver";

    private static final String isVpnConnect = "isVpnConnect";
    private static final String native_by_page = "native_by_page";
    private static final String native_array_int = "native_array_int";
    private static final String nativeBanner_array_int = "nativeBanner_array_int";
    private static final String fullads_array_int = "fullads_array_int";
    private static final String OpenAds_array_int = "OpenAds_array_int";
    private static final String AdaptiveBanner_array_int = "AdaptiveBanner_array_int";
    private static final String admob_page = "admob_page";
    private static final String inter_admob = "inter_admob";
    private static final String native_admob = "native_admob";
    private static final String banner_admob = "banner_qureka";
    private static final String admob_load_type = "admob_load_type";
    private static final String ads_open_admob = "ads_open_admob";
    private static final String inter_back_admob = "inter_back_admob";
    private static final String user_name = "user_name";
    private static final String profile_url = "profile_url";
    private static final String is_big_native_qureka = "is_big_native_qureka";
    private static final String is_small_native_qureka = "is_small_native_qureka";
    private static final String buttonanimate = "buttonanimate";
    private static final String animationtype = "animationtype";
    private static final String bottomads_type = "bottomads_type";
    private static final String vn_header_show = "vn_header_show";
    private static final String fb_native_id = "fb_native_id";
    private static final String fb_interstitial_id = "fb_interstitial_id";
    private static final String fb_native_banner_id = "fb_native_banner_id";
    private static final String VPN_Show = "VPN_Show";
    private static final String Click_Long = "Click_Long";
    private static final String vpn_direct_connect = "vpn_direct_connect";
    private static final String admob_native_content_text_color = "admob_native_content_text_color";
    private static final String admob_native_bg_color = "admob_native_bg_color";
    private static final String fb_native_btn_color = "fb_native_btn_color";
    private static final String fb_native_btn_text_color = "fb_native_btn_text_color";
    private static final String fb_native_content_text_color = "fb_native_content_text_color";
    private static final String fb_native_bg_color = "fb_native_bg_color";
    private static final String admob_reward_id = "admob_reward_id";
    private static final String user_img_uri = "user_img_uri";


    public static void isFirstTime(boolean value) {
        get().edit().putBoolean(user_img_uri, value).apply();
    }

    public static boolean getFirstTime() {
        return get().getBoolean(user_img_uri, false);
    }

    public static void setuser_name(String value) {
        get().edit().putString(user_name, value).apply();
    }

    public static String getuser_name() {
        return get().getString(user_name, "");
    }
    public static void setadmob_reward_id(String value) {
        get().edit().putString(admob_reward_id, value).apply();
    }

    public static String getadmob_reward_id() {
        return get().getString(admob_reward_id, "");
    }

    public static void setadmob_native_content_text_color(String value) {
        get().edit().putString(admob_native_content_text_color, value).apply();
    }

    public static String getadmob_native_content_text_color() {
        return get().getString(admob_native_content_text_color, "");
    }

    public static void setadmob_native_bg_color(String value) {
        get().edit().putString(admob_native_bg_color, value).apply();
    }

    public static String getadmob_native_bg_color() {
        return get().getString(admob_native_bg_color, "");
    }

    public static void setfb_native_btn_color(String value) {
        get().edit().putString(fb_native_btn_color, value).apply();
    }

    public static String getfb_native_btn_color() {
        return get().getString(fb_native_btn_color, "");
    }

    public static void setfb_native_btn_text_color(String value) {
        get().edit().putString(fb_native_btn_text_color, value).apply();
    }

    public static String getfb_native_btn_text_color() {
        return get().getString(fb_native_btn_text_color, "");
    }

    public static void setfb_native_content_text_color(String value) {
        get().edit().putString(fb_native_content_text_color, value).apply();
    }

    public static String getfb_native_content_text_color() {
        return get().getString(fb_native_content_text_color, "");
    }

    public static void setfb_native_bg_color(String value) {
        get().edit().putString(fb_native_bg_color, value).apply();
    }

    public static String getfb_native_bg_color() {
        return get().getString(fb_native_bg_color, "");
    }

    public static void setFb_native_banner_id(String value) {
        get().edit().putString(fb_native_banner_id, value).apply();
    }

    public static String getFb_native_banner_id() {
        return get().getString(fb_native_banner_id, "");
    }

    private static final String Vn_direct_connect = "Vn_direct_connect";
    private static final String ad_one_by_one_ids = "ad_one_by_one_ids";


    public static void setVn_direct_connect(boolean value) {
        get().edit().putBoolean(Vn_direct_connect, value).apply();
    }

    public static boolean getVn_direct_connect() {
        return get().getBoolean(Vn_direct_connect, false);
    }



    public static void setAd_one_by_one(boolean value) {
        get().edit().putBoolean(ad_one_by_one_ids, value).apply();
    }

    public static boolean getAd_one_by_one() {
        return get().getBoolean(ad_one_by_one_ids, false);
    }






    public static void setClick_Long(boolean value) {
        get().edit().putBoolean(Click_Long, value).apply();
    }

    public static boolean getClick_Long() {
        return get().getBoolean(Click_Long, true);
    }


    public static void setFb_interstitial_id(String value) {
        get().edit().putString(fb_interstitial_id, value).apply();
    }

    public static String getFb_interstitial_id() {
        return get().getString(fb_interstitial_id, "");
    }


    public static void setVPN_Show(boolean value) {
        get().edit().putBoolean(VPN_Show, value).apply();
    }

    public static boolean getVPN_Show() {
        return get().getBoolean(VPN_Show, false);
    }

    public static void setFb_native_id(String value) {
        get().edit().putString(fb_native_id, value).apply();
    }

    public static String getFb_native_id() {
        return get().getString(fb_native_id, "");
    }

    public static boolean getisVpnConnect() {
        return get().getBoolean(isVpnConnect, false);
    }

    public static void setisVpnConnect(boolean value) {
        get().edit().putBoolean(isVpnConnect, value).apply();
    }



    public static void setBottomads_type(String value) {
        get().edit().putString(bottomads_type, value).apply();
    }

    public static String getBottomads_type() {
        return get().getString(bottomads_type, "nativebanner");
    }

    public static void setVn_header_show(boolean value) {
        get().edit().putBoolean(vn_header_show, value).apply();
    }

    public static boolean getVn_header_show() {
        return get().getBoolean(vn_header_show, false);
    }

    public static void setNative_by_page(Integer value) {
        get().edit().putInt(native_by_page, value).apply();
    }

    public static Integer getNative_by_page() {
        return get().getInt(native_by_page, 1);
    }


    public static void setbuttonanimate(boolean value) {
        get().edit().putBoolean(buttonanimate, value).apply();
    }

    public static boolean getbuttonanimate() {
        return get().getBoolean(buttonanimate, false);
    }

    public static void setanimationtype(String value) {
        get().edit().putString(animationtype, value).apply();
    }

    public static String getanimationtype() {
        return get().getString(animationtype, "alpha");
    }


    public static void setIs_big_native_qureka(boolean value) {
        get().edit().putBoolean(is_big_native_qureka, value).apply();
    }

    public static boolean getIs_big_native_qureka() {
        return get().getBoolean(is_big_native_qureka, false);
    }


    public static void setIs_small_native_qureka(boolean value) {
        get().edit().putBoolean(is_small_native_qureka, value).apply();
    }

    public static boolean getIs_small_native_qureka() {
        return get().getBoolean(is_small_native_qureka, false);
    }


    public static String getProfile_url() {
        return get().getString(profile_url, "");
    }

    public static void setProfile_url(String value) {
        get().edit().putString(profile_url, value).apply();
    }

    public static String getUser_name() {
        return get().getString(user_name, "");
    }

    public static void setUser_name(String value) {
        get().edit().putString(user_name, value).apply();
    }


    public static void setInter_back_admob(Integer value) {
        get().edit().putInt(inter_back_admob, value).apply();
    }

    public static Integer getInter_back_admob() {
        return get().getInt(inter_back_admob, 3);
    }


    public static void setAds_open_admob(Integer value) {
        get().edit().putInt(ads_open_admob, value).apply();
    }

    public static Integer getAds_open_admob() {
        return get().getInt(ads_open_admob, 3);
    }



    public static void setbanner_admob(Integer value) {
        get().edit().putInt(banner_admob, value).apply();
    }

    public static Integer getbanner_admob() {
        return get().getInt(banner_admob, 3);
    }


    public static void setnative_admob(Integer value) {
        get().edit().putInt(native_admob, value).apply();
    }

    public static Integer getnative_admob() {
        return get().getInt(native_admob, 3);
    }


    public static void setinter_admob(Integer value) {
        get().edit().putInt(inter_admob, value).apply();
    }

    public static Integer getinter_admob() {
        return get().getInt(inter_admob, 3);
    }

    public static void setAdmob_page(boolean value) {
        get().edit().putBoolean(admob_page, value).apply();
    }

    public static boolean getAdmob_page() {
        return get().getBoolean(admob_page, false);
    }


    public static Integer getFulladsArrayINT() {
        return get().getInt(fullads_array_int, 0);
    }

    public static void setFulladsArrayINT(int ad_type) {
        get().edit().putInt(fullads_array_int, ad_type).apply();
    }


    public static Integer getAdaptiveBannerArrayINT() {
        return get().getInt(AdaptiveBanner_array_int, 0);
    }

    public static void setAdaptiveBannerArrayINT(int ad_type) {
        get().edit().putInt(AdaptiveBanner_array_int, ad_type).apply();
    }


    public static Integer getOpenAdsArrayINT() {
        return get().getInt(OpenAds_array_int, 0);
    }

    public static void setOpenAdsArrayINT(int ad_type) {
        get().edit().putInt(OpenAds_array_int, ad_type).apply();
    }


    public static Integer getNativeArrayINT() {
        return get().getInt(native_array_int, 0);
    }

    public static void setNativeArrayINT(int ad_type) {
        get().edit().putInt(native_array_int, ad_type).apply();
    }

    public static Integer getNativeBannerArrayINT() {
        return get().getInt(nativeBanner_array_int, 0);
    }

    public static void setNativeBannerArrayINT(int ad_type) {
        get().edit().putInt(nativeBanner_array_int, ad_type).apply();
    }


    public static String getVnid() {
        return get().getString(vnid, "no_vpn");
    }

    public static void setVnid(String value) {
        get().edit().putString(vnid, value).apply();
    }


    public static String getVnpassword() {
        return get().getString(vnpassword, "ZEziW6v0O3");
    }

    public static void setVnpassword(String value) {
        get().edit().putString(vnpassword, value).apply();
    }


    public static void setisBottom_native(boolean value) {
        get().edit().putBoolean(isBottom_native, value).apply();
    }

    public static boolean getisBottom_native() {
        return get().getBoolean(isBottom_native, false);
    }

    public static void setscreenshow(Integer value) {
        get().edit().putInt(screenshow, value).apply();
    }

    public static Integer getscreenshow() {
        return get().getInt(screenshow, 1);
    }

    public static void setislivetv(boolean value) {
        get().edit().putBoolean(islivetv, value).apply();
    }

    public static boolean getislivetv() {
        return get().getBoolean(islivetv, false);
    }

    public static void setqureka_header_show(boolean value) {
        get().edit().putBoolean(qureka_header_show, value).apply();
    }

    public static boolean getQureka_header_show() {
        return get().getBoolean(qureka_header_show, false);
    }

    public static void setcomingsoon(boolean value) {
        get().edit().putBoolean(comingsoon, value).apply();
    }

    public static boolean getcomingsoon() {
        return get().getBoolean(comingsoon, false);
    }

    public static void setlivetvurl(String value) {
        get().edit().putString(livetvurl, value).apply();
    }

    public static String getlivetvurl() {
        return get().getString(livetvurl, "#ffffff");
    }

    public static void setnative_button_color(String value) {
        get().edit().putString(native_button_color, value).apply();
    }

    public static String getnative_button_color() {
        return get().getString(native_button_color, "#ffffff");
    }

    public static void setnative_btn_text_color(String value) {
        get().edit().putString(native_btn_text_color, value).apply();
    }

    public static String getnative_btn_text_color() {
        return get().getString(native_btn_text_color, "#ffffff");
    }

    private static SharedPreferences get() {
        return Appcontroller.getApp().getSharedPreferences(
                "CreditcradApp1", Context.MODE_PRIVATE);

    }


    public static void setisVnConnect(boolean value) {
        get().edit().putBoolean(isVnConnect, value).apply();
    }

    public static boolean getisVnConnect() {
        return get().getBoolean(isVnConnect, false);
    }


    public static void setAppOpen_click(int value) {
        get().edit().putInt(appOpen_click, value).apply();
    }

    public static int getAppOpen_click() {
        return get().getInt(appOpen_click, 0);
    }

    public static void setAppOpen_back_click(int value) {
        get().edit().putInt(appOpen_back_click, value).apply();
    }

    public static int getAppOpen_back_click() {
        return get().getInt(appOpen_back_click, 0);
    }


    public static void setAppOpen_inter_show(boolean value) {
        get().edit().putBoolean(appOpen_inter_show, value).apply();
    }

    public static boolean getAppOpen_inter_show() {
        return get().getBoolean(appOpen_inter_show, false);
    }

    public static int getpercentage() {
        return get().getInt(percentage, 0);
    }

    public static void setpercentage(int token) {
        get().edit().putInt(percentage, token).apply();
    }

    public static boolean gethowtoshow() {
        return get().getBoolean(howtoshow, true);
    }

    public static void sethowtoshow(boolean token) {
        get().edit().putBoolean(howtoshow, token).apply();
    }

    public static Integer getTHEME_POSITION() {
        return get().getInt(THEME_POSITION, 0);
    }

    public static void setTHEME_POSITION(Integer token) {
        get().edit().putInt(THEME_POSITION, token).apply();
    }

    public static String getBASE_URL() {
        return get().getString(BASE_URL, "");
    }

    public static void setBASE_URL(String token) {
        get().edit().putString(BASE_URL, token).apply();
    }


    public static void setqureka_show(boolean value) {
        get().edit().putBoolean(qureka_show, value).apply();
    }

    public static boolean getqureka_show() {
        return get().getBoolean(qureka_show, false);
    }


    public static void setapp_open_id(String value) {
        get().edit().putString(app_open_id, value).apply();
    }

    public static String getapp_open_id() {
        return get().getString(app_open_id, "");
    }

    public static void setprivacy_policy(String value) {
        get().edit().putString(privacy_policy, value).apply();
    }

    public static String getprivacy_policy() {
        return get().getString(privacy_policy, "");
    }

    public static void setlink(String value) {
        get().edit().putString(link, value).apply();
    }

    public static String getlink() {
        return get().getString(link, "");
    }

    public static void setsplash_ads_type(String value) {
        get().edit().putString(splash_ads_type, value).apply();
    }

    public static String getsplash_ads_type() {
        return get().getString(splash_ads_type, "");
    }

    public static void setads_type(String value) {
        get().edit().putString(ads_type, value).apply();
    }

    public static String getads_type() {
        return get().getString(ads_type, "");
    }

    public static void setis_update(boolean value) {
        get().edit().putBoolean(is_update, value).apply();
    }

    public static boolean getis_update() {
        return get().getBoolean(is_update, false);
    }

    public static void setupdate_url(String value) {
        get().edit().putString(update_url, value).apply();
    }

    public static String getupdate_url() {
        return get().getString(update_url, "");
    }

    public static void setapp_msg(String value) {
        get().edit().putString(app_msg, value).apply();
    }

    public static String getapp_msg() {
        return get().getString(app_msg, "");
    }



    public static void setversion_name(String value) {
        get().edit().putString(version_name, value).apply();
    }

    public static String getversion_name() {
        return get().getString(version_name, "");
    }

   /* public static void setAdmob_banner(String value) {
        get().edit().putString(admbo_banner, value).apply();
    }

    public static String getAdmbo_banner() {
        return get().getString(admbo_banner, "");
    }


    public static void setid(int value) {
        get().edit().putInt(id, value).apply();
    }

    public static int getid() {
        return get().getInt(id, 0);
    }*/


    //for int
    public static void setads_click(int value) {
        get().edit().putInt(ads_click, value).apply();
    }

    public static int getads_click() {
        return get().getInt(ads_click, 0);
    }

    public static void setmaintenance(Boolean value) {
        get().edit().putBoolean(maintenance, value).apply();
    }

    public static Boolean getmaintenance() {
        return get().getBoolean(maintenance, false);
    }


    public static void setback_click(int value) {
        get().edit().putInt(back_click, value).apply();
    }

    public static int getback_click() {
        return get().getInt(back_click, 0);
    }


    public static void setads_open_time(int value) {
        get().edit().putInt(ads_open_time, value).apply();
    }

    public static int getads_open_time() {
        return get().getInt(ads_open_time, 0);
    }

    public static void setads_click_time(int value) {
        get().edit().putInt(ads_click_time, value).apply();
    }

    public static int getads_click_time() {
        return get().getInt(ads_click_time, 0);
    }


    public static void setRendomserver(boolean value) {
        get().edit().putBoolean(rendomserver, value).apply();
    }

    public static boolean getRendomserver() {
        return get().getBoolean(rendomserver, false);
    }


    //for String
    public static void setadmob_native_two(String value) {
        get().edit().putString(admob_native_two, value).apply();
    }

    public static String getadmob_native_two() {
        return get().getString(admob_native_two, "");
    }


    public static void set_id(String value) {
        get().edit().putString(_id, value).apply();
    }

    public static String get_id() {
        return get().getString(_id, "");
    }

    public static void setPackage(String value) {
        get().edit().putString(Package, value).apply();
    }

    public static String getPackage() {
        return get().getString(Package, "");
    }

    public static void setadmob_interstitial_id(String value) {
        get().edit().putString(admob_interstitial_id, value).apply();
    }

    public static String getadmob_interstitial_id() {
        return get().getString(admob_interstitial_id, "");
    }

    public static void setadmob_native_id(String value) {
        get().edit().putString(admob_native_id, value).apply();
    }

    public static String getadmob_native_id() {
        return get().getString(admob_native_id, "");
    }

    public static void setadmob_banner_id(String value) {
        get().edit().putString(admob_banner_id, value).apply();
    }

    public static String getadmob_banner_id() {
        return get().getString(admob_banner_id, "");
    }


    public static void setAura_user_id(long value) {
        get().edit().putLong(aura_user_id, value).apply();
    }

    public static long getAura_user_id() {
        return get().getLong(aura_user_id, 0);
    }

    public static void setAccessToken(String value) {
        get().edit().putString(accessToken, value).apply();
    }

    public static String getAccessToken() {
        return get().getString(accessToken, "");
    }

    public static void setVPNTime_date(String value) {
        get().edit().putString(VPNTime_date, value).apply();
    }

    public static String getVPNTime_date() {
        return get().getString(VPNTime_date, "");
    }

    public static void set_server_short(String value) {
        get().edit().putString(server_short, value).apply();
    }

    public static String getServer_short() {
        return get().getString(server_short, "US");
    }


    public static void setserver_name(String value) {
        get().edit().putString(server_name, value).apply();
    }

    public static String getserver_name() {
        return get().getString(server_name, "");
    }

    public static void setServer_image(String value) {
        get().edit().putString(server_image, value).apply();
    }

    public static String getServer_image() {
        return get().getString(server_image, "");
    }

    public static void setLetsgoscreen(boolean value) {
        get().edit().putBoolean(letsgoscreen, value).apply();
    }

    public static boolean getLetsgoscreen() {
        return get().getBoolean(letsgoscreen, false);
    }

    public static void setNextscreen(boolean value) {
        get().edit().putBoolean(nextscreen, value).apply();
    }

    public static boolean getNextscreen() {
        return get().getBoolean(nextscreen, false);
    }

//    public static void setRendomserver(boolean value) {
//        get().edit().putBoolean(rendomserver, value).apply();
//    }
//
//    public static boolean getRendomserver() {
//        return get().getBoolean(rendomserver, false);
//    }


    public static void setPrivacy_policy_html(String value) {
        get().edit().putString(privacy_policy_html, value).apply();
    }

    public static String getPrivacy_policy_html() {
        return get().getString(privacy_policy_html, "");
    }


    public static void setWhatsapp_URI(String value) {
        get().edit().putString(Whatsapp_URI, value).apply();
    }

    public static String getWhatsapp_URI() {
        return get().getString(Whatsapp_URI, "");
    }

    public static void setStateNameVN(String value) {
        get().edit().putString(StateNameVN, value).apply();
    }

    public static String getStateNameVN() {
        return get().getString(StateNameVN, "");
    }

    public static void setCountryNmeVN(String value) {
        get().edit().putString(CountryNmeVN, value).apply();
    }

    public static String getCountryNmeVN() {
        return get().getString(CountryNmeVN, "");
    }

    public static void setCityNmeVN(String value) {
        get().edit().putString(CityNmeVN, value).apply();
    }

    public static String getCityNmeVN() {
        return get().getString(CityNmeVN, "");
    }







    //for App----------------------------------------------------------------------
    private static final String Profile_pic = "Profile_pic";
    private static final String profile_user_name = "profile_user_name";
    private static final String insta_login = "insta_login";
    private static final String wt_show = "wt_show";
    private static final String intro_show = "intro_show";

    //new For Share
    private static final String Sort_Video = "Sort_Video";
    private static final String Sort_Type_video = "getSort_Type_video";
    private static final String Sort_Music = "Sort_Music";
    private static final String Sort_Type_Music = "Sort_Type_Music";
    private static final String Sort_Image = "Sort_Image";
    private static final String Sort_Type_Image = "Sort_Type_Image";
    private static final String Sort_Apk = "Sort_Apk";
    private static final String Sort_Type_Apk = "Sort_Type_Apk";
    private static final String gift_view_show = "gift_view_show";

    public static void setgift_view_show(boolean value) {
        get().edit().putBoolean(gift_view_show, value).apply();
    }

    public static boolean getgift_view_show() {
        return get().getBoolean(gift_view_show, false);
    }


    public static void setprofile_user_name(String value) {
        get().edit().putString(profile_user_name, value).apply();
    }

    public static String getprofile_user_name() {
        return get().getString(profile_user_name, "");
    }

    public static void setSort_Apk(String value) {
        get().edit().putString(Sort_Apk, value).apply();
    }

    public static String getSort_Apk() {
        return get().getString(Sort_Apk, "Name");
    }

    public static void setSort_Type_Apk(String value) {
        get().edit().putString(Sort_Type_Apk, value).apply();
    }

    public static String getSort_Type_Apk() {
        return get().getString(Sort_Type_Apk, "New");
    }

    public static void setSort_Image(String value) {
        get().edit().putString(Sort_Image, value).apply();
    }

    public static String getSort_Image() {
        return get().getString(Sort_Image, "Date");
    }

    public static void setSort_Type_Image(String value) {
        get().edit().putString(Sort_Type_Image, value).apply();
    }

    public static String getSort_Type_Image() {
        return get().getString(Sort_Type_Image, "New");
    }


    public static void setSort_Music(String value) {
        get().edit().putString(Sort_Music, value).apply();
    }

    public static String getSort_Music() {
        return get().getString(Sort_Music, "Date");
    }

    public static void setSort_Type_Music(String value) {
        get().edit().putString(Sort_Type_Music, value).apply();
    }

    public static String getSort_Type_Music() {
        return get().getString(Sort_Type_Music, "New");
    }

    public static void setSort_video(String value) {
        get().edit().putString(Sort_Video, value).apply();
    }

    public static String getSort_video() {
        return get().getString(Sort_Video, "Date");
    }

    public static void setSort_Type_video(String value) {
        get().edit().putString(Sort_Type_video, value).apply();
    }

    public static String getSort_Type_video() {
        return get().getString(Sort_Type_video, "New");
    }





    public static Integer getProfile_pic() {
        return get().getInt(Profile_pic, 1);
    }

    public static void setProfile_pic(int ad_type) {
        get().edit().putInt(Profile_pic, ad_type).apply();
    }




    public static boolean getintro_show() {
        return get().getBoolean(intro_show, false);
    }

    public static void setintro_show(boolean token) {
        get().edit().putBoolean(intro_show, token).apply();
    }

    public static boolean getInsta_login() {
        return get().getBoolean(insta_login, false);
    }

    public static void setInsta_login(boolean token) {
        get().edit().putBoolean(insta_login, token).apply();
    }



    public static void setwt_show(boolean value) {
        get().edit().putBoolean(wt_show, value).apply();
    }

    public static boolean getwt_show() {
        return get().getBoolean(wt_show, true);
    }
}
