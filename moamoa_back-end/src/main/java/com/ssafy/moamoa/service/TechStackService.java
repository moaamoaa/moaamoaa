package com.ssafy.moamoa.service;

import com.ssafy.moamoa.domain.dto.TechStackForm;
import com.ssafy.moamoa.domain.entity.*;
import com.ssafy.moamoa.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
@Slf4j
public class TechStackService {
	@PersistenceContext
	EntityManager em;

	private final TechStackRepository techstackRepository;

	private final ProfileRepository profileRepository;

	private final SideProjectRepository sideProjectRepository;

	private final SideProjectTechStackRepository sideProjectTechStackRepository;

	private final ProfileTechStackRepository profileTechStackRepository;

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




	public List<TechStackForm> getProfileTechStacks(Long profileId)
	{
		List<ProfileTechStack> profileTechStackList= profileTechStackRepository.getProfileTechStacksByOrderAsc(profileId);
		List<TechStackForm> techStackFormList = new ArrayList<>();

		for(ProfileTechStack profileTechStack : profileTechStackList)
		{
			TechStackForm techStackForm = TechStackForm.builder()
				.id(profileTechStack.getTechStack().getId())
				.name(profileTechStack.getTechStack().getName())
				.build();

			techStackFormList.add(techStackForm);

		}
	return techStackFormList;
	}


	public List<TechStackForm> getSideProjectTechStacks(Long projectId)
	{
		List<SidePjtTechStack> sidePjtTechStackList= sideProjectTechStackRepository.getSideProjectTechStacks(projectId);
		List<TechStackForm> techStackFormList = new ArrayList<>();

		for(SidePjtTechStack sidePjtTechStack : sidePjtTechStackList)
		{
			TechStackForm techStackForm = TechStackForm.builder()
				.id(sidePjtTechStack.getTechStack().getId())
				.name(sidePjtTechStack.getTechStack().getName())
				.build();

			techStackFormList.add(techStackForm);

		}
		return techStackFormList;
	}



	// Id, techStackFormList
	public List<TechStackForm> modifyProfileTechStack(Long profileId, List<TechStackForm> techStackFormList) {


		Profile profile = profileRepository.getProfileById(profileId);
		List<ProfileTechStack> profileTechStackList = profileTechStackRepository.getProfileTechStacksByOrderAsc(profileId);

		boolean isChanged = false;
		int inputListSize = techStackFormList.size(); // 5
		int originListSize = profileTechStackList.size(); // 3

		if(inputListSize!= originListSize)
		{
			isChanged=true;
		}
		else if(inputListSize==originListSize)
		{
			for(int i=0;i<inputListSize;i++)
			{
				TechStackForm techStackForm = techStackFormList.get(i);
				ProfileTechStack profileTechStack = profileTechStackList.get(i);

				if(!techStackForm.getId().equals(profileTechStack.getTechStack().getId()))
				{
					isChanged=true;
					break;
				}
			}
		}
		if(!isChanged)
		{
			return null;
		}
		else
		{
			Long deleteCount = profileTechStackRepository.deleteAllProfileTechStacksById(profileId);
			for(int i =0;i<inputListSize;i++)
			{
				ProfileTechStack profileTechStack = ProfileTechStack.builder()
						.profile(profile)
						.techStack(techstackRepository.getTechStackById(techStackFormList.get(i).getId()))
						.order(i+1).build();

				profileTechStackRepository.save(profileTechStack);
			}
		}


		List<ProfileTechStack> resultList= profileTechStackRepository.getProfileTechStacksByOrderAsc(profileId);
		List<TechStackForm> returnList = new ArrayList<>();
		for(ProfileTechStack profileTechStack : resultList)
		{
		TechStackForm techStackForm = TechStackForm.toEntity(profileTechStack.getTechStack());
			returnList.add(techStackForm);
		}
		return returnList;
	}


