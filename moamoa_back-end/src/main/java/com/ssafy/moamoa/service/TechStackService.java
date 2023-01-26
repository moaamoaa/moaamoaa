package com.ssafy.moamoa.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.ProjectTechStack;
import com.ssafy.moamoa.domain.TechStack;
import com.ssafy.moamoa.domain.UserTechStack;
import com.ssafy.moamoa.dto.TechStackForm;
import com.ssafy.moamoa.repository.ProjectRepository;
import com.ssafy.moamoa.repository.ProjectTechStackRepository;
import com.ssafy.moamoa.repository.TechStackRepository;
import com.ssafy.moamoa.repository.UserRepository;
import com.ssafy.moamoa.repository.UserTechStackRepository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = false)
@NoArgsConstructor
@Slf4j
public class TechStackService {
	@PersistenceContext
	EntityManager em;
	@Autowired
	private TechStackRepository techstackRepository;

	@Autowired
	private UserTechStackRepository userTechStackRepository;

	@Autowired
	private ProjectTechStackRepository projectTechStackRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProjectRepository projectRepository;

	public List<TechStackForm> searchTechStackByName(String techName) {
		List<TechStack> domainResult = techstackRepository.searchTechStackByName(techName);

		List<TechStackForm> techStackFormList = new ArrayList<>();

		for (TechStack ts : domainResult) {
			techStackFormList.add(new TechStackForm(ts.getName(), ts.getLogo()));
		}

		return techStackFormList;
	}

	// Id, techStackFormList
	public List<TechStackForm> modifyUserTechStack(Long userId, List<TechStackForm> techStackFormList) {

		List<UserTechStack> userTechStackList = new ArrayList<>();
		List<TechStackForm> techStackFormListResult = new ArrayList<>();
		// 유저 스택 모두 삭제
		Long deleteCount = userTechStackRepository.deleteAllUserStackById(userId);

		// 유저 스택 모두 추가
		for (TechStackForm techStackForm : techStackFormList) {
			// profile
			UserTechStack userTechStack = UserTechStack.builder()
				.techStack(techstackRepository.getTechStackByName(techStackForm.getName()))
				.user(userRepository.findById(userId).get()).build();

			userTechStackRepository.save(userTechStack);
		}

		List<UserTechStack> techStackList = userTechStackRepository.getAllUserTechStackByOrder(userId);
		for (UserTechStack uts : techStackList) {
			TechStackForm techStackForm = TechStackForm.builder()
				.name(uts.getTechStack().getName())
				.img(uts.getTechStack().getLogo())
				.build();
			techStackFormListResult.add(techStackForm);
		}

		return techStackFormListResult;
	}

	public List<TechStackForm> modifyTeamTechStack(Long projectId, List<TechStackForm> techStackFormList) {
		List<ProjectTechStack> teamTechStackList = new ArrayList<>();
		List<TechStackForm> techStackFormListResult = new ArrayList<>();
		// 팀 스택 모두 삭제
		Long deleteCount = projectTechStackRepository.deleteAllProjectStackById(projectId);
		System.out.println(deleteCount);
		// 팀 스택 모두 추가
		for (TechStackForm techStackForm : techStackFormList) {
			// Team
			ProjectTechStack projectTechStack = ProjectTechStack.builder()
				.techStack(techstackRepository.getTechStackByName(techStackForm.getName()))
				.project(projectRepository.findById(projectId).get()).build();

			projectTechStackRepository.save(projectTechStack);
		}

		List<ProjectTechStack> projectTechStackList = projectTechStackRepository.getAllProjectTechStackByOrder(
			projectId);
		for (ProjectTechStack pts : projectTechStackList) {
			TechStackForm techStackForm = TechStackForm.builder()
				.name(pts.getTechStack().getName())
				.img(pts.getTechStack().getLogo())
				.build();
			techStackFormListResult.add(techStackForm);
		}

		return techStackFormListResult;
	}
}
