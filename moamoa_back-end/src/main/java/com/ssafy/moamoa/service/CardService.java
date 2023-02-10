package com.ssafy.moamoa.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.ProfileOnOffStatus;
import com.ssafy.moamoa.domain.TeamRole;
import com.ssafy.moamoa.domain.dto.ProfileResultDto;
import com.ssafy.moamoa.domain.dto.ProjectResultDto;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.Team;
import com.ssafy.moamoa.domain.entity.TechStack;
import com.ssafy.moamoa.domain.entity.User;
import com.ssafy.moamoa.repository.ProfileRepository;
import com.ssafy.moamoa.repository.ProfileTechStackRepository;
import com.ssafy.moamoa.repository.ProjectTechStackRepository;
import com.ssafy.moamoa.repository.TeamRepository;
import com.ssafy.moamoa.repository.UserAreaRepository;
import com.ssafy.moamoa.repository.projection.TechStackOnly;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CardService {

	private final ProjectTechStackRepository projectTechStackRepository;
	private final ProfileTechStackRepository profileTechStackRepository;
	private final TeamRepository teamRepository;
	private final ProfileRepository profileRepository;
	private final UserAreaRepository userAreaRepository;

	public ProjectResultDto setTechStacks(ProjectResultDto result) {
		List<TechStack> findTechStacks = projectTechStackRepository.findTop4ByProject_IdOrderByOrderAsc(result.getId())
			.stream()
			.map(TechStackOnly::getTechStack)
			.collect(Collectors.toList());
		if (!findTechStacks.isEmpty()) {
			result.setTechStacks(findTechStacks);
		}
		return result;
	}

	public ProfileResultDto setTechStacks(ProfileResultDto result) {
		List<TechStack> findTechStacks = profileTechStackRepository.findTop4ByProfile_IdOrderByOrderAsc(result.getId())
			.stream()
			.map(TechStackOnly::getTechStack)
			.collect(Collectors.toList());
		if (!findTechStacks.isEmpty()) {
			result.setTechStacks(findTechStacks);
		}
		return result;
	}

	public ProjectResultDto setLeaderNickname(ProjectResultDto result) {
		Optional<Team> findTeam = teamRepository.findByRoleAndProject_Id(TeamRole.LEADER, result.getId());
		if (findTeam.isPresent()) {
			User leader = findTeam.get().getUser();
			Optional<Profile> findProfile = profileRepository.findByUser_Id(leader.getId());
			findProfile.ifPresent(profile -> result.setLeaderName(profile.getNickname()));
		}
		return result;
	}

	public ProfileResultDto setArea(ProfileResultDto result) {
		if (isPreferOnline(result.getStatus())) {
			result.setArea(List.of("온라인"));
			return result;
		}

		List<String> areas = userAreaRepository.findByProfile_Id(result.getId())
			.stream()
			.map(p -> p.getArea().getName())
			.collect(Collectors.toList());
		if (!areas.isEmpty()) {
			result.setArea(areas);
		}
		return result;
	}

	public boolean isPreferOnline(ProfileOnOffStatus status) {
		return status.equals(ProfileOnOffStatus.ONLINE);

	}

}
