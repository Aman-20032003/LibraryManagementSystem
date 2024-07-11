package com.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.controller.request.AdminLoginRequest;
import com.lms.controller.response.AdminLoginResponse;
import com.lms.service.IadminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Admin")
public class AdminController {
	@Autowired
    private	IadminService adminService;

	@PostMapping("/Login")
	public ResponseEntity<AdminLoginResponse> Login(@Valid @RequestBody AdminLoginRequest adminLoginRequest) {
		return adminService.adminLogin(adminLoginRequest);
	}

	
	
}
