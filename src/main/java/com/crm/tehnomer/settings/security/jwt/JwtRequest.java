package com.crm.tehnomer.settings.security.jwt;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class JwtRequest implements Serializable {

    @NotNull
    @Email(message = "Некорректный email")
    private String email;

    @NotNull
    private String password;
}
