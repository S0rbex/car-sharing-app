package vitalitus.carsharingapp.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateProfileDto(
        @NotBlank(message = "First name cannot be blank")
        String firstName,
        @NotBlank(message = "Last name cannot be blank")
        String lastName) {
}
