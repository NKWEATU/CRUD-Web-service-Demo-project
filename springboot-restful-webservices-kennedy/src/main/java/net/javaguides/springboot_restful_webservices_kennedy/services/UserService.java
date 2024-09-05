package net.javaguides.springboot_restful_webservices_kennedy.services;

import net.javaguides.springboot_restful_webservices_kennedy.dto.UserDto;
import net.javaguides.springboot_restful_webservices_kennedy.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto user);

    void deleteUser(Long userId);
}
