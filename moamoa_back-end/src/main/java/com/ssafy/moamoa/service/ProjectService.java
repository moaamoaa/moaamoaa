package com.ssafy.moamoa.service;

import com.ssafy.moamoa.domain.ProjectCategory;
import com.ssafy.moamoa.domain.ProjectStatus;
import com.ssafy.moamoa.domain.TeamRole;
import com.ssafy.moamoa.domain.dto.*;
import com.ssafy.moamoa.domain.entity.*;
import com.ssafy.moamoa.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ProjectService {

	private final AreaService areaService;
	private final TeamService teamService;
	private final TechStackService techStackService;
	private final ProjectRepository projectRepository;
	private final ProjectTechStackRepository projectTechStackRepository;
	private final UserRepository userRepository;
	private final TeamRepository teamRepository;
	private final ProfileRepository profileRepository;

	private final S3Service s3Service;
	private final ImageService imageService;
	private final ProjectAreaRepository projectAreaRepository;

	public void isProjectLocked(Long id) throws Exception {
		Project project = findProjectById(id);
		if (project.isLocked()) {
			if (project.getCategory() == ProjectCategory.PROJECT) {
				throw new EntityNotFoundException("해당 프로젝트는 존재하지 않습니다.");
			} else if (project.getCategory() == ProjectCategory.STUDY) {
				throw new EntityNotFoundException("해당 스터디는 존재하지 않습니다.");
			}
		}
	}

	public Project findProjectById(Long id) {
		Optional<Project> findProject = projectRepository.findById(id);
		if (!findProject.isPresent()) {
			throw new EntityNotFoundException("해당 id " + id + "의 프로젝트가 없습니다.");
		}
		return findProject.get();
	}

	public void checkPeriod(LocalDate endDate) {

		LocalDate startDate = LocalDate.now();
		Period diff = Period.between(startDate, endDate);
		if (diff.getYears() != 0 || diff.getMonths() != 0 || diff.getDays() > 28) {
			throw new IllegalArgumentException("잘못된 기간 설정");
		}
	}

	public void checkCntPeople(int cntPeople, int minCnt) {

		if (cntPeople > 10) {
			throw new IllegalArgumentException("잘못된 인원수 설정");
		}

		if (cntPeople < minCnt) {
			throw new IllegalArgumentException("잘못된 인원수 설정");
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
	public ProjectDetail creatProject(ProjectForm projectForm, MultipartFile file) throws Exception {
		boolean isImgNull = false;

		if (file == null) {
			isImgNull = true;
		}
		// 기간 4주이내인지 확인
		LocalDate endDate = LocalDate.parse(projectForm.getEndDate(), DateTimeFormatter.ISO_DATE);
		checkPeriod(endDate);

		// 인원수 10이하인지 확인
		int cntPeople = projectForm.getTotalPeople();
		checkCntPeople(cntPeople, 1);

		// 유저 정보 확인
		Optional<User> findUsers = userRepository.findById(projectForm.getUserId());
		if (!findUsers.isPresent()) {
			throw new EntityNotFoundException("해당 id " + projectForm.getUserId() + "의 유저가 없습니다.");
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
				.img(imageService.getRandomDefaultProjectImage())
				.title(projectForm.getTitle())
				.contents(projectForm.getContents())
				.totalPeople(cntPeople)
				.currentPeople(1)
				.isLocked(false)
				.build();
		Project projectSaved = projectRepository.save(project);

		// Setting img to our Project
		if (!isImgNull) {
			projectSaved.setImg(s3Service.uploadProjectImg(projectSaved.getId(), file, projectSaved.getTitle()));
		}
		// team
		Optional<User> findUser = userRepository.findById(projectForm.getUserId());
		User user = findUser.get();
		Team team = Team.builder().role(TeamRole.LEADER).project(project).user(user).build();
		teamRepository.save(team);

		// project techstack
		techStackService.modifyProjectTechStack(project.getId(), projectForm.getTechStacks());

		// project area
		areaService.addProjectAreaList(project, projectForm.getAreaId());

		ProjectDetail projectDetail = accessProject(project.getId(), 0);
		return projectDetail;
	}

	// 프로젝트/스터디 수정
	// 유저 id, projectForm, file
	public ProjectDetail updateProject(ProjectForm projectForm, MultipartFile file) throws Exception {
		boolean imgExist = false;
		if (file != null) {
			imgExist = true;
		}

		// locked check
		isProjectLocked(projectForm.getProjectId());

		Project project = findProjectById(projectForm.getProjectId());
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
		project.setContents(projectForm.getContents());
		// Set Project Img
		if (imgExist) {
			project.setImg(s3Service.uploadProjectImg(project.getId(), file, projectForm.getTitle()));
		}


		// project techstack
		techStackService.modifyProjectTechStack(project.getId(), projectForm.getTechStacks());

		// project area
		Long areaId = projectForm.getAreaId();
		ProjectArea projectArea = areaService.findProjectAreaList(project);
		areaService.deleteProjectAreaList(projectArea);
		areaService.addProjectAreaList(project, areaId);

		ProjectDetail projectDetail = accessProject(project.getId(), 0);
		return projectDetail;
	}

	// 프로젝트/스터디 삭제
	public void deleteProject(ProjectForm projectForm) throws Exception {
		// locked check
		isProjectLocked(projectForm.getProjectId());

		Project findProject = projectRepository.findById(projectForm.getProjectId()).get();
		findProject.setLocked(true);
	}

	// 팀원 삭제
	public void deleteMember(ProjectForm projectForm) throws Exception {
		// locked check
		isProjectLocked(projectForm.getProjectId());

		if (!teamRepository.findByUser_IdAndProject_Id(projectForm.getUserId(), projectForm.getProjectId()).isPresent()
				|| userRepository.findById(projectForm.getUserId()).get().isLocked()) {
			throw new EntityNotFoundException("존재하지 않는 팀원입니다.");
		}
		Team fineTeam = teamRepository.findByUser_IdAndProject_Id(projectForm.getUserId(), projectForm.getProjectId())
				.get();
		teamRepository.delete(fineTeam);
	}

	// 자신이 속한 프로젝트/스터디 반환
	public List<ProjectForm> findByUser(Long id, ProjectCategory projectCategory) {
		List<Team> teams = teamRepository.findByUser_IdAndProjectCategory(id, projectCategory);
		List<ProjectForm> projectForms = new ArrayList<>();
		for (Team t : teams) {
			if (t.getProject().isLocked())
				continue;
			ProjectForm projectForm = ProjectForm.toEntity(t.getProject());
			projectForms.add(projectForm);
		}
		return projectForms;
	}

	// 팀 페이지 return
	public ProjectDetail accessProject(Long projectId, int hit) throws Exception {
		// locked check
		isProjectLocked(projectId);

		Project project = findProjectById(projectId);
		project.setHit(project.getHit() + hit);
		ProjectDetail projectDetail = ProjectDetail.toEntity(project);

		// Team
		List<Team> teams = teamRepository.findByProject_Id(project.getId());
		List<ProfileResultDto> profileResultDtoList = new ArrayList<>();
		for (Team t : teams) {
			if (t.getUser().isLocked())
				continue;
			Profile profile = profileRepository.findByUser_Id(t.getUser().getId()).get();
			ProfileResultDto profileResultDto = new ProfileResultDto(profile.getId(), profile.getNickname(),
					profile.getImg(), profile.getContext(), profile.getProfileOnOffStatus());
			profileResultDtoList.add(profileResultDto);
			if (t.getRole() == TeamRole.LEADER) {
				projectDetail.setLeaderId(t.getUser().getId());
				projectDetail.setLeaderNickname(profile.getNickname());

			}
		}
		projectDetail.setProfileResultDtoList(profileResultDtoList);

		// TechStack
		List<TechStackForm> techStackForms = new ArrayList<>();
		List<TechStack> techStacks = projectTechStackRepository.findByProject_Id(projectId)
				.stream()
				.map(t -> t.getTechStack())
				.collect(Collectors.toList());
		if (!techStacks.isEmpty()) {
			for (TechStack t : techStacks) {
				TechStackForm techStackForm = TechStackForm.toEntity(t);
				techStackForms.add(techStackForm);
			}
			projectDetail.setProjectTechStacks(techStackForms);
		}

		// area
		projectDetail.setAreaForm(AreaForm.toEntity(projectAreaRepository.getProjectAreaById(projectId).getArea()));

		return projectDetail;
	}

	public boolean setIsLeader(Long userId, Long projectId) {
		return teamService.checkLeader(userId, projectId);
	}

}

