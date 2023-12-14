package com.backend.chatopbackend.configuration.cloudinary;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration pour le service Cloudinary.
 * <p>
 * Cette classe configure et fournit un bean Cloudinary pour gérer les ressources média.
 * </p>
 */
@Configuration
public class CloudinaryConfig {

    @Value("${cloudinary.url}")
    private String cloudinaryUrl;
    @Bean
    public Cloudinary cloudinary(){
        System.out.println(cloudinaryUrl);
        Cloudinary cloudinary = new Cloudinary(cloudinaryUrl);
        cloudinary.config.secure = true;
        return cloudinary;
    }
}
