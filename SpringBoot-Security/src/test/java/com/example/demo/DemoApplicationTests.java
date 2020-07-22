package com.example.demo;

import com.example.demo.dao.UserDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private UserDAO userdao;

    @Test
    void contextLoads() {

    }

    @Test
    void tsetfunction() {
        System.out.println(userdao.findAll().toString());
//        System.out.println(userdao.findByFromUserId(1).get(0).toString());
    }

}
