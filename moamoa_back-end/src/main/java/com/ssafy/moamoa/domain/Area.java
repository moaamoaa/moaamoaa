package com.ssafy.moamoa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Area {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "area_no")
    private Long id;

    @Column(name = "area_name")
    @NotNull
    private String name;

/*    @JsonIgnore
    @OneToMany(mappedBy = "area")
    private List<ProjectArea> projectAreas = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "area")
    private List<UserArea> userAreas = new ArrayList<>();*/
}
