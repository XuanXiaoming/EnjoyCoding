package com.example.demo.service;

import com.example.demo.entity.MyUser;

public interface UserService {

    MyUser findUserByUsername(String username);

}
