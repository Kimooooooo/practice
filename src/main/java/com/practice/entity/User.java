package com.practice.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_pass")
    private String password;
    @Column(name = "user_name")
    private String name;
    @Column(name = "user_age")
    private int age;
    @Column(name = "user_rating")
    private String rating; //유저 등급
    @Column(name = "user_authority")
    private String authority;//권한 Y 혹은 N
    @Column(name = "user_join")
    private LocalDateTime joindate;//가입일

    public User() {

    }
    public User(Long id,String userId,String password,String name,int age,String rating, String authority,LocalDateTime joindate){
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.age = age;
        this.rating = rating;
        this.authority = authority;
        this.joindate = joindate;
    }
    public User(Long id, String userId, String password, String name, int age) { //선택적 필드만 갖는 생성자
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.age = age;
    }
    public void updateFields(String password, String name, int age) {
        this.password = password;
        this.name = name;
        this.age = age;
    }
}
