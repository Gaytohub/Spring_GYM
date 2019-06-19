package com.springboot.gymclub.controller;

import com.springboot.gymclub.service.CacheService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class CacheController {
    @Autowired
    private CacheService cacheService;

    @ApiOperation(value = "清除内存缓存")
    @GetMapping("/clearCache")
    public void clearAllCache()
    {
        cacheService.clearAllCache();
    }
}
