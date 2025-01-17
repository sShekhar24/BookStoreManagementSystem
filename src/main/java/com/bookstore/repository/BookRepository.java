package com.bookstore.repository;

import com.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for the Book entity.
 * Acts as a bridge between the application and the database, 
 * providing methods to perform CRUD operations and queries on the Book entity.
 * Flushing, batch operations, and fine-grained transaction control can be managed here.
 * Custom Query Methods can be considered as an enhancement.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
