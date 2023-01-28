package com.ssafy.moamoa.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ssafy.moamoa.domain.dto.ApplyForm;
import com.ssafy.moamoa.domain.dto.MatchingForm;
import com.ssafy.moamoa.service.ApplyService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@Transactional
public class MatchingController {

	private final ApplyService applyService;

	// 지원 보내기
	@ApiOperation(value = "지원 보내기",
		notes = "개인이 팀에게 지원을 한다.")
	@PostMapping("/apply/{userId}")
	public ResponseEntity<?> sendApply(@PathVariable Long userId, @RequestBody MatchingForm matchingForm) {

		applyService.sendApply(userId, matchingForm.getProjectId());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// 개인 지원 확인
	@ApiOperation(value = "개인 지원 확인",
		notes = "개인이 팀에게 보낸 지원을 확인한다.")
	@GetMapping("/apply/user/{userId}")
	public ResponseEntity<?> showSendApply(@PathVariable Long userId) {

		List<ApplyForm> applyForms = applyService.showSendApply(userId);
		return new ResponseEntity<List<ApplyForm>>(applyForms, HttpStatus.OK);
	}

	// 팀 지원 확인
	@ApiOperation(value = "팀 지원 확인",
		notes = "팀장이 개인에게 받은 지원을 확인한다.")
	@GetMapping("/apply/project/{projectId}")
	public ResponseEntity<?> showReceiveApply(@PathVariable Long projectId) {

		List<ApplyForm> applyForms = applyService.showReceiveApply(projectId);
		return new ResponseEntity<List<ApplyForm>>(applyForms, HttpStatus.OK);
	}

	// 지원 철회
	// 팀의 cnt_apply--
	@ApiOperation(value = "지원 철회",
		notes = "개인이 팀에게 보낸 지원을 철회한다.")
	@DeleteMapping("/apply/user/{userId}")
	public ResponseEntity<?> deleteSendApply(@PathVariable Long userId, @RequestBody MatchingForm matchingForm) {

		// 철회할 apply id를 받고 -> apply에서 삭제
		applyService.deleteSendApply(matchingForm);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// 지원 거절
	@ApiOperation(value = "지원 거절",
		notes = "팀장이 개인에게 받은 지원을 거절한다.")
	@DeleteMapping("/apply/project/{projectId}")
	public ResponseEntity<?> deleteReceiveApply(@PathVariable Long projectId, @RequestBody MatchingForm matchingForm) {

		// 철회할 apply id를 받고 -> apply에서 삭제
		applyService.deleteReceiveApply(matchingForm.getApplyId());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// 제안 보내기
	@ApiOperation(value = "제안 보내기",
		notes = "팀이 개인에게 제안을 한다.")
	@PostMapping("/offer/{projectId}")
	public ResponseEntity<?> sendOffer(@PathVariable Long projectId, @RequestBody MatchingForm matchingForm) {

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
