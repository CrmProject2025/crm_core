package com.crm.tehnomer.settings.security;



import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.crm.tehnomer.entities.User;

@Service
public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);

    void saveUser(User user);
}