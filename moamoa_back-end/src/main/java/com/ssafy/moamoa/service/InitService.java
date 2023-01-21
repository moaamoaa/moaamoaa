package com.ssafy.moamoa.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.Category;
import com.ssafy.moamoa.domain.TechStack;
import com.ssafy.moamoa.repository.CatagoryRepository;
import com.ssafy.moamoa.repository.TechstackRepository;

import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class InitService {

	private final CatagoryRepository catagoryRepository;
	private final TechstackRepository techstackRepository;

	public void addCatagory() {
		Category categoryBack = new Category("BackEnd");
		catagoryRepository.save(categoryBack);

		Category categoryFront = new Category("FrontEnd");
		catagoryRepository.save(categoryFront);

		Category categoryMobile = new Category("Mobile");
		catagoryRepository.save(categoryMobile);

		Category categoryEtc = new Category("Etc");
		catagoryRepository.save(categoryEtc);
	}

	public void addTechstack() {
		// FrontEnd
		TechStack techStackJs = new TechStack("Javascript");
		techStackJs.setFront("yes");
		techstackRepository.save(techStackJs);

		TechStack techStackTs = new TechStack("Typescript");
		techStackTs.setFront("yes");
		techstackRepository.save(techStackTs);

		TechStack techStackReact = new TechStack("React");
		techStackReact.setFront("yes");
		techStackReact.setMobile("yes");
		techstackRepository.save(techStackReact);

		TechStack techStackVue = new TechStack("Vue");
		techStackVue.setFront("yes");
		techstackRepository.save(techStackVue);

		TechStack techStackAngular = new TechStack("Angular");
		techStackAngular.setFront("yes");
		techstackRepository.save(techStackAngular);

		TechStack techStackSvelte = new TechStack("Svelte");
		techStackSvelte.setFront("yes");
		techstackRepository.save(techStackSvelte);

		// BackEnd
		TechStack techStackC = new TechStack("C");
		techStackC.setBack("yes");
		techstackRepository.save(techStackC);

		TechStack techStackCsharp = new TechStack("C#");
		techStackCsharp.setBack("yes");
		techstackRepository.save(techStackCsharp);

		TechStack techStackCplus = new TechStack("C++");
		techStackCplus.setBack("yes");
		techstackRepository.save(techStackCplus);

		TechStack techStackJava = new TechStack("Java");
		techStackJava.setBack("yes");
		techstackRepository.save(techStackJava);

		TechStack techStackPython = new TechStack("Python");
		techStackPython.setBack("yes");
		techstackRepository.save(techStackPython);

		TechStack techStackKotlin = new TechStack("Kotlin");
		techStackKotlin.setBack("yes");
		techStackKotlin.setMobile("yes");
		techstackRepository.save(techStackKotlin);

		TechStack techStackGo = new TechStack("Go");
		techStackGo.setBack("yes");
		techstackRepository.save(techStackGo);

		TechStack techStackNestjs = new TechStack("Nest.js");
		techStackNestjs.setBack("yes");
		techstackRepository.save(techStackNestjs);

		TechStack techStackNodejs = new TechStack("Node.js");
		techStackNodejs.setBack("yes");
		techstackRepository.save(techStackNodejs);

		TechStack techStackDjango = new TechStack("Django");
		techStackDjango.setBack("yes");
		techstackRepository.save(techStackDjango);

		TechStack techStackSpring = new TechStack("Spring");
		techStackSpring.setBack("yes");
		techstackRepository.save(techStackSpring);

		TechStack techStackMysql = new TechStack("MySQL");
		techStackMysql.setBack("yes");
		techstackRepository.save(techStackMysql);

		TechStack techStackGraphQL = new TechStack("GraphQL");
		techStackGraphQL.setBack("yes");
		techstackRepository.save(techStackGraphQL);

		TechStack techStackRedis = new TechStack("Redis");
		techStackRedis.setBack("yes");
		techstackRepository.save(techStackRedis);

		TechStack techStackMongodb = new TechStack("MongoDB");
		techStackMongodb.setBack("yes");
		techstackRepository.save(techStackMongodb);

		TechStack techStackFirebase = new TechStack("Firebase");
		techStackFirebase.setBack("yes");
		techstackRepository.save(techStackFirebase);

		// Mobile
		TechStack techStackSwift = new TechStack("Swift");
		techStackSwift.setMobile("yes");
		techstackRepository.save(techStackSwift);

		TechStack techStackSwiftui = new TechStack("SwiftUI");
		techStackSwiftui.setMobile("yes");
		techstackRepository.save(techStackSwiftui);

		TechStack techStackFlutter = new TechStack("Flutter");
		techStackFlutter.setMobile("yes");
		techstackRepository.save(techStackFlutter);

		// Etc
		TechStack techStackAws = new TechStack("AWS");
		techStackAws.setEtc("yes");
		techstackRepository.save(techStackAws);

		TechStack techStackDocker = new TechStack("Docker");
		techStackDocker.setEtc("yes");
		techstackRepository.save(techStackDocker);

		TechStack techStackKubernetes = new TechStack("Kubernetes");
		techStackKubernetes.setEtc("yes");
		techstackRepository.save(techStackKubernetes);

		TechStack techStackJenkins = new TechStack("Jenkins");
		techStackJenkins.setEtc("yes");
		techstackRepository.save(techStackJenkins);

		TechStack techStackFigma = new TechStack("Figma");
		techStackFigma.setEtc("yes");
		techstackRepository.save(techStackFigma);

		TechStack techStackGit = new TechStack("Git");
		techStackGit.setEtc("yes");
		techstackRepository.save(techStackGit);

		TechStack techStackUnity = new TechStack("Unity");
		techStackUnity.setEtc("yes");
		techstackRepository.save(techStackUnity);

	}
}
