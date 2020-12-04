package com.ckyue.shop.entities;

public class Address {
    private Integer id;
    private String street;
    private String province;
    private String country;
    private String zip;
    private String phone;

    public Address(String street, String province, String country, String zip, String phone) {
        this.street = street;
        this.province = province;
        this.country = country;
        this.zip = zip;
        this.phone = phone;
        this.id = null;
    }

    public Address(Integer id, String street, String province, String country, String zip, String phone) {
        this.id = id;
        this.street = street;
        this.province = province;
        this.country = country;
        this.zip = zip;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
