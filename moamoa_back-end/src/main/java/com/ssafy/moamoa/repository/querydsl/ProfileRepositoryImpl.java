package com.ssafy.moamoa.repository.querydsl;

import static com.querydsl.jpa.JPAExpressions.*;
import static com.ssafy.moamoa.domain.entity.QProfileArea.*;
import static com.ssafy.moamoa.domain.entity.QProfileTechStack.*;
import static com.ssafy.moamoa.domain.entity.QProject.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.ssafy.moamoa.domain.ProfileOnOffStatus;
import com.ssafy.moamoa.domain.ProfileSearchStatus;
import com.ssafy.moamoa.domain.ProjectCategory;
import com.ssafy.moamoa.domain.ProjectStatus;
import com.ssafy.moamoa.domain.dto.ProfileResultDto;
import com.ssafy.moamoa.domain.dto.QProfileResultDto;
import com.ssafy.moamoa.domain.dto.SearchCondition;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.QProfile;

public class ProfileRepositoryImpl extends QuerydslRepositorySupport implements ProfileRepositoryCustom {
	private final JPAQueryFactory queryFactory;
	@PersistenceContext
	EntityManager em;

	QProfile profile = QProfile.profile;


	public ProfileRepositoryImpl(EntityManager em) {
		super(Profile.class);
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public List<ProfileResultDto> search(SearchCondition condition, Long cursorId, Pageable pageable) {
		return queryFactory
			.select(new QProfileResultDto(profile.id, profile.nickname, profile.context, profile.profileOnOffStatus))
			.from(profile)
			.where(nicknameContain(condition.getQuery()), categoryIn(condition.getCategory()),
				onlineOrArea(condition.getStatus(), condition.getArea()),
				techStackIn(condition.getStack()),
				searchStatusNeNone(), cursorIdLt(cursorId))
			.orderBy(profile.id.desc())
			.fetch();

	}

	private BooleanExpression cursorIdLt(Long cursorId) {
		return cursorId != null ? profile.id.lt(cursorId) : null;
	}

	//닉네임 포함 쿼리
	private BooleanExpression nicknameContain(String query) {
		return query != null ? profile.nickname.contains(query) : null;
	}

	//검색 상태 포함
	private BooleanExpression categoryIn(ProjectCategory categoryCond) {
		if (categoryCond != null) {
			ProfileSearchStatus cond = ProfileSearchStatus.valueOf(categoryCond.toString());
			return profile.searchState.in(ProfileSearchStatus.ALL, cond);
		}
		return null;
	}

	//온라인여부
	private BooleanExpression statusIn(ProjectStatus statusCond) {
		if (statusCond != null && ProfileOnOffStatus.valueOf(statusCond.toString()).equals(ProfileOnOffStatus.ONLINE)) {
			return profile.profileOnOffStatus.ne(ProfileOnOffStatus.OFFLINE);
		}
		return null;
	}

	//해당 지역을 포함하는 프로젝트
	private BooleanExpression areaIn(List<Long> areaCond) {
		return areaCond != null ?
			profile.id.in(
				select(profileArea.profile.id).distinct().from(profileArea).where(profileArea.area.id.in(areaCond))) :
			null;
	}

	//해당 지역을 포함하는 프로젝트
	private BooleanExpression onlineOrArea(ProjectStatus statusCond, List<Long> areaCond) {
		if (areaCond == null) {
			return statusIn(statusCond);
		}
		return statusNeOnline().and(areaIn(areaCond)).or(statusIn(statusCond));
	}

	private BooleanExpression statusNeOnline() {
		return profile.profileOnOffStatus.ne(ProfileOnOffStatus.ONLINE);
	}

	//해당 기술스택을 포함한 프로젝트
	private BooleanExpression techStackIn(List<Long> stackCond) {
		return stackCond != null ? project.id.in(
			select(profileTechStack.profile.id).distinct()
				.from(profileTechStack)
				.where(profileTechStack.techStack.id.in(stackCond))) : null;
	}

	private BooleanExpression searchStatusNeNone() {
		return profile.searchState.ne(ProfileSearchStatus.NONE);
	}

	@Override
	public Profile getProfileById(Long profileId) {

		return queryFactory
			.select(profile)
			.from(profile)
			.where(profile.id.eq(profileId))
			.fetchOne();

		return returnProfile;
	}

	@Override
	public Profile getProfileByName(String nickName) {

		return queryFactory.select(profile)
			.from(profile)
			.where(profile.nickname.eq(nickName))
			.fetchOne();

	}

	@Override
	public void deleteProfileContextById(Long profileId) {
		JPAUpdateClause jpaUpdateClause = new JPAUpdateClause(em, profile);
		jpaUpdateClause.where(profile.id.eq(profileId))
			.setNull(profile.context)
			.execute();

	}

	@Override
	public String setProfileOnOffStatus(Long profileId, ProfileOnOffStatus status) {
		JPAUpdateClause jpaUpdateClause = new JPAUpdateClause(em,profile);

		jpaUpdateClause.where(profile.id.eq(profileId))
			.set(profile.profileOnOffStatus,status)
			.execute();

		return status.toString();
	}

	@Override
	public void setProfile(Profile inputProfile) {
		JPAUpdateClause jpaUpdateClause = new JPAUpdateClause(em,profile);

		jpaUpdateClause.where(profile.id.eq(inputProfile.getId()))
			.set(profile.nickname, inputProfile.getNickname())
			.set(profile.profileOnOffStatus,inputProfile.getProfileOnOffStatus())
			.execute();
	}

	@Override
	public Profile getProfileByUserId(Long userId) {
		return queryFactory.select(profile)
				.from(profile)
				.where(profile.user.id.eq(userId))
				.fetchOne();
	}
}
