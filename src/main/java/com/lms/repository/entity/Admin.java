package com.lms.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
	@Id
	@NotEmpty(message="Email is Required")
	private String email;
	@NotEmpty(message="Password Required")
	private String password;

}
