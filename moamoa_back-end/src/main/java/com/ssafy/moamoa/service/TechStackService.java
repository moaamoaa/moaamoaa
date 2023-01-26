package com.ssafy.moamoa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.TechStack;
import com.ssafy.moamoa.domain.UserTechStack;
import com.ssafy.moamoa.dto.TechStackForm;
import com.ssafy.moamoa.repository.TechStackRepository;
import com.ssafy.moamoa.repository.UserRepository;
import com.ssafy.moamoa.repository.UserTechStackRepository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = false)
@NoArgsConstructor
@Slf4j
public class TechStackService {

	@Autowired
	private TechStackRepository techstackRepository;

	@Autowired
	private UserTechStackRepository userTechStackRepository;

	@Autowired
	private UserRepository userRepository;

	public List<TechStackForm> searchTechStackByName(String techName) {
		List<TechStack> domainResult = techstackRepository.searchTechStackByName(techName);

		List<TechStackForm> techStackFormList = new ArrayList<>();

		for (TechStack ts : domainResult) {
			techStackFormList.add(new TechStackForm(ts.getName(), ts.getLogo()));
		}

		return techStackFormList;
	}

	// Id, techStackFormList
	public String modifyUserTechStack(Long userId, List<TechStackForm> TechStackFormList) {
		List<UserTechStack> userTechStackList = new ArrayList<>();
		// 유저 스택 모두 삭제
		Long deleteCount = userTechStackRepository.deleteAllUserStackById(userId);

		// 유저 스택 모두 추가
		for (TechStackForm techStackForm : TechStackFormList) {
			// profile
			UserTechStack userTechStack = UserTechStack.builder()
				.techStack(techstackRepository.getTechStackByName(techStackForm.getName()))
				.user(userRepository.findById(userId).get()).build();

			userTechStackRepository.save(userTechStack);
		}

		return "SUCCESS";
	}
}
