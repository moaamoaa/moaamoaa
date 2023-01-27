package com.ssafy.moamoa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.ProfileSearchStatus;
import com.ssafy.moamoa.domain.dto.ProfileForm;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.User;
import com.ssafy.moamoa.repository.ProfileRepository;
import com.ssafy.moamoa.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class ProfileService {
	private static final String SUCCESS = "SUCCESS";
	private static final String FAIL = "FAIL";

	private ProfileRepository profileRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public ProfileService(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}

	public Optional<Profile> getProfile(Long userId) {
		return profileRepository.findById(userId);
	}

	public ProfileForm changeUserState(Long userId) {

		User user = userRepository.findById(userId).get();
		System.out.println(user.getId());

		Optional<Profile> profileList = profileRepository.findByUser_Id(user.getId());

		Profile profile = profileList.get();
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
		ProfileForm profileForm = new ProfileForm(profile.getId(), profile.getNickname(),
			String.valueOf(originSearchState), profile.getImg(), profile.getContext());

		return profileForm;
	}

	// public String changeSearchState(User user, String searchState) {
	//
	// 	switch (searchState) {
	// 		case "ALL":
	// 			searchState = String.valueOf(ProfileSearchStatus.PROJECT);
	// 			break;
	// 		case "PROJECT":
	// 			searchState = String.valueOf(ProfileSearchStatus.STUDY);
	// 			break;
	// 		case "STUDY":
	// 			searchState = String.valueOf(ProfileSearchStatus.NONE);
	// 			break;
	// 		case "NONE":
	// 			searchState = String.valueOf(ProfileSearchStatus.ALL);
	// 			break;
	// 	}
	// 	// Repository
	//
	// 	Profile profile = profileRepository.findBySearchState(searchState);
	// 	return SUCCESS;
	// }

}