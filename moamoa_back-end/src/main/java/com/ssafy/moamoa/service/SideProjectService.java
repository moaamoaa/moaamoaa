package com.ssafy.moamoa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.dto.SidePjtForm;
import com.ssafy.moamoa.domain.dto.TechStackForm;
import com.ssafy.moamoa.domain.entity.SidePjt;
import com.ssafy.moamoa.domain.entity.SidePjtTechStack;
import com.ssafy.moamoa.repository.ProfileRepository;
import com.ssafy.moamoa.repository.SideProjectRepository;
import com.ssafy.moamoa.repository.TechStackRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class SideProjectService {
	private static final String SUCCESS = "SUCCESS";
	private static final String FAIL = "FAIL";

	private final ProfileRepository profileRepository;

	private final SideProjectRepository sideProjectRepository;

	private final TechStackRepository techStackRepository;

	private final TechStackService techStackService;


	// 사이드 프로젝트

	public List<SidePjtForm> getSideProjects(Long profileId) {
		List<SidePjt> sidePjtList = sideProjectRepository.getSideProjects(profileId);
		List<SidePjtForm> sidePjtFormList = new ArrayList<>();
		for (SidePjt sidePjt : sidePjtList) {
			SidePjtForm sidePjtForm = SidePjtForm.builder()
				.name(sidePjt.getName())
				.year(sidePjt.getYear())
				.context(sidePjt.getContext())
				.build();

			sidePjtFormList.add(sidePjtForm);
		}

		return sidePjtFormList;
	}

	public String addSidePjt(Long profileId, SidePjtForm sidePjtForm) {

		SidePjt sidePjt = SidePjt.builder()
			.profile(profileRepository.getProfileById(profileId))
			.name(sidePjtForm.getName())
			.year(sidePjtForm.getYear())
			.context(sidePjtForm.getContext())
			.build();

		sideProjectRepository.save(sidePjt);
		// sidePjt = SideProjectRepository

		// Get SidePjt Id
		SidePjt tempsidePjt = sideProjectRepository.getSideProjectByAll(profileId,sidePjt);

		log.info("Side Hello Project"+tempsidePjt.getId()+tempsidePjt.getName()+tempsidePjt.getContext()+tempsidePjt.getYear());


		// Parsing SideProjectTechStack
		List<TechStackForm> techStackFormList = sidePjtForm.getTechStackFormList();

		techStackService.modifySideProjectTechStack(tempsidePjt.getId(),techStackFormList);

		//

		return SUCCESS;
	}
}
