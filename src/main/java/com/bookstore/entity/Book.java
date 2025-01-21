package com.bookstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Entity class representing a Book in the bookstore system.
 * Includes validation constraints and implements Serializable.
 */
@Entity
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title must not be empty")
    private String title;

    @NotBlank(message = "Author must not be empty")
    private String author;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be a positive value")
    private double price;

    /**
     * Default constructor required by JPA.
     */
    public Book() {
        // Default constructor
    }

    /**
     * Parameterized constructor for creating a Book instance.
     *
     * @param title  Title of the book.
     * @param author Author of the book.
     * @param price  Price of the book.
     */
    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Getters and Setters

    /**
     * Gets the unique identifier of the book.
     *
     * @return Book ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the book.
     *
     * @param id Book ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

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
