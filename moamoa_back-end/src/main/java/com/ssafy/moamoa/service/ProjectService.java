package com.ssafy.moamoa.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.ProfileOnOffStatus;
import com.ssafy.moamoa.domain.ProjectCategory;
import com.ssafy.moamoa.domain.ProjectStatus;
import com.ssafy.moamoa.domain.TeamRole;
import com.ssafy.moamoa.domain.dto.ProfileResultDto;
import com.ssafy.moamoa.domain.dto.ProjectDetail;
import com.ssafy.moamoa.domain.dto.ProjectForm;
import com.ssafy.moamoa.domain.dto.SearchCondition;
import com.ssafy.moamoa.domain.dto.TechStackForm;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.Project;
import com.ssafy.moamoa.domain.entity.ProjectArea;
import com.ssafy.moamoa.domain.entity.ProjectTechStack;
import com.ssafy.moamoa.domain.entity.Team;
import com.ssafy.moamoa.domain.entity.TechStack;
import com.ssafy.moamoa.domain.entity.User;
import com.ssafy.moamoa.exception.BadRequestException;
import com.ssafy.moamoa.exception.NotFoundUserException;
import com.ssafy.moamoa.repository.ProfileRepository;
import com.ssafy.moamoa.repository.ProjectAreaRepository;
import com.ssafy.moamoa.repository.ProjectRepository;
import com.ssafy.moamoa.repository.ProjectTechStackRepository;
import com.ssafy.moamoa.repository.TeamRepository;
import com.ssafy.moamoa.repository.TechStackRepository;
import com.ssafy.moamoa.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectService {

	private final UserService userService;
	private final AreaService areaService;
	private final TeamService teamService;
	private final TechStackService techStackService;
	private final ProjectRepository projectRepository;
	private final TechStackRepository techstackRepository;
	private final ProjectTechStackRepository projectTechStackRepository;
	private final UserRepository userRepository;
	private final TeamRepository teamRepository;
	private final ProfileRepository profileRepository;
	private final ProjectAreaRepository projectAreaRepository;

	public Boolean isLocked(Long id){
		Project project = projectRepository.findById(id).get();
		return project.isLocked();
	}
	public Project findProjectById(Long id) {
		Optional<Project> findProject = projectRepository.findById(id);
		Project project = findProject.get();
		return project;
	}

	public void checkPeriod(LocalDate endDate) throws BadRequestException {

		LocalDate startDate = LocalDate.now();
		Period diff = Period.between(startDate, endDate);
		if (diff.getDays() > 28) {
			throw new BadRequestException("잘못된 기간 설정");
		}
	}

	public void checkCntPeople(int cntPeople, int minCnt) throws BadRequestException {

		if (cntPeople > 10) {
			throw new BadRequestException("잘못된 인원수 설정");
		}

		if (cntPeople < minCnt) {
			throw new BadRequestException("잘못된 인원수 설정");
		}
	}

	// 프로젝트 전체 조회
	public List<Project> findProjects() {
		return projectRepository.findProjectByCategory(ProjectCategory.PROJECT);
	}

	// 스터디 전체 조회
	public List<Project> findStudies() {
		return projectRepository.findProjectByCategory(ProjectCategory.STUDY);
	}

	// 프로젝트/스터디 등록
	public ProjectDetail creatProject(ProjectForm projectForm) throws Exception {

		// 기간 4주이내인지 확인
		LocalDate endDate = LocalDate.parse(projectForm.getEndDate(), DateTimeFormatter.ISO_DATE);
		checkPeriod(endDate);

		// 인원수 10이하인지 확인
		int cntPeople = projectForm.getTotalPeople();
		checkCntPeople(cntPeople, 1);

		// 유저 정보 확인
		Optional<User> findUsers = userRepository.findById(projectForm.getUserId());
		if (!findUsers.isPresent()) {
			throw new NotFoundUserException("해당 id의 유저가 없습니다.");
		}

		// project
		ProjectCategory projectCategory = ProjectCategory.PROJECT;

		switch (projectForm.getCategory()) {
			case "PROJECT":
				projectCategory = ProjectCategory.PROJECT;
				break;
			case "STUDY":
				projectCategory = ProjectCategory.STUDY;
				break;
		}

		ProjectStatus projectStatus = ProjectStatus.ONLINE;
		switch (projectForm.getProjectStatus()) {
			case "ONLINE":
				projectStatus = ProjectStatus.ONLINE;
				break;
			case "OFFLINE":
				projectStatus = ProjectStatus.OFFLINE;
				break;
		}
		Project project = Project.builder()
			.category(projectCategory)
			.countApply(0)
			.hit(0)
			.img(projectForm.getImg())
			.onoffline(projectStatus)
			.createDate(LocalDate.now())
			.startDate(LocalDate.now())
			.endDate(endDate)
			.title(projectForm.getTitle())
			.contents(projectForm.getContents())
			.totalPeople(cntPeople)
			.currentPeople(1)
			.isLocked(false)
			.build();
		projectRepository.save(project);

		// team
		Optional<User> findUser = userRepository.findById(projectForm.getUserId());
		User user = findUser.get();
		Team team = Team.builder()
			.role(TeamRole.LEADER)
			.project(project)
			.user(user)
			.build();
		teamRepository.save(team);

		// project techstack
		techStackService.modifyProjectTechStack(project.getId(), projectForm.getTechStacks());

		// project area
		areaService.addProjectAreaList(project, projectForm.getAreaId());

		ProjectDetail projectDetail = accessProject(project.getId());
		return projectDetail;
	}

	// 프로젝트/스터디 수정
	public ProjectDetail updateProject(ProjectForm projectForm) throws Exception {

		Optional<Project> findProject = projectRepository.findById(projectForm.getProjectId());
		Project project = findProject.get();
		LocalDate endDate = LocalDate.parse(projectForm.getEndDate(), DateTimeFormatter.ISO_DATE);
		int cntPeople = projectForm.getTotalPeople();

		LocalDate startDate = project.getStartDate();
		if (!(project.getEndDate().equals(endDate))) {
			// 기간 4주이내인지 확인
			startDate = LocalDate.now();
			checkPeriod(endDate);
		}

		if (project.getTotalPeople() != cntPeople) {
			List<Team> findTeam = teamRepository.findByProject_Id(project.getId());
			int minCnt = findTeam.size();
			// 인원수 10이하인지 확인 & 팀원들의 인원수보다 작은지
			checkCntPeople(cntPeople, minCnt);
		}

		ProjectStatus projectStatus = ProjectStatus.ONLINE;
		switch (projectForm.getProjectStatus()) {
			case "ONLINE":
				projectStatus = ProjectStatus.ONLINE;
				break;
			case "OFFLINE":
				projectStatus = ProjectStatus.OFFLINE;
				break;
		}

		// project
		project.setOnoffline(projectStatus);
		project.setStartDate(startDate);
		project.setEndDate(endDate);
		project.setTitle(projectForm.getTitle());
		project.setTotalPeople(cntPeople);
		project.setImg(projectForm.getImg());
		project.setContents(projectForm.getContents());

		// project techstack
		techStackService.modifyProjectTechStack(project.getId(), projectForm.getTechStacks());

		// project area
		Long areaId = projectForm.getAreaId();
		ProjectArea projectArea = areaService.findProjectAreaList(project);
		areaService.deleteProjectAreaList(projectArea);
		areaService.addProjectAreaList(project, areaId);

		ProjectDetail projectDetail = accessProject(project.getId());
		return projectDetail;
	}

	// 프로젝트/스터디 삭제
	public void deleteProject(Long id) {

		Optional<Project> findProject = projectRepository.findById(id);
		Project project = findProject.get();

		// team
		List<Team> findTeam = teamRepository.findByProject_Id(project.getId());
		for (Team t : findTeam) {
			teamRepository.delete(t);
		}

		// project techstack
		List<ProjectTechStack> projectTechStacks = projectTechStackRepository.findByProject(project);
		for (ProjectTechStack ts : projectTechStacks) {
			projectTechStackRepository.delete(ts);
		}

		// project area
		ProjectArea projectArea = areaService.findProjectAreaList(project);
		areaService.deleteProjectAreaList(projectArea);

		// project
		projectRepository.delete(project);
	}

	// 프로젝트/스터디 삭제
	public void deleteMember(ProjectForm projectForm) {
		Project findProject = projectRepository.findById(projectForm.getProjectId()).get();
		findProject.setLocked(true);
	}

	public List<ProjectForm> findByUser(Long id) {
		List<Team> teams = teamRepository.findByUser_Id(id);
		List<ProjectForm> projectForms = new ArrayList<>();
		for (Team t: teams) {
			ProjectForm projectForm = ProjectForm.toEntity(t.getProject());
			projectForms.add(projectForm);
		}
		return projectForms;
	}

	// 팀 페이지 return
	public ProjectDetail accessProject(Long projectId)
	{
		Project project = projectRepository.findById(projectId).get();
		project.setHit(project.getHit()+1);
		ProjectDetail projectDetail = ProjectDetail.toEntity(project);

		// Team
		List<Team> teams = teamRepository.findByProject_Id(project.getId());
		List<ProfileResultDto> profileResultDtoList = new ArrayList<>();
		for (Team t:teams) {
			Profile profile = profileRepository.findByUser_Id(t.getUser().getId()).get();
			ProfileResultDto profileResultDto = new ProfileResultDto(profile.getId(),profile.getNickname(),profile.getContext(),profile.getProfileOnOffStatus());
			profileResultDtoList.add(profileResultDto);
			if(t.getRole()==TeamRole.LEADER)
			{
				projectDetail.setLeaderId(t.getUser().getId());
				projectDetail.setLeaderNickname(profile.getNickname());

			}
		}
		projectDetail.setProfileResultDtoList(profileResultDtoList);

		// TechStack
		List<TechStack> techStacks = projectTechStackRepository.findByProject_Id(projectId)
			.stream()
			.map(t -> t.getTechStack())
			.collect(Collectors.toList());
		if (!techStacks.isEmpty()) {
			/*System.out.println("not null");
			for (TechStack t: techStacks) {
				System.out.println(t);
			}*/
			projectDetail.setTechStacks(techStacks);
		}
		/*List<ProjectTechStack> resultList = projectTechStackRepository.getAllProjectTechStackByOrder(projectId);
		List<TechStack> techStacks = new ArrayList<>();
		//if (!resultList.isEmpty()) {
			for (ProjectTechStack pt: resultList) {
				TechStack techStack = pt.getTechStack();
				techStacks.add(techStack);
			}
			projectDetail.setTechStacks(techStacks);
		//}*/

		// area
		projectDetail.setAreaId(projectAreaRepository.findByProject_Id(projectId).get().getArea().getId());

		return projectDetail;
	}

	public boolean setIsLeader(Long userId, Long projectId)
	{
		boolean isLeader = teamService.checkLeader(userId, projectId);
		return isLeader;
	}

}

