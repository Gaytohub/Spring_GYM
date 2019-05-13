package com.springboot.gymclub.service;

import com.springboot.gymclub.entity.User;

public interface UserService {
    User findByPhone(Long phone);
    User findByPhoneAndPassword(Long phone,String password);
    void addUser(String username,String password,String gender,Long phone);
    boolean tryAcquireSeckill();
}
