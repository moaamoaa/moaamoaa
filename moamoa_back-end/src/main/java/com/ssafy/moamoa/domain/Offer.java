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

@Entity
@Getter
public class Offer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "offer_no")
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "offer_send")
	private Project project;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "offer_receive")
	private User user;

	@Column(name = "apply_time")
	private Date time;

}
