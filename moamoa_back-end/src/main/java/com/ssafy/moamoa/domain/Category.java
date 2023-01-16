package com.ssafy.moamoa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "category_no")
    private Long id;

    @Column(name = "category_name")
    @NotNull
    private String name;

/*    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<TechStackCategory> techStackCategories = new ArrayList<>();*/
}
