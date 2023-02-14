package com.ssafy.moamoa.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.entity.Area;
import com.ssafy.moamoa.domain.entity.QArea;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.ssafy.moamoa.domain.entity.QArea.*;

public class AreaRepositoryImpl extends QuerydslRepositorySupport implements AreaRepositoryCustom {
    @PersistenceContext
    EntityManager em;

    private final JPAQueryFactory queryFactory;

    public AreaRepositoryImpl(EntityManager em) {
        super(Area.class);
        this.queryFactory = new JPAQueryFactory(em);
    }



    QArea area = QArea.area;

    @Override
    public Area getAreaById(Long areaId) {


        return queryFactory.select(area)
                .from(area)
                .where(area.id.eq(areaId))
                .fetchOne();
    }
}
