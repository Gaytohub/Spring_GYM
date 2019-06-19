package com.springboot.gymclub.service;

import com.springboot.gymclub.entity.User;
import com.springboot.gymclub.entity.UserCourse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> findByPhone(Long phone);
    Mono<User> findByPhoneAndPassword(Long phone,String password);
    void addUser(String username,String password,String gender,Long phone);
    boolean tryAcquireSeckill();
    Flux<UserCourse> getUserCourse(Long phone);
}
