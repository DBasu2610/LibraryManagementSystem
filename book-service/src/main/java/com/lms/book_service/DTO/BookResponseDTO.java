package com.lms.book_service.DTO;

public class BookResponseDTO {

    private String bookId;
    private String title;
    private String author;
    private String genre;
    private String totalCopies;
    private String availableCopies;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(String totalCopies) {
        this.totalCopies = totalCopies;
    }

    public String getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(String availableCopies) {
        this.availableCopies = availableCopies;
    }
}
