package com.example.myapplication.model;

public class BinList {
    private Bank bank;
    private String brand;
    private Country country;
    private Number number;
    private String prepaid;
    private String scheme;
    private String type;

    public Number getNumber() {
        return this.number;
    }

    public void setNumber(Number number2) {
        this.number = number2;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country2) {
        this.country = country2;
    }

    public Bank getBank() {
        return this.bank;
    }

    public void setBank(Bank bank2) {
        this.bank = bank2;
    }

    public String getScheme() {
        return this.scheme;
    }

    public void setScheme(String str) {
        this.scheme = str;
    }

    public String getPrepaid() {
        return this.prepaid;
    }

    public void setPrepaid(String str) {
        this.prepaid = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String str) {
        this.brand = str;
    }

    public String toString() {
        return "ClassPojo [number = " + this.number + ", country = " + this.country + ", bank = " + this.bank + ", scheme = " + this.scheme + ", prepaid = " + this.prepaid + ", type = " + this.type + ", brand = " + this.brand + "]";
    }
}
