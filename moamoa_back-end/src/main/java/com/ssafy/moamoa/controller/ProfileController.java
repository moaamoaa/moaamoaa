package com.ssafy.moamoa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.moamoa.domain.dto.ContextForm;
import com.ssafy.moamoa.domain.dto.ProfileForm;
import com.ssafy.moamoa.domain.dto.ReviewForm;
import com.ssafy.moamoa.domain.dto.SidePjtForm;
import com.ssafy.moamoa.domain.dto.TechStackForm;
import com.ssafy.moamoa.domain.dto.UserForm;
import com.ssafy.moamoa.service.ProfileService;
import com.ssafy.moamoa.service.ReviewService;
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

	private final ProfileService profileService;

	private final TechStackService techStackService;

	private final ReviewService reviewService;

	// 검색 여부 변경
	@PutMapping("/search-state")
	// input userId output String ProfileSearchStatus
	ResponseEntity<Map<String, Object>> updateSearchState(@RequestBody UserForm user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		log.info("userId log={}", user.getId());

		String resultSearchState = profileService.changeUserState(user.getId());
		resultMap.put("message", resultSearchState);

		status = HttpStatus.ACCEPTED;
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 마이페이지 접근
	@GetMapping("/mypage")
	public ResponseEntity<Map<String, Object>> myPage(@RequestBody Long profileId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 다른 사용자 페이지 접근
	@GetMapping("/{userId}")
	public ResponseEntity<Map<String, Object>> userPage(@PathVariable Long profileId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		ProfileForm profileForm = profileService.getProfile(profileId);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	//유저 프로필 수정
	@ApiOperation(value = "프로필 수정",
		notes = "프로필 수정을 누를 시에 사용자의 기술스택 , 링크 ,지역을 수정합니다.")
	@PutMapping("/register/{profileId}")
	public ResponseEntity<?> modifyProfile(@PathVariable Long profileId,
		@RequestBody List<TechStackForm> techStackFormList) {

		// 기술 스택 , 지역, 링크 리스트로 모두 리턴

		// 기술 스택
		String result = techStackService.modifyProfileTechStack(profileId, techStackFormList);

		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// 자기소개
	@PutMapping("/context/{profileId}")
	public ResponseEntity<Map<String, Object>> addContext(@PathVariable Long profileId,
		@RequestBody ContextForm contextForm) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		// Service
		ContextForm contextFormReturn = profileService.addContext(profileId, contextForm.getContext());
		resultMap.put("context", contextFormReturn.getContext());
		resultMap.put("message", SUCCESS);
		status = HttpStatus.ACCEPTED;
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@DeleteMapping("/context/{profileId}")
	public ResponseEntity<Map<String, Object>> deleteContext(@PathVariable Long profileId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		// Service
		String message = profileService.deleteContext(profileId);
		if (message.equals(SUCCESS)) {
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} else {
			resultMap.put("message", FAIL);
			status = HttpStatus.NOT_ACCEPTABLE;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 댓글
	@PostMapping("/review/")
	public ResponseEntity<Map<String, Object>> addReview(@RequestBody ReviewForm reviewForm) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		// Service
		ReviewForm reviewFormReturn = reviewService.addReview(reviewForm.getProfileId(), reviewForm.getSenderId(),
			reviewForm.getContext());
		resultMap.put("receiverId", reviewFormReturn.getProfileId());
		resultMap.put("senderId", reviewFormReturn.getSenderId());
		resultMap.put("time", reviewFormReturn.getTime());
		resultMap.put("context", reviewFormReturn.getContext());

		resultMap.put("message", SUCCESS);

		status = HttpStatus.ACCEPTED;
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 사이드 프로젝트
	@GetMapping("/sidepjt/{profileId}")
	public ResponseEntity<Map<String, Object>> getSidePjts(@PathVariable Long profileId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		List<SidePjtForm> sidePjtFormList = profileService.getSideProjects(profileId);
		resultMap.put("sidepjt", sidePjtFormList);
		status = HttpStatus.ACCEPTED;
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PutMapping("/sidepjt/{profileId}")
	public ResponseEntity<Map<String, Object>> addSidePjt(@PathVariable Long profileId,
		@RequestBody SidePjtForm sidePjtForm) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		String result = profileService.addSidePjt(profileId, sidePjtForm);
		resultMap.put("message", SUCCESS);
		status = HttpStatus.ACCEPTED;
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
