package com.ssafy.moamoa.repository.querydsl;

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
import com.ssafy.moamoa.domain.dto.ProjectDto;
import com.ssafy.moamoa.domain.dto.SearchCondition;
import com.ssafy.moamoa.domain.entity.Project;
import com.ssafy.moamoa.repository.ProjectRepository;
import com.ssafy.moamoa.service.ProjectService;
import com.ssafy.moamoa.service.UserService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectRepositoryImplTest {

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	ProjectService projectService;

	@Autowired
	UserService userService;

	@Autowired
	InitTestService initService;

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
	public void 종료된프로젝트() {
		//given
		SearchCondition condition = SearchCondition.builder().query("시간지남").build();

		//when
		List<ProjectDto> result = projectRepository.search(condition);

		//then
		Assertions.assertThat(result).isEmpty();

	}

	@Test
	public void search() {
		//given
		List<Long> area = new ArrayList<>(Arrays.asList(1L, 2L));
		List<Long> stack = new ArrayList<>(Arrays.asList(1L, 2L));
		SearchCondition condition = new SearchCondition("hello", ProjectStatus.ONLINE, area, ProjectCategory.STUDY,
			stack);

		//when
		List<ProjectDto> result = projectRepository.search(condition);

		//then
		System.out.println("++++++++++++++++++++");
		for (ProjectDto r : result) {
			System.out.println(r.toString());
		}
		System.out.println("++++++++++++++++++++");
	}

	@Test
	public void 쿼리검색() {
		//given
		SearchCondition condition = SearchCondition.builder().query("hello").build();

		//when
		List<ProjectDto> result = projectRepository.search(condition);

		//then
		System.out.println("++++++++++++++++++++");
		for (ProjectDto r : result) {
			System.out.println(r.toString());
		}
		System.out.println("++++++++++++++++++++");
	}

	@Test
	public void 온오프라인검색() {
		//given
		SearchCondition condition = SearchCondition.builder().status(ProjectStatus.ONLINE).build();

		//when
		List<ProjectDto> result = projectRepository.search(condition);

		//then
		System.out.println("++++++++++++++++++++");
		for (ProjectDto r : result) {
			System.out.println(r.toString());
		}
		System.out.println("++++++++++++++++++++");
	}

	@Test
	public void 모집구분() {
		//given
		SearchCondition condition = SearchCondition.builder().category(ProjectCategory.STUDY).build();

		//when
		List<ProjectDto> result = projectRepository.search(condition);

		//then
		System.out.println("++++++++++++++++++++");
		for (ProjectDto r : result) {
			System.out.println(r.toString());
		}
		System.out.println("++++++++++++++++++++");
	}

	@Test
	public void 기술스택검색() {
		//given
		List<Long> stack = new ArrayList<>(Arrays.asList(1L, 5L));
		SearchCondition condition = SearchCondition.builder().stack(stack).build();

		//when
		List<ProjectDto> result = projectRepository.search(condition);

		//then
		System.out.println("++++++++++++++++++++");
		for (ProjectDto r : result) {
			System.out.println(r.toString());
		}
		System.out.println("++++++++++++++++++++");

	}

	@Test
	public void 지역조건검색() {
		//given
		List<Long> area = new ArrayList<>(List.of(1L));
		SearchCondition condition = SearchCondition.builder().area(area).build();

		//when
		List<ProjectDto> result = projectRepository.search(condition);

		//then
		System.out.println("++++++++++++++++++++");
		for (ProjectDto r : result) {
			System.out.println(r.toString());
		}
		System.out.println("++++++++++++++++++++");

	}

}