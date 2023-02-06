package com.ssafy.moamoa.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.TeamRole;
import com.ssafy.moamoa.domain.dto.ApplyForm;
import com.ssafy.moamoa.domain.dto.MatchingForm;
import com.ssafy.moamoa.domain.entity.Apply;
import com.ssafy.moamoa.domain.entity.Project;
import com.ssafy.moamoa.domain.entity.Team;
import com.ssafy.moamoa.domain.entity.User;
import com.ssafy.moamoa.repository.ApplyRepository;
import com.ssafy.moamoa.repository.ProjectRepository;
import com.ssafy.moamoa.repository.TeamRepository;
import com.ssafy.moamoa.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApplyService {

	private final UserService userService;
	private final TeamService teamService;
	private final ProjectService projectService;
	private final ProjectRepository projectRepository;
	private final ApplyRepository applyRepository;
	private final UserRepository userRepository;
	private final TeamRepository teamRepository;

	// 지원 보내기
	public void sendApply(Long userId, Long projectId) throws Exception {

		User user = userService.findUser(userId);
		Project project = projectService.findProjectById(projectId);


		if(teamRepository.findByUser(user, project).isPresent() || project.isLocked()){throw new Exception();}
		else
		{
			project.setCountApply(project.getCountApply() + 1);
			Apply apply = Apply.builder()
				.user(user)
				.project(project)
				.time(LocalDateTime.now())
				.build();
			applyRepository.save(apply);
		}
	}

	public List<ApplyForm> showSendApply(Long userId) {
		User user = userRepository.findById(userId).get();
		List<Apply> applies = applyRepository.findByUser(user);
		List<ApplyForm> applyForms = new ArrayList<>();
		for (Apply a : applies) {
			ApplyForm applyForm = ApplyForm.toEntity(a);
			applyForms.add(applyForm);
		}
		return applyForms;
	}

	public List<ApplyForm> showReceiveApply(Long projectId) {
		Project project = projectRepository.findById(projectId).get();
		List<Apply> applies = applyRepository.findByProject(project);
		List<ApplyForm> applyForms = new ArrayList<>();
		for (Apply a : applies) {
			ApplyForm applyForm = ApplyForm.toEntity(a);
			applyForms.add(applyForm);
		}
		return applyForms;
	}

	// 지원 수락
	public void acceptApply(MatchingForm matchingForm) throws Exception {
		Project project = projectRepository.findById(matchingForm.getProjectId()).get();
		// 잠김 확인
		if(!project.isLocked()) {
			// 지원자를 팀에 등록
			int change = project.getCurrentPeople() + 1;
			project.setCurrentPeople(change);

			// 인원수 다 차면 잠그기
			if(project.getTotalPeople() == change) {project.setLocked(true);}

			User user = userRepository.findById(matchingForm.getUserId()).get();
			Team team = Team.builder()
				.role(TeamRole.MEMBER)
				.project(project)
				.user(user)
				.build();
			teamRepository.save(team);

			deleteReceiveApply(matchingForm.getApplyId());
		}
		else throw new Exception();
	}

	public void deleteSendApply(MatchingForm matchingForm) {
		// cnt_apply--
		Project project = projectRepository.findById(matchingForm.getProjectId()).get();
		project.setCountApply(project.getCountApply() - 1);
		applyRepository.deleteById(matchingForm.getApplyId());
	}

	public void deleteReceiveApply(Long applyId) {
		applyRepository.deleteById(applyId);
	}
}