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
import com.ssafy.moamoa.domain.dto.ProfilePageForm;
import com.ssafy.moamoa.domain.dto.ReviewForm;
import com.ssafy.moamoa.domain.dto.SidePjtForm;
import com.ssafy.moamoa.domain.dto.UserForm;
import com.ssafy.moamoa.service.AreaService;
import com.ssafy.moamoa.service.ProfileService;
import com.ssafy.moamoa.service.ReviewService;
import com.ssafy.moamoa.service.SideProjectService;
import com.ssafy.moamoa.service.SiteService;
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

	private final SideProjectService sideProjectService;

	private final SiteService siteService;

	private final AreaService areaService;

	// 검색 여부 변경
	@PutMapping("/search-state")

	ResponseEntity<Map<String, Object>> updateSearchState(@RequestBody UserForm user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		log.info("userId log={}", user.getId());

		String resultSearchState = profileService.changeUserSearchState(user.getId());
		resultMap.put("message", resultSearchState);

		status = HttpStatus.ACCEPTED;
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// // 마이페이지
	// @GetMapping("/mypage")
	// public ResponseEntity<Map<String, Object>> myPage(@RequestBody Long profileId) {
	// 	Map<String, Object> resultMap = new HashMap<>();
	// 	HttpStatus status = null;
	//
	// 	status = HttpStatus.ACCEPTED;
	// 	return new ResponseEntity<Map<String, Object>>(resultMap, status);
	// }

	// 다른 사용자 페이지 접근
	@GetMapping("/{profileId}")
	public ResponseEntity<?> getProfilePage(@PathVariable Long profileId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		status = HttpStatus.ACCEPTED;
	ProfilePageForm profilePageForm = profileService.getProfile(profileId);
		return new ResponseEntity<ProfilePageForm>(profilePageForm, status);
	}

	//유저 프로필 수정
	@ApiOperation(value = "프로필 수정",
		notes = "프로필 수정을 누를 시에 사용자의 기술스택 , 링크 ,지역을 수정합니다.")
	@PutMapping("/modify/{profileId}")
	public ResponseEntity<ProfilePageForm> modifyProfile(@PathVariable Long profileId,
		@RequestBody ProfilePageForm profilePageForm) {
		HttpStatus status = null;

		// 기술 스택
		// List<TechStackForm> result = techStackService.modifyProfileTechStack(profileId, techStackFormList);

		ProfilePageForm result = profileService.modifyProfile(profileId,profilePageForm);
		status = HttpStatus.ACCEPTED;
		return new ResponseEntity<ProfilePageForm>(result, status);
	}




	// 자기소개
	@PutMapping("/context/{profileId}")
	public ResponseEntity<?> addContext(@PathVariable Long profileId,
		@RequestBody ContextForm contextForm) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		// Service
		ContextForm contextFormReturn = profileService.addContext(profileId, contextForm.getContext());
		// resultMap.put("context", contextFormReturn.getContext());
		// resultMap.put("message", SUCCESS);
		status = HttpStatus.ACCEPTED;
		return new ResponseEntity<ContextForm>(contextFormReturn, status);
	}

	@DeleteMapping("/context/{profileId}")
	public ResponseEntity<Map<String, Object>> deleteContext(@PathVariable Long profileId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

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
	// SidePjt
	@PostMapping ("/sidepjt/{profileId}")
	public ResponseEntity<?> addSideProject(@PathVariable Long profileId , @RequestBody SidePjtForm sidePjtForm)
	{
		HttpStatus status =null;
		List<SidePjtForm> result = sideProjectService.addSidePjt(profileId,sidePjtForm);
		status = HttpStatus.ACCEPTED;
		return new ResponseEntity<List<SidePjtForm>>(result,status);
	}


	// Reviews

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




}
