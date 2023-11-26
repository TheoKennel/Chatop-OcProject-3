package com.backend.chatopbackend.controllers;

import com.backend.chatopbackend.models.Rentals;
import com.backend.chatopbackend.services.RentalsServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {

    @Autowired
    private RentalsServices rentalsServices;

    @GetMapping
    public Iterable<Rentals> getAllRental() {
        return rentalsServices.getAllRentals();
    }

    @GetMapping("/{id}")
    public Optional<Rentals> getRentalById(@PathVariable("id") final Integer id) {
        return rentalsServices.getRentals(id);
    }

    @PostMapping
    public ResponseEntity<String> saveRentals(@RequestParam MultipartFile imageFile, @RequestParam String rentalJson ) {
        try {
        Rentals rental = new ObjectMapper().readValue(rentalJson, Rentals.class);
        rentalsServices.saveRental(rental, imageFile);
        return ResponseEntity.ok("Rental created !");
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRentals(@PathVariable("id") final Integer id, @RequestParam String name,
                                                @RequestParam BigDecimal surface, @RequestParam BigDecimal price, @RequestParam String description) {
        return rentalsServices.getRentals(id)
                .map(rentals -> {
                    rentalsServices.updateRental(rentals, name, surface, price, description);
                    return ResponseEntity.ok("Rental updated !");
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
