package com.library.manager.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class AddBookRequest {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9 ]{2,30}$", message = "Book title should be 2-30 characters long")
    private String title;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z ]{2,50}$", message = "Author name should be 2-50 characters long")
    private String author;

    @NotNull
    @Pattern(regexp = "^[0-9]{1,9}$", message = "Value must be a number")
    @Min(value = 1, message = "Value must be at least 1")
    private long quantity;

    public AddBookRequest() {}

    public AddBookRequest(String title, String author, long quantity) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
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
}
