package com.ssafy.moamoa.service;

import com.ssafy.moamoa.domain.Profile;
import com.ssafy.moamoa.domain.ProfileSearchStatus;
import com.ssafy.moamoa.domain.User;
import com.ssafy.moamoa.dto.ProfileForm;
import com.ssafy.moamoa.repository.ProfileRepository;
import com.ssafy.moamoa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";

    private ProfileRepository profileRepository;

    private UserRepository userRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }


    @Override
    public Optional<Profile> getProfile(Long userId) {
        return profileRepository.findById(userId);
    }

    @Override
    public ProfileForm changeUserState(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new NullPointerException();
        }

        Optional<Profile> profileList = profileRepository.findByUser_Id(user.get().getId());
        if (profileList.isEmpty()) {
            // 회원이 없는 경우 exception
        }
		/*else if(profileList.size()>=2){
			// 프로필이 왜이리 많아요 exception
		}*/

        Profile profile = profileList.get();
        String searchState = String.valueOf(profile.getSearchState());
        switch (searchState) {
            case "ALL":
                searchState = String.valueOf(ProfileSearchStatus.PROJECT);
                break;
            case "PROJECT":
                searchState = String.valueOf(ProfileSearchStatus.STUDY);
                break;
            case "STUDY":
                searchState = String.valueOf(ProfileSearchStatus.NONE);
                break;
            case "NONE":
                searchState = String.valueOf(ProfileSearchStatus.ALL);
                break;
        }
        ProfileForm profileForm = new ProfileForm(profile.getId(), profile.getNickname(),
                searchState, profile.getImg(), profile.getContext());

        return profileForm;
    }


    // public String changeSearchState(User user, String searchState) {
    //
    // 	switch (searchState) {
    // 		case "ALL":
    // 			searchState = String.valueOf(ProfileSearchStatus.PROJECT);
    // 			break;
    // 		case "PROJECT":
    // 			searchState = String.valueOf(ProfileSearchStatus.STUDY);
    // 			break;
    // 		case "STUDY":
    // 			searchState = String.valueOf(ProfileSearchStatus.NONE);
    // 			break;
    // 		case "NONE":
    // 			searchState = String.valueOf(ProfileSearchStatus.ALL);
    // 			break;
    // 	}
    // 	// Repository
    //
    // 	Profile profile = profileRepository.findBySearchState(searchState);
    // 	return SUCCESS;
    // }


}
