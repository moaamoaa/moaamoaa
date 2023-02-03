package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import com.ssafy.moamoa.domain.dto.ProfileResultDto;
import com.ssafy.moamoa.domain.dto.SearchCondition;
import com.ssafy.moamoa.domain.entity.Profile;

public interface ProfileRepositoryCustom {
	List<ProfileResultDto> search(SearchCondition condition);

	Profile getProfileById(Long profileId);

	Profile getProfileByName(String nickName);

	void deleteProfileContextById(Long profileId);


}
