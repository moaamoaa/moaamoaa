package com.ssafy.moamoa.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {
    @JsonProperty("sender")
    private String sender;
    @JsonProperty("receiver")
    private String receiver;

    @JsonProperty("context")
    private String context;
    
    @JsonProperty("time")
    private String time;
}
