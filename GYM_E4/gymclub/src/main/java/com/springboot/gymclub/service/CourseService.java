package com.springboot.gymclub.service;

import com.springboot.gymclub.entity.Course;
import com.springboot.gymclub.entity.UserCourse;
import reactor.core.publisher.Flux;

public interface CourseService {
    int findTotalPage();
    Flux<Course> getAllCourses(int pages);
    Flux<Course> findByTrainer(String trainer, int pages);
    Flux<Course> findByName(String name, int pages);
    Flux<Course> findByNameAndTrainer(String name, String trainer, int pages);

    boolean tryAcquireSeckill();

    UserCourse buildUserCourse(Long userPhone,String trainerName,Integer courseId,String courseName);
    void operate(Integer courseId,String status);
}
