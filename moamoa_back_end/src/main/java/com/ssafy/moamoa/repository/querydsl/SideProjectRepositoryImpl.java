package com.ssafy.moamoa.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.entity.QSidePjt;
import com.ssafy.moamoa.domain.entity.SidePjt;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.ssafy.moamoa.domain.entity.QSidePjt.sidePjt;

public class SideProjectRepositoryImpl extends QuerydslRepositorySupport implements SideProjectRepositoryCustom {
    @PersistenceContext
    EntityManager em;

    private final JPAQueryFactory queryFactory;


    public SideProjectRepositoryImpl(EntityManager em) {

        super(SidePjt.class);
        this.queryFactory = new JPAQueryFactory(em);
    }

    QSidePjt qSidePjt = sidePjt;

    @Override
    public List<SidePjt> getSideProjectsByIdAsc(Long profileId) {

        return queryFactory
                .select(qSidePjt)
                .from(qSidePjt)
                .where(qSidePjt.profile.id.eq(profileId))
                .orderBy(qSidePjt.year.asc())
                .fetch();
    }


    @Override
    public SidePjt getSideProjectById(Long projectId) {

        return queryFactory.select(qSidePjt)
                .from(qSidePjt)
                .where(qSidePjt.id.eq(projectId))
                .fetchOne();
    }

    @Override
    public Long deleteSideProjectById(Long projectId) {
        return queryFactory.delete(qSidePjt)
                .where(qSidePjt.id.eq(projectId))
                .execute();
    }
}
