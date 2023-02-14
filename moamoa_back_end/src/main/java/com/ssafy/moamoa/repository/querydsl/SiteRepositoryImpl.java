package com.ssafy.moamoa.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.entity.QSite;
import com.ssafy.moamoa.domain.entity.Site;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.ssafy.moamoa.domain.entity.QSite.*;

public class SiteRepositoryImpl extends QuerydslRepositorySupport implements SiteRepositoryCustom {
    @PersistenceContext
    EntityManager em;

    private final JPAQueryFactory queryFactory;

    public SiteRepositoryImpl(EntityManager em) {
        super(Site.class);
        this.queryFactory = new JPAQueryFactory(em);
    }


    QSite site = QSite.site;

    @Override
    public Site getSiteByName(String name) {

        return queryFactory.select(site)
                .from(site)
                .where(site.name.eq(name))
                .fetchOne();
    }
}
