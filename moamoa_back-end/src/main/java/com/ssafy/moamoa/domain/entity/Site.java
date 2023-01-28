package com.ssafy.moamoa.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;

@Entity
@Getter
public class Site {

	@Id
	@GeneratedValue
	@Column(name = "site_no")
	private Long id;

	@NotNull
	@Column(name = "site_name")
	private String name;

	@Column(name = "site_logo")
	private String logo;
}
