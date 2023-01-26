package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import com.ssafy.moamoa.domain.UserTechStack;

public interface QUserTechStackRepository {

	List<UserTechStack> getAllUserTechStackByOrder(Long userId);

	Long deleteAllUserStackById(Long userId);
}
