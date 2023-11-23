package com.backend.chatopbackend.services;

import com.backend.chatopbackend.models.Messages;
import com.backend.chatopbackend.repository.MessagesRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class MessagesServices {

    @Autowired
    private MessagesRepository messagesRepository;

    public Messages saveMessage(Messages messages) {
        Messages savedMessage = messagesRepository.save(messages);
        return savedMessage;
    }
}
