package com.springboot.gymclub.dao;

import com.springboot.gymclub.entity.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCourseDao extends JpaRepository<UserCourse, Integer> {
    List<UserCourse> findByUserPhone(Long phone);
    List<UserCourse> findByTrainerPhone(Long phone);
}

