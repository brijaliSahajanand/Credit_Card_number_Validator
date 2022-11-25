package com.example.myapplication.model;

public class Number {
    private String length;
    private String luhn;

    public String getLength() {
        return this.length;
    }

    public void setLength(String str) {
        this.length = str;
    }

    public String getLuhn() {
        return this.luhn;
    }

    public void setLuhn(String str) {
        this.luhn = str;
    }

    public String toString() {
        return "ClassPojo [length = " + this.length + ", luhn = " + this.luhn + "]";
    }
}
