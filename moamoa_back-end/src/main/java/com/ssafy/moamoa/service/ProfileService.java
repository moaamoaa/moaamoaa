package com.ssafy.moamoa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.Profile;
import com.ssafy.moamoa.repository.ProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfileService {
	private final ProfileRepository profileRepository;

	// 회원 가입 - profile
	public Profile save(Profile profile) {
		validateDuplicateUserNickname(profile); //중복 회원 검증
		return profileRepository.save(profile);
	}

	// 닉네임 중복 조회
	private void validateDuplicateUserNickname(Profile profile) {
		List<Profile> findProfiles = profileRepository.findByNickname(profile.getNickname());
		if (!findProfiles.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}
}
