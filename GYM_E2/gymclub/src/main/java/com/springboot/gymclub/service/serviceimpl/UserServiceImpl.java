package com.springboot.gymclub.service.serviceimpl;

import com.google.common.util.concurrent.RateLimiter;
import com.springboot.gymclub.dao.UserDao;
import com.springboot.gymclub.entity.User;
import com.springboot.gymclub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;

    /**
     * 每秒钟只发出5个令牌，拿到令牌的请求才可以进入下一个业务
     */
    private RateLimiter seckillRateLimiter = RateLimiter.create(5);

    @Override
    public boolean tryAcquireSeckill() {
        return seckillRateLimiter.tryAcquire();
    }

    public User findByPhone(Long phone){
        return userDao.findByPhone(phone);
    }

    @Override
    public User findByPhoneAndPassword(Long phone, String password) {
        return userDao.findByPhoneAndPassword(phone,password);
    }

    @Override
    public void addUser(String username, String password, String gender, Long phone) {
        User user=new User();
        user.setName(username);
        user.setPassword(password);
        user.setGender(gender);
        user.setPhone(phone);
        userDao.save(user);
    }
}
