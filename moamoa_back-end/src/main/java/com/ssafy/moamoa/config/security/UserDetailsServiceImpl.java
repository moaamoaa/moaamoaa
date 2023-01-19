package com.ssafy.moamoa.config.security;

import com.ssafy.moamoa.domain.Profile;
import com.ssafy.moamoa.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssafy.moamoa.domain.User;
import com.ssafy.moamoa.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //user를 가져옴
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다: " + username));
        Profile profile = profileRepository.findByUser_Id(user.getId()).get(0);
        return new UserDetailsImpl(user.getId(), user.getEmail(), user.getPassword(), profile.getNickname());
    }
}
