package com.ssafy.moamoa.controller;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.moamoa.dto.ProfileForm;
import com.ssafy.moamoa.dto.UserForm;
import com.ssafy.moamoa.service.ProfileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@Transactional
@RequestMapping("/profile")
public class ProfileController {
	private static final String SUCCESS = "SUCCESS";
	private static final String FAIL = "FAIL";

	private ProfileService profileService;

	@Autowired
	public ProfileController(ProfileService profileService) {
		this.profileService = profileService;
	}

	// 검색 여부 변경
	@PutMapping("/searchState")
	// input userId output String ProfileSearchStatus
	ResponseEntity<Map<String, Object>> updateSearchState(@RequestBody UserForm user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		log.info("userId log={}", user.getId());

		ProfileForm profileForm = profileService.changeUserState(user.getId());
		resultMap.put("message", profileForm.getProfileSearchStatus());

		status = HttpStatus.ACCEPTED;
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 마이페이지 접근
	@GetMapping("/mypage")
	public ResponseEntity<Map<String, Object>> goMyPage(@RequestBody String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 다른 사용자 페이지 접근
	@GetMapping("/user/{userId}")
	public ResponseEntity<Map<String, Object>> goUserPage(@PathVariable String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
