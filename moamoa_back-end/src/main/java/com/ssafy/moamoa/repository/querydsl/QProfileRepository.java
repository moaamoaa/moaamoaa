package com.ssafy.moamoa.repository.querydsl;

import com.ssafy.moamoa.domain.entity.Profile;

public interface QProfileRepository {

	Profile getProfileById(Long profileId);

	void deleteProfileContextById(Long profileId);
}
