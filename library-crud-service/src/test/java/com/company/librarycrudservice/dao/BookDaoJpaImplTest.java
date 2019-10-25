package com.company.librarycrudservice.dao;

import com.company.librarycrudservice.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookDaoJpaImplTest {

    @Autowired
    BookDaoJpaImpl bookDao;

    Book book, book2;

    @Before
    public void setUp() throws Exception {
        bookDao.deleteAll();
        book = new Book("12321", "title", "author");
        book2 = new Book("1234321", "title 2", "author 2");
    }

    @Test
    public void testSaveBook() {
        Book test = bookDao.save(book);
        assertNotEquals(0, test.getId());
    }

    @Test
    public void findOneBook() {
        book = bookDao.save(book);
        Optional<Book> isBook = bookDao.findById(book.getId());
        isBook.ifPresent(book1 -> assertEquals(book, book1));
    }

    @Test
    public void findAllBooks() {
        bookDao.save(book);
        bookDao.save(book2);
        assertEquals(2, bookDao.findAll().size());
    }

    @Test
    public void deleteBook() {
        book = bookDao.save(book);
        bookDao.deleteById(book.getId());
        Optional<Book> isGone = bookDao.findById(book.getId());
        assertFalse(isGone.isPresent());
    }

    @Test
    public void updateBook() {
        book = bookDao.save(book);
        book.setAuthor("NEW AUTHOR");
        bookDao.save(book);
        Optional<Book> testCase = bookDao.findById(book.getId());
        testCase.ifPresent(book1 -> assertEquals(book, book1));
    }
}