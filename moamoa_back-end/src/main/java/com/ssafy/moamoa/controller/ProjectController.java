package com.ssafy.moamoa.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.moamoa.domain.dto.ProjectForm;
import com.ssafy.moamoa.domain.entity.Project;
import com.ssafy.moamoa.service.ProjectService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/projects")
@Transactional
public class ProjectController {
	private final ProjectService projectService;

	@ApiOperation(value = "자기가 속한 프로젝트/스터디 조회",
		notes = "자기가 속한 프로젝트/스터디를 조회한다.")
	@GetMapping("/{id}")
	public ResponseEntity<?> showProjects(@PathVariable Long id) throws Exception {
		List<Project> projects = projectService.findByUser(id);
		return new ResponseEntity<List<Project>>(projects, HttpStatus.OK);
	}

	@ApiOperation(value = "팀 페이지 open",
		notes = "팀 페이지 open")
	@GetMapping("/Leader/{userId}")
	public ResponseEntity<?> accessProject(@PathVariable Long userId, @RequestParam("projectId") Long projectId) throws Exception {
		ProjectForm projectForm = projectService.accessProject(userId, projectId);

		return new ResponseEntity<ProjectForm>(projectForm, HttpStatus.OK);
	}

	@ApiOperation(value = "프로젝트/스터디 등록",
		notes = "프로젝트/스터디 등록을 한다.")
	@PostMapping
	public ResponseEntity<?> createProject(@RequestBody ProjectForm projectForm) throws Exception {

		projectService.creatProject(projectForm);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "프로젝트/스터디 수정",
		notes = "프로젝트/스터디 수정을 한다.")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProject(@PathVariable Long id, @RequestBody ProjectForm projectForm) throws
		Exception {

		projectService.updateProject(id, projectForm);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "프로젝트/스터디 삭제",
		notes = "프로젝트/스터디 삭제를 한다.")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProject(@PathVariable Long id) {
		projectService.deleteProject(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
