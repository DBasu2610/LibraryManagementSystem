package lms.borrow_service.controller;

import lms.borrow_service.DTO.BorrowRequestDTO;
import lms.borrow_service.DTO.BorrowResponseDTO;
import lms.borrow_service.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/borrow")
public class BorrowController {

    private final BorrowService borrowService;

    @Autowired
    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping
    public ResponseEntity<BorrowResponseDTO> createBorrow(@RequestBody BorrowRequestDTO requestDTO) {
        BorrowResponseDTO responseDTO = borrowService.createBorrow(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowResponseDTO> getBorrowById(@PathVariable UUID id) {
        BorrowResponseDTO responseDTO = borrowService.getBorrowById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<BorrowResponseDTO>> getAllBorrows() {
        List<BorrowResponseDTO> borrows = borrowService.getAllBorrows();
        return ResponseEntity.ok(borrows);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<BorrowResponseDTO> markAsReturned(@PathVariable UUID id) {
        BorrowResponseDTO responseDTO = borrowService.markAsReturned(id);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrow(@PathVariable UUID id) {
        borrowService.deleteBorrow(id);
        return ResponseEntity.noContent().build();
    }
}

