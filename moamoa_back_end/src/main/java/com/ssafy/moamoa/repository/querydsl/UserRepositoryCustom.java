package com.ssafy.moamoa.repository.querydsl;

import com.ssafy.moamoa.domain.entity.User;

public interface UserRepositoryCustom {

    User getUserById(Long userId);
}
