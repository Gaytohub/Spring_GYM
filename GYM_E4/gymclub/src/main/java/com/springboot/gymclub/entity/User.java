package com.springboot.gymclub.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    private Long phone;
    @Column(length = 32)
    private String name;
    @Column(length = 32)
    private String password;
    @Column(length = 16)
    private String gender;
    @CreatedDate
    private String registerDate;

    public Long getPhone() { return phone; }
    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
}

