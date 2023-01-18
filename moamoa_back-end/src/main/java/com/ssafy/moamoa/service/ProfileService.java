package com.ssafy.moamoa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.Profile;
import com.ssafy.moamoa.domain.ProfileSearchStatus;
import com.ssafy.moamoa.domain.User;
import com.ssafy.moamoa.repository.ProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfileService {
	private static final String SUCCESS = "SUCCESS";
	private static final String FAIL = "FAIL";

	@Autowired
	private final ProfileRepository profileRepository;

	public String changeSearchState(User user, String searchState) {

		switch (searchState) {
			case "ALL":
				searchState = String.valueOf(ProfileSearchStatus.PROJECT);
				break;
			case "PROJECT":
				searchState = String.valueOf(ProfileSearchStatus.STUDY);
				break;
			case "STUDY":
				searchState = String.valueOf(ProfileSearchStatus.NONE);
				break;
			case "NONE":
				searchState = String.valueOf(ProfileSearchStatus.ALL);
				break;
		}
		// Repository

		Profile profile = profileRepository.findBySearchState(searchState);
		return SUCCESS;
	}
}
