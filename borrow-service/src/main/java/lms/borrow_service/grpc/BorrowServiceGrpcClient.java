package lms.borrow_service.grpc;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import library.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BorrowServiceGrpcClient {
    Logger log = LoggerFactory.getLogger(BorrowServiceGrpcClient.class);

    private MemberServiceGrpc.MemberServiceBlockingStub memberServiceBlockingStub;
    private BookServiceGrpc.BookServiceBlockingStub bookServiceBlockingStub;

    public BorrowServiceGrpcClient(@Value("${book.service.address:localhost}") String bookServerAddress,@Value("${book.service.grpc.port:4000}") int bookServerPort,@Value("${member.service.address:localhost}") String memberServerAddress,@Value("${member.service.grpc.port:4001}") int memberServerPort
) {
        ManagedChannel bookChannel = ManagedChannelBuilder.forAddress(bookServerAddress,bookServerPort).usePlaintext().build();
        ManagedChannel memberChannel = ManagedChannelBuilder.forAddress(memberServerAddress,memberServerPort).usePlaintext().build();

        memberServiceBlockingStub=MemberServiceGrpc.newBlockingStub(memberChannel);
        bookServiceBlockingStub=BookServiceGrpc.newBlockingStub(bookChannel);

    }

    public boolean isBookAvailable(String bookId) {
        BookAvailabilityRequest request = BookAvailabilityRequest.newBuilder()
                .setBookId(bookId)
                .build();
        BookAvailabilityResponse response = bookServiceBlockingStub.checkAvailability(request);
        log.info("BookService response: {}", response);
        return response.getAvailable();
    }

    public boolean isMemberValid(String memberId) {
        MemberValidationRequest request = MemberValidationRequest.newBuilder()
                .setMemberId(memberId)
                .build();
        MemberValidationResponse response = memberServiceBlockingStub.validateMember(request);
        log.info("MemberService response: {}", response);
        return response.getValid();
    }

    public boolean isBookValid(String bookId) {
        BookAvailabilityRequest request = BookAvailabilityRequest.newBuilder()
                .setBookId(bookId)
                .build();
        BookAvailabilityResponse response = bookServiceBlockingStub.validateBook(request);
        log.info("BookService response: {}", response);
        return response.getAvailable();
    }

    public void addBook(String bookId) {
        BookEditRequest request = BookEditRequest.newBuilder()
                .setBookId(bookId)
                .build();
        bookServiceBlockingStub.addBook(request);
        log.info("Book with ID {} added successfully", bookId);
    }

    public void removeBook(String bookId) {
        BookEditRequest request = BookEditRequest.newBuilder()
                .setBookId(bookId)
                .build();
        bookServiceBlockingStub.subtractBook(request);
        log.info("Book with ID {} removed successfully", bookId);
    }

}
