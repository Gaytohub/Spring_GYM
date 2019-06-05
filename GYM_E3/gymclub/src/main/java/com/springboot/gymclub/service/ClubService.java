package com.springboot.gymclub.service;

import com.springboot.gymclub.entity.Club;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClubService {
    int findTotalPage();
    Page<Club> getAllClubs(int pages);
    Page<Club> findByPhone(Long phone,int pages);
    Page<Club> findByName(String name,int pages);
    Page<Club> findByNameAndPhone(String name, Long phone,int pages);

    Club findByName(String name);
    boolean tryAcquireSeckill();
}
