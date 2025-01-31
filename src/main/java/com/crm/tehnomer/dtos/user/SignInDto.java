package com.crm.tehnomer.dtos.user;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInDto {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
