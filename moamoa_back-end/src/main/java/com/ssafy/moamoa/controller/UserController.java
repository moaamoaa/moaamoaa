package com.ssafy.moamoa.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.moamoa.domain.Profile;
import com.ssafy.moamoa.domain.User;
import com.ssafy.moamoa.dto.SignForm;
import com.ssafy.moamoa.service.MailService;
import com.ssafy.moamoa.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
@Transactional
public class UserController {
    private final UserService userService;
    private final MailService mailService;

    @GetMapping
    public ResponseEntity<?> showList() throws Exception {
        List<User> users = userService.findUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignForm signForm) throws JsonProcessingException {

        String email = signForm.getEmail();
        String password = signForm.getPassword();
        String nickname = signForm.getNickname();

        String userNickname = userService.signup(email, password, nickname);

        return new ResponseEntity<String>(userNickname, HttpStatus.OK);
    }

    // 회원 가입 시 메일 유효성 확인
    @GetMapping("/email")
    public ResponseEntity<?> checkEmail(@RequestBody SignForm signForm) throws MessagingException {
        String code = mailService.joinEmail(signForm.getEmail());

        return new ResponseEntity<String>("code", HttpStatus.OK);
    }
    
    // 닉네임 중복 확인
    @GetMapping("/nickname")
    public ResponseEntity<?> checkNickname(@RequestParam("nickname") String nickname) throws JsonProcessingException {
        Profile profile = Profile.builder()
                .nickname(nickname)
                .build();
        userService.validateDuplicateProfileNickname(profile);
        return new ResponseEntity<String>("닉네임 중복 검증 성공", HttpStatus.OK);
    }

    // 비밀번호 변경
    @PostMapping("/password/{id}")
    public ResponseEntity<?> updatePassword(@PathVariable Long id, @RequestBody SignForm signForm) {
        // 받은 비밀번호로 update
        userService.updatePassword(signForm.getPassword(), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

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

    // 닉네임 변경
    @PostMapping("/nickname")
    public ResponseEntity<?> updateNickname(@RequestBody SignForm signForm) {
        // 받은 닉네임으로 update
        userService.updateNickname(signForm.getNickname(), signForm.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 회원 삭제
    @DeleteMapping()
    public ResponseEntity<?> deleteUser(@RequestBody SignForm signForm) {
        // 받은 이메일로 delete
        userService.deleteUser(signForm.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
