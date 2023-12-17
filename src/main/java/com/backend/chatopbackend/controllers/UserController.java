package com.backend.chatopbackend.controllers;

import com.backend.chatopbackend.models.entity.Users;
import com.backend.chatopbackend.services.UsersServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Contrôleur pour gérer les utilisateurs dans l'application.
 * <p>
 * Ce contrôleur permet de récupérer les informations d'un utilisateur.
 * </p>
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private UsersServices usersServices;

    /**
     * Récupère les informations d'un utilisateur spécifique par son identifiant.
     *
     * @param id L'identifiant unique de l'utilisateur.
     * @return Les informations de l'utilisateur si trouvé, sinon une réponse indiquant que l'utilisateur n'est pas trouvé.
     */

    @Operation(summary = "Récupère un utilisateur par ID", description = "Retourne les informations d'un utilisateur spécifique en fonction de son identifiant.")
    @ApiResponse(responseCode = "200", description = "Utilisateur trouvé",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Users.class)))
    @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé",
            content = @Content(mediaType = "application/json"))
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserId(@PathVariable("id") Integer id) {
        return usersServices.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
