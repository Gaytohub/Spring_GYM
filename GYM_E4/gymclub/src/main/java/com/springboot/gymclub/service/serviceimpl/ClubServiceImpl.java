package com.springboot.gymclub.service.serviceimpl;

import com.google.common.util.concurrent.RateLimiter;
import com.springboot.gymclub.dao.ClubDao;
import com.springboot.gymclub.entity.Club;
import com.springboot.gymclub.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {
    @Autowired
    private ClubDao clubDao;

    /**
     * 每秒钟只发出5个令牌，拿到令牌的请求才可以进入下一个业务
     */
    private RateLimiter seckillRateLimiter = RateLimiter.create(5);

    @Override
    public boolean tryAcquireSeckill() {
        return seckillRateLimiter.tryAcquire();
    }

    @Override
    public int findTotalPage() {
        List<Club> list = clubDao.findAll();
        if(list.size()%2==0){
            return list.size()/2;
        }else{
            return list.size()/2+1;
        }
    }

    @Cacheable(value = "findAllClubs")//ehcache的缓存仅仅存在于内存中，若服务器关闭则缓存被清除
    public Flux<Club> getAllClubs(int pages)
    {
//        Club club1 = new Club();
//        club1.setName("424健身俱乐部");
//        club1.setPhone(7788424L);
//        club1.setAddress("北京市海淀区北下关街道上园村3号16号楼");
//        club1.setIntro("具有三年悠久历史，与许多国际获奖的优秀健身教练合作，如xfy，rxs，phw...");
//        clubDao.save(club1);
//
//        Club club2 = new Club();
//        club2.setName("315健身俱乐部");
//        club2.setPhone(7788315L);
//        club2.setAddress("北京市海淀区北下关街道上园村3号16号楼");
//        club2.setIntro("器材设备齐全，定期维护换新，给你最佳的健身体验");
//        clubDao.save(club2);
        System.out.println("访问数据库来查询俱乐部信息，不走缓存");
        Pageable pageable =PageRequest.of(pages-1,1);
        return Flux.fromIterable(clubDao.findAll(pageable));
    }

    @Cacheable(value = "findClubsByPhone")
    @Override
    public Flux<Club> findByPhone(Long phone,int pages) {
        System.out.println("访问数据库来查询俱乐部信息，不走缓存");
        Pageable pageable =PageRequest.of(pages-1,1);
        return Flux.fromIterable(clubDao.findByPhone(phone,pageable));
    }

    @Cacheable(value = "findClubsByName")
    @Override
    public Flux<Club> findByName(String name,int pages) {
        System.out.println("访问数据库来查询俱乐部信息，不走缓存");
        Pageable pageable =PageRequest.of(pages-1,1);
        return Flux.fromIterable(clubDao.findByName(name, pageable));
    }

    @Cacheable(value = "findClubsByNameAndPhone")
    @Override
    public Flux<Club> findByNameAndPhone(String name, Long phone, int pages) {
        System.out.println("访问数据库来查询俱乐部信息，不走缓存");
        Pageable pageable = PageRequest.of(pages-1,1);
        return Flux.fromIterable(clubDao.findByNameAndPhone(name,phone,pageable));
    }


    //用于查询俱乐部详情，不需要分页
    @Override
    public Mono<Club> findByName(String name) {
        System.out.println("访问数据库来查询俱乐部信息，不走缓存");
        return Mono.justOrEmpty(clubDao.findByName(name));
    }
}
