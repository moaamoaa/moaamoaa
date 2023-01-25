package com.ssafy.moamoa.domain;

import static javax.persistence.FetchType.*;

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
import org.springframework.web.bind.annotation.PathVariable;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTechStack {

	@Id
	@GeneratedValue
	@Column(name = "user_stck_no")
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tech_stack_no")
	private TechStack techStack;

	@NotNull
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_no")
	private User user;
}

