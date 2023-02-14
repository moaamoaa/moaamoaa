package com.ssafy.moamoa.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.moamoa.domain.entity.Area;

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

    public static AreaForm toEntity(Area area){
        return AreaForm.builder()
            .id(area.getId())
            .name(area.getName())
            .build();
    }

}
