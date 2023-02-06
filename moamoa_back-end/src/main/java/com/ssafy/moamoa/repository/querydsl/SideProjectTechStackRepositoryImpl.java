package com.ssafy.moamoa.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.entity.QSidePjtTechStack;
import com.ssafy.moamoa.domain.entity.SidePjtTechStack;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.ssafy.moamoa.domain.entity.QSidePjtTechStack.sidePjtTechStack;

public class SideProjectTechStackRepositoryImpl extends QuerydslRepositorySupport implements SideProjectTechStackRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    private final JPAQueryFactory queryFactory;

    public SideProjectTechStackRepositoryImpl(EntityManager em) {

        super(SidePjtTechStack.class);
        this.queryFactory = new JPAQueryFactory(em);
    }

    QSidePjtTechStack qSidePjtTechStack = sidePjtTechStack;

    @Override
    public List<SidePjtTechStack> getSideProjectTechStacks(Long projectId) {

        List<SidePjtTechStack> sidePjtTechStackList = queryFactory.select(sidePjtTechStack)
                .from(sidePjtTechStack)
                .where(sidePjtTechStack.sidePjt.id.eq(projectId))
                .fetch();

        return sidePjtTechStackList;
    }

    @Override
    public Long deleteSideProjectTechStackByOrder(int order) {

        Long count = queryFactory.delete(sidePjtTechStack)
                .where(sidePjtTechStack.order.eq(order))
                .execute();
        return count;
    }

    @Override
    public SidePjtTechStack getSidePjtTechStack(Long projectId, Long techStackId) {


        SidePjtTechStack tempSidePjtTechStack = queryFactory.select(sidePjtTechStack)
                .from(sidePjtTechStack)
                .where(sidePjtTechStack.sidePjt.id.eq(projectId).and(sidePjtTechStack.techStack.id.eq(techStackId)))
                .fetchOne();


        return tempSidePjtTechStack;
    }

    @Override
    public Long deleteAllSideProjectTechStack(Long projectId) {

        return queryFactory.delete(sidePjtTechStack)
                .where(sidePjtTechStack.sidePjt.id.eq(projectId))
                .execute();


    }


}
