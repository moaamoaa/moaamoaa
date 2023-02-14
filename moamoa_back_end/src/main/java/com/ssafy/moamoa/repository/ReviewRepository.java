package com.ssafy.moamoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moamoa.domain.entity.Review;
import com.ssafy.moamoa.repository.querydsl.ReviewRepositoryCustom;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {

}
