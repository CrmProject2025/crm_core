package com.crm.tehnomer.services.userService;

import com.crm.tehnomer.dtos.order.OrderCreateDto;
import com.crm.tehnomer.dtos.user.CreateClientDto;
import com.crm.tehnomer.dtos.user.SignUpClientDto;
import com.crm.tehnomer.dtos.user.SignUpDto;
import com.crm.tehnomer.entities.User;

public interface UserService {
    User createUserClient(CreateClientDto createClientDto);
    void createUser(SignUpDto signUpDto);

}
