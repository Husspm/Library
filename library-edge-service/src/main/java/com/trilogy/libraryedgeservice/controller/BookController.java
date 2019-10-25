package com.trilogy.libraryedgeservice.controller;

import com.trilogy.libraryedgeservice.model.Book;
import com.trilogy.libraryedgeservice.model.Isbns;
import com.trilogy.libraryedgeservice.service.BookServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookServiceLayer serviceLayer;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBooks() {
        return serviceLayer.getAllBooks();
    }

    @GetMapping("/checkout")
    @ResponseStatus(HttpStatus.OK)
    public String checkoutBook(@RequestBody Isbns isbns){
        return serviceLayer.checkOutBooks(isbns);
    }
}
