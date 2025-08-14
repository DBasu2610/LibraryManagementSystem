package lms.borrow_service.service;

import lms.borrow_service.DTO.BorrowRequestDTO;
import lms.borrow_service.DTO.BorrowResponseDTO;
import lms.borrow_service.exception.BookNotAvailableException;
import lms.borrow_service.exception.BookNotFoundException;
import lms.borrow_service.exception.BorrowNotFoundException;
import lms.borrow_service.exception.MemberNotFoundException;
import lms.borrow_service.grpc.BorrowServiceGrpcClient;
import lms.borrow_service.mapper.BorrowMapper;
import lms.borrow_service.entity.Borrow;
import lms.borrow_service.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BorrowServiceGrpcClient borrowServiceGrpcClient;

    @Autowired
    public BorrowService(BorrowRepository borrowRepository, BorrowServiceGrpcClient borrowServiceGrpcClient) {
        this.borrowServiceGrpcClient = borrowServiceGrpcClient;
        this.borrowRepository = borrowRepository;
    }

    public BorrowResponseDTO createBorrow(BorrowRequestDTO requestDTO) {


        UUID.fromString(requestDTO.getBookId());
        UUID.fromString(requestDTO.getUserId());

        boolean isBookIdValid = borrowServiceGrpcClient.isBookValid(requestDTO.getBookId());
        if (!isBookIdValid) {
            throw new BookNotFoundException("Book with ID " + requestDTO.getBookId() + " is not valid.");
        }

        boolean isBookAvailable = borrowServiceGrpcClient.isBookAvailable(requestDTO.getBookId());
        boolean isMemberValid = borrowServiceGrpcClient.isMemberValid(requestDTO.getUserId());

        if (!isBookAvailable) {
            throw new BookNotAvailableException("Book with ID " + requestDTO.getBookId() + " is not available for borrowing.");
        }
        if (!isMemberValid) {
            throw new MemberNotFoundException("Member with ID " + requestDTO.getUserId() + " is not valid for borrowing.");
        }

        borrowServiceGrpcClient.removeBook(requestDTO.getBookId());
        Borrow borrow = BorrowMapper.toBorrow(requestDTO);
        Borrow saved = borrowRepository.save(borrow);
        return BorrowMapper.toBorrowResponseDTO(saved);
    }

    public BorrowResponseDTO getBorrowById(UUID borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new BorrowNotFoundException("Borrow record not found for ID: " + borrowId));
        return BorrowMapper.toBorrowResponseDTO(borrow);
    }

    public List<BorrowResponseDTO> getAllBorrows() {
        return borrowRepository.findAll()
                .stream()
                .map(BorrowMapper::toBorrowResponseDTO)
                .collect(Collectors.toList());
    }

    public BorrowResponseDTO markAsReturned(UUID borrowId) {

        Borrow borrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new BorrowNotFoundException("Borrow record not found for ID: " + borrowId));

        borrowServiceGrpcClient.addBook(borrow.getBookId());
        borrow.setReturned(true);
        Borrow updated = borrowRepository.save(borrow);
        return BorrowMapper.toBorrowResponseDTO(updated);
    }

    public void deleteBorrow(UUID borrowId) {
        if (!borrowRepository.existsById(borrowId)) {
            throw new BorrowNotFoundException("Cannot delete. Borrow record not found for ID: " + borrowId);
        }
        borrowRepository.deleteById(borrowId);
    }


}
