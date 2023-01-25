package com.ssafy.moamoa.repository.querydsl;

import com.ssafy.moamoa.domain.UserTechStack;

import java.util.List;

public interface SearchUserTechStackRepository {

    List<UserTechStack> getAllUserTechStackByName(Long userId);

    Long deleteAllUserStackById(Long userId);
}
