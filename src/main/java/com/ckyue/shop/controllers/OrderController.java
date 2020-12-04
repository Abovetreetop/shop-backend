package com.ckyue.shop.controllers;

import com.ckyue.shop.entities.Address;
import com.ckyue.shop.entities.Book;
import com.ckyue.shop.entities.Order;
import com.ckyue.shop.entities.User;
import com.ckyue.shop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/history")
    @ResponseBody
    public ResponseEntity<List<Order>> history(@RequestParam String bid) {
        List<Order> orders = orderService.getOrdersByBid(bid);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewOrder(@RequestBody Map<String, Object> orderMap) {
        String first_name = (String) orderMap.get("first_name");
        String last_name = (String) orderMap.get("last_name");
        String status = (String) orderMap.get("status");


        Map<String, Object> addressMap = (Map<String, Object>) orderMap.get("address");
        String street = (String) addressMap.get("street");
        String province = (String) addressMap.get("province");
        String country = (String) addressMap.get("country");
        String zip = (String) addressMap.get("zip");
        String phone = (String) addressMap.get("phone");

        List<String> bids = (List<String>) orderMap.get("books");
        Address address = new Address(street, province, country, zip, phone);
        Integer order_id = orderService.createOrder(first_name, last_name, status, address, bids);
        String ret = "Added new order successfully, order_id: " + order_id.toString();
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
