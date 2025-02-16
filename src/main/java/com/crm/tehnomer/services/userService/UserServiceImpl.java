package com.crm.tehnomer.services.userService;

import com.crm.tehnomer.dtos.order.OrderCreateDto;
import com.crm.tehnomer.dtos.user.CreateClientDto;
import com.crm.tehnomer.dtos.user.SignUpClientDto;
import com.crm.tehnomer.dtos.user.SignUpDto;
import com.crm.tehnomer.entities.User;
import com.crm.tehnomer.entities.enums.Role;
import com.crm.tehnomer.exceptions.customException.CustomException;
import com.crm.tehnomer.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public User createUserClient(CreateClientDto createClientDto) {

        String usernameEmail = createClientDto.getEmail();

        if (userRepository.existsByUsername(usernameEmail)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "User already exist");
        }

        // потом надо создать ручку для админа, чтобы он мог получить пароль этот и
        // отправить пользователю на почту, чтобы он мог зайти в аккаунт, после оплаты
        // будет доступ в кабинет для просмотра счетчиков и показаний, автоматически это
        // можно настроить
        User user = new User(usernameEmail,
                passwordEncoder.encode(PasswordGenerator.generatePassword(12)),
                Role.CLIENT_ROLE);
        user.setEmail(createClientDto.getEmail());
        user.setActive(true);
        user.setPhone(createClientDto.getPhone());
        User newUser = userRepository.save(user);

        logger.info("Creating user client with email: {}", newUser.getEmail());
        
        return newUser;

    }

    public void createUser(SignUpDto signUpDto) {

        String usernameEmail = signUpDto.getEmail();

        if (userRepository.existsByUsername(usernameEmail)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "User already exist");
        }

        User user = new User(usernameEmail,
                passwordEncoder.encode(signUpDto.getPassword()),
                Role.SALER_ROLE);
        user.setEmail(signUpDto.getEmail());
        user.setPhone(signUpDto.getPhone());

        user.setActive(true);

        userRepository.save(user);

    }

}