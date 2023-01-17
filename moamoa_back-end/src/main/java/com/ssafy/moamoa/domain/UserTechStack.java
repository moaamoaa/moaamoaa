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
public class UserTechStack {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_stck_no")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "tech_stack_no")
	@NotNull
	private TechStack techStack;
    
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_no")
	@NotNull
	private User user;
}

