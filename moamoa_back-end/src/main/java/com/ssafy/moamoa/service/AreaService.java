package com.ssafy.moamoa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ssafy.moamoa.domain.dto.AreaForm;
import com.ssafy.moamoa.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.entity.Area;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.ProfileArea;
import com.ssafy.moamoa.domain.entity.Project;
import com.ssafy.moamoa.domain.entity.ProjectArea;
import com.ssafy.moamoa.domain.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AreaService {

	private final AreaRepository areaRepository;
	private final UserAreaRepository userAreaRepository;
	private final ProjectAreaRepository projectAreaRepository;


	private final ProfileAreaRepository profileAreaRepository;
	private final ProfileRepository profileRepository;


	// 매칭되는 지역 찾기 -> list 형식으로 return
	public ProjectArea findProjectAreaList(Project project) {
		Optional<ProjectArea> findProjectArea = projectAreaRepository.findByProject(project);
		ProjectArea projectArea = findProjectArea.get();
		return projectArea;
	}

	public List<ProfileArea> findUserAreaList(User user) {
		List<ProfileArea> profileAreas = userAreaRepository.findByUser(user);
		return profileAreas;
	}

	// 지역 등록
	public void addProjectAreaList(Project project, Long areaId) {

		Optional<Area> findarea = areaRepository.findById(areaId);
		Area area = findarea.get();
		ProjectArea projectArea = ProjectArea.builder()
			.project(project)
			.area(area)
			.build();
		projectAreaRepository.save(projectArea);

	}

	public void addUserAreaList(Profile profile, Long[] areas) {
		for (int i = 0; i < areas.length; i++) {
			Optional<Area> findArea = areaRepository.findById(areas[i]);
			Area area = findArea.get();
			ProfileArea profileArea = ProfileArea.builder()
				.profile(profile)
				.area(area)
				.build();
			userAreaRepository.save(profileArea);
		}
	}

	// 지역 삭제
	public void deleteProjectAreaList(ProjectArea projectArea) {
		projectAreaRepository.delete(projectArea);
	}

	public void deleteUserAreaList(List<ProfileArea> profileAreas) {
		for (ProfileArea ua : profileAreas) {
			userAreaRepository.delete(ua);
		}
	}

	//////////////////// Profile Area ///////////////

	public List<AreaForm> getProfileAreas(Long profileId)
	{
	List<ProfileArea> profileAreaList = profileAreaRepository.getAreasByIdAsc(profileId);
		List<AreaForm> areaFormList = new ArrayList<>();
	for(ProfileArea profileArea : profileAreaList)
	{
		AreaForm areaForm = AreaForm.builder()
				.name(profileArea.getArea().getName()).build();
		areaFormList.add(areaForm);
	}
	return areaFormList;
	}

	public List<AreaForm> modifyProfileAreas(Long profileId,List<AreaForm> areaFormList)
	{
	// List<ProfileArea> profileAreaList =  profileAreaRepository.getAreasByIdAsc(profileId);
	Long deleteCount =  profileAreaRepository.deleteAreasById(profileId);
		List<AreaForm> returnList = new ArrayList<>();
	for(int i=0;i <areaFormList.size();i++)
	{
		AreaForm areaForm = areaFormList.get(i);
		ProfileArea profileArea = ProfileArea.builder()
				.profile(profileRepository.getProfileById(profileId))
				.area(areaRepository.getAreaById(areaForm.getId()))
				.order(i+1).build();

		AreaForm returnAreaForm = AreaForm.builder()
				.id(areaForm.getId())
				.name(profileArea.getArea().getName())
				.build();

		profileAreaRepository.save(profileArea);
		returnList.add(returnAreaForm);
	}
	return returnList;
	}
 }