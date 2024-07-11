package com.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.repository.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByEmailAndPassword(String email,String password); 
	public User findByEmail(String email);


}
