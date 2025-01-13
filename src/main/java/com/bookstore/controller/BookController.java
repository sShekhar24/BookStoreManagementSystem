package com.bookstore.controller;

import com.bookstore.dto.BookDTO;
import com.bookstore.entity.Book;
import com.bookstore.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST Controller for handling HTTP requests related to Books.
 */
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Retrieve all books.
     *
     * @return List of BookDTO objects.
     */
    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks().stream()
                .map(book -> new BookDTO(book.getTitle(), book.getAuthor(), book.getPrice()))
                .collect(Collectors.toList());
    }

    /**
     * Save a new book.
     *
     * @param bookDTO BookDTO received in the request body.
     * @return The saved BookDTO object.
     */
    @PostMapping
    public BookDTO saveBook(@RequestBody BookDTO bookDTO) {
        Book book = new Book(bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPrice());
        Book savedBook = bookService.saveBook(book);
        return new BookDTO(savedBook.getTitle(), savedBook.getAuthor(), savedBook.getPrice());
    }
}
