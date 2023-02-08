package com.ssafy.moamoa.domain.dto;

import lombok.*;

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

    private String context;


}
