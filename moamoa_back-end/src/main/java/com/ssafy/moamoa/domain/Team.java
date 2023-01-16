package com.ssafy.moamoa.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
public class Team {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "team_no")
    private Long id;

    //연관관계 주인 설정
//    @ManyToOne
//    private User user;

    @OneToOne
    @MapsId //@id로 지정한 컬럼에 @OneToOne 이나 @ManyToOne 관계를 매핑시키는 역할
    private Project project;
    @NotNull
    @Column(columnDefinition = "TINYINT")
    private boolean isLeader;
}
