package com.ssafy.moamoa.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.Apply;
import com.ssafy.moamoa.domain.Project;
import com.ssafy.moamoa.domain.User;
import com.ssafy.moamoa.dto.MatchingForm;
import com.ssafy.moamoa.repository.ApplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApplyService {

	private final UserService userService;
	private final ProjectService projectService;
	private final ApplyRepository applyRepository;

	// 지원 보내기
	public void sendApply(MatchingForm matchingForm) {

		User user = userService.findUser(matchingForm.getUserid());
		Project project = projectService.findProjectById(matchingForm.getProjectid());

		Apply apply = Apply.builder()
			.user(user)
			.project(project)
			.time(LocalDateTime.now())
			.build();
		applyRepository.save(apply);
	}

	public List<Apply> showApply(Long userId) {

		User user = userService.findUser(userId);
		List<Apply> applies = applyRepository.findByUser(user);
		return applies;
	}
}