package com.bookstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;

/**
 * Data Transfer Object (DTO) for Book.
 * Used to transfer data between layers in the application.
 */
public class BookDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Author cannot be blank")
    private String author;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be greater than zero")
    private Double price;

    /**
     * Default constructor for deserialization and framework usage.
     */
    public BookDTO() {
        // Default constructor
    }

    /**
     * Parameterized constructor for creating a BookDTO instance.
     *
     * @param title  Title of the book.
     * @param author Author of the book.
     * @param price  Price of the book.
     */
    public BookDTO(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Getters and Setters

    /**
     * Gets the title of the book.
     *
     * @return Title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     *
     * @param title Title of the book.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the author of the book.
     *
     * @return Author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     *
     * @param author Author of the book.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the price of the book.
     *
     * @return Price of the book.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the book.
     *
     * @param price Price of the book.
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
