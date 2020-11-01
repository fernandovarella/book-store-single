package com.fernando.bookstore.service;

import com.fernando.bookstore.api.RepositoryUtil;
import com.fernando.bookstore.data.model.Book;
import com.fernando.bookstore.exception.EntityNotFoundException;
import com.fernando.bookstore.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book get(String id) {
        return bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    
    @Override
    public Page<Book> getAll() {
        return getAll(0, 10, "id,asc");
    }

    @Override
    public Page<Book> getAll(int page, int size, String... sort) {
        return bookRepository.findAll(RepositoryUtil.buildPageableFromRequest(page, size, sort));
    }

    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }
}
