package com.backend.chatopbackend.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class RentalsSave {
    private Integer id;
    private String name;
    private BigDecimal surface;
    private BigDecimal price;
    private String description;
}
