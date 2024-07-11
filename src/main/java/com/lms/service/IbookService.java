package com.lms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.controller.request.AddBooksRequest;
import com.lms.controller.request.BorrowBookRequest;
import com.lms.controller.request.DisplayBorrowedBooksRequest;
import com.lms.controller.request.RemoveBooksRequest;
import com.lms.controller.request.ReturnBookRequest;
import com.lms.controller.response.BooksResponse;
import com.lms.controller.response.UserResponse;
import com.lms.repository.entity.Books;

public interface IbookService {

	public ResponseEntity<UserResponse> borrowbooks(BorrowBookRequest bookRequest);
	
	public List<Books>getAllBooks();
	
	public ResponseEntity<BooksResponse> addBooks(AddBooksRequest addBooksRequest);

	public ResponseEntity<?>showBorrowedBooks(DisplayBorrowedBooksRequest booksRequest) ;
	
	public ResponseEntity<BooksResponse> removeBooks(RemoveBooksRequest booksRequest);
	
	public ResponseEntity<BooksResponse> returnBooks(ReturnBookRequest bookRequest);
	
	
}
