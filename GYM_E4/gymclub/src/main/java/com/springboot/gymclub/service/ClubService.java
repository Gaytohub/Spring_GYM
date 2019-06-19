package com.springboot.gymclub.service;

import com.springboot.gymclub.entity.Club;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ClubService {
    int findTotalPage();
    Flux<Club> getAllClubs(int pages);
    Flux<Club> findByPhone(Long phone,int pages);
    Flux<Club> findByName(String name,int pages);
    Flux<Club> findByNameAndPhone(String name, Long phone,int pages);

    Mono<Club> findByName(String name);
    boolean tryAcquireSeckill();
}
