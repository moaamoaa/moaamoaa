package com.ssafy.moamoa.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SiteForm {

    @JsonProperty("name")
    private String name;
    @JsonProperty("link")
    private String link;

    @JsonProperty("logo")
    private String logo;
}
