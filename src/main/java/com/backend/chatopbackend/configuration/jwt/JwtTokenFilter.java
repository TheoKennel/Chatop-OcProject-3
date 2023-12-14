package com.backend.chatopbackend.configuration.jwt;

import com.backend.chatopbackend.services.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtre JWT pour authentifier les requêtes basées sur le jeton.
 * <p>
 * Ce filtre valide le jeton JWT présent dans les requêtes entrantes et configure le contexte de sécurité.
 * </p>
 */
@Component
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Filtre les requêtes pour extraire et valider le jeton JWT.
     *
     * @param request La requête HTTP entrante.
     * @param response La réponse HTTP.
     * @param filterChain La chaîne de filtres.
     * @throws ServletException Si une erreur de servlet survient.
     * @throws IOException Si une erreur d'entrée/sortie survient.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.extractToken(request);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtTokenProvider.getUsername(token));
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
