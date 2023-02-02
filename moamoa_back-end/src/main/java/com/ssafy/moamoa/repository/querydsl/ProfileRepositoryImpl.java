package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.ssafy.moamoa.domain.dto.SearchCondition;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.QProfile;

public class ProfileRepositoryImpl implements ProfileRepositoryCustom {
	private final JPAQueryFactory queryFactory;
	@PersistenceContext
	EntityManager em;

	QProfile profile = QProfile.profile;
	public ProfileRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public List<Profile> search(SearchCondition condition) {
		return null;
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
