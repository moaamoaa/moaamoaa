package com.ssafy.moamoa.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AreaForm {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

}
