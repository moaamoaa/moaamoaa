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
public class Category {

	@Id
	@GeneratedValue
	@Column(name = "category_no")
	private Long id;

	@NotNull
	@Column(name = "category_name")
	private String name;

	public Category(String name) {
		this.name = name;
	}

	public Category() {
	}
}
