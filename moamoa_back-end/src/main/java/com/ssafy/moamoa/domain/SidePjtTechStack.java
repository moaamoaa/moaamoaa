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
public class SidePjtTechStack {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sidepjt_tech_stck_no")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "tech_stack_no")
	@NotNull
	private TechStack techStack;

	// sidepjt mapping
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "sidepjt_no")
	@NotNull
	private SidePjt sidePjt;
}

