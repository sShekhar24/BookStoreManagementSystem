package com.library.manager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.library.manager.entities.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    // find books by exact title (case insensitive)
    @Query(value = "select * from TLM_BOOK b where lower(b.TITLE) = lower(:title)", nativeQuery = true)
    List<BookEntity> findByTitle(@Param("title") String title);

    // find books by author (case insensitive)
    @Query(value = "select * from TLM_BOOK b where lower(b.AUTHOR) = lower(:author)", nativeQuery = true)
    List<BookEntity> findByAuthor(@Param("author") String author);

    // find books by title (case insensitive, partial match)
    @Query(value = "select * from TLM_BOOK b where lower(b.TITLE) LIKE lower(CONCAT('%', :searchCriteria, '%'))", nativeQuery = true)
    List<BookEntity> findByTitleContaining(@Param("searchCriteria") String searchCriteria);

    // find books by author (case insensitive, partial match)
    @Query(value = "select * from TLM_BOOK b where lower(b.AUTHOR) LIKE lower(CONCAT('%', :author, '%'))", nativeQuery = true)
    List<BookEntity> findByAuthorContaining(@Param("author") String author);

    // find books by borrowed status
    @Query(value = "select * from TLM_BOOK b where b.BORROWED_STATUS = :borrowedStatus", nativeQuery = true)
    List<BookEntity> findByBorrowedStatus(@Param("borrowedStatus") String borrowedStatus);

    // find books by quantity greater than a certain value
    @Query(value = "select * from TLM_BOOK b where b.QUANTITY > :quantity", nativeQuery = true)
    List<BookEntity> findByQuantityGreaterThan(@Param("quantity") long quantity);

    // find books by borrowed quantity
    @Query(value = "select * from TLM_BOOK b where b.BORROWED_QUANTITY = :borrowedQuantity", nativeQuery = true)
    List<BookEntity> findByBorrowedQuantity(@Param("borrowedQuantity") long borrowedQuantity);

    // find books by returned quantity
    @Query(value = "select * from TLM_BOOK b where b.RETURNED_QUANTITY = :returnedQuantity", nativeQuery = true)
    List<BookEntity> findByReturnedQuantity(@Param("returnedQuantity") long returnedQuantity);

    // find books by issued date
    @Query(value = "select * from TLM_BOOK b where b.ISSUED_AT = :issuedAt", nativeQuery = true)
    List<BookEntity> findByIssuedAt(@Param("issuedAt") java.util.Date issuedAt);

    // find books by returned date
    @Query(value = "select * from TLM_BOOK b where b.RETURNED_DATE = :returnedAt", nativeQuery = true)
    List<BookEntity> findByReturnedAt(@Param("returnedAt") java.util.Date returnedAt);
}
