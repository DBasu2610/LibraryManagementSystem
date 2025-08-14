package lms.borrow_service.mapper;

import lms.borrow_service.DTO.BorrowRequestDTO;
import lms.borrow_service.DTO.BorrowResponseDTO;
import lms.borrow_service.entity.Borrow;

public class BorrowMapper {

    public static Borrow toBorrow(BorrowRequestDTO dto) {
        Borrow borrow = new Borrow();

        borrow.setUserId(dto.getUserId());
        borrow.setBookId(dto.getBookId());
        borrow.setBorrowDate(dto.getBorrowDate());
        borrow.setDueDate(dto.getDueDate());
        borrow.setReturned(false);
        return borrow;
    }

    public static BorrowResponseDTO toBorrowResponseDTO(Borrow borrow) {
        BorrowResponseDTO dto = new BorrowResponseDTO();
        dto.setBorrowId(borrow.getBorrowId().toString());
        dto.setUserId(borrow.getUserId());
        dto.setBookId(borrow.getBookId());
        dto.setBorrowDate(borrow.getBorrowDate().toString());
        dto.setDueDate(borrow.getDueDate().toString());
        dto.setReturned(borrow.getReturned().toString());
        return dto;
    }
}
