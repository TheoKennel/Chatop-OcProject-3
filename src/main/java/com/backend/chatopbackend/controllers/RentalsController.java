package com.backend.chatopbackend.controllers;

import com.backend.chatopbackend.models.dto.RentalsSave;
import com.backend.chatopbackend.models.entity.Rentals;
import com.backend.chatopbackend.services.RentalsServices;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Optional;

/**
 * Contrôleur pour la gestion des rentals.
 * <p>
 * Ce contrôleur permet la création, la récupération et la mise à jour des informations de location.
 * </p>
 */
@RestController
@RequestMapping("/api/rentals")
public class RentalsController {

    @Autowired
    private RentalsServices rentalsServices;

    /**
     * Obtient la liste de toutes les locations disponibles.
     *
     * @return La liste des locations.
     */
    @GetMapping
    public Iterable<Rentals> getAllRental() {
        return rentalsServices.getAllRentals();
    }


    /**
     * Obtient les détails d'une location spécifique par son identifiant.
     *
     * @param id L'identifiant de la location.
     * @return Les détails de la location ou un objet vide si non trouvé.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtient une location par ID", description = "Retourne les détails d'une location spécifique en fonction de son identifiant.")
    public Optional<Rentals> getRentalById(@PathVariable("id") final Integer id) {
        return rentalsServices.getRentals(id);
    }

    /**
     * Crée une nouvelle location.
     *
     * @param picture L'image de la location.
     * @param rentalJson Les détails de la location à enregistrer.
     * @return Un message indiquant le succès de la création ou échec.
     */
    @PostMapping
    @Operation(summary = "Crée une nouvelle location", description = "Enregistre une nouvelle location avec les détails fournis.")
    public ResponseEntity<Map<String, String>> saveRentals(@RequestParam MultipartFile picture, @ModelAttribute RentalsSave rentalJson ) {
        try {
            rentalsServices.saveRental(rentalJson, picture);
            return ResponseEntity.ok(Map.of("message", "Rental created !"));
        } catch (Exception e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Internal server error : " + e.getMessage()));
        }
    }

    /**
     * Met à jour une location existante.
     *
     * @param id L'identifiant de la location à mettre à jour.
     * @param rentalsSave Les nouvelles informations de la location.
     * @return Un message indiquant le succès de la mise à jour ou une erreur si la location n'est pas trouvée.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateRentals(@PathVariable("id") final Integer id,  @ModelAttribute RentalsSave rentalsSave) {
        return rentalsServices.getRentals(id)
                .map(rentals -> {
                    rentalsServices.updateRental(rentals, rentalsSave);
                    return ResponseEntity.ok(Map.of("message", "Rental updated !"));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
