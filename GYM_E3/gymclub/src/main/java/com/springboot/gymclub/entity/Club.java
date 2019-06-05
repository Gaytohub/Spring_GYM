package com.springboot.gymclub.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clubs")
public class Club extends ResourceSupport {//需要缓存的对象必须序列化
    @Id
    private Long phone;//七位数字的电话号码
    @Column(length = 32)
    private String name;
    @Column(length = 128)
    private String address;
    @Column(length = 256)
    private String intro;

    public Club(){

    }

    @JsonCreator
    public Club(@JsonProperty("phone") Long phone, @JsonProperty("name") String name,
                   @JsonProperty("address") String address, @JsonProperty("intro") String intro) {
        this.phone = phone;
        this.name = name;
        this.address = address;
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

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getIntro() {
        return intro;
    }
    public void setIntro(String intro) {
        this.intro = intro;
    }
}
