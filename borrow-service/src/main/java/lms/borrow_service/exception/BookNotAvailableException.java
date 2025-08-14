package lms.borrow_service.exception;

public class BookNotAvailableException extends RuntimeException{
    public BookNotAvailableException(String message) {
        super(message);
    }
}
