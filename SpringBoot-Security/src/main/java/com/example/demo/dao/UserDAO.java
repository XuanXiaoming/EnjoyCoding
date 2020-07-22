package com.example.demo.dao;

import com.example.demo.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<MyUser, String> {

    @Query("from MyUser where username = :username")
    MyUser findUserByUsername(@Param("username") String username);
}
