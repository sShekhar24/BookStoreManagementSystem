package com.library.manager.models;

public class UpdateBookRequest {

    private long id;
    private String title;
    private String author;
    private long quantity;
    private String borrowedStatus;
    private long borrowedQuantity;
    private long returnedQuantity;

    public UpdateBookRequest() {}

    public UpdateBookRequest(long id, String title, String author, long quantity, String borrowedStatus, long borrowedQuantity, long returnedQuantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.borrowedStatus = borrowedStatus;
        this.borrowedQuantity = borrowedQuantity;
        this.returnedQuantity = returnedQuantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
