package com.ssafy.moamoa.controller;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.constraints.Email;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ssafy.moamoa.domain.Profile;
import com.ssafy.moamoa.domain.User;
import com.ssafy.moamoa.dto.SignUpForm;
import com.ssafy.moamoa.service.ProfileService;
import com.ssafy.moamoa.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
@Transactional
public class UserController {

	private final UserService userService;
	private final ProfileService profileService;
	@GetMapping
	public ResponseEntity<?> showList() throws Exception {
		List<User> users = userService.findUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignUpForm signUpForm) throws JsonProcessingException {

		String email = signUpForm.getEmail();
		String password = signUpForm.getPassword();
		String nickname = signUpForm.getNickname();

		String userNickname = userService.signup(email, password, nickname);

		return new ResponseEntity<String>(userNickname, HttpStatus.OK);
	}
}
