package com.ssafy.moamoa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.ssafy.moamoa.domain.dto.*;
import org.apache.http.protocol.HTTP;
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

	private static final HttpStatus OK = HttpStatus.OK;
	private static final HttpStatus ACCEPTED = HttpStatus.ACCEPTED;
	private static final HttpStatus BAD_REQUEST=  HttpStatus.BAD_REQUEST;

	private final ProfileService profileService;

	private final TechStackService techStackService;

	private final ReviewService reviewService;

	private final SideProjectService sideProjectService;

	private final SiteService siteService;

	private final AreaService areaService;

	// 검색 여부 변경
	@ApiOperation(value = "검색 여부 변경",notes = "검색 여부를 (ALL -> PROJECT -> STUDY -> NONE ) 순으로 변경해줍니다. ")
	@PutMapping("/search-state")

	ResponseEntity<ProfileSearchStatusForm> updateSearchState(@RequestBody ProfileSearchStatusForm profileSearchStatusForm) {

		ProfileSearchStatusForm result = profileService.changeUserSearchState(profileSearchStatusForm.getId());

		return new ResponseEntity<ProfileSearchStatusForm>(result, OK);
	}



	@ApiOperation(value="사용자 페이지 접근", notes = "사용자 페이지 정보를 리턴해줍니다.")
	@GetMapping("/{profileId}")
	public ResponseEntity<?> getProfilePage(@PathVariable Long profileId) {

		ProfilePageForm profilePageForm = profileService.getProfile(profileId);
		return new ResponseEntity<ProfilePageForm>(profilePageForm, OK);
	}

	//유저 프로필 수정
	@ApiOperation(value = "프로필 수정",
		notes = "프로필의 기술스택 , 링크, 진행방식 ,지역을 수정합니다.")
	@PutMapping("/modify/{profileId}")
	public ResponseEntity<ProfilePageForm> modifyProfile(@PathVariable Long profileId,
		@RequestBody ProfilePageForm profilePageForm) {

		ProfilePageForm result = profileService.modifyProfile(profileId,profilePageForm);

		return new ResponseEntity<ProfilePageForm>(result, OK);
	}




	// 자기소개
	@ApiOperation(value = "자기소개 수정",notes = "자기소개 수정을 합니다.")
	@PutMapping("/context/{profileId}")
	public ResponseEntity<?> modifyContext(@PathVariable Long profileId,
		@RequestBody ContextForm contextForm) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		// Service
		ContextForm contextFormReturn = profileService.addContext(profileId, contextForm.getContext());

		status = HttpStatus.ACCEPTED;
		return new ResponseEntity<ContextForm>(contextFormReturn, status);
	}

	@ApiOperation(value = "자기소개 삭제",notes = "자기소개 삭제를 합니다.")
	@DeleteMapping("/context/{profileId}")
	public ResponseEntity<?> deleteContext(@PathVariable Long profileId) {

		profileService.deleteContext(profileId);

		return new ResponseEntity<Map<String, Object>>(OK);
	}

	// SidePjt
	@ApiOperation(value = "사이드 프로젝트 추가",notes = "사이드 프로젝트를 추가합니다.")
	@PostMapping ("/sidepjt/{profileId}")
	public ResponseEntity<?> addSideProject(@PathVariable Long profileId , @RequestBody SidePjtForm sidePjtForm)
	{
		HttpStatus status =null;
		List<SidePjtForm> result = sideProjectService.addSidePjt(profileId,sidePjtForm);
		status = HttpStatus.ACCEPTED;
		return new ResponseEntity<List<SidePjtForm>>(result,status);
	}

	@ApiOperation(value = "사이드 프로젝트 수정",notes = "사이드 프로젝트를 수정합니다.")
	@PutMapping("/sidepjt/{profileId}")
	public ResponseEntity<?> modifySideProject(@PathVariable Long profileId , @RequestBody SidePjtForm sidePjtForm)
	{
		HttpStatus status =null;
		List<SidePjtForm> result = sideProjectService.modifySidePjt(profileId,sidePjtForm);
		status = HttpStatus.ACCEPTED;
		return new ResponseEntity<List<SidePjtForm>>(result,status);
	}

	@ApiOperation(value = "사이드 프로젝트 삭제",notes = "사이드 프로젝트를 삭제합니다.")
	@DeleteMapping("/sidepjt/{profileId}")
	public ResponseEntity<?> deleteSideProject(@PathVariable Long profileId,@RequestBody SidePjtForm sidePjtForm)
	{
		HttpStatus status =null;
		List<SidePjtForm> result = sideProjectService.deleteSidePjt(profileId,sidePjtForm);
		status = HttpStatus.ACCEPTED;
		return new ResponseEntity<List<SidePjtForm>>(result,status);
	}


	// Reviews
	@ApiOperation(value = "리뷰 목록 조회",notes = "사이드 프로젝트를 수정합니다.")
	@GetMapping("review/{profileId}")
	public ResponseEntity<?> getReviews(@PathVariable Long profileId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		// Service
		List<ReviewForm> result = reviewService.getReviews(profileId);


		resultMap.put("review", result);

		status = HttpStatus.ACCEPTED;
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "댓글 추가 ",notes = "댓글을 추가합니다.")
	@PostMapping("/review/{profileId}")
	public ResponseEntity<?> addReview(@PathVariable Long profileId, @RequestBody ReviewForm reviewForm) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		// Service
		ReviewForm result = reviewService.addReview(profileId, reviewForm);


		resultMap.put("review", result);

		status = HttpStatus.OK;
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "댓글 수정",notes = "댓글을 수정합니다.")
	@PutMapping("/review/{profileId}")
	public ResponseEntity<?> modifyReview(@PathVariable Long profileId, @RequestBody ReviewForm reviewForm) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		// Service
		ReviewForm result = reviewService.modifyReview(profileId, reviewForm);


		resultMap.put("review", result);

		status = HttpStatus.ACCEPTED;
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "댓글 삭제",notes = "댓글을 삭제합니다.")
	@DeleteMapping("/review/{profileId}")
	public ResponseEntity<?> deleteReview(@PathVariable Long profileId , @RequestBody ReviewForm reviewForm) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		// Service
		List<ReviewForm> result = reviewService.deleteReview(profileId, reviewForm);


		resultMap.put("review", result);

		status = HttpStatus.ACCEPTED;
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}




}
