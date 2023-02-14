package com.ssafy.moamoa.repository.querydsl;


import com.ssafy.moamoa.domain.dto.ProfileResultDto;
import com.ssafy.moamoa.domain.dto.SearchCondition;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfileRepositoryCustom {

    List<ProfileResultDto> search(SearchCondition condition, String cursorId, Pageable pageable);

    Profile getProfileById(Long profileId);

    void deleteProfileContextById(Long profileId);


    void setProfile(Profile profile);

    void setProfileImgLink(Long profileId, String img);

    void setProfileImgNull(Long profileId);

    Profile getProfileByUserId(Long userId);

    User getUserByProfileId(Long profileId);

}
