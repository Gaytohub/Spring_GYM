package com.springboot.gymclub.dao;

import com.springboot.gymclub.entity.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CourseDao  extends JpaRepository<Course, Integer> {
    Collection<Course> findByTrainer(String trainer, Pageable pageable);
    Collection<Course> findByName(String name, Pageable pageable);
    Collection<Course> findByNameAndTrainer(String name,String trainer, Pageable pageable);
}
