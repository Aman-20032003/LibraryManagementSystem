package com.lms.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DisplayUsersRequest {
private	String email;
private String userName;
private Long contactNo;

}
