package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.controller.request.ForgotPasswordRequest;
import com.lms.controller.request.RemoveUserRequest;
import com.lms.controller.request.UserLoginRequest;
import com.lms.controller.request.UserRegistrationRequest;
import com.lms.controller.response.UserResponse;
import com.lms.repository.entity.User;
import com.lms.service.IuserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/User")
public class UserController {
	@Autowired
private IuserService userService;

	@PostMapping("/Registration")
	public ResponseEntity<UserResponse> registrationUser(@Valid @RequestBody UserRegistrationRequest registrationRequest) {
		return userService.userRegisteration(registrationRequest);
	}

	@PostMapping("/Login")
	public ResponseEntity<UserResponse> userLogin(@Valid @RequestBody UserLoginRequest loginRequest) {
		return userService.userLogin(loginRequest);
	}


	@PutMapping("/ForgetPassword")
	public ResponseEntity<UserResponse> forgetpassword(@RequestBody @Valid ForgotPasswordRequest forgotPasswordRequest) {
		return userService.forgotPassword(forgotPasswordRequest);
	}
	@GetMapping("/allUsers")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@DeleteMapping("/RemoveUser")
	public ResponseEntity<UserResponse> removeUser(@RequestBody RemoveUserRequest removeUserRequest) {
		return userService.removeUser(removeUserRequest);
	}
	
}
