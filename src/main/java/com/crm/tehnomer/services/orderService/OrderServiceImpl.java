package com.crm.tehnomer.services.orderService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.crm.tehnomer.dtos.order.OrderCreateDto;
import com.crm.tehnomer.dtos.order.TakeRequestedOrderBySalerDto;
import com.crm.tehnomer.entities.Order;
import com.crm.tehnomer.entities.User;
import com.crm.tehnomer.entities.enums.OrderStatus;
import com.crm.tehnomer.exceptions.customException.CustomException;
import com.crm.tehnomer.exceptions.exception.OrderNotFoundException;
import com.crm.tehnomer.exceptions.exception.UserNotFoundException;
import com.crm.tehnomer.repositories.OrderRepository;
import com.crm.tehnomer.services.userService.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public void createOrder(OrderCreateDto orderCreateDto, User client) {
        Order order = new Order();
        order.setClient(client);
        order.setInfo(orderCreateDto.getInfo());
        order.setAddress(orderCreateDto.getAddress());
        order.setStatus(OrderStatus.REQUEST_STATUS);
        orderRepository.save(order);
        logger.info("Creating order for user(email): {}", order.getClient().getEmail());

    }

    public Page<Order> listOrders(OrderStatus status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(
                Sort.Direction.DESC, "dateCreate"));
        return orderRepository.findAllByStatus(status, pageable);
    }

    // будет менять статус на любой, который приходит из контроллера
    public void editOrderStatus(Long id, TakeRequestedOrderBySalerDto takeRequestedOrderBySalerDto,
            User currentUser) {
        Order oldOrder = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("not found order with id:" + id));

        oldOrder.setStatus(takeRequestedOrderBySalerDto.getStatus());
        orderRepository.save(oldOrder);
        logger.info("Creating user with name: {}", 123);

    }

}
