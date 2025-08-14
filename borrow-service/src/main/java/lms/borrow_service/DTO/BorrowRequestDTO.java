package lms.borrow_service.DTO;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;


public class BorrowRequestDTO {

    @NotBlank(message="userId cannot be null")
    private String userId;

    @NotBlank(message="bookId cannot be null")
    private String bookId;

    @NotBlank(message="borrowDate cannot be null")
    private LocalDate borrowDate;

    @NotBlank(message="dueDate cannot be null")
    private LocalDate dueDate;

    public BorrowRequestDTO() {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
