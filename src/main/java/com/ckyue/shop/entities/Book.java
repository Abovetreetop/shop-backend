package com.ckyue.shop.entities;

public class Book {
    private String bid;
    private String title;
    private Integer price;
    private String category;

    public Book(String bid, String title, Integer price, String category) {
        this.bid = bid;
        this.title = title;
        this.price = price;
        this.category = category;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
