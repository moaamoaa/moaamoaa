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
import com.ssafy.moamoa.repository.ProfileRepository;
import com.ssafy.moamoa.repository.ProjectRepository;
import com.ssafy.moamoa.repository.TeamRepository;
import com.ssafy.moamoa.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApplyService {

	private final UserService userService;
	private final ProjectService projectService;
	private final ProjectRepository projectRepository;
	private final ApplyRepository applyRepository;
	private final UserRepository userRepository;
	private final TeamRepository teamRepository;
	private final ProfileRepository profileRepository;

	// 지원 보내기
	public void sendApply(Long userId, Long projectId) throws Exception {
		User user = userService.findUser(userId);
		Project project = projectService.findProjectById(projectId);

		if(teamRepository.findByUser(user, project).isPresent())
		{
			throw new Exception("이미 참여하고 있습니다.");
		}
		if(project.isLocked() || !(projectRepository.findById(projectId).isPresent()))
		{
			throw new Exception("존재하지 않는 프로젝트입니다.");
		}
		if(applyRepository.findByUser_IdAndProject_Id(userId, projectId).isPresent())
		{
			throw new Exception("이미 지원을 보냈습니다.");
		}
		else
		{
			Apply apply = Apply.builder()
				.user(user)
				.project(project)
				.time(LocalDateTime.now())
				.build();
			applyRepository.save(apply);
			project.setCountApply(project.getCountApply() + 1);
		}
	}

	public List<ApplyForm> showSendApply(Long userId) {
		User user = userRepository.findById(userId).get();
		List<Apply> applies = applyRepository.findByUser(user);
		List<ApplyForm> applyForms = new ArrayList<>();
		for (Apply a : applies) {
			if(a.getProject().isLocked()) continue;
			ApplyForm applyForm = ApplyForm.toEntity(a);
			applyForm.setNickname(profileRepository.findByUser(a.getUser()).get().getNickname());
			applyForm.setProfileContext(profileRepository.findByUser(a.getUser()).get().getContext());
			applyForm.setTitle(a.getProject().getTitle());
			applyForm.setProjectContents(a.getProject().getContents());
			applyForm.setProjectImg(a.getProject().getImg());
			applyForms.add(applyForm);
		}
		return applyForms;
	}

	public List<ApplyForm> showReceiveApply(Long projectId) {
		Project project = projectRepository.findById(projectId).get();
		List<Apply> applies = applyRepository.findByProject(project);
		List<ApplyForm> applyForms = new ArrayList<>();
		for (Apply a : applies) {
			if(a.getUser().isLocked()) continue;
			ApplyForm applyForm = ApplyForm.toEntity(a);
			applyForm.setNickname(profileRepository.findByUser(a.getUser()).get().getNickname());
			applyForm.setProfileContext(profileRepository.findByUser(a.getUser()).get().getContext());
			applyForm.setTitle(a.getProject().getTitle());
			applyForm.setProjectContents(a.getProject().getContents());
			applyForm.setProfileImg(profileRepository.findByUser(a.getUser()).get().getImg());
			applyForms.add(applyForm);
		}
		return applyForms;
	}

	// 지원 수락
	public void acceptApply(MatchingForm matchingForm) throws Exception {
		// 수락할 user id를 받고 -> team에 등록
		Project project = projectRepository.findById(matchingForm.getProjectId()).get();
		int change = project.getCurrentPeople() + 1;
		if(project.getTotalPeople() >= change) {
			// 지원자를 팀에 등록
			project.setCurrentPeople(change);

			User user = userRepository.findById(matchingForm.getUserId()).get();
			Team team = Team.builder()
				.role(TeamRole.MEMBER)
				.project(project)
				.user(user)
				.build();
			teamRepository.save(team);

			deleteReceiveApply(matchingForm.getApplyId());
		}
		else throw new Exception("인원 모집이 끝났습니다.");
	}

	public void deleteSendApply(MatchingForm matchingForm) {
		// 철회할 apply id를 받고 -> apply에서 삭제
		// 팀의 cnt_apply--
		Project project = projectRepository.findById(matchingForm.getProjectId()).get();
		project.setCountApply(project.getCountApply() - 1);
		applyRepository.deleteById(matchingForm.getApplyId());
	}

	public void deleteReceiveApply(Long applyId) {
		// 철회할 apply id를 받고 -> apply에서 삭제
		applyRepository.deleteById(applyId);
	}
}