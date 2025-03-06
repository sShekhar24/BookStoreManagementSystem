package com.library.manager.services;

import com.library.manager.entities.BookEntity;
import com.library.manager.models.AddBookRequest;
import com.library.manager.models.UpdateBookRequest;

import java.util.List;

public interface BookService {

    BookEntity addNewBook(AddBookRequest addBookRequest);

    BookEntity updateBook(UpdateBookRequest updateBookRequest);

    boolean deleteBookById(long bookId);

    boolean deleteBookByTitle(String title);

    boolean deleteBookByAuthor(String author);

    List<BookEntity> findAllBooks();

    List<BookEntity> findAllBooksByName(String searchCriteria);

    BookEntity findBookById(long id);

    List<BookEntity> findBooksByAuthor(String author);

    List<BookEntity> findBooksByTitle(String title);
}
