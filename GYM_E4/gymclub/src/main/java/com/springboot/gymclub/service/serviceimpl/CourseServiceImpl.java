package com.springboot.gymclub.service.serviceimpl;

import com.google.common.util.concurrent.RateLimiter;
import com.springboot.gymclub.dao.CourseDao;
import com.springboot.gymclub.dao.TrainerDao;
import com.springboot.gymclub.dao.UserCourseDao;
import com.springboot.gymclub.dao.UserDao;
import com.springboot.gymclub.entity.Course;
import com.springboot.gymclub.entity.Trainer;
import com.springboot.gymclub.entity.User;
import com.springboot.gymclub.entity.UserCourse;
import com.springboot.gymclub.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private TrainerDao trainerDao;
    @Autowired
    private UserCourseDao userCourseDao;


    @Override
    public int findTotalPage() {
        List<Course> list = courseDao.findAll();
        if(list.size()%2==0){
            return list.size()/2;
        }else{
            return list.size()/2+1;
        }
    }

    @Cacheable(value = "findAllCourses")
    @Override
    public Flux<Course> getAllCourses(int pages) {
        Pageable pageable = PageRequest.of(pages-1,1);
        return Flux.fromIterable(courseDao.findAll(pageable));
    }

    @Cacheable(value = "findCoursesByTrainer")
    @Override
    public Flux<Course> findByTrainer(String trainer, int pages) {
        Pageable pageable = PageRequest.of(pages-1,1);
        return Flux.fromIterable(courseDao.findByTrainer(trainer,pageable));
    }

    @Cacheable(value = "findCoursesByName")
    @Override
    public Flux<Course> findByName(String name,int pages) {
        Pageable pageable = PageRequest.of(pages-1,1);
        return Flux.fromIterable(courseDao.findByName(name,pageable));
    }

    @Cacheable(value = "findCoursesByNameAndTrainer")
    @Override
    public Flux<Course> findByNameAndTrainer(String name, String trainer, int pages){
        Pageable pageable = PageRequest.of(pages-1,1);
        return Flux.fromIterable(courseDao.findByNameAndTrainer(name, trainer, pageable));
    }

    /**
     * 每秒钟只发出5个令牌，拿到令牌的请求才可以进入下一个业务
     */
    private RateLimiter seckillRateLimiter = RateLimiter.create(5);

    @Override
    public boolean tryAcquireSeckill() {
        return seckillRateLimiter.tryAcquire();
    }

    @Override
    public UserCourse buildUserCourse(Long userPhone,String trainerName,Integer courseId,String courseName) {
        User user  = userDao.findByPhone(userPhone);
        UserCourse userCourse = new UserCourse();
        Trainer trainer = trainerDao.findByName(trainerName);

        userCourse.setCourseId(Integer.valueOf(courseId));
        userCourse.setCourse(courseName);
        userCourse.setTrainer(trainer.getName());
        userCourse.setTrainerPhone(trainer.getPhone());
        userCourse.setUser(user.getName());
        userCourse.setUserPhone(user.getPhone());
        userCourse.setStatus("待通过");
        return userCourse;
    }

    @Async
    @Override
    public void operate(Integer courseId,String status) {
        System.out.println("线程"+Thread.currentThread().getName()+"正在运行");
        Integer id = Integer.valueOf(courseId);
        UserCourse userCourse = userCourseDao.findById(id).get();

        userCourse.setId(userCourse.getId());
        userCourse.setCourseId(userCourse.getCourseId());
        userCourse.setCourse(userCourse.getCourse());
        userCourse.setTrainer(userCourse.getTrainer());
        userCourse.setTrainerPhone(userCourse.getTrainerPhone());
        userCourse.setUser(userCourse.getUser());
        userCourse.setUserPhone(userCourse.getUserPhone());
        userCourse.setStatus(status);
        userCourseDao.save(userCourse);
    }
}
