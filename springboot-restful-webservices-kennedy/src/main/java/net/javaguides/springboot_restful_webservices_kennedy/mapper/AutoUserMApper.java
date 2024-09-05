package net.javaguides.springboot_restful_webservices_kennedy.mapper;

import net.javaguides.springboot_restful_webservices_kennedy.dto.UserDto;
import net.javaguides.springboot_restful_webservices_kennedy.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMApper {
    AutoUserMApper MAPPER = Mappers.getMapper(AutoUserMApper.class);

    UserDto mapToUserDto (User user);

    User mapToUser(UserDto userDto);
}
