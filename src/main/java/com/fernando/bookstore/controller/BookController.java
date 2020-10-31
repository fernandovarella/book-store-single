package com.fernando.bookstore.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Optional;

import javax.websocket.server.PathParam;

import com.fernando.bookstore.api.ControlllerUtil;
import com.fernando.bookstore.data.model.Book;
import com.fernando.bookstore.exception.EntityNotFoundException;
import com.fernando.bookstore.repository.BookRepository;

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
@RequestMapping(path = "api/v1/books", produces = APPLICATION_JSON_VALUE)
public class BookController {

    @Autowired
	private BookRepository bookRepository;
	

	@GetMapping(path = "/{bookId}")
	public ResponseEntity<Book> getBooks(@PathVariable(value = "bookId") String bookId) {
		Optional<Book> book = bookRepository.findById(bookId);
		if (book.isPresent()) {
			return ResponseEntity.ok(book.get());
		} else {
			throw new EntityNotFoundException();
		}
	}

	@GetMapping(path = "")
	public ResponseEntity<Page<Book>> getBooks(@RequestParam(defaultValue = "0", required = false) Integer page, 
							@RequestParam(defaultValue = "10", required = false) Integer size,
							@RequestParam(defaultValue = "id,asc", required = false) String[] sort
							) {
		return ResponseEntity.ok(bookRepository.findAll(ControlllerUtil.buildPageableFromRequest(page, size, sort)));
	}
	

    @PostMapping(path = "")
	public ResponseEntity<Book> create(@RequestBody Book book) {
		System.out.println("create owner " + book);

		System.out.println(book);
		// Book resp = bookRepository.save(book);
		// if (1==1) throw new RuntimeException("Erro de teste no controller");
		return ResponseEntity.ok(new Book());
	}
	

}
