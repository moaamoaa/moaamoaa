package com.ssafy.moamoa.controller;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ssafy.moamoa.dto.MatchingForm;
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
	@PostMapping("/apply")
	public ResponseEntity<?> sendApply(@RequestBody MatchingForm matchingForm) {

		applyService.sendApply(matchingForm);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
