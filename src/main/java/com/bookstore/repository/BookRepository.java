package com.bookstore.repository;

import com.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for the Book entity.
 * Provides CRUD operations and query capabilities.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
