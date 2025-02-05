package com.crm.tehnomer.services.userService;

import com.crm.tehnomer.dtos.order.OrderCreateByClientDto;
import com.crm.tehnomer.dtos.user.SignUpClientDto;
import com.crm.tehnomer.dtos.user.SignUpDto;
import com.crm.tehnomer.entities.User;

public interface UserService {
    User createUserClient(OrderCreateByClientDto orderCreateByClientDto);
    void createUser(SignUpDto signUpDto);

}
