package com.ssafy.moamoa.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class TechStack {

	@Id
	@GeneratedValue
	@Column(name = "tech_stack_no")
	private Long id;

	@NotNull
	@Column(name = "tech_stack_name")
	private String name;

	//@NotNull
	@Column(name = "tech_stack_logo")
	private String logo;

	public TechStack(String name, String logo) {
		this.name = name;
		this.logo = logo;
	}

	public TechStack() {
	}
}
