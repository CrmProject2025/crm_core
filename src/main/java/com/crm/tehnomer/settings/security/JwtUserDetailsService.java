package com.crm.tehnomer.settings.security;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crm.tehnomer.entities.User;
import com.crm.tehnomer.exceptions.customException.CustomException;
import com.crm.tehnomer.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new CustomException(HttpStatus.NOT_FOUND, "User not found.");
        }

        if (!user.isEnabled()) {
            throw new CustomException(HttpStatus.FORBIDDEN, "User was banned.");
        }

//        return user;
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>(user.getRoles()));
    }
}