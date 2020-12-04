package com.ckyue.shop.entities;

import java.util.List;

public class Order {
    private Integer id;
    private String bid;
    private Integer price;
    private String first_name;
    private String last_name;
    private String status;
    private Address address;


    public Order(Integer id, String bid, Integer price, String first_name, String last_name, String status, Address address) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.status = status;
        this.address = address;
        this.bid = bid;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
