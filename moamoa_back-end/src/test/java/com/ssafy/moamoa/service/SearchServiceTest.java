package com.ssafy.moamoa.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.moamoa.InitTestService;
import com.ssafy.moamoa.domain.ProjectCategory;
import com.ssafy.moamoa.domain.ProjectStatus;
import com.ssafy.moamoa.domain.dto.FilterDto;
import com.ssafy.moamoa.domain.dto.ProjectResultDto;
import com.ssafy.moamoa.domain.dto.SearchCondition;
import com.ssafy.moamoa.domain.dto.TechStackCategoryDto;
import com.ssafy.moamoa.domain.entity.Area;
import com.ssafy.moamoa.domain.entity.Project;
import com.ssafy.moamoa.domain.entity.TechStack;
import com.ssafy.moamoa.repository.ProjectRepository;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SearchServiceTest {
	@Autowired
	SearchService searchService;

	@Autowired
	UserService userService;
	@Autowired
	CardService cardService;

	@Autowired
	InitTestService initService;

	@Autowired
	ProjectRepository projectRepository;

	@BeforeAll
	public void setData() throws Exception {
		initService.addUser();
		initService.addProject();
		LocalDate startDate = LocalDate.parse("2023-01-21", DateTimeFormatter.ISO_DATE);
		LocalDate endDate = LocalDate.parse("2023-01-26", DateTimeFormatter.ISO_DATE);

		Project project = Project.builder()
			.category(ProjectCategory.STUDY)
			.countOffer(0)
			.hit(0)
			.onoffline(ProjectStatus.ONLINE)
			.createDate(startDate)
			.startDate(startDate)
			.endDate(endDate)
			.title("시간지남")
			.totalPeople(5)
			.currentPeople(1)
			.isLocked(false)
			.build();

		projectRepository.save(project);

	}

	@Test
	void searchProject() {
		//given
		List<Long> stacks = new ArrayList<>(Arrays.asList(1L, 2L));
		SearchCondition searchCondition = SearchCondition.builder().stack(stacks).build();

		//when
		List<ProjectResultDto> searchResult = searchService.searchProject(searchCondition);

		//then
		Assertions.assertThat(searchResult.get(0).getTechStacks()).isNotNull();
		Assertions.assertThat(searchResult.get(0).getLeaderName()).isNotNull();

	}

	@Test
	void setTechStacks() {
		//given
		ProjectResultDto projectResultDto = ProjectResultDto.builder().id(1L).build();

		//when
		ProjectResultDto setResult = cardService.setTechStacks(projectResultDto);

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
		ProjectResultDto projectResultDto = ProjectResultDto.builder().id(1L).build();

		//when
		ProjectResultDto setResult = cardService.setLeaderNickname(projectResultDto);

		//then
		Assertions.assertThat(setResult.getLeaderName()).isNotNull();
		System.out.println("*************");
		System.out.println(setResult.getLeaderName());
		System.out.println("*************");
	}

	@Test
	public void 검색필터조회_지역() {
		//given
		FilterDto searchFilter = searchService.getSearchFilter();

		//when
		List<Area> areas = searchFilter.getAreas();

		//then
		Assertions.assertThat(areas).isNotNull();
		System.out.println("*************");
		for (Area a : areas) {
			System.out.println(a.toString());
		}
		System.out.println("*************");

	}

	@Test
	public void 검색필터조회_기술스택() {
		//given
		FilterDto searchFilter = searchService.getSearchFilter();

		//when
		List<TechStackCategoryDto> techs = searchFilter.getTechs();

		//then
		Assertions.assertThat(techs).isNotNull();
		System.out.println("*************");
		for (TechStackCategoryDto t : techs) {
			System.out.println(t.toString());
		}
		System.out.println("*************");

	}
}