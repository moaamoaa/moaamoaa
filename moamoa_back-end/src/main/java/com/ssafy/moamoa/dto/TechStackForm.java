package com.ssafy.moamoa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TechStackForm {
    @JsonProperty("techName")
    private String name;
    @JsonProperty("img")
    private String img;
}
