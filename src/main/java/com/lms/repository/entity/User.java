package com.lms.repository.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_Id;
	@NotEmpty(message = "User Name Must Not Be Empty")
	private String username;
	@NotEmpty(message = "Email Must Not Be Empty")
	private String email;
	@JsonIgnore
	@NotEmpty(message = "Password Must Not Be Empty")
	private String password;

	private long contact_No;
	@ManyToMany
	private List<Books> bookList;

}
