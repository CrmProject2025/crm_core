package com.crm.tehnomer.services.orderService;

import org.springframework.data.domain.Page;

import com.crm.tehnomer.dtos.order.OrderCreateByClientDto;
import com.crm.tehnomer.entities.Order;
import com.crm.tehnomer.entities.User;

public interface OrderService {

    Order createOrder(OrderCreateByClientDto orderCreateByClientDto, User currentUser);
    Page listOrders(int page, int size);
    
}
