package com.university.springuniversitydemo.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import com.university.springuniversitydemo.entity.User;
import com.university.springuniversitydemo.user.CrmUser;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);
    void save(CrmUser crmUser);
}
