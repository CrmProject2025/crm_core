package com.crm.tehnomer.dtos.user;

import com.crm.tehnomer.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserGetDto {
    private Long id;
    private String username;

    public static UserGetDto toDto(User user) {
        UserGetDto userGetDto = new UserGetDto();
        userGetDto.setUsername(user.getUsername());
        userGetDto.setId(user.getId());
        return userGetDto;
    }

    public static List<UserGetDto> toListUser(List<User> userFromDb) {
        return userFromDb.stream().map(UserGetDto::toDto)
                .collect(Collectors.toList());
    }
}
