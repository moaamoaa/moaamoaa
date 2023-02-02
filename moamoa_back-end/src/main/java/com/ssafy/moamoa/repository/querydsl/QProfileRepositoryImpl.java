package com.ssafy.moamoa.repository.querydsl;

import static com.ssafy.moamoa.domain.entity.QProfile.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.QProfile;

public class QProfileRepositoryImpl extends QuerydslRepositorySupport implements QProfileRepository {
	@PersistenceContext
	EntityManager em;

	QProfile qProfile = profile;

	public QProfileRepositoryImpl() {
		super(Profile.class);
	}

	@Override
	public Profile getProfileById(Long profileId) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		Profile returnProfile = queryFactory
			.select(profile)
			.from(profile)
			.where(profile.id.eq(profileId))
			.fetchOne();

		return returnProfile;
	}

	@Override
	public void deleteProfileContextById(Long profileId) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		JPAUpdateClause jpaUpdateClause = new JPAUpdateClause(em, profile);
		jpaUpdateClause.where(profile.id.eq(profileId))
			.setNull(profile.context)
			.execute();

	}
}
