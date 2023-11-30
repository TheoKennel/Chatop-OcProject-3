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
//@Api(value = "MessageController", description = "Contrôleur pour gérer les messages")
public class MessageController {

    @Autowired
    private MessagesServices messagesServices;

    @PostMapping("/messages/")
//    @ApiOperation(value = "Enregistrer un message", response = ResponseEntity.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Message envoyé avec succès"),
//            @ApiResponse(code = 500, message = "Erreur lors de l'enregistrement du message")
//    })
    public ResponseEntity<?> saveMessage(@RequestBody Messages messages) {
        try {
             messagesServices.saveMessage(messages);
            return ResponseEntity.ok(Map.of("message","Message send with succes"));
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(Map.of( "message", " Error while saving message"));
        }
    }
}
