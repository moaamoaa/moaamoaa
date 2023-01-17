package com.ssafy.moamoa.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;

import lombok.Getter;

@Entity
@Getter
public class Project {
	@Id
	@GeneratedValue
	@Column(name = "project_no")
	private Long id;

	@NotNull
	@Column(name = "project_title")
	private String title;

	@Column(name = "project_contents", columnDefinition = "TEXT")
	private String contents;

	@Column(name = "project_img")
	private String img;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "project_onoffline")
	private ProjectStatus onoffline;  //이름

	@NotNull
	@Column(name = "project_hit")
	@ColumnDefault("0")
	private int hit;
	@NotNull
	@Column(name = "project_cnt_offer")
	@ColumnDefault("0")
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
	@Column(name = "project_create_date", columnDefinition = "TIMESTAMP")
	@NotNull
	private LocalDateTime createDate;

	@OneToMany(mappedBy = "project")
	private List<ProjectArea> areas = new ArrayList<>();

	@OneToMany(mappedBy = "project")
	@NotNull
	private List<ProjectTechStack> techStacks = new ArrayList<>();
}
