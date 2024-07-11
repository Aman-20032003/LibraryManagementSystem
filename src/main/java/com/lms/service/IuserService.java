package com.lms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.controller.request.ForgotPasswordRequest;
import com.lms.controller.request.RemoveUserRequest;
import com.lms.controller.request.UserLoginRequest;
import com.lms.controller.request.UserRegistrationRequest;
import com.lms.controller.response.UserResponse;
import com.lms.repository.entity.User;

public interface IuserService {
	public ResponseEntity<UserResponse> userRegisteration(UserRegistrationRequest registrationRequest);
	
	public ResponseEntity<UserResponse> userLogin(UserLoginRequest loginRequest );
	
	public ResponseEntity<UserResponse> forgotPassword(ForgotPasswordRequest forgotPasswordRequest) ;
	
	public ResponseEntity<UserResponse> removeUser(RemoveUserRequest removeUserRequest);
	
	public List<User>getAllUsers();

}
