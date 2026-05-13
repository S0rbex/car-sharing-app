package vitalitus.carsharingapp.service;

import org.springframework.stereotype.Service;
import vitalitus.carsharingapp.dto.user.UserLoginRequestDto;
import vitalitus.carsharingapp.dto.user.UserLoginResponseDto;
import vitalitus.carsharingapp.dto.user.UserRegistrationRequestDto;
import vitalitus.carsharingapp.dto.user.UserResponseDto;

@Service
public interface AuthenticationService {
    UserResponseDto register(UserRegistrationRequestDto userRegistrationRequestDto);

    UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto);
}
