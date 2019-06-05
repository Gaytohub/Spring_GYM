package com.springboot.gymclub.dao;

import com.springboot.gymclub.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDao  extends JpaRepository<Course, Integer> {
    Page<Course> findByTrainer(String trainer, Pageable pageable);
    Page<Course> findByName(String name, Pageable pageable);
    Page<Course> findByNameAndTrainer(String name,String trainer, Pageable pageable);
}
