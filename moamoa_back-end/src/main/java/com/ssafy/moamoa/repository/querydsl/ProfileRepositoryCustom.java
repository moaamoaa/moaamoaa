package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import com.ssafy.moamoa.domain.ProfileOnOffStatus;
import com.ssafy.moamoa.domain.dto.ProfileResultDto;
import com.ssafy.moamoa.domain.dto.SearchCondition;
import com.ssafy.moamoa.domain.entity.Profile;
import org.springframework.data.domain.Pageable;

public interface ProfileRepositoryCustom {
	List<ProfileResultDto> search(SearchCondition condition, Long cursorId, Pageable pageable);

	Profile getProfileById(Long profileId);

	void deleteProfileContextById(Long profileId);

	String setProfileOnOffStatus(Long profileId, ProfileOnOffStatus status);

	void setProfile(Profile profile);

	Profile getProfileByUserId(Long userId);


}
