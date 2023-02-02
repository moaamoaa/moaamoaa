package com.ssafy.moamoa.repository.querydsl;

import static com.querydsl.core.group.GroupBy.*;
import static com.ssafy.moamoa.domain.entity.QTechStackCategory.*;
import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.dto.TechStackCategoryDto;
import com.ssafy.moamoa.domain.entity.Category;
import com.ssafy.moamoa.domain.entity.TechStack;

public class TechStackCategoryRepositoryImpl implements TechStackCategoryRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	public TechStackCategoryRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public List<TechStackCategoryDto> findTechStackWithCategory() {
		Map<Category, List<TechStack>> result = queryFactory.from(techStackCategory)
			.transform(groupBy(techStackCategory.category).as(list(techStackCategory.techStack)));

		return result.entrySet().stream()
			.map(entry -> new TechStackCategoryDto(entry.getKey(), entry.getValue()))
			.collect(toList());
	}
}
