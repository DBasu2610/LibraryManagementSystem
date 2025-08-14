package com.lms.book_service.mapper;

import com.lms.book_service.DTO.BookRequestDTO;
import com.lms.book_service.DTO.BookResponseDTO;
import com.lms.book_service.entity.Book;

public class BookMapper {

    public static BookResponseDTO toBookResponseDTO(Book book) {
        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        bookResponseDTO.setBookId(book.getBookId().toString());
        bookResponseDTO.setTitle(book.getTitle());
        bookResponseDTO.setAuthor(book.getAuthor());
        bookResponseDTO.setGenre(book.getGenre());
       bookResponseDTO.setTotalCopies(book.getTotalCopies().toString());
       bookResponseDTO.setAvailableCopies(book.getAvailableCopies().toString());
        return bookResponseDTO;
    }

    public static Book toBook(BookRequestDTO bookRequestDTO) {
        Book book = new Book();
        book.setTitle(bookRequestDTO.getTitle());
        book.setAuthor(bookRequestDTO.getAuthor());
        book.setGenre(bookRequestDTO.getGenre());
        book.setTotalCopies(bookRequestDTO.getTotalCopies());
        book.setAvailableCopies(bookRequestDTO.getAvailableCopies());
        return book;
    }
}