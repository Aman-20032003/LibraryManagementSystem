package com.lms.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.repository.LogRepository;
import com.lms.repository.entity.LogEntity;

@Service
public class LogService {
@Autowired
 private  LogRepository logRepository;

	public String logMessage(String message) {
	LocalDateTime localDateTime=LocalDateTime.now();
	DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:ms");
	String formatedDate=localDateTime.format(dateTimeFormatter);
	LogEntity logEntity= LogEntity.builder().message(message).timestamp(formatedDate).build();
	logRepository.save(logEntity);
	return message;
	}
}
