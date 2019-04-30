package com.springboot.gymclub.service;

import com.springboot.gymclub.entity.Trainer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TrainerService
{
    List<Trainer> getAllTrainers();

    List<Trainer> findByClub(String club);

    List<Trainer> findByClubAndGender(String club, String gender);

    List<Trainer> findByGender(String gender);
}
