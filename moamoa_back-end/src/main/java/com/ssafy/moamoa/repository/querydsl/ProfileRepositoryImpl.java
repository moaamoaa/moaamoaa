package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import javax.persistence.EntityManager;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.dto.SearchCondition;
import com.ssafy.moamoa.domain.entity.Profile;

public class ProfileRepositoryImpl implements ProfileRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	public ProfileRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public List<Profile> search(SearchCondition condition) {
		return null;
	}
}
