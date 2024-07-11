package com.LMS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lms.controller.request.UserLoginRequest;

import com.lms.controller.response.UserResponse;
import com.lms.repository.UserRepository;
import com.lms.repository.entity.User;
import com.lms.service.LogService;
import com.lms.service.UserServiceImp;

@ExtendWith(MockitoExtension.class)

public class FindValidUser {
	  @Mock
	    private UserRepository userRepository;

	    @Mock
	    private LogService logService;

	    @InjectMocks
	    private UserServiceImp userService;

	    private UserLoginRequest loginRequest;
	    private User user;

	    @BeforeEach
	    public void setUp() {
	        loginRequest = new UserLoginRequest();
	        loginRequest.setEmail("test@example.com");
	        loginRequest.setPassword("password");

	        user = new User();
	        user.setEmail("test@example.com");
	        user.setPassword("password");
	    }
 
	    @Test
	    public void testUserLogin_Success() {
	        when(userRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword())).thenReturn(user);
	        when(logService.logMessage("test@example.com")).thenReturn(" valid");

	        ResponseEntity<UserResponse> response = userService.userLogin(loginRequest);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals("Login Successfully", response.getBody().getMessage());
	        assertEquals(true, response.getBody().isSuccess());
	    }

	    @Test
	    public void testUserLogin_Failure() {
	        when(userRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword())).thenReturn(null);
	        when(logService.logMessage("Login Failed")).thenReturn("login failed");

	        ResponseEntity<UserResponse> response = userService.userLogin(loginRequest);

	        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	        assertEquals("Invalid Email or Password", response.getBody().getMessage());
	        assertEquals(false, response.getBody().isSuccess());
	    }
	}


