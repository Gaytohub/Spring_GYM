package com.springboot.gymclub.service;

import com.springboot.gymclub.entity.Trainer;
import com.springboot.gymclub.entity.UserCourse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TrainerService
{
    int findTotalPage();
    Flux<Trainer> getAllTrainers(int pages);
    Flux<Trainer> findByClub(String club,int pages);
    Flux<Trainer> findByClubAndGender(String club, String gender,int pages);
    Flux<Trainer> findByGender(String gender, int pages);
    Flux<Trainer> findByName(int pages);

    Mono<Trainer> findByPhone(Long phone);
    Mono<Trainer> findByPhoneAndPassword(Long phone, Integer password);
    Mono<Trainer> findByName(String name);

    boolean tryAcquireSeckill();

    Flux<UserCourse> getUserCourse(Long phone);
}
