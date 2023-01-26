package com.ssafy.moamoa.repository.querydsl;

import static com.ssafy.moamoa.domain.QUserTechStack.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.QUserTechStack;
import com.ssafy.moamoa.domain.UserTechStack;

public class SearchUserTechStackRepositoryImpl extends QuerydslRepositorySupport
	implements SearchUserTechStackRepository {

	@PersistenceContext
	EntityManager em;

	QUserTechStack qUserTechStack = userTechStack;

	public SearchUserTechStackRepositoryImpl() {
		super(UserTechStack.class);
	}

	@Override
	public List<UserTechStack> getAllUserTechStackByOrder(Long userId) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		List<UserTechStack> userTechStackList = queryFactory
			.select(userTechStack)
			.from(userTechStack)
			.where(userTechStack.user.id.eq(userId))
			.orderBy(userTechStack.techStack.id.asc())
			.fetch();
		return userTechStackList;
	}

	@Override
	public Long deleteAllUserStackById(Long userId) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		Long count = queryFactory.delete(userTechStack)
			.where(userTechStack.user.id.eq(userId))
			.execute();

		return count;
	}

}
