package vitalitus.carsharingapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vitalitus.carsharingapp.dto.user.UserLoginRequestDto;
import vitalitus.carsharingapp.dto.user.UserLoginResponseDto;
import vitalitus.carsharingapp.dto.user.UserRegistrationRequestDto;
import vitalitus.carsharingapp.dto.user.UserResponseDto;
import vitalitus.carsharingapp.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(
            summary = "Register a new user",
            description = "Creates a new user account based on the provided registration details."
    )
    @ApiResponse(responseCode = "200", description = "User successfully registered")
    @PostMapping("/registration")
    public UserResponseDto registration(
            @RequestBody @Valid UserRegistrationRequestDto userRegistrationRequestDto) {
        return authenticationService.register(userRegistrationRequestDto);
    }

    @Operation(
            summary = "Authentication a user",
            description = "Login a user from a DB"
    )
    @ApiResponse(responseCode = "200", description = "User successfulle logined")
    @PostMapping("/login")
    public UserLoginResponseDto login(
            @RequestBody @Valid UserLoginRequestDto userLoginRequestDto) {
        return authenticationService.login(userLoginRequestDto);
    }

}
