package com.ssafy.moamoa.domain.dto;

import java.util.List;

import com.ssafy.moamoa.domain.ProjectCategory;
import com.ssafy.moamoa.domain.ProjectStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchCondition {

	//쿼리
	private String query;

	//진행방식
	private ProjectStatus status;

	//지역
	private List<Long> area;

	//모집 구분
	private ProjectCategory category;

	//기술스택
	private List<Long> stack;

}
