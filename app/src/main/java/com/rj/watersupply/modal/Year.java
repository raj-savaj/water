package com.rj.watersupply.modal;
import java.util.ArrayList;

public class Year {
    private ArrayList<Float> amounts;
    private Double totalAmount;

    public ArrayList<Float> getAmounts() {
        return this.amounts;
    }

    public void setAmounts(ArrayList<Float> amounts2) {
        this.amounts = amounts2;
    }

    public Double getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(Double totalAmount2) {
        this.totalAmount = totalAmount2;
    }
}
