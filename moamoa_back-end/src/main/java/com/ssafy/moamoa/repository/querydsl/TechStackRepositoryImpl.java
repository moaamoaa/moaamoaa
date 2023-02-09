package com.ssafy.moamoa.repository.querydsl;

import static com.ssafy.moamoa.domain.entity.QTechStack.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.entity.TechStack;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TechStackRepositoryImpl extends QuerydslRepositorySupport implements TechStackRepositoryCustom {

	@PersistenceContext
	EntityManager em;
	private final JPAQueryFactory queryFactory;

	public TechStackRepositoryImpl(EntityManager em) {
		super(TechStack.class);
		this.queryFactory =new JPAQueryFactory(em);
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

	@Override
	public TechStack getTechStackByName(String techName) {


		TechStack getTechStack = queryFactory.select(techStack)
			.from(techStack)
			.where(techStack.name.eq(techName))
			.fetchOne();

		return getTechStack;
	}
}
