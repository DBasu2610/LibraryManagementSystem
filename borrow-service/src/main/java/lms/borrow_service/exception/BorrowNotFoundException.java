package lms.borrow_service.exception;

public class BorrowNotFoundException extends RuntimeException {

    public BorrowNotFoundException(String message) {
        super(message);
    }
}
