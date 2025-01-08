package com.bookstore.dao;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// DAO class for custom data access operations
@Repository
public class BookDAO {
    
    @Autowired
    private BookRepository bookRepository;

    // Save a book to the database
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    // Retrieve all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Find a book by its ID
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    // Update the quantity of a book
    public Book updateBookQuantity(Long id, int quantity) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            book.setQuantity(book.getQuantity() + quantity);
            return bookRepository.save(book);
        }
        return null;
    }
}
