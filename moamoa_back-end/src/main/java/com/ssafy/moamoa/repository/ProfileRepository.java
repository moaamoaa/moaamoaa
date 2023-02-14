package com.ssafy.moamoa.repository;

import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.User;
import com.ssafy.moamoa.repository.querydsl.ProfileRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long>, ProfileRepositoryCustom {

    Optional<Profile> findByNickname(String nickname);

    Optional<Profile> findByUser_Id(Long userId);

    Optional<Profile> findByUser(User user);


}
