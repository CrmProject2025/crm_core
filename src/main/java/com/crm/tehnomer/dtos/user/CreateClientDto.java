package com.crm.tehnomer.dtos.user;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateClientDto {
    @NotNull
    private String email;

    @NotNull
    private int phone;
}
