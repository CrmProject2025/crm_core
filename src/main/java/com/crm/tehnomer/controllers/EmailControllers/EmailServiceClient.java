package com.crm.tehnomer.controllers.EmailControllers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.crm.tehnomer.dtos.ResponseDto;
import com.crm.tehnomer.dtos.email.EmailSendDto;

@FeignClient(name = "email-service")
public interface EmailServiceClient {

    @GetMapping("/email/{id}")
    Long getEmailIdTest(@PathVariable("id") Long id);

    @PostMapping("/email")
    void sendToEmail(EmailSendDto emailSendDto);
}