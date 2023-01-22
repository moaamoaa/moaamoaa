package com.ssafy.moamoa.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.Area;
import com.ssafy.moamoa.domain.Category;
import com.ssafy.moamoa.domain.TechStack;
import com.ssafy.moamoa.domain.TechStackCategory;
import com.ssafy.moamoa.repository.AreaRepository;
import com.ssafy.moamoa.repository.CatagoryRepository;
import com.ssafy.moamoa.repository.TechstackCatagoryRepository;
import com.ssafy.moamoa.repository.TechstackRepository;

import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class InitService {

	private final CatagoryRepository catagoryRepository;
	private final TechstackRepository techstackRepository;
	private final AreaRepository areaRepository;
	private final TechstackCatagoryRepository techstackCatagoryRepository;

	public void addTechstackCatagory() {
		//==Category==//
		Category categoryBack = new Category("BackEnd");
		catagoryRepository.save(categoryBack);

		Category categoryFront = new Category("FrontEnd");
		catagoryRepository.save(categoryFront);

		Category categoryMobile = new Category("Mobile");
		catagoryRepository.save(categoryMobile);

		Category categoryEtc = new Category("Etc");
		catagoryRepository.save(categoryEtc);

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
		techstackCatagoryRepository.save(techStackCategoryB1);

		TechStackCategory techStackCategoryB2 = new TechStackCategory();
		techStackCategoryB2.setCategory(categoryBack);
		techStackCategoryB2.setTechstack(techStackCsharp);
		techstackCatagoryRepository.save(techStackCategoryB2);

		TechStackCategory techStackCategoryB3 = new TechStackCategory();
		techStackCategoryB3.setCategory(categoryBack);
		techStackCategoryB3.setTechstack(techStackCplus);
		techstackCatagoryRepository.save(techStackCategoryB3);

		TechStackCategory techStackCategoryB4 = new TechStackCategory();
		techStackCategoryB4.setCategory(categoryBack);
		techStackCategoryB4.setTechstack(techStackJava);
		techstackCatagoryRepository.save(techStackCategoryB4);

		TechStackCategory techStackCategoryB5 = new TechStackCategory();
		techStackCategoryB5.setCategory(categoryBack);
		techStackCategoryB5.setTechstack(techStackPython);
		techstackCatagoryRepository.save(techStackCategoryB5);

		TechStackCategory techStackCategoryB6 = new TechStackCategory();
		techStackCategoryB6.setCategory(categoryBack);
		techStackCategoryB6.setTechstack(techStackKotlin);
		techstackCatagoryRepository.save(techStackCategoryB6);

		TechStackCategory techStackCategoryB7 = new TechStackCategory();
		techStackCategoryB7.setCategory(categoryBack);
		techStackCategoryB7.setTechstack(techStackGo);
		techstackCatagoryRepository.save(techStackCategoryB7);

		TechStackCategory techStackCategoryB8 = new TechStackCategory();
		techStackCategoryB8.setCategory(categoryBack);
		techStackCategoryB8.setTechstack(techStackNestjs);
		techstackCatagoryRepository.save(techStackCategoryB8);

		TechStackCategory techStackCategoryB9 = new TechStackCategory();
		techStackCategoryB9.setCategory(categoryBack);
		techStackCategoryB9.setTechstack(techStackNodejs);
		techstackCatagoryRepository.save(techStackCategoryB9);

		TechStackCategory techStackCategoryB10 = new TechStackCategory();
		techStackCategoryB10.setCategory(categoryBack);
		techStackCategoryB10.setTechstack(techStackDjango);
		techstackCatagoryRepository.save(techStackCategoryB10);

		TechStackCategory techStackCategoryB11 = new TechStackCategory();
		techStackCategoryB11.setCategory(categoryBack);
		techStackCategoryB11.setTechstack(techStackSpring);
		techstackCatagoryRepository.save(techStackCategoryB11);

		TechStackCategory techStackCategoryB12 = new TechStackCategory();
		techStackCategoryB12.setCategory(categoryBack);
		techStackCategoryB12.setTechstack(techStackMysql);
		techstackCatagoryRepository.save(techStackCategoryB12);

		TechStackCategory techStackCategoryB13 = new TechStackCategory();
		techStackCategoryB13.setCategory(categoryBack);
		techStackCategoryB13.setTechstack(techStackGraphQL);
		techstackCatagoryRepository.save(techStackCategoryB13);

		TechStackCategory techStackCategoryB14 = new TechStackCategory();
		techStackCategoryB14.setCategory(categoryBack);
		techStackCategoryB14.setTechstack(techStackRedis);
		techstackCatagoryRepository.save(techStackCategoryB14);

		TechStackCategory techStackCategoryB15 = new TechStackCategory();
		techStackCategoryB15.setCategory(categoryBack);
		techStackCategoryB15.setTechstack(techStackMongodb);
		techstackCatagoryRepository.save(techStackCategoryB15);

		TechStackCategory techStackCategoryB16 = new TechStackCategory();
		techStackCategoryB16.setCategory(categoryBack);
		techStackCategoryB16.setTechstack(techStackFirebase);
		techstackCatagoryRepository.save(techStackCategoryB16);

		// FrontEnd
		TechStackCategory techStackCategoryF1 = new TechStackCategory();
		techStackCategoryF1.setCategory(categoryFront);
		techStackCategoryF1.setTechstack(techStackJs);
		techstackCatagoryRepository.save(techStackCategoryF1);

		TechStackCategory techStackCategoryF2 = new TechStackCategory();
		techStackCategoryF2.setCategory(categoryFront);
		techStackCategoryF2.setTechstack(techStackTs);
		techstackCatagoryRepository.save(techStackCategoryF2);

		TechStackCategory techStackCategoryF3 = new TechStackCategory();
		techStackCategoryF3.setCategory(categoryFront);
		techStackCategoryF3.setTechstack(techStackReact);
		techstackCatagoryRepository.save(techStackCategoryF3);

		TechStackCategory techStackCategoryF4 = new TechStackCategory();
		techStackCategoryF4.setCategory(categoryFront);
		techStackCategoryF4.setTechstack(techStackVue);
		techstackCatagoryRepository.save(techStackCategoryF4);

		TechStackCategory techStackCategoryF5 = new TechStackCategory();
		techStackCategoryF5.setCategory(categoryFront);
		techStackCategoryF5.setTechstack(techStackAngular);
		techstackCatagoryRepository.save(techStackCategoryF5);

		TechStackCategory techStackCategoryF6 = new TechStackCategory();
		techStackCategoryF6.setCategory(categoryFront);
		techStackCategoryF6.setTechstack(techStackSvelte);
		techstackCatagoryRepository.save(techStackCategoryF6);

		// Mobile
		TechStackCategory techStackCategoryM1 = new TechStackCategory();
		techStackCategoryM1.setCategory(categoryMobile);
		techStackCategoryM1.setTechstack(techStackSwift);
		techstackCatagoryRepository.save(techStackCategoryM1);

		TechStackCategory techStackCategoryM2 = new TechStackCategory();
		techStackCategoryM2.setCategory(categoryMobile);
		techStackCategoryM2.setTechstack(techStackSwiftui);
		techstackCatagoryRepository.save(techStackCategoryM2);

		TechStackCategory techStackCategoryM3 = new TechStackCategory();
		techStackCategoryM3.setCategory(categoryMobile);
		techStackCategoryM3.setTechstack(techStackKotlin);
		techstackCatagoryRepository.save(techStackCategoryM3);

		TechStackCategory techStackCategoryM4 = new TechStackCategory();
		techStackCategoryM4.setCategory(categoryMobile);
		techStackCategoryM4.setTechstack(techStackReact);
		techstackCatagoryRepository.save(techStackCategoryM4);

		TechStackCategory techStackCategoryM5 = new TechStackCategory();
		techStackCategoryM5.setCategory(categoryMobile);
		techStackCategoryM5.setTechstack(techStackFlutter);
		techstackCatagoryRepository.save(techStackCategoryM5);

		// Etc
		TechStackCategory techStackCategoryE1 = new TechStackCategory();
		techStackCategoryE1.setCategory(categoryEtc);
		techStackCategoryE1.setTechstack(techStackAws);
		techstackCatagoryRepository.save(techStackCategoryE1);

		TechStackCategory techStackCategoryE2 = new TechStackCategory();
		techStackCategoryE2.setCategory(categoryEtc);
		techStackCategoryE2.setTechstack(techStackDocker);
		techstackCatagoryRepository.save(techStackCategoryE2);

		TechStackCategory techStackCategoryE3 = new TechStackCategory();
		techStackCategoryE3.setCategory(categoryEtc);
		techStackCategoryE3.setTechstack(techStackKubernetes);
		techstackCatagoryRepository.save(techStackCategoryE3);

		TechStackCategory techStackCategoryE4 = new TechStackCategory();
		techStackCategoryE4.setCategory(categoryEtc);
		techStackCategoryE4.setTechstack(techStackJenkins);
		techstackCatagoryRepository.save(techStackCategoryE4);

		TechStackCategory techStackCategoryE5 = new TechStackCategory();
		techStackCategoryE5.setCategory(categoryEtc);
		techStackCategoryE5.setTechstack(techStackFigma);
		techstackCatagoryRepository.save(techStackCategoryE5);

		TechStackCategory techStackCategoryE6 = new TechStackCategory();
		techStackCategoryE6.setCategory(categoryEtc);
		techStackCategoryE6.setTechstack(techStackGit);
		techstackCatagoryRepository.save(techStackCategoryE6);

		TechStackCategory techStackCategoryE7 = new TechStackCategory();
		techStackCategoryE7.setCategory(categoryEtc);
		techStackCategoryE7.setTechstack(techStackUnity);
		techstackCatagoryRepository.save(techStackCategoryE7);

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
}
