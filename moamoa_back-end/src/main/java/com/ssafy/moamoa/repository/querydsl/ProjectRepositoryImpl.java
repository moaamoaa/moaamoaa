package com.ssafy.moamoa.repository.querydsl;

import static com.querydsl.jpa.JPAExpressions.*;
import static com.ssafy.moamoa.domain.entity.QProjectArea.*;
import static com.ssafy.moamoa.domain.entity.QProjectTechStack.*;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.ProjectCategory;
import com.ssafy.moamoa.domain.ProjectStatus;
import com.ssafy.moamoa.domain.dto.ProjectResultDto;
import com.ssafy.moamoa.domain.dto.QProjectResultDto;
import com.ssafy.moamoa.domain.dto.SearchCondition;
import com.ssafy.moamoa.domain.entity.Project;
import com.ssafy.moamoa.domain.entity.QProject;

public class ProjectRepositoryImpl implements ProjectRepositoryCustom {
	private final JPAQueryFactory queryFactory;
	@PersistenceContext
	EntityManager em;

	public ProjectRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	QProject project = QProject.project;

	@Override
	public List<ProjectResultDto> search(SearchCondition condition, Long cursorId, Pageable pageable) {
		return queryFactory.select(
				new QProjectResultDto(project.id, project.title, project.contents, project.hit, project.totalPeople,
					project.currentPeople))
			.from(project)
			.where(titleContain(condition.getQuery()), statusEq(condition.getStatus()),
				categoryEq(condition.getCategory()), areaIn(condition.getArea()), techStackIn(condition.getStack()),
				nowDateBetween(), cursorIdLt(cursorId), unlockedProject())
			.orderBy(project.id.desc())
			.limit(pageable.getPageSize())
			.fetch();
	}

	private BooleanExpression cursorIdLt(Long cursorId) {
		return cursorId != null ? project.id.lt(cursorId) : null;
	}

	private BooleanExpression nowDateBetween() {
		return DateExpression.currentDate(LocalDate.class).between(project.startDate, project.endDate);
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

	private BooleanExpression unlockedProject() {
		return project.isLocked.eq(false);
	}

	//해당 지역을 포함하는 프로젝트
	private BooleanExpression areaIn(List<Long> areaCond) {
		return areaCond != null ?
			project.id.in(select(projectArea.project.id).from(projectArea).where(projectArea.area.id.in(areaCond))) :
			null;
	}

	//해당 기술스택을 포함한 프로젝트
	private BooleanExpression techStackIn(List<Long> stackCond) {
		return stackCond != null ? project.id.in(select(projectTechStack.project.id).distinct()
			.from(projectTechStack)
			.where(projectTechStack.techStack.id.in(stackCond))) : null;
	}

	@Override
	public Project getProjectById(Long projectId) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		return queryFactory.select(project).from(project).where(project.id.eq(projectId)).fetchOne();

	}

	@Override
	public void setProjectImgNull(Long projectId) {
		queryFactory.update(project)
			.setNull(project.img)
			.where(project.id.eq(projectId))
			.execute();
	}


	@Override
	public void setProjectImgLink(Long projectId, String img) {
		queryFactory.update(project)
			.set(project.img, img)
			.where(project.id.eq(projectId))
			.execute();
	}



}
