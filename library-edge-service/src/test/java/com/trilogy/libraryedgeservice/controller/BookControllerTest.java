package com.trilogy.libraryedgeservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogy.libraryedgeservice.model.Book;
import com.trilogy.libraryedgeservice.model.Isbns;
import com.trilogy.libraryedgeservice.service.BookServiceLayer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BookServiceLayer bookService;

    private ObjectMapper mapper = new ObjectMapper();


    @Test
    public void getAllBooks() throws Exception{
        Book book1 = new Book(
                1, "123", "title", "guy"
        );
        Book book2 = new Book(
                2, "234", "another", "girl"
        );
        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);

        when(bookService.getAllBooks()).thenReturn(bookList);

        List<Book> bookList2 = new ArrayList<>();
        bookList2.addAll(bookList);

        String outputJson = mapper.writeValueAsString(bookList2);

        this.mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void checkoutBook() throws Exception{
        Book book1 = new Book(
                1, "123", "title", "guy"
        );
        Book book2 = new Book(
                2, "234", "another", "girl"
        );
        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);

        when(bookService.getAllBooks()).thenReturn(bookList);

        List<String> isbs = new ArrayList<>();
        isbs.add("123");
        isbs.add("234");
        Isbns isbns = new Isbns(
                isbs, "Monday"
        );

        String inputJson = mapper.writeValueAsString(isbns);

        String success = "You have successfully checked out all your books";

        when(bookService.checkOutBooks(isbns)).thenReturn(success);

        String outputJson = mapper.writeValueAsString(success);

        this.mockMvc.perform(get("/books/checkout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(success));

    }
}