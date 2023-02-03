package com.ssafy.moamoa.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.moamoa.domain.dto.AreaForm;
import com.ssafy.moamoa.domain.entity.Area;
import com.ssafy.moamoa.domain.entity.ProfileArea;
import com.ssafy.moamoa.domain.entity.QArea;
import com.ssafy.moamoa.domain.entity.QProfileArea;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.ssafy.moamoa.domain.entity.QArea.*;
import static com.ssafy.moamoa.domain.entity.QProfileArea.*;

public class ProfileAreaRepositoryImpl extends QuerydslRepositorySupport implements ProfileAreaRepositoryCustom {

    public ProfileAreaRepositoryImpl() {
        super(ProfileArea.class);
    }
    @PersistenceContext
    EntityManager em;
   QProfileArea qProfileArea = profileArea;

    @Override
    public List<ProfileArea> getAreasByIdAsc(Long profileId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory.select(profileArea).from(profileArea)
                .where(profileArea.profile.id.eq(profileId))
                .orderBy(profileArea.order.asc())
                .fetch();
    }

    @Override
    public Long deleteAreasById(Long profileId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory.delete(profileArea)
                .where(profileArea.profile.id.eq(profileId))
                .execute();
    }
}
