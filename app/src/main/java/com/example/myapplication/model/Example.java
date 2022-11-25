package com.example.myapplication.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {

    @SerializedName("package")
    @Expose
    private Package _package;

    @SerializedName("countrylist")
    @Expose
    public List<CountryList> countryList = null;

    @SerializedName("privacypolicy")
    @Expose
    public String privacypolicy = null;

    @SerializedName("DATE")
    public String DATE;

    @SerializedName("vn_show")
    public boolean vn_show;

    public Package getPackage() {
        return _package;
    }

    public void setPackage(Package _package) {
        this._package = _package;
    }

    public class Package {

        @SerializedName("ads_click")
        @Expose
        private Integer adsClick;
        @SerializedName("maintenance")
        @Expose
        private Boolean maintenance;
        @SerializedName("back_click")
        @Expose
        private Integer backClick;
        @SerializedName("ads_open_time")
        @Expose
        private Integer adsOpenTime;
        @SerializedName("ads_click_time")
        @Expose
        private Integer adsClickTime;
        @SerializedName("admob_native_two")
        @Expose
        private String admobNativeTwo;
        @SerializedName("is_quiz")
        @Expose
        private Boolean is_quiz;
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("package")
        @Expose
        private String _package;
        @SerializedName("admob_interstitial_id")
        @Expose
        private String admobInterstitialId;
        @SerializedName("admob_native_id")
        @Expose
        private String admobNativeId;
        @SerializedName("admob_banner_id")
        @Expose
        private String admobBannerId;
        @SerializedName("admob_reward_id")
        @Expose
        public String admob_reward_id;
        @SerializedName("version_name")
        @Expose
        private String versionName;

        @SerializedName("app_msg")
        @Expose
        private String appMsg;

        @SerializedName("version_code")
        public String version_code;

        @SerializedName("update_coming_soon")
        public boolean update_coming_soon;


        @SerializedName("update_url")
        @Expose
        private String updateUrl;
        @SerializedName("is_update")
        @Expose
        private boolean isUpdate;
        @SerializedName("ads_type")
        @Expose
        private String adsType;
        @SerializedName("splash_ads_type")
        @Expose
        private String splashAdsType;
        @SerializedName("link")
        @Expose
        private String link;
        @SerializedName("privacy_policy")
        @Expose
        private String privacyPolicy;
        @SerializedName("app_open_id")
        @Expose
        private String appOpenId;

        @SerializedName("vn_id")
        @Expose
        public String vnid;
        @SerializedName("vn_pass")
        @Expose
        public String vnpassword;

        @SerializedName("app_open_interstitial_click")
        @Expose
        public Integer app_open_interstitial_click;

        @SerializedName("app_open_back_interstitial_click")
        @Expose
        public Integer app_open_back_interstitial_click;

        @SerializedName("app_open_interstitial_show")
        @Expose
        public boolean app_open_interstitial_show;

        @SerializedName("admob_native_btn_color")
        @Expose
        public String admob_native_btn_color;
        @SerializedName("admob_native_btn_text_color")
        @Expose
        public String admob_native_btn_text_color;
        @SerializedName("quiz_header_show")
        public boolean quiz_header_show;
        @SerializedName("coming_soon")
        public boolean comingsoon;
        @SerializedName("bottom_ads_type")
        @Expose
        public String bottom_ads_type;
        @SerializedName("admob_native_id_list")
        @Expose
        private List<String> admob_native_id_list = null;
        @SerializedName("admob_bottom_native_id_list")
        @Expose
        private List<String> admob_bottom_native_id_list = null;
        @SerializedName("admob_interstitial_id_list")
        @Expose
        private List<String> admob_interstitial_id_list = null;
        @SerializedName("app_open_id_list")
        @Expose
        private List<String> app_open_id_list = null;
        @SerializedName("admob_banner_id_list")
        @Expose
        private List<String> admob_banner_id_list = null;
        @SerializedName("is_start_with_zero")
        @Expose
        public boolean is_start_with_zero;
        @SerializedName("admob_page")
        @Expose
        public Boolean admob_page;
        @SerializedName("inter_admob_click")
        @Expose
        public Integer inter_admob_click;
        @SerializedName("native_admob_click")
        @Expose
        public Integer native_admob_click;
        @SerializedName("banner_admob_click")
        @Expose
        public Integer banner_admob_click;
        @SerializedName("ads_open_admob_click")
        @Expose
        public Integer ads_open_admob_click;
        @SerializedName("inter_back_admob_click")
        @Expose
        public Integer inter_back_admob_click;

        @SerializedName("random_server")
        @Expose
        public boolean random_server;

        @SerializedName("country_name")
        @Expose
        public String country_name;

        @SerializedName("country_code")
        @Expose
        public String country_code;

        @SerializedName("images_url")
        @Expose
        public String images_url;

        @SerializedName("gift_view_show")
        @Expose
        public boolean gift_view_show;
        @SerializedName("is_live_tv")
        @Expose
        public boolean is_live_tv;

        @SerializedName("admob_load_type")
        @Expose
        public Boolean admob_load_type;
        @SerializedName("firebaseanalytics")
        @Expose
        public boolean firebaseanalytics;

        @Expose
        @SerializedName("firstcreen")
        public boolean firstscreen;
        @Expose
        @SerializedName("secodscreen")
        public boolean secondscreen;
        @SerializedName("is_big_native_quiz")
        public boolean is_big_native_quiz;
        @SerializedName("is_small_native_quiz")
        public boolean is_small_native_quiz;

        @SerializedName("ads_buttom_animation")
        @Expose
        public boolean ads_buttom_animation;
        @SerializedName("ads_button_animation_type")
        @Expose
        public String ads_button_animation_type;
        @SerializedName("native_by_page")
        @Expose
        public Integer native_by_page;
        @SerializedName("fb_native_id")
        @Expose
        public String fb_native_id;
        @SerializedName("fb_interstitial_id")
        @Expose
        public String fb_interstitial_id;
        @SerializedName("fb_native_banner_id")
        @Expose
        public String fb_native_banner_id;
        @SerializedName("vn_direct_connect")
        @Expose
        public Boolean vn_direct_connect;

        @SerializedName("ad_one_by_one_ids")
        @Expose
        public Boolean ad_one_by_one_ids;


        @SerializedName("vn_header_show")
        @Expose
        private Boolean vnHeaderShow;
        @SerializedName("admob_native_content_text_color")
        @Expose
        public String admob_native_content_text_color;
        @SerializedName("admob_native_bg_color")
        @Expose
        public String admob_native_bg_color;
        @SerializedName("fb_native_btn_color")
        @Expose
        public String fb_native_btn_color;
        @SerializedName("fb_native_btn_text_color")
        @Expose
        public String fb_native_btn_text_color;
        @SerializedName("fb_native_content_text_color")
        @Expose
        public String fb_native_content_text_color;
        @SerializedName("fb_native_bg_color")
        @Expose
        public String fb_native_bg_color;
        @SerializedName("screen_show")
        @Expose
        public int screen_show;

        @SerializedName("telegram_join")
        @Expose
        public Boolean telegram_join;
        @SerializedName("telegramurl")
        @Expose
        public String telegramurl;
        @SerializedName("livetvurl")
        @Expose
        public String livetvurl;

        @SerializedName("interstialshow_time")
        @Expose
        public Integer interstialshow_time;
        @SerializedName("ads_in_video")
        @Expose
        public boolean ads_in_video;


        public String getadmob_native_content_text_color() {
            return admob_native_content_text_color;
        }

        public void setadmob_native_content_text_color(String admob_native_content_text_color) {
            this.admob_native_content_text_color = admob_native_content_text_color;
        }

        public List<String> getAdmob_banner_id_list() {
            return admob_banner_id_list;
        }

        public void setAdmob_banner_id_list(List<String> admob_banner_id_list) {
            this.admob_banner_id_list = admob_banner_id_list;
        }

        public Boolean getVnHeaderShow() {
            return vnHeaderShow;
        }

        public void setVnHeaderShow(Boolean vnHeaderShow) {
            this.vnHeaderShow = vnHeaderShow;
        }

        public List<String> getApp_open_id_list() {
            return app_open_id_list;
        }

        public void setApp_open_id_list(List<String> app_open_id_list) {
            this.app_open_id_list = app_open_id_list;
        }

        public List<String> getAdmob_interstitial_id_list() {
            return admob_interstitial_id_list;
        }

        public void setAdmob_interstitial_id_list(List<String> admobInterstitialList) {
            this.admob_interstitial_id_list = admobInterstitialList;
        }

        public List<String> getAdmob_native_id_list() {
            return admob_native_id_list;
        }

        public void setAdmob_native_id_list(List<String> admobnativeID) {
            this.admob_native_id_list = admobnativeID;
        }

        public List<String> getAdmob_bottom_native_id_list() {
            return admob_bottom_native_id_list;
        }

        public void setAdmob_bottom_native_id_list(List<String> admobnativeID) {
            this.admob_bottom_native_id_list = admobnativeID;
        }


        public Integer getApp_open_interstitial_click() {
            return app_open_interstitial_click;
        }

        public void setApp_open_interstitial_click(Integer app_open_interstitial_click) {
            this.app_open_interstitial_click = app_open_interstitial_click;
        }

        public boolean isApp_open_interstitial_show() {
            return app_open_interstitial_show;
        }

        public void setApp_open_interstitial_show(boolean app_open_interstitial_show) {
            this.app_open_interstitial_show = app_open_interstitial_show;
        }

        public Integer getAdsClick() {
            return adsClick;
        }

        public void setAdsClick(Integer adsClick) {
            this.adsClick = adsClick;
        }

        public Boolean getMaintenance() {
            return maintenance;
        }

        public void setMaintenance(Boolean maintenance) {
            this.maintenance = maintenance;
        }

        public Integer getBackClick() {
            return backClick;
        }

        public void setBackClick(Integer backClick) {
            this.backClick = backClick;
        }

        public Integer getAdsOpenTime() {
            return adsOpenTime;
        }

        public void setAdsOpenTime(Integer adsOpenTime) {
            this.adsOpenTime = adsOpenTime;
        }

        public Integer getAdsClickTime() {
            return adsClickTime;
        }

        public void setAdsClickTime(Integer adsClickTime) {
            this.adsClickTime = adsClickTime;
        }

        public String getAdmobNativeTwo() {
            return admobNativeTwo;
        }

        public void setAdmobNativeTwo(String admobNativeTwo) {
            this.admobNativeTwo = admobNativeTwo;
        }

        public Boolean getIs_quiz() {
            return is_quiz;
        }

        public void setIs_quiz(Boolean is_quiz) {
            this.is_quiz = is_quiz;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPackage() {
            return _package;
        }

        public void setPackage(String _package) {
            this._package = _package;
        }

        public String getAdmobInterstitialId() {
            return admobInterstitialId;
        }

        public void setAdmobInterstitialId(String admobInterstitialId) {
            this.admobInterstitialId = admobInterstitialId;
        }

        public String getAdmobNativeId() {
            return admobNativeId;
        }

        public void setAdmobNativeId(String admobNativeId) {
            this.admobNativeId = admobNativeId;
        }

        public String getAdmobBannerId() {
            return admobBannerId;
        }

        public void setAdmobBannerId(String admobBannerId) {
            this.admobBannerId = admobBannerId;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }


        public String getAppMsg() {
            return appMsg;
        }

        public void setAppMsg(String appMsg) {
            this.appMsg = appMsg;
        }

        public String getUpdateUrl() {
            return updateUrl;
        }

        public void setUpdateUrl(String updateUrl) {
            this.updateUrl = updateUrl;
        }

        public boolean getIsUpdate() {
            return isUpdate;
        }

        public void setIsUpdate(boolean isUpdate) {
            this.isUpdate = isUpdate;
        }

        public String getAdsType() {
            return adsType;
        }

        public void setAdsType(String adsType) {
            this.adsType = adsType;
        }

        public String getSplashAdsType() {
            return splashAdsType;
        }

        public void setSplashAdsType(String splashAdsType) {
            this.splashAdsType = splashAdsType;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getPrivacyPolicy() {
            return privacyPolicy;
        }

        public void setPrivacyPolicy(String privacyPolicy) {
            this.privacyPolicy = privacyPolicy;
        }

        public String getAppOpenId() {
            return appOpenId;
        }

        public void setAppOpenId(String appOpenId) {
            this.appOpenId = appOpenId;
        }


        public String getVnid() {
            return vnid;
        }

        public void setVnid(String vnid) {
            this.vnid = vnid;
        }

        public String getVnpassword() {
            return vnpassword;
        }

        public void setVnpassword(String vnpassword) {
            this.vnpassword = vnpassword;
        }
    }

    public class CountryList {
        @SerializedName("name")
        public String name;

        @SerializedName("countrycode")
        public String code;

        @SerializedName("image")
        public String cuntryimages;
    }
}


