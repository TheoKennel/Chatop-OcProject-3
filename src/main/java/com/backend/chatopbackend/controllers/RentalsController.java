package com.backend.chatopbackend.controllers;

import com.backend.chatopbackend.dto.RentalsSave;
import com.backend.chatopbackend.models.Rentals;
import com.backend.chatopbackend.services.RentalsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Map;
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
    public ResponseEntity<Map<String, String>> saveRentals(@RequestParam MultipartFile picture, @ModelAttribute RentalsSave rentalJson ) {
        //        Rentals rental = new ObjectMapper().readValue(rentalJson, Rentals.class);
        String rentalName = rentalJson.getName();
        BigDecimal rentalsSurface = rentalJson.getSurface();
        BigDecimal price = rentalJson.getPrice();
        String description = rentalJson.getDescription();
        Rentals rental = new Rentals();
        rental.setName(rentalName);
        rental.setSurface(rentalsSurface);
        rental.setPrice(price);
        rental.setDescription(description);
        rentalsServices.saveRental(rental, picture);
        return ResponseEntity.ok(Map.of("message", "Rental created !"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateRentals(@PathVariable("id") final Integer id, @RequestParam String name,
                                                @RequestParam BigDecimal surface, @RequestParam BigDecimal price, @RequestParam String description) {
        return rentalsServices.getRentals(id)
                .map(rentals -> {
                    rentalsServices.updateRental(rentals, name, surface, price, description);
                    return ResponseEntity.ok(Map.of("message", "Rental updated !"));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
