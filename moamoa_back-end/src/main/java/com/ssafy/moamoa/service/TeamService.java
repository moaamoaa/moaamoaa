package com.ssafy.moamoa.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
public class TeamService {

	private final ProjectRepository projectRepository;
	private final TeamRepository teamRepository;
	private final UserRepository userRepository;

	// 팀장 확인
	public boolean checkLeader(Long userId, Long projectId) {
		// 팀장인지 확인
		Project project = projectRepository.findById(projectId).get();
		User user = userRepository.findById(userId).get();
		//List<Team> teams = teamRepository.findByProject(project);

		TeamRole teamRole = TeamRole.LEADER;
		if(teamRepository.findByUser(user, project).isPresent()) {
			if (teamRepository.findByUser(user, project).get().getRole() == teamRole) {
				return true;
			}
		}
		return false;
	}
}