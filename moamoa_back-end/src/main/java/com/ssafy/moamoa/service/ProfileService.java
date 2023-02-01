package com.ssafy.moamoa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.ProfileSearchStatus;
import com.ssafy.moamoa.domain.dto.ContextForm;
import com.ssafy.moamoa.domain.dto.ProfileForm;
import com.ssafy.moamoa.domain.dto.SidePjtForm;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.SidePjt;
import com.ssafy.moamoa.repository.ProfileRepository;
import com.ssafy.moamoa.repository.SideProjectRepository;
import com.ssafy.moamoa.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class ProfileService {
	private static final String SUCCESS = "SUCCESS";
	private static final String FAIL = "FAIL";

	private final ProfileRepository profileRepository;

	private final UserRepository userRepository;

	private final SideProjectRepository sideProjectRepository;

	public ProfileForm getProfile(Long profileId) {

		Profile profile = profileRepository.findById(profileId).get();
		ProfileForm profileForm = ProfileForm.builder()
			.id(profile.getId())
			.nickname(profile.getNickname())
			.context(profile.getContext())
			.img(profile.getImg())
			.profileOnOffStatus(profile.getProfileOnOffStatus() + "")
			.profileSearchStatus(profile.getSearchState() + "")
			.build();
		return profileForm;
	}

	public String changeUserState(Long profileId) {

		Profile profile = profileRepository.findById(profileId).get();
		// System.out.println(profile.getId());

		ProfileSearchStatus originSearchState = profile.getSearchState();
		String searchState = String.valueOf(originSearchState);
		switch (searchState) {
			case "ALL":
				originSearchState = ProfileSearchStatus.PROJECT;
				break;
			case "PROJECT":
				originSearchState = ProfileSearchStatus.STUDY;
				break;
			case "STUDY":
				originSearchState = ProfileSearchStatus.NONE;
				break;
			case "NONE":
				originSearchState = ProfileSearchStatus.ALL;
				break;
		}
		profile.setSearchState(originSearchState);
		profileRepository.save(profile);

		return String.valueOf(profile.getSearchState());
	}

	public String modifyOnOffStatus(String status) {
		return "SUCCESS";
	}

	public ContextForm addContext(Long profileId, String context) {
		Profile profile = profileRepository.getProfileById(profileId);
		profile.setContext(context);

		profileRepository.save(profile);

		return ContextForm.builder().Context(context).build();
	}

	public String deleteContext(Long profileId) {
		profileRepository.deleteProfileContextById(profileId);
		Profile profile = profileRepository.getProfileById(profileId);

		if (profile.getContext() == null) {
			return SUCCESS;
		} else
			return FAIL;

	}
	// 사이드 프로젝트

	public List<SidePjtForm> getSideProjects(Long profileId) {
		List<SidePjt> sidePjtList = sideProjectRepository.getSideProjects(profileId);
		List<SidePjtForm> sidePjtFormList = new ArrayList<>();
		for (SidePjt sidePjt : sidePjtList) {
			SidePjtForm sidePjtForm = SidePjtForm.builder()
				.name(sidePjt.getName())
				.year(sidePjt.getYear())
				.context(sidePjt.getContext())
				.build();

			sidePjtFormList.add(sidePjtForm);
		}

		return sidePjtFormList;
	}

	public String addSidePjt(Long profileId, SidePjtForm sidePjtForm) {
		SidePjt sidePjt = SidePjt.builder()
			.profile(profileRepository.getProfileById(profileId))
			.name(sidePjtForm.getName())
			.year(sidePjtForm.getYear())
			.context(sidePjtForm.getContext())
			.build();

		sideProjectRepository.save(sidePjt);

		return SUCCESS;
	}
}