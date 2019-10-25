package com.trilogy.libraryedgeservice.util.feign;

import com.trilogy.libraryedgeservice.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "book-service")
public interface BookFeign {
    @PostMapping("/books")
    Book createBook(@RequestBody Book book);

    @GetMapping("/books/{id}")
    Book readBooks();

    @PutMapping("/books")
    void updateBook(@RequestBody Book book);

    @DeleteMapping("/books/{id}")
    void deleteBook();

    @GetMapping("/books")
    List<Book> getAllBooks();
}
