package com.crm.tehnomer.settings.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.crm.tehnomer.entities.User;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Можно добавить логику проверки срока действия аккаунта
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Можно добавить логику блокировки аккаунта
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Можно добавить логику проверки срока действия пароля
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    public User getUser() {
        return user;
    }
}
// @Service
// public interface UserService extends UserDetailsService {
// UserDetails loadUserByUsername(String username);

// void saveUser(User user);

// }
