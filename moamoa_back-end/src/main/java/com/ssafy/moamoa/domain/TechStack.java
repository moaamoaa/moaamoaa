package com.ssafy.moamoa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class TechStack {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "tech_stack_no")
    private Long id;

    @Column(name = "tech_stack_name")
    @NotNull
    private String name;

    @Column(name = "tech_stack_logo")
    @NotNull
    private byte[] logo;

/*    @JsonIgnore
    @OneToMany(mappedBy = "techStack")
    private List<TechStackCategory> techStackCategories = new ArrayList<>();*/

}
