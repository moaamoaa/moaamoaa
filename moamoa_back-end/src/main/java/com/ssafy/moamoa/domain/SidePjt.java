package com.ssafy.moamoa.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter

public class SidePjt {
    @Id
    @GeneratedValue
    @Column(name="sidepjt_no")
    private Long id;

    @ManyToOne()
    @JoinColumn(name="user_no")

    private User user;

    @Column(name="sidepjt_name")
    private String name;

    @Lob
    @Column(name="sidepjt_context",length = 1000)
    private String context;
}
