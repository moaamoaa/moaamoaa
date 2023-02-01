package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import com.ssafy.moamoa.domain.dto.SearchCondition;
import com.ssafy.moamoa.domain.entity.Profile;

public interface ProfileRepositoryCustom {
	List<Profile> search(SearchCondition condition);
}
