package com.ssafy.moamoa;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssafy.moamoa.service.InitService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitDb {

	@Autowired
	private InitService initService;

	@PostConstruct
	public void init() throws Exception {
		initService.addTechstackCatagory();
		initService.addArea();
		initService.addUser();
		initService.addProject();
	}
}
