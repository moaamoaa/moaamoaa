package com.ssafy.moamoa.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfilePageForm {
	@JsonProperty("profile")
	ProfileForm profileForm = new ProfileForm();

	@JsonProperty("sideprojects")
	private List<SidePjtForm> sidePjtFormList = new ArrayList<>();

	@JsonProperty("reviews")
	private List<ReviewForm> reviewFormList = new ArrayList<>();

	@JsonProperty("techstacks")
	private List<TechStackForm> techStackFormList = new ArrayList<>();

	@JsonProperty("sites")
	private List<SiteForm> siteFormList = new ArrayList<>();

	@JsonProperty("areas")
	private List<AreaForm> areaList = new ArrayList<>();
}
