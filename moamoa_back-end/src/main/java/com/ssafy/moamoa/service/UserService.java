package com.ssafy.moamoa.service;

import com.ssafy.moamoa.domain.Profile;
import com.ssafy.moamoa.domain.ProfileSearchStatus;
import com.ssafy.moamoa.domain.User;
import com.ssafy.moamoa.exception.DuplicateProfileNicknameException;
import com.ssafy.moamoa.exception.DuplicateUserEmailException;
import com.ssafy.moamoa.repository.ProfileRepository;
import com.ssafy.moamoa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    // 회원 전체 조회
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    // 이메일 중복 조회
    public void validateDuplicateUserEmail(User user) {
        List<User> findUsers = userRepository.findByEmail(user.getEmail());
        if (!findUsers.isEmpty()) {
            throw new DuplicateUserEmailException("이미 존재하는 회원입니다.");
        }
    }

    // 닉네임 중복 조회
    public void validateDuplicateProfileNickname(Profile profile) {
        List<Profile> findProfiles = profileRepository.findByNickname(profile.getNickname());
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
}
