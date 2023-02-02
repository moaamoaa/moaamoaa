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
		List<ProfileTechStack> profileTechStackList = profileTechStackRepository.getProfileTechStacks(profileId);
		// 리스트 전처리 작업
		int inputListSize = techStackFormList.size(); // 5
		int originListSize = profileTechStackList.size(); // 3
		log.info("Initial size :"+inputListSize+" "+originListSize);
		List<Integer> differOrder = new ArrayList<>();

		if (originListSize > inputListSize) {  // 삭제를 해야하는 경우
			for (int i = originListSize; i > inputListSize; i--) {
				differOrder.add(i);
			}
			for (int i : differOrder) {
				profileTechStackRepository.deleteProfileTechStackByOrder(i);

			}
			profileTechStackList = profileTechStackRepository.getProfileTechStacks(profileId); // 다시 가져옴

		}
		else if (originListSize < inputListSize) // 추가를 해야하는 경우
		{
			for (int i = inputListSize - 1; i > originListSize - 1; i--) {
				TechStackForm tempTechStackForm = techStackFormList.remove(i);
				ProfileTechStack tempProfileTechStack = ProfileTechStack.builder()
						.profile(profile)
						.techStack(techstackRepository.getTechStackById(tempTechStackForm.getId()))
						.order(i+1).build();
				profileTechStackRepository.save(tempProfileTechStack); // 뒤에 있는 거 저장
			}
			inputListSize = techStackFormList.size();
		}
		if(inputListSize==0){
			return "SUCCESS";
		}
		for (int size = 0; size < inputListSize; size++) {

			TechStackForm techStackForm = techStackFormList.get(size);

			ProfileTechStack tempProfileTechStack = ProfileTechStack.builder()
				.profile(profile)
				.techStack(techstackRepository.getTechStackById(techStackForm.getId()))
				.order(size + 1).build();
			log.info("TechStack > "+tempProfileTechStack.getTechStack().getName()+tempProfileTechStack.getOrder());

			ProfileTechStack originProfileTechStack = profileTechStackRepository.getProfileTechStack(
				tempProfileTechStack.getTechStack().getId());

			log.info(originProfileTechStack.getTechStack().getName()+" "+originProfileTechStack.getOrder());
			originProfileTechStack.setTechStack(tempProfileTechStack.getTechStack());
			originProfileTechStack.setOrder(tempProfileTechStack.getOrder());
			profileTechStackRepository.save(originProfileTechStack);
		}


		return "SUCCESS";
	}

	// public ProfileTechStack convertTechStackFormToProfileTechStack(Profile profile, TechStackForm techStackForm) {
	// 	ProfileTechStack tempProfileTechStack = ProfileTechStack.builder()
	// 		.profile(profile)
	// 		.techStack(techstackRepository.getTechStackById(techStackForm.getId()))
	// 		.order().build();
	// }

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
