package com.ssafy.moamoa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.moamoa.domain.dto.SearchCondition;
import com.ssafy.moamoa.service.SearchService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

	private final SearchService searchService;

	@GetMapping("/project")
	public ResponseEntity<?> searchProject(SearchCondition condition) {
		log.debug(condition.toString());
		return null;
	}

	@GetMapping("/profile")
	public ResponseEntity<?> searchProfile(SearchCondition condition) {
		log.debug(condition.toString());
		return null;
	}
}
