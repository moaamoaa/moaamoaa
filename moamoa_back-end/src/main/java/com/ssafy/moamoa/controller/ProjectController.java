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

import com.ssafy.moamoa.domain.Project;
import com.ssafy.moamoa.dto.ProjectForm;
import com.ssafy.moamoa.service.ProjectService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@Transactional
public class ProjectController {
	private final ProjectService projectService;

	@ApiOperation(value = "전체 프로젝트 조회",
		notes = "전체 프로젝트를 조회한다.")
	@GetMapping("projects")
	public ResponseEntity<?> showProjects() throws Exception {
		List<Project> projects = projectService.findProjects();
		return new ResponseEntity<List<Project>>(projects, HttpStatus.OK);
	}

	@ApiOperation(value = "전체 스터디 조회",
		notes = "전체 스터디를 조회한다.")
	@GetMapping("studies")
	public ResponseEntity<?> showStudies() throws Exception {
		List<Project> projects = projectService.findStudies();
		return new ResponseEntity<List<Project>>(projects, HttpStatus.OK);
	}

	@ApiOperation(value = "프로젝트/스터디 등록",
		notes = "프로젝트/스터디 등록을 한다.")
	@PostMapping("/projects")
	public ResponseEntity<?> createProject(@RequestBody ProjectForm projectForm) throws Exception {

		projectService.creatProject(projectForm);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "프로젝트/스터디 수정",
		notes = "프로젝트/스터디 수정을 한다.")
	@PutMapping("/projects/{id}")
	public ResponseEntity<?> updateProject(@PathVariable Long id, @RequestBody ProjectForm projectForm) throws
		Exception {

		projectService.updateProject(id, projectForm);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "프로젝트/스터디 삭제",
		notes = "프로젝트/스터디 삭제를 한다.")
	@DeleteMapping("/projects/{id}")
	public ResponseEntity<?> deleteProject(@PathVariable Long id) {
		projectService.deleteProject(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
