package com.ssafy.moamoa.domain;

import static javax.persistence.FetchType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Entity
@Getter
public class ProjectArea {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_area_no")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "area_no")
	@NotNull
	private Area area;

	// project mapping
	@NotNull
	@ManyToOne
	@JoinColumn(name = "project_no")
	private Project project;
}
