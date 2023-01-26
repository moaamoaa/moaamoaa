package com.ssafy.moamoa.controller;

import java.util.HashMap;
import java.util.List;
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
import com.ssafy.moamoa.dto.TechStackForm;
import com.ssafy.moamoa.dto.UserForm;
import com.ssafy.moamoa.service.ProfileService;
import com.ssafy.moamoa.service.TechStackService;

import io.swagger.annotations.ApiOperation;
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
	@Autowired
	private ProfileService profileService;

	@Autowired
	private TechStackService techStackService;

	// 검색 여부 변경
	@PutMapping("/search-state")
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
	public ResponseEntity<Map<String, Object>> myPage(@RequestBody String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 다른 사용자 페이지 접근
	@GetMapping("/{userId}")
	public ResponseEntity<Map<String, Object>> userPage(@PathVariable String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 유저 프로필 수정
	@ApiOperation(value = "프로필 수정",
		notes = "프로필 수정을 누를 시에 사용자의 프로필, 링크 ,지역을 수정합니다.")
	@PutMapping("/register/{userId}")
	public ResponseEntity<?> addTechStack(@PathVariable Long userId,
		@RequestBody List<TechStackForm> techStackFormList) {

		//techStackFormList = techStackService.modifyUserTechStack(userId, techStackFormList);

		techStackFormList = techStackService.modifyTeamTechStack(1L, techStackFormList);
		return new ResponseEntity<List<TechStackForm>>(techStackFormList, HttpStatus.OK);
	}
}
