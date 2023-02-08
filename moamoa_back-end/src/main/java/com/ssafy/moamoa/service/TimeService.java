package com.ssafy.moamoa.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

	// 현재 시간을 리턴 해줍니다.
	public LocalDateTime getCurrentTime() {
		LocalDateTime localDateTime = LocalDateTime.now();

		return localDateTime;

	}

	// 현재 시간을 formatting 해줘서 String으로 리턴해줍니다.
	public String parseCurrentTime(LocalDateTime localDateTime)
	{
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY.MM.dd HH:mm");

		return localDateTime.format(dateTimeFormatter);
	}

	public String parseCreatedTime(LocalDate localDate)
	{
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY.MM.dd HH:mm");

		return localDate.format(dateTimeFormatter);
	}




}


