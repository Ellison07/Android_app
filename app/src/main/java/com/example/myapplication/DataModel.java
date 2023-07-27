package com.example.myapplication;
public class DataModel {
    private double principal;
    private double rate;
    private double months;
    private double interest;
    private String date;
    public DataModel(double principal, double rate, double months, double interest, String date) {
        this.principal = principal;
        this.rate = rate;
        this.months = months;
        this.interest = interest;
        this.date = date;
    }
    public DataModel() {
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setPrincipal(double principal) {
        this.principal = principal;
    }
    public double getPrincipal() {
        return principal;
    }
    public void setRate(double rate) {
        this.rate = rate;
    }
    public double getRate() {
        return rate;
    }
    public void setMonths(double months) {
        this.months = months;
    }
    public double getMonths() {
        return months;
    }
    public void setInterest(double interest) {
        this.interest = interest;
    }
    public double getInterest() {
        return interest;
    }
    public String getDate() {
        return date;
    }
}

