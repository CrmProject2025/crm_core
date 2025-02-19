package com.crm.tehnomer.controllers.EmailControllers;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.tehnomer.dtos.ResponseDto;
import com.crm.tehnomer.dtos.email.EmailSendDto;
import com.crm.tehnomer.dtos.order.OrderCreateDto;
import com.crm.tehnomer.dtos.order.OrderGetDto;
import com.crm.tehnomer.dtos.order.TakeRequestedOrderBySalerDto;
import com.crm.tehnomer.entities.Order;
import com.crm.tehnomer.entities.User;
import com.crm.tehnomer.entities.enums.OrderStatus;
import com.crm.tehnomer.repositories.UserRepository;
import com.crm.tehnomer.services.orderService.OrderService;
import com.crm.tehnomer.services.userService.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/email")
public class EmailController {
    private EmailServiceClient emailServiceClient;
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getEmailIdTest(@PathVariable("id") Long id,
            Authentication auth) {
        User user = userRepository.findByUsername(auth.getName());
        Long test = emailServiceClient.getEmailIdTest(user.getId());
        return ResponseEntity.ok(ResponseDto.toDto("get test int " + test));
    }

    @PostMapping("")
    public void sendToEmail(@Validated @RequestBody EmailSendDto emailSendDto,
            Authentication auth) {
        emailServiceClient.sendToEmail(emailSendDto);

    }
}
