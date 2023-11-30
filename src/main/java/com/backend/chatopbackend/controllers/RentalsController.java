package com.backend.chatopbackend.controllers;

import com.backend.chatopbackend.models.Rentals;
import com.backend.chatopbackend.services.RentalsServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {

    @Autowired
    private RentalsServices rentalsServices;

    // Iterable fais pour retourné une collection (donc en général plus d'un)
    @GetMapping
    public Iterable<Rentals> getAllRental() {
        return rentalsServices.getAllRentals();
    }


    // Cette méthode est un endpoint GET pour récupérer une location spécifique par son ID.
// Elle prend en paramètre l'ID de la location (annoté avec @PathVariable) qui est utilisé
// pour identifier la location demandée.
// 'Optional<Rentals>' est retourné, ce qui signifie que la méthode peut retourner un objet
// Rentals ou un objet vide si la location avec l'ID spécifié n'est pas trouvée.
// Cela aide à gérer les cas où la location n'existe pas, sans retourner null.
    @GetMapping("/{id}")
    public Optional<Rentals> getRentalById(@PathVariable("id") final Integer id) {
        return rentalsServices.getRentals(id);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> saveRentals(@RequestParam MultipartFile imageFile, @RequestParam String rentalJson ) {
        try {
        Rentals rental = new ObjectMapper().readValue(rentalJson, Rentals.class);
        rentalsServices.saveRental(rental, imageFile);
        return ResponseEntity.ok(Map.of("message", "Rental created !"));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Error while saving rental in DB"));
        }
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
