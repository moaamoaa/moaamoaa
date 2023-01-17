package com.ssafy.moamoa.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NonNull;

@Entity
@Getter
public class Apply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "apply_no")
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "apply_receive")
	private Project project;

	@NonNull
	@ManyToOne
	@JoinColumn(name = "apply_send")
	private User user;

	@Column(name = "apply_time")
	private Date time;
}
