package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import javax.persistence.EntityManager;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.dto.SearchCondition;
import com.ssafy.moamoa.domain.entity.Project;

public class ProjectRepositoryImpl implements ProjectRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	public ProjectRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public List<Project> search(SearchCondition condition) {
		return null;
	}
}
