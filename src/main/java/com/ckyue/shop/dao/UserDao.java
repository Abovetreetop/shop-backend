package com.ckyue.shop.dao;

import com.ckyue.shop.entities.User;
import com.ckyue.shop.exceptions.ShopAuthException;

public interface UserDao {
    Integer create(String firstName, String lastName, String email, String password) throws ShopAuthException;

    User findByEmailAndPassword(String email, String password) throws ShopAuthException;

    User findById(Integer userId);

    Integer getCountByEmail(String email);
}
