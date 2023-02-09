package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.Review;

public interface ReviewRepositoryCustom {

	List<Review> getReviewsByOrderAsc(Long profileId);

	Review getReviewById(Long reviewId);

	Long deleteReviewById(Long reviewId);

	Profile getProfileByReviewId(Long reviewId);

}
