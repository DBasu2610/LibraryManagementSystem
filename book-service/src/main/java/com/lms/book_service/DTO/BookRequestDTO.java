package com.lms.book_service.DTO;

import jakarta.validation.constraints.*;

public class BookRequestDTO {

    @NotBlank(message = "Title is required")
    @Size(max = 70, min = 3, message = "title should be between 3 and 70 characters")
    private String title;

    @NotBlank(message = "Author is required")
    @Size(max = 70, min = 2, message = "author should be between 2 and 70 characters")
    private String author;

    @NotBlank(message = "Genre is required")
    private String genre;

    @NotNull(message = "Total copies is required")
    @Min(value = 1, message = "There must be at least 1 total copy")
    private Integer totalCopies;

    @NotNull(message = "Available copies is required")
    @Min(value = 0, message = "Available copies cannot be negative")
    private Integer availableCopies;

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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(Integer totalCopies) {
        this.totalCopies = totalCopies;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}
