package com.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.controller.request.AddBooksRequest;
import com.lms.controller.request.BorrowBookRequest;
import com.lms.controller.request.DisplayBorrowedBooksRequest;
import com.lms.controller.request.RemoveBooksRequest;
import com.lms.controller.request.ReturnBookRequest;
import com.lms.controller.response.BooksResponse;
import com.lms.controller.response.UserResponse;
import com.lms.repository.BookRepository;
import com.lms.repository.UserRepository;
import com.lms.repository.entity.Books;
import com.lms.repository.entity.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookServiceImp implements IbookService {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LogService logService;

	public List<Books> getAllBooks() {
		return bookRepository.findAll();
	}

	public ResponseEntity<BooksResponse> addBooks(AddBooksRequest addBooksRequest) {
		Books book1 = bookRepository.findBySerialNo(addBooksRequest.getSerial_No());
		Books book = Books.builder().description(addBooksRequest.getDescription()).bookQty(addBooksRequest.getBookQty())
				.serialNo(addBooksRequest.getSerial_No()).author_Name(addBooksRequest.getAuthor_Name())
				.book_Name(addBooksRequest.getBook_Name()).build();
		if (book == null) {
			log.warn(logService.logMessage("Invalid Request ! Failed To Add A Book By Admin"));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BooksResponse("Invalid Request", false));
		}
		if (addBooksRequest.getSerial_No() == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BooksResponse("Serial No required ", false));

		}
		if (book1 == null) {
			bookRepository.save(book);
			log.warn(logService.logMessage("Book Added Successfully By Admin"));
			return ResponseEntity.status(HttpStatus.OK).body(new BooksResponse("Book Added Successfully", true));
		}
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new BooksResponse("Serial No Already Exists! try With New SerialNo", false));
	}

	public ResponseEntity<UserResponse> borrowbooks(BorrowBookRequest bookRequest) {
		User user = userRepository.findByEmail(bookRequest.getEmail());
		if (user == null) {
			log.warn(logService.logMessage("User not found for email: " + bookRequest.getEmail()));
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserResponse("Login First", false));
		}
		List<Books> books = bookRepository.findAllBySerialNoIn(bookRequest.getSerialNo());

		for (Books book : books) {
			int quntity = book.getBookQty();
			if (quntity == 0) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserResponse(
						"Book Is Not Available ! Try Later with Serial No " + book.getSerialNo(), false));
			}

			if (user.getBookList().contains(book)) {
				log.info(logService
						.logMessage("User Add A book But Already exists In Account " + bookRequest.getEmail()));
				return ResponseEntity.status(HttpStatus.CONFLICT).body(new UserResponse(
						"Book Already Exists In User Account With SerialNo:  " + book.getSerialNo(), false));
			}
		}

		books.forEach(book -> {
			book.setBookQty(book.getBookQty() - 1);
			bookRepository.save(book);
		});
		user.setBookList(books);

		userRepository.save(user);
		log.info("Books Add Successfully in: " + bookRequest.getEmail());
		return ResponseEntity.status(HttpStatus.OK).body(new UserResponse("Books Added Successfully", true));

	}

	public ResponseEntity<?> showBorrowedBooks(DisplayBorrowedBooksRequest booksRequest) {
		User user = userRepository.findByEmail(booksRequest.getEmail());

		if (user == null) {
			return ResponseEntity.badRequest().body("User Not Found ! With Email: " + booksRequest.getEmail());
		}
		List<Books> borrowedBooks = user.getBookList();
		if (borrowedBooks.equals(null)) {
			return ResponseEntity.badRequest().body("Books Not Found With Email: " + booksRequest.getEmail());
		}
		return ResponseEntity.ok(borrowedBooks);
	}

	public ResponseEntity<BooksResponse> removeBooks(RemoveBooksRequest booksRequest) {
		Books books = bookRepository.findBySerialNo(booksRequest.getSerial_No());
		if (books != null) {
			bookRepository.delete(books);
			log.info(logService.logMessage("Admin Removed Book Successfully"));
			return ResponseEntity.status(HttpStatus.OK).body(new BooksResponse("Book Removed Successsfully", true));

		} else {
			log.warn(logService.logMessage("Admin Remove Book Operation Failed ! serial No Does Not Exists"));

			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new BooksResponse("Serial No Does Not Exists", false));
		}

	}

	public ResponseEntity<BooksResponse> returnBooks(ReturnBookRequest bookRequest) {
		User user = userRepository.findByEmail(bookRequest.getEmail());
		if (user == null) {
			log.warn(logService.logMessage("User not found for email: " + bookRequest.getEmail()));
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new BooksResponse(" User Doesn't Exists! Login First", false));

		}
		List<Books> books = bookRepository.findAllBySerialNoIn(bookRequest.getSerialNo());
		for (Books book1 : books) {
			int bQuentity = book1.getBookQty();
			if (book1.equals(null)) {
				log.warn(logService.logMessage("User with email: " + bookRequest.getEmail()
						+ " does not have the book with serial number: " + book1.getSerialNo()));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						new BooksResponse("The user does not have a book With SerialNo " + book1.getSerialNo(), false));
			}
			book1.setBookQty(bQuentity + 1);
			bookRepository.save(book1);
		}
		user.getBookList().removeAll(books);
		userRepository.save(user);
		log.info(logService.logMessage("Book with serial number: " + bookRequest.getSerialNo()
				+ " returned successfully by user with email: " + bookRequest.getEmail()));

		return ResponseEntity.status(HttpStatus.OK).body(new BooksResponse("Book returned successfully", true));
	}

}
