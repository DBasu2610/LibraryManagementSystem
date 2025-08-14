package com.lms.book_service.grpc;

import com.lms.book_service.service.BookService;
import io.grpc.stub.StreamObserver;
import library.BookAvailabilityRequest;
import library.BookAvailabilityResponse;
import library.BookEditRequest;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;


@GrpcService
public class BookGrpcService extends library.BookServiceGrpc.BookServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(BookGrpcService.class);
    private final BookService bookService;

    @Autowired
    public BookGrpcService(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void checkAvailability(BookAvailabilityRequest request, StreamObserver<BookAvailabilityResponse> responseObserver) {
        log.info("Received gRPC request to check availability for book ID: {}", request.getBookId());
        boolean isAvailable = bookService.isBookAvailable(UUID.fromString(request.getBookId()));
        BookAvailabilityResponse response = BookAvailabilityResponse.newBuilder()
                .setAvailable(isAvailable)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void validateBook(BookAvailabilityRequest request, StreamObserver<BookAvailabilityResponse> responseObserver) {
        log.info("Received gRPC request to validate book ID: {}", request.getBookId());
        boolean isValid = bookService.isBookValid(UUID.fromString(request.getBookId()));
        BookAvailabilityResponse response = BookAvailabilityResponse.newBuilder()
                .setAvailable(isValid)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void addBook(BookEditRequest request, StreamObserver<library.BookEditResponse> responseObserver) {
        log.info("Received gRPC request to add book with ID: {}", request.getBookId());
        UUID bookId = UUID.fromString(request.getBookId());
        bookService.addBook(bookId);
        library.BookEditResponse response = library.BookEditResponse.newBuilder()
                .setSuccess(true)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void subtractBook(BookEditRequest request, StreamObserver<library.BookEditResponse> responseObserver) {
        log.info("Received gRPC request to remove book with ID: {}", request.getBookId());
        UUID bookId = UUID.fromString(request.getBookId());
        bookService.subtractBook(bookId);
        library.BookEditResponse response = library.BookEditResponse.newBuilder()
                .setSuccess(true)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
