package com.university.springuniversitydemo.dao;

import com.university.springuniversitydemo.entity.User;

public interface UserDao {
    User findByUserName(String userName);
    void save(User user);
    
}
