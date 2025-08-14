package com.lms.book_service.controller;



import com.lms.book_service.DTO.BookRequestDTO;
import com.lms.book_service.DTO.BookResponseDTO;
import com.lms.book_service.service.BookService;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        List<BookResponseDTO> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }


    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable UUID bookId) {
        BookResponseDTO book = bookService.getBookById(bookId);
        return ResponseEntity.ok(book);
    }


    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@Validated(Default.class) @RequestBody BookRequestDTO bookDTO) {
        BookResponseDTO createdBook = bookService.createBook(bookDTO);
        return ResponseEntity.ok(createdBook);
    }


    @PutMapping("/{bookId}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable UUID bookId, @RequestBody BookRequestDTO bookDTO) {
        BookResponseDTO updatedBook = bookService.updateBook(bookId, bookDTO);
        return ResponseEntity.ok(updatedBook);
    }


    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }
}

