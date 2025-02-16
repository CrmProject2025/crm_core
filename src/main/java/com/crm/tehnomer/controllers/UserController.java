package com.crm.tehnomer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.tehnomer.dtos.ResponseDto;
import com.crm.tehnomer.dtos.order.OrderCreateDto;
import com.crm.tehnomer.dtos.user.CreateClientDto;
import com.crm.tehnomer.entities.User;
import com.crm.tehnomer.services.EmailService;
import com.crm.tehnomer.services.userService.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;

    @PostMapping("/create_client")
    public ResponseEntity<ResponseDto> createClient(
            @Validated @RequestBody CreateClientDto createClientDto) {
        User client = userService.createUserClient(createClientDto);
        
        return ResponseEntity.ok(ResponseDto.toDto("Client created with email: " + client.getEmail()));
    }


}
