package com.crm.tehnomer.controllers;

import com.crm.tehnomer.dtos.ResponseDto;
import com.crm.tehnomer.dtos.user.SignInDto;
import com.crm.tehnomer.dtos.user.SignUpDto;
import com.crm.tehnomer.entities.User;
import com.crm.tehnomer.repositories.UserRepository;
import com.crm.tehnomer.services.UserService;
import com.crm.tehnomer.settings.security.JwtUserDetailsService;
import com.crm.tehnomer.settings.security.jwt.JwtRequest;
import com.crm.tehnomer.settings.security.jwt.JwtResponse;
import com.crm.tehnomer.settings.security.jwt.JwtTokenUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        userService.createUser(signUpDto);
        return ResponseEntity.ok(ResponseDto.toDto("User registered successfully"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> loginUser(@Validated @RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = jwtUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(authenticationRequest.getUsername());

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
        return ResponseEntity.ok(ResponseDto.toDto("users give to you"));
    }
}
