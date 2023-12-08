package com.backend.chatopbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RentalsSave {
    private Integer id;
    private String name;
    private BigDecimal surface;
    private BigDecimal price;
    private String description;
}
