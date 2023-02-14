package com.ssafy.moamoa.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.entity.ProfileTechStack;
import com.ssafy.moamoa.domain.entity.QProfileTechStack;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.ssafy.moamoa.domain.entity.QProfileTechStack.profileTechStack;

public class ProfileTechStackRepositoryImpl extends QuerydslRepositorySupport implements
        ProfileTechStackRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    private final JPAQueryFactory queryFactory;

    public ProfileTechStackRepositoryImpl(EntityManager em) {
        super(ProfileTechStack.class);
        this.queryFactory = new JPAQueryFactory(em);
    }


    QProfileTechStack qProfileTechStack = profileTechStack;


    @Override
    public List<ProfileTechStack> getProfileTechStacksByOrderAsc(Long profileId) {


        return queryFactory.select(profileTechStack)
                .from(profileTechStack)
                .where(profileTechStack.profile.id.eq(profileId))
                .orderBy(profileTechStack.order.asc())
                .fetch();
    }


    @Override
    public Long deleteAllProfileTechStacksById(Long profileId) {

        return queryFactory.delete(profileTechStack)
                .where(profileTechStack.profile.id.eq(profileId))
                .execute();


    }
}
