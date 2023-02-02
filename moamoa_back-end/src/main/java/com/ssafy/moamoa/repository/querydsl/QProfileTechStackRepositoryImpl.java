package com.ssafy.moamoa.repository.querydsl;

import static com.ssafy.moamoa.domain.entity.QProfileTechStack.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.entity.ProfileTechStack;
import com.ssafy.moamoa.domain.entity.QProfileTechStack;

public class QProfileTechStackRepositoryImpl extends QuerydslRepositorySupport implements QProfileTechStackRepository {

	public QProfileTechStackRepositoryImpl() {
		super(ProfileTechStack.class);
	}

	@PersistenceContext
	EntityManager em;
	QProfileTechStack qProfileTechStack = profileTechStack;

	@Override
	public List<ProfileTechStack> getProfileTechStacks(Long profileId) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		return queryFactory.select(profileTechStack)
			.from(profileTechStack)
			.where(profileTechStack.profile.id.eq(profileId))
			.fetch();

	}

	@Override
	public List<ProfileTechStack> getProfileTechStacksByOrderAsc(Long profileId) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		return queryFactory.select(profileTechStack)
			.from(profileTechStack)
			.where(profileTechStack.profile.id.eq(profileId))
			.orderBy(profileTechStack.order.asc())
			.fetch();
	}

	@Override
	public Long deleteProfileTechStackByOrder(int order) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		Long count = queryFactory.delete(profileTechStack)
			.where(profileTechStack.order.eq(order))
			.execute();
		return count;
	}

	@Override
	public ProfileTechStack getProfileTechStack(Long techStackId) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		return queryFactory.select(profileTechStack)
			.from(profileTechStack)
			.where(profileTechStack.techStack.id.eq(techStackId))
			.fetchOne();
	}
}
