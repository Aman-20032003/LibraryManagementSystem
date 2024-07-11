package com.lms.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.controller.request.ForgotPasswordRequest;
import com.lms.controller.request.RemoveUserRequest;
import com.lms.controller.request.UserLoginRequest;
import com.lms.controller.request.UserRegistrationRequest;
import com.lms.controller.response.UserResponse;
import com.lms.repository.UserRepository;
import com.lms.repository.entity.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImp implements IuserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LogService logService;

	public ResponseEntity<UserResponse> userRegisteration(UserRegistrationRequest registrationRequest) {
		Pattern p = Pattern.compile("^[a-z0-9]+@[a-z]+\\.[a-z]{2,}$");
		Matcher m = p.matcher(registrationRequest.getEmail());

		if (!m.find()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new UserResponse("Invalid Email Format Or Email Must Not Be Empty", false));
		}
		User user1 = userRepository.findByEmail(registrationRequest.getEmail());
		if (user1 != null) {
			log.warn(logService
					.logMessage("User " + registrationRequest.getEmail() + " Try To Registeration But Already Exists"));
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new UserResponse("User Already Exists", false));
		}
		User user = User.builder().email(registrationRequest.getEmail()).contact_No(registrationRequest.getContact_No())
				.password(registrationRequest.getPassword()).username(registrationRequest.getUsername()).build();

		if (user != null) {
			log.info(logService.logMessage("User " + registrationRequest.getEmail() + " Registration Successfully"));
			userRepository.save(user);
			return ResponseEntity.status(HttpStatus.OK).body(new UserResponse("User Registration Successfully", true));
		}
		log.warn(logService.logMessage("User Registration Failed"));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponse("User Registration Failed", false));
	}

	public ResponseEntity<UserResponse> userLogin(UserLoginRequest loginRequest) {
		User user = userRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
		if (user != null) {
			log.info(logService.logMessage(loginRequest.getEmail()) + " Login Successfully");
			return ResponseEntity.status(HttpStatus.OK).body(new UserResponse("Login Successfully", true));
		}
		log.warn(logService.logMessage("Login Failed"));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserResponse("Invalid Email or Password", false));
	}

	public ResponseEntity<UserResponse> forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
		User user = userRepository.findByEmail(forgotPasswordRequest.getEmail());
		if (user == null) {
			log.warn(logService
					.logMessage("User Forget Password With Invalid Email " + forgotPasswordRequest.getEmail()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponse("Invalid Email", false));
		}
		if (!forgotPasswordRequest.getNewPassword().equals(forgotPasswordRequest.getConfirmPassword())) {
			log.warn(logService
					.logMessage("New Password And Confirm Password Does Not Match For Email " + user.getEmail()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new UserResponse("New Password And Confirm Password Does Not Match", false));
		}
		user.setPassword(forgotPasswordRequest.getNewPassword());
		userRepository.save(user);
		log.info(logService.logMessage("Password Changed Successfully For User " + user.getEmail()));
		return ResponseEntity.status(HttpStatus.OK).body(new UserResponse("Password Changed Successfully", true));
	}

	public ResponseEntity<UserResponse> removeUser(RemoveUserRequest removeUserRequest) {
		User user = userRepository.findByEmail(removeUserRequest.getUserEmail());
		if (user == null) {
			log.warn(logService.logMessage("User Not Found With Email" + removeUserRequest.getUserEmail()));
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new UserResponse("Email Does Not Exist ! ..Invalid Email", false));
		}
		userRepository.delete(user);
		log.info("User removed successfully for email: " + removeUserRequest.getUserEmail());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserResponse("User Removed Successfully", true));
	}

	public List<User> getAllUsers() {
		log.info(logService.logMessage("Admin Display User List"));
		return userRepository.findAll();

	}

}
