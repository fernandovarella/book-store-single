package com.fernando.bookstore.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import com.fernando.bookstore.api.JsonApiExceptionHandler;
import com.fernando.bookstore.data.model.Book;
import com.fernando.bookstore.exception.EntityNotFoundException;
import com.fernando.bookstore.service.BookService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import io.restassured.http.ContentType;

@WebMvcTest
@ActiveProfiles("test")
@TestPropertySource(properties="spring.profiles.active=dev")
public class BookControllerTest {

    @Autowired
    BookController bookController;

    @MockBean
    BookService bookService;
    
    @InjectMocks
    private JsonApiExceptionHandler jsonApiExceptionHandler;

    @BeforeAll
    public static void setupAll() {
        System.setProperty("spring.profiles.active", "dev");
    }

    @BeforeEach
    public void setup() {
        standaloneSetup(bookController, jsonApiExceptionHandler);
        ReflectionTestUtils.setField(jsonApiExceptionHandler, "activeProfile", "dev");

        //
        // when(bookService.getAll())
    }

    @Test
    public void should_Ok_When_Ret_getById() {


        when(this.bookService.get("5f738dcca5ba8036305fa2e5"))
            .thenReturn(Book.builder().build());


        given()
            .accept(ContentType.JSON)
        .when()
            .get("v1/books/{bookId}", "5f738dcca5ba8036305fa2e5")
        .then()
            .statusCode(HttpStatus.OK.value());
    }

    
    @Test
    public void should_404_When_getById() {

        when(this.bookService.get("non-existent"))
            .thenThrow(new EntityNotFoundException());

        given()
            .accept(ContentType.JSON)
        .when()
            .get("v1/books/{bookId}", "non-existent")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
            
    }

}
