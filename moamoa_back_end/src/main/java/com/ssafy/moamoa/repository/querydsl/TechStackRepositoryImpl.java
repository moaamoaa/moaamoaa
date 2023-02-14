package com.ssafy.moamoa.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.entity.TechStack;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.ssafy.moamoa.domain.entity.QTechStack.techStack;

@Slf4j
public class TechStackRepositoryImpl extends QuerydslRepositorySupport implements TechStackRepositoryCustom {

    @PersistenceContext
    EntityManager em;
    private final JPAQueryFactory queryFactory;

    public TechStackRepositoryImpl(EntityManager em) {
        super(TechStack.class);
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<TechStack> searchTechStackByName(String techName) {

        List<TechStack> techStackList = queryFactory
                .select(techStack)
                .from(techStack)
                .where(techStack.name.like(techName + "%"))
                .fetch();

        return techStackList;

    }

    @Override
    public TechStack getTechStackById(Long techStackId) {

        TechStack getTechStack = queryFactory.select(techStack)
                .from(techStack)
                .where(techStack.id.eq(techStackId))
                .fetchOne();

        return getTechStack;

    }

}
