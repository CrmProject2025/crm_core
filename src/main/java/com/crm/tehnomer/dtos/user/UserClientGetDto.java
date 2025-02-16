package com.crm.tehnomer.dtos.user;

import com.crm.tehnomer.entities.User;
import com.crm.tehnomer.entities.enums.Role;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserClientGetDto {
    private String email;
    private int phone;
    private Set<Role> roles = new HashSet<>();

    public static UserClientGetDto toDto(User user) {
        UserClientGetDto userClientGetDto = new UserClientGetDto();
        userClientGetDto.setEmail(user.getEmail());
        userClientGetDto.setPhone(user.getPhone());
        userClientGetDto.setRoles(user.getRoles());
        return userClientGetDto;
    }

}
