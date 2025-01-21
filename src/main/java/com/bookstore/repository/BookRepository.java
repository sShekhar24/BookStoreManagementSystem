package com.bookstore.repository;

import com.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository interface for Book entity.
 * Provides CRUD operations and custom query support.
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Find books by title (case-insensitive) containing the specified keyword.
     * 
     * @param titleKeyword Keyword to search for in the title.
     * @return List of books with titles containing the keyword.
     */
    List<Book> findByTitleContainingIgnoreCase(String titleKeyword);

    /**
     * Find books by author (case-insensitive).
     *
     * @param author Author's name.
     * @return List of books written by the specified author.
     */
    List<Book> findByAuthorIgnoreCase(String author);

    /**
     * Find books with a price less than the specified amount.
     *
     * @param price Price threshold.
     * @return List of books priced less than the specified amount.
     */
    List<Book> findByPriceLessThan(double price);

    /**
     * Custom JPQL query to find books within a specific price range.
     *
     * @param minPrice Minimum price.
     * @param maxPrice Maximum price.
     * @return List of books with prices between the specified range.
     */
    @Query("SELECT b FROM Book b WHERE b.price BETWEEN :minPrice AND :maxPrice")
    List<Book> findBooksByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

    /**
     * Custom JPQL query to count the number of books by a specific author.
     *
     * @param author Author's name.
     * @return Number of books written by the specified author.
     */
    @Query("SELECT COUNT(b) FROM Book b WHERE LOWER(b.author) = LOWER(:author)")
    long countBooksByAuthor(@Param("author") String author);

    /**
     * Custom JPQL query to find the top N most expensive books.
     *
     * @param limit Maximum number of books to retrieve.
     * @return List of the most expensive books.
     */
    @Query(value = "SELECT b FROM Book b ORDER BY b.price DESC")
    List<Book> findTopExpensiveBooks(org.springframework.data.domain.Pageable limit);

    /**
     * Custom JPQL query to delete all books by a specific author.
     *
     * @param author Author's name.
     */
    @Query("DELETE FROM Book b WHERE LOWER(b.author) = LOWER(:author)")
    void deleteBooksByAuthor(@Param("author") String author);
}
