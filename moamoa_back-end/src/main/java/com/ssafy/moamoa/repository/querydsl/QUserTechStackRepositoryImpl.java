package com.ssafy.moamoa.repository.querydsl;

import static com.ssafy.moamoa.domain.entity.QProfileTechStack.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.entity.ProfileTechStack;
import com.ssafy.moamoa.domain.entity.QProfileTechStack;

public class QUserTechStackRepositoryImpl extends QuerydslRepositorySupport
	implements QUserTechStackRepository {

	@PersistenceContext
	EntityManager em;

	QProfileTechStack qProfileTechStack = profileTechStack;

	public QUserTechStackRepositoryImpl() {
		super(ProfileTechStack.class);
	}

	@Override
	public List<ProfileTechStack> getAllUserTechStackByOrder(Long userId) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		List<ProfileTechStack> profileTechStackList = queryFactory
			.select(profileTechStack)
			.from(profileTechStack)
			.where(profileTechStack.profile.id.eq(userId))
			.orderBy(profileTechStack.techStack.id.asc())
			.fetch();
		return profileTechStackList;
	}

	@Override
	public Long deleteAllUserStackById(Long userId) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		Long count = queryFactory.delete(profileTechStack)
			.where(profileTechStack.profile.id.eq(userId))
			.execute();

		return count;
	}

}
