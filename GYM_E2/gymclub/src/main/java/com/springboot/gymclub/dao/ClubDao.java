package com.springboot.gymclub.dao;

import com.springboot.gymclub.entity.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubDao extends JpaRepository<Club, Long>{
    Page<Club> findByPhone(Long phone, Pageable pageable);
    Page<Club> findByName(String name, Pageable pageable);
    Page<Club> findByNameAndPhone(String name, Long phone, Pageable pageable);

    Club findByName(String name);
}
