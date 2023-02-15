package com.ssafy.moamoa.controller;

import com.ssafy.moamoa.domain.dto.ChatRoomDto;
import com.ssafy.moamoa.domain.dto.MessageDto;
import com.ssafy.moamoa.domain.entity.Message;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.service.ChatRoomService;
import com.ssafy.moamoa.service.MessageService;
import com.ssafy.moamoa.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatRoomService chatRoomService;
    private final MessageService messageService;

    private final ProfileService profileService;


    @MessageMapping("/send")
    public void receivePrivateMessage(@Payload Message message) {
        // messageService 로직 with redis
        messageService.saveMessage(message);
        simpMessagingTemplate.convertAndSend("/user/" + message.getReceiver().getNickname(), message); // /user/nickName

    }

    @GetMapping("/room")
    public ResponseEntity<?> getChatRooms(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Map<String, Object> resultMap = new HashMap<>();
        Profile profile = profileService.profileByUserId(Long.valueOf(userDetails.getUsername()));
        List<ChatRoomDto> result = chatRoomService.getChatroomsByProfile(profile);
        resultMap.put("chatrooms", result);
        try {

        } catch (NullPointerException e) {
            resultMap.put("error", "No Authentication");
            return new ResponseEntity<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/room/enter")
    public ResponseEntity<?> joinChatRoom(@RequestBody ChatRoomDto chatRoomDto, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try {

            Long roomId = chatRoomService.checkChatRoom(chatRoomDto, userDetails);
            List<MessageDto> result = messageService.getMessages(roomId);
            resultMap.put("messages", result);
        } catch (IllegalStateException e) {
            return new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
        } catch (NullPointerException e) {
            resultMap.put("error", "No Authentication");
            return new ResponseEntity<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }


}
