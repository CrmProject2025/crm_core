package com.crm.tehnomer.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.tehnomer.dtos.ResponseDto;
import com.crm.tehnomer.dtos.order.OrderCreateDto;
import com.crm.tehnomer.dtos.order.OrderGetDto;
import com.crm.tehnomer.dtos.order.TakeRequestedOrderBySalerDto;
import com.crm.tehnomer.entities.Order;
import com.crm.tehnomer.entities.User;
import com.crm.tehnomer.entities.enums.OrderStatus;
import com.crm.tehnomer.repositories.UserRepository;
import com.crm.tehnomer.services.orderService.OrderService;
import com.crm.tehnomer.services.userService.UserService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @PostMapping("")
    public ResponseEntity<ResponseDto> createOrder(
            @Validated @RequestBody OrderCreateDto orderCreateDto, Authentication auth) {
        User user = userRepository.findByUsername(auth.getName());
        orderService.createOrder(orderCreateDto, user);
        return ResponseEntity.ok(ResponseDto.toDto("Order created by " + user.getEmail()));
    }

    @GetMapping("")
    public Page<OrderGetDto> getRequestedOrders(
            @RequestParam(defaultValue = "REQUEST_STATUS") OrderStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Order> orders = orderService.listOrders(status, page, size);
        return OrderGetDto.toPageOrders(orders);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDto> takeRequestedOrderBySaler(@PathVariable("id") Long id,
            @Validated @RequestBody TakeRequestedOrderBySalerDto takeRequestedOrderBySalerDto,
            Authentication auth) {
        User currentUser = userRepository.findByUsername(auth.getName());
        orderService.editOrderStatus(id, takeRequestedOrderBySalerDto, currentUser);

        return ResponseEntity.ok(ResponseDto.toDto("Request status changed to PROCESSING_BY_THE_SELLER_STATUS"));
    }

    // createOrderBySaler

    // 2 изменить заказ, добавить новые позиции(по гайду в миро) это в другом
    // контроллере(order_product)

    @GetMapping("/test")
    public List<OrderGetDto> testReact(Authentication auth) {
        System.out.println(auth.getName());
        List<Order> orders = orderService.listOrdersTest();
        return OrderGetDto.toListOrders(orders);

    }
}
