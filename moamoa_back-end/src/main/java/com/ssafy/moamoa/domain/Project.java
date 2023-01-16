package com.ssafy.moamoa.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_no")
    private Long id;

    @NotNull
    @Column(name = "project_title")
    private String title;

    @Column(name = "project_contents")
    private String contents;

    @Column(name = "project_img")
    private String img;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "project_onoffline")
    private ProjectStatus onoffline;  //이름

    @NotNull
    @Column(name = "project_hit")
    private int hit;
    @NotNull
    @Column(name = "project_cnt_offer")
    private int countOffer;
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "project_category")
    private ProjectCategory category;

    @Column(name = "project_start_date")
    @NotNull
    private LocalDate startDate;
    @Column(name = "project_end_date")
    @NotNull
    private LocalDate endDate;
    @Column(name = "project_create_date")
    @NotNull
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "project")
    private List<ProjectArea> areas = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    @NotNull
    private List<ProjectTechStack> techStacks = new ArrayList<>();
}
