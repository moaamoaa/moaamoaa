package com.ssafy.moamoa.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.moamoa.domain.ProfileSearchStatus;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileSearchStatusForm {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("searchstatus")
    private String status;
}
