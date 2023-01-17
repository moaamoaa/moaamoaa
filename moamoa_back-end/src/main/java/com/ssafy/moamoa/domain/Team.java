package com.ssafy.moamoa.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_no")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "project_no")
    private Project project;
    @NotNull
    @Column(columnDefinition = "TINYINT")
    private boolean isLeader;
}
