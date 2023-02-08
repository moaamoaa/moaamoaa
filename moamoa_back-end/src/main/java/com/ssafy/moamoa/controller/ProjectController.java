package com.ssafy.moamoa.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.moamoa.domain.dto.ProjectDetail;
import com.ssafy.moamoa.domain.dto.ProjectForm;
import com.ssafy.moamoa.service.ProjectService;
import com.ssafy.moamoa.service.TeamService;

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
	private final TeamService teamService;

	@ApiOperation(value = "자기가 속한 프로젝트/스터디 조회",
		notes = "자기가 속한 프로젝트/스터디를 조회한다.")
	@GetMapping
	public ResponseEntity<?> showProjects(Authentication authentication) throws Exception {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		List<ProjectForm> projectForms = projectService.findByUser(Long.valueOf(userDetails.getUsername()));
		return new ResponseEntity<List<ProjectForm>>(projectForms, HttpStatus.OK);
	}

	@ApiOperation(value = "팀 페이지 open",
		notes = "팀 페이지 open")
	@GetMapping("/detail")
	public ResponseEntity<?> accessProject(@RequestParam("projectId") Long projectId, Authentication authentication) throws Exception {
		ProjectDetail projectDetail = projectService.accessProject(projectId);

		// 로그인 한 상태
		if(authentication != null)
		{
			UserDetails userDetails = (UserDetails)authentication.getPrincipal();
			boolean isLeader = projectService.setIsLeader(Long.valueOf(userDetails.getUsername()), projectDetail.getProjectId());
			projectDetail.setLeader(isLeader);
		}

		return new ResponseEntity<ProjectDetail>(projectDetail, HttpStatus.OK);
	}

	@ApiOperation(value = "프로젝트/스터디 등록",
		notes = "프로젝트/스터디 등록을 한다.")
	@PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
		MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> createProject(@RequestPart(value = "projectForm") ProjectForm projectForm, @RequestPart(value = "file") MultipartFile file, Authentication authentication) throws Exception {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		projectForm.setUserId(Long.valueOf(userDetails.getUsername()));
		projectService.creatProject(projectForm,file);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "프로젝트/스터디 수정",
		notes = "프로젝트/스터디 수정을 한다.")
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
		MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> updateProject(@RequestPart(value = "projectForm") ProjectForm projectForm, @RequestPart(value = "file") MultipartFile file,  Authentication authentication) throws
		Exception {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		if(!teamService.checkLeader(Long.valueOf(userDetails.getUsername()), projectForm.getProjectId()))
		{
			throw new Exception("팀장이 아닙니다.");
		}
		projectService.updateProject(Long.valueOf(userDetails.getUsername()), projectForm,file);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "프로젝트/스터디 삭제",
		notes = "프로젝트/스터디 삭제를 한다.")
	@DeleteMapping
	public ResponseEntity<?> deleteProject(@RequestBody ProjectForm projectForm, Authentication authentication) throws
		Exception {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		if(!teamService.checkLeader(Long.valueOf(userDetails.getUsername()), projectForm.getProjectId()))
		{
			throw new Exception("팀장이 아닙니다.");
		}
		projectService.deleteProject(Long.valueOf(userDetails.getUsername()));
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "팀원 강퇴",
		notes = "팀장이 팀원을 강퇴한다.")
	@DeleteMapping("/member")
	public ResponseEntity<?> deleteMember(@RequestBody ProjectForm projectForm, Authentication authentication) throws
		Exception {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		if(!teamService.checkLeader(Long.valueOf(userDetails.getUsername()), projectForm.getProjectId()))
		{
			throw new Exception("팀장이 아닙니다.");
		}
		projectService.deleteMember(projectForm);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
