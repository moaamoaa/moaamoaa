package com.ssafy.moamoa.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileForm {
    private Long id; // profileId

    private String nickname;

    private String profileSearchStatus; // 검색 Status

    private String profileOnOffStatus; // 온/오프라인 유무

    private String img;

    //  private MultipartFile file;

    private String context;


}
