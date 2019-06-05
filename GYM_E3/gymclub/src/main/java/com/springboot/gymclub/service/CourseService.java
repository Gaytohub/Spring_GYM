package com.springboot.gymclub.service;

import com.springboot.gymclub.entity.Course;
import com.springboot.gymclub.entity.UserCourse;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;

public interface CourseService {
    int findTotalPage();
    Page<Course> getAllCourses(int pages);
    Page<Course> findByTrainer(String trainer, int pages);
    Page<Course> findByName(String name,int pages);
    Page<Course> findByNameAndTrainer(String name, String trainer, int pages);

    boolean tryAcquireSeckill();

    UserCourse buildUserCourse(Long userPhone,String trainerName,Integer courseId,String courseName);
    void operate(Integer courseId,String status);
}
