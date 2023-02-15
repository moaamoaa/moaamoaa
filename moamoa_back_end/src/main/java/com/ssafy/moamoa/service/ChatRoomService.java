package com.ssafy.moamoa.service;

import com.ssafy.moamoa.domain.dto.ChatRoomDto;
import com.ssafy.moamoa.domain.entity.Chatroom;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.repository.ChatroomRepository;
import com.ssafy.moamoa.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatroomRepository chatroomRepository;

    private final ProfileService profileService;

    private final RedisTemplate<String, String> redisTemplate;

    private final ProfileRepository profileRepository;


    public Chatroom createChatRoom(Profile receiverProfile, Profile senderProfile) {

        Chatroom chatroom = Chatroom.builder()
                .userOne(senderProfile)
                .userTwo(receiverProfile)
                .build();

        Chatroom returnChatroom = chatroomRepository.save(chatroom);

        return returnChatroom;
    }

    public Long checkChatRoom(ChatRoomDto chatRoomDto, UserDetails userDetails) {

        Profile senderProfile = profileService.profileByUserId(Long.valueOf(userDetails.getUsername()));

        Profile receiverProfile = profileRepository.getProfileById(chatRoomDto.getReceiverId());

        Long roomId = 0L;

        Chatroom chatroom = chatroomRepository.getChatroomByProfiles(senderProfile, receiverProfile);

        if (chatroom == null) {
            chatroom = createChatRoom(receiverProfile, senderProfile);
        }

        roomId = chatroom.getId();

        return roomId;
    }

    public List<ChatRoomDto> getChatroomsByProfile(Profile senderProfile) {

        List<Chatroom> result = chatroomRepository.getChatroomsBySenderProfile(senderProfile);
        List<ChatRoomDto> returnList = new ArrayList<>();
        for (Chatroom c : result) {
            Long oneId = c.getUserOne().getId();
            Long twoId = c.getUserTwo().getId();
            boolean isOne = true; // isOne이 sender 일 경우
            if (oneId != senderProfile.getId()) {
                isOne = false;
            }

            Long senderId = 0L;
            Long receiveId = 0L;

            if (isOne) {
                senderId = oneId;
                receiveId = twoId;
            } else {
                senderId = twoId;
                receiveId = oneId;
            }

            ChatRoomDto chatRoomDto = ChatRoomDto.builder()
                    .id(c.getId())
                    .receiverId(receiveId)
                    .senderId(senderId)
                    .receiverName(profileRepository.getProfileById(receiveId).getNickname())
                    .build();

            returnList.add(chatRoomDto);
        }
        return returnList;
    }


}
