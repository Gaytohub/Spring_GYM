package com.springboot.gymclub.entity;

import org.springframework.cache.annotation.CacheConfig;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "trainers")
//@CacheConfig(cacheNames = "trainers")
public class Trainer{
    @Id
    private Long phone;
    @Column(length = 32)
    private String name;
    @Column(length = 16)
    private String gender;
    @Column(length = 16)
    private int age;
    @Column(length = 16)
    private String club;
    @Column(length = 256)
    private String intro;

    public Long getPhone() {
        return phone;
    }
    public void setPhone(Long phone) { this.phone = phone; }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getClub() {
        return club;
    }
    public void setClub(String club) {
        this.club = club;
    }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIntro() {
        return intro;
    }
    public void setIntro(String intro) {
        this.intro = intro;
    }
}
