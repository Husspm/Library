package com.company.librarycrudservice.controller;

import com.company.librarycrudservice.dao.BookDaoJpaImpl;
import com.company.librarycrudservice.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookDaoJpaImpl dao;

    private <T> String writeToJson(T obj) throws Exception {
        return new ObjectMapper().writeValueAsString(obj);
    }

    private static Book inputBook, outputBook;

    static {
        inputBook = new Book("12321", "title", "author");
        outputBook = new Book("12321", "title", "author");
        outputBook.setId(1);
    }

    @Test
    public void readAllBooks() throws Exception {
        List<Book> allBooks = new ArrayList<>();
        allBooks.add(outputBook);

        when(dao.findAll()).thenReturn(allBooks);

        mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(writeToJson(allBooks)));
    }

    @Test
    public void createBook() throws Exception {

        when(dao.save(inputBook)).thenReturn(outputBook);

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(writeToJson(inputBook)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(writeToJson(outputBook)));
    }

    @Test
    public void updateBook() throws Exception {
        mockMvc.perform(put("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(writeToJson(outputBook)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void readBook() throws Exception {
        when(dao.findById(1)).thenReturn(Optional.ofNullable(outputBook));

        mockMvc.perform(get("/books/" + 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(writeToJson(outputBook)));
    }

    @Test
    public void deleteBook() throws Exception {
        mockMvc.perform(delete("/books/"+1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void testSaveBookWillFailIfInfoIsMissing() throws Exception {
        Book fail = new Book();

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(writeToJson(fail)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}