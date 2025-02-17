package com.crm.tehnomer.dtos.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.crm.tehnomer.dtos.user.UserClientGetDto;
import com.crm.tehnomer.dtos.user.UserGetDto;
import com.crm.tehnomer.entities.Order;
import com.crm.tehnomer.entities.User;
import com.crm.tehnomer.entities.enums.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderGetDto {

    private long id;

    private String info;

    private String address;

    private LocalDateTime dateCreate;

    private OrderStatus status;

    private UserClientGetDto userClientGetDto;

    private UserClientGetDto userSalerGetDto;

    public static OrderGetDto toDto(Order order) {
        OrderGetDto orderGetDto = new OrderGetDto();
        orderGetDto.setAddress(order.getAddress());
        orderGetDto.setDateCreate(order.getDateCreate());
        orderGetDto.setId(order.getId());
        orderGetDto.setInfo(order.getInfo());
        orderGetDto.setStatus(order.getStatus());

        if (order.getClient() != null) {
            orderGetDto.setUserClientGetDto(UserClientGetDto.toDto(order.getClient()));
        }
        if (order.getSaler() != null) {
            orderGetDto.setUserClientGetDto(UserClientGetDto.toDto(order.getSaler()));
        }
        return orderGetDto;
    }

    public static Page<OrderGetDto> toPageOrders(Page<Order> orderFromDb) {
        return orderFromDb.map(OrderGetDto::toDto);
    }

    public static List<OrderGetDto> toListOrders(List<Order> orderFromDb) {
        return orderFromDb.stream().map(OrderGetDto::toDto)
                .collect(Collectors.toList());
    }

}
