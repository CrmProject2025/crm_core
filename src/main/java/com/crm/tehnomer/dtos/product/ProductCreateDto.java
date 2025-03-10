package com.crm.tehnomer.dtos.product;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ProductCreateDto {

    private String info;

    private String address;

}
