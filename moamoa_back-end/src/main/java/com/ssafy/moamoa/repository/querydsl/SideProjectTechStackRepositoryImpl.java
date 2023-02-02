package com.ssafy.moamoa.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.entity.ProjectTechStack;
import com.ssafy.moamoa.domain.entity.QSidePjtTechStack;
import com.ssafy.moamoa.domain.entity.SidePjtTechStack;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.ssafy.moamoa.domain.entity.QProjectTechStack.projectTechStack;
import static com.ssafy.moamoa.domain.entity.QSidePjtTechStack.*;

public class SideProjectTechStackRepositoryImpl extends QuerydslRepositorySupport implements SideProjectTechStackRepositoryCustom {


    public SideProjectTechStackRepositoryImpl() {
        super(SidePjtTechStack.class);
    }
    @PersistenceContext
    EntityManager em;

    QSidePjtTechStack qSidePjtTechStack = sidePjtTechStack;

    @Override
    public List<SidePjtTechStack> getSideProjectsByOrderAsc(Long projectId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        List<SidePjtTechStack> sidePjtTechStackList = queryFactory.select(sidePjtTechStack)
                .from(sidePjtTechStack)
                .where(sidePjtTechStack.sidePjt.id.eq(projectId))
                .fetch();

        return sidePjtTechStackList;
    }

    @Override
    public Long deleteSideProjectTechStackByOrder(int order) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        Long count = queryFactory.delete(sidePjtTechStack)
                .where(sidePjtTechStack.order.eq(order))
                .execute();
        return count;
    }

    @Override
    public SidePjtTechStack getSidePjtTechStack(Long projectId, Long techStackId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        SidePjtTechStack tempSidePjtTechStack = queryFactory.select(sidePjtTechStack)
                .from(sidePjtTechStack)
                .where(sidePjtTechStack.sidePjt.id.eq(projectId).and(sidePjtTechStack.techStack.id.eq(techStackId)))
                .fetchOne();


        return tempSidePjtTechStack;
    }

    @Override
    public Long deleteAllSideProjectTechStack(Long projectId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory.delete(sidePjtTechStack)
                .where(sidePjtTechStack.sidePjt.id.eq(projectId))
                .execute();


    }


}
