package com.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.entity.Book;
import com.library.exception.BookNotFoundException;
import com.library.service.BookService;

@RestController
public class BookController {

    private final BookService bookService;
    
    public BookController(BookService bookService) {
		this.bookService = bookService;
	}

    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            throw new BookNotFoundException("Book with id " + id + " not found.");
        }
        return ResponseEntity.ok(book);
    }
    
    @PostMapping("/api/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
    	return ResponseEntity.ok(bookService.createBook(book));
    }
    
    @GetMapping("/api/books/all")
    public List<Book> allBooks(){
    	return bookService.findAll();
    }
    
}
