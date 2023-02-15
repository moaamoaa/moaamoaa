package com.ssafy.moamoa.repository.querydsl;

import com.ssafy.moamoa.domain.entity.Chatroom;
import com.ssafy.moamoa.domain.entity.Profile;

import java.util.List;

public interface ChatroomRepositoryCustom {

    Chatroom getChatroomByProfiles(Profile one, Profile two);

    List<Chatroom> getChatroomsBySenderProfile(Profile senderProfile);
}
