package com.ssafy.moamoa.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.QTechStack;
import com.ssafy.moamoa.domain.QUserTechStack;
import com.ssafy.moamoa.domain.UserTechStack;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static com.ssafy.moamoa.domain.QUserTechStack.userTechStack;

public class SearchUserTechStackRepositoryImpl extends QuerydslRepositorySupport implements SearchUserTechStackRepository {

    @PersistenceContext
    EntityManager em;


    QUserTechStack qUserTechStack = userTechStack;

    public SearchUserTechStackRepositoryImpl() {
        super(UserTechStack.class);
    }

    @Override
    public List<UserTechStack> getAllUserTechStackByName(Long userId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
            List<UserTechStack> userTechStackList  = queryFactory
                    .select(userTechStack)
                    .from(userTechStack)
                    .where(userTechStack.user.id.eq(userId))
                    .fetch();
        return userTechStackList;
    }

    @Override
    public Long deleteAllUserStackById(Long userId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        Long count = queryFactory.delete(userTechStack)
                .where(userTechStack.user.id.eq(userId))
                .execute();

        return count;
    }

}
