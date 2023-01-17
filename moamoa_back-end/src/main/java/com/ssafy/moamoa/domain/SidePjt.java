package com.ssafy.moamoa.domain;

import java.time.Year;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.Getter;

@Entity
@Getter

public class SidePjt {
	@Id
	@GeneratedValue
	@Column(name = "sidepjt_no")
	private Long id;

	@ManyToOne()
	@JoinColumn(name = "user_no")

	private User user;

	@Column(name = "sidepjt_name")
	private String name;

	@Lob
	@Column(name = "sidepjt_context", length = 1000)
	private String context;

	@Column(name = "sidepjt_year")
	private Year year;
}
