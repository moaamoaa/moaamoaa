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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SidePjtTechStack {

	@Id
	@GeneratedValue
	@Column(name = "sidepjt_tech_stck_no")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tech_stack_no")
	@NotNull
	private TechStack techStack;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sidepjt_no")
	//@NotNull
	private SidePjt sidePjt;

	@Column(name="sidepjt_order")
	private int order;

	public void setTechStack(TechStack techStack) {
		this.techStack = techStack;
	}

	public void setSidePjt(SidePjt sidePjt) {
		this.sidePjt = sidePjt;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}