	public List<TechStackForm> modifyProjectTechStack(Long projectId, Long[] techStackList) {

		List<TechStackForm> techStackFormList = new ArrayList<>();

		// Parse Long[] into TechStackFormList
		for(Long id: techStackList)
		{
			TechStackForm techStackForm = TechStackForm.builder()
					.id(id).build();
			techStackFormList.add(techStackForm);
		}

		Project project = projectRepository.getProjectById(projectId);
		List<ProjectTechStack> projectTechStackList = projectTechStackRepository.getAllProjectTechStackByOrder(projectId);

		boolean isChanged = false;
		int inputListSize = techStackFormList.size(); // 5
		int originListSize = projectTechStackList.size(); // 3

		if(inputListSize!= originListSize)
		{
			isChanged=true;
		}
		else if(inputListSize==originListSize)
		{
			for(int i=0;i<inputListSize;i++)
			{
				TechStackForm techStackForm = techStackFormList.get(i);
				ProjectTechStack projectTechStack = projectTechStackList.get(i);

				if(!techStackForm.getId().equals(projectTechStack.getTechStack().getId()))
				{
					isChanged=true;
					break;
				}
			}
		}
		if(!isChanged)
		{
			return null;
		}
		else
		{
			Long deleteCount = projectTechStackRepository.deleteAllProjectStackById(projectId);
			for(int i =0;i<inputListSize;i++)
			{
				ProjectTechStack projectTechStack = ProjectTechStack.builder()
						.project(project)
						.techStack(techstackRepository.getTechStackById(techStackFormList.get(i).getId()))
						.order(i+1).build();

				projectTechStackRepository.save(projectTechStack);
			}
		}
//		Project project = projectRepository.getProjectById(projectId);
//		List<ProjectTechStack> projectTechStackList = projectTechStackRepository.getAllProjectTechStackByOrder(projectId);
//		// 리스트 전처리 작업
//		int inputListSize = techStackFormList.size(); // 5
//		int originListSize = projectTechStackList.size(); // 3
//		log.info("Initial size :"+inputListSize+" "+originListSize);
//		List<Integer> differOrder = new ArrayList<>();
//
//		if (originListSize > inputListSize) {  // 삭제를 해야하는 경우
//			for (int i = originListSize; i > inputListSize; i--) {
//				differOrder.add(i);
//			}
//			for (int i : differOrder) {
//				projectTechStackRepository.deleteProjectTechStackByOrder(i);
//
//			}
//			projectTechStackList = projectTechStackRepository.getAllProjectTechStackByOrder(projectId); // 다시 가져옴
//
//		}
//		else if (originListSize < inputListSize) // 추가를 해야하는 경우
//		{
//			for (int i = inputListSize - 1; i > originListSize - 1; i--) {
//				TechStackForm tempTechStackForm = techStackFormList.remove(i);
//				ProjectTechStack tempProjectTechStack = ProjectTechStack.builder()
//					.project(project)
//					.techStack(techstackRepository.getTechStackById(tempTechStackForm.getId()))
//					.order(i+1).build();
//				projectTechStackRepository.save(tempProjectTechStack); // 뒤에 있는 거 저장
//			}
//			inputListSize = techStackFormList.size();
//		}
//		if(inputListSize==0){
//			return "SUCCESS";
//		}
//		for (int size = 0; size < inputListSize; size++) {
//
//			TechStackForm techStackForm = techStackFormList.get(size);
//
//			ProjectTechStack tempProjectTechStack = ProjectTechStack.builder()
//				.project(project)
//				.techStack(techstackRepository.getTechStackById(techStackForm.getId()))
//				.order(size + 1).build();
//			log.info("TechStack > "+tempProjectTechStack.getTechStack().getName()+tempProjectTechStack.getOrder());
//
//			ProjectTechStack originProjectTechStack = projectTechStackRepository.getProjectTechStack(projectId,tempProjectTechStack.getTechStack().getId());
//
//
//			originProjectTechStack.setTechStack(tempProjectTechStack.getTechStack());
//			originProjectTechStack.setOrder(tempProjectTechStack.getOrder());
//			projectTechStackRepository.save(originProjectTechStack);
//		}


		List<ProjectTechStack> resultList= projectTechStackRepository.getAllProjectTechStackByOrder(projectId);
		List<TechStackForm> returnList = new ArrayList<>();
		for(ProjectTechStack projectTechStack : resultList)
		{
			TechStackForm techStackForm = TechStackForm.toEntity(projectTechStack.getTechStack());

			returnList.add(techStackForm);
		}
		return returnList;
	}

