package com.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.repository.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {
	public Admin findUserByEmailAndPassword(String email,String Password);

}
