package vitalitus.carsharingapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import vitalitus.carsharingapp.config.SecurityConfig;
import vitalitus.carsharingapp.dto.user.UserLoginRequestDto;
import vitalitus.carsharingapp.dto.user.UserLoginResponseDto;
import vitalitus.carsharingapp.dto.user.UserRegistrationRequestDto;
import vitalitus.carsharingapp.dto.user.UserResponseDto;
import vitalitus.carsharingapp.exception.RegistrationException;
import vitalitus.carsharingapp.map.UserMapper;
import vitalitus.carsharingapp.model.User;
import vitalitus.carsharingapp.repository.UserRepository;
import vitalitus.carsharingapp.security.JwtUtil;
import vitalitus.carsharingapp.service.AuthenticationService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SecurityConfig securityConfig;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto userRegistrationRequestDto) {
        if (userRepository.findByEmail(userRegistrationRequestDto.email()).isPresent()) {
            throw new RegistrationException(
                    "User with this email: "
                            + userRegistrationRequestDto.email() + " is already exist");
        }
        if (!userRegistrationRequestDto.password()
                .equals(userRegistrationRequestDto.repeatPassword())) {
            throw new RegistrationException("Passwords do not match");
        }
        User model = userMapper.toModel(userRegistrationRequestDto);
        model.setPassword(securityConfig
                .passwordEncoder()
                .encode(userRegistrationRequestDto.password()));
        userRepository.save(model);
        return userMapper.toDto(model);
    }

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(userLoginRequestDto.email(),
                                userLoginRequestDto.password())
                );
        String token = jwtUtil.generateToken(authentication.getName());
        return new UserLoginResponseDto(token);

    }
}
