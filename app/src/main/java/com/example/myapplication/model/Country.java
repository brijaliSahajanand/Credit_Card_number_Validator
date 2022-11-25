package com.example.myapplication.model;

public class Country {
    private String alpha2;
    private String currency;
    private String emoji;
    private String latitude;
    private String longitude;
    private String name;
    private String numeric;

    public String getEmoji() {
        return this.emoji;
    }

    public void setEmoji(String str) {
        this.emoji = str;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String str) {
        this.latitude = str;
    }

    public String getAlpha2() {
        return this.alpha2;
    }

    public void setAlpha2(String str) {
        this.alpha2 = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getNumeric() {
        return this.numeric;
    }

    public void setNumeric(String str) {
        this.numeric = str;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String str) {
        this.longitude = str;
    }

    public String toString() {
        return "ClassPojo [emoji = " + this.emoji + ", latitude = " + this.latitude + ", alpha2 = " + this.alpha2 + ", name = " + this.name + ", numeric = " + this.numeric + ", currency = " + this.currency + ", longitude = " + this.longitude + "]";
    }
}
