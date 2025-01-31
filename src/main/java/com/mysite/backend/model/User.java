package com.mysite.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id //기본키 설정
    @GeneratedValue(strategy =  GenerationType.IDENTITY) //자동 증가 (Auto Increment)
    private Long id;
    private String username;
    private String name;

    private String email;

}
