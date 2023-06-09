package com.ssafy.moamoa.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class ProjectArea {

	@Id
	@GeneratedValue
	@Column(name = "project_area_no")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_no")
	@NotNull
	private Area area;

	// project mapping
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_no")
	private Project project;

	public ProjectArea() {
	}
}
