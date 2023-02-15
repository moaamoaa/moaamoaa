package com.ssafy.moamoa.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomDto {

    @JsonProperty("roomId")
    private Long id;

    private Long senderId;

    @JsonProperty("nickname")
    private String receiverName;

    private Long receiverId;


}
