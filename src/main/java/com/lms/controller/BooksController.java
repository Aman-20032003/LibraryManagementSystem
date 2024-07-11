package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.controller.request.AddBooksRequest;
import com.lms.controller.request.BorrowBookRequest;
import com.lms.controller.request.DisplayBorrowedBooksRequest;
import com.lms.controller.request.RemoveBooksRequest;
import com.lms.controller.request.ReturnBookRequest;
import com.lms.controller.response.BooksResponse;
import com.lms.controller.response.UserResponse;
import com.lms.repository.entity.Books;
import com.lms.service.IbookService;

import jakarta.validation.Valid;

@RestController
public class BooksController {

	@Autowired
private	IbookService bookService;
	
	@PostMapping("/AddBooks")
	public ResponseEntity<BooksResponse> addbooks(@Valid @RequestBody AddBooksRequest addBooksRequest) {
		return bookService.addBooks(addBooksRequest);
	}

	@GetMapping("/DisplayBooks")
	public List<Books> getAllBooks() {
		return bookService.getAllBooks();
	}

	@DeleteMapping("/RemoveBook")
	public ResponseEntity<BooksResponse> RemoveBooks(@Valid @RequestBody RemoveBooksRequest booksRequest) {
		return bookService.removeBooks(booksRequest);
	}
	
	@PostMapping("/BorrowBooks")
	public ResponseEntity<UserResponse> borrowBooks(@RequestBody @Valid BorrowBookRequest bookRequest) {
		return bookService.borrowbooks(bookRequest);
	}

	@DeleteMapping("/returnBooks")
	public ResponseEntity<BooksResponse> returnBooks(@RequestBody @Valid ReturnBookRequest bookRequest) {
		return bookService.returnBooks(bookRequest);
	}
	
	@GetMapping("/ShowBorrowBooks")
	public ResponseEntity<?> showBorrowedBooks(@Valid DisplayBorrowedBooksRequest showBorrowedBooks) {
		return bookService.showBorrowedBooks(showBorrowedBooks);
	}

}
