package com.backend.chatopbackend.controllers;

import com.backend.chatopbackend.configuration.jwt.JwtTokenProvider;
import com.backend.chatopbackend.models.dto.LoginRequest;
import com.backend.chatopbackend.models.entity.Users;
import com.backend.chatopbackend.services.UsersServices;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Contrôleur pour l'authentification des utilisateurs.
 * <p>
 * Ce contrôleur offre des endpoints pour l'enregistrement, la connexion et la récupération des informations de l'utilisateur connecté.
 * </p>
 */
@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class UsersAuthController {

    @Autowired
    private UsersServices usersServices;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    /**
     * Enregistre un nouvel utilisateur dans le système et renvoie un token JWT.
     *
     * @param users Les informations de l'utilisateur à enregistrer.
     * @return Un token JWT pour l'utilisateur enregistré.
     */
    @PostMapping("/register")
    @Operation(summary = "Enregistre un nouvel utilisateur", description = "Enregistre un utilisateur et renvoie un token JWT.")

    public ResponseEntity<?> registerUser(@RequestBody Users users) {
        String userPassword = users.getPassword();
        Users user =  usersServices.registerUser(users);
        Authentication authentication = authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       user.getEmail(),
                       userPassword
               )
       );
        String token = jwtTokenProvider.createToken(authentication);
        return ResponseEntity.ok(Map.of("token", token));
    }

    /**
     * Authentifie un utilisateur et renvoie un token JWT.
     *
     * @param loginRequest Les identifiants de connexion de l'utilisateur.
     * @return Un token JWT si les identifiants sont valides, sinon un message d'erreur.
     */
    @PostMapping("/login")
    @Operation(summary = "Authentifie un utilisateur", description = "Effectue la connexion d'un utilisateur et renvoie un token JWT en cas de succès.")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        String userEmail = loginRequest.getEmail();
        String userPassword = loginRequest.getPassword();
        Authentication authenticate = usersServices.loginUser(userEmail, userPassword);
        if (authenticate != null) {
            String token = jwtTokenProvider.createToken(authenticate);
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }

    /**
     * Récupère les informations de l'utilisateur actuellement connecté.
     *
     * @return Les informations de l'utilisateur connecté.
     */
    @GetMapping("/me")
    @Operation(summary = "Obtient les informations de l'utilisateur connecté", description = "Retourne les détails de l'utilisateur actuellement connecté.")
    public Users getLoggedUser() {
      return usersServices.getConnectedUser();
    }
}