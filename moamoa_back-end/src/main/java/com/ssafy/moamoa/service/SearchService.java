package com.ssafy.moamoa.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.dto.FilterDto;
import com.ssafy.moamoa.domain.dto.ProfileResultDto;
import com.ssafy.moamoa.domain.dto.ProjectResultDto;
import com.ssafy.moamoa.domain.dto.SearchCondition;
import com.ssafy.moamoa.domain.dto.TechStackCategoryDto;
import com.ssafy.moamoa.domain.entity.Area;
import com.ssafy.moamoa.repository.AreaRepository;
import com.ssafy.moamoa.repository.ProfileRepository;
import com.ssafy.moamoa.repository.ProjectRepository;
import com.ssafy.moamoa.repository.TechStackCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchService {

	private final ProjectRepository projectRepository;
	private final ProfileRepository profileRepository;
	private final AreaRepository areaRepository;
	private final TechStackCategoryRepository techStackCategoryRepository;
	private final CardService cardService;

	//프로젝트 검색
	public List<ProjectResultDto> searchProject(SearchCondition condition, Long cursorId, Pageable pageable) {
		List<ProjectResultDto> searchResultList = projectRepository.search(condition, cursorId, pageable);

		for (ProjectResultDto result : searchResultList) {
			//해당 기술 스택 가져오기
			cardService.setTechStacks(result);
			//팀 리더 가져오기
			cardService.setLeaderNickname(result);
		}
		return searchResultList;
	}

	//프로필 검색
	public List<ProfileResultDto> searchProfile(SearchCondition condition, Long cursorId, Pageable pageable) {
		List<ProfileResultDto> searchResultList = profileRepository.search(condition, cursorId, pageable);
		for (ProfileResultDto result : searchResultList) {
			//해당 기술 스택 가져오기
			cardService.setTechStacks(result);
			//지역 리스트
			cardService.setArea(result);

		}
		return searchResultList;
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
