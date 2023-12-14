package com.backend.chatopbackend.services;

import com.backend.chatopbackend.models.dto.MessageDto;
import com.backend.chatopbackend.models.entity.Messages;
import com.backend.chatopbackend.models.entity.Rentals;
import com.backend.chatopbackend.models.entity.Users;
import com.backend.chatopbackend.repository.MessagesRepository;
import com.backend.chatopbackend.repository.RentalsRepository;
import com.backend.chatopbackend.repository.UsersRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class MessagesServices {

    @Autowired
    private MessagesRepository messagesRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RentalsRepository rentalsRepository;

    public void saveMessage(MessageDto messages) {
        Rentals rental =  rentalsRepository.findById(messages.getRental_id()).orElse(null);
        Users user = usersRepository.findById(messages.getUser_id()).orElse(null);
        Messages message = new Messages();
        message.setMessage(messages.getMessage());
        message.setRentals(rental);
        message.setUser(user);
        messagesRepository.save(message);
    }
}

