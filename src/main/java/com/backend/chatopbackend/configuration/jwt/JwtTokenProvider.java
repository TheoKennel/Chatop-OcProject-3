package com.backend.chatopbackend.configuration.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;


/**
 * Fournisseur de jetons JWT pour l'authentification.
 * <p>
 * Gère la création, la résolution et la validation des jetons JWT pour les utilisateurs authentifiés.
 * </p>
 */
@Component
@Slf4j
public class JwtTokenProvider {

    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    /**
     * Crée un jeton JWT pour un utilisateur authentifié.
     *
     * @param authentication Les informations d'authentification de l'utilisateur.
     * @return Un jeton JWT signé.
     */
    public String createToken(Authentication authentication) {
        UserDetails usersDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        //Le token expire 1h après sa création.
        Date expireDate = new Date(now.getTime() + 3600000);

        return Jwts.builder()
                .setSubject(usersDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }


    /**
     * Extrait le jeton JWT de la requête HTTP.
     *
     * @param request La requête HTTP.
     * @return Le jeton JWT ou null si aucun jeton n'est trouvé.
     */
    public String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            // Extrait le token en enlevant le préfixe Bearer
            return bearerToken.substring(7);
        }
        return null;
    }


    /**
     * Valide le jeton JWT.
     *
     * @param token Le jeton JWT à valider.
     * @return True si le jeton est valide, False sinon.
     */
    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty");
        } catch (SignatureException e) {
            log.error("there is an error with the signature of you token");
        }
        return false;
    }

    /**
     * Récupère le nom d'utilisateur à partir du jeton JWT.
     *
     * @param token Le jeton JWT.
     * @return Le nom d'utilisateur.
     */
    public String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
