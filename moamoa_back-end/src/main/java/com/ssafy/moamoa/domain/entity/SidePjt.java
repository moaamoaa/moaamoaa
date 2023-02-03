package com.ssafy.moamoa.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SidePjt {
	@Id
	@GeneratedValue
	@Column(name = "sidepjt_no")
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profile_no")
	private Profile profile;

	@NotNull
	@Column(name = "sidepjt_name")
	private String name;

	@Column(name = "sidepjt_context", columnDefinition = "TEXT")
	private String context;

	@Column(name = "sidepjt_year")
	private String year;
}
