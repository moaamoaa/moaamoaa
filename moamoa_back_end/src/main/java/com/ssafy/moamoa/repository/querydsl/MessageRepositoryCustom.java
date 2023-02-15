package com.ssafy.moamoa.repository.querydsl;

import com.ssafy.moamoa.domain.entity.Message;

import java.util.List;

public interface MessageRepositoryCustom {

    List<Message> getMessagesByChatroomIdTimeAsc(Long roomId);
}
