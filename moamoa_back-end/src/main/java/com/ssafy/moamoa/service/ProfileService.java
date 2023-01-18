package com.ssafy.moamoa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.Profile;
import com.ssafy.moamoa.exception.DuplicateProfileNicknameException;
import com.ssafy.moamoa.repository.ProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfileService {
	private final ProfileRepository profileRepository;
}
