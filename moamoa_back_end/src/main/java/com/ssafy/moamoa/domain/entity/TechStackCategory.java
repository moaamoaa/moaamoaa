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
public class TechStackCategory {

	@Id
	@GeneratedValue
	@Column(name = "tech_stack_category_no")
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_no")
	private Category category;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tech_stack__no")
	private TechStack techStack;

	public void setTechstack(TechStack techStack) {
		this.techStack = techStack;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
