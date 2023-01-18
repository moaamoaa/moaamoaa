package com.ssafy.moamoa.service;

import com.ssafy.moamoa.domain.Profile;
import com.ssafy.moamoa.dto.ProfileForm;

import java.util.List;
import java.util.Optional;

public interface ProfileService {

	Optional<Profile> getProfile(Long userId);

	ProfileForm changeUserState(Long userId);




}
