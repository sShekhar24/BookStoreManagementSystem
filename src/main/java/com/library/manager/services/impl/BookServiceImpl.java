package com.library.manager.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.manager.entities.BookEntity;
import com.library.manager.models.AddBookRequest;
import com.library.manager.models.UpdateBookRequest;
import com.library.manager.repositories.BookRepository;
import com.library.manager.services.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    @Transactional
    public BookEntity addNewBook(AddBookRequest addBookRequest) {
        System.out.println("Adding new book: " + addBookRequest.getTitle());
        BookEntity book = new BookEntity();
        book.setTitle(addBookRequest.getTitle());
        book.setAuthor(addBookRequest.getAuthor());
        book.setQuantity(addBookRequest.getQuantity());
        book.setBorrowedStatus("Available");
        book.setBorrowedQuantity(0);
        book.setReturnedQuantity(0);
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public BookEntity updateBook(UpdateBookRequest updateBookRequest) {
        System.out.println("Updating book with ID: " + updateBookRequest.getId());
        Optional<BookEntity> existingBook = bookRepository.findById(updateBookRequest.getId());
        if (existingBook.isPresent()) {
            BookEntity book = existingBook.get();
            book.setTitle(updateBookRequest.getTitle());
            book.setAuthor(updateBookRequest.getAuthor());
            book.setQuantity(updateBookRequest.getQuantity());
            book.setBorrowedStatus(updateBookRequest.getBorrowedStatus());
            book.setBorrowedQuantity(updateBookRequest.getBorrowedQuantity());
            book.setReturnedQuantity(updateBookRequest.getReturnedQuantity());
            return bookRepository.save(book);
        } else {
            System.out.println("Book with ID " + updateBookRequest.getId() + " not found");
            throw new RuntimeException("Book not found!");
        }
    }

    @Override
    @Transactional
    public boolean deleteBookById(long bookId) {
        System.out.println("Deleting book with ID: " + bookId);
        if (bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
            return true;
        } else {
            System.out.println("Book with ID " + bookId + " not found");
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteBookByTitle(String title) {
        System.out.println("Deleting book with title: " + title);
        List<BookEntity> books = bookRepository.findByTitle(title);
        if (!books.isEmpty()) {
            bookRepository.deleteAll(books);
            return true;
        } else {
            System.out.println("No books found with title " + title);
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteBookByAuthor(String author) {
        System.out.println("Deleting books by author: " + author);
        List<BookEntity> books = bookRepository.findByAuthor(author);
        if (!books.isEmpty()) {
            bookRepository.deleteAll(books);
            return true;
        } else {
            System.out.println("No books found by author " + author);
            return false;
        }
    }

    @Override
    public List<BookEntity> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<BookEntity> findAllBooksByName(String searchCriteria) {
        return bookRepository.findByTitleContaining(searchCriteria);
    }

    @Override
    public BookEntity findBookById(long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public List<BookEntity> findBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public List<BookEntity> findBooksByTitle(String title) {
        return bookRepository.findByTitle(title);
    }
}
