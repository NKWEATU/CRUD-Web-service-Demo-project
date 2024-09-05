package net.javaguides.springboot_restful_webservices_kennedy.services.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springboot_restful_webservices_kennedy.dto.UserDto;
import net.javaguides.springboot_restful_webservices_kennedy.entity.User;
import net.javaguides.springboot_restful_webservices_kennedy.exception.EmailAlreadyExistsException;
import net.javaguides.springboot_restful_webservices_kennedy.exception.ResourceNotFoundException;
import net.javaguides.springboot_restful_webservices_kennedy.mapper.AutoUserMApper;
import net.javaguides.springboot_restful_webservices_kennedy.mapper.UserMapper;
import net.javaguides.springboot_restful_webservices_kennedy.repository.UserRespository;
import net.javaguides.springboot_restful_webservices_kennedy.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRespository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        //convert UserDto into User JPA Entity
        //User user = UserMapper.mapToUser(userDto);
       // User user = modelMapper.map(userDto, User.class);

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exists For User");
            
        }
        
        
        User user = AutoUserMApper.MAPPER.mapToUser(userDto);

        User savedUser = userRepository.save(user);

        //Convert User JPA entity to UserDto
        //UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

        //UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);

        UserDto savedUserDto = AutoUserMApper.MAPPER.mapToUserDto(savedUser);

        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        //return UserMapper.mapToUserDto(user);
        //return modelMapper.map(user, UserDto.class);

        return AutoUserMApper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
//        return users.stream().map(UserMapper::mapToUserDto)
//                .collect(Collectors.toList());

//        return users.stream().map((user)-> modelMapper.map(user, UserDto.class))
//                .collect(Collectors.toList());


        return users.stream().map((user)-> AutoUserMApper.MAPPER.mapToUserDto(user))
                .collect(Collectors.toList());
    }



    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", user.getId())
        );

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updateUser = userRepository.save(existingUser);

        //return UserMapper.mapToUserDto(updateUser);
        //return modelMapper.map(updateUser, UserDto.class);

        return AutoUserMApper.MAPPER.mapToUserDto(updateUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );


        userRepository.deleteById(userId);
    }
}
