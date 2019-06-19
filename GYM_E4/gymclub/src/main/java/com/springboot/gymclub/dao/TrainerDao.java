package com.springboot.gymclub.dao;

import com.springboot.gymclub.entity.Trainer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface TrainerDao extends JpaRepository<Trainer, Long>{
    Collection<Trainer> findByClub(String club,Pageable pageable);
    Collection<Trainer> findByClubAndGender(String club, String gender,Pageable pageable);
    Collection<Trainer> findByGender(String gender, Pageable pageable);

    Trainer findByPhone(Long phone);
    Trainer findByPhoneAndPassword(Long phone, Integer password);
    Trainer findByName(String name);
}
