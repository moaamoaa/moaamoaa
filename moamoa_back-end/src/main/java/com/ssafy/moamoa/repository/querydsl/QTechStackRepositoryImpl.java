package com.ssafy.moamoa.repository.querydsl;

import static com.ssafy.moamoa.domain.QTechStack.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.TechStack;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QTechStackRepositoryImpl extends QuerydslRepositorySupport implements QTechStackRepository {

	@PersistenceContext
	EntityManager em;

	public QTechStackRepositoryImpl() {
		super(TechStack.class);
	}

	@Override
	public List<TechStack> searchTechStackByName(String techName) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		List<TechStack> techStackList = queryFactory
			.select(techStack)
			.from(techStack)
			.where(techStack.name.like(techName + "%"))
			.fetch();

		return techStackList;

	}

	@Override
	public TechStack getTechStackById(Long techStackId) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		TechStack getTechStack = queryFactory.select(techStack)
			.from(techStack)
			.where(techStack.id.eq(techStackId))
			.fetchOne();

		return getTechStack;

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
