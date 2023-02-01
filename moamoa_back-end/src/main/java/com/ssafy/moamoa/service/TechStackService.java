package com.ssafy.moamoa.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.dto.TechStackForm;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.ProfileTechStack;
import com.ssafy.moamoa.domain.entity.ProjectTechStack;
import com.ssafy.moamoa.domain.entity.TechStack;
import com.ssafy.moamoa.repository.ProfileRepository;
import com.ssafy.moamoa.repository.ProfileTechStackRepository;
import com.ssafy.moamoa.repository.ProjectRepository;
import com.ssafy.moamoa.repository.ProjectTechStackRepository;
import com.ssafy.moamoa.repository.TechStackRepository;
import com.ssafy.moamoa.repository.UserRepository;
import com.ssafy.moamoa.repository.UserTechStackRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
@Slf4j
public class TechStackService {
	@PersistenceContext
	EntityManager em;

	private final TechStackRepository techstackRepository;

	private final ProfileRepository profileRepository;

	private final ProfileTechStackRepository profileTechStackRepository;

	private final UserTechStackRepository userTechStackRepository;

	private final ProjectTechStackRepository projectTechStackRepository;

	private final UserRepository userRepository;

	private final ProjectRepository projectRepository;

	public List<TechStackForm> searchTechStackByName(String techName) {
		List<TechStack> domainResult = techstackRepository.searchTechStackByName(techName);

		List<TechStackForm> techStackFormList = new ArrayList<>();

		for (TechStack ts : domainResult) {
			techStackFormList.add(new TechStackForm(ts.getId(), ts.getName(), ts.getLogo()));
		}

		return techStackFormList;
	}

	// Id, techStackFormList
	public String modifyProfileTechStack(Long profileId, List<TechStackForm> techStackFormList) {

		Profile profile = profileRepository.getProfileById(profileId);
		StringBuilder sb = new StringBuilder();

		for (TechStackForm techStackForm : techStackFormList) {
			// ProfileTechStack
			ProfileTechStack profileTechStack = ProfileTechStack.builder()
				.techStack(techstackRepository.getTechStackById(techStackForm.getId()))
				.profile(profile)
				.build();
			sb.append(techStackForm.getId() + ",");
			profileTechStackRepository.save(profileTechStack);
		}
		sb.deleteCharAt(sb.length() - 1);
		profile.setTechStackOrder(sb.toString());
		profileRepository.save(profile);
		return "SUCCESS";
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
				.techStack(techstackRepository.getTechStackById(techStackForm.getId()))
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
