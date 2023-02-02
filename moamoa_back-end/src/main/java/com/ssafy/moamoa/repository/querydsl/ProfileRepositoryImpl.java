package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.ssafy.moamoa.domain.dto.ProfileResultDto;
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
	public List<ProfileResultDto> search(SearchCondition condition) {
		// queryFactory
		// 	.select(new QProfileResultDto())
		// 	.from(profile)
		// 	.where(nicknameContain(condition.getQuery()), categoryIn(condition.getCategory()),
		// 		statusEq(condition.getStatus()),
		// 		areaIn(condition.getArea()), techStackIn(condition.getStack()),
		// 		nowDateBetween())
		return null;
	}

	// //검색 허용 안함
	//
	// //닉네임 포함 쿼리
	// private BooleanExpression nicknameContain(String query) {
	// 	return query != null ? profile.nickname.contains(query) : null;
	// }
	//
	// //검색 상태 포함
	// private BooleanExpression categoryIn(ProjectCategory categoryCond) {
	// 	ProfileSearchStatus cond = ProfileSearchStatus.valueOf(categoryCond.toString());
	// 	return categoryCond != null ? profile.searchState.in(ProfileSearchStatus.ALL, cond) : null;
	// }
	//
	//
	// private BooleanExpression statusIn(ProjectStatus statusCond) {
	// 	return statusCond != null ? profile.searchState.eq(statusCond) : null;
	// }
	//
	// //해당 지역을 포함하는 프로젝트
	// private BooleanExpression areaIn(List<Long> areaCond) {
	// 	return areaCond != null ?
	// 		project.id.in(select(projectArea.project.id).from(projectArea).where(projectArea.area.id.in(areaCond))) :
	// 		null;
	// }
	//
	// //해당 기술스택을 포함한 프로젝트
	// private BooleanExpression techStackIn(List<Long> stackCond) {
	// 	return stackCond != null ? project.id.in(select(projectTechStack.project.id).distinct()
	// 		.from(projectTechStack)
	// 		.where(projectTechStack.techStack.id.in(stackCond))) : null;
	// }

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
