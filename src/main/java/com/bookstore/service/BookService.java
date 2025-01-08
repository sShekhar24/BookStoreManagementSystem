package com.bookstore.service;

import com.bookstore.dto.BookDTO;
import com.bookstore.entity.Book;
import com.bookstore.dao.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookDAO bookDAO;

    // Add a new book by converting DTO to Entity
    public Book addBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPrice(bookDTO.getPrice());
        book.setQuantity(bookDTO.getQuantity());

        return bookDAO.saveBook(book);
    }

    // Get all books from the store
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    // Find a book by ID
    public Optional<Book> getBookById(Long id) {
        return bookDAO.getBookById(id);
    }

    // Update book quantity
    public Book updateBookQuantity(Long id, int quantity) {
        return bookDAO.updateBookQuantity(id, quantity);
    }
}
