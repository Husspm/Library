package com.trilogy.libraryedgeservice.service;

import com.trilogy.libraryedgeservice.model.Book;
import com.trilogy.libraryedgeservice.model.Isbns;
import com.trilogy.libraryedgeservice.util.feign.BookFeign;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookServiceLayerTest {

    private BookServiceLayer serviceLayer;
    private BookFeign bookFeign;

    private List<Book> bookList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        Book book1 = new Book(
                1, "123", "title", "guy"
        );
        Book book2 = new Book(
                2, "234", "another", "girl"
        );

        bookList.add(book1);
        bookList.add(book2);

        setUpFeignClientMock();
        serviceLayer = new BookServiceLayer(bookFeign);
    }

    private void setUpFeignClientMock() {
        bookFeign = mock(BookFeign.class);
        doReturn(bookList).when(bookFeign).getAllBooks();
    }

    @Test
    public void testGetAllBooks() {
        List<Book> expected = new ArrayList<>();
        expected.addAll(bookList);
        List<Book> actual = serviceLayer.getAllBooks();
        assertEquals(expected, actual);
    }

    @Test
    public void checkOutBooks() {
        List<String> isbs = new ArrayList<>();
        isbs.add("123");
        isbs.add("234");
        Isbns isbns = new Isbns(
                isbs, "Monday"
        );
        String actual = serviceLayer.checkOutBooks(isbns);
        assertEquals("You have successfully checked out all your books",actual);

    }
}