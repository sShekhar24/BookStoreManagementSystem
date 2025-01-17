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

	/**
	 * BookRepository is injected into BookService using constructor injection
	 * Avoids issues like circular dependencies compared to field injection 
	 * Methods like getAllBooks() and saveBook() can be reused by multiple controllers or
	 * other services Enables adding complex business rules or validations without
	 * cluttering other layers
	 */
	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	/**
	 * Retrieve all books from the database.
	 * @return List of books.
	 */
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	/**
	 * Save a new book to the database.
	 * @param book Book object to save.
	 * @return The saved Book.
	 */
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}
}
