package com.ssafy.moamoa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.moamoa.domain.entity.Apply;
import com.ssafy.moamoa.domain.entity.Offer;
import com.ssafy.moamoa.domain.entity.Project;
import com.ssafy.moamoa.domain.entity.User;

public interface OfferRepository extends JpaRepository<Offer, Long> {
	@Query(value = "select offer "
		+ "from Offer offer "
		+ "where offer.user = :user")
	List<Offer> findByUser(@Param("user") User user);

	@Query(value = "select offer "
		+ "from Offer offer "
		+ "where offer.project = :project")
	List<Offer> findByProject(@Param("project") Project project);

	Optional<Offer> findByUser_IdAndProject_Id(Long userId, Long projectId);
}
