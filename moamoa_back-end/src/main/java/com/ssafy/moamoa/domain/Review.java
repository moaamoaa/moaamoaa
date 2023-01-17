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
public class Review {
	@Id
	@GeneratedValue
	@Column(name = "review_no")
	private Long id;

	@NotNull
	@Column(name = "review_context", columnDefinition = "TEXT")
	private String context;

	@NotNull
	@Column(name = "review_time", columnDefinition = "TIMESTAMP")
	private LocalDateTime time;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "review_receive")
	private User receiveUser;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "review_send")
	private User sendUser;

}
