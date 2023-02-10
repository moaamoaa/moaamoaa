package com.ssafy.moamoa.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.moamoa.config.security.CookieUtil;
import com.ssafy.moamoa.config.security.JwtTokenProvider;
import com.ssafy.moamoa.domain.dto.LoginForm;
import com.ssafy.moamoa.domain.dto.SignForm;
import com.ssafy.moamoa.domain.dto.TokenDto;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.User;
import com.ssafy.moamoa.exception.customException.UnAuthorizedException;
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
	private final JwtTokenProvider jwtTokenProvider;
	private final CookieUtil cookieUtil;

	@ApiOperation(value = "전체 사용자 정보 조회", notes = "전체 사용자의 정보를 조회한다.")
	@GetMapping
	public ResponseEntity<?> showList() throws Exception {
		List<User> users = userService.findUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@ApiOperation(value = "회원 가입", notes = "email, password, nickname 정보로 회원 가입을 한다.")
	// 회원 가입
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody @Valid SignForm signForm) throws JsonProcessingException {

		String email = signForm.getEmail();
		String password = signForm.getPassword();
		String nickname = signForm.getNickname();

		String userNickname = userService.signup(email, password, nickname);

		return new ResponseEntity<String>(userNickname, HttpStatus.OK);
	}

	@ApiOperation(value = "회원 가입 시 메일 유효성 확인", notes = "email의 중복 검사와 유효성 검사를 한다.")
	// 회원 가입 시 메일 유효성 확인
	@GetMapping("/email")
	public ResponseEntity<?> checkEmail(@RequestParam("email") String email) throws MessagingException {
		User user = User.builder().email(email).build();
		userService.validateDuplicateUserEmail(user);
		String code = mailService.joinEmail(email);

		return new ResponseEntity<String>(code, HttpStatus.OK);
	}

	@ApiOperation(value = "회원 가입 시 닉네임 중복 확인", notes = "nickname의 중복 검사를 한다.")
	// 닉네임 중복 확인
	@GetMapping("/nickname")
	public ResponseEntity<?> checkNickname(@RequestParam("nickname") String nickname) throws JsonProcessingException {
		Profile profile = Profile.builder().nickname(nickname).build();
		userService.validateDuplicateProfileNickname(profile);
		return new ResponseEntity<String>("닉네임 중복 검증 성공", HttpStatus.OK);
	}

	@ApiOperation(value = "비밀번호 변경", notes = "id에 맞는 회원의 password를 수정한다.")
	// 비밀번호 변경
	@PostMapping("/password")
	public ResponseEntity<?> updatePassword(@Valid @RequestBody SignForm signForm, Authentication authentication) {
		// 받은 비밀번호로 update
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		userService.updatePassword(signForm.getPassword(), Long.valueOf(userDetails.getUsername()));
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "임시 비밀번호 발급", notes = "email에 맞는 회원의 비밀번호를 임시 비밀번호로 수정하고 메일을 전송한다.")
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

	@ApiOperation(value = "회원 삭제", notes = "email에 맞는 회원을 삭제한다.")
	// 회원 삭제
	@DeleteMapping
	public ResponseEntity<?> deleteUser(Authentication authentication) {
		// 받은 이메일로 delete
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		userService.deleteUser(Long.valueOf(userDetails.getUsername()));
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "로그인", notes = "email, password 정보로 로그인을 한다.")
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginForm loginForm, HttpServletResponse response) {
		TokenDto tokenDto = userService.authenticateUser(loginForm.getEmail(), loginForm.getPassword());

		Cookie cookie = cookieUtil.createCookie("REFRESH_TOKEN", tokenDto.getRefreshToken());
		response.addCookie(cookie);

		return new ResponseEntity<>(tokenDto, HttpStatus.OK);
	}

	@ApiOperation(value = "로그아웃")
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletRequest request) {
		String accessToken = jwtTokenProvider.resolveToken(request);
		String userEmail = jwtTokenProvider.getUserEmail(accessToken);
		userService.deleteRefreshToken(userEmail);
		userService.setBlackList(accessToken);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "access token 재발급", notes = "access token, refresh token 정보로 access token 재발급한다.")
	@PostMapping("/reissue")
	public ResponseEntity<?> reissue(HttpServletRequest request) {
		String accessToken = jwtTokenProvider.resolveToken(request);
		String refreshToken = cookieUtil.getCookie(request, "REFRESH_TOKEN").getValue();
		TokenDto reissueToken = userService.reissueAccessToken(accessToken, refreshToken);

		if (reissueToken == null) {
			throw new UnAuthorizedException("토큰 정보를 인증할 수 없습니다.");
		}

		return new ResponseEntity<>(reissueToken, HttpStatus.CREATED);
	}

}
