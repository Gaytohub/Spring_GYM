package com.springboot.gymclub.controller;

import com.springboot.gymclub.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {
    @Autowired
    private CacheService cacheService;

    @GetMapping("/clearCache")
    public void clearAllCache()
    {
        cacheService.clearAllCache();
    }
}
