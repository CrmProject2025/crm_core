package com.crm.tehnomer.controllers;

import com.crm.tehnomer.dtos.ResponseDto;
import com.crm.tehnomer.dtos.user.SignUpDto;
import com.crm.tehnomer.entities.User;
import com.crm.tehnomer.repositories.UserRepository;
import com.crm.tehnomer.services.userService.UserService;
import com.crm.tehnomer.settings.security.JwtUserDetailsService;
import com.crm.tehnomer.settings.security.jwt.JwtRequest;
import com.crm.tehnomer.settings.security.jwt.JwtResponse;
import com.crm.tehnomer.settings.security.jwt.JwtTokenUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserLoginController {
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private JwtUserDetailsService jwtUserDetailsService;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Validated @RequestBody SignUpDto signUpDto) {
        userService.createUser(signUpDto);
        return ResponseEntity.ok(ResponseDto.toDto("User registered successfully"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> loginUser(@Validated @RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        final UserDetails userDetails = jwtUserDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(authenticationRequest.getEmail());

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<ResponseDto> getUsers(Authentication auth) {
        logger.info("Creating order for user(email): {}", 1234);
        logger.debug("Creating order for user(email): {}", 1234);
        logger.error("Creating order for user(email): {}", 1234);

        return ResponseEntity.ok(ResponseDto.toDto("users give to you"));
    }
}
