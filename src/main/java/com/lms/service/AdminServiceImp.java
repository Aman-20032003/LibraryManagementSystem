package com.lms.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.controller.request.AdminLoginRequest;
import com.lms.controller.response.AdminLoginResponse;
import com.lms.repository.AdminRepository;
import com.lms.repository.entity.Admin;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminServiceImp implements IadminService {
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private LogService logService;

	public ResponseEntity<AdminLoginResponse> adminLogin(AdminLoginRequest adminLoginRequest) {
		
		Admin adminLogin = adminRepository.findUserByEmailAndPassword(adminLoginRequest.getEmail(),
				adminLoginRequest.getPassword());
		if (adminLogin != null) {
			log.info(logService.logMessage("Admin Login Successfully"));
			return ResponseEntity.ok(new AdminLoginResponse("Admin Login Successfully", true));
			}
		 else {
			log.warn(logService.logMessage("Admin Login Failed ! Invalid Credentials"));
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new AdminLoginResponse("Login Failed ! Invalid Email or Password", false));
		}
	}

}
