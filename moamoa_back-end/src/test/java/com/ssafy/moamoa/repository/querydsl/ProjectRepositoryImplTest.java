package com.ssafy.moamoa.repository.querydsl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.ProjectCategory;
import com.ssafy.moamoa.domain.ProjectStatus;
import com.ssafy.moamoa.domain.dto.ProjectDto;
import com.ssafy.moamoa.domain.dto.ProjectForm;
import com.ssafy.moamoa.domain.dto.SearchCondition;
import com.ssafy.moamoa.repository.ProjectRepository;
import com.ssafy.moamoa.service.ProjectService;
import com.ssafy.moamoa.service.UserService;

@SpringBootTest
@Transactional
class ProjectRepositoryImplTest {

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	ProjectService projectService;

	@Autowired
	UserService userService;

	@Test
	public void search() throws Exception {
		//given
		userService.signup("ssafy@ssafy.com", "s@12341234", "김싸피");
		ProjectForm project1 = new ProjectForm("hi", "", "", "ONLINE", "PROJECT", "2023-02-21", 2, false, 0L,
			new Long[] {1L, 6L}, 1L);
		ProjectForm project2 = new ProjectForm("냠", "", "", "OFFLINE", "STUDY", "2023-02-21", 2, false, 0L,
			new Long[] {5L, 6L}, 3L);
		projectService.creatProject(project1);
		projectService.creatProject(project2);

		List<Long> area = new ArrayList<>(Arrays.asList(1L, 2L));
		List<Long> stack = new ArrayList<>(Arrays.asList(1L, 2L));
		SearchCondition condition = new SearchCondition("hi", ProjectStatus.ONLINE, area, ProjectCategory.PROJECT,
			stack);

		//when
		List<ProjectDto> result = projectRepository.search(condition);

		//then
		for (ProjectDto r : result) {
			System.out.println(r.toString());
		}
	}

}