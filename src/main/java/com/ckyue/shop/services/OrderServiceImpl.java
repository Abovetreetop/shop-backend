package com.ckyue.shop.services;

import com.ckyue.shop.dao.OrderDao;
import com.ckyue.shop.dao.UserDao;
import com.ckyue.shop.entities.Address;
import com.ckyue.shop.entities.Book;
import com.ckyue.shop.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderDao orderDao;

    @Override
    public Integer createOrder(String first_name, String last_name, String status, Address address, List<String> bids) {
        Integer address_id = orderDao.createAddress(address.getStreet(), address.getProvince(), address.getCountry(), address. getZip(), address.getPhone());
        Integer order_id = orderDao.createOrder(first_name, last_name, status, address_id);
        for(String bid : bids) {
            Integer book_price = orderDao.getBookByBid(bid).getPrice();
            orderDao.AddItemToOrder(order_id, bid, book_price);
        }
        return order_id;
    }

    @Override
    public List<Order> getOrdersByBid(String bid) {
        return orderDao.getOrdersByBid(bid);
    }
}
