package com.ssafy.moamoa.repository;

import com.ssafy.moamoa.domain.entity.Message;
import com.ssafy.moamoa.repository.querydsl.MessageRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long>, MessageRepositoryCustom {
}
