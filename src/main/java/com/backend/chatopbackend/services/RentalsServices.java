package com.backend.chatopbackend.services;

import com.backend.chatopbackend.models.dto.RentalsSave;
import com.backend.chatopbackend.models.entity.Rentals;
import com.backend.chatopbackend.models.entity.Users;
import com.backend.chatopbackend.repository.RentalsRepository;
import com.backend.chatopbackend.repository.UsersRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Data
@Service
public class RentalsServices {

    @Autowired
    private RentalsRepository rentalsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private Cloudinary cloudinary;

    public Iterable<Rentals> getAllRentals() {
        return rentalsRepository.findAll();
    }

    public Optional<Rentals> getRentals(final Integer id) {
        var a = rentalsRepository.findById(id);
        return a;
    }

    public Rentals saveRental(RentalsSave rentalJson, MultipartFile imageFile) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users owner = usersRepository.findByEmail(auth.getName()).orElse(null);
        String imageUrl = uploadImage(imageFile);
        Rentals rental = new Rentals();
        rental.setName(rentalJson.getName());
        rental.setSurface(rentalJson.getSurface());
        rental.setPrice(rentalJson.getPrice());
        rental.setDescription(rentalJson.getDescription());
        rental.setPicture(imageUrl);
        rental.setOwner(owner);
        return rentalsRepository.save(rental);
    }

    public void updateRental(Rentals rentalToUpdate, RentalsSave rentalsSave) {
        rentalToUpdate.setName(rentalsSave.getName());
        rentalToUpdate.setSurface(rentalsSave.getSurface());
        rentalToUpdate.setPrice(rentalsSave.getPrice());
        rentalToUpdate.setDescription(rentalsSave.getDescription());
        rentalsRepository.save(rentalToUpdate);
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
