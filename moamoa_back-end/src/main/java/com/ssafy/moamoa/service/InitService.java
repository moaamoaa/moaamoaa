package com.ssafy.moamoa.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.Area;
import com.ssafy.moamoa.domain.Category;
import com.ssafy.moamoa.domain.TechStack;
import com.ssafy.moamoa.domain.TechStackCategory;
import com.ssafy.moamoa.repository.AreaRepository;
import com.ssafy.moamoa.repository.CategoryRepository;
import com.ssafy.moamoa.repository.TechStackCategoryRepository;
import com.ssafy.moamoa.repository.TechStackRepository;

import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class InitService {

	private final CategoryRepository categoryRepository;
	private final TechStackRepository techstackRepository;
	private final AreaRepository areaRepository;
	private final TechStackCategoryRepository techStackCategoryRepository;
	private final UserService userService;

	public void addTechstackCatagory() {
		//==Category==//
		Category categoryBack = new Category("BackEnd");
		categoryRepository.save(categoryBack);

		Category categoryFront = new Category("FrontEnd");
		categoryRepository.save(categoryFront);

		Category categoryMobile = new Category("Mobile");
		categoryRepository.save(categoryMobile);

		Category categoryEtc = new Category("Etc");
		categoryRepository.save(categoryEtc);

		//==Techstack==//
		// BackEnd
		TechStack techStackC = new TechStack("C");
		techstackRepository.save(techStackC);

		TechStack techStackCsharp = new TechStack("C#");
		techstackRepository.save(techStackCsharp);

		TechStack techStackCplus = new TechStack("C++");
		techstackRepository.save(techStackCplus);

		TechStack techStackJava = new TechStack("Java");
		techstackRepository.save(techStackJava);

		TechStack techStackPython = new TechStack("Python");
		techstackRepository.save(techStackPython);

		TechStack techStackKotlin = new TechStack("Kotlin");
		techstackRepository.save(techStackKotlin);

		TechStack techStackGo = new TechStack("Go");
		techstackRepository.save(techStackGo);

		TechStack techStackNestjs = new TechStack("Nest.js");
		techstackRepository.save(techStackNestjs);

		TechStack techStackNodejs = new TechStack("Node.js");
		techstackRepository.save(techStackNodejs);

		TechStack techStackDjango = new TechStack("Django");
		techstackRepository.save(techStackDjango);

		TechStack techStackSpring = new TechStack("Spring");
		techstackRepository.save(techStackSpring);

		TechStack techStackMysql = new TechStack("MySQL");
		techstackRepository.save(techStackMysql);

		TechStack techStackGraphQL = new TechStack("GraphQL");
		techstackRepository.save(techStackGraphQL);

		TechStack techStackRedis = new TechStack("Redis");
		techstackRepository.save(techStackRedis);

		TechStack techStackMongodb = new TechStack("MongoDB");
		techstackRepository.save(techStackMongodb);

		TechStack techStackFirebase = new TechStack("Firebase");
		techstackRepository.save(techStackFirebase);

		// FrontEnd
		TechStack techStackJs = new TechStack("Javascript");
		techstackRepository.save(techStackJs);

		TechStack techStackTs = new TechStack("Typescript");
		techstackRepository.save(techStackTs);

		TechStack techStackReact = new TechStack("React");
		techstackRepository.save(techStackReact);

		TechStack techStackVue = new TechStack("Vue");
		techstackRepository.save(techStackVue);

		TechStack techStackAngular = new TechStack("Angular");
		techstackRepository.save(techStackAngular);

		TechStack techStackSvelte = new TechStack("Svelte");
		techstackRepository.save(techStackSvelte);

		// Mobile
		TechStack techStackSwift = new TechStack("Swift");
		techstackRepository.save(techStackSwift);

		TechStack techStackSwiftui = new TechStack("SwiftUI");
		techstackRepository.save(techStackSwiftui);

		TechStack techStackFlutter = new TechStack("Flutter");
		techstackRepository.save(techStackFlutter);

		// Etc
		TechStack techStackAws = new TechStack("AWS");
		techstackRepository.save(techStackAws);

		TechStack techStackDocker = new TechStack("Docker");
		techstackRepository.save(techStackDocker);

		TechStack techStackKubernetes = new TechStack("Kubernetes");
		techstackRepository.save(techStackKubernetes);

		TechStack techStackJenkins = new TechStack("Jenkins");
		techstackRepository.save(techStackJenkins);

		TechStack techStackFigma = new TechStack("Figma");
		techstackRepository.save(techStackFigma);

		TechStack techStackGit = new TechStack("Git");
		techstackRepository.save(techStackGit);

		TechStack techStackUnity = new TechStack("Unity");
		techstackRepository.save(techStackUnity);

		//==TechstackCategory==//
		// BackEnd
		TechStackCategory techStackCategoryB1 = new TechStackCategory();
		techStackCategoryB1.setCategory(categoryBack);
		techStackCategoryB1.setTechstack(techStackC);
		techStackCategoryRepository.save(techStackCategoryB1);

		TechStackCategory techStackCategoryB2 = new TechStackCategory();
		techStackCategoryB2.setCategory(categoryBack);
		techStackCategoryB2.setTechstack(techStackCsharp);
		techStackCategoryRepository.save(techStackCategoryB2);

		TechStackCategory techStackCategoryB3 = new TechStackCategory();
		techStackCategoryB3.setCategory(categoryBack);
		techStackCategoryB3.setTechstack(techStackCplus);
		techStackCategoryRepository.save(techStackCategoryB3);

		TechStackCategory techStackCategoryB4 = new TechStackCategory();
		techStackCategoryB4.setCategory(categoryBack);
		techStackCategoryB4.setTechstack(techStackJava);
		techStackCategoryRepository.save(techStackCategoryB4);

		TechStackCategory techStackCategoryB5 = new TechStackCategory();
		techStackCategoryB5.setCategory(categoryBack);
		techStackCategoryB5.setTechstack(techStackPython);
		techStackCategoryRepository.save(techStackCategoryB5);

		TechStackCategory techStackCategoryB6 = new TechStackCategory();
		techStackCategoryB6.setCategory(categoryBack);
		techStackCategoryB6.setTechstack(techStackKotlin);
		techStackCategoryRepository.save(techStackCategoryB6);

		TechStackCategory techStackCategoryB7 = new TechStackCategory();
		techStackCategoryB7.setCategory(categoryBack);
		techStackCategoryB7.setTechstack(techStackGo);
		techStackCategoryRepository.save(techStackCategoryB7);

		TechStackCategory techStackCategoryB8 = new TechStackCategory();
		techStackCategoryB8.setCategory(categoryBack);
		techStackCategoryB8.setTechstack(techStackNestjs);
		techStackCategoryRepository.save(techStackCategoryB8);

		TechStackCategory techStackCategoryB9 = new TechStackCategory();
		techStackCategoryB9.setCategory(categoryBack);
		techStackCategoryB9.setTechstack(techStackNodejs);
		techStackCategoryRepository.save(techStackCategoryB9);

		TechStackCategory techStackCategoryB10 = new TechStackCategory();
		techStackCategoryB10.setCategory(categoryBack);
		techStackCategoryB10.setTechstack(techStackDjango);
		techStackCategoryRepository.save(techStackCategoryB10);

		TechStackCategory techStackCategoryB11 = new TechStackCategory();
		techStackCategoryB11.setCategory(categoryBack);
		techStackCategoryB11.setTechstack(techStackSpring);
		techStackCategoryRepository.save(techStackCategoryB11);

		TechStackCategory techStackCategoryB12 = new TechStackCategory();
		techStackCategoryB12.setCategory(categoryBack);
		techStackCategoryB12.setTechstack(techStackMysql);
		techStackCategoryRepository.save(techStackCategoryB12);

		TechStackCategory techStackCategoryB13 = new TechStackCategory();
		techStackCategoryB13.setCategory(categoryBack);
		techStackCategoryB13.setTechstack(techStackGraphQL);
		techStackCategoryRepository.save(techStackCategoryB13);

		TechStackCategory techStackCategoryB14 = new TechStackCategory();
		techStackCategoryB14.setCategory(categoryBack);
		techStackCategoryB14.setTechstack(techStackRedis);
		techStackCategoryRepository.save(techStackCategoryB14);

		TechStackCategory techStackCategoryB15 = new TechStackCategory();
		techStackCategoryB15.setCategory(categoryBack);
		techStackCategoryB15.setTechstack(techStackMongodb);
		techStackCategoryRepository.save(techStackCategoryB15);

		TechStackCategory techStackCategoryB16 = new TechStackCategory();
		techStackCategoryB16.setCategory(categoryBack);
		techStackCategoryB16.setTechstack(techStackFirebase);
		techStackCategoryRepository.save(techStackCategoryB16);

		// FrontEnd
		TechStackCategory techStackCategoryF1 = new TechStackCategory();
		techStackCategoryF1.setCategory(categoryFront);
		techStackCategoryF1.setTechstack(techStackJs);
		techStackCategoryRepository.save(techStackCategoryF1);

		TechStackCategory techStackCategoryF2 = new TechStackCategory();
		techStackCategoryF2.setCategory(categoryFront);
		techStackCategoryF2.setTechstack(techStackTs);
		techStackCategoryRepository.save(techStackCategoryF2);

		TechStackCategory techStackCategoryF3 = new TechStackCategory();
		techStackCategoryF3.setCategory(categoryFront);
		techStackCategoryF3.setTechstack(techStackReact);
		techStackCategoryRepository.save(techStackCategoryF3);

		TechStackCategory techStackCategoryF4 = new TechStackCategory();
		techStackCategoryF4.setCategory(categoryFront);
		techStackCategoryF4.setTechstack(techStackVue);
		techStackCategoryRepository.save(techStackCategoryF4);

		TechStackCategory techStackCategoryF5 = new TechStackCategory();
		techStackCategoryF5.setCategory(categoryFront);
		techStackCategoryF5.setTechstack(techStackAngular);
		techStackCategoryRepository.save(techStackCategoryF5);

		TechStackCategory techStackCategoryF6 = new TechStackCategory();
		techStackCategoryF6.setCategory(categoryFront);
		techStackCategoryF6.setTechstack(techStackSvelte);
		techStackCategoryRepository.save(techStackCategoryF6);

		// Mobile
		TechStackCategory techStackCategoryM1 = new TechStackCategory();
		techStackCategoryM1.setCategory(categoryMobile);
		techStackCategoryM1.setTechstack(techStackSwift);
		techStackCategoryRepository.save(techStackCategoryM1);

		TechStackCategory techStackCategoryM2 = new TechStackCategory();
		techStackCategoryM2.setCategory(categoryMobile);
		techStackCategoryM2.setTechstack(techStackSwiftui);
		techStackCategoryRepository.save(techStackCategoryM2);

		TechStackCategory techStackCategoryM3 = new TechStackCategory();
		techStackCategoryM3.setCategory(categoryMobile);
		techStackCategoryM3.setTechstack(techStackKotlin);
		techStackCategoryRepository.save(techStackCategoryM3);

		TechStackCategory techStackCategoryM4 = new TechStackCategory();
		techStackCategoryM4.setCategory(categoryMobile);
		techStackCategoryM4.setTechstack(techStackReact);
		techStackCategoryRepository.save(techStackCategoryM4);

		TechStackCategory techStackCategoryM5 = new TechStackCategory();
		techStackCategoryM5.setCategory(categoryMobile);
		techStackCategoryM5.setTechstack(techStackFlutter);
		techStackCategoryRepository.save(techStackCategoryM5);

		// Etc
		TechStackCategory techStackCategoryE1 = new TechStackCategory();
		techStackCategoryE1.setCategory(categoryEtc);
		techStackCategoryE1.setTechstack(techStackAws);
		techStackCategoryRepository.save(techStackCategoryE1);

		TechStackCategory techStackCategoryE2 = new TechStackCategory();
		techStackCategoryE2.setCategory(categoryEtc);
		techStackCategoryE2.setTechstack(techStackDocker);
		techStackCategoryRepository.save(techStackCategoryE2);

		TechStackCategory techStackCategoryE3 = new TechStackCategory();
		techStackCategoryE3.setCategory(categoryEtc);
		techStackCategoryE3.setTechstack(techStackKubernetes);
		techStackCategoryRepository.save(techStackCategoryE3);

		TechStackCategory techStackCategoryE4 = new TechStackCategory();
		techStackCategoryE4.setCategory(categoryEtc);
		techStackCategoryE4.setTechstack(techStackJenkins);
		techStackCategoryRepository.save(techStackCategoryE4);

		TechStackCategory techStackCategoryE5 = new TechStackCategory();
		techStackCategoryE5.setCategory(categoryEtc);
		techStackCategoryE5.setTechstack(techStackFigma);
		techStackCategoryRepository.save(techStackCategoryE5);

		TechStackCategory techStackCategoryE6 = new TechStackCategory();
		techStackCategoryE6.setCategory(categoryEtc);
		techStackCategoryE6.setTechstack(techStackGit);
		techStackCategoryRepository.save(techStackCategoryE6);

		TechStackCategory techStackCategoryE7 = new TechStackCategory();
		techStackCategoryE7.setCategory(categoryEtc);
		techStackCategoryE7.setTechstack(techStackUnity);
		techStackCategoryRepository.save(techStackCategoryE7);

	}

	public void addArea() {
		Area areaSeoul = new Area("서울특별시");
		areaRepository.save(areaSeoul);

		Area areaBusan = new Area("부산광역시");
		areaRepository.save(areaBusan);

		Area areaDaegu = new Area("대구광역시");
		areaRepository.save(areaDaegu);

		Area areaIncheon = new Area("인천광역시");
		areaRepository.save(areaIncheon);

		Area areaGwangju = new Area("광주광역시");
		areaRepository.save(areaGwangju);

		Area areaDaejeon = new Area("대전광역시");
		areaRepository.save(areaDaejeon);

		Area areaUlsan = new Area("울산광역시");
		areaRepository.save(areaUlsan);

		Area areaSejong = new Area("세종특별자치시");
		areaRepository.save(areaSejong);

		Area areaGyeonggi = new Area("경기도");
		areaRepository.save(areaGyeonggi);

		Area areaGangwon = new Area("강원도");
		areaRepository.save(areaGangwon);

		Area areaChungcheongbuk = new Area("충청북도");
		areaRepository.save(areaChungcheongbuk);

		Area areaChungcheongnam = new Area("충청남도");
		areaRepository.save(areaChungcheongnam);

		Area areaJeollabuk = new Area("전라북도");
		areaRepository.save(areaJeollabuk);

		Area areaJeollanam = new Area("전라남도");
		areaRepository.save(areaJeollanam);

		Area areaGyeongsangbuk = new Area("경상북도");
		areaRepository.save(areaGyeongsangbuk);

		Area areaGyeongsangnam = new Area("경상남도");
		areaRepository.save(areaGyeongsangnam);

		Area areaJeju = new Area("제주특별자치시");
		areaRepository.save(areaJeju);
	}

	public void addUser() {
		userService.signup("ssafy@ssafy.com", "s@12341234", "김싸피");
	}
}
