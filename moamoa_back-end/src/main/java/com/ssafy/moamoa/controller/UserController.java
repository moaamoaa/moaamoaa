package com.ssafy.moamoa.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.moamoa.domain.Profile;
import com.ssafy.moamoa.domain.User;
import com.ssafy.moamoa.dto.LoginForm;
import com.ssafy.moamoa.dto.SignForm;
import com.ssafy.moamoa.dto.TokenDto;
import com.ssafy.moamoa.service.MailService;
import com.ssafy.moamoa.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
@Transactional
public class UserController {
	private final UserService userService;
	private final MailService mailService;

	@ApiOperation(value = "전체 사용자 정보 조회",
		notes = "전체 사용자의 정보를 조회한다.")
	@GetMapping
	public ResponseEntity<?> showList() throws Exception {
		List<User> users = userService.findUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@ApiOperation(value = "회원 가입",
		notes = "email, password, nickname 정보로 회원 가입을 한다.")
	// 회원 가입
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignForm signForm) throws JsonProcessingException {

		String email = signForm.getEmail();
		String password = signForm.getPassword();
		String nickname = signForm.getNickname();

		String userNickname = userService.signup(email, password, nickname);

		return new ResponseEntity<String>(userNickname, HttpStatus.OK);
	}

	@ApiOperation(value = "회원 가입 시 메일 유효성 확인",
		notes = "email의 중복 검사와 유효성 검사를 한다.")
	// 회원 가입 시 메일 유효성 확인
	@GetMapping("/email")
	public ResponseEntity<?> checkEmail(@RequestBody SignForm signForm) throws MessagingException {
		User user = User.builder()
			.email(signForm.getEmail())
			.build();
		userService.validateDuplicateUserEmail(user);
		String code = mailService.joinEmail(signForm.getEmail());

		return new ResponseEntity<String>(code, HttpStatus.OK);
	}

	@ApiOperation(value = "회원 가입 시 닉네임 중복 확인",
		notes = "nickname의 중복 검사를 한다.")
	// 닉네임 중복 확인
	@GetMapping("/nickname")
	public ResponseEntity<?> checkNickname(@RequestParam("nickname") String nickname) throws JsonProcessingException {
		Profile profile = Profile.builder()
			.nickname(nickname)
			.build();
		userService.validateDuplicateProfileNickname(profile);
		return new ResponseEntity<String>("닉네임 중복 검증 성공", HttpStatus.OK);
	}

	@ApiOperation(value = "비밀번호 변경",
		notes = "id에 맞는 회원의 password를 수정한다.")
	// 비밀번호 변경
	@PostMapping("/password/{id}")
	public ResponseEntity<?> updatePassword(@PathVariable Long id, @RequestBody SignForm signForm) {
		// 받은 비밀번호로 update
		userService.updatePassword(signForm.getPassword(), id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "임시 비밀번호 발급",
		notes = "email에 맞는 회원의 비밀번호를 임시 비밀번호로 수정하고 메일을 전송한다.")
	// 임시 비밀번호 발급
	@PutMapping("/email")
	public ResponseEntity<?> lostPassword(@RequestBody SignForm signForm) throws MessagingException {
		// mailService에서 임시 비밀번호 생성
		// 메일 전송
		// 해당 string으로 update
		String newPassword = mailService.tempPassword(signForm.getEmail());
		userService.updatePasswordByEmail(newPassword, signForm.getEmail());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "닉네임 변경",
		notes = "email에 맞는 회원의 nickname을 중복 검사 후 수정한다.")
	// 닉네임 변경
	@PostMapping("/nickname")
	public ResponseEntity<?> updateNickname(@RequestBody SignForm signForm) {
		// 받은 닉네임으로 update
		userService.updateNickname(signForm.getNickname(), signForm.getEmail());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "회원 삭제",
		notes = "email에 맞는 회원을 삭제한다.")
	// 회원 삭제
	@DeleteMapping()
	public ResponseEntity<?> deleteUser(@RequestBody SignForm signForm) {
		// 받은 이메일로 delete
		userService.deleteUser(signForm.getEmail());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "로그인",
		notes = "email, password 정보로 로긍인을 한다.")
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

}
