package com.bookstore.service;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class to handle business logic for Book-related operations.
 */
@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Retrieve all books from the database.
     *
     * @return List of books.
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Save a new book to the database.
     *
     * @param book Book object to save.
     * @return The saved Book.
     */
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
}
