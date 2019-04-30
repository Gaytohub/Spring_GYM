package com.springboot.gymclub.dao;

import com.springboot.gymclub.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubDao extends JpaRepository<Club, Long>{
    List<Club> findByPhone(Long phone);
    List<Club> findByName(String name);
    List<Club> findByNameAndPhone(String name, Long phone);
}
