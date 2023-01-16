package com.ssafy.moamoa.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class ProjectTechStack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_stck_no")
    private Long id;

    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "tech_stack_no")
    private TechStack techStack;

    // project mapping

    @NotNull
    @ManyToOne
    @JoinColumn(name = "project_no")
    private Project project;
}
