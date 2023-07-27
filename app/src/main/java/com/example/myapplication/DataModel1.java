package com.example.myapplication;

public class DataModel1 {
    private double TotalPrincipal;
    private double TotalInterest;
    public DataModel1(double TotalPrincipal, double TotalInterest) {
        this.TotalPrincipal = TotalPrincipal;
        this.TotalInterest = TotalInterest;
    }
    public DataModel1() {
    }
    public void setTotalPrincipal(double TotalPrincipal) {
        this.TotalPrincipal = TotalPrincipal;
    }
    public double getTotalPrincipal() {
        return TotalPrincipal;
    }
    public void setTotalInterest(double TotalInterest) {
        this.TotalInterest = TotalInterest;
    }
    public double getTotalInterest() {
        return TotalInterest;
    }
}
