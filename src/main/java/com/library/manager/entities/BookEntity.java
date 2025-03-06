package com.library.manager.entities;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "TLM_BOOK")
@Entity
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_ID")
    private long bookId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "QUANTITY")
    private long quantity;

    @Column(name = "ISSUED_AT")
    private Date issuedAt;

    @Column(name = "RETURNED_DATE")
    private Date returnedAt;

    @Column(name = "BORROWED_STATUS")
    private String borrowedStatus;

    @Column(name = "BORROWED_QUANTITY")
    private long borrowedQuantity;

    @Column(name = "RETURNED_QUANTITY")
    private long returnedQuantity;

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }

    public Date getReturnedAt() {
        return returnedAt;
    }

    public void setReturnedAt(Date returnedAt) {
        this.returnedAt = returnedAt;
    }

    public String getBorrowedStatus() {
        return borrowedStatus;
    }

    public void setBorrowedStatus(String borrowedStatus) {
        this.borrowedStatus = borrowedStatus;
    }

    public long getBorrowedQuantity() {
        return borrowedQuantity;
    }

    public void setBorrowedQuantity(long borrowedQuantity) {
        this.borrowedQuantity = borrowedQuantity;
    }

    public long getReturnedQuantity() {
        return returnedQuantity;
    }

    public void setReturnedQuantity(long returnedQuantity) {
        this.returnedQuantity = returnedQuantity;
    }
}
