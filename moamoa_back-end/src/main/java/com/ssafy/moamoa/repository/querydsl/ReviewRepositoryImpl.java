package com.ssafy.moamoa.repository.querydsl;

import static com.ssafy.moamoa.domain.entity.QReview.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.entity.QReview;
import com.ssafy.moamoa.domain.entity.Review;

public class ReviewRepositoryImpl extends QuerydslRepositorySupport implements ReviewRepositoryCustom {
	@PersistenceContext
	EntityManager em;

	private final JPAQueryFactory queryFactory;

	public ReviewRepositoryImpl(EntityManager em) {
		super(Review.class);
		this.queryFactory =new JPAQueryFactory(em);
	}

	QReview qReview = review;

	@Override
	public List<Review> getReviewsByOrderAsc(Long profileId) {

		return queryFactory.select(review)
			.from(review)
			.where(review.receiveProfile.id.eq(profileId))
			.orderBy(review.time.asc())
			.fetch();
	}
}
