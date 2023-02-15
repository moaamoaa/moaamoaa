package com.ssafy.moamoa.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.config.security.JwtTokenProvider;
import com.ssafy.moamoa.domain.ProfileOnOffStatus;
import com.ssafy.moamoa.domain.ProfileSearchStatus;
import com.ssafy.moamoa.domain.dto.TokenDto;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.User;
import com.ssafy.moamoa.exception.customException.DuplicateUserException;
import com.ssafy.moamoa.exception.customException.UnAuthorizedException;
import com.ssafy.moamoa.repository.ProfileRepository;
import com.ssafy.moamoa.repository.UserRepository;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final ProfileRepository profileRepository;
	private final AuthenticationManager authenticationManager;
	private final RedisTemplate<String, String> redisTemplate;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;

	private final ImageService imageService;

	// 회원 한명 조회
	public User findUser(Long userId) {
		return userRepository.findById(userId).get();
	}

	// 이메일 중복 조회
	public void validateDuplicateUserEmail(User user) {
		Optional<User> findUser = userRepository.findByEmail(user.getEmail());
		if (!findUser.isEmpty()) {
			throw new DuplicateUserException("이미 존재하는 회원입니다.");
		}
	}

	// 닉네임 중복 조회
	public void validateDuplicateProfileNickname(Profile profile) {
		Optional<Profile> findProfiles = profileRepository.findByNickname(profile.getNickname());
		if (!findProfiles.isEmpty()) {
			throw new DuplicateUserException("이미 존재하는 닉네임입니다.");
		}
	}

	// 회원 가입
	public String signup(String email, String password, String nickname) {
		// user
		User user = User.builder()
			.email(email)
			.password(getEncodedPassword(password))
			.joinDate(LocalDate.now())
			.build();
		// profile
		Profile profile = Profile.builder()
			.nickname(nickname)
			.searchState(ProfileSearchStatus.ALL)
			.profileOnOffStatus(ProfileOnOffStatus.ONLINE)
			.img(imageService.getRandomDefaultProfileImage())
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

	public TokenDto authenticateUser(String email, String password) {
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,
				password);
			authenticationManager.authenticate(authenticationToken);

			//검증 완료
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			User user = userRepository.findByEmail(email).get();

			String accessToken = issueAccessToken(user);
			String refreshToken = issueRefreshToken(user);
			Long id = user.getId();

			return new TokenDto(accessToken, refreshToken, id);

		} catch (AuthenticationException e) {
			//검증 실패
			//AuthenticationEntryPoint 실행
			throw new UnAuthorizedException("로그인에 실패하였습니다.");
		} catch (AccessDeniedException e) {
			//AccessDeniedHandler
			return null;
		}
	}

	public String issueAccessToken(User user) {
		Profile profile = profileRepository.findByUser_Id(user.getId()).get();
		return jwtTokenProvider.createAccessToken(user.getEmail(), profile.getNickname());
	}

	public String issueRefreshToken(User user) {
		String refreshToken = jwtTokenProvider.createRefreshToken();
		user.saveRefreshToken(refreshToken);
		return refreshToken;
	}

	public void updatePassword(String password, Long id) {
		Optional<User> findUsers = userRepository.findById(id);
		if (!findUsers.isPresent()) {
			throw new EntityNotFoundException("해당 id의 유저가 없습니다.");
		}
		User findUser = findUsers.get();
		findUser.setPassword(getEncodedPassword(password));
	}

	public void updatePasswordByEmail(String password, String email) {
		Optional<User> findUsers = userRepository.findByEmail(email);
		if (!findUsers.isPresent()) {
			throw new EntityNotFoundException("email에 정보가 맞지않습니다.");
		}
		User findUser = findUsers.get();
		findUser.setPassword(getEncodedPassword(password));
	}

	public void deleteUser(Long id) {
		Optional<User> findUsers = userRepository.findById(id);
		if (!findUsers.isPresent()) {
			throw new EntityNotFoundException("해당 id의 유저가 없습니다.");
		}
		User findUser = findUsers.get();
		findUser.setLocked(true);
	}

	public void setBlackList(String token) {
		Long expiration = jwtTokenProvider.getExpiration(token);
		redisTemplate.opsForValue()
			.set(token, "logout",
				expiration, TimeUnit.MILLISECONDS);

	}

	public void deleteRefreshToken(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		user.ifPresent(User::deleteRefreshToken);
	}

	public TokenDto reissueAccessToken(String accessToken, String refreshToken) {
		try {
			String userEmail = jwtTokenProvider.getUserEmail(accessToken);

		} catch (ExpiredJwtException e) {
			String userEmail = e.getClaims().get("email").toString();
			Optional<User> findUser = userRepository.findByEmail(userEmail);

			//accessToken & refreshToken 인증X
			if (!findUser.isPresent() || refreshToken == null || !jwtTokenProvider.validateToken(refreshToken)
				|| !refreshToken.equals(
				findUser.get().getRefreshToken())) {
				return null;
			}

			String reissueToken = issueAccessToken(findUser.get());
			TokenDto tokenDto = new TokenDto();
			tokenDto.setAccessToken(reissueToken);
			return tokenDto;
		}

		return null;
	}

}