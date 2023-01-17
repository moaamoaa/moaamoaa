package com.ssafy.moamoa.domain;

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
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "team_no")
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_no")
	private User user;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "project_no")
	private Project project;
	@NotNull
	@Column(columnDefinition = "TINYINT")
	private boolean isLeader;
}
