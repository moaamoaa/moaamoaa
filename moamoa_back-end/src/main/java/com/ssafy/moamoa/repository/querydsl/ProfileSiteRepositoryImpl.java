package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.ssafy.moamoa.domain.entity.ProfileSite;
import com.ssafy.moamoa.domain.entity.QProfileSite;

public class ProfileSiteRepositoryImpl extends QuerydslRepositorySupport implements ProfileSiteRepositoryCustom {



    public ProfileSiteRepositoryImpl() {
        super(ProfileSite.class);
    }

    @PersistenceContext
    EntityManager em;

    QProfileSite profileSite = QProfileSite.profileSite;
    @Override
    public ProfileSite getProfileSiteByName(String name) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        return queryFactory.select(profileSite)
                .from(profileSite)
                .where(profileSite.site.name.eq(name))
                .fetchOne();
    }

    @Override
    public List<ProfileSite> getProfileSitesByIdAsc(Long profileId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory.select(profileSite)
            .from(profileSite)
            .where(profileSite.profile.id.eq(profileId))
            .orderBy(profileSite.site.id.asc())
            .fetch();
    }

    @Override
    public List<ProfileSite> getProfileSitesById(Long profileId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory.select(profileSite)
                .from(profileSite)
                .where(profileSite.profile.id.eq(profileId))
                .fetch();
    }

    @Override
    public void setProfileSiteLink(ProfileSite inputProfileSite) {
        JPAUpdateClause updateClause = new JPAUpdateClause(em,profileSite);

        updateClause.where(profileSite.site.name.eq(inputProfileSite.getSite().getName()))
                .set(profileSite.link,inputProfileSite.getLink())

                .execute();
    }

    @Override
    public Long deleteProfileSiteById(Long profileId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory.delete(profileSite)
                .where(profileSite.profile.id.eq(profileId))
                .execute();
    }


}
