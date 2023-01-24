package com.ssafy.moamoa.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.config.security.JwtTokenProvider;
import com.ssafy.moamoa.domain.Profile;
import com.ssafy.moamoa.domain.ProfileSearchStatus;
import com.ssafy.moamoa.domain.User;
import com.ssafy.moamoa.dto.TokenDto;
import com.ssafy.moamoa.exception.DuplicateProfileNicknameException;
import com.ssafy.moamoa.exception.DuplicateUserEmailException;
import com.ssafy.moamoa.exception.NotFoundUserException;
import com.ssafy.moamoa.repository.ProfileRepository;
import com.ssafy.moamoa.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final ProfileRepository profileRepository;
	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;

	// 회원 한명 조회
	public Optional<User> findUser(Long userId) {
		return userRepository.findById(userId);
	}

	// 회원 전체 조회
	public List<User> findUsers() {
		return userRepository.findAll();
	}

	// 이메일 중복 조회
	public void validateDuplicateUserEmail(User user) {
		Optional<User> findUser = userRepository.findByEmail(user.getEmail());
		if (!findUser.isEmpty()) {
			throw new DuplicateUserEmailException("이미 존재하는 회원입니다.");
		}
	}

	// 닉네임 중복 조회
	public void validateDuplicateProfileNickname(Profile profile) {
		Optional<Profile> findProfiles = profileRepository.findByNickname(profile.getNickname());
		if (!findProfiles.isEmpty()) {
			throw new DuplicateProfileNicknameException("이미 존재하는 닉네임입니다.");
		}
	}

	// 회원 가입
	public String signup(String email, String password, String nickname) {
		// user
		User user = User.builder()
			.email(email)
			.password(password)
			.joinDate(LocalDate.now())
			.build();
		// profile
		Profile profile = Profile.builder()
			.nickname(nickname)
			.searchState(ProfileSearchStatus.ALL)
			.build();

		user.setProfile(profile);

		validateDuplicateUserEmail(user);
		validateDuplicateProfileNickname(profile);

		userRepository.save(user);
		profileRepository.save(profile);
		return nickname;
	}

	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}

	public TokenDto authenticateUser(String username, String password) {
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);
			authenticationManager.authenticate(authenticationToken);

			//검증 완료
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			return new TokenDto(getAccessToken(username), getRefreshToken());

		} catch (AuthenticationException e) {
			//검증 실패
			//AuthenticationEntryPoint 실행
			e.printStackTrace();
			return null;
		} catch (AccessDeniedException e) {
			//AccessDeniedHandler
			e.printStackTrace();
			return null;
		}
	}

	public String getAccessToken(String username) {
		User user = userRepository.findByEmail(username).get();
		Profile profile = profileRepository.findByUser_Id(user.getId()).get();
		return jwtTokenProvider.createAccessToken(username, profile.getNickname());
	}

	public String getRefreshToken() {
		return jwtTokenProvider.createRefreshToken();
	}

	public void updatePassword(String password, Long id) {
		Optional<User> findUsers = userRepository.findById(id);
		if (!findUsers.isPresent()) {
			throw new NotFoundUserException("해당 id의 유저가 없습니다.");
		}
		User findUser = findUsers.get();
		findUser.setPassword(password);
	}

	public void updatePasswordByEmail(String password, String email) {
		Optional<User> findUsers = userRepository.findByEmail(email);
		if (!findUsers.isPresent()) {
			return;
		}
		User findUser = findUsers.get();
		findUser.setPassword(password);
	}

	public void updateNickname(String nickname, String email) {
		Optional<User> findUsers = userRepository.findByEmail(email);
		if (!findUsers.isPresent()) {
			return;
		}
		User findUser = findUsers.get();
		// profile
		Profile profile = Profile.builder()
			.nickname(nickname)
			.searchState(ProfileSearchStatus.ALL)
			.build();

		validateDuplicateProfileNickname(profile);
		Optional<Profile> findProfiles = profileRepository.findByUser(findUser);
		if (!findProfiles.isPresent()) {
			return;
		}
		Profile findProfile = findProfiles.get();
		findProfile.setNickname(nickname);
	}

	public void deleteUser(String email) {
		Optional<User> findUsers = userRepository.findByEmail(email);
		if (!findUsers.isPresent()) {
			return;
		}
		User findUser = findUsers.get();
		// profile
		Optional<Profile> findProfiles = profileRepository.findByUser(findUser);
		if (!findProfiles.isPresent()) {
			return;
		}
		Profile findProfile = findProfiles.get();
		profileRepository.delete(findProfile);
		userRepository.deleteByEmail(email);
	}
}