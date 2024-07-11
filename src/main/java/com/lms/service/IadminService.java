package com.lms.service;

import org.springframework.http.ResponseEntity;

import com.lms.controller.request.AdminLoginRequest;
import com.lms.controller.response.AdminLoginResponse;


public interface IadminService {
	public ResponseEntity<AdminLoginResponse> adminLogin(AdminLoginRequest adminLoginRequest);
}
