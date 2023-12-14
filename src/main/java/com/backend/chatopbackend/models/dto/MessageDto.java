package com.backend.chatopbackend.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto {
    private String message;
    private int user_id;
    private int rental_id;
}
