package com.springboot.gymclub.service.serviceimpl;

import com.springboot.gymclub.dao.ClubDao;
import com.springboot.gymclub.entity.Club;
import com.springboot.gymclub.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {
    @Autowired
    private ClubDao clubDao;

    @Cacheable(value = "findAllClubs")//ehcache的缓存仅仅存在于内存中，若服务器关闭则缓存被清除
    public List<Club> getAllClubs()
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
        return clubDao.findAll();
    }

    @Cacheable(value = "findClubsByPhone")
    @Override
    public List<Club> findByPhone(Long phone) {
        System.out.println("访问数据库来查询俱乐部信息，不走缓存");
        return clubDao.findByPhone(phone);
    }

    @Cacheable(value = "findClubsByName")
    @Override
    public List<Club> findByName(String name) {
        System.out.println("访问数据库来查询俱乐部信息，不走缓存");
        return clubDao.findByName(name);
    }

    @Cacheable(value = "findClubsByNameAndPhone")
    @Override
    public List<Club> findByNameAndPhone(String name, Long phone) {
        System.out.println("访问数据库来查询俱乐部信息，不走缓存");
        return clubDao.findByNameAndPhone(name,phone);
    }
}
