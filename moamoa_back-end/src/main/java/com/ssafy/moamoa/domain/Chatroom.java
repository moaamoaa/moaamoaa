package com.ssafy.moamoa.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Chatroom {

    @Id
    @GeneratedValue
    @Column(name="chatroom_no")
    private Long id;

    @Column(name="chatroom_title",length = 20)
    private String title;

    @OneToOne
    @JoinColumn(name="chatroom_user_one")
    private User userOne;

    @OneToOne
    @JoinColumn(name="hatroom_user_two")

    private User userTwo;





}
