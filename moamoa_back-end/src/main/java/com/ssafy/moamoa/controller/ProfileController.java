package com.ssafy.moamoa.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.moamoa.domain.dto.ContextForm;
import com.ssafy.moamoa.domain.dto.ProfilePageForm;
import com.ssafy.moamoa.domain.dto.ProfileSearchStatusForm;
import com.ssafy.moamoa.domain.dto.ReviewForm;
import com.ssafy.moamoa.domain.dto.SidePjtForm;
import com.ssafy.moamoa.service.AreaService;
import com.ssafy.moamoa.service.ProfileService;
import com.ssafy.moamoa.service.ReviewService;
import com.ssafy.moamoa.service.SideProjectService;
import com.ssafy.moamoa.service.SiteService;
import com.ssafy.moamoa.service.TechStackService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

    private final ProfileService profileService;

    private final TechStackService techStackService;

    private final ReviewService reviewService;

    private final SideProjectService sideProjectService;

    private final SiteService siteService;

    private final AreaService areaService;

    // 검색 여부 변경
    @ApiOperation(value = "검색 여부 변경", notes = "검색 여부를 (ALL -> PROJECT -> STUDY -> NONE ) 순으로 변경해줍니다. ")
    @PutMapping("/search-state")
    ResponseEntity<ProfileSearchStatusForm> updateSearchState(@RequestBody ProfileSearchStatusForm profileSearchStatusForm) {

        ProfileSearchStatusForm result = profileService.changeUserSearchState(profileSearchStatusForm.getId());

        return new ResponseEntity<ProfileSearchStatusForm>(result, OK);
    }


    @ApiOperation(value = "회원 탈퇴", notes = "회원을 탈퇴합니다.", response = ProfilePageForm.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "HttpStatus.OK"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @DeleteMapping()
    public ResponseEntity<?> deleteUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        profileService.deleteUser(Long.valueOf(userDetails.getUsername()));
        return new ResponseEntity<ProfilePageForm>(OK);
    }
    @ApiOperation(value = "사용자 페이지 접근", notes = "사용자 페이지 정보를 리턴해줍니다.", response = ProfilePageForm.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "유저의 프로필 정보들을 리턴해줍니다."),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/{profileId}")
    public ResponseEntity<?> getProfilePage(@ApiParam(value = "profileId") @PathVariable Long profileId, Authentication authentication) {
        boolean isValidUser = profileService.checkDeletedUser(profileId);
        ProfilePageForm profilePageForm = null;
            HttpStatus status = null;
            if(!isValidUser)
            {
                status = HttpStatus.NOT_FOUND;
            }
            else
                {
                    profileService.addProfileHit(profileId,authentication);
                    status = OK;
                    profilePageForm = profileService.getProfile(profileId);
                }

        // ProfilePageForm profilePageForm = profileService.getProfile(profileId);
        return new ResponseEntity<ProfilePageForm>(profilePageForm, status);
    }

    //유저 프로필 수정
    @ApiOperation(value = "프로필 수정",
            notes = "프로필의 기술스택 , 링크, 진행방식 ,지역을 수정합니다.", response = ProfilePageForm.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "수정이 반영된 유저의 프로필 정보들을 리턴해줍니다."),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ProfilePageForm> modifyProfile(
      @ApiParam(value = "profilePageForm")  @RequestPart ProfilePageForm profilePageForm , @RequestPart(value = "file") MultipartFile file, Authentication authentication)
            throws IOException {
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();

        ProfilePageForm result = profileService.modifyProfile(profileService.profileByUserId(Long.valueOf(userDetails.getUsername())).getId(), profilePageForm, file);

        return new ResponseEntity<ProfilePageForm>(result, OK);
    }


    // 자기소개
    @ApiOperation(value = "자기소개 수정", notes = "자기소개 수정을 합니다.", response = ContextForm.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "수정이 반영된 유저의 자기소개를 리턴해줍니다."),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @Transactional
    @PutMapping("/context")
    public ResponseEntity<?> modifyContext(@ApiParam(value = "\"context\": \"string\"") @RequestBody ContextForm contextForm, Authentication authentication) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Service
        ContextForm contextFormReturn = profileService.addContext(profileService.profileByUserId(Long.valueOf(userDetails.getUsername())).getId(), contextForm.getContext());

        status = HttpStatus.ACCEPTED;
        return new ResponseEntity<ContextForm>(contextFormReturn, status);
    }

    @ApiOperation(value = "자기소개 삭제", notes = "자기소개 삭제를 합니다.", response = HttpStatus.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Return HTTP.OK"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @DeleteMapping("/context")
    public ResponseEntity<?> deleteContext(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        profileService.deleteContext(profileService.profileByUserId(Long.valueOf(userDetails.getUsername())).getId());

        return new ResponseEntity<Map<String, Object>>(OK);
    }

    // SidePjt
    @ApiOperation(value = "사이드 프로젝트 추가", notes = "사이드 프로젝트를 추가합니다.", response = SidePjtForm.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 200, message = "추가 후 , 년도 순으로 정렬된 사이드 프로젝트 목록을 리턴해줍니다."),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping("/sidepjt")
    public ResponseEntity<?> addSideProject(@ApiParam(value = "\"context\": \"string\" , \"name\": \"string\" ,\"pjt_tech_stack\": [{\"tech_stack_no\": Long }], \"year\": \"string\" ") @RequestBody SidePjtForm sidePjtForm, Authentication authentication) {
        HttpStatus status = null;
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<SidePjtForm> result = sideProjectService.addSidePjt(profileService.profileByUserId(Long.valueOf(userDetails.getUsername())).getId(), sidePjtForm);
        status = HttpStatus.ACCEPTED;
        return new ResponseEntity<List<SidePjtForm>>(result, status);
    }

    @ApiOperation(value = "사이드 프로젝트 수정", notes = "사이드 프로젝트를 수정합니다.", response = SidePjtForm.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 200, message = "수정 후 , 년도 순으로 정렬된 사이드 프로젝트 목록을 리턴해줍니다."),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PutMapping("/sidepjt")
    public ResponseEntity<?> modifySideProject(@ApiParam(value = "\"context\": \"string\" , \"name\": \"string\" ,\"pjt_tech_stack\": [{\"tech_stack_no\": Long }], \"year\": \"string\" ") @RequestBody SidePjtForm sidePjtForm, Authentication authentication) {
        HttpStatus status = null;
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<SidePjtForm> result = sideProjectService.modifySidePjt(profileService.profileByUserId(Long.valueOf(userDetails.getUsername())).getId(), sidePjtForm);
        status = HttpStatus.ACCEPTED;
        return new ResponseEntity<List<SidePjtForm>>(result, status);
    }

    @ApiOperation(value = "사이드 프로젝트 삭제", notes = "사이드 프로젝트를 삭제합니다.", response = SidePjtForm.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 200, message = "삭제 후 , 년도 순으로 정렬된 사이드 프로젝트 목록을 리턴해줍니다."),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @DeleteMapping("/sidepjt/{projectId}")
    public ResponseEntity<?> deleteSideProject(@ApiParam(value = "\"id\": \"Long\"") @PathVariable Long projectId, Authentication authentication) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<SidePjtForm> result = sideProjectService.deleteSidePjt(profileService.profileByUserId(Long.valueOf(userDetails.getUsername())).getId(), projectId);
        status = HttpStatus.OK;
        return new ResponseEntity<List<SidePjtForm>>(result, status);
    }


    // Reviews
    @ApiOperation(value = "댓글 목록 조회", notes = "댓글 목록을 조회합니다.", response = ReviewForm.class,
            responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 200, message = "댓글 목록을 시간 순대로 정렬하여 리턴해줍니다."),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("review")
    public ResponseEntity<?> getReviews(Authentication authentication) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Service
        List<ReviewForm> result = reviewService.getReviews(profileService.profileByUserId(Long.valueOf(userDetails.getUsername())).getId());


        resultMap.put("review", result);

        status = HttpStatus.ACCEPTED;
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "댓글 추가 ", notes = "댓글을 추가합니다. ", response = ReviewForm.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "추가한 댓글의 정보를 리턴해줍니다."),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping("/review")
    public ResponseEntity<?> addReview(
        @ApiParam(value = "\"context\": \"string\", ") @RequestBody ReviewForm reviewForm, Authentication authentication) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // Service
        ReviewForm result = reviewService.addReview(profileService.profileByUserId(Long.valueOf(userDetails.getUsername())).getId(), reviewForm);


        resultMap.put("review", result);

        status = HttpStatus.OK;
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "댓글 수정", notes = "댓글을 수정합니다.", response = ReviewForm.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "수정한 댓글의 정보를 리턴해줍니다."),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PutMapping("/review")
    public ResponseEntity<?> modifyReview(
                                          @ApiParam(value = "\"context\": \"string\" , \"id\" (해당 댓글 id): Long  ") @RequestBody ReviewForm reviewForm, Authentication authentication) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // Service
        ReviewForm result = reviewService.modifyReview(profileService.profileByUserId(Long.valueOf(userDetails.getUsername())).getId(), reviewForm);

        if(result == null)
        {
            status = HttpStatus.BAD_REQUEST;
        }
        else {
            resultMap.put("review", result);

            status = HttpStatus.ACCEPTED;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제합니다.", response = ReviewForm.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 200, message = "삭제된 후 댓글들의 목록을 리턴해줍니다."),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<?> deleteReview(@ApiParam(value = "  \"id\" (해당 댓글 id): Long ") @PathVariable Long reviewId, Authentication authentication) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        UserDetails userDetails= (UserDetails) authentication.getPrincipal();

        // Service
        List<ReviewForm> result = reviewService.deleteReview(profileService.profileByUserId(Long.valueOf(userDetails.getUsername())).getId(), reviewId);
        if(result == null)
        {
            status = HttpStatus.BAD_REQUEST;
        }
        else {
            resultMap.put("review", result);

            status = HttpStatus.ACCEPTED;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

}
