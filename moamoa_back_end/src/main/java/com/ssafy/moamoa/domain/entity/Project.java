package com.ssafy.moamoa.domain.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.ssafy.moamoa.domain.ProjectCategory;
import com.ssafy.moamoa.domain.ProjectStatus;

import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
public class Project {
	@Id
	@GeneratedValue
	@Column(name = "project_no")
	private Long id;

	@NotNull
	@Column(name = "project_title")
	private String title;

	@Column(name = "project_contents", columnDefinition = "TEXT")
	private String contents;

	@Column(name = "project_img")
	private String img;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "project_onoffline")
	private ProjectStatus onoffline;  //이름

	@NotNull
	@Column(name = "project_hit")
	@ColumnDefault("0")
	private int hit;

	@NotNull
	@Column(name = "project_cnt_apply")
	@ColumnDefault("0")
	private int countApply;

	@NotNull
	@Column(name = "project_total_people")
	@ColumnDefault("1")
	private int totalPeople;

	@NotNull
	@Column(name = "project_current_people")
	@ColumnDefault("1")
	private int currentPeople;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "project_category")
	private ProjectCategory category;

	@Column(name = "project_start_date")
	@NotNull
	private LocalDate startDate;

	@Column(name = "project_end_date")
	@NotNull
	private LocalDate endDate;

	@Column(name = "project_create_date")
	@NotNull
	private LocalDate createDate;

	@NotNull
	@ColumnDefault("0")
	@Column(name = "project_state")
	private boolean isLocked = false;

	public Project() {
	}

	public Project(Long id, String title, String contents, String img, ProjectStatus onoffline, int hit, int countApply,
		int totalPeople, int currentPeople, ProjectCategory category, LocalDate startDate, LocalDate endDate,
		LocalDate createDate, boolean isLocked) {
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.img = img;
		this.onoffline = onoffline;
		this.hit = hit;
		this.countApply = countApply;
		this.totalPeople = totalPeople;
		this.currentPeople = currentPeople;
		this.category = category;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createDate = createDate;
		this.isLocked = isLocked;
	}

	//==set==//
	public void setTitle(String title) {
		this.title = title;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public void setOnoffline(ProjectStatus onoffline) {
		this.onoffline = onoffline;
	}

	public void setTotalPeople(int totalPeople) {
		this.totalPeople = totalPeople;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public void setCountApply(int countApply) {
		this.countApply = countApply;
	}

	public void setCurrentPeople(int currentPeople){this.currentPeople = currentPeople;}

	public void setLocked(boolean isLocked){this.isLocked = isLocked;}

	public void setHit(int hit){this.hit = hit;}
}
