package lms.borrow_service.exception;

public class MemberNotFoundException extends  RuntimeException {

    public MemberNotFoundException(String message) {
        super(message);
    }
}
