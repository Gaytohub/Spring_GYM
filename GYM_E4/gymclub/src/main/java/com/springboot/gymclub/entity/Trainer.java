package com.springboot.gymclub.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;

@Entity
@Table(name = "trainers")
public class Trainer extends ResourceSupport {
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
    @Column
    private Integer password;

    public Trainer(){

    }

    @JsonCreator
    public Trainer(@JsonProperty("phone") Long phone, @JsonProperty("name") String name,
                   @JsonProperty("gender") String gender, @JsonProperty("age") int age,
                   @JsonProperty("club") String club, @JsonProperty("intro") String intro) {
        this.phone = phone;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.club = club;
        this.intro = intro;
    }

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

    public Integer getPassword() {
        return password;
    }
    public void setPassword(Integer password) {
        this.password = password;
    }
}
