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


	public ReviewRepositoryImpl() {
		super(Review.class);
	}
	@PersistenceContext
	EntityManager em;

	QReview qReview = review;

	@Override
	public List<Review> getReviewsByOrderAsc(Long profileId) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		return queryFactory.select(review)
			.from(review)
			.where(review.receiveProfile.id.eq(profileId))
			.orderBy(review.time.asc())
			.fetch();
	}
}
