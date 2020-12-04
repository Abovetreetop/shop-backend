package com.ckyue.shop.dao;

import com.ckyue.shop.entities.Address;
import com.ckyue.shop.entities.Book;
import com.ckyue.shop.entities.Order;

import java.util.List;

public interface OrderDao {
    Integer createAddress(String street, String province, String country, String zip, String phone);

    Integer createOrder(String first_name, String last_name, String status, Integer address);

    String AddItemToOrder(Integer id, String bid, Integer price);

    List<Order> getOrdersByBid(String bid);

    Address getAddressById(Integer id);

    Book getBookByBid(String bid);
}
