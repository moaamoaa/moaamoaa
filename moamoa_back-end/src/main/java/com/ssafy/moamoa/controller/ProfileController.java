package com.ssafy.moamoa.controller;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import com.ssafy.moamoa.dto.ProfileForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.moamoa.domain.Profile;
import com.ssafy.moamoa.service.ProfileServiceImpl;

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

	private ProfileServiceImpl profileService;

	@Autowired
	public ProfileController(ProfileServiceImpl profileService){
		this.profileService = profileService;
	}

	// 검색 여부 변경
	@PutMapping("/searchState")
	ResponseEntity<Map<String, Object>> updateSearchState(@RequestBody Long userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		ProfileForm profileForm = profileService.changeUserState(userId);
		resultMap.put("message",profileForm.getProfileSearchStatus());
		status= HttpStatus.ACCEPTED;
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
	@GetMapping("/user")
	public ResponseEntity<Map<String, Object>> goUserPage(@PathVariable String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
