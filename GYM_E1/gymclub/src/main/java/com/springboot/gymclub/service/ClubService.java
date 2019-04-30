package com.springboot.gymclub.service;

import com.springboot.gymclub.entity.Club;
import java.util.List;

public interface ClubService {
    List<Club> getAllClubs();
    List<Club> findByPhone(Long phone);
    List<Club> findByName(String name);
    List<Club> findByNameAndPhone(String name, Long phone);
}
