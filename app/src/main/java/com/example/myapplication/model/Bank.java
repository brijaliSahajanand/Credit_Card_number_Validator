package com.example.myapplication.model;

public class Bank {
    private String name;
    private String phone;
    private String url;

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String str) {
        this.phone = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String toString() {
        return "ClassPojo [phone = " + this.phone + ", name = " + this.name + ", url = " + this.url + "]";
    }
}
