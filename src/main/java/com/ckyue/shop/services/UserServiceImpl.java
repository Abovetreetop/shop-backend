package com.ckyue.shop.services;

import com.ckyue.shop.dao.UserDao;
import com.ckyue.shop.entities.User;
import com.ckyue.shop.exceptions.ShopAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Override
    public User validateUser(String email, String password) throws ShopAuthException {
        if(email != null) email = email.toLowerCase();
        return userDao.findByEmailAndPassword(email, password);
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) throws ShopAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(email != null) email = email.toLowerCase();
        if(!pattern.matcher(email).matches())
            throw new ShopAuthException("Invalid email format");
        Integer count = userDao.getCountByEmail(email);
        if(count > 0)
            throw new ShopAuthException("Email already in use");
        Integer userId = userDao.create(firstName, lastName, email, password);
        return userDao.findById(userId);
    }
}
