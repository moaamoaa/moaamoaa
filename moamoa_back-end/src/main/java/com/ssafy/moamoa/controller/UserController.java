package com.ssafy.moamoa.controller;

import java.util.List;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.moamoa.config.security.JwtTokenProvider;
import com.ssafy.moamoa.domain.Profile;
import com.ssafy.moamoa.domain.User;
import com.ssafy.moamoa.dto.LoginForm;
import com.ssafy.moamoa.dto.SignUpForm;
import com.ssafy.moamoa.dto.TokenDto;
import com.ssafy.moamoa.service.ProfileServiceImpl;
import com.ssafy.moamoa.dto.Mail;
import com.ssafy.moamoa.service.MailService;
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
	private final ProfileServiceImpl profileService;
	private final JwtTokenProvider jwtTokenProvider;
	private final MailService mailService;

	@GetMapping
	public ResponseEntity<?> showList() throws Exception {
		List<User> users = userService.findUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}


	// 회원 가입
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignUpForm signUpForm) throws JsonProcessingException {

		String email = signUpForm.getEmail();
		String password = signUpForm.getPassword();
		String nickname = signUpForm.getNickname();

		String userNickname = userService.signup(email, password, nickname);

		return new ResponseEntity<String>(userNickname, HttpStatus.OK);
	}


	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody LoginForm loginForm, HttpServletResponse response) {
		log.debug("입력 들어옴");
		TokenDto tokenDto = userService.authenticateUser(loginForm.getEmail(), loginForm.getPassword());

		System.out.println(tokenDto.toString());
		Cookie cookie = new Cookie("REFRESH_TOKEN", tokenDto.getRefreshToken());
		cookie.setHttpOnly(true);
		// cookie.setSecure(true);
		response.addCookie(cookie);

		return new ResponseEntity<>(tokenDto, HttpStatus.OK);
	}

	@PostMapping("/nickname")
	public ResponseEntity<?> checkNickName(@RequestBody String nickname) throws JsonProcessingException {
		Profile profile = Profile.builder()
			.nickname(nickname)
			.build();
		userService.validateDuplicateProfileNickname(profile);
		return new ResponseEntity<String>("닉네임 중복 확인", HttpStatus.OK);

	}


}
