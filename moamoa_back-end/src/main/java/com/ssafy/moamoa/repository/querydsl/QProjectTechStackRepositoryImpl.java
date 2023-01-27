package com.ssafy.moamoa.repository.querydsl;

import static com.ssafy.moamoa.domain.entity.QProjectTechStack.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.entity.ProjectTechStack;
import com.ssafy.moamoa.domain.entity.QProjectTechStack;

public class QProjectTechStackRepositoryImpl extends QuerydslRepositorySupport implements QProjectTechStackRepository {

	@PersistenceContext
	EntityManager em;

	QProjectTechStack qProjectTechStack = projectTechStack;

	public QProjectTechStackRepositoryImpl() {
		super(ProjectTechStack.class);
	}

	@Override
	public List<ProjectTechStack> getAllProjectTechStackByOrder(Long projectId) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		List<ProjectTechStack> projectTechStackList = queryFactory
			.select(projectTechStack)
			.from(projectTechStack)
			.where(projectTechStack.project.id.eq(projectId))
			.orderBy(projectTechStack.techStack.id.asc())
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
}