	public List<TechStackForm> modifySideProjectTechStack(Long projectId, List<TechStackForm> techStackFormList) {

		SidePjt sidePjt = sideProjectRepository.getSideProjectById(projectId);
		List<SidePjtTechStack> sidePjtTechStackList = sideProjectTechStackRepository.getSideProjectTechStacks(projectId);

		boolean isChanged = false;
		int inputListSize = techStackFormList.size(); // 5
		int originListSize = sidePjtTechStackList.size(); // 3

		if(inputListSize!= originListSize)
		{
			isChanged=true;
		}
		else if(inputListSize==originListSize)
		{
			for(int i=0;i<inputListSize;i++)
			{
				TechStackForm techStackForm = techStackFormList.get(i);
				SidePjtTechStack sideProjectTechStack = sidePjtTechStackList.get(i);

				if(!techStackForm.getId().equals(sideProjectTechStack.getTechStack().getId()))
				{
					isChanged=true;
					break;
				}
			}
		}
		if(!isChanged)
		{
			return null;
		}
		else
		{
			Long deleteCount = sideProjectTechStackRepository.deleteAllSideProjectTechStack(projectId);
			for(int i =0;i<inputListSize;i++)
			{
				SidePjtTechStack sidePjtTechStack = SidePjtTechStack.builder()
						.sidePjt(sidePjt)
						.techStack(techstackRepository.getTechStackById(techStackFormList.get(i).getId()))
						.order(i+1).build();

				sideProjectTechStackRepository.save(sidePjtTechStack);
			}
		}


//		SidePjt sidePjt = sideProjectRepository.getSideProjectById(projectId);
//		List<SidePjtTechStack> sidePjtTechStackList = sideProjectTechStackRepository.getSideProjectsByOrderAsc(projectId);
//		// 리스트 전처리 작업
//		int inputListSize = techStackFormList.size(); // 5
//		int originListSize = sidePjtTechStackList.size(); // 3
//		log.info("Initial size :"+inputListSize+" "+originListSize);
//		List<Integer> differOrder = new ArrayList<>();
//
//		if (originListSize > inputListSize) {  // 삭제를 해야하는 경우
//			for (int i = originListSize; i > inputListSize; i--) {
//				differOrder.add(i);
//			}
//			for (int i : differOrder) {
//				sideProjectTechStackRepository.deleteSideProjectTechStackByOrder(i);
//
//			}
//			sidePjtTechStackList = sideProjectTechStackRepository.getSideProjectsByOrderAsc(projectId); // 다시 가져옴
//
//		}
//		else if (originListSize < inputListSize) // 추가를 해야하는 경우
//		{
//			for (int i = inputListSize - 1; i > originListSize - 1; i--) {
//				TechStackForm tempTechStackForm = techStackFormList.remove(i);
//				SidePjtTechStack tempSidePjtTechStack = SidePjtTechStack.builder()
//						.sidePjt(sidePjt)
//						.techStack(techstackRepository.getTechStackById(tempTechStackForm.getId()))
//						.order(i+1).build();
//				sideProjectTechStackRepository.save(tempSidePjtTechStack); // 뒤에 있는 거 저장
//			}
//			inputListSize = techStackFormList.size();
//		}
//		if(inputListSize==0){
//			return "SUCCESS";
//		}
//		for (int size = 0; size < inputListSize; size++) {
//
//			TechStackForm techStackForm = techStackFormList.get(size);
//
//			SidePjtTechStack tempSidePjtTechStack = SidePjtTechStack.builder()
//					.sidePjt(sidePjt)
//					.techStack(techstackRepository.getTechStackById(techStackForm.getId()))
//					.order(size + 1).build();
//			log.info("SidePjtTechStack > "+tempSidePjtTechStack.getTechStack().getName()+tempSidePjtTechStack.getOrder());
//
//			SidePjtTechStack originSidePjtTechStack = sideProjectTechStackRepository.getSidePjtTechStack(projectId,tempSidePjtTechStack.getTechStack().getId());
//
//
//			originSidePjtTechStack.setTechStack(tempSidePjtTechStack.getTechStack());
//			originSidePjtTechStack.setOrder(tempSidePjtTechStack.getOrder());
//			sideProjectTechStackRepository.save(originSidePjtTechStack);
//		}


		List<SidePjtTechStack> resultList= sideProjectTechStackRepository.getSideProjectTechStacks(projectId);
		List<TechStackForm> returnList = new ArrayList<>();
		for(SidePjtTechStack sidePjtTechStack : resultList)
		{
			TechStackForm techStackForm = TechStackForm.toEntity(sidePjtTechStack.getTechStack());

			returnList.add(techStackForm);
		}
		return returnList;
	}
}
