package com.crm.tehnomer.services;

import com.crm.tehnomer.dtos.user.SignUpDto;
import com.crm.tehnomer.entities.User;
import com.crm.tehnomer.entities.enums.Role;
import com.crm.tehnomer.exceptions.customException.CustomException;
import com.crm.tehnomer.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public void createUser(SignUpDto signUpDto) {

        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "User already exist");
        }

        User user = new User(signUpDto.getUsername(),
                passwordEncoder.encode(signUpDto.getPassword()),
                Role.ROLE_USER);
        user.setActive(true);

        userRepository.save(user);

    }
}