package com.lab1.demo.service;

import com.lab1.demo.model.Book;
import com.lab1.demo.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        try {
            return bookRepository.findAll();
        } catch (Exception e) {
            logger.error("Error fetching books", e);
            throw new RuntimeException("Failed to fetch books");
        }
    }

    public Book addBook(Book book) {
        try {
            return bookRepository.save(book);
        } catch (Exception e) {
            logger.error("Error adding book", e);
            throw new RuntimeException("Failed to add book");
        }
    }

    public Book updateBook(Long id, Book bookDetails) {
        try {
            Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));

            book.setName(bookDetails.getName());
            book.setCategory(bookDetails.getCategory());
            book.setAuthor(bookDetails.getAuthor());
            book.setAvailableCopies(bookDetails.getAvailableCopies());

            return bookRepository.save(book);
        } catch (Exception e) {
            logger.error("Error updating book with ID: {}", id, e);
            throw new RuntimeException("Failed to update book");
        }
    }

    public void deleteBook(Long id) {
        try {
            bookRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error deleting book with ID: {}", id, e);
            throw new RuntimeException("Failed to delete book");
        }
    }

    public Book markAsRented(Long id) {
        try {
            Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));

            if (book.getAvailableCopies() > 0) {
                book.setAvailableCopies(book.getAvailableCopies() - 1);
                return bookRepository.save(book);
            } else {
                throw new RuntimeException("No available copies for book ID: " + id);
            }
        } catch (Exception e) {
            logger.error("Error marking book as rented with ID: {}", id, e);
            throw new RuntimeException("Failed to mark book as rented");
        }
    }
}
