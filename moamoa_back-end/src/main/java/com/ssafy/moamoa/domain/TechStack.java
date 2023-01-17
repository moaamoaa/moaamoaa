package com.ssafy.moamoa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Entity
@Getter
public class TechStack {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tech_stack_no")
	private Long id;

	@Column(name = "tech_stack_name")
	@NotNull
	private String name;

	@Column(name = "tech_stack_logo")
	@NotNull
	private byte[] logo;

}
