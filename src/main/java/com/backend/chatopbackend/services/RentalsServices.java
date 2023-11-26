package com.backend.chatopbackend.services;

import com.backend.chatopbackend.models.Rentals;
import com.backend.chatopbackend.repository.RentalsRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@Data
@Service
public class RentalsServices {

    @Autowired
    private RentalsRepository rentalsRepository;

    @Autowired
    private Cloudinary cloudinary;

    public Iterable<Rentals> getAllRentals() {
        return rentalsRepository.findAll();
    }

    public Optional<Rentals> getRentals(final Integer id) {
        return rentalsRepository.findById(id);
    }

    public Rentals saveRental(Rentals rentals, MultipartFile imageFile) {
        String imageUrl = uploadImage(imageFile);
        rentals.setPicture(imageUrl);
        return rentalsRepository.save(rentals);
    }

    public void updateRental(Rentals rentalToUpdate, String name, BigDecimal surface, BigDecimal price,
                                String description) {
        rentalToUpdate.setName(name);
        rentalToUpdate.setSurface(surface);
        rentalToUpdate.setPrice(price);
        rentalToUpdate.setDescription(description);
    }

    public String uploadImage(MultipartFile file) {
        try {
            var uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            return (String) uploadResult.get("url");
        } catch (IOException e) {
            return String.format("Error while uploading picture :  %s", e);
        }
    }
}
