package com.ssafy.moamoa.domain.entity;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.moamoa.repository.ProfileRepository;

class ProfileTest {
	@Autowired
	private ProfileRepository profileRepository;

	@Test
	public void ProfileRepositoryTest() {
		Optional<Profile> profile = profileRepository.findById(1L);
		System.out.println(profile.get().getNickname());
	}
}