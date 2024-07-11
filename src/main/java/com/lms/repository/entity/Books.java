package com.lms.repository.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Books {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int book_id;
	private int serialNo;
	@NotBlank(message = "Book Name is Required")
	private String book_Name;
	@NotBlank(message = "Author Name is Required")
	private String author_Name;
	@JsonIgnore
	private int bookQty;
	private String description;

	@ManyToMany(mappedBy = "bookList")
	@JsonIgnoreProperties("bookList")
	@JsonIgnore
	private List<User> user;

}
