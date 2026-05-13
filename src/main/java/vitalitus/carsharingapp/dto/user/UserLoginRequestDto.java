package vitalitus.carsharingapp.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoginRequestDto(
        @Email String email,
        @NotBlank(message = "Password cannot be blank") String password) {
}
