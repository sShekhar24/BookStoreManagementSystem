package com.library.manager.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.manager.entities.BookEntity;
import com.library.manager.models.AddBookRequest;
import com.library.manager.models.UpdateBookRequest;
import com.library.manager.services.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public BookEntity addNewBook(@Valid @RequestBody AddBookRequest addBookRequest) {
        return bookService.addNewBook(addBookRequest);
    }

    @PutMapping("/update")
    public BookEntity updateBook(@Valid @RequestBody UpdateBookRequest updateBookRequest) {
        return bookService.updateBook(updateBookRequest);
    }

    @DeleteMapping("/delete/{bookId}")
    public boolean deleteBookById(@PathVariable long bookId) {
        return bookService.deleteBookById(bookId);
    }

    @DeleteMapping("/delete/title/{title}")
    public boolean deleteBookByTitle(@PathVariable String title) {
        return bookService.deleteBookByTitle(title);
    }

    @DeleteMapping("/delete/author/{author}")
    public boolean deleteBookByAuthor(@PathVariable String author) {
        return bookService.deleteBookByAuthor(author);
    }

    @GetMapping("/findAll")
    public List<BookEntity> findAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/findByTitle")
    public List<BookEntity> findBooksByTitle(@RequestParam String title) {
        return bookService.findBooksByTitle(title);
    }

    @GetMapping("/findByAuthor")
    public List<BookEntity> findBooksByAuthor(@RequestParam String author) {
        return bookService.findBooksByAuthor(author);
    }
}
