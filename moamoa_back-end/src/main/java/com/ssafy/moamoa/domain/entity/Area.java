package com.ssafy.moamoa.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Area {

	@Id
	@GeneratedValue
	@Column(name = "area_no")
	private Long id;

	@NotNull
	@Column(name = "area_name")
	private String name;

	public Area(String name) {
		this.name = name;
	}

}
