package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.moamoa.domain.dto.TechStackCategoryDto;
import com.ssafy.moamoa.repository.TechStackCategoryRepository;

@SpringBootTest
class TechStackCategoryRepositoryImplTest {

	@Autowired
	TechStackCategoryRepository techStackCategoryRepository;

	@Test
	void findTechStackWithCategory() {
		//when
		List<TechStackCategoryDto> techStackCategoryDto = techStackCategoryRepository.findTechStackWithCategory();

		//then
		Assertions.assertThat(techStackCategoryDto.size()).isEqualTo(4);
	}
}