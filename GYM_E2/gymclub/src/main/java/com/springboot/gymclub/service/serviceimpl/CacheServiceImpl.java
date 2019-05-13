package com.springboot.gymclub.service.serviceimpl;

import com.springboot.gymclub.service.CacheService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements CacheService {
    @Override
    @CacheEvict(allEntries = true,cacheNames = {"findAllClubs","findClubsByName","findClubsByNameAndPhone","findClubsByPhone",
            "findAllTrainers","findTrainersByClub","findTrainersByClubAndGender","findTrainersByGender"})
    public void clearAllCache() {
        //清除所有缓存
    }
}
