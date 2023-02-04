package com.ssafy.moamoa.repository.querydsl;

import static com.ssafy.moamoa.domain.entity.QSidePjt.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.entity.QSidePjt;
import com.ssafy.moamoa.domain.entity.SidePjt;

public class SideProjectRepositoryImpl extends QuerydslRepositorySupport implements SideProjectRepositoryCustom {
	@PersistenceContext
	EntityManager em;

	private final JPAQueryFactory queryFactory;


	public SideProjectRepositoryImpl(EntityManager em) {

		super(SidePjt.class);
		this.queryFactory = new JPAQueryFactory(em);
	}

	QSidePjt qSidePjt = sidePjt;

	@Override
	public List<SidePjt> getSideProjectsByIdAsc(Long profileId) {

		return queryFactory
			.select(qSidePjt)
			.from(qSidePjt)
			.where(qSidePjt.profile.id.eq(profileId))
			.orderBy(qSidePjt.year.asc())
			.fetch();
	}

	@Override
	public SidePjt getSideProjectByAll(Long profileId, SidePjt sidePjt) {

		return queryFactory.select(qSidePjt)
			.from(qSidePjt)
			.where(qSidePjt.profile.id.eq(profileId).and(qSidePjt.name.eq(sidePjt.getName())).and(qSidePjt.year.eq(sidePjt.getYear())))
			.fetchOne();
	}

	@Override
	public SidePjt getSideProjectById(Long projectId) {

		return queryFactory.select(qSidePjt)
				.from(qSidePjt)
				.where(qSidePjt.id.eq(projectId))
				.fetchOne();
	}

	@Override
	public Long deleteSideProjectById(Long projectId) {
		return queryFactory.delete(qSidePjt)
				.where(qSidePjt.id.eq(projectId))
				.execute();
	}
}
