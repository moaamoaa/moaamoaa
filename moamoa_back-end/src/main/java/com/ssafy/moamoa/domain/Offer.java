package com.ssafy.moamoa.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
public class Offer {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "offer_no")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "project_no")
    private Project project;

    //user랑 연관관계 맺어야함
//    @NotNull
//    @ManyToOne
//    @JoinColumn(name="user_no")
//    private User user;

}
