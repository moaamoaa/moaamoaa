package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import com.ssafy.moamoa.domain.entity.ProfileTechStack;

public interface QUserTechStackRepository {

	List<ProfileTechStack> getAllUserTechStackByOrder(Long userId);

	Long deleteAllUserStackById(Long userId);
}
