package com.example.crescooshop.Constructor;

public class addItemConstructor {

    String prodName, quantity, cp, sp;


    //empty Constructor
    public addItemConstructor(){

    }

    public addItemConstructor(String prodName, String quantity, String cp, String sp) {
        this.prodName = prodName;
        this.quantity = quantity;
        this.cp = cp;
        this.sp = sp;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }
}
