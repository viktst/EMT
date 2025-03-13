package com.lab1.demo.service;

import com.lab1.demo.model.Author;
import com.lab1.demo.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private static final Logger logger = LoggerFactory.getLogger(AuthorService.class);

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> findAll() {
        try {
            return authorRepository.findAll();
        } catch (Exception e) {
            logger.error("Error fetching authors", e);
            throw new RuntimeException("Failed to fetch authors");
        }
    }

    public Author findById(Long id) {
        try {
            return authorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Author not found with ID: " + id));
        } catch (Exception e) {
            logger.error("Error finding author with ID: {}", id, e);
            throw new RuntimeException("Failed to fetch author");
        }
    }

    public Author save(Author author) {
        try {
            return authorRepository.save(author);
        } catch (Exception e) {
            logger.error("Error saving author", e);
            throw new RuntimeException("Failed to save author");
        }
    }

    public void delete(Long id) {
        try {
            authorRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error deleting author with ID: {}", id, e);
            throw new RuntimeException("Failed to delete author");
        }
    }
}
