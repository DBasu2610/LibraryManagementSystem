package com.lms.book_service.service;

import com.lms.book_service.DTO.BookRequestDTO;
import com.lms.book_service.DTO.BookResponseDTO;
import com.lms.book_service.entity.Book;
import com.lms.book_service.exception.BookNotFoundException;
import com.lms.book_service.mapper.BookMapper;
import com.lms.book_service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public BookResponseDTO createBook(BookRequestDTO requestDTO) {
        Book book = BookMapper.toBook(requestDTO);
        Book savedBook = bookRepository.save(book);
        return  BookMapper.toBookResponseDTO(savedBook);
    }


    public BookResponseDTO getBookById(UUID id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));
        return BookMapper.toBookResponseDTO(book);
    }


    public List<BookResponseDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(BookMapper::toBookResponseDTO)
                .collect(Collectors.toList());
    }


    public void deleteBook(UUID id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book with ID " + id + " not found");
        }
        bookRepository.deleteById(id);
    }


    public BookResponseDTO updateBook(UUID id, BookRequestDTO requestDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));

        book.setTitle(requestDTO.getTitle());
        book.setAuthor(requestDTO.getAuthor());
        book.setGenre(requestDTO.getGenre());
        book.setTotalCopies(requestDTO.getTotalCopies());
        book.setAvailableCopies(requestDTO.getAvailableCopies());

        return BookMapper.toBookResponseDTO(bookRepository.save(book));
    }

    public boolean isBookAvailable(UUID id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));
        return book.getAvailableCopies() > 0;
    }

    public boolean isBookValid(UUID id) {
        return bookRepository.existsById(id);
    }

    public void addBook(UUID id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);
    }

    public void subtractBook(UUID id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
    }
}


