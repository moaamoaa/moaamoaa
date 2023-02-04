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
    @PersistenceContext
    EntityManager em;

    private final JPAQueryFactory queryFactory;


    public ProfileSiteRepositoryImpl(EntityManager em) {
        super(ProfileSite.class);
        this.queryFactory = new JPAQueryFactory(em);
    }


    QProfileSite profileSite = QProfileSite.profileSite;

    @Override
    public ProfileSite getProfileSiteByName(String name) {
        return queryFactory.select(profileSite)
                .from(profileSite)
                .where(profileSite.site.name.eq(name))
                .fetchOne();
    }

    @Override
    public List<ProfileSite> getProfileSitesByIdAsc(Long profileId) {

        return queryFactory.select(profileSite)
            .from(profileSite)
            .where(profileSite.profile.id.eq(profileId))
            .orderBy(profileSite.site.id.asc())
            .fetch();
    }

    @Override
    public List<ProfileSite> getProfileSitesById(Long profileId) {

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

        return queryFactory.delete(profileSite)
                .where(profileSite.profile.id.eq(profileId))
                .execute();
    }


}
