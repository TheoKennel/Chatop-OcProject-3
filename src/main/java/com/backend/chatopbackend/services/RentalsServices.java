package com.backend.chatopbackend.services;

import com.backend.chatopbackend.models.Rentals;
import com.backend.chatopbackend.repository.RentalsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class RentalsServices {

    @Autowired
    private RentalsRepository rentalsRepository;

    public Iterable<Rentals> getAllRentals() {
        return rentalsRepository.findAll();
    }

    public Optional<Rentals> getRentals(final Integer id) {
        return rentalsRepository.findById(id);
    }

    public Rentals saveRental(Rentals rentals) {
        Rentals savedRental = rentalsRepository.save(rentals);
        return savedRental;
    }
}
