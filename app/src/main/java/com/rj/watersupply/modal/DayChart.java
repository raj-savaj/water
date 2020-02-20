package com.rj.watersupply.modal;

import java.util.ArrayList;

public class DayChart {
    private double amount;
    private ArrayList<Day> days;

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount2) {
        this.amount = amount2;
    }

    public ArrayList<Day> getDays() {
        return this.days;
    }

    public void setDays(ArrayList<Day> days2) {
        this.days = days2;
    }
}
