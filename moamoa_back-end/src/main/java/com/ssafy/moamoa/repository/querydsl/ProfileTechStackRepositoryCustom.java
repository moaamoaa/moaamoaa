package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import com.ssafy.moamoa.domain.entity.ProfileTechStack;

public interface ProfileTechStackRepositoryCustom {

	List<ProfileTechStack> getProfileTechStacks(Long profileId);

	List<ProfileTechStack> getProfileTechStacksByOrderAsc(Long profileId);

	Long deleteProfileTechStackByOrder(int order);

	ProfileTechStack getProfileTechStack(Long profileId, Long techStackId);


	// List<ProfileTechStack> getAllUserTechStackByOrder(Long userId);
	//
	// Long deleteAllProfileStackById(Long profileId);
}
