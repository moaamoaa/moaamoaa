package com.ssafy.moamoa.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.Project;
import com.ssafy.moamoa.domain.ProjectArea;
import com.ssafy.moamoa.domain.ProjectCategory;
import com.ssafy.moamoa.domain.ProjectStatus;
import com.ssafy.moamoa.domain.ProjectTechStack;
import com.ssafy.moamoa.domain.Team;
import com.ssafy.moamoa.domain.TeamRole;
import com.ssafy.moamoa.domain.TechStack;
import com.ssafy.moamoa.domain.User;
import com.ssafy.moamoa.dto.ProjectForm;
import com.ssafy.moamoa.exception.BadRequestException;
import com.ssafy.moamoa.exception.NotFoundUserException;
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

	private final AreaService areaService;
	private final ProjectRepository projectRepository;
	private final TechStackRepository techstackRepository;
	private final ProjectTechStackRepository projectTeckStackRepository;
	private final UserRepository userRepository;
	private final TeamRepository teamRepository;
	private final UserService userService;

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
	public void creatProject(ProjectForm projectForm) throws Exception {

		// 기간 4주이내인지 확인
		LocalDate endDate = LocalDate.parse(projectForm.getEndDate(), DateTimeFormatter.ISO_DATE);
		checkPeriod(endDate);

		// 인원수 10이하인지 확인
		int cntPeople = projectForm.getTotalPeople();
		checkCntPeople(cntPeople, 1);

		// 팀원 정보 확인
		Optional<User> findUsers = userRepository.findById(projectForm.getUserid());
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
			.countOffer(0)
			.hit(0)
			.onoffline(projectStatus)
			.createDate(LocalDate.now())
			.startDate(LocalDate.now())
			.endDate(endDate)
			.title(projectForm.getTitle())
			.totalPeople(cntPeople)
			.currentPeople(1)
			.isLocked(false)
			.build();
		projectRepository.save(project);

		// team
		Optional<User> finduser = userRepository.findById(projectForm.getUserid());
		User user = finduser.get();
		Team team = Team.builder()
			.role(TeamRole.LEADER)
			.project(project)
			.user(user)
			.build();
		teamRepository.save(team);

		// project techstack
		Long[] teckstacks = projectForm.getTechStacks();
		for (int i = 0; i < teckstacks.length; i++) {
			Optional<TechStack> findtechStack = techstackRepository.findById(teckstacks[i]);
			TechStack techStack = findtechStack.get();

			ProjectTechStack projectTechStack = ProjectTechStack.builder()
				.project(project)
				.techStack(techStack)
				.build();
			projectTeckStackRepository.save(projectTechStack);
		}

		// project area
		areaService.addProjectAreaList(project, projectForm.getAreas());
	}

	// 프로젝트/스터디 수정
	public void updateProject(Long id, ProjectForm projectForm) throws Exception {

		Optional<Project> findProject = projectRepository.findById(id);
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
			List<Team> findTeam = teamRepository.findByProject(project);
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

		// project techstack
		List<ProjectTechStack> projectTechStacks = projectTeckStackRepository.findByProject(project);
		for (ProjectTechStack ts : projectTechStacks) {
			projectTeckStackRepository.delete(ts);
		}

		Long[] teckstacks = projectForm.getTechStacks();
		for (int i = 0; i < teckstacks.length; i++) {
			Optional<TechStack> findtechStack = techstackRepository.findById(teckstacks[i]);
			TechStack techStack = findtechStack.get();

			ProjectTechStack projectTechStack = ProjectTechStack.builder()
				.project(project)
				.techStack(techStack)
				.build();
			projectTeckStackRepository.save(projectTechStack);
		}

		// project area
		Long[] areas = projectForm.getAreas();
		List<ProjectArea> projectAreas = areaService.findProjectAreaList(project);
		areaService.deleteProjectAreaList(projectAreas);
		areaService.addProjectAreaList(project, areas);
	}

	// 프로젝트/스터디 삭제
	public void deleteProject(Long id) {

		Optional<Project> findProject = projectRepository.findById(id);
		Project project = findProject.get();

		// team
		List<Team> findTeam = teamRepository.findByProject(project);
		for (Team t : findTeam) {
			teamRepository.delete(t);
		}

		// project techstack
		List<ProjectTechStack> projectTechStacks = projectTeckStackRepository.findByProject(project);
		for (ProjectTechStack ts : projectTechStacks) {
			projectTeckStackRepository.delete(ts);
		}

		// project area
		List<ProjectArea> projectAreas = areaService.findProjectAreaList(project);
		areaService.deleteProjectAreaList(projectAreas);

		// project
		projectRepository.delete(project);
	}

	public List<Project> findByUser(Long id) {
		User user = userService.findUser(id);
		List<Project> projectList = teamRepository.findByProject(user);
		return projectList;
	}
}

