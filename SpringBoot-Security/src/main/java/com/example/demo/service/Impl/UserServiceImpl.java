package com.example.demo.service.Impl;

import com.example.demo.dao.UserDAO;
import com.example.demo.entity.MyUser;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDao;

    @Autowired
    public UserServiceImpl(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    public MyUser findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }
}
