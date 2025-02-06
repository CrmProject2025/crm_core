package com.crm.tehnomer.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDto {

    @NotNull
    @Email(message = "Некорректный email")
    private String email;

    private int phone;

    @NotNull
    private String password;
}
