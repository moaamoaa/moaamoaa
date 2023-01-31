package com.ssafy.moamoa.repository.querydsl;

import static com.querydsl.jpa.JPAExpressions.*;
import static com.ssafy.moamoa.domain.entity.QProject.*;
import static com.ssafy.moamoa.domain.entity.QProjectArea.*;
import static com.ssafy.moamoa.domain.entity.QProjectTechStack.*;

import java.util.List;

import javax.persistence.EntityManager;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.ProjectCategory;
import com.ssafy.moamoa.domain.ProjectStatus;
import com.ssafy.moamoa.domain.dto.ProjectDto;
import com.ssafy.moamoa.domain.dto.QProjectDto;
import com.ssafy.moamoa.domain.dto.SearchCondition;
import com.ssafy.moamoa.domain.entity.ProjectArea;
import com.ssafy.moamoa.domain.entity.ProjectTechStack;

public class ProjectRepositoryImpl implements ProjectRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	public ProjectRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public List<ProjectDto> search(SearchCondition condition) {
		return queryFactory.select(
				new QProjectDto(project.id, project.title, project.contents, project.hit, project.totalPeople,
					project.currentPeople))
			.from(project)
			.where(titleContain(condition.getQuery()), statusEq(condition.getStatus()),
				categoryEq(condition.getCategory()), areaIn(condition.getArea()), techStackIn(condition.getStack()))
			.fetch();

		//쿼리 query

		//진행방식 status

		//모집 구분 category

		//지역 area -> join 필요X (리턴 데이터 필요X) / where절에 subquery 필요

		//기술스택 stack -> join 필요 (리턴 데이터 필요) (select의 subquery로) / select절에 subQuery 필요
	}

	@Override
	public List<ProjectTechStack> searchTechStack(SearchCondition condition) {
		return queryFactory.selectFrom(projectTechStack).where(techStackIn(condition.getStack())).fetch();
	}

	@Override
	public List<ProjectArea> searchArea(SearchCondition condition) {
		return queryFactory.selectFrom(projectArea).where(areaIn(condition.getArea())).fetch();
	}

	private BooleanExpression titleContain(String query) {

		return query != null ? project.title.contains(query) : null;
	}

	private BooleanExpression statusEq(ProjectStatus statusCond) {
		return statusCond != null ? project.onoffline.eq(statusCond) : null;
	}

	private BooleanExpression categoryEq(ProjectCategory categoryCond) {
		return categoryCond != null ? project.category.eq(categoryCond) : null;
	}

	private BooleanExpression areaIn(List<Long> areaCond) {
		return areaCond != null ? project.id.in(
			select(projectArea.project.id)
				.from(projectArea)
				.where(projectArea.area.id.in(areaCond))
		) : null;
	}

	private BooleanExpression projectEq(List<Long> ids) {
		return ids != null ? project.id.in(ids) : null;
	}

	private BooleanExpression techStackIn(List<Long> stackCond) {
		return stackCond != null ? projectTechStack.techStack.id.in(stackCond) : null;
	}

}
