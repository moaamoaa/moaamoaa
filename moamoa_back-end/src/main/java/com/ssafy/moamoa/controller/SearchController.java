package com.ssafy.moamoa.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.moamoa.domain.dto.FilterDto;
import com.ssafy.moamoa.domain.dto.ProfileResultDto;
import com.ssafy.moamoa.domain.dto.ProjectResultDto;
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
	public ResponseEntity<?> searchProject(SearchCondition condition, String cursorId, Pageable pageable) {
		log.debug(pageable.getSort().toString());
		List<ProjectResultDto> results = searchService.searchProject(condition, cursorId, pageable);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}

	@GetMapping("/profile")
	public ResponseEntity<?> searchProfile(SearchCondition condition, String cursorId, Pageable pageable) {
		List<ProfileResultDto> results = searchService.searchProfile(condition, cursorId, pageable);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<?> getSearchFilter() {
		FilterDto searchFilter = searchService.getSearchFilter();
		return new ResponseEntity<>(searchFilter, HttpStatus.OK);
	}
}
