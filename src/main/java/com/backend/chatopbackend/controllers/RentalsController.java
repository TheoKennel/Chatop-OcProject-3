package com.backend.chatopbackend.controllers;

import com.backend.chatopbackend.models.Rentals;
import com.backend.chatopbackend.services.RentalsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Rentals saveRentals(@RequestBody Rentals rentals) {
        return rentalsServices.saveRental(rentals);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRentals(@PathVariable("id") final Integer id, @RequestParam String name,
                                                @RequestParam BigDecimal prince, @RequestParam BigDecimal price) {
        return rentalsServices.getRentals(id)
                        .map(rentals ->
                                rentals.setName(name))
        ResponseEntity.ok(Map.of("message", "Rental updated"));
    }
}
