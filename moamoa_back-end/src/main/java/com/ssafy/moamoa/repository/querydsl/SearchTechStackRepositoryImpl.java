package com.ssafy.moamoa.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.QTechStack;
import com.ssafy.moamoa.domain.TechStack;
import com.ssafy.moamoa.dto.TechStackForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.ssafy.moamoa.domain.QTechStack.techStack;
@Slf4j
public class SearchTechStackRepositoryImpl extends QuerydslRepositorySupport implements SearchTechStackRepository {


    @PersistenceContext
    EntityManager em;




    public SearchTechStackRepositoryImpl() {
        super(TechStack.class);
    }

    @Override
    public List<TechStack> searchTechStackByName(String techName) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);



        List<TechStack> techStackList = queryFactory
                .select(techStack)
                .from(techStack)
                .where(techStack.name.like(techName+"%"))
                .fetch();
        System.out.println(techStackList.size());
        for(TechStack ts : techStackList){
            System.out.println(ts.getName());
        }
        return techStackList;

    }

    @Override
    public TechStack getTechStackByName(String techName) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        TechStack getTechStack = queryFactory.select(techStack)
                .from(techStack)
                .where(techStack.name.eq(techName))
                .fetchOne();


        return getTechStack;
    }
}
