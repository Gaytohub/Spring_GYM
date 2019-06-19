package com.springboot.gymclub.dao;

import com.springboot.gymclub.entity.Club;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ClubDao extends JpaRepository<Club, Long>{
    Collection<Club> findByPhone(Long phone, Pageable pageable);
    Collection<Club> findByName(String name, Pageable pageable);
    Collection<Club> findByNameAndPhone(String name, Long phone, Pageable pageable);

    Club findByName(String name);
}
