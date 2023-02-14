package com.ssafy.moamoa.domain.entity;

import java.time.LocalDateTime;

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
@NoArgsConstructor
@AllArgsConstructor
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
	private Profile receiveProfile;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "review_send")
	private Profile sendUser;

	public void setContext(String context) {
		this.context = context;
	}
}
