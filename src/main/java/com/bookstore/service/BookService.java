package com.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;

import jakarta.validation.Valid;

@Service
@Validated
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Get all books from the database.
     *
     * @return List of all books.
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Save a new book to the database.
     *
     * @param book Book entity to be saved.
     * @return Saved Book entity.
     */
    public Book saveBook(@Valid Book book) {
        return bookRepository.save(book);
    }

    /**
     * Get a book by its ID.
     *
     * @param id ID of the book.
     * @return Optional containing the Book if found, empty if not.
     */
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
}
