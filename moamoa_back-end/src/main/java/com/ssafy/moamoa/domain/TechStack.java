package com.ssafy.moamoa.domain;

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

	// type
	@Column(name = "tech_stack_back")
	private String back = "no";
	@Column(name = "tech_stack_front")
	private String front = "no";
	@Column(name = "tech_stack_mobile")
	private String mobile = "no";
	@Column(name = "tech_stack_etc")
	private String etc = "no";

	//@NotNull
	@Column(name = "tech_stack_logo")
	private String logo;

	public TechStack(String name) {
		this.name = name;
	}

	public TechStack() {

	}

	//==set==//
	public void setBack(String back) {
		this.back = back;
	}

	public void setFront(String front) {
		this.front = front;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}
}
