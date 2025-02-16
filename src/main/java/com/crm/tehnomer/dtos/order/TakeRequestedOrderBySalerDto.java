package com.crm.tehnomer.dtos.order;

import com.crm.tehnomer.entities.enums.OrderStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TakeRequestedOrderBySalerDto {

    @NotNull
    private OrderStatus status;

}
