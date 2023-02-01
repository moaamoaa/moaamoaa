package com.ssafy.moamoa.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.moamoa.domain.dto.ProjectDto;
import com.ssafy.moamoa.domain.dto.SearchCondition;
import com.ssafy.moamoa.domain.entity.TechStack;

@SpringBootTest
class SearchServiceTest {
	@Autowired
	SearchService searchService;

	@Test
	void searchProject() {
		//given
		List<Long> stacks = new ArrayList<>(Arrays.asList(1L, 2L));
		SearchCondition searchCondition = SearchCondition.builder().stack(stacks).build();

		//when
		List<ProjectDto> searchResult = searchService.searchProject(searchCondition);

		//then
		Assertions.assertThat(searchResult.get(0).getTechStacks()).isNotNull();
		Assertions.assertThat(searchResult.get(0).getLeaderName()).isNotNull();

	}

	@Test
	void setTechStacks() {
		//given
		ProjectDto projectDto = ProjectDto.builder().id(1L).build();

		//when
		ProjectDto setResult = searchService.setTechStacks(projectDto);

		//then
		Assertions.assertThat(setResult.getTechStacks()).isNotNull();

		System.out.println("*************");
		for (TechStack t : setResult.getTechStacks()) {
			System.out.println(t.getName());
		}
		System.out.println("*************");
	}

	@Test
	void setLeaderNickname() {
		//given
		ProjectDto projectDto = ProjectDto.builder().id(1L).build();

		//when
		ProjectDto setResult = searchService.setLeaderNickname(projectDto);

		//then
		Assertions.assertThat(setResult.getLeaderName()).isNotNull();
		System.out.println("*************");
		System.out.println(setResult.getLeaderName());
		System.out.println("*************");
	}
}