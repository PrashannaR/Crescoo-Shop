package com.example.crescooshop.Constructor;

public class Constructor {
    String shopName, phone, ownerName, shopType, shopLocation, franchise, desc;

    public Constructor(){

    }

    public Constructor(String shopName, String phone, String ownerName, String shopType, String shopLocation, String franchise, String desc) {
        this.shopName = shopName;
        this.phone = phone;
        this.ownerName = ownerName;
        this.shopType = shopType;
        this.shopLocation = shopLocation;
        this.franchise = franchise;
        this.desc = desc;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getShopLocation() {
        return shopLocation;
    }

    public void setShopLocation(String shopLocation) {
        this.shopLocation = shopLocation;
    }

    public String getFranchise() {
        return franchise;
    }

    public void setFranchise(String franchise) {
        this.franchise = franchise;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
