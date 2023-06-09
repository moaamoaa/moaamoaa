package com.ssafy.moamoa.repository.querydsl;

import static com.ssafy.moamoa.domain.entity.QReview.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.entity.Profile;
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

	@Override
	public Review getReviewById(Long reviewId) {
		return queryFactory.select(review)
				.from(review)
				.where(review.id.eq(reviewId))
				.fetchOne();
	}

	@Override
	public Long deleteReviewById(Long reviewId) {
		return queryFactory.delete(review)
				.where(review.id.eq(reviewId))
				.execute();


	}

	@Override
	public Profile getProfileByReviewId(Long reviewId) {
		return queryFactory.select(review.receiveProfile)
			.from(review)
			.where(review.id.eq(reviewId))
			.fetchOne();
	}
}
