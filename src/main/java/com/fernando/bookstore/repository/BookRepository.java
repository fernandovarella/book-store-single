package com.fernando.bookstore.repository;


import com.fernando.bookstore.data.model.Book;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book, String> {
    
    public Book findByTitle(String title);
}
