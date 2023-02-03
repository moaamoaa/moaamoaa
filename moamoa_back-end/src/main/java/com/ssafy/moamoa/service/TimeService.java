package com.ssafy.moamoa.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = false)

public class TimeService {
	private static final String SUCCESS = "SUCCESS";
	private static final String FAIL = "FAIL";

	private static TimeService timeService = new TimeService();

	public TimeService() {
	}

	static TimeService getInstance() {
		return timeService;
	}

	public LocalDateTime getCurrentTime() {
		LocalDateTime localDateTime = LocalDateTime.now();

		return localDateTime;

	}

}


