package com.ssafy.moamoa.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.entity.ProjectArea;
import com.ssafy.moamoa.domain.entity.QProfileArea;
import com.ssafy.moamoa.domain.entity.QProjectArea;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.ssafy.moamoa.domain.entity.QProjectArea.*;


public class ProjectAreaRepositoryImpl extends QuerydslRepositorySupport implements ProjectAreaRepositoryCustom {
    @PersistenceContext
    EntityManager em;

    private final JPAQueryFactory queryFactory;

    QProjectArea qProjectArea = projectArea;

    public ProjectAreaRepositoryImpl(EntityManager em) {
        super(ProjectArea.class);
        this.queryFactory = new JPAQueryFactory(em);
    }



    @Override
    public ProjectArea getProjectAreaById(Long projectId) {
       return queryFactory.select(projectArea)
               .from(projectArea)
               .where(projectArea.project.id.eq(projectId))
               .fetchOne();
    }
}
