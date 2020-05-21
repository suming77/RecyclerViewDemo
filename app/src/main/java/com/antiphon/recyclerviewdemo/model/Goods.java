package com.antiphon.recyclerviewdemo.model;

public class Goods {
    private String name;
    private long price;
    private String des;
    private int viewType;

    public Goods() {
    }

    public Goods(String name, long price, String des) {
        this.name = name;
        this.price = price;
        this.des = des;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
