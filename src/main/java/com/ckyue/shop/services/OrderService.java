package com.ckyue.shop.services;

import com.ckyue.shop.entities.Address;
import com.ckyue.shop.entities.Book;
import com.ckyue.shop.entities.Order;

import java.util.List;

public interface OrderService {
    Integer createOrder(String first_name, String last_name, String status, Address address, List<String> bids);

    List<Order> getOrdersByBid(String bid);
}
