package com.ssafy.moamoa.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Entity
@Getter
public class Offer {
	@Id
	@GeneratedValue
	@Column(name = "offer_no")
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "offer_send")
	private Project project;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "offer_receive")
	private User user;

	@Column(name = "apply_time", columnDefinition = "TIMESTAMP")
	private LocalDateTime time;

}
