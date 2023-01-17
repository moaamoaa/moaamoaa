package com.ssafy.moamoa.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;

@Entity
@Getter
public class Review {
	@Id
	@GeneratedValue
	@Column(name = "review_no")
	private Long id;

	@Lob
	@Column(name = "review_context")
	private String context;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "review_time")
	private Date time;

	@ManyToOne()
	@JoinColumn(name = "review_receive")

	private User receiveUser;

	@ManyToOne()
	@JoinColumn(name = "review_send")

	private User sendUser;

}
