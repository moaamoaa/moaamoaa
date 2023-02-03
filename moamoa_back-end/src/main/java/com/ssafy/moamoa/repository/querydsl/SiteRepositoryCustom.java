package com.ssafy.moamoa.repository.querydsl;

import com.ssafy.moamoa.domain.entity.Site;

public interface SiteRepositoryCustom  {

    Site getSiteByName(String name);
}
