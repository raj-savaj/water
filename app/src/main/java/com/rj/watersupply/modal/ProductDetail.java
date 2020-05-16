package com.rj.watersupply.modal;

public class ProductDetail {
    private int pd_id;
    private int pid;
    private int qty;
    private int sell_qty;
    private int price;
    private String date;
    private String description;
    private String pcode;

    public int getPd_id() {
        return pd_id;
    }

    public int getPid() {
        return pid;
    }

    public int getQty() {
        return qty;
    }

    public int getSell_qty() {
        return sell_qty;
    }

    public int getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getPcode() {
        return pcode;
    }

    // Setter Methods

    public void setPd_id(int pd_id) {
        this.pd_id = pd_id;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setSell_qty(int sell_qty) {
        this.sell_qty = sell_qty;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }
}
