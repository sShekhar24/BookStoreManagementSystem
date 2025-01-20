package com.bookstore.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.dto.BookDTO;
import com.bookstore.entity.Book;
import com.bookstore.exception.ResourceNotFoundException;
import com.bookstore.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
@Validated
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Get all books.
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
    public BookDTO saveBook(@Valid @RequestBody BookDTO bookDTO) {
        Book book = new Book(bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPrice());
        Book savedBook = bookService.saveBook(book);
        return new BookDTO(savedBook.getTitle(), savedBook.getAuthor(), savedBook.getPrice());
    }

    /**
     * Get a book by its ID.
     *
     * @param id Book's ID.
     * @return ResponseEntity containing the BookDTO if found, otherwise throws an exception.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        if (book.isEmpty()) {
            throw new ResourceNotFoundException("Book with id " + id + " not found");
        }
        BookDTO bookDTO = new BookDTO(book.get().getTitle(), book.get().getAuthor(), book.get().getPrice());
        return ResponseEntity.ok(bookDTO);
    }
}
