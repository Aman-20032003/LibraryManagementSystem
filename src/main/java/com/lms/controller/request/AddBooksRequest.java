package com.lms.controller.request;

import lombok.Data;

@Data
public class AddBooksRequest {
	private int serial_No;
	private String book_Name;
	private String author_Name;
	private String description;
	private int bookQty;

}
