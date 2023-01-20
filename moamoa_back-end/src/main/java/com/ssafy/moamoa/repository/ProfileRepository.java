package com.ssafy.moamoa.repository;

import com.ssafy.moamoa.domain.Profile;
import com.ssafy.moamoa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByNickname(String nickname);

    Profile findBySearchState(String searchState);

    Optional<Profile> findByUser_Id(Long userId);

    Optional<Profile> findByUser(User user);
}
