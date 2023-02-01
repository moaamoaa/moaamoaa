package com.ssafy.moamoa.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ssafy.moamoa.domain.TeamRole;
import com.ssafy.moamoa.domain.dto.ProjectDto;
import com.ssafy.moamoa.domain.dto.SearchCondition;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.Team;
import com.ssafy.moamoa.domain.entity.TechStack;
import com.ssafy.moamoa.domain.entity.User;
import com.ssafy.moamoa.repository.ProfileRepository;
import com.ssafy.moamoa.repository.ProjectRepository;
import com.ssafy.moamoa.repository.ProjectTechStackRepository;
import com.ssafy.moamoa.repository.TeamRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchService {

	private final ProjectRepository projectRepository;
	private final ProjectTechStackRepository projectTechStackRepository;
	private final TeamRepository teamRepository;
	private final ProfileRepository profileRepository;

	public List<ProjectDto> searchProject(SearchCondition condition) {
		List<ProjectDto> searchResultList = projectRepository.search(condition);

		for (ProjectDto result : searchResultList) {
			//해당 기술 스택 가져오기
			setTechStacks(result);

			//팀 리더 가져오기
			setLeaderNickname(result);
		}
		return searchResultList;
	}

	public ProjectDto setTechStacks(ProjectDto result) {
		List<TechStack> findTechStacks = projectTechStackRepository.findTop4ByProject_Id(result.getId())
			.stream()
			.map(t -> t.getTechStack())
			.collect(Collectors.toList());
		if (!findTechStacks.isEmpty()) {
			result.setTechStacks(findTechStacks);
		}
		return result;
	}

	public ProjectDto setLeaderNickname(ProjectDto result) {
		Optional<Team> findTeam = teamRepository.findByRoleAndProject_Id(TeamRole.LEADER, result.getId());
		if (findTeam.isPresent()) {
			User leader = findTeam.get().getUser();
			Optional<Profile> findProfile = profileRepository.findByUser_Id(leader.getId());
			findProfile.ifPresent(profile -> result.setLeaderName(profile.getNickname()));
		}
		return result;
	}
}
