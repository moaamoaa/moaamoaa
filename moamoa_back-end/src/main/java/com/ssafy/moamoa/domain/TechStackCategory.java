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
public class TechStackCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
