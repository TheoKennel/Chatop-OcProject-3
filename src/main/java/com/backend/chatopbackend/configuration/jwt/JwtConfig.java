package com.backend.chatopbackend.configuration.jwt;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.impl.crypto.MacSigner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

//@Configuration
//public class JwtConfig {
//    Dotenv dotenv = Dotenv.load();
//    private String jwtKey = dotenv.get("JWT_KEY");
//
//    @Bean
//    public JwtDecoder jwtDecoder() {
//        SecretKeySpec keySpec = new SecretKeySpec(this.jwtKey.getBytes(), "HmacSHA256");
//        return NimbusJwtDecoder.withSecretKey(keySpec).macAlgorithm(MacAlgorithm.HS256).build();
//    }
//
//    @Bean
//    public JwtEncoder jwtEncoder() {
//        SecretKey key = new SecretKeySpec(jwtKey.getBytes(), "HmacSHA256");
//        return new NimbusJwtEncoder(key);
//    }

//    @Bean
//    public JwtEncoder jwtEncoder() {
//        return new NimbusJwtEncoder(new ImmutableSecret<>(this.jwtKey.getBytes()));
//    }
//}
