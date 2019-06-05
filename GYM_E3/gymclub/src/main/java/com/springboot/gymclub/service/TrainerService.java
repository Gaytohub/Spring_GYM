package com.springboot.gymclub.service;

import com.springboot.gymclub.entity.Trainer;
import com.springboot.gymclub.entity.UserCourse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TrainerService
{
    int findTotalPage();
    Page<Trainer> getAllTrainers(int pages);
    Page<Trainer> findByClub(String club,int pages);
    Page<Trainer> findByClubAndGender(String club, String gender,int pages);
    Page<Trainer> findByGender(String gender,int pages);
    Page<Trainer> findByName(int pages);

    Trainer findByPhone(Long phone);
    Trainer findByPhoneAndPassword(Long phone, Integer password);
    Trainer findByName(String name);

    boolean tryAcquireSeckill();

    List<UserCourse> getUserCourse(Long phone);
}
