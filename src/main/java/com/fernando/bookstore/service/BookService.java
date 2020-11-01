package com.fernando.bookstore.service;

import com.fernando.bookstore.data.model.Book;

import org.springframework.data.domain.Page;

public interface BookService {

    public Book get(String id);

    public Page<Book> getAll();

    public Page<Book> getAll(int page, int size, String... sort);

    public Book create(Book book);
}
