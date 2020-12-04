package com.ckyue.shop.services;

import com.ckyue.shop.entities.User;
import com.ckyue.shop.exceptions.ShopAuthException;

public interface UserService {
    User validateUser(String email, String password) throws ShopAuthException;

    User registerUser(String firstName, String lastName, String email, String password) throws ShopAuthException;

}
