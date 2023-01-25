package com.ssafy.moamoa.repository;

import com.ssafy.moamoa.domain.UserTechStack;
import com.ssafy.moamoa.repository.querydsl.SearchUserTechStackRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTechStackRepository extends JpaRepository<UserTechStack,Long>, SearchUserTechStackRepository {


}
