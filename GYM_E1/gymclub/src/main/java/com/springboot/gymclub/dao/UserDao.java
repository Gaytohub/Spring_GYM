package com.springboot.gymclub.dao;

import com.springboot.gymclub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByPhone(Long phone);
    User findByPhoneAndPassword(Long phone, String password);
}
