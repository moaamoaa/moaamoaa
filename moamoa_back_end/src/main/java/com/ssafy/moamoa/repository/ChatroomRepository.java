package com.ssafy.moamoa.repository;

import com.ssafy.moamoa.domain.entity.Chatroom;
import com.ssafy.moamoa.repository.querydsl.ChatroomRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatroomRepository extends JpaRepository<Chatroom, Long> , ChatroomRepositoryCustom {
}
