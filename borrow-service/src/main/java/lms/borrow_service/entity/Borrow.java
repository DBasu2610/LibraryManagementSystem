package lms.borrow_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID borrowId;

    @NotNull
    private String userId;

    @NotNull
    private String bookId;

    @NotNull
    private LocalDate borrowDate;

    @NotNull
    private LocalDate dueDate;

    @NotNull
    private Boolean returned;

    public Borrow() {}

    public UUID getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(UUID borrowId) {
        this.borrowId = borrowId;
    }

    public Boolean getReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
