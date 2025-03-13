package com.lab1.demo.service;

import com.lab1.demo.model.Country;
import com.lab1.demo.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private final CountryRepository countryRepository;
    private static final Logger logger = LoggerFactory.getLogger(CountryService.class);

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> findAll() {
        try {
            return countryRepository.findAll();
        } catch (Exception e) {
            logger.error("Error fetching countries", e);
            throw new RuntimeException("Failed to fetch countries");
        }
    }

    public Country findById(Long id) {
        try {
            return countryRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Country not found with ID: " + id));
        } catch (Exception e) {
            logger.error("Error finding country with ID: {}", id, e);
            throw new RuntimeException("Failed to fetch country");
        }
    }

    public Country save(Country country) {
        try {
            return countryRepository.save(country);
        } catch (Exception e) {
            logger.error("Error saving country", e);
            throw new RuntimeException("Failed to save country");
        }
    }

    public void delete(Long id) {
        try {
            countryRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error deleting country with ID: {}", id, e);
            throw new RuntimeException("Failed to delete country");
        }
    }
}