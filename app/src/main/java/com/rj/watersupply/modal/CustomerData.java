package com.rj.watersupply.modal;

public class CustomerData {
    private String c_id;
    private String name;
    private String address;
    private String phone;
    private int cBottle;
    private int hBottle;
    private int cBottlePrice;
    private int hBottlePrice;
    private int cBottleRet;
    private int hBottleRet;
    private int credit;
    private int debit;
    private int total;
    private String status;
    private String date;

    public String getC_id() {
        return c_id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public int getCBottle() {
        return cBottle;
    }

    public int getHBottle() {
        return hBottle;
    }

    public int getCBottlePrice() {
        return cBottlePrice;
    }

    public int getHBottlePrice() {
        return hBottlePrice;
    }

    public int getCBottleRet() {
        return cBottleRet;
    }

    public int getHBottleRet() {
        return hBottleRet;
    }

    public int getCredit() {
        return credit;
    }

    public int getDebit() {
        return debit;
    }

    public int getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    // Setter Methods

    public void setC_id( String c_id ) {
        this.c_id = c_id;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public void setAddress( String address ) {
        this.address = address;
    }

    public void setPhone( String phone ) {
        this.phone = phone;
    }

    public void setCBottle( int cBottle ) {
        this.cBottle = cBottle;
    }

    public void setHBottle( int hBottle ) {
        this.hBottle = hBottle;
    }

    public void setCBottlePrice( int cBottlePrice ) {
        this.cBottlePrice = cBottlePrice;
    }

    public void setHBottlePrice( int hBottlePrice ) {
        this.hBottlePrice = hBottlePrice;
    }

    public void setCBottleRet( int cBottleRet ) {
        this.cBottleRet = cBottleRet;
    }

    public void setHBottleRet( int hBottleRet ) {
        this.hBottleRet = hBottleRet;
    }

    public void setCredit( int credit ) {
        this.credit = credit;
    }

    public void setDebit( int debit ) {
        this.debit = debit;
    }

    public void setTotal( int total ) {
        this.total = total;
    }

    public void setStatus( String status ) {
        this.status = status;
    }

    public void setDate( String date ) {
        this.date = date;
    }
}
