package com.springboot.gymclub.service;

import com.springboot.gymclub.entity.Trainer;
import org.springframework.data.domain.Page;

public interface TrainerService
{
    int findTotalPage();
    Page<Trainer> getAllTrainers(int pages);
    Page<Trainer> findByClub(String club,int pages);
    Page<Trainer> findByClubAndGender(String club, String gender,int pages);
    Page<Trainer> findByGender(String gender,int pages);
    Page<Trainer> findByName(int pages);

    Trainer findByPhone(Long phone);
    boolean tryAcquireSeckill();
}
