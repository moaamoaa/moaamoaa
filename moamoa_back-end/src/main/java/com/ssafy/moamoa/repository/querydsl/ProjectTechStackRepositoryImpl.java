package com.ssafy.moamoa.repository.querydsl;

import static com.ssafy.moamoa.domain.entity.QProjectTechStack.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.entity.ProjectTechStack;
import com.ssafy.moamoa.domain.entity.QProjectTechStack;

public class ProjectTechStackRepositoryImpl extends QuerydslRepositorySupport implements
	ProjectTechStackRepositoryCustom {

	@PersistenceContext
	EntityManager em;

	QProjectTechStack qProjectTechStack = projectTechStack;

	public ProjectTechStackRepositoryImpl() {
		super(ProjectTechStack.class);
	}

	// order 순서대로 DB에서 가져온다.
	@Override
	public List<ProjectTechStack> getAllProjectTechStackByOrder(Long projectId) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		List<ProjectTechStack> projectTechStackList = queryFactory
			.select(projectTechStack)
			.from(projectTechStack)
			.where(projectTechStack.project.id.eq(projectId))
			.orderBy(projectTechStack.order.asc())
			.fetch();

		return projectTechStackList;
	}

	@Override
	public Long deleteAllProjectStackById(Long projectId) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		Long count = queryFactory.delete(projectTechStack)
			.where(projectTechStack.project.id.eq(projectId))
			.execute();
		return count;
	}

	@Override
	public Long deleteProjectTechStackByOrder(int order) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		Long count = queryFactory.delete(projectTechStack)
			.where(projectTechStack.order.eq(order))
			.execute();
		return count;
	}

	@Override
	public ProjectTechStack getProjectTechStack(Long projectId, Long techStackId) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		ProjectTechStack tempProjectTechStack = queryFactory.select(projectTechStack)
			.from(projectTechStack)
			.where(projectTechStack.project.id.eq(projectId).and(projectTechStack.techStack.id.eq(techStackId)))
			.fetchOne();


		return tempProjectTechStack;
	}

}
