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

	public SideProjectRepositoryImpl() {
		super(SidePjt.class);
	}

	@PersistenceContext
	EntityManager em;

	QSidePjt qSidePjt = sidePjt;

	@Override
	public List<SidePjt> getSideProjects(Long profileId) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		return queryFactory
			.select(qSidePjt)
			.from(qSidePjt)
			.where(qSidePjt.profile.id.eq(profileId))
			.fetch();
	}

	@Override
	public SidePjt getSideProjectByAll(SidePjt sidePjt) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);


		return queryFactory.select(qSidePjt)
			.from(qSidePjt)
			.where(qSidePjt.profile.id.eq(sidePjt.getId()).and(qSidePjt.name.eq(sidePjt.getName())).and(qSidePjt.year.eq(sidePjt.getYear())))
			.fetchOne();
	}
}
