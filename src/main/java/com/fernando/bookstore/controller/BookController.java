package com.fernando.bookstore.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fernando.bookstore.data.model.Book;
import com.fernando.bookstore.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(path = "v1/books", produces = APPLICATION_JSON_VALUE)
public class BookController {

	@Autowired
	private BookService bookService;
	

	@GetMapping(path = "/{bookId}")
	public ResponseEntity<Book> getBook(@PathVariable(value = "bookId") String bookId) {
		return ResponseEntity.ok(bookService.get(bookId));
	}

	@GetMapping(path = "")
	public ResponseEntity<Page<Book>> getBooks(@RequestParam(defaultValue = "0", required = false) Integer page, 
							@RequestParam(defaultValue = "10", required = false) Integer size,
							@RequestParam(defaultValue = "id,asc", required = false) String[] sort
							) {
		// return ResponseEntity.ok(bookRepository.findAll(ControlllerUtil.buildPageableFromRequest(page, size, sort)));
		return ResponseEntity.ok(bookService.getAll(page, size, sort));
	}
	

    @PostMapping(path = "")
	public ResponseEntity<Book> create(@RequestBody Book book) {
		return ResponseEntity.ok(bookService.create(book));
	}
	

}
