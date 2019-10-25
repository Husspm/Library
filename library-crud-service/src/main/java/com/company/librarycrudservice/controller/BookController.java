package com.company.librarycrudservice.controller;

import com.company.librarycrudservice.dao.BookDaoJpaImpl;
import com.company.librarycrudservice.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookDaoJpaImpl bookDao;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> readAllBooks() {
        return bookDao.findAll();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody @Valid Book book) {
        return bookDao.save(book);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@RequestBody @Valid Book book) {
        bookDao.save(book);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book readBook(@PathVariable int id) {
        return bookDao.findById(id).orElseThrow(()-> new NoSuchElementException("Could Not Find That Book"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable int id) {
        bookDao.deleteById(id);
    }
}
