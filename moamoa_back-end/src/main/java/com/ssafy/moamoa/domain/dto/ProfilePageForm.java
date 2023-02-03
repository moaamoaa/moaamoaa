package com.ssafy.moamoa.domain.dto;

import java.util.ArrayList;
import java.util.List;

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

	ProfileForm profileForm = new ProfileForm();

	private List<SidePjtForm> sidePjtFormList = new ArrayList<>();

	private List<ReviewForm> reviewFormList = new ArrayList<>();

	private List<TechStackForm> techStackFormList = new ArrayList<>();

	private List<SiteForm> siteFormList = new ArrayList<>();
}
