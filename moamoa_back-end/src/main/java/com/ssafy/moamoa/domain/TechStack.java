package com.ssafy.moamoa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Entity
@Getter
public class TechStack {

	@Id
	@GeneratedValue
	@Column(name = "tech_stack_no")
	private Long id;

	@NotNull
	@Column(name = "tech_stack_name")
	private String name;

	@NotNull
	@Column(name = "tech_stack_logo")
	private String logo;

}
