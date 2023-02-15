package com.ssafy.moamoa.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.entity.Message;
import com.ssafy.moamoa.domain.entity.QMessage;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static com.ssafy.moamoa.domain.entity.QMessage.*;


public class MessageRepositoryImpl extends QuerydslRepositorySupport implements MessageRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @PersistenceContext
    EntityManager em;


    public MessageRepositoryImpl(EntityManager em) {
        super(Message.class);
        this.queryFactory = new JPAQueryFactory(em);
    }

    QMessage qMessage = message;


    @Override
    public List<Message> getMessagesByChatroomIdTimeAsc(Long roomId) {
        return queryFactory.select(message)
                .from(message)
                .where(message.chatroom.id.eq(roomId))
                .orderBy(message.time.asc())
                .fetch();
    }
}
