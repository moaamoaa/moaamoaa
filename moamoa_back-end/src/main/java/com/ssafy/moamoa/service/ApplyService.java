package com.ssafy.moamoa.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.Apply;
import com.ssafy.moamoa.domain.Project;
import com.ssafy.moamoa.domain.User;
import com.ssafy.moamoa.dto.ApplyForm;
import com.ssafy.moamoa.dto.MatchingForm;
import com.ssafy.moamoa.repository.ApplyRepository;
import com.ssafy.moamoa.repository.ProjectRepository;
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

	// 지원 보내기
	public void sendApply(Long userId, Long projectId) {

		User user = userService.findUser(userId);
		Project project = projectService.findProjectById(projectId);
		project.setCountOffer(project.getCountOffer() + 1);
		Apply apply = Apply.builder()
			.user(user)
			.project(project)
			.time(LocalDateTime.now())
			.build();
		applyRepository.save(apply);
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

	public void deleteSendApply(MatchingForm matchingForm) {
		// cnt_apply--
		Project project = projectRepository.findById(matchingForm.getProjectId()).get();
		project.setCountOffer(project.getCountOffer() - 1);
		applyRepository.deleteById(matchingForm.getApplyId());
	}

	public void deleteReceiveApply(Long applyId) {
		applyRepository.deleteById(applyId);
	}
}