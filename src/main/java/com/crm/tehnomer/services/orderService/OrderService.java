package com.crm.tehnomer.services.orderService;

import org.springframework.data.domain.Page;

import com.crm.tehnomer.dtos.order.OrderCreateDto;
import com.crm.tehnomer.dtos.order.TakeRequestedOrderBySalerDto;
import com.crm.tehnomer.entities.Order;
import com.crm.tehnomer.entities.User;
import com.crm.tehnomer.entities.enums.OrderStatus;

public interface OrderService {

    void createOrder(OrderCreateDto orderCreateDto, User currentUser);

    Page listOrders(OrderStatus status, int page, int size);

    void editOrderStatus(Long id, TakeRequestedOrderBySalerDto takeRequestedOrderBySaler,
            User currentUser);

}
