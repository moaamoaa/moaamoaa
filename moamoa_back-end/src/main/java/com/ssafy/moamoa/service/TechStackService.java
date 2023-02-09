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
import com.ssafy.moamoa.domain.entity.Project;
import com.ssafy.moamoa.domain.entity.ProjectTechStack;
import com.ssafy.moamoa.domain.entity.SidePjt;
import com.ssafy.moamoa.domain.entity.SidePjtTechStack;
import com.ssafy.moamoa.domain.entity.TechStack;
import com.ssafy.moamoa.repository.ProfileRepository;
import com.ssafy.moamoa.repository.ProfileTechStackRepository;
import com.ssafy.moamoa.repository.ProjectRepository;
import com.ssafy.moamoa.repository.ProjectTechStackRepository;
import com.ssafy.moamoa.repository.SideProjectRepository;
import com.ssafy.moamoa.repository.SideProjectTechStackRepository;
import com.ssafy.moamoa.repository.TechStackRepository;
import com.ssafy.moamoa.repository.UserRepository;

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
				.img(profileTechStack.getTechStack().getLogo())
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
				.img(sidePjtTechStack.getTechStack().getLogo())
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
		TechStackForm techStackForm = TechStackForm.builder()
				.id(profileTechStack.getTechStack().getId())
				.name(profileTechStack.getTechStack().getName())
				.img(profileTechStack.getTechStack().getLogo()).build();
		log.info(profileTechStack.getTechStack().getName() +" "+profileTechStack.getTechStack().getLogo());
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
