package com.ssafy.moamoa.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.entity.Chatroom;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.QChatroom;
import com.ssafy.moamoa.domain.entity.QProfile;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static com.ssafy.moamoa.domain.entity.QChatroom.*;

public class ChatroomRepositoryImpl extends QuerydslRepositorySupport implements ChatroomRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @PersistenceContext
    EntityManager em;

    public ChatroomRepositoryImpl(EntityManager em) {
        super(Chatroom.class);
        this.queryFactory = new JPAQueryFactory(em);
    }


   QChatroom qChatroom = chatroom;


    @Override
    public Chatroom getChatroomByProfiles(Profile one, Profile two) {
        return queryFactory.select(chatroom)
                .from(chatroom)
                .where(chatroom.userOne.id.eq(one.getId()).and(chatroom.userTwo.id.eq(two.getId()))
                    .or(chatroom.userOne.id.eq(two.getId()).and(chatroom.userTwo.id.eq(one.getId()))))
                .fetchOne();
    }

    @Override
    public List<Chatroom> getChatroomsBySenderProfile(Profile senderProfile) {
        return queryFactory.select(chatroom)
                .from(chatroom)
                .where(chatroom.userOne.id.eq(senderProfile.getId()).or(chatroom.userTwo.id.eq(senderProfile.getId())))
                .fetch();
    }
}
