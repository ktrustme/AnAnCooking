package com.anan.anancooking.client.util;

/**
 * Created by kuoxin on 3/30/15.
 */
public class Record {
    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }

    public void setTermInYears(int termInYears) {
        this.termInYears = termInYears;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void setFirstPaymentDate(String firstPaymentDate) {
        this.firstPaymentDate = firstPaymentDate;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public void setOverallPayment(double overallPayment) {
        this.overallPayment = overallPayment;
    }

    public void setPayoffDate(String payoffDate) {
        this.payoffDate = payoffDate;
    }

    public double getPurchasePrice() {

        return purchasePrice;
    }

    public double getDownPayment() {
        return downPayment;
    }

    public int getTermInYears() {
        return termInYears;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public String getFirstPaymentDate() {
        return firstPaymentDate;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public double getOverallPayment() {
        return overallPayment;
    }

    public String getPayoffDate() {
        return payoffDate;
    }

    private double purchasePrice;
    private double downPayment;
    private int termInYears;
    private double interestRate;
    private String firstPaymentDate;
    private double monthlyPayment;
    private double overallPayment;
    private String payoffDate;
}
