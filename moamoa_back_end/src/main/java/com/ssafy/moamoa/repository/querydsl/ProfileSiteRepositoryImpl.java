package com.ssafy.moamoa.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.entity.ProfileSite;
import com.ssafy.moamoa.domain.entity.QProfileSite;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ProfileSiteRepositoryImpl extends QuerydslRepositorySupport implements ProfileSiteRepositoryCustom {
    @PersistenceContext
    EntityManager em;

    private final JPAQueryFactory queryFactory;


    public ProfileSiteRepositoryImpl(EntityManager em) {
        super(ProfileSite.class);
        this.queryFactory = new JPAQueryFactory(em);
    }


    QProfileSite profileSite = QProfileSite.profileSite;


    @Override
    public List<ProfileSite> getProfileSitesByIdAsc(Long profileId) {

        return queryFactory.select(profileSite)
                .from(profileSite)
                .where(profileSite.profile.id.eq(profileId))
                .orderBy(profileSite.site.id.asc())
                .fetch();
    }


    @Override
    public Long deleteProfileSiteById(Long profileId) {

        return queryFactory.delete(profileSite)
                .where(profileSite.profile.id.eq(profileId))
                .execute();
    }


}
