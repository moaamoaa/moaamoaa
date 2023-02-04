package com.ssafy.moamoa.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.entity.QUser;
import com.ssafy.moamoa.domain.entity.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.Embedded;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.ssafy.moamoa.domain.entity.QUser.*;

public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {
    @PersistenceContext
    EntityManager em;

    private final JPAQueryFactory queryFactory;
    public UserRepositoryImpl(EntityManager em ) {
        super(User.class);
        this.queryFactory = new JPAQueryFactory(em);
    }

    QUser user = QUser.user;

    @Override
    public User getUserById(Long userId) {
        return queryFactory.select(user)
                .from(user)
                .where(user.id.eq(userId))
                .fetchOne();
    }
}
