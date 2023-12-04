package com.backend.chatopbackend.userdetails;

import com.backend.chatopbackend.models.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final Users user;

    public CustomUserDetails(Users user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retourne une liste vide si aucun rôle n'est défini
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // Retourne true si vous ne gérez pas l'expiration du compte
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Retourne true si vous ne gérez pas le verrouillage du compte
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Retourne true si vous ne gérez pas l'expiration des identifiants
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Retourne true si votre logique métier requiert une vérification de l'état "activé" du compte
        // Sinon, vous pouvez adapter cette méthode en fonction de l'état du compte dans votre entité Users
        return true;
    }
}
