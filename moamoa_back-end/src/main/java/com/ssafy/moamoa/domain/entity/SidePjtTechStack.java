package com.ssafy.moamoa.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Entity
@Getter
public class SidePjtTechStack {

	@Id
	@GeneratedValue
	@Column(name = "sidepjt_tech_stack_no")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tech_stack_no")
	@NotNull
	private TechStack techStack;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sidepjt_no")
	@NotNull
	private SidePjt sidePjt;
}

