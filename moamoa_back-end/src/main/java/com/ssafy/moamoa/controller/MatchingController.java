package com.ssafy.moamoa.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ssafy.moamoa.domain.dto.ApplyForm;
import com.ssafy.moamoa.domain.dto.MatchingForm;
import com.ssafy.moamoa.domain.dto.OfferForm;
import com.ssafy.moamoa.service.ApplyService;
import com.ssafy.moamoa.service.OfferService;
import com.ssafy.moamoa.service.TeamService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@Transactional
public class MatchingController {

	private final ApplyService applyService;
	private final OfferService offerService;
	private final TeamService teamService;

	// 지원 보내기
	@ApiOperation(value = "지원 보내기", notes = "개인이 팀에게 지원을 한다.")
	@PostMapping("/apply")
	public ResponseEntity<?> sendApply(@RequestBody MatchingForm matchingForm, Authentication authentication) throws
		Exception {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		applyService.sendApply(Long.valueOf(userDetails.getUsername()), matchingForm.getProjectId());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// 개인 지원 확인
	@ApiOperation(value = "개인 지원 확인", notes = "개인이 팀에게 보낸 지원을 확인한다.")
	@GetMapping("/apply/user")
	public ResponseEntity<?> showSendApply(Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		List<ApplyForm> applyForms = applyService.showSendApply(Long.valueOf(userDetails.getUsername()));
		return new ResponseEntity<List<ApplyForm>>(applyForms, HttpStatus.OK);
	}

	// 팀 지원 확인
	@ApiOperation(value = "팀 지원 확인", notes = "팀장이 개인에게 받은 지원을 확인한다.")
	@GetMapping("/apply/project")
	public ResponseEntity<?> showReceiveApply(@RequestBody MatchingForm matchingForm,
		Authentication authentication) throws Exception {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		if (!teamService.checkLeader(Long.valueOf(userDetails.getUsername()), matchingForm.getProjectId())) {
			throw new AccessDeniedException("팀장이 아닙니다.");
		}
		List<ApplyForm> applyForms = applyService.showReceiveApply(matchingForm.getProjectId());
		return new ResponseEntity<List<ApplyForm>>(applyForms, HttpStatus.OK);
	}

	// 팀 지원 수락
	@ApiOperation(value = "지원 수락", notes = "팀장이 개인에게 받은 지원을 수락한다.")
	@PutMapping("/apply/project")
	public ResponseEntity<?> acceptApply(@RequestBody MatchingForm matchingForm, Authentication authentication) throws
		Exception {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		if (!teamService.checkLeader(Long.valueOf(userDetails.getUsername()), matchingForm.getProjectId())) {
			throw new AccessDeniedException("팀장이 아닙니다.");
		}
		applyService.acceptApply(matchingForm);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// 지원 철회
	@ApiOperation(value = "지원 철회", notes = "개인이 팀에게 보낸 지원을 철회한다.")
	@DeleteMapping("/apply/user")
	public ResponseEntity<?> deleteSendApply(@RequestBody MatchingForm matchingForm, Authentication authentication) {
		applyService.deleteSendApply(matchingForm);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// 지원 거절
	@ApiOperation(value = "지원 거절", notes = "팀장이 개인에게 받은 지원을 거절한다.")
	@DeleteMapping("/apply/project")
	public ResponseEntity<?> deleteReceiveApply(@RequestBody MatchingForm matchingForm,
		Authentication authentication) throws Exception {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		if (!teamService.checkLeader(Long.valueOf(userDetails.getUsername()), matchingForm.getProjectId())) {
			throw new AccessDeniedException("팀장이 아닙니다.");
		}
		applyService.deleteReceiveApply(matchingForm.getApplyId());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// 제안 보내기
	@ApiOperation(value = "제안 보내기", notes = "팀이 개인에게 제안을 한다.")
	@PostMapping("/offer")
	public ResponseEntity<?> sendOffer(@RequestBody MatchingForm matchingForm, Authentication authentication) throws
		Exception {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		if (!teamService.checkLeader(Long.valueOf(userDetails.getUsername()), matchingForm.getProjectId())) {
			throw new AccessDeniedException("팀장이 아닙니다.");
		}
		offerService.sendOffer(matchingForm.getUserId(), matchingForm.getProjectId());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// 개인 제안 확인
	@ApiOperation(value = "개인 제안 확인", notes = "개인이 팀에게 받은 제안을 확인한다.")
	@GetMapping("/offer/user")
	public ResponseEntity<?> showReceiveOffer(Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		List<OfferForm> offerForms = offerService.showReceiveOffer(Long.valueOf(userDetails.getUsername()));
		return new ResponseEntity<List<OfferForm>>(offerForms, HttpStatus.OK);
	}

	// 팀 제안 확인
	@ApiOperation(value = "팀 제안 확인", notes = "팀장이 개인에게 보낸 지원을 확인한다.")
	@GetMapping("/offer/project")
	public ResponseEntity<?> showSendOffer(@RequestBody MatchingForm matchingForm, Authentication authentication) throws
		Exception {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		if (!teamService.checkLeader(Long.valueOf(userDetails.getUsername()), matchingForm.getProjectId())) {
			throw new AccessDeniedException("팀장이 아닙니다.");
		}
		List<OfferForm> offerForms = offerService.showSendOffer(matchingForm.getProjectId());
		return new ResponseEntity<List<OfferForm>>(offerForms, HttpStatus.OK);
	}

	// 제안 수락
	@ApiOperation(value = "제안 수락", notes = "개인이 팀에게 받은 제안을 수락한다.")
	@PutMapping("/offer/user")
	public ResponseEntity<?> acceptOffer(Authentication authentication, @RequestBody MatchingForm matchingForm) throws
		Exception {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		offerService.acceptOffer(Long.valueOf(userDetails.getUsername()), matchingForm);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// 제안 거절
	@ApiOperation(value = "제안 거절", notes = "개인이 팀에게 받은 제안을 거절한다.")
	@DeleteMapping("/offer/user")
	public ResponseEntity<?> deleteReceiveOffer(@RequestBody MatchingForm matchingForm) {
		offerService.deleteReceiveOffer(matchingForm);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// 제안 철회
	@ApiOperation(value = "제안 철회", notes = "팀장이 개인에게 보낸 제안을 철회한다.")
	@DeleteMapping("/offer/project")
	public ResponseEntity<?> deleteSendOffer(@RequestBody MatchingForm matchingForm,
		Authentication authentication) throws Exception {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		if (!teamService.checkLeader(Long.valueOf(userDetails.getUsername()), matchingForm.getProjectId())) {
			throw new AccessDeniedException("팀장이 아닙니다.");
		}
		offerService.deleteSendOffer(matchingForm);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
