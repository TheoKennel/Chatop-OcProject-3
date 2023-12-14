package com.backend.chatopbackend.controllers;

import com.backend.chatopbackend.models.dto.MessageDto;
import com.backend.chatopbackend.services.MessagesServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Contrôleur pour la gestion des messages dans l'application.
 * <p>
 * Ce contrôleur fournit des endpoints pour les messages.
 * </p>
 */
@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private MessagesServices messagesServices;

    /**
     * Enregistre un nouveau message dans la db.
     *
     * @param messages Le DTO représentant le message à enregistrer.
     * @return Une réponse indiquant le succès ou l'échec de l'opération d'enregistrement du message.
     */
    @PostMapping("/messages/")
    @Operation(summary = "Enregistre un nouveau message", description = "Enregistre un message envoyé par un utilisateur dans la base de données.")
    @ApiResponse(responseCode = "200", description = "Message enregistré avec succès",
            content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur lors de l'enregistrement du message",
            content = @Content(mediaType = "application/json"))
    public ResponseEntity<?> saveMessage(@RequestBody MessageDto messages) {
        try {
            messagesServices.saveMessage(messages);
            return ResponseEntity.ok(Map.of("message", "Message send with succes"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", " Error while saving message"));
        }
    }
}
