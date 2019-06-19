package com.springboot.gymclub.dao;

import com.springboot.gymclub.entity.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserCourseDao extends JpaRepository<UserCourse, Integer> {
    Collection<UserCourse> findByUserPhone(Long phone);
    Collection<UserCourse> findByTrainerPhone(Long phone);
}

