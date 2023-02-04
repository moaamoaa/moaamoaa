package com.ssafy.moamoa.service;

import com.ssafy.moamoa.domain.dto.ProfileSearchStatusForm;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.ProfileOnOffStatus;
import com.ssafy.moamoa.domain.ProfileSearchStatus;
import com.ssafy.moamoa.domain.dto.ContextForm;
import com.ssafy.moamoa.domain.dto.ProfileForm;
import com.ssafy.moamoa.domain.dto.ProfilePageForm;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.repository.ProfileRepository;
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

	private final TechStackService techStackService;

	private final ReviewService reviewService;

	private final SideProjectService sideProjectService;

	private final SiteService siteService;

	private final AreaService areaService;


	private final ProfileRepository profileRepository;

	private final UserRepository userRepository;




	public ProfilePageForm getProfile(Long profileId) {


		Profile profile = profileRepository.getProfileById(profileId);
		log.info(profile.getNickname());

		ProfileForm profileForm = ProfileForm.builder()
			.id(profile.getId())
			.nickname(profile.getNickname())
			.context(profile.getContext())
			.img(profile.getImg())
			.profileOnOffStatus(profile.getProfileOnOffStatus() + "")
			.profileSearchStatus(profile.getSearchState() + "")
			.build();


		ProfilePageForm profilePageForm = ProfilePageForm.builder()
			.profileForm(profileForm)
			.techStackFormList(techStackService.getProfileTechStacks(profileId))
			.reviewFormList(reviewService.getReviews(profileId))
			.sidePjtFormList(sideProjectService.getSideProjects(profileId))
			.siteFormList(siteService.getProfileSites(profileId))
			.build();

		return profilePageForm;
	}

	public ProfilePageForm modifyProfile(Long profileId, ProfilePageForm profilePageForm)
	{
	// Profile
	Profile profile = profileRepository.getProfileById(profileId);
	ProfileForm profileForm = profilePageForm.getProfileForm();

	Profile inputProfile = Profile.builder()
		.id(profileId)
		.profileOnOffStatus(ProfileOnOffStatus.valueOf(profileForm.getProfileOnOffStatus()))
		.nickname(profileForm.getNickname()).build();
	profileRepository.setProfile(inputProfile);

	// TechStack

	techStackService.modifyProfileTechStack(profileId,profilePageForm.getTechStackFormList());

	// Site

		siteService.modifyProfileSite(profileId, profilePageForm.getSiteFormList());



	// Area

		areaService.modifyProfileAreas(profileId,profilePageForm.getAreaList());


	return profilePageForm;
	}

	public ProfileSearchStatusForm changeUserSearchState(Long profileId) {

		Profile profile = profileRepository.findById(profileId).get();

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

		return	ProfileSearchStatusForm.builder().id(profile.getId()).status(originSearchState.toString()).build();
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

}