package com.backend.chatopbackend.repository;

import com.backend.chatopbackend.models.Messages;
import org.springframework.data.repository.CrudRepository;

public interface MessagesRepository extends CrudRepository<Messages, Integer> {
}
