package com.springboot.gymclub.dao;


import com.springboot.gymclub.entity.Trainer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface TrainerDao extends JpaRepository<Trainer, Long>{
    Page<Trainer> findByClub(String club,Pageable pageable);
    Page<Trainer> findByClubAndGender(String club, String gender,Pageable pageable);
    Page<Trainer> findByGender(String gender,Pageable pageable);

    Trainer findByPhone(Long phone);
}
