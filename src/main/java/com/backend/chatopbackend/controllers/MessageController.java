package com.backend.chatopbackend.controllers;

import com.backend.chatopbackend.models.Messages;
import com.backend.chatopbackend.services.MessagesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private MessagesServices messagesServices;

    @PostMapping("/messages/")
    // <?> = Signifie que ça peut retourner n'importe quelle type d'object int : strign etc
    // Map = interface / Hash map = Implémentation d'interface = Meilleur abstraction / Maintenabilité du code
    public ResponseEntity<?> saveMessage(@RequestBody Messages messages) {
        try {
             messagesServices.saveMessage(messages);
            return ResponseEntity.ok(Map.of("message","Message send with succes"));
        } catch (Exception e) {
            // Cela indique qu'une erreur s'est produite du côté serveur.
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(Map.of( "message", " Error while saving message"));
        }
    }
}
