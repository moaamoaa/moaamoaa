package com.ssafy.moamoa.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class TechStackCategory {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "tech_stack_category_no")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_no")
    @NotNull
    private Category category;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "tech_stack__no")
    @NotNull
    private TechStack techStack;

}
