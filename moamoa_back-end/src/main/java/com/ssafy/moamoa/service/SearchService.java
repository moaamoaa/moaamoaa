package com.ssafy.moamoa.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.ProfileOnOffStatus;
import com.ssafy.moamoa.domain.TeamRole;
import com.ssafy.moamoa.domain.dto.FilterDto;
import com.ssafy.moamoa.domain.dto.ProfileResultDto;
import com.ssafy.moamoa.domain.dto.ProjectResultDto;
import com.ssafy.moamoa.domain.dto.SearchCondition;
import com.ssafy.moamoa.domain.dto.TechStackCategoryDto;
import com.ssafy.moamoa.domain.entity.Area;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.Team;
import com.ssafy.moamoa.domain.entity.TechStack;
import com.ssafy.moamoa.domain.entity.User;
import com.ssafy.moamoa.repository.AreaRepository;
import com.ssafy.moamoa.repository.ProfileRepository;
import com.ssafy.moamoa.repository.ProfileTechStackRepository;
import com.ssafy.moamoa.repository.ProjectRepository;
import com.ssafy.moamoa.repository.ProjectTechStackRepository;
import com.ssafy.moamoa.repository.TeamRepository;
import com.ssafy.moamoa.repository.TechStackCategoryRepository;
import com.ssafy.moamoa.repository.UserAreaRepository;
import com.ssafy.moamoa.repository.projection.TechStackOnly;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchService {

	private final ProjectRepository projectRepository;
	private final ProfileRepository profileRepository;
	private final TeamRepository teamRepository;
	private final UserAreaRepository userAreaRepository;
	private final ProjectTechStackRepository projectTechStackRepository;
	private final ProfileTechStackRepository profileTechStackRepository;
	private final AreaRepository areaRepository;
	private final TechStackCategoryRepository techStackCategoryRepository;

	//프로젝트 검색
	public List<ProjectResultDto> searchProject(SearchCondition condition) {
		List<ProjectResultDto> searchResultList = projectRepository.search(condition);

		for (ProjectResultDto result : searchResultList) {
			//해당 기술 스택 가져오기
			setTechStacksInProject(result);
			//팀 리더 가져오기
			setLeaderNickname(result);
		}
		return searchResultList;
	}

	public ProjectResultDto setTechStacksInProject(ProjectResultDto result) {
		List<TechStack> findTechStacks = projectTechStackRepository.findTop4ByProject_Id(result.getId())
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

	//프로필 검색
	public List<ProfileResultDto> searchProfile(SearchCondition condition) {
		List<ProfileResultDto> searchResultList = profileRepository.search(condition);
		for (ProfileResultDto result : searchResultList) {
			//해당 기술 스택 가져오기
			setTechStacksInProfile(result);
			//지역 리스트
			setAreaInProfile(result);

		}
		return searchResultList;
	}

	public ProfileResultDto setTechStacksInProfile(ProfileResultDto result) {
		List<TechStack> findTechStacks = profileTechStackRepository.findTop4ByProfile_Id(result.getId())
			.stream()
			.map(TechStackOnly::getTechStack)
			.collect(Collectors.toList());
		if (!findTechStacks.isEmpty()) {
			result.setTechStacks(findTechStacks);
		}
		return result;
	}

	public ProfileResultDto setAreaInProfile(ProfileResultDto result) {
		if (isPreferOnline(result.getStatus())) {
			return null;
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

	public FilterDto getSearchFilter() {
		List<Area> areas = getAreaList();
		List<TechStackCategoryDto> techs = getTechStackWithCategory();
		return new FilterDto(techs, areas);

	}

	public List<Area> getAreaList() {
		return areaRepository.findAll();
	}

	public List<TechStackCategoryDto> getTechStackWithCategory() {
		return techStackCategoryRepository.findTechStackWithCategory();
	}

}
