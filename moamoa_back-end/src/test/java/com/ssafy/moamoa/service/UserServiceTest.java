package com.ssafy.moamoa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.moamoa.repository.ProfileRepository;
import com.ssafy.moamoa.repository.UserRepository;

@SpringBootTest
class UserServiceTest {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProfileRepository profileRepository;

	// @Test
	// public void 로그인성공() {
	// 	//given
	// 	String email = "ssafy@ssafy.com";
	// 	String password = "s@12341234";
	// 	User user = User.builder().id(1L).email(email).password(password).joinDate(LocalDate.now()).build();
	// 	userRepository.save(user);
	// 	Profile profile = Profile.builder()
	// 		.nickname("김싸피")
	// 		.user(user)
	// 		.profileOnOffStatus(ProfileOnOffStatus.ONLINE)
	// 		.searchState(ProfileSearchStatus.ALL)
	// 		.build();
	//
	// 	profileRepository.save(profile);
	//
	// 	//when
	// 	TokenDto tokenDto = userService.authenticateUser(email, password);
	//
	// 	//then
	// 	Assertions.assertThat(tokenDto.getId()).isEqualTo(1L);
	//
	// }
}