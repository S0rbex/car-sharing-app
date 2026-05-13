package vitalitus.carsharingapp.map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vitalitus.carsharingapp.dto.user.UserRegistrationRequestDto;
import vitalitus.carsharingapp.dto.user.UserResponseDto;
import vitalitus.carsharingapp.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    User toModel(UserRegistrationRequestDto userRegistrationRequestDto);
}
