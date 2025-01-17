package com.bookstore.dto;

/**
 * Data Transfer Object for transferring Book data.
 */
public class BookDTO {

    private String title;
    private String author;
    private Double price;

    // Constructors
    //Allows creating an empty DTO object, which is later populated using setters or deserialization
    public BookDTO() {
    }

    public BookDTO(String title, String author, Double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Getters and Setters
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
