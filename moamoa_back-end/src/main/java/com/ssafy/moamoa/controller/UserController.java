package com.ssafy.moamoa.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.moamoa.domain.Profile;
import com.ssafy.moamoa.domain.User;
import com.ssafy.moamoa.dto.SignUpForm;
import com.ssafy.moamoa.service.ProfileServiceImpl;
import com.ssafy.moamoa.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
@Transactional
public class UserController {

    private final UserService userService;
    private final ProfileServiceImpl profileService;

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

    @PostMapping("/checkemail")
    public ResponseEntity<?> checkEmail(@RequestBody String email) throws JsonProcessingException {
        System.out.println(email);
        User user = User.builder()
                .email(email)
                .build();
        userService.validateDuplicateUserEmail(user);

        return new ResponseEntity<String>("이메일 중복 확인", HttpStatus.OK);
    }

    @PostMapping("/checkenickname")
    public ResponseEntity<?> checkNickName(@RequestBody String nickname) throws JsonProcessingException {
        Profile profile = Profile.builder()
                .nickname(nickname)
                .build();
        userService.validateDuplicateProfileNickname(profile);
        return new ResponseEntity<String>("닉네임 중복 확인", HttpStatus.OK);
    }
}
