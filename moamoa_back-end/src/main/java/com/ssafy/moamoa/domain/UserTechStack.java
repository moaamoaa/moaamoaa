package com.ssafy.moamoa.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class UserTechStack {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "user_stck_no")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "tech_stack_no")
    @NotNull
    private TechStack techStack;

    // user mapping
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_no")
    @NotNull
    private User user;
}
