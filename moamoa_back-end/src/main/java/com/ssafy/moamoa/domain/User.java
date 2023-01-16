package com.ssafy.moamoa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "USER")
public class User {


    @Id @GeneratedValue
    @Column(name="user_no")
    private Long id;

    @Column(name ="user_email" ,length = 100)
    private String email;

    @Setter
    @Column(name = "user_pwd",length =100)
    private String password;

    @Setter
    @Column(name = "user_refresh_token",length = 100)
    private String refreshToken;


}
