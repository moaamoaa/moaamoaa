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
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

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

    public void updatePassword(String password, Long id) {
        Optional<User> findUsers = userRepository.findById(id);
        if (!findUsers.isPresent()) {
            return;
        }
        User findUser = findUsers.get();
        System.out.println(findUser.getId());
        findUser.setPassword(password);
    }

    public void updatePasswordByEmail(String password, String email) {
        Optional<User> findUsers = userRepository.findByEmail(email);
        if (!findUsers.isPresent()) {
            return;
        }
        User findUser = findUsers.get();
        System.out.println(findUser.getId());
        findUser.setPassword(password);
    }

    public void updateNickname(String nickname, String email) {
        Optional<User> findUsers = userRepository.findByEmail(email);
        if (!findUsers.isPresent()) {
            return;
        }
        User findUser = findUsers.get();
        System.out.println(findUser.getId());
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
        System.out.println(findProfile.getUser());
        findProfile.setNickname(nickname);
    }

    public void deleteUser(String email) {
        Optional<User> findUsers = userRepository.findByEmail(email);
        if (!findUsers.isPresent()) {
            return;
        }
        User findUser = findUsers.get();
        System.out.println(findUser.getId());
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