package com.springboot.gymclub.service.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import com.google.common.util.concurrent.RateLimiter;
import com.springboot.gymclub.dao.TrainerDao;
import com.springboot.gymclub.dao.UserCourseDao;
import com.springboot.gymclub.entity.Trainer;
import com.springboot.gymclub.entity.UserCourse;
import com.springboot.gymclub.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImpl implements TrainerService {
    @Autowired
    private TrainerDao trainerDao;
    @Autowired
    private UserCourseDao userCourseDao;
    /**
     * 每秒钟只发出5个令牌，拿到令牌的请求才可以进入下一个业务
     */
    private RateLimiter rateLimiter = RateLimiter.create(5);

    @Override
    public boolean tryAcquireSeckill() {
        return rateLimiter.tryAcquire();
    }

    @Override
    public int findTotalPage() {
        List<Trainer> list = trainerDao.findAll();
        if(list.size()%2==0){
            return list.size()/2;
        }else{
            return list.size()/2+1;
        }
    }

    @Cacheable(value = "findAllTrainers")//这个注解表示trainer以getAlltrainers的key值存入缓存中，后续可以根据这个值来加载缓存或从缓存中删除
    public Page<Trainer> getAllTrainers(int pages)
    {
//        Trainer user1 = new Trainer();
//        user1.setName("phw");
//        user1.setGender("男");
//        user1.setAge(31);
//        user1.setPhone(17600061934L);
//        user1.setClub("424健身俱乐部");
//        user1.setIntro("176cm，65kg，HKSCA国际高级私人教练认证，HKSCA国际高级运动营养师认证，主攻增肌减脂康复");
//        trainerDao.save(user1);
//
//        Trainer user2 = new Trainer();
//        user2.setName("xfy");
//        user2.setGender("男");
//        user2.setAge(32);
//        user2.setPhone(13261988833L);
//        user2.setClub("315健身俱乐部");
//        user2.setIntro("184cm，75kg，模特，健身教练，2007年中国健美先生70公斤级亚军， 香港体育教练员协会培训导师");
//        trainerDao.save(user2);
//
//        Trainer user3 = new Trainer();
//        user3.setName("ycb");
//        user3.setGender("男");
//        user3.setAge(33);
//        user3.setPhone(15910887389L);
//        user3.setClub("424健身俱乐部");
//        user3.setIntro("180cm，65kg，体适能专项训练导师，功能性训练技术导师，全国十佳健身教练，国家一级运动员");
//        trainerDao.save(user3);
//
//        Trainer user4 = new Trainer();
//        user4.setName("rxs");
//        user4.setGender("男");
//        user4.setAge(34);
//        user4.setClub("315健身俱乐部");
//        user4.setPhone(18810861312L);
//        user4.setIntro("172cm，65kg，HKSCA国际高级运动营养师认证 国际康体学院搏击教练认证 LESMILLS国际教练认证");
//        trainerDao.save(user4);
        System.out.println("访问数据库来查询教练信息，不走缓存");
        Pageable pageable =PageRequest.of(pages-1,2);
        return trainerDao.findAll(pageable);
    }

    @Cacheable(value = "findTrainersByClub")
    public Page<Trainer> findByClub(String club,int pages){
        System.out.println("访问数据库来查询教练信息，不走缓存");
        Pageable pageable = PageRequest.of(pages-1,2);
        return trainerDao.findByClub(club,pageable);
    }

        @Cacheable(value = "findTrainersByClub")
    public Page<Trainer> findByName(int pages){
        System.out.println("访问数据库来查询教练信息，不走缓存");
        Pageable pageable = PageRequest.of(pages-1,2);
        return trainerDao.findAll(pageable);
    }

    @Cacheable(value = "findTrainersByClubAndGender")
    public Page<Trainer> findByClubAndGender(String club, String gender,int pages){
        System.out.println("访问数据库来查询教练信息，不走缓存");
        Pageable pageable =PageRequest.of(pages-1,2);
        return trainerDao.findByClubAndGender(club, gender,pageable);
    }

    @Cacheable(value = "findTrainersByGender")
    public Page<Trainer> findByGender(String gender,int pages){
        System.out.println("访问数据库来查询教练信息，不走缓存");
        Pageable pageable =PageRequest.of(pages-1,2);
        return trainerDao.findByGender(gender,pageable);
    }



    //用于教练详情的查询，不需要分页
    public Trainer findByPhone(Long phone){
        System.out.println("访问数据库来查询教练信息，不走缓存");
        return trainerDao.findByPhone(phone);
    }

    public Trainer findByPhoneAndPassword(Long phone, Integer password){
        return trainerDao.findByPhoneAndPassword(phone,password);
    }

    public Trainer findByName(String name){
        return trainerDao.findByName(name);
    }

    @Override
    public List<UserCourse> getUserCourse(Long phone){
        return userCourseDao.findByTrainerPhone(phone);
    }
}