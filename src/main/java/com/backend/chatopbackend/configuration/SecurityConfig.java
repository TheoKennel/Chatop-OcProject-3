package com.backend.chatopbackend.configuration;

import com.backend.chatopbackend.configuration.jwt.JwtTokenFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration de la sécurité pour l'application.
 * <p>
 * Configure les détails de la sécurité, notamment l'authentification, l'autorisation, et le traitement des JWT.
 * </p>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenFilter jwtAuthenticationFilter;
    private final UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    public SecurityConfig(JwtTokenFilter jwtAuthenticationFilter,
                          UserDetailsService userDetailsService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailsService = userDetailsService;
    }


    /**
     * Fournit un fournisseur d'authentification personnalisé.
     *
     * @return Le fournisseur d'authentification.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Encodeur de mot de passe pour l'authentification.
     *
     * @return L'encodeur de mot de passe.
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return bCryptPasswordEncoder;
    }

    /**
     * Gestionnaire d'authentification pour l'application.
     *
     * @param authenticationConfiguration La configuration d'authentification.
     * Cette configuration automatique utilise généralement le premier AuthenticationProvider ici authenticationProvider() déclaré au dessus
     * @return Le gestionnaire d'authentification.
     * @throws Exception En cas d'erreur dans la configuration.
     */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Configuration de la chaîne de filtres de sécurité pour les requêtes HTTP.
     *
     * @param httpSecurity La configuration de sécurité HTTP.
     * @return La chaîne de filtres de sécurité.
     * @throws Exception En cas d'erreur dans la configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/register", "/api/auth/login").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll()
                        .requestMatchers("/swagger-ui.html", "/v2/api-docs").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, authException) ->
                                response.sendError(
                                        HttpServletResponse.SC_UNAUTHORIZED,
                                        authException.getLocalizedMessage()
                                )
                )
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}