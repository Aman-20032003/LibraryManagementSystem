package com.lms.controller.request;

import lombok.Data;

@Data
public class UserRegistrationRequest {
	private String username;
	private String email;
	private String password;
	private long contact_No;


}
