package com.ssafy.moamoa;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.dto.ProjectForm;
import com.ssafy.moamoa.service.ProjectService;
import com.ssafy.moamoa.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
@Service
public class InitTestService {
	private final UserService userService;
	private final ProjectService projectService;

	public void addUser() {
		userService.signup("ssafy@ssafy.com", "s@12341234", "김싸피");
		userService.signup("yeonjin@ssafy.com", "s@12341234", "정연진");
		userService.signup("youjiyeon4@ssafy.com", "s@12341234", "유지연");
		userService.signup("smartpodo@ssafy.com", "s@12341234", "김동동");
		userService.signup("ssafy1@ssafy.com", "s@12341234", "김싸피1");
		userService.signup("ssafy2@ssafy.com", "s@12341234", "김싸피2");
		userService.signup("ssafy3@ssafy.com", "s@12341234", "김싸피3");
		userService.signup("ssafy4@ssafy.com", "s@12341234", "김싸피4");
		userService.signup("ssafy5@ssafy.com", "s@12341234", "김싸피5");
		userService.signup("ssafy6@ssafy.com", "s@12341234", "김싸피6");
		userService.signup("ssafy7@ssafy.com", "s@12341234", "김싸피7");
		userService.signup("ssafy8@ssafy.com", "s@12341234", "김싸피8");
		userService.signup("ssafy9@ssafy.com", "s@12341234", "김싸피9");
		userService.signup("ssafy10@ssafy.com", "s@12341234", "김싸피10");
		userService.signup("ssafy11@ssafy.com", "s@12341234", "김싸피11");
		userService.signup("ssafy12@ssafy.com", "s@12341234", "김싸피12");
		userService.signup("ssafy13@ssafy.com", "s@12341234", "김싸피13");
		userService.signup("ssafy14@ssafy.com", "s@12341234", "김싸피14");
		userService.signup("ssafy15@ssafy.com", "s@12341234", "김싸피15");
		userService.signup("ssafy16@ssafy.com", "s@12341234", "김싸피16");
		userService.signup("ssafy17@ssafy.com", "s@12341234", "김싸피17");
		userService.signup("ssafy18@ssafy.com", "s@12341234", "김싸피18");
		userService.signup("ssafy19@ssafy.com", "s@12341234", "김싸피19");

	}

	public void addProject() throws Exception {
		ProjectForm studyOffForm = new ProjectForm("hi", "", "", "OFFLINE", "STUDY", "2023-02-21", 2, false, 1L,
			new Long[] {5L, 6L}, 1L);
		ProjectForm projectOffForm = new ProjectForm("hello", "", "", "OFFLINE", "PROJECT", "2023-02-21", 2, false, 1L,
			new Long[] {1L, 4L}, 1L);
		ProjectForm studyOnForm = new ProjectForm("hello world!", "", "", "ONLINE", "STUDY", "2023-02-21", 2, false, 1L,
			new Long[] {1L, 2L}, 2L);
		ProjectForm projectOnForm = new ProjectForm("하이", "", "", "ONLINE", "PROJECT", "2023-02-21", 2, false, 1L,
			new Long[] {3L, 2L}, 1L);
		for (int i = 0; i < 20; i++) {
			projectService.creatProject(studyOffForm);
			projectService.creatProject(projectOffForm);
			projectService.creatProject(studyOnForm);
			projectService.creatProject(projectOnForm);

		}

	}

}
