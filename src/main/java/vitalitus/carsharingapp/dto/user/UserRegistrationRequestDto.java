package vitalitus.carsharingapp.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegistrationRequestDto(
        @Email String email,
        @NotBlank(message = "Password cannot be blank")
        @Size(min = 8, max = 32)
        String password,
        @NotBlank(message = "Password cannot be blank")
        String repeatPassword,
        @NotBlank(message = "First name cannot be blank")
        String firstName,
        @NotBlank(message = "Last name cannot be blank")
        String lastName) {
}
