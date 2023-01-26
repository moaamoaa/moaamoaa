package com.ssafy.moamoa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.Area;
import com.ssafy.moamoa.domain.Project;
import com.ssafy.moamoa.domain.ProjectArea;
import com.ssafy.moamoa.domain.User;
import com.ssafy.moamoa.domain.UserArea;
import com.ssafy.moamoa.repository.AreaRepository;
import com.ssafy.moamoa.repository.ProjectAreaRepository;
import com.ssafy.moamoa.repository.UserAreaRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AreaService {

	private final AreaRepository areaRepository;
	private final UserAreaRepository userAreaRepository;
	private final ProjectAreaRepository projectAreaRepository;

	// 매칭되는 지역 찾기 -> list 형식으로 return
	public ProjectArea findProjectAreaList(Project project) {
		Optional<ProjectArea> findProjectArea = projectAreaRepository.findByProject(project);
		ProjectArea projectArea = findProjectArea.get();
		return projectArea;
	}

	public List<UserArea> findUserAreaList(User user) {
		List<UserArea> userAreas = userAreaRepository.findByUser(user);
		return userAreas;
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

	public void addUserAreaList(User user, Long[] areas) {
		for (int i = 0; i < areas.length; i++) {
			Optional<Area> findarea = areaRepository.findById(areas[i]);
			Area area = findarea.get();
			UserArea userArea = UserArea.builder()
				.user(user)
				.area(area)
				.build();
			userAreaRepository.save(userArea);
		}
	}

	// 지역 삭제
	public void deleteProjectAreaList(ProjectArea projectArea) {
		projectAreaRepository.delete(projectArea);
	}

	public void deleteUserAreaList(List<UserArea> userAreas) {
		for (UserArea ua : userAreas) {
			userAreaRepository.delete(ua);
		}
	}
}