package com.ssafy.moamoa.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class UserArea {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="project_area_no")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "area_no")
    @NotNull
    private Area area;

    // user mapping
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_no")
    @NotNull
    private User user;
}

