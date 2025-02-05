package com.crm.tehnomer.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.tehnomer.dtos.ResponseDto;
import com.crm.tehnomer.dtos.order.OrderCreateByClientDto;
import com.crm.tehnomer.entities.Order;
import com.crm.tehnomer.entities.User;
import com.crm.tehnomer.repositories.OrderRepository;
import com.crm.tehnomer.repositories.UserRepository;
import com.crm.tehnomer.services.orderService.OrderService;
import com.crm.tehnomer.services.userService.UserService;
import com.crm.tehnomer.settings.security.CustomUserDetails;
import com.crm.tehnomer.settings.security.JwtUserDetailsService;
// import com.crm.tehnomer.settings.security.UserService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private UserRepository userRepository;
    private OrderService orderService;
    private UserService userService;

    /**
     * Client create order (request for saler)
     *
     * @param OrderCreateByClientDto orderDto
     * @return message
     */
    @PostMapping("/client")
    public ResponseEntity<ResponseDto> createOrderByClient(
            @Validated @RequestBody OrderCreateByClientDto orderCreateByClientDto) {
        User client = userService.createUserClient(orderCreateByClientDto);
        orderService.createOrder(orderCreateByClientDto, client);
        return ResponseEntity.ok(ResponseDto.toDto("Order created by " + client.getEmail()));
    }

    /**
     * Client create order (request for saler)
     *
     * @param OrderCreateByClientDto orderDto
     * @return message
     */
    @GetMapping("")
    public ResponseEntity<ResponseDto> getRequestedOrders(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        
        Page<Order> orders = orderService.listOrders(page, size);
        // System.out.println(orders.getContent());
        // сделать dto для возвращаемых заказов
        // почекать в gpt
        return ResponseEntity.ok(ResponseDto.toDto();
    }

    // createOrderBySaler

    // надо сюда емайл добавить, но не авторизировать его, только регать и цеплять
    // заказ к клиенту а потом счетчики

    // взять заказ (saler) меняем статус
    // показать все заказы в статусе request
    // изменить заказ, добавить новые позиции(по гайду в миро) это в другом
    // контроллере(order_product)

}
