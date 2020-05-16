package com.rj.watersupply.modal;

public class Product {
    private int PID;
    private String pname;
    private String pcode;
    private int base_price;


    // Getter Methods

    public int getPID() {
        return PID;
    }

    public String getPname() {
        return pname;
    }

    public String getPcode() {
        return pcode;
    }

    public int getBase_price() {
        return base_price;
    }

    // Setter Methods

    public void setPID(int PID) {
        this.PID = PID;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public void setBase_price(int base_price) {
        this.base_price = base_price;
    }
}
