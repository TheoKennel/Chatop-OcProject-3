package com.backend.chatopbackend.controllers;

import com.backend.chatopbackend.models.Messages;
import com.backend.chatopbackend.services.MessagesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private MessagesServices messagesServices;

    @PostMapping("/message")
    public Messages saveMessage(@RequestBody Messages messages) {
        return messagesServices.saveMessage(messages);
    }
}
