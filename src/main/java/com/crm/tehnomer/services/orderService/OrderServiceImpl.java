package com.crm.tehnomer.services.orderService;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.crm.tehnomer.dtos.order.OrderCreateByClientDto;
import com.crm.tehnomer.dtos.order.TakeRequestedOrderBySalerDto;
import com.crm.tehnomer.entities.Order;
import com.crm.tehnomer.entities.User;
import com.crm.tehnomer.entities.enums.OrderStatus;
import com.crm.tehnomer.exceptions.customException.CustomException;
import com.crm.tehnomer.repositories.OrderRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Override
    public Order createOrder(OrderCreateByClientDto orderCreateByClientDto, User client) {
        Order order = new Order();
        order.setClient(client);
        order.setInfo(orderCreateByClientDto.getInfo());
        order.setAddress(orderCreateByClientDto.getAddress());
        order.setStatus(OrderStatus.REQUEST_STATUS);
        return orderRepository.save(order);
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
        //  не работает, не бросает ошибку
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "order not found"));
        oldOrder.setStatus(takeRequestedOrderBySalerDto.getStatus());
        orderRepository.save(oldOrder);
    }

}
